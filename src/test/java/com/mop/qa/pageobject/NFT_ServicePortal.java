package com.mop.qa.pageobject;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.ClickAndHoldAction;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mop.qa.Utilities.ExtentUtility;
import com.mop.qa.Utilities.ReadDataSheet;
import com.mop.qa.testbase.PageBase;
import com.relevantcodes.extentreports.LogStatus;

import ch.qos.logback.classic.boolex.IEvaluator;
import ch.qos.logback.core.joran.action.Action;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSTouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

/**
 * @author Telstra
 * @version 1.0 Use Case for Web NFT SSP Date Modified - 23 Dec 2019
 *
 */

public class NFT_ServicePortal extends PageBase {

	ReadDataSheet rds = new ReadDataSheet();

	public NFT_ServicePortal(RemoteWebDriver remoteDriver) {
		super(remoteDriver);
	}

	
	// Web Elements

	@FindBy(xpath = "//*[@name='usernameOrEmail']")
	private WebElement userName_Web;

	@FindBy(xpath = "//*[@name='password']")
	private WebElement password_Web;
	
	@FindBy(xpath = "//*[@name='Password']")
	private WebElement Confirmpassword_Web;
	
	@FindBy(xpath = "//*[@class='btn btn-warning btn-block']")
	private WebElement Forgotpassword_Web;
	
	@FindBy(xpath = "//*[contains(text(),'Reset Password')]")
	private WebElement RestestpasswordButton_Web;
	
	@FindBy(xpath = "//*[contains(text(),'Sign In')]")
	private WebElement Signin_Web;
	
	@FindBy(xpath = "//*[@class='btn btn-secondary btn-block']")
	private WebElement dontHaveAccount_Web;
	
	@FindBy(xpath = "//*[@name='email']")
	private WebElement emailAddress_Web;
	
	@FindBy(xpath = "//*[@name='name']")
	private WebElement Name_Web;
	
	@FindBy(xpath = "//*[@id='main-content']//div//button[1]")
	private WebElement RegisterButton_Web;
	
	@FindBy(xpath = "//*[@class='alert alert-danger fade show']")
	private WebElement RegisterConformationmessage_Web;
	
	@FindBy(xpath = "//*[@class=' css-2b097c-container']")
	private WebElement TeamDropDown_Web;
	
	@FindBy(xpath = "//*[@id='main-content']//div/form/div[2]/div[1]/div[2]/div/div/div[2]/div")
	private WebElement selectO2A_Web;
	
	@FindBy(xpath = "//*[text()=’O2A’]")
	private WebElement TeamDropDownSelectO2A_Web;
	
	@FindBy(xpath = "//*[contains(text(),'O2A Stream')]")
	private WebElement O2AStreamDashboard;
	
	@FindBy(xpath = "//*[contains(text(),'Demo')]")
	private WebElement Demo;
	 
	@FindBy(xpath = "//*[@id='main-content']//div/div/div[2]/div/div/div/div")
	private WebElement APIunderDemo;
	
	@FindBy(xpath = "//*[@class=' css-1wa3eu0-placeholder']")
	private WebElement SelectScenarioDropDown;
	
	@FindBy(xpath = "//*[contains(text(),'Shakeout')]")
	private WebElement ShakeoutBox;
	
	@FindBy(xpath = "//*[@id='main-content']//div/div/div[5]//div/div[1]/div/div/span[2]")
	private WebElement Launch;
	
	@FindBy(xpath = "//*[@id='main-content']//div/div/div[5]/div/div/div[3]/div/div/span[2]")
	private WebElement viewEditPayload;
	
	@FindBy(xpath = "//*[@id='main-content']//div/div/div[5]/div/div/div[2]/div/div")
	private WebElement CurrentTestResults;
	
	
	
	@FindBy(xpath = "//*[@class='swal2-confirm swal2-styled']")
	private WebElement LaunchConfirmation;
	
	@FindBy(xpath = "//*[contains(text(),'Test History')]")
	private WebElement TestHistory;
	
	@FindBy(xpath = "//*[@id='main-content']/div/div/div[2]/div/div[2]/div/div/table/tbody/tr[2]/td[1]")
	private WebElement ClickFirstTest;
	
	
	
	@FindBy(xpath = "//*[contains(text(),'Portal Insights')]")
	private WebElement PortalInsights;
	
	@FindBy(xpath = "//*[contains(text(),'General User Metrics')]")
	private WebElement GeneralUserMetrics;
	
