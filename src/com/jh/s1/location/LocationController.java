package com.jh.s1.location;

import java.util.List;
import java.util.Scanner;

public class LocationController {

	private Scanner sc;
	private LocationDAO locationDAO;
	private LocationView locationView;

	public LocationController() {
		sc = new Scanner(System.in);
		locationDAO = new LocationDAO();
		locationView = new LocationView();

	}

	public void start() throws Exception {
		boolean flag = true;

		while (flag) {
			System.out.println("1. 위치 리스트 출력");
			System.out.println("2. 위치 번호 검색");
			System.out.println("3. 종료");

			System.out.println("메뉴를 선택하세요.");
			int select = sc.nextInt();

			switch (select) {
			case 1:
				List<LocationDTO> ar = locationDAO.getList();
				locationView.view(ar);
				break;

			case 2:
				System.out.println("검색할 위치 번호를 입력하세요.");
				LocationDTO locationDTO = new LocationDTO();
				locationDTO.setLocation_id(sc.nextInt());
				locationDTO = locationDAO.getOne(locationDTO);
				locationView.view(locationDTO);
				break;

			default:
				flag = false;
			}
		}
	}
}
