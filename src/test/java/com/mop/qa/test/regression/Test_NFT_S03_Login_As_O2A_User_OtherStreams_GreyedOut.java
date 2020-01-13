package com.mop.qa.test.regression;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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
public class Test_NFT_S03_Login_As_O2A_User_OtherStreams_GreyedOut extends TestBase {
	@Test
	public void Test_NFT_S03_Login_As_O2A_User_OtherStreams_GreyedOut_TestCase() throws Exception {
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
			
			NFTobj.Login_As_SSPUser_Check_GreyedOut_OtherStreams();
			 
	        NFTobj.logout();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
