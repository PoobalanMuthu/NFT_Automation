package com.mop.qa.Utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.mop.qa.testbase.PageBase;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;


public class ExtentUtility {
	
	public static ExtentTest test ;
	public static ExtentReports extent ;
	public static String reportFolder ="";
	static Map extentTestMap = new HashMap();
	
	
	
	 public synchronized static ExtentReports getReporter()
	 {
		    PageBase pageBase = new PageBase();
		    String os ="",udid ="",deviceName="",build="";
		    try
		    {
		    	os=pageBase.getAppProperties("report_Environment");
		    	udid= pageBase.getAppProperties("report_DeviceUDID");
		    	deviceName=pageBase.getAppProperties("report_DeviceName");
		    	build=pageBase.getAppProperties("report_Build");		    	 
		    	 
		    }catch(Exception e) {}	
		   
	        if (extent == null) 
	        {
	        	
	        	SimpleDateFormat sdfDateReport = new SimpleDateFormat(
						"yyyy-MM-dd-HH-mm-ss");// dd/MM/yyyy
				Date now = new Date();
				 reportFolder = "HtmlReport_" + sdfDateReport.format(now);
	        	String s=new File("ReportGenerator/"+reportFolder+"/TestReport.html").getPath();
				 extent = new ExtentReports(s, true,Locale.ENGLISH);
				// extent.addSystemInfo("Selenium Version", "2.46");
				// extent.addSystemInfo("Environment", "");
				 extent.addSystemInfo("Execution Environment",os);
				 extent.addSystemInfo("Device Name", deviceName);
				 extent.addSystemInfo("Device UDID", udid);
				 extent.addSystemInfo("Application Build",build);
				 extent.assignProject("AFL Automation Project");		        
			        
	        }
	        
	        return extent;
	    }
	 public static synchronized ExtentTest getTest() {
	        return (ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId()));
	    }

	    public static synchronized void endTest() {
	        extent.endTest((ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId())));
	    }

	    public static synchronized ExtentTest startTest(String testName) {
	        return startTest(testName, "");
	    }

	    public static synchronized ExtentTest startTest(String testName, String desc) {
	        ExtentTest test = extent.startTest(testName, desc);
	        extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);

	        return test;
	    }
	
	
}
