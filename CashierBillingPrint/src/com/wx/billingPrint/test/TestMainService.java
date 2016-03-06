package com.wx.billingPrint.test;

import org.junit.Assert;
import org.junit.Test;

import com.wx.billingPrint.service.BillingPrintService;
import com.wx.billingPrint.service.IBillingPrintService;


public class TestMainService {
	
	@Test
	public void testMainServiceProcessTrue() {
		String jsonString = "[aaa,bbb-3,ccc-4,aaa]";
		IBillingPrintService service  = new BillingPrintService();
		Assert.assertTrue(service.processBilling(jsonString).isSuccess());
	}
	
	@Test
	public void testMainServiceInputFormatError(){
		String jsonString = "[hhh]";
		IBillingPrintService service  = new BillingPrintService();
		Assert.assertFalse(service.processBilling(jsonString).isSuccess());
	}
	

}
