package com.wx.billingPrint.item;

class Product{
	private String name; 
	private double price;  
	private String numberUnit;
	private String barcode;

	public Product(String barcode,String name,double price, String numberUnit){
		this.barcode = barcode;
		this.name = name;
		this.price = price;
		this.numberUnit = numberUnit;			
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public String getNumberUnit() {
		return numberUnit;
	}

	public void setNumberUnit(String numberUnit) {
		this.numberUnit = numberUnit;
	}

}
