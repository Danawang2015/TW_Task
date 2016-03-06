package com.wx.billingPrint.billing;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import com.wx.billingPrint.util.Constants;
import com.wx.billingPrint.util.PropertiesUtil;

public class BillingStrategyConfig {
	private boolean discountCashFlag;
	private double discount;
	private boolean returnCashFlag;
	private int returnValue;
	private int conditionValue;
	private boolean returnAandDiscountConflictFlag;
	private Set<String> discountBarcodes;
	private Set<String> returnBarcodes;
	private static Properties BILLING_STRATEGY_PROPERTIES;

	public BillingStrategyConfig() {
		BILLING_STRATEGY_PROPERTIES = PropertiesUtil.getPropertiesFromClasspath(Constants.BILLING_STRATEGY_FILE);
		discountCashFlag = Boolean.parseBoolean(BILLING_STRATEGY_PROPERTIES.getProperty("discountCashFlag"));
		discount = Double.parseDouble(BILLING_STRATEGY_PROPERTIES.getProperty("discount"));
		returnCashFlag = Boolean.parseBoolean(BILLING_STRATEGY_PROPERTIES.getProperty("returnCashFlag"));
		returnValue = Integer.parseInt(BILLING_STRATEGY_PROPERTIES.getProperty("returnValue"));
		conditionValue = Integer.parseInt(BILLING_STRATEGY_PROPERTIES.getProperty("conditionValue"));
		returnAandDiscountConflictFlag = Boolean
				.parseBoolean(BILLING_STRATEGY_PROPERTIES.getProperty("returnAandDiscountConflictFlag"));
		initDiscountBarcodes();
		initReturnBarcodes();
	}
	
	public boolean belongToReturnStrategy(String key) {
		if (returnCashFlag && returnBarcodes.contains(key))
			return true;

		return false;
	}

	public boolean belongToDiscountStrategy(String key) {
		if (discountCashFlag && discountBarcodes.contains(key))
			return true;

		return false;
	}

	private void initDiscountBarcodes() {
		if (discountCashFlag) {

			String _discountBarcodes = BILLING_STRATEGY_PROPERTIES.getProperty("discountBarcodes");
			if (_discountBarcodes != null && !"".equals(_discountBarcodes)) {
				String[] _barcode = _discountBarcodes.split(Constants.SEPRATOR_COMMA);
				discountBarcodes = new HashSet<String>();
				for (int i = 0; i < _barcode.length; i++) {
					discountBarcodes.add(_barcode[i]);
				}
			}
		}
	}

	private void initReturnBarcodes() {
		if (returnCashFlag) {
			String _returnBarcodes = BILLING_STRATEGY_PROPERTIES.getProperty("returnBarcodes");
			if (_returnBarcodes != null && !"".equals(_returnBarcodes)) {
				String[] _barcode = _returnBarcodes.split(Constants.SEPRATOR_COMMA);
				returnBarcodes = new HashSet<String>();
				for (int i = 0; i < _barcode.length; i++) {
					returnBarcodes.add(_barcode[i]);
				}
			}
		}
	}

	public boolean isDiscountStrategy() {
		return discountCashFlag;
	}

	public double getDiscount() {
		return discount;
	}

	public boolean isReturnStrategy() {
		return returnCashFlag;
	}

	public int getReturnValue() {
		return returnValue;
	}

	public int getConditionValue() {
		return conditionValue;
	}

	public boolean isStrategyConflict() {
		return returnAandDiscountConflictFlag;
	}

	public Set<String> getDiscountBarcodes() {
		return discountBarcodes;
	}

	public Set<String> getReturnBarcodes() {
		return returnBarcodes;
	}

}