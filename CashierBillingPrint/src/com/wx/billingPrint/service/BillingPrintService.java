package com.wx.billingPrint.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;

import com.wx.billingPrint.billing.Billing;
import com.wx.billingPrint.billing.BillingContext;
import com.wx.billingPrint.log.LogWriter;
import com.wx.billingPrint.print.DefaultPrintBuilder;
import com.wx.billingPrint.print.PrintDirector;
import com.wx.billingPrint.util.Constants;

/**
 *
 */
public class BillingPrintService implements IBillingPrintService {

	private RetData ret;
	private Map<String, Integer> inputMap;

	private Map<String, Integer> getInputMap() {
		return inputMap;
	}
	
	public BillingPrintService(){
		inputMap = new HashMap<String, Integer>();
	}

	public RetData processBilling(String jsonString) {
		try {

			if (!parseItemInput(jsonString)) {
				setFailedPrintMsg(Constants.INPUT_ERROR_MESSAGE);
				return ret;
			}

			List<Billing> currentBilling = BillingContext.getInstance().createBilling(getInputMap());

			if (currentBilling == null || currentBilling.isEmpty()) {
				setFailedPrintMsg(Constants.INPUT_ERROR_MESSAGE);
				return ret;
			}

			PrintDirector director = new PrintDirector(new DefaultPrintBuilder(currentBilling));
			setSuccessPrintMsg(director.buildPrintMessage());
		} catch (Exception e) {
			LogWriter.getLog().print(e.getMessage());
			setFailedPrintMsg(Constants.INPUT_ERROR_MESSAGE);
		}
		return ret;
	}

	private boolean parseItemInput(String input) {
		Map<String, Integer> barcodeMap = new HashMap<String, Integer>();

		try {
			JSONArray jsonArray = new JSONArray(input);

			for (int i = 0; i < jsonArray.length(); i++) {
				String barcode = (String) jsonArray.get(i);
				String[] st = barcode.split(Constants.INPUT_BARCODE_NUMBER_SEPRATOR);

				int num = 1;
				if (st.length > 1) {
					barcode = st[0];
					num = Integer.valueOf(st[1]);
				}
				
				if(barcodeMap.get(barcode)!=null)
					num += barcodeMap.get(barcode);
				
				barcodeMap.put(barcode, num);
			}

		} catch (Exception e) {
			LogWriter.getLog().print(e.getMessage());
			return false;
		}
		inputMap.putAll(barcodeMap);
		return true;

	}

	private void setFailedPrintMsg(String msg) {
		if (ret == null)
			ret = new RetData();
		ret.setSuccess(false);
		ret.setMsg(msg);
	}

	private void setSuccessPrintMsg(String msg) {
		if (ret == null)
			ret = new RetData();
		ret.setSuccess(true);
		ret.setMsg(msg);
	}

}
