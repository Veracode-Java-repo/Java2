package util.Data;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Map;
import java.util.Properties;

import util.Common.BaseUtil;

public class LocatorsUtil extends BaseUtil {
	private Properties loc;


	public LocatorsUtil(String LocatorsPath) {
		loc = new Properties();
		try {
			loc.load(new FileReader(LocatorsPath));
		} catch (Exception e) {
			if (LocatorsPath.contains("locators.properties"))
			 { try {
				FileOutputStream outputProp = new FileOutputStream(LocatorsPath);
				FileWriter write = new FileWriter(LocatorsPath);
				write.write("###Locators Should be like ~ login_xpath = //div[] ### Should End With _xpath, _id, _name, _className, _linkText, _css ");
				write.close();
				outputProp.close();
				loc.load(new FileReader(LocatorsPath));

			} catch (Exception e2) {
				e2.printStackTrace();
			} }

		}
		
		for (Map.Entry<Object, Object> entry : loc.entrySet()) {
			Locators.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
		}
		
	}
	
	

}
