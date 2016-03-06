package com.wx.billingPrint.billing.impl;

import com.wx.billingPrint.billing.Billing;
import com.wx.billingPrint.billing.IBillingStrategy;
import com.wx.billingPrint.item.Item;

public class NormalBillingStrategy implements IBillingStrategy {
	private static final int DEFAULT_SAVING_NUMBER = 0;

	@Override
	public Billing createBilling(Item item) {
		if(item==null)
			return null;
		
		Billing billing= new Billing(item);
		billing.setTotal(item.getPrice()*item.getNumber());
		billing.setSavingNumber(DEFAULT_SAVING_NUMBER);
		return billing;
	}

}
