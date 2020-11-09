package com.wcf.report;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.wcf.model.CellPhone;
import com.wcf.model.CellPhoneUsage;

@Component
public class ReportImpl {

	@Value("classpath:java-developer-cell-phone-usage-master/CellPhone.csv")
	Resource cellphone;
	
	@Value("classpath:java-developer-cell-phone-usage-master/CellPhoneUsageByMonth.csv")
	Resource cellphoneUsage;
	
	public Map<String, CellPhone> initialData() throws IOException {
		Map<String, CellPhone> res = new HashMap<String, CellPhone>();

		
		try (InputStream cellphoneResource = cellphone.getInputStream(); 
				BufferedReader reader = new BufferedReader(new InputStreamReader(cellphoneResource));
			 InputStream cellphoneUsageResource = cellphoneUsage.getInputStream();
				BufferedReader reader2 = new BufferedReader(new InputStreamReader(cellphoneUsageResource))	) {
			
			reader.lines().map(this::mapCellPhone).forEach(cp -> {
				res.put(String.valueOf(cp.getEmployeeId()), cp);
			});
			
			reader2.lines().forEach(s -> this.updateUsage(s, res));
		}
		
		return res;

	}
	
	private void updateUsage(String s, Map<String, CellPhone> res) {
		String[] str = s.split(",");
		if (str.length != 4) throw new RuntimeException("Wrong Input Source of CellPhone");
		CellPhone cp = res.get(str[0]);
		if (cp == null) return;
		
		SimpleDateFormat sf = new SimpleDateFormat("MM/dd/yyyy");
		sf.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date date;
		try {
			date = sf.parse(str[1]);
		} catch (ParseException e) {
			throw new RuntimeException("Wrong Input Source of CellPhone Purchase Date");
		}
		int month = date.getMonth() + 1;
		int year = date.getYear() + 1900;
		Map<Integer, float[]> dataUsage = cp.getUsage().getDataUsed();
		Map<Integer, int[]> miniteUsage = cp.getUsage().getMinutesUsed();
		
		if(miniteUsage.containsKey(year)) miniteUsage.get(year)[month] += Integer.parseInt(str[2]);
		else {
			int[] mins = new int[13];
			mins[month] =  Integer.parseInt(str[2]);
			miniteUsage.put(year, mins);
		}
		
		if(dataUsage.containsKey(year)) dataUsage.get(year)[month] +=  Float.parseFloat(str[3]);
		else {
			float[] datas = new float[13];
			datas[month] = Float.parseFloat(str[3]);
			dataUsage.put(year, datas);
		}
	}
	
	
	private CellPhone mapCellPhone(String s) {
		String[] str = s.split(",");
		if (str.length != 4) throw new RuntimeException("Wrong Input Source of CellPhone");
		CellPhone cp = new CellPhone();
		cp.setEmployeeId(Integer.parseInt(str[0]));
		cp.setEmployeeName(str[1]);
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		sf.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date date;
		try {
			date = sf.parse(str[2]);
		} catch (ParseException e) {
			throw new RuntimeException("Wrong Input Source of CellPhone Purchase Date");
		}
		cp.setPurchaseDate(date);
		cp.setModel(str[3]);
		cp.setUsage(new CellPhoneUsage());
		return cp;
	}
}
