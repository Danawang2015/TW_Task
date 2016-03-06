package com.wx.billingPrint.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
	
	public static Properties getPropertiesFromClasspath(String filename){
        if(filename==null||"".equals(filename))
            return null;
        
        InputStream in = null;
        Properties   properties = null;
        try {
            in = PropertiesUtil.class.getResourceAsStream(filename);
            if (in == null)
                return null;
            
            properties = new Properties();
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null  ) {
                try {
                    in.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
 
        return properties;
    }

}
