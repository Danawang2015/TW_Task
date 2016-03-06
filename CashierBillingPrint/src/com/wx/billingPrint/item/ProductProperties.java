package com.wx.billingPrint.item;

import java.util.Properties;

import com.wx.billingPrint.util.Constants;
import com.wx.billingPrint.util.PropertiesUtil;

public class ProductProperties {
	private static Properties PRODUCT_PROPERTIES = PropertiesUtil.getPropertiesFromClasspath(Constants.PRODUCT_BARCODE_MAPPING_FILE);
	
	public static String getProperty(String key){
		return PRODUCT_PROPERTIES.getProperty(key);
	}
	
}
