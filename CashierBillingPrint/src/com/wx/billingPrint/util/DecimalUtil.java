package com.wx.billingPrint.util;

import java.math.BigDecimal;

public class DecimalUtil {

	public static double formatDouble(double d) {
		BigDecimal b = new BigDecimal(d);
		return b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

}
