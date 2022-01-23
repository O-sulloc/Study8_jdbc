package com.jh.s1.employee;

import java.util.List;
import java.util.Scanner;

public class EmployeeController {

	private Scanner sc;
	private EmployeeDAO employeeDAO;
	private EmployeeView employeeView;

	public EmployeeController() {
		sc = new Scanner(System.in);
		employeeDAO = new EmployeeDAO();
		employeeView = new EmployeeView();
	}

	public void start() throws Exception {
		boolean flag = true;

		while (flag) {
			System.out.println("1. 사원 리스트 출력");
			System.out.println("2. 사원 번호 검색");
			System.out.println("3. 종료");

			System.out.println("메뉴를 선택하세요.");
			int select = sc.nextInt();

			switch (select) {
			case 1:
				List<EmployeeDTO> ar = employeeDAO.getList();
				employeeView.view(ar);
				break;

			case 2:
				System.out.println("검색할 사원 번호를 입력하세요.");
				EmployeeDTO employeeDTO = new EmployeeDTO();
				employeeDTO.setEmployee_id(sc.nextInt());
				employeeDTO = employeeDAO.getOne(employeeDTO);
				employeeView.view(employeeDTO);
				break;

			default:
				flag = false;
			}
		}

	}
}
