package com.jh.s1.department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import com.jh.s1.util.DBConnector;

public class DepartmentDAO {
	// dao = date access object
	// 즉 db에 있는 데이터를 꺼내오는 클래스!!

	private DBConnector dbConnector;
	// util 패키지에 만들어놓은 DBConnector 클래스의 getConnect를 메서드를 사용해야 1.db로그인을 할 수 있어
	// 근데 왜 굳이 접근지정자를 private을 썼느냐? 여기 DAO 내에서만 db에 접속할 수 있도록 할거니까!!

	public DepartmentDAO() {
		dbConnector = new DBConnector();
		// departmentDAO 객체가 만들어질 때 dbConnector 객체도 한 번 만들어져야함.
		// 위에 private dbConnector만 해놓고 생성자를 안만들었잖아. 생성자로 객체 만들어야지
	}

	// 부서번호로 조회하는 메서드
	public DepartmentDTO getOne(DepartmentDTO dep) throws Exception {
		DepartmentDTO departmentDTO = null;
		// 왜 null? dto가 레퍼런스타입이니까 초기값을 null로 넣어주는거지.
		// 왜 넣는데?
		// 만약에 departmentController에서 있지도 않은 1000번 부서번호 넣으면 어떻게 됨?
		// 밑에 if (rs.next())에 next가 false니까(데이터가 없으니까) if문이 실행이 안되겠지.
		// 그러면 if문 안에 departmentDTO = new DepartmentDTO(); 객체도 생성 안되겠지.
		// 그러면 return departmentDTO하면 무슨 값이 리턴되겠어? null이 리턴되겠지.

		Connection con = dbConnector.getConnect();
		String sql = "select * from departments where department_id = ? ";
		// 물음표는 뭐야? java injection 해킹 당하지 않으려고 ?로 쓴거임
		// 처음에 String sql = "select * from departments where department_id =" +
		// department_id; 이렇게 써놓음
		// 이렇게 쓰면 해킹(java injection)당할 위험이 높음 그래서 물음표로 대체하는 거임.

		// 3. 쿼리문 미리 전송해서 db에게 준비시키기
		// ▼ 뭘 준비시켜? 물음표 보낼거니까 준비하라고
		PreparedStatement st = con.prepareStatement(sql);

		// 4. ? 값 세팅
		// ▼st.set데이터타입(int 타입의 index, 값)
		st.setInt(1, dep.getDepartment_id());
		// 왜 setInt냐? 부서_id가 int타입이 들어갈거잖아. 즉 물음표(?)에 10번, 20번같은 int타입이 들어갈건잖아!
		// 근데 그 부서 번호 어디서 받아올건데? 
		// 첫번째 물음표에 department_id를 넣어주세요~
		// index는 물음표의 순서를 말하는거임.
		// 근데!!! 자바에서는 인덱스 번호가 0번부터 시작했지만 오라클에서는 1번부터 시작임!!!
		// 만약에 select ? from departments where department_id = ? 라고 나와있으며ㅑㄴ
		// select 뒤에 물음표가 1번이고 마지막 물음표는 2번인거
		

		ResultSet rs = st.executeQuery();

		// 데이터가 하나만 올거니까 while문 돌릴 필요가 없다.
		if (rs.next()) {
			// 왜 여기는 while 반복문이 아니고 if문이야?
			// 일단 부서번호 입력하면 정보가 하나만 나오잖아? 부서 번호 10번의 정보 하나, 20번의 정보 하나인데 반복할 이유가 없지
			// 그리고 부서번호 300번에는 정보 있어? 없어
			// 즉 정보가 있거나 없거나 둘 중 하나인 거지. => rs.next가 있으면(데이터가 있으면) true 없으면 false
			// 그러니까 true, false 값을 처리하는 if를 쓰면 만사형통이다.

			departmentDTO = new DepartmentDTO();
			// 객체가 있어야 데이터를 집어넣을 수 있지~
			// 그리고 rs.next에 데이터가 있어야 dto에도 데이터를 집을 수 있으니까 if 문 안에 넣어야지

			departmentDTO.setDepartment_id(rs.getInt("department_id"));
			departmentDTO.setDepartment_name(rs.getString("department_name"));
			departmentDTO.setManager_id(rs.getInt("manager_id"));
			departmentDTO.setLocation_id(rs.getInt("location_id"));

			// 한 줄(row)만 받을거니까 arrayList 만들 필요 없음. 바로 리턴하자~
		}

		rs.close();
		st.close();
		con.close();

		return departmentDTO;
	}

