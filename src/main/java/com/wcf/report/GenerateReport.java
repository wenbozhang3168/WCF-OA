package com.wcf.report;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.wcf.model.CellPhone;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Component
public class GenerateReport {
	
	@Value("classpath:cellPhoneUsage.jrxml")
	Resource layout;
	
	public void generateReport(Map<String, CellPhone> res) throws JRException, IOException {
		//TODO implement the report generation and data injection and pdf designs
		
		
		
//		JasperReport jasperReport =  JasperCompileManager.compileReport(layout.getFile().getAbsolutePath());
//		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(res.values());
//		Map<String,Object> params = new HashMap<String,Object>();
//		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
//		JasperExportManager.exportReportToPdfFile(jasperPrint,"output.pdf");
	}
}
