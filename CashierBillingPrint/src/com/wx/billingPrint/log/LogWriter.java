package com.wx.billingPrint.log;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class LogWriter {
	private static LogWriter log = new LogWriter();
	private PrintStream logFile = null;

	public static LogWriter getLog() {
		return log;
	}

	public void setLogFile(String filename, boolean append) throws FileNotFoundException {
		if (null != filename && filename.equals("stdout")) {
			logFile = System.out;
		} else {
			String logFileName = filename == null ? "./clem_batch.log" : filename;
			try {
				logFile = new PrintStream(new FileOutputStream(logFileName, append), false, "UTF-8");
			} catch (UnsupportedEncodingException ueE) {
				logFile = new PrintStream(new FileOutputStream(logFileName, append));
			}
		}
	}

	public void println(String message) {
		getLogStream().println(message);
	}

	public void print(String message) {
		getLogStream().print(message);
	}

	private PrintStream getLogStream() {
		return logFile != null ? logFile : System.err;
	}

	private LogWriter() {
	};

}
