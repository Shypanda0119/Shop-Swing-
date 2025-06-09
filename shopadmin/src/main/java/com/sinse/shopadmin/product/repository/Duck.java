package com.sinse.shopadmin.product.repository;

public class Duck {
	String name = "hi";
	private static Duck d;

	// 생성자를 막아버린다..
	private Duck() {
	}

	public static Duck getD() {
		if (d == null) {
			d = new Duck();
		}
		return d;
	}
}
