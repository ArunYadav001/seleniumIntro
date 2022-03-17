package config;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class PropertiesFile {

Properties prop = new Properties();	
String location = "C:\\Users\\arun.y\\eclipse-workspace\\intro\\src\\config\\config.properties";

public  String readProperty(String key) {
//	Properties prop = new Properties();
	String getValue = "";
	try {
		InputStream input = new FileInputStream(location);
		prop.load(input);
		getValue = prop.getProperty(key);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return getValue;
}

public void writeProperty(String key , String value) {
	try {
		OutputStream write = new FileOutputStream(location);
		prop.setProperty(key,value);
		prop.store(write,null);
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
	
}
