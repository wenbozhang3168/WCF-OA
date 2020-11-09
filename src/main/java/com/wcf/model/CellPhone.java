package com.wcf.model;

import java.util.Date;

import lombok.Data;

@Data
public class CellPhone {
	private int employeeId;
	private String employeeName;
	private Date purchaseDate;
	private String model;
	private CellPhoneUsage usage;
}
