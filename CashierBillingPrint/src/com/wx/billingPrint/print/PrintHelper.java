package com.wx.billingPrint.print;

import com.wx.billingPrint.billing.Billing;
import com.wx.billingPrint.util.Constants;
import com.wx.billingPrint.util.DecimalUtil;

public class PrintHelper {
	private Billing billing;

	public PrintHelper(Billing billing) {
		this.billing = billing;
	}

	public String buildItemDetailMessage() {
		StringBuffer sb = new StringBuffer();
		sb.append(Constants.PRODUCT_NAME).append(Constants.SEPRATOR_COLON).append(billing.getName()).append(Constants.SEPRATOR_COMMA);
		sb.append(Constants.PRODUCT_NUMBER).append(Constants.SEPRATOR_COLON).append(billing.getNumber()).append(billing.getNumberUnit())
				.append(Constants.SEPRATOR_COMMA);
		sb.append(Constants.PRODUCT_PRICE).append(Constants.SEPRATOR_COLON).append(billing.getPrice()).append(Constants.PRODUCT_MONEY_UNIT)
				.append(Constants.SEPRATOR_COMMA);
		sb.append(Constants.ITEM_TOTAL).append(Constants.SEPRATOR_COLON).append(billing.getTotal()).append(Constants.PRODUCT_MONEY_UNIT);
		if (billing.getSavingMoney() > 0)
			sb.append(Constants.SEPRATOR_COMMA).append(Constants.ITEM_SAVING_MONEY).append(Constants.SEPRATOR_COLON).append(getItemSavingMoney())
					.append(Constants.PRODUCT_MONEY_UNIT);

		sb.append(Constants.PRINT_lINE_BREAK);
		return sb.toString();
	}

	private double getItemSavingMoney() {
		return DecimalUtil.formatDouble(billing.getPrice() * billing.getNumber() - billing.getTotal());
	}

	public String buildItemSavingMessage() {
		if (billing.getSavingNumber() < 1)
			return null;

		StringBuffer sb = new StringBuffer();
		sb.append(Constants.PRODUCT_NAME).append(Constants.SEPRATOR_COLON).append(billing.getName()).append(Constants.SEPRATOR_COMMA);
		sb.append(Constants.PRODUCT_NUMBER).append(Constants.SEPRATOR_COLON).append(billing.getSavingNumber()).append(Constants.PRODUCT_MONEY_UNIT)
				.append(Constants.PRINT_lINE_BREAK);

		return sb.toString();
	}

}
