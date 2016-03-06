package com.wx.billingPrint.print;

public class PrintDirector {
	private AbstractPrintBuilder builder;

	public PrintDirector(AbstractPrintBuilder builder) {
		this.builder = builder;
	}

	public String buildPrintMessage() {
		StringBuffer sb = new StringBuffer();
		sb.append(builder.buildTitle());
		sb.append(builder.buildDetail());
		sb.append(builder.buildSaving());
		sb.append(builder.buildSumary());
		return sb.toString();
	}

}