	@FindBy(xpath = "//*[contains(text(),'Stream Metrics')]")
	private WebElement StreamMetrics;
	
	@FindBy(xpath = "//*[contains(text(),'Specific User Metrics')]")
	private WebElement SpecificUserMetrics;
	
	@FindBy(xpath = "//*[contains(text(),'All User Metrics')]")
	private WebElement AllUserMetrics;
	
	@FindBy(xpath = "//*[contains(text(),'Test Metrics')]")
	private WebElement TestMetrics;
	
	@FindBy(xpath = "//*[@class='td-icon-sm icon-my-profile position-static']")
	private WebElement profileicon;
	
	@FindBy(xpath = "//*[contains(text(),'Logout')]")
	private WebElement logout;
	
	@FindBy(xpath = "//*[@id='root']/div[2]/div/div/nav/div[1]/div/div/a/div/p[1]")
	private WebElement NFTSelfServePortal;
	
	@FindBy(xpath = "//*[contains(text(),'P2O')]")
	private WebElement P2O;
	
	@FindBy(xpath = "//*[contains(text(),'R2R')]")
	private WebElement R2R;
	
	@FindBy(xpath = "//*[contains(text(),'S2P')]")
	private WebElement S2P;
	
	@FindBy(xpath = "//*[contains(text(),'U2C')]")
	private WebElement U2C;
	
	@FindBy(xpath = "//*[@class='swal2-confirm swal2-styled']")
	private WebElement OK;
	
	@FindBy(xpath = "//*[@class='btn btn-outline-primary btn-lg btn-block']")
	private WebElement showPayload;
	
	@FindBy(xpath = "//*[@id='main-content']//div/div[2]/div/div[2]/div/div/table/tbody/tr[1]/td[2]/div/div/input")
	private WebElement TestStatus_Edit;
	
	@FindBy(xpath = "//*[contains(text(),'Stop Test')]")
	private WebElement StopTest;
	
	@FindBy(xpath = "//*[contains(text(),'Yes, stop it!')]")
	private WebElement ConfirmStopTest;
	
	
	
/* -----------*************************** Scripting Started ************************* --------------------*/
	
	
	
	/* Use Case:2 - Login: As an existing SSP user, I need to login into the SSP portal */
	
	/* Use Case:4 - O2A stream user- Test Run */
	/* Use Case:5 - O2A stream user- View& Edit pay load */
	
	
	public void clickOnTab(String tabName) throws Exception 
	{
		remoteDriver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		try
		{
			//clickWithoutWait("//*[@text='"+tabName+"']","Clicking on Tab");
			
			clickWithoutWait("//*[contains(text(),'"+tabName+"')]","Clicking on Tab");
			System.out.println("Clicking on Tab "+tabName);
		}catch (Exception e){}
	}
	
	
	public void logout() throws Exception {
		try {
			
			clickWithoutWait(profileicon, "Profile icon");
			clickWithoutWait(logout, "Logout");
			System.out.println("Sucessfully closed window");
			remoteDriver.close();
				
		} 
		catch (Exception exc) {
			exc.printStackTrace();

		}

		}

	
	
	/* Use Case : 1 
	 * New Registration:  
	 * As a first time user, I need to register myself into the SSP Portal*/

	
	public void newRegisteration(String webURL, String Name, String Password,String ConfirmPassword,String EmailRegister) throws Exception {
		if (toolName.equalsIgnoreCase("Selenium")) {
			enterUrl(webURL);
			click(dontHaveAccount_Web, " Don't have an account ");
			enterText(emailAddress_Web, EmailRegister, "Email Address");
			enterText(Name_Web, Name, "Name");
			
			WebElement Team = remoteDriver.findElement(By.className("css-19bqh2r"));
	        Team.click();
	        Thread.sleep(2000);      
	        remoteDriver.findElement(By.xpath("//*[text()='O2A']")).click();
	        Thread.sleep(2000);
	        
			enterText(password_Web, Password, "Password");
			enterText(Confirmpassword_Web, ConfirmPassword, "Confirm Password");
			click(RegisterButton_Web, "RegisterButton");
			System.out.println("Registered - New Registration completed");
			Thread.sleep(1000);
			//remoteDriver.close();
		}
	}

	

	/* Use Case : 2 
	 * Login :  
	 *  As an existing SSP user, I need to login into the SSP portal. */
	
