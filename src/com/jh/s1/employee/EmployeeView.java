package com.jh.s1.employee;

import java.util.List;

public class EmployeeView {

	public void view(EmployeeDTO employeeDTO) {

		System.out.print(employeeDTO.getEmployee_id() + "\t");
		System.out.print(employeeDTO.getFirst_name() + "\t");
		System.out.print(employeeDTO.getLast_name() + "\t");
		System.out.print(employeeDTO.getEmail() + "\t");
		System.out.print(employeeDTO.getPhone_number() + "\t");
		System.out.print(employeeDTO.getHire_date() + "\t");
		System.out.print(employeeDTO.getJob_id() + "\t");
		System.out.print(employeeDTO.getSalary() + "\t");
		System.out.print(employeeDTO.getCommission_pct() + "\t");
		System.out.print(employeeDTO.getManger_id() + "\t");
		System.out.println(employeeDTO.getDepartment_id() + "\t");

	}

	public void view(List<EmployeeDTO> ar) {
		for (int i = 0; i < ar.size(); i++) {
			this.view(ar.get(i));
			System.out.println("=============================");
		}

	}
}
