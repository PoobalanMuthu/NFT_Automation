package com.mop.qa.test.regression;

import org.testng.annotations.Test;

import com.mop.qa.pageobject.NFT_ServicePortal;
//import com.mop.qa.pageobject.TTA_Admin_Quote;
import com.mop.qa.testbase.TestBase;

/**
 * Purpose: Use Case : 1 - New Registration:  As a first time user, I need to register myself into the SSP Portal
 * 
 * @version 1.0 Use Case for Web
 *  Date Modified - 03 Jan 2020
 */


public class Test_NFT_S01_New_Registration extends TestBase {
	@Test
	public void Test_NFT_S01_New_Registration_TestCase() throws Exception {
		try {
			String webURL = rds.getValue("DATA", currentTest, "NFTUrl");
			String Name = rds.getValue("DATA", currentTest, "UserName");
			String EmailRegister = rds.getValue("DATA", currentTest, "EmailRegister");
			String Password = rds.getValue("DATA", currentTest, "Password");
			String ConfirmPassword = rds.getValue("DATA", currentTest, "ConfirmPassword");
			
			
			NFT_ServicePortal NFTobj = null;
			NFTobj = selectToolNFT();
			
			
			/*
			 * Scenario 1: Step 1- New Registration: As a first time user, I need to register
			 *  myself into the SSP Portal
			 */
			
			NFTobj.newRegisteration(webURL, Name, Password,ConfirmPassword,EmailRegister);

			
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
