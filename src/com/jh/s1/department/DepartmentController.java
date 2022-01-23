package com.jh.s1.department;

import java.util.List;
import java.util.Scanner;

public class DepartmentController {

	private Scanner sc;
	private DepartmentDAO departmentDAO;
	private DepartmentView departmentView;
	// 여기서만 사용하게 하려고 private 으로 객체 선언
	// dao랑 view 연결해줘야하니까 여기에도 적어줘야지. 데이터 받고 보내주려면
	// 여기까지는 선언만 한거임. 아직 값이 없음

	public DepartmentController() { // 이거 생성자 호출한거임..
		// 이렇게 DepartmentController 생성자 호출하면 무슨 일이 일어난다고? 멤버변수값을 초기화한다고
		// 멤버변수가 뭔데? departmentDAO랑 departmetnView랑 sc
		departmentDAO = new DepartmentDAO();
		departmentView = new DepartmentView();
		sc = new Scanner(System.in);
		// departmentDAO랑 departmetnView, sc에도 생성자 호출해줘야지

		// 컨트롤러 객체 만들어질 때, dao랑 view, sc를 호출하는거임.
		// 왜 호출하는데? 생성자 호출해야 객체가 만들어지잖아

	}

	public void start() throws Exception {
		// main에서 했던걸 여기서 하려고
		boolean flag = true;

		while (flag) {
			System.out.println("1. 부서 리스트 출력");
			System.out.println("2. 부서 번호 검색");
			System.out.println("3. 종료");

			System.out.println("메뉴를 선택하세요.");
			int select = sc.nextInt();

			switch (select) {
			case 1:
				List<DepartmentDTO> ar = departmentDAO.getList();
				// 부서 리스트를 출력하려면 dao를 통해야함. getㅣlist메서드 통해서
				// list타입의 제네릭 departmentdto 타입이 올거니까
				departmentView.view(ar);
				break;

			case 2:
				System.out.println("검색할 부서 번호를 입력하세요.");
				DepartmentDTO departmentDTO = new DepartmentDTO();
				departmentDTO.setDepartment_id(sc.nextInt());
				departmentDTO = departmentDAO.getOne(departmentDTO);
				// 위에 getOne()괄호 안에 departmentdto타입이 들어가야 함
				// 그래서 객체 새로 만들었음
				departmentView.view(departmentDTO);
				// dto에서 받은거 어디서 출력하기로 했어? view에서

				break;

			default:
				flag = false;
			}

		}
	}
}
