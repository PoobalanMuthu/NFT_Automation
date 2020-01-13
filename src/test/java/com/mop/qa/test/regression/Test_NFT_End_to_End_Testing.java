package com.mop.qa.test.regression;

import java.util.Random;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mop.qa.pageobject.NFT_ServicePortal;
import com.mop.qa.testbase.TestBase;

/**
 * Purpose: Login: New Registration:  As a first time user, I need to register myself into the SSP Portal
 * 
 * @version 1.0 Use Case for Web
 *  Date Modified - 23 Dec 2019
 */
public class Test_NFT_End_to_End_Testing extends TestBase {
	@Test
	@Parameters({"servicePortalUrl","userName","password","payLoad","name_S01","password_S01","confirmPassword_S01"})
	public void Test_NFT_End_to_End_Testing_TestCase(String servicePortalUrl, String userName, String password, String payLoad, String name_S01, String password_S01, String confirmPassword_S01) throws Exception {
		try {
			//String URL = rds.getValue("DATA", currentTest, "NFTUrl");
			//String EmailRegister = rds.getValue("DATA", currentTest, "EmailRegister");
			//String Name = rds.getValue("DATA", currentTest, "UserName");
			//String UserName = rds.getValue("DATA", currentTest, "UserName");
			//String Password = rds.getValue("DATA", currentTest, "Password");
			//String EmailAddress = rds.getValue("DATA", currentTest, "EmailAddress");
			//String ConfirmPassword = rds.getValue("DATA", currentTest, "ConfirmPassword");
			//String payload =rds.getValue("DATA", currentTest, "Payload");
			
			NFT_ServicePortal NFTobj = null;
			NFTobj = selectToolNFT();
			
			
			String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	        StringBuilder salt = new StringBuilder();
	        Random rnd = new Random();
	        while (salt.length() < 8) { // length of the random string.
	            int index = (int) (rnd.nextFloat() * str.length());
	            salt.append(str.charAt(index));
	        }
	        String emailRegister = salt.toString()+"@team.telstra.com";
			/*
			 * Scenario 1: Step 1- New Registration: As a first time user, I need to register
			 *  myself into the SSP Portal
			 */
			
			NFTobj.newRegisteration(servicePortalUrl, name_S01, password_S01,confirmPassword_S01,emailRegister);
			
			
		  /* Scenario 2 :	Login: As an existing SSP user, I need to login into the SSP portal.	 */
			
			
			NFTobj.loginAsExistingUser(servicePortalUrl, userName, password);
			
			/*3 : As an existing O2A User, am I able to trigger any test runs from any other Stream (P2O, R2R, U2C)*/
			
			NFTobj.Trigger_Any_Tests();
			
			/*4 : O2A stream user- Test Run: After logging into the portal as an O2A user, am I be able to successfully run tests that are classified under O2A Streams*/
			
			NFTobj.O2A_Stream_User();
			
			/* 5 : O2A stream user- Current Test Results: After logging into the portal as an O2A user- Can I successfully view the current test results for any particular API that is classified under O2A Stream*/
			
			NFTobj.O2A_Stream_Current_Test_Results();
			
			/*6 : O2A stream user- View& Edit payload: After logging into the portal as O2A user, am I be able to view/edit the payload for O2A */
			
			NFTobj.navigateToPage(payLoad);
			
            /*7 & 8 As an existing SSP user, am I be able to see the result of a particular test by clicking on the outputs from the Test History Page.*/
			
			NFTobj.testOutputsFromHistory_Page();
			
			/* 9 */
			
			NFTobj.OutputsFromHistoryCard_Page();
			
			/* 10 */
			
			NFTobj.O2AStreamUser_launchandStopTest_withoutCheckPayloadinfo();
			
			/*Log out*/
			NFTobj.logout();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
