package com.sinse.shop.product.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sinse.shop.common.util.DBManager;
import com.sinse.shop.product.model.Size;

public class SizeDAO {
	DBManager dbManager = DBManager.getInstance();
	
	public int insert(Size size) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;

		StringBuffer sql = new StringBuffer();
		sql.append("insert into size(size) values(?)");

		conn = dbManager.getConnection();

		try {
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, size.getSize());// 바인드 변수값 결정
			result = pstmt.executeUpdate(); // DML이 수행되면, 이 쿼리에 의해 영향을 받은 레코드 수 반환
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.release(pstmt);
		}
		return result;
	}
	
	// 등록된 모든 색상 가져오기
		public List selectAll() {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			ArrayList list = new ArrayList();

			conn = dbManager.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select * from size");

			try {
				pstmt = conn.prepareStatement(sql.toString());
				rs = pstmt.executeQuery();// 표 반환

				// rs죽이기 전에 rs가 보유한 데이터를 모델 객체로 옮기자!!!
				// 모델 인스턴스 1건은 레코드 1건을 담는다.
				while (rs.next()) {
					Size size = new Size(); // 레코드 1건을 담는 모델 인스턴스
					size.setSize_id(rs.getInt("size_id"));
					size.setSize(rs.getString("size"));
					list.add(size);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return list;
		}
}
