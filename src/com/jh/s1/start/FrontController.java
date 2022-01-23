package com.jh.s1.start;

import java.util.Scanner;

import com.jh.s1.department.DepartmentController;
import com.jh.s1.employee.EmployeeController;
import com.jh.s1.location.LocationController;

public class FrontController {

	private Scanner sc;
	private DepartmentController departmentController;
	private LocationController locationController;
	private EmployeeController employeeController;

	// 메뉴 1,2번 실행하게 하는거

	public FrontController() {
		// 생성자 만들었음
		sc = new Scanner(System.in);
		departmentController = new DepartmentController();
		locationController = new LocationController();
		employeeController = new EmployeeController();
	}

	public void mainStart() throws Exception {
		boolean flag = true;
		// 무한반복문 돌리려고

		while (flag) {
			System.out.println("1. 직원 관리"); // employ 만들어서 추가
			System.out.println("2. 부서 관리");
			System.out.println("3. 지역 관리"); // location 추가
			System.out.println("4. 종료");

			System.out.println("메뉴를 선택하세요.");
			int select = sc.nextInt();

			switch (select) {
			case 1:
				employeeController.start();
				break;

			case 2:
				departmentController.start();
				break;

			case 3:
				locationController.start();
				break;

			default:
				flag = false;
			}

		}

	}

}
