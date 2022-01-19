package com.jh.s1.start;

import java.util.List;

import com.jh.s1.department.DepartmentDAO;
import com.jh.s1.department.DepartmentDTO;
import com.jh.s1.department.DepartmentView;
import com.jh.s1.util.DBConnector;

public class StartMain {

	public static void main(String[] args) {
		System.out.println("DB 연동 테스트 시작");

		// ▼ 객체 만들기
		DepartmentDAO departmentDAO = new DepartmentDAO();
		DepartmentView departmentView = new DepartmentView();

		try {
			List<DepartmentDTO> ar = departmentDAO.getList();
			departmentView.view(ar);
			// ▲ departmentDAO.getList();에서 꺼낸 자료들을 view에서 보고 싶음

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("DB 연동 테스트 끝");
	}

}
