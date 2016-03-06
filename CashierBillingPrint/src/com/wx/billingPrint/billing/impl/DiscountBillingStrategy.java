package com.wx.billingPrint.billing.impl;

import com.wx.billingPrint.billing.Billing;
import com.wx.billingPrint.billing.IBillingStrategy;
import com.wx.billingPrint.item.Item;
import com.wx.billingPrint.util.DecimalUtil;

public class DiscountBillingStrategy implements IBillingStrategy {
	
	private double discount = 1;
	
	public DiscountBillingStrategy(double discount){
		if(discount>1||discount<0)
			this.discount = 1;
		else
			this.discount = discount;
	}
	

	@Override
	public Billing createBilling(Item item) {
		if (item == null)
			return null;

		Billing billing = new Billing(item);
		double total = item.getPrice() * item.getNumber();
		double discountMoney = total * discount;
		billing.setTotal(discountMoney);
		billing.setSavingMoney(DecimalUtil.formatDouble(total - discountMoney));
		billing.setSavingNumber(0);
		return billing;
	}

}