	public void loginAsExistingUser(String webURL, String UserName, String Password) throws Exception {
		if (toolName.equalsIgnoreCase("Selenium")) {
			enterUrl(webURL);
			enterText(userName_Web, UserName, "User Name");
			enterText(password_Web, Password, "Password");
			click(Signin_Web, " NFT Sign in ");
			System.out.println("Logged on as Existing user");
		}
	}
	
	
	
	/* Use Case : 3
	 * Password Reset :  
	 *  As an existing SSP User, If I forgot my password, I need to be able to reset the password from the portal. */
	
	public void password_Reset(String webURL, String EmailAddress) throws Exception {
		if (toolName.equalsIgnoreCase("Selenium")) {
			enterUrl(webURL);
			click(Forgotpassword_Web,"Forgot Password Button");
			enterText(emailAddress_Web, EmailAddress, "Email address");
			click(RestestpasswordButton_Web,"Retest Password Button");
		}
	}
	
	
	
	/* Use Case : 4
	 * O2A stream user- Test Run :  
	 * After logging into the portal as an O2A user, am I be able to successfully run tests that are classified under O2A Streams */

	
	public void O2A_Stream_User() throws Exception {
		if (toolName.equalsIgnoreCase("Selenium")) {
			
			//click(NFTSelfServePortal, " Portal icon ");
			click(O2AStreamDashboard,"O2A Stream User");
			Thread.sleep(1000);
			click(Demo,"Demo");
			Thread.sleep(1000);
			click(APIunderDemo,"API");
			Thread.sleep(5000);
			
			WebElement ScenarioDropDown = remoteDriver.findElement(By.className("css-1wa3eu0-placeholder"));
			ScenarioDropDown.click();
	        Thread.sleep(2000);      
	        remoteDriver.findElement(By.xpath("//*[text()='Team Demo | Wikipedia Pages']")).click();
	        Thread.sleep(2000);
			
			
	        clickWithoutWait(ShakeoutBox,"Shakeout Tab");
			Thread.sleep(1000);
			clickWithoutWait(Launch,"Launch Tab");
			Thread.sleep(1000);
			clickWithoutWait(LaunchConfirmation,"Launch confirmation Tab");
			Thread.sleep(1000);
			
			System.out.println("Before get text");
			
			
			/*
			
			String fulltext = remoteDriver.findElement(By.xpath("//*[@id='swal2-content']")).getText();
			
			String modified[] = fulltext.split(" :");
			System.out.println("ID : "+modified[2]);
			
			String input[] = modified[2].split("_");
			System.out.println("Input "+input[1]);*/
			
			
			
			WebElement TxtBoxContent = remoteDriver.findElement(By.xpath("//*[@id='swal2-content']")); 
			//TxtBoxContent.getText();
			System.out.println(TxtBoxContent.getText());
			
			clickWithoutWait(OK,"Launch confirmation Tab");
		}
	
}
	
	/* Use Case : 5
	 * O2A stream user- Current Test Results :  
	 * After logging into the portal as an O2A user- Can I successfully view the current test results for any particular API that is classified under O2A Stream */
	
	public void O2A_Stream_view_Edit_Payload() throws Exception {
		if (toolName.equalsIgnoreCase("Selenium")) {
			//click(NFTSelfServePortal, " Portal icon ");
			click(O2AStreamDashboard,"O2A Stream User");
			Thread.sleep(1000);
			click(Demo,"Demo");
			Thread.sleep(1000);
			click(APIunderDemo,"API");
			Thread.sleep(5000);
			
			WebElement ScenarioDropDown = remoteDriver.findElement(By.className("css-1wa3eu0-placeholder"));
			ScenarioDropDown.click();
	        Thread.sleep(2000);      
	        remoteDriver.findElement(By.xpath("//*[text()='Team Demo | Wikipedia Pages']")).click();
	        Thread.sleep(2000);
			
			
			click(ShakeoutBox,"Shakeout Tab");
			Thread.sleep(1000);
			click(viewEditPayload,"view/Edit Payload Tab");
			Thread.sleep(1000);
			//click(LaunchConfirmation,"Launch confirmation Tab");
			
		}
	}
	
	
	
	/* Use Case : 6
	 * O2A stream user- View& Edit Payload :  
	 * After logging into the portal as O2A user, am I be able to view/edit the payload for O2A  */
	
