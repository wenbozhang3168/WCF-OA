package com.wcf;

import java.io.IOException;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.wcf.model.CellPhone;
import com.wcf.report.GenerateReport;
import com.wcf.report.ReportImpl;

import net.sf.jasperreports.engine.JRException;

@SpringBootApplication
public class WcfOaApplication {

	public static void main(String[] args) throws IOException, JRException {
		ConfigurableApplicationContext context = SpringApplication.run(WcfOaApplication.class, args);
		
		ReportImpl report = context.getBean(ReportImpl.class);
		Map<String, CellPhone> res = report.initialData();
		System.out.println(res);
		GenerateReport generateReport = context.getBean(GenerateReport.class);
		generateReport.generateReport(res);
		
		context.close();
	}

}
