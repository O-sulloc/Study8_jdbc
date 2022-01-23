package com.jh.s1.employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.jh.s1.util.DBConnector;

public class EmployeeDAO {

	private DBConnector dbConnector;

	public EmployeeDAO() {
		dbConnector = new DBConnector();
	}

	public EmployeeDTO getOne(EmployeeDTO emp) throws Exception {
		EmployeeDTO employeeDTO = null;
		Connection con = dbConnector.getConnect();
		String sql = "SELECT * from employees where employee_id = ? ";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, emp.getEmployee_id());
		ResultSet rs = st.executeQuery();

		if (rs.next()) {
			employeeDTO = new EmployeeDTO();
			employeeDTO.setEmployee_id(rs.getInt("employee_id"));
			employeeDTO.setFirst_name(rs.getString("first_name"));
			employeeDTO.setLast_name(rs.getString("last_name"));
			employeeDTO.setEmail(rs.getString("email"));
			employeeDTO.setPhone_number(rs.getInt("phone_number"));
			employeeDTO.setHire_date(rs.getInt("hire_date"));
			employeeDTO.setJob_id(rs.getString("job_id"));
			employeeDTO.setSalary(rs.getInt("salary"));
			employeeDTO.setCommission_pct(rs.getInt("commission_pct"));
			employeeDTO.setManger_id(rs.getInt("manager_id"));
			employeeDTO.setDepartment_id(rs.getInt("department_id"));

		}
		rs.close();
		st.close();
		con.close();

		return employeeDTO;
	}

	public List<EmployeeDTO> getList() throws Exception {
		ArrayList<EmployeeDTO> ar = new ArrayList<>();
		Connection con = dbConnector.getConnect();
		String sql = "SELECT * FROM employees";
		PreparedStatement st = con.prepareStatement(sql);
		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			EmployeeDTO employeeDTO = new EmployeeDTO();

			employeeDTO.setEmployee_id(rs.getInt("employee_id"));
			employeeDTO.setFirst_name(rs.getString("first_name"));
			employeeDTO.setLast_name(rs.getString("last_name"));
			employeeDTO.setEmail(rs.getString("email"));
			employeeDTO.setPhone_number(rs.getInt("phone_number"));
			employeeDTO.setHire_date(rs.getInt("hire_date"));
			employeeDTO.setJob_id(rs.getString("job_id"));
			employeeDTO.setSalary(rs.getInt("salary"));
			employeeDTO.setCommission_pct(rs.getInt("commission_pct"));
			employeeDTO.setManger_id(rs.getInt("manager_id"));
			employeeDTO.setDepartment_id(rs.getInt("department_id"));

			ar.add(employeeDTO);
		}
		rs.close();
		st.close();
		con.close();

		return ar;
	}

}
