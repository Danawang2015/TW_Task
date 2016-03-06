package com.wx.billingPrint.billing;

import com.wx.billingPrint.item.Item;

public interface IBillingStrategy {
	
	public Billing createBilling(Item item);

}
