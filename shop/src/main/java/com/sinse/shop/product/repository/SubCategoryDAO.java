package com.sinse.shop.product.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sinse.shop.common.util.DBManager;
import com.sinse.shop.product.model.SubCategory;
import com.sinse.shop.product.model.TopCategory;

public class SubCategoryDAO {
	DBManager dbManager = DBManager.getInstance();

	public void selectAll() {

	}

	// 하위 카테고리 중 유저가 선택한 상위 카테고리에 소속된 목록만 가ㅣ져오기
	public List selectbyTop(TopCategory topcategory) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList list = new ArrayList(); // size 0

		StringBuffer sql = new StringBuffer();
		sql.append("select * from subcategory where topcategory_id=?");

		conn = dbManager.getConnection();

		try {
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, topcategory.getTopcategory_id());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				SubCategory subCategory = new SubCategory(); // empty
				subCategory.setSubCategory_id(rs.getInt("subcategory_id"));// pk 옮기기
				subCategory.setSub_name(rs.getString("sub_name"));
				subCategory.setTopCategory(topcategory);
				list.add(subCategory);
				// ERD 상에서의 부모인 TopCategory의 정보는 TopCategory 모델 객체로 표현되므로
				// TopCategory 부모 레코드를 반영한 모델 객체 아래의 setter에 대입..
				// sql.delete(0, sql.length());//현재 버퍼에 존재하는 0번째 부터 끝까지 삭제
				// sql.append("select * from topcategory where topcategory_id=");
				// pstmt2=conn.prepareStatement(sql.toString());
				// pstmt2.setInt(1, topcategory_id);
				// rs=pstmt2.executeQuery();

				// subCategory.setTopCategory();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.release(pstmt, rs);
		}
		return list;
	}
}
