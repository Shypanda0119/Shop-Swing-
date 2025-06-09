package com.sinse.shopadmin.product.model;

public class SubCategory{
	//데이터베이스의 컬럼명과 일치하는 멤버변수를 보유하고, 은ㄴ닉화 시켜야함
	private int subCategory_id;
	private String sub_name;
	private TopCategory topCategory;//데이터베이스에서 부모를 표현한 모델을 자식이 보유..
	
	public TopCategory getTopCategory() {
		return topCategory;
	}
	public void setTopCategory(TopCategory topCategory) {
		this.topCategory = topCategory;
	}
	public int getSubCategory_id() {
		return subCategory_id;
	}
	public void setSubCategory_id(int subCategory_id) {
		this.subCategory_id = subCategory_id;
	}
	public String getSub_name() {
		return sub_name;
	}
	public void setSub_name(String sub_name) {
		this.sub_name = sub_name;
	}
	
	@Override
	public String toString() {
		return sub_name;
	}
}