	// 전체조회하는 메서드
	// db에서 select * From departments; 했었잖아
	// 그 때 나온 리스트들 자바로 가져오는 메서드 만들자
	public List<DepartmentDTO> getList() throws Exception {
		ArrayList<DepartmentDTO> ar = new ArrayList<>();

		// 1. db 로그인
		Connection con = dbConnector.getConnect();
		// ▲ 로그인 하기 위해 dbConnector 클래스에 만들어둔 getConnect 메서드 호출해온거.
		// dbconnector 클래스의 getConnect 메서드가 무슨 타입을 리턴하기로 했지? Connection 타입을 리턴하기로 함.
		// 에러(밑줄)는 왜 뜨냐면 예외처리 안해서. throws로 던져주기

		// 2. SQL Query문 작성
		// java에서 sql 작성할 때는 ; 붙이면 안됨!!
		// "SELECT * FROM DEPARTMENTS;"; 이렇게하면 에러남
		String sql = "SELECT * FROM DEPARTMENTS";

		// 3. 작성한 sql queary를 미리! 전송
		PreparedStatement st = con.prepareStatement(sql);

		// 4. ? 물음표의 값을 세팅하기. 없으면 통과하면 됨. 위에 getOne 메서드에서 자세히 설명 ㅇㅇ

		// 5. 이제 최종! 전송하고 그 결과물을 처리하기
		// oracle에 resultset에 있는 결과물을 여기로 가져와야 돼

		ResultSet rs = st.executeQuery(); // st의 쿼리를 실행해. 그리고 rs에 넣어
		// resultset은 뭐냐? 방금 위에서 실행한 sql문 있잖아 select * from departments.
		// 이 select문이 오라클(sql developer)에서 실행되면 그 결과물이 ResultSet이라는 곳에 보관이 되거든.. 블로그 참조
		// 그리고 우리는 그 ResultSet에 있는 결과물을 여기 자바로 가져오고 싶은거야
		// 즉 executeQuery로 sql을 실행했지? 그 결과물이 저장되어 있는 오라클 resultSet을 여기로 가져오고 싶다는거임
		// 그걸 어떻게 가져오냐고? 자바의 ResultSet 이라는 인터페이스를 활용하면 됨. 그래서 앞에 resultset 인터펭이스 쓰고 변수명은
		// rs라고 쓴거

		// cursor가 한 줄 씩 읽게 하는 메서드는 -> next() 메서드
		// 이 next()로 데이터가 있으면 true, 없으면 false를 리턴한다
		// while은 왜 돌려? 전체 데이터가 몇 개인지 모르니까 while문으로 반복문 돌려야지.
		while (rs.next()) {
			DepartmentDTO departmentDTO = new DepartmentDTO();

			departmentDTO.setDepartment_name(rs.getString("department_name"));
			departmentDTO.setDepartment_id(rs.getInt("department_id"));
			departmentDTO.setManager_id(rs.getInt("manager_id"));
			departmentDTO.setLocation_id(rs.getInt("location_id"));

			// 와 이렇게 해서 dto 하나가 만들어졌어요
			// 이 데이터가 while문을 다시 만나면 다시 새로운 데이터로 덮어씌어지겠지? 그럼 안돼
			// 이 dto들을 잘 저장해놔야겠다.. 콜렉션이 좋겠다. arraylist를 사용하자
			// 객체 만들었어 위에 ArrayList<DepartmentDTO> ar = new ArrayList<>();
			// ▼ 이제 dto를 ar에 저장하자

			ar.add(departmentDTO);
			// 이거.. while 끝나면 또 지역변수라 못쓰는거 아니냐?
			// 밖에서도 쓸 수 있게 return으로 보내줘야겠다
		}

		// 6. 외부와 연결된 자원을 해제하는 작업하기
		// 연결된 순서의 역순으로 끊어주기
		rs.close();
		st.close();
		con.close();

		return ar;
	}
}
