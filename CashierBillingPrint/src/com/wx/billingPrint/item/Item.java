package com.wx.billingPrint.item;

public class Item {
	private int number;
	private Product prod;
	
	public static Item createItemByBarcode(String barcode, int number) {
		if (barcode == null || "".equals(barcode))
			return null;

		String productDetail = ProductProperties.getProperty(barcode);
		if (productDetail == null)
			return null;

		String[] st = productDetail.split(",");
		if (st.length < 3)
			return null;

		Product prod = new Product(barcode, st[0], Double.valueOf(st[1]), st[2]);

		return new Item(prod,number);
	}
	
	public Item(Product prod) {
		this.prod = prod;
		number = 1;
	}
	
	public Item(Product prod, int num) {
		this.prod = prod;
		number = num;
	}
	
	public void addNumber(int num){
		number +=num;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	public Product getProd() {
		return prod;
	}

	public void setProd(Product prod) {
		this.prod = prod;
	}

	public double getPrice() {
		return this.prod.getPrice();
	}
	
	public String getName(){
		return this.prod.getName();
	}
	
	public String getNumberUnit(){
		return this.prod.getNumberUnit();
	}
	
	public String getBarcode(){
		return this.prod.getBarcode();
	}
	
}