	public void O2A_Stream_Current_Test_Results() throws Exception {
		if (toolName.equalsIgnoreCase("Selenium"))
		{
			click(NFTSelfServePortal, " Portal icon ");
			click(O2AStreamDashboard,"O2A Stream User");
			Thread.sleep(1000);
			click(Demo,"Demo Tab");
			Thread.sleep(1000);
			click(APIunderDemo,"API");
			Thread.sleep(5000);
			
			WebElement ScenarioDropDown = remoteDriver.findElement(By.className("css-1wa3eu0-placeholder"));
			ScenarioDropDown.click();
	        Thread.sleep(2000);      
	        remoteDriver.findElement(By.xpath("//*[text()='Team Demo | Wikipedia Pages']")).click();
	        Thread.sleep(2000);
			
			click(ShakeoutBox,"Shakeout Tab");
			Thread.sleep(1000);
			click(CurrentTestResults,"Current Test Results Tab");
			
			
		}
	}

	
	
	
    String logoXpath = "//div[@class='theme-gradient-primary-secondary clip']";
	
	@FindBy(xpath="//input[@name='usernameOrEmail']")
	WebElement usernameXpath;
	
	@FindBy(xpath="//input[@name='password']")
	WebElement passwordXpath;
	
	String signinXpath = "//*[contains(text(),'Sign In')]";
	String profileXpath = "//*[contains(text(),'Welcome, ')]";
	String o2astreamXpath = "//*[contains(text(),'O2A Stream')]";
	String demoXpath = "//*[@id=\"main-content\"]/div/div/div/div/div/div/div[2]/div/div/div";
	String apiXpath = "//*[@id=\"main-content\"]/div/div/div/div[2]/div/div/div/div/div/div";
	String scernarioXpath = "//*[@id=\"main-content\"]/div/div/div/div[3]/div/div/div/div/div[1]";
	String editpayloadXpath = "//*[@id=\"main-content\"]/div/div/div/div[5]/div/div/div[3]/div/div";
	String clicktoeditXpath = "//*[@id=\"main-content\"]/div/div/div/div[2]/div/div[2]/div/div/div/div/div/div/div/div/div/div[3]/div/div/div[1]/div/div/div[1]/div[2]";
	String editXpath = "//*[@id=\"main-content\"]/div/div/div/div[2]/div/div[2]/div/div/div/div/div/div/div/div/div/div[3]/div/div/div[1]/div/div/div[1]/div/div/div/span[2]";
	@FindBy(xpath="//*[@id=\"main-content\"]/div/div/div/div[2]/div/div[2]/div/div/div/div/div/div/div/div/div/div[3]/div/div/div[1]/div/div/div[1]/div/div/textarea")
	WebElement textboxXpath;
	String saveXpath = "//*[contains(text(),'Save')]";
	@FindBy(xpath="//*[@id=\"root\"]/div[2]/div/div/nav/div[2]/div/div[3]/div[1]/a")
	WebElement userprofileXpath;
	String logoutXpath = "//*[@id=\"user-menu\"]/a[4]/div/span";
		
