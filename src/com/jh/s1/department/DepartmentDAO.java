package com.jh.s1.department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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

	// db에서 select * From departments; 했었잖아
	// 그 때 나온 리스트들 자바로 가져오는 메서드 만들자
	public List<DepartmentDTO> getList() throws Exception {
		ArrayList<DepartmentDTO> ar = new ArrayList<>();

		// 1. db 로그인
		Connection con = dbConnector.getConnect();
		// ▲ 로그인 하기 위해 dbConnector 클래스에 만들어둔 getConnect 메서드 호출해온거.
		// dbconnector 클래스의 getConnect 메서드가 무슨 타입을 리턴하기로 했지? Connection 타입을 리턴하기로 함.

		// 2. SQL Query문 작성
		// java에서 sql 작성할 때는 ; 붙이면 안됨!!
		// "SELECT * FROM DEPARTMENTS;"; 이렇게하면 에러남
		String sql = "SELECT * FROM DEPARTMENTS";

		// 3. 작성한 sql queary를 미리! 전송
		PreparedStatement st = con.prepareStatement(sql);

		// 4. ? 물음표의 값을 세팅하기. 없으면 통과하면 됨.

		// 5. 이제 최종! 전송하고 그 결과물을 처리하기
		// oracle에 resultset에 있는 결과물을 여기로 가져와야 돼

		ResultSet rs = st.executeQuery(); // st의 쿼리를 실행해. 그리고 rs에 넣어
		// resultset은 뭐냐? 방금 위에서 실행한 sql문 있잖아 select * from departments.
		// 이 select문이 오라클(sql developer)에서 실행되면 그 결과물이 ResultSet이라는 곳에 보관이 되거든.. 블로그 참조
		// 그리고 우리는 그 ResultSet에 있는 결과물을 여기 자바로 가져오고 싶은거야
		// 즉 executeQuery로 sql을 실행했지? 그 결과물이 저장되어 있는 오라클 resultSet을 여기로 가져오고 싶다는거임
		// 그걸 어떻게 가져오냐고? 자바의 ResultSet 이라는 인터페이스를 활용하면 됨. 그래서 앞에 resultset 인터펭이스 쓰고 변수명은 rs라고 쓴거
		

		// cursor가 한 줄 씩 읽게 하는 메서드는 -> next() 메서드
		// 이 next()로 데이터가 있으면 true, 없으면 false를 리턴한다
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
