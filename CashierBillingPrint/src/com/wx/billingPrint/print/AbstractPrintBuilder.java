package com.wx.billingPrint.print;

import java.util.List;

import com.wx.billingPrint.billing.Billing;
import com.wx.billingPrint.util.Constants;
import com.wx.billingPrint.util.DecimalUtil;

public abstract class AbstractPrintBuilder {
	protected List<Billing> billings;

	public AbstractPrintBuilder(List<Billing> billings) {
		this.billings = billings;
	}
	
	public abstract String buildTitle();
	public abstract String buildDetail();
	public abstract String buildSaving();
	public abstract String buildSumary();

}