package com.mop.qa.test.regression;

import org.testng.annotations.Test;

import com.mop.qa.pageobject.NFT_ServicePortal;
//import com.mop.qa.pageobject.TTA_Admin_Quote;
import com.mop.qa.testbase.TestBase;

/**
 * Purpose: Login: As an existing SSP user, Need to login into the SSP portal
 * 
 * @version 1.0 Use Case for Web
 *  Date Modified - 23 Dec 2019
 */
public class Test_NFT_S02_Login_As_Existing_User extends TestBase {
	@Test
	public void Test_NFT_S02_Login_As_Existing_User_TestCase() throws Exception {
		try {
			String URL = rds.getValue("DATA", currentTest, "URL");
			String UserName = rds.getValue("DATA", currentTest, "UserName");
			String Password = rds.getValue("DATA", currentTest, "Password");
                   
			NFT_ServicePortal NFTobj = null;
			NFTobj = selectToolNFT();
			
			
			/*
			 * Scenario 1: Step 1- Login: As an existing SSP user, I need to login into the SSP portal
			 */
			
			NFTobj.loginAsExistingUser(URL, UserName, Password);
			NFTobj.logout();
			NFTobj.remoteDriver.close();
			
		   //NFTobj.O2A_Stream_Current_Test_Results();
		   
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
