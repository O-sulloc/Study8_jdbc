package com.jh.s1.location;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.jh.s1.util.DBConnector;

public class LocationDAO {

	private DBConnector dbConnector;
	// db

	public LocationDAO() {
		dbConnector = new DBConnector();
	} // locationDAO 객체 만들 때 DBConnector 객체도 만들어라

	// location_id로 조회하는 메서드
	public LocationDTO getOne(LocationDTO loc) throws Exception {
		LocationDTO locationDTO = null;
		Connection con = dbConnector.getConnect();
		String sql = "SELECT * from locations where location_id = ? ";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, loc.getLocation_id());
		ResultSet rs = st.executeQuery();

		if (rs.next()) {
			locationDTO = new LocationDTO();
			locationDTO.setLocation_id(rs.getInt("location_id"));
			locationDTO.setStreet_address(rs.getString("street_address"));
			locationDTO.setPostal_code(rs.getString("postal_code"));
			locationDTO.setCity(rs.getString("city"));
			locationDTO.setState_province(rs.getString("state_province"));
			locationDTO.setCountry_id(rs.getString("country_id"));

		}
		rs.close();
		st.close();
		con.close();

		return locationDTO;
	}

	// 전체조회 메서드
	public List<LocationDTO> getList() throws Exception { // void 나중에 수정
		ArrayList<LocationDTO> ar = new ArrayList<>();
		// 1. db에 로그인해야돼 (만들어놓음)
		Connection con = dbConnector.getConnect();
		// ▲ 로그인 하기 위해 dbConnector 클래스에 만들어둔 getConnect 메서드 호출해온거.
		// dbconnector 클래스의 getConnect 메서드가 무슨 타입을 리턴하기로 했지? Connection 타입을 리턴하기로 함.

		// 2. sql query문 작성
		String sql = "SELECT * FROM locations";

		// 3. 미리 전송
		PreparedStatement st = con.prepareStatement(sql);

		// 4. 물음표 셋팅 (패스)
		// 5. 최종 전송 후 결과 처리
		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			LocationDTO locationDTO = new LocationDTO();

			locationDTO.setLocation_id(rs.getInt("location_id"));
			locationDTO.setStreet_address(rs.getString("street_address"));
			locationDTO.setPostal_code(rs.getString("postal_code"));
			locationDTO.setCity(rs.getString("city"));
			locationDTO.setState_province(rs.getString("state_province"));
			locationDTO.setCountry_id(rs.getString("country_id"));

			ar.add(locationDTO);
		}
		// 6. 역순으로 해체하기
		rs.close();
		st.close();
		con.close();

		return ar;
	}
}
