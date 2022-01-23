package com.jh.s1.start;

import java.util.ArrayList;
import java.util.List;

import com.jh.s1.department.DepartmentController;
import com.jh.s1.department.DepartmentDAO;
import com.jh.s1.department.DepartmentDTO;
import com.jh.s1.department.DepartmentView;
import com.jh.s1.location.LocationDAO;
import com.jh.s1.location.LocationDTO;
import com.jh.s1.location.LocationView;
import com.jh.s1.util.DBConnector;

public class StartMain {

	public static void main(String[] args) {
		System.out.println("DB 연동 테스트 시작");

		// start메서드 테스트
		FrontController frontController = new FrontController();

		try {
			frontController.mainStart();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//
//// ======================== department test=====================
//		// ▼ 객체 만들기
//		DepartmentDAO departmentDAO = new DepartmentDAO();
////		DepartmentView departmentView = new DepartmentView();
//
//		try {
//			DepartmentDTO departmentDTO = new DepartmentDTO();
//			departmentDTO.setDepartment_id(20);
//			departmentDTO = departmentDAO.getOne(departmentDTO);
////			DepartmentDTO departmentDTO=departmentDAO.getOne(20);
		// ▲부서번호가 20번인 부서의 정보를 departmentDTO라는 가방에 담아서 보여줘. 
//			System.out.println(departmentDTO.getDepartment_name());
		// ▲dto에서 부서name꺼내서 프린트해줘
////			List<DepartmentDTO> ar = departmentDAO.getList();
//			// getlist의 결과물이 list<departmentDTO>니까 (뭐라는? 잘 이해안감;ㅅㅂ)
//			// 그니까 dao.getlist를 호출하면 departmentdto가 결과물인거임
//			// 이 결과물을 점검하기 위해 ar로 꺼내서 view에서 보고 싶은거지
//
////			departmentView.view(ar);
//			// ▲ departmentDAO.getList();에서 꺼낸 자료들을 view에서 보고 싶음
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//// ============================= Location test=====================
////		LocationDAO locationDAO = new LocationDAO();
////		LocationView locationView = new LocationView();
////
////		try {
////			List<LocationDTO> ar = locationDAO.getList();
////			locationView.view(ar);
////		} catch (Exception e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}

		System.out.println("DB 연동 테스트 끝");
	}

}
