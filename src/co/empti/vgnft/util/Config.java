package co.empti.vgnft.util;

import java.io.UnsupportedEncodingException;
import java.util.ResourceBundle;
import java.util.MissingResourceException;

public class Config {

	private static ResourceBundle beanProps;
	private static ResourceBundle adminProps;
	
	static {
		beanProps = ResourceBundle.getBundle( "Config" );
//		adminProps= ResourceBundle.getBundle( "admin" );
		
	}
	
	public static String getProperty(String key) {
		String retStr = null;
		String str_source = null;
		byte[] byte_ret = null;
		try {
			str_source = beanProps.getString( key );
			try {
				byte_ret = str_source.getBytes("ISO-8859-1");
			} catch (UnsupportedEncodingException e) {
				// shrug
			}
			try {
				retStr = new String(byte_ret,"EUC-KR");
			} catch (UnsupportedEncodingException e) {
				// shrug
			}
		} catch (MissingResourceException mre) {
			// shrug
		}
		
		return retStr;
	}
	
	public static String getProperty(String key, String defaultValue) {
		String value = getProperty( key );
		if ( value == null )
			value = defaultValue;
		return value;
	}
	
	public static String getAdminProperty(String key) {
		String retStr = null;
		String str_source = null;
		byte[] byte_ret = null;
		try {
			str_source = adminProps.getString( key );
			try {
				byte_ret = str_source.getBytes("ISO-8859-1");
			} catch (UnsupportedEncodingException e) {
				// shrug
			}
			try {
				retStr = new String(byte_ret,"EUC-KR");
			} catch (UnsupportedEncodingException e) {
				// shrug
			}
		} catch (MissingResourceException mre) {
			// shrug
		}
		
		return retStr;
	}
	
	public static String getAdminProperty(String key, String defaultValue) {
		String value = getProperty( key );
		if ( value == null )
			value = defaultValue;
		return value;
	}
	
}
