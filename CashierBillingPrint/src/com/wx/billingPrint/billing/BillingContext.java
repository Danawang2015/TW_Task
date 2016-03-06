package com.wx.billingPrint.billing;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.wx.billingPrint.billing.impl.DiscountBillingStrategy;
import com.wx.billingPrint.billing.impl.NormalBillingStrategy;
import com.wx.billingPrint.billing.impl.ReturnBillingStrategy;
import com.wx.billingPrint.item.Item;

public class BillingContext {
	
	private static BillingContext instance = new BillingContext();
	private BillingStrategyConfig strategyconfig ;
	
	private BillingContext(){
		strategyconfig = new BillingStrategyConfig();
	}

	public static BillingContext getInstance() {
        return instance;
    }


	public List<Billing> createBilling(Map<String, Integer> barcodemap) {
		if(barcodemap==null||barcodemap.isEmpty())
			return null;
		
		List<Billing> billings = new ArrayList<Billing>();
		for(String barcode:barcodemap.keySet()){
			Billing billing = createBilling(barcode, barcodemap.get(barcode));
			billings.add(billing);
		}
		
		return billings;
	}
	
	private Billing createBilling(String barcode, int number) {
		Item item = Item.createItemByBarcode(barcode,number);
		IBillingStrategy stragety = chooseBillingStrategy(barcode);
		return stragety.createBilling(item);

	}
	
	private IBillingStrategy chooseBillingStrategy(String barcode) {
		IBillingStrategy billingSettlement = null;

		if (strategyconfig.belongToReturnStrategy(barcode)) {
			billingSettlement = new ReturnBillingStrategy(strategyconfig.getConditionValue(), strategyconfig.getReturnValue());
		}

		if (strategyconfig.belongToDiscountStrategy(barcode)) {
			if (billingSettlement == null || !strategyconfig.isStrategyConflict())
				billingSettlement = new DiscountBillingStrategy(strategyconfig.getDiscount());
		}

		if (billingSettlement == null) {
			billingSettlement = new NormalBillingStrategy();
		}

		return billingSettlement;
	}
	
}
