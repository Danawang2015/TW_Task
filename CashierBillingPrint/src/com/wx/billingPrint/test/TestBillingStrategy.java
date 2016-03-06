package com.wx.billingPrint.test;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.wx.billingPrint.billing.Billing;
import com.wx.billingPrint.billing.BillingContext;

public class TestBillingStrategy {
	String jsonString = "[aaa,bbb-3,ccc-4]";
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testBillingReturnStrategy() {
		Map<String, Integer> barcodemap = new HashMap<String, Integer>();
		barcodemap.put("ccc", 3);
		
		List<Billing> billings = BillingContext.getInstance().createBilling(barcodemap);
		Assert.assertNotNull( billings.get(0));
		Assert.assertEquals(1, billings.get(0).getSavingNumber());
	}
	
	@Test
	public void testBillingDiscountStrategy() {
		Map<String, Integer> barcodemap = new HashMap<String, Integer>();
		barcodemap.put("aaa", 3);
		
		List<Billing> billings = BillingContext.getInstance().createBilling(barcodemap);
		Assert.assertNotNull( billings.get(0));
		Assert.assertTrue(billings.get(0).getSavingMoney()>0);
	}
	
	@Test
	public void testBillingNormalStrategy() {
		Map<String, Integer> barcodemap = new HashMap<String, Integer>();
		barcodemap.put("ddd", 3);
		
		List<Billing> billings = BillingContext.getInstance().createBilling(barcodemap);
		Assert.assertNotNull( billings.get(0));
		Assert.assertTrue(billings.get(0).getSavingMoney()==0);
		Assert.assertTrue(billings.get(0).getSavingNumber()==0);
	}
	
	@Test
	public void testBillingStrategyConflict() {
		Map<String, Integer> barcodemap = new HashMap<String, Integer>();
		barcodemap.put("bbb", 3);
		
		List<Billing> billings = BillingContext.getInstance().createBilling(barcodemap);
		Assert.assertNotNull( billings.get(0));
		Assert.assertTrue(billings.get(0).getSavingMoney()==0);
		Assert.assertEquals(1, billings.get(0).getSavingNumber());
	}

}
