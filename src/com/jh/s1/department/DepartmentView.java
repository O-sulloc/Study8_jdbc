package com.jh.s1.department;

import java.util.List;

public class DepartmentView {

	public void view(DepartmentDTO departmentDTO) {
		//컨트롤러 통해서 부서dto 정보가 있는 매개변수를 받아오는 view 메서드(오버로딩)
		System.out.print(departmentDTO.getDepartment_id() + "\t");
		System.out.print(departmentDTO.getDepartment_name() + "\t");
		System.out.print(departmentDTO.getManager_id() + "\t");
		System.out.println(departmentDTO.getLocation_id());
	}

	public void view(List<DepartmentDTO> ar) {
		// dao에서 rs.getString("department_name");로 꺼낸 자료들을
		// view에서 보고싶어
		// 근데 모든 정보가 이제 list에 있어 그러니까
		// 그래서 public void view(List<DepartmentDTO> ar) 이걸 선언한거지
		// 다 출력하려면 for문 필요하지. 안에 있는 정보 다 꺼내야되잖아

		for (int i = 0; i < ar.size(); i++) {
			this.view(ar.get(i));
			// 여기 i를 위에 sysout 문장들로 보내버려서 그걸 출력하는거
			System.out.println("===================================");
		}

	}
}
