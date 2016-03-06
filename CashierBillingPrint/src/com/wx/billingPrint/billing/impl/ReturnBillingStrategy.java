package com.wx.billingPrint.billing.impl;

import com.wx.billingPrint.billing.Billing;
import com.wx.billingPrint.billing.IBillingStrategy;
import com.wx.billingPrint.item.Item;

public class ReturnBillingStrategy implements IBillingStrategy {
	private int numberCondition = 1;
	private int muberRet = 0;
	
	public ReturnBillingStrategy(int numberCondition,int muberRet){
		if(numberCondition<1||muberRet<0||numberCondition<muberRet){
			this.numberCondition = 1;
		    this.muberRet = 0;
		}else{
			this.numberCondition = numberCondition;
		    this.muberRet = muberRet;
		}
	}

	@Override
	public Billing createBilling(Item item) {
		if (item == null)
			return null;
		
		Billing billing = new Billing(item);
		
		int needPayNumber = getNeedPayNumberForReturn(item.getNumber());
		int savingNumber = item.getNumber() - needPayNumber;

		billing.setTotal(item.getPrice() * needPayNumber);
		billing.setSavingNumber(savingNumber);
		billing.setSavingMoney(0);
		return billing;
	}
	
	private int getNeedPayNumberForReturn(int number){
		return (number / (numberCondition + muberRet))*numberCondition
		+ number % (numberCondition + muberRet);
	}

}
