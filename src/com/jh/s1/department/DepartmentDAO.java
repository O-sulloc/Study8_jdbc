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

	public DepartmentDAO() {
		dbConnector = new DBConnector();
	}

	public List<DepartmentDTO> getList() throws Exception {
		ArrayList<DepartmentDTO> ar = new ArrayList<>();

		// db에서 select * From departments; 했었잖아
		// 그 때 나온 리스트들 자바로 가져오려고
		// 1. db 로그인
		Connection con = dbConnector.getConnect();

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
