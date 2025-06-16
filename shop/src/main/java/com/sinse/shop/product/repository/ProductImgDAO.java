package com.sinse.shop.product.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.sinse.shop.common.exception.ProductImgException;
import com.sinse.shop.common.util.DBManager;
import com.sinse.shop.product.model.ProductImg;

public class ProductImgDAO {
	DBManager dbManager = DBManager.getInstance();

	// 하나의 제품에 딸려있는 이미지 등록
	public void insert(ProductImg productImg) throws ProductImgException{
		Connection conn = null;
		PreparedStatement pstmt = null;

		conn = dbManager.getConnection();

		StringBuffer sql = new StringBuffer();
		sql.append("insert into product_img(filename, product_id) values(?,?)");

		try {
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, productImg.getFilename());
			pstmt.setInt(2, productImg.getProduct().getProduct_id());
			int result = pstmt.executeUpdate();
			if (result < 1) {
				throw new ProductImgException("상품 이미지가 등록되지 않았습니다");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.release(pstmt);
		}
	}
}