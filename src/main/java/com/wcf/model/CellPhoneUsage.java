package com.wcf.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;

public class CellPhoneUsage {
	@Getter private Map<Integer, int[]>  minutesUsed; 
	@Getter private Map<Integer, float[]>  dataUsed; 
	
	public CellPhoneUsage() {
		minutesUsed = new HashMap<Integer, int[]>();
		dataUsed = new HashMap<Integer, float[]>();
	}

	@Override
	public String toString() {
		ObjectMapper obj = new ObjectMapper();
		StringBuilder sb = new StringBuilder();
		try {
			sb.append("Minutes Usage: ");
			String res = obj.writerWithDefaultPrettyPrinter().writeValueAsString(minutesUsed);
			sb.append(res);
			sb.append(", Data Usage: ");
			res = obj.writerWithDefaultPrettyPrinter().writeValueAsString(dataUsed);
			sb.append(res);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

}
