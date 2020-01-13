package com.mop.qa.test.regression;

import org.testng.annotations.Test;

import com.mop.qa.pageobject.NFT_ServicePortal;
import com.mop.qa.testbase.TestBase;

/**
 * Purpose: Login: As an existing SSP user, Need to login into the SSP portal
 * 
 * @version 1.0 Use Case for Web
 *  Date Modified - 23 Dec 2019
 */
public class Test_NFT_S12_Password_Reset extends TestBase {
	@Test
	public void Test_NFT_S3_Password_Reset_TestCase() throws Exception {
		try {
			String webURL = rds.getValue("DATA", currentTest, "URL");
			String EmailAddress = rds.getValue("DATA", currentTest, "EmailAddress");
                   
			NFT_ServicePortal NFTobj = null;
			NFTobj = selectToolNFT();
			
			
			/*
			 * Scenario 1: Step 1- Login: As an existing SSP user, I need to login into the SSP portal
			 */
			
			NFTobj.password_Reset(webURL,EmailAddress);
			

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
