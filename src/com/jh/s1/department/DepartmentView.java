package com.jh.s1.department;

import java.util.List;

public class DepartmentView {

	public void view(List<DepartmentDTO> ar) {
		// dao에서 rs.getString("department_name");로 꺼낸 자료들을
		// view에서 보고싶어
		// 근데 모든 정보가 이제 list에 있어 그러니까
		// 그래서 public void view(List<DepartmentDTO> ar) 이걸 선언한거지
		// 다 출력하려면 for문 필요하지. 안에 있는 정보 다 꺼내야되잖아

		for (int i = 0; i < ar.size(); i++) {
			System.out.print(ar.get(i).getDepartment_id() + "\t");
			System.out.print(ar.get(i).getDepartment_name() + "\t");
			System.out.print(ar.get(i).getManager_id() + "\t");
			System.out.println(ar.get(i).getLocation_id());
			System.out.println("===================================");
		}

	}
}
