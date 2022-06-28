package com.example.merkletesting.Model.Carts;

import java.util.List;

public class Cart{
	private String date;
	private int V;
	private int id;
	private int userId;
	private List<Product> products;

	public String getDate(){
		return date;
	}

	public int getV(){
		return V;
	}

	public int getId(){
		return id;
	}

	public int getUserId(){
		return userId;
	}

	public List<Product> getProducts(){
		return products;
	}
}