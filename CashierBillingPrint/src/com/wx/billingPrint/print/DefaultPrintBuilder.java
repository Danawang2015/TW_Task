package com.wx.billingPrint.print;

import java.util.List;

import com.wx.billingPrint.billing.Billing;
import com.wx.billingPrint.util.Constants;
import com.wx.billingPrint.util.DecimalUtil;

public class DefaultPrintBuilder extends AbstractPrintBuilder {

	public DefaultPrintBuilder(List<Billing> billings) {
		super(billings);
	}

	public String buildDetail() {
		StringBuffer sb = new StringBuffer();
		for (Billing billing : billings) {
			PrintHelper helper = new PrintHelper(billing);
			sb.append(helper.buildItemDetailMessage());
		}
		sb.append(Constants.PRINT_SEPARATING_LINE);
		return sb.toString();
	}

	public String buildSaving() {
		boolean haveSavingMessage = false;
		StringBuffer sb = new StringBuffer(Constants.PRINT_SAVING_MESSAGE_TITLE);
		for (Billing billing : billings) {
			PrintHelper helper = new PrintHelper(billing);
			if (helper.buildItemSavingMessage() != null) {
				haveSavingMessage = true;
				sb.append(helper.buildItemSavingMessage());
			}
		}
		sb.append(Constants.PRINT_SEPARATING_LINE);
		return haveSavingMessage ? sb.toString() : "";
	}

	public String buildSumary() {
		double total = 0, saving = 0;
		for (Billing billing : billings) {
			total += billing.getTotal();
			saving += billing.getPrice() * billing.getNumber() - billing.getTotal();
		}

		StringBuffer sb = new StringBuffer();
		sb.append(Constants.ITEM_SUMMARY).append(Constants.SEPRATOR_COLON).append(DecimalUtil.formatDouble(total))
				.append(Constants.PRINT_lINE_BREAK);

		if (saving > 0)
			sb.append(Constants.ITEM_SAVING_MONEY).append(Constants.SEPRATOR_COLON)
					.append(DecimalUtil.formatDouble(saving)).append(Constants.PRINT_lINE_BREAK);

		sb.append(Constants.PRINT_ENDING_LINE);
		return sb.toString();
	}

	public String buildTitle() {
		return Constants.PRINT_DETAIL_MESSAGE_TITLE;
	}

}
