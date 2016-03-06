package com.wx.billingPrint.billing;

import com.wx.billingPrint.item.Item;
import com.wx.billingPrint.util.DecimalUtil;

public class Billing {
	private double total;
	private int savingNumber;
	private double savingMoney;
	private Item item;

	public Billing(Item item) {
		this.item = item;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = DecimalUtil.formatDouble(total);
	}

	public int getSavingNumber() {
		return savingNumber;
	}

	public void setSavingNumber(int savingNumber) {
		this.savingNumber = savingNumber;
	}

	public double getSavingMoney() {
		return savingMoney;
	}

	public void setSavingMoney(double savingMoney) {
		this.savingMoney = DecimalUtil.formatDouble(savingMoney);
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
	
	public double getPrice() {
		return this.item.getPrice();
	}
	
	public String getName(){
		return this.item.getName();
	}
	
	public String getNumberUnit(){
		return this.item.getNumberUnit();
	}
	
	public String getBarcode(){
		return this.item.getBarcode();
	}
	
	public int getNumber() {
		return this.item.getNumber();
	}
}