		public void navigateToPage(String payload) throws Exception {
			try {
				
				this.waitFor(50);
				assertTrue("The NFT SSP Portal Launched Sucessfully - Verified the Logo ", this.elementIsDisplayed(logoXpath));
				click(NFTSelfServePortal, " Portal icon ");
				click(O2AStreamDashboard,"O2A Stream User");
				Thread.sleep(1000);
				click(Demo,"Demo");
				Thread.sleep(1000);
				click(APIunderDemo,"API");
				Thread.sleep(5000);
				
				WebElement ScenarioDropDown = remoteDriver.findElement(By.className("css-1wa3eu0-placeholder"));
				ScenarioDropDown.click();
		        Thread.sleep(2000);      
		        remoteDriver.findElement(By.xpath("//*[text()='Team Demo | Wikipedia Pages']")).click();
		        Thread.sleep(2000);
				
				click(ShakeoutBox,"Shakeout Tab");
				Thread.sleep(1000);
				this.click(editpayloadXpath, "Edit Payload");
				upKey(7);
				
				Actions actions = new Actions(remoteDriver);
			    WebElement viewPayloadUrlChange = remoteDriver.findElement(By.xpath("//*[@id='main-content']//div/div/div[2]/div/div[2]/div/div/div/div/div/div/div/div/div/div[3]/div/div/div[1]/div/div/div[1]/div[1]/div/span[2]"));
			    actions.moveToElement(viewPayloadUrlChange).perform();
			    System.out.println("Done ");
				
				
				this.click(clicktoeditXpath, "Click to Edit");
				this.enterText(textboxXpath, payload, "Payload");
				this.click(editXpath, "Edit Completed");
				upKey(5);
				Thread.sleep(1000);
				this.click(saveXpath, "Save");
				
								
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			try {
				assertTrue("The NFT SSP Home page Launched Sucessfully - Verified the Welcome Text ", this.elementIsDisplayed(profileXpath));
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}	
}

	
	
	
	
	
		public void Trigger_Any_Tests() throws Exception {
			
			if (toolName.equalsIgnoreCase("Selenium")) {
				
				    WebElement P2OStream = remoteDriver.findElement(By.xpath("//*[contains(text(),'P2O')]"));
			        WebElement R2RStream = remoteDriver.findElement(By.xpath("//*[contains(text(),'R2R')]"));
			        WebElement S2PStream = remoteDriver.findElement(By.xpath("//*[contains(text(),'S2P')]"));
			        WebElement U2CStream = remoteDriver.findElement(By.xpath("//*[contains(text(),'U2C')]"));
				
			        if(!P2OStream.isEnabled())
			        {
			         System.out.print("P2O Stream Enabled");
			        }
			        else
			        {
			         System.out.print("P2O Stream is Disabled");
			        }
			        
			        
			        if(!R2RStream.isEnabled())
			        {
			         System.out.print("R2R Stream Enabled");
			        }
			        else
			        {
			         System.out.print("R2R Stream is Disabled");
			        }
			        
			        
			        
			        if(!S2PStream.isEnabled())
			        {
			         System.out.print("S2P Stream Enabled");
			        }
			        else
			        {
			         System.out.print("S2P Stream Tab is Disabled");
			        }
			        
			        
			        if(!U2CStream.isEnabled())
			        {
			         System.out.print("U2C Stream Enabled");
			        }
			        else
			        {
			         System.out.print("U2C Stream is Tab Disabled");
			        }
			        
			        
			}
		}
		
		/* Sc-& : As an existing SSP user, am I be able to see the result of a particular test by clicking on the outputs from the Test History Page.*/
		
		
		
		public void testOutputsFromHistory_Page() throws Exception {
			if (toolName.equalsIgnoreCase("Selenium")) {
				
				//Thread.sleep(5000);
				//click(NFTSelfServePortal, " NFP Portal ");
				//Thread.sleep(5000);
				click(TestHistory, " Test History ");
				Thread.sleep(2000);
				click(ClickFirstTest, " Test Result ");
				Thread.sleep(2000);
				downKey(15);
				
				/*WebElement TxtBoxContent = remoteDriver.findElement(By.xpath("//*[@id=\"main-content\"]/div/div/div[2]/div[1]/div/div/h1"));
				TxtBoxContent.getText();
				System.out.println("Test Status "+TxtBoxContent);
				
				WebElement TestDetails = remoteDriver.findElement(By.xpath("//*[@id='main-content']/div/div/div[2]/div[2]/div"));
				TestDetails.getText();
				System.out.println("Test Status "+TestDetails);
				
				WebElement AvgResponseTime = remoteDriver.findElement(By.xpath("//*[@id='main-content']/div/div/div[3]/div[1]/div"));
				AvgResponseTime.getText();
				System.out.println("Avg Response Time "+AvgResponseTime);

				/*WebElement AvgResponseTime = remoteDriver.findElement(By.xpath("//*[@id='main-content']/div/div/div[3]/div[1]/div"));
				AvgResponseTime.getText();
				System.out.println("Avg Response Time "+AvgResponseTime);*/
			}
		}
		
	
		
		
		public void see_Payload_historicalTest_of_ParticularTest() throws Exception {
			if (toolName.equalsIgnoreCase("Selenium")) {
				
				Thread.sleep(5000);
				click(NFTSelfServePortal, " NFP Portal ");
				Thread.sleep(5000);
				click(TestHistory, " Test History ");
				Thread.sleep(2000);
				click(ClickFirstTest, " Test Result ");
				Thread.sleep(2000);
				downKey(6);
				
				/*WebElement TxtBoxContent = remoteDriver.findElement(By.xpath("//*[@id=\"main-content\"]/div/div/div[2]/div[1]/div/div/h1"));
				TxtBoxContent.getText();
				System.out.println("Test Status "+TxtBoxContent);
				
				WebElement TestDetails = remoteDriver.findElement(By.xpath("//*[@id='main-content']/div/div/div[2]/div[2]/div"));
				TestDetails.getText();
				System.out.println("Test Status "+TestDetails);
				
				WebElement AvgResponseTime = remoteDriver.findElement(By.xpath("//*[@id='main-content']/div/div/div[3]/div[1]/div"));
				AvgResponseTime.getText();
				System.out.println("Avg Response Time "+AvgResponseTime);

				/*WebElement AvgResponseTime = remoteDriver.findElement(By.xpath("//*[@id='main-content']/div/div/div[3]/div[1]/div"));
				AvgResponseTime.getText();
				System.out.println("Avg Response Time "+AvgResponseTime);*/
				downKey(20);
				Thread.sleep(1000);
				click(showPayload, " show Payload ");
				Thread.sleep(10000);
				downKey(6);
				Thread.sleep(10000);
				
				assertTrue(" Verified the show Payload ", this.elementIsDisplayed(showPayload));
				remoteDriver.navigate().back();
			}
		}
		
	
		public void OutputsFromHistoryCard_Page() throws Exception {
			if (toolName.equalsIgnoreCase("Selenium")) {
				
				Thread.sleep(3000);
				click(NFTSelfServePortal, " NFP Portal ");
				click(TestHistory, " Test History ");
				
				
				enterText(TestStatus_Edit, "Passed", "Test Status - Passed");
				Thread.sleep(1000);
				TestStatus_Edit.clear();
				Thread.sleep(1000);
				enterText(TestStatus_Edit, "Aborted", "Test Status - Aborted");
				Thread.sleep(1000);
				TestStatus_Edit.clear();
			}
		}
		
		
	/*As an existing SSP User, am I be able to stop the test once I have launched a test without checking the payload information’s */
	
		public void O2AStreamUser_launchandStopTest_withoutCheckPayloadinfo() throws Exception {
			if (toolName.equalsIgnoreCase("Selenium")) {
				
				click(NFTSelfServePortal, " Portal icon ");
				click(O2AStreamDashboard,"O2A Stream User");
				Thread.sleep(1000);
				click(Demo,"Demo");
				Thread.sleep(1000);
				click(APIunderDemo,"API");
				Thread.sleep(5000);
				
				WebElement ScenarioDropDown = remoteDriver.findElement(By.className("css-1wa3eu0-placeholder"));
				ScenarioDropDown.click();
		        Thread.sleep(2000);      
		        remoteDriver.findElement(By.xpath("//*[text()='Team Demo | Wikipedia Pages']")).click();
		        Thread.sleep(2000);
				
				
				click(ShakeoutBox,"Shakeout Tab");
				Thread.sleep(1000);
				click(Launch,"Launch Tab");
				Thread.sleep(1000);
				click(LaunchConfirmation,"Launch confirmation Tab");
				Thread.sleep(10000);
				
				/*WebElement TxtBoxContent = remoteDriver.findElement(By.xpath("//*[@id='swal2-content']"));
				TxtBoxContent.getText();
				System.out.println("Printing "+TxtBoxContent);*/
				clickWithoutWait(OK,"Launch confirmation Tab");
				//upKey(8);
				Thread.sleep(7000);
				clickWithoutWait(StopTest,"Stop Test");
				Thread.sleep(7000);
				clickWithoutWait(ConfirmStopTest,"Yes, stop it!");
				Thread.sleep(10000);
				
			}
		}
		
		
		
		/* Use Case : 13
		 * Existing SSP stream User :  
		 * As an existing SSP stream User, can I see the general metrics of the streams, inside the Portal Insights Card.? */
		
		public void check_General_Mectrics() throws Exception {
			if (toolName.equalsIgnoreCase("Selenium")) {
				
				click(PortalInsights, " Portal Insights ");
				Thread.sleep(1000);
				downKey(5);
				Thread.sleep(1000);
				upKey(5);
			}
		}
		
		
		/* Use Case : 14
		 * Admin User :  
		 * As an Admin User of the SSP, am I be able to see all the metrics that is getting generated in the portal  */
		
		public void check_All_The_Mectrics() throws Exception {
			if (toolName.equalsIgnoreCase("Selenium")) {
				
				Thread.sleep(1000);
				click(PortalInsights, " Portal Insights ");
				Thread.sleep(3000);
				click(GeneralUserMetrics, " General User Mertics ");
				downKey(5);
				Thread.sleep(3000);
				upKey(5);
				click(TestMetrics, " Test Metrics ");
				downKey(5);
				Thread.sleep(3000);
				upKey(5);
				click(StreamMetrics, " Stream Metics ");
				Thread.sleep(2000);
				click(SpecificUserMetrics, " Stream User Metrics ");
				downKey(5);
				Thread.sleep(2000);
				upKey(5);
				click(AllUserMetrics, " All User Metrics ");
				upKey(2);
				Thread.sleep(2000);
			}
		}
		
		
		/* Use Case : 15 
		 * Admin User :  
		 * As an Admin User, Am I be able to execute any test from any streams in the portal? */
		
		public void P2O_StreamUser_TestRun() throws Exception {
			if (toolName.equalsIgnoreCase("Selenium")) {
				
				//click(NFTSelfServePortal, " Portal icon ");
				click(P2O,"P2O Stream User");
				Thread.sleep(1000);
				click(Demo,"Demo");
				Thread.sleep(1000);
				click(APIunderDemo,"API");
				Thread.sleep(1000);
				
				WebElement ScenarioDropDown = remoteDriver.findElement(By.className("css-1wa3eu0-placeholder"));
				ScenarioDropDown.click();
		        Thread.sleep(1000);      
		        remoteDriver.findElement(By.xpath("//*[text()='Team Demo | Wikipedia Pages']")).click();
		        Thread.sleep(1000);
				
				
				click(ShakeoutBox,"Shakeout Tab");
				Thread.sleep(1000);
				click(Launch,"Launch Tab");
				Thread.sleep(1000);
				click(LaunchConfirmation,"Launch confirmation Tab");
				
				/*WebElement TxtBoxContent = remoteDriver.findElement(By.xpath("//*[@id='swal2-content']"));
				TxtBoxContent.getText();
				System.out.println("Printing "+TxtBoxContent);*/
				clickWithoutWait(OK,"Launch confirmation Tab");
				Thread.sleep(1000);
				//upKey(8);
			}
		}

		
		/* Use Case : 15 
		 * Admin User :  
		 * As an Admin User, Am I be able to execute any test from any streams in the portal? */
		
		public void R2R_StreamUser_TestRun() throws Exception {
			if (toolName.equalsIgnoreCase("Selenium")) {
				
				click(NFTSelfServePortal, " Portal icon ");
				click(R2R,"R2R Stream User");
				Thread.sleep(1000);
				click(Demo,"Demo");
				Thread.sleep(1000);
				click(APIunderDemo,"API");
				Thread.sleep(1000);
				
				WebElement ScenarioDropDown = remoteDriver.findElement(By.className("css-1wa3eu0-placeholder"));
				ScenarioDropDown.click();
		        Thread.sleep(2000);      
		        remoteDriver.findElement(By.xpath("//*[text()='Team Demo | Wikipedia Pages']")).click();
		        Thread.sleep(2000);
				
				
				click(ShakeoutBox,"Shakeout Tab");
				Thread.sleep(1000);
				click(Launch,"Launch Tab");
				Thread.sleep(1000);
				click(LaunchConfirmation,"Launch confirmation Tab");
				Thread.sleep(1000);
				
				/*WebElement TxtBoxContent = remoteDriver.findElement(By.xpath("//*[@id='swal2-content']"));
				TxtBoxContent.getText();
				System.out.println("Printing "+TxtBoxContent);*/
				click(OK,"Launch confirmation Tab");
				Thread.sleep(1000);
				//upKey(8);
			}
		}


		/* Use Case : 15 
		 * Admin User :  
		 * As an Admin User, Am I be able to execute any test from any streams in the portal? */
		
		public void S2P_StreamUser_TestRun() throws Exception {
			if (toolName.equalsIgnoreCase("Selenium")) {
				
				click(NFTSelfServePortal, " Portal icon ");
				click(S2P,"S2P Stream User");
				Thread.sleep(1000);
				click(Demo,"Demo");
				Thread.sleep(1000);
				click(APIunderDemo,"API");
				Thread.sleep(5000);
				
				WebElement ScenarioDropDown = remoteDriver.findElement(By.className("css-1wa3eu0-placeholder"));
				ScenarioDropDown.click();
		        Thread.sleep(2000);      
		        remoteDriver.findElement(By.xpath("//*[text()='Team Demo | Wikipedia Pages']")).click();
		        Thread.sleep(2000);
				
				
				click(ShakeoutBox,"Shakeout Tab");
				Thread.sleep(1000);
				click(Launch,"Launch Tab");
				Thread.sleep(1000);
				click(LaunchConfirmation,"Launch confirmation Tab");
				Thread.sleep(1000);
				
				/*WebElement TxtBoxContent = remoteDriver.findElement(By.xpath("//*[@id='swal2-content']"));
				TxtBoxContent.getText();
				System.out.println("Printing "+TxtBoxContent);*/
				clickWithoutWait(OK,"Launch confirmation Tab");
				Thread.sleep(1000);
				//upKey(8);
			}
		}


		/* Use Case : 15 
		 * Admin User :  
		 * As an Admin User, Am I be able to execute any test from any streams in the portal? */

		public void U2C_StreamUser_TestRun() throws Exception {
			if (toolName.equalsIgnoreCase("Selenium")) {
				
				click(NFTSelfServePortal, " Portal icon ");
				click(U2C,"U2C Stream User");
				Thread.sleep(1000);
				click(Demo,"Demo");
				Thread.sleep(1000);
				click(APIunderDemo,"API");
				Thread.sleep(5000);
				
				WebElement ScenarioDropDown = remoteDriver.findElement(By.className("css-1wa3eu0-placeholder"));
				ScenarioDropDown.click();
		        Thread.sleep(2000);      
		        remoteDriver.findElement(By.xpath("//*[text()='Team Demo | Wikipedia Pages']")).click();
		        Thread.sleep(2000);
				
				
				click(ShakeoutBox,"Shakeout Tab");
				Thread.sleep(1000);
				click(Launch,"Launch Tab");
				Thread.sleep(1000);
				click(LaunchConfirmation,"Launch confirmation Tab");
				Thread.sleep(1000);
				
				/*WebElement TxtBoxContent = remoteDriver.findElement(By.xpath("//*[@id='swal2-content']"));
				TxtBoxContent.getText();
				System.out.println("Printing "+TxtBoxContent);*/
				clickWithoutWait(OK,"Launch confirmation Tab");
				Thread.sleep(1000);
				//upKey(8);
			}
		}



		
/* As an existing SSP User, am I be able to see any aborted Tests from the test History Page? */		
		
		public void see_any_Aborted_Tests_From_TestHistoryPage() throws Exception {
			if (toolName.equalsIgnoreCase("Selenium")) {
				
				Thread.sleep(5000);
				click(NFTSelfServePortal, " NFP Portal ");
				Thread.sleep(5000);
				click(TestHistory, " Test History ");
				Thread.sleep(6000);
				downKey(2);
				click(ClickFirstTest, " Test Result ");
				Thread.sleep(2000);
			}
		}
		
		

		
		public void Login_As_SSPUser_Check_GreyedOut_OtherStreams() throws Exception {
			if (toolName.equalsIgnoreCase("Selenium")) {
				
				 WebElement P2OStream = remoteDriver.findElement(By.xpath("//*[contains(text(),'P2O')]"));
			        WebElement R2RStream = remoteDriver.findElement(By.xpath("//*[contains(text(),'R2R')]"));
			        WebElement S2PStream = remoteDriver.findElement(By.xpath("//*[contains(text(),'S2P')]"));
			        WebElement U2CStream = remoteDriver.findElement(By.xpath("//*[contains(text(),'U2C')]"));
					
			        
			        if(!P2OStream.isEnabled())
			        {
			        	
			         System.out.print("P2O Stream Enabled");
			        }
			        else
			        {
			         System.out.print("P2O Stream Tab is Disabled");
			        }
			        
			        
			        
			        if(!R2RStream.isEnabled())
			        {
			         System.out.print("R2R Stream Enabled");
			        }
			        else
			        {
			        	System.out.print("R2R Stream Tab is Disabled");
			        }
			        
			        
			        
			        if(!S2PStream.isEnabled())
			        {
			         System.out.print("S2P Stream Enabled");
			        }
			        else
			        {
			        	System.out.print("S2P Stream Tab is Disabled");
			        }
			        
			        
			        if(!U2CStream.isEnabled())
			        {
			         System.out.print("U2C Stream Enabled");
			        }
			        else
			        {
			        	System.out.print("S2P Stream Tab is Disabled");
			        }
			       
			}
		}
		
		
		
}
