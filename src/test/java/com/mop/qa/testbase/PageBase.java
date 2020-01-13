package com.mop.qa.testbase;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.awt.image.RenderedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.opencv.core.Core;
import org.opencv.core.Core.MinMaxLocResult;
import org.opencv.core.CvType;
import org.opencv.core.DMatch;
import org.opencv.core.Mat;
import org.opencv.core.MatOfDMatch;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.core.Rect;
import org.opencv.core.Size;
import org.opencv.features2d.DescriptorExtractor;
import org.opencv.features2d.DescriptorMatcher;
import org.opencv.features2d.FeatureDetector;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.mop.qa.Utilities.ExtentUtility;
import com.mop.qa.Utilities.ReadDataSheet;
import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
//import io.appium.java_client.NetworkConnectionSetting;
//import io.appium.java_client.ScrollsTo;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileBrowserType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
//import io.appium.java_client.touch.offset.PointOption;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;


@SuppressWarnings("deprecation")
public class PageBase 
{

	public RemoteWebDriver remoteDriver;
	@SuppressWarnings("rawtypes")
	public AppiumDriver appiumDriver;
	protected String toolName;
	public String device_OS;
	protected static String appType;
	protected static String platform_Name;
	TestBase t;
	String filesperator = System.getProperty("file.separator");
	String installApp=null;
	String deviceSerialNumber=null;
	String installPath=null;
	

	
/******   Constructor ****************/
	@SuppressWarnings("rawtypes")

	/* Ela
	public PageBase(AppiumDriver driver) 
	{
		this.appiumDriver = driver;
		PageFactory.initElements(appiumDriver, this);
		toolName = "Appium";
		TestBase testBaseObj = new TestBase();
		appType = testBaseObj.appType;
	} */

	public PageBase(RemoteWebDriver driver) 
	{
		this.remoteDriver = driver;
		PageFactory.initElements(remoteDriver, this);
		toolName = "Selenium";

	}
	public PageBase() {
		try {
			TestBase testBaseObj = new TestBase();
			if (testBaseObj.toolName.equalsIgnoreCase("selenium")) 
			{
				this.remoteDriver = remoteDriver;
				PageFactory.initElements(remoteDriver, this);
				toolName = "Selenium";

			}
			
			
			/*Ela
			 * else if (testBaseObj.toolName.equalsIgnoreCase("appium"))
			{
				this.appiumDriver = appiumDriver;
				PageFactory.initElements(appiumDriver, this);
				toolName = "Appium";
			}*/
		} catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static enum Page 
	{
		FirstThrid, SecondThrid, Whole, swipe
	}

	
	/******   Constants ****************/
	public int count3 = 1, imagewidth1, imageheight1, imagewidth2, imageheight2;
	public static int n = 0;
	public String text1 = null;
	public String tool = null, webBrowser = null, chromeDriverPath = null, fireFoxDriverPath = null,
			IEDriverPath = null, deviceName = null, appName = null, appiumPort = null, deviceVersion = null,
			appPackage = null, appActivity = null, Android_Appium_Server_Path = null, appiumPort_Ios = null,
			devicePlatformName_Ios = null, deviceVersion_Ios = null, device_UDID = null, platformName = null,
			applicationPath = null, appiumURL = null, ParentWinhadleMob = null, ParentWinhadle = null,
			mobileCloud = null,
			accesskey = null;

	
 /******   Methods  ****************/

	public AppiumDriver launchApp(String udid, String locality, String url, String toolname, String appType,
			String port, String platformName, String startURL) throws Exception 
	{
		platform_Name = platformName;
		System.out.println("platformName " + platformName);
		System.out.println("appType " + appType);
		tool = toolName;
		installApp = getAppProperties("installApplication");		
		appName = getAppProperties("appName");
		deviceVersion = getAppProperties("deviceVersion");
		appPackage = getAppProperties("appPackage");
		appActivity = getAppProperties("appActivity");
		String bundleID=getAppProperties("bundleID");
		ReadDataSheet rds = new ReadDataSheet();
		if (locality.equalsIgnoreCase("Hub")) 
		{
			if (tool.equalsIgnoreCase("Appium")) 
			{
				if ((platformName.equalsIgnoreCase("iOS")) && (appType.equalsIgnoreCase("Native"))) {
					System.out.println("iOS Native");
					DesiredCapabilities capabilities = new DesiredCapabilities();	
					String bootstrapPath=getAppProperties("bootstrapPath");
					String agentPath=getAppProperties("agentPath");
					//String bundleID=getAppProperties("bundleID");					
					appiumPort_Ios = getAppProperties("appiumPort_Ios");
					deviceVersion_Ios = getAppProperties("deviceVersion_Ios");
					if (udid == null ) { udid =getAppProperties("device_UDID");}
					applicationPath = getAppProperties("applicationPath");
					
					System.out.println("port: " + port);
					if (System.getProperty("os.name").contains("Win")) 
					{
						appiumURL = "http://127.0.0.1:" + port + "/wd/hub";
					} else {
						appiumURL = "http://0.0.0.0:" + port + "/wd/hub";
					} 
					System.out.println("URL  : "+appiumURL);
					capabilities.setCapability("deviceName", "iPhone 7");
					// capabilities.setCapability("appium-version", "1.0");
					capabilities.setCapability("platformName", platformName);
					capabilities.setCapability("platformVersion", deviceVersion_Ios);
					capabilities.setCapability("instrumentApp", "true");
					capabilities.setCapability("udid", udid);
					capabilities.setCapability("automationName", "XCUITest");
					capabilities.setCapability("autoAcceptAlerts", true);
					capabilities.setCapability("autoDismissAlerts", true);
					
					if (installApp =="Y") {capabilities.setCapability("app", applicationPath);}
					capabilities.setCapability("bundleId", bundleID);
					capabilities.setCapability("bootstrapPath",bootstrapPath);
					capabilities.setCapability("agentPath",agentPath);
				   // From APPIUM STUDIO
				    //"C:\\Users\\581395\\AppData\\Local\\Programs\\Appium\\resources\\app\\node_modules\\appium\\node_modules\\appium-xcuitest-driver\\WebDriverAgent\\WebDriverAgent.xcodeproj"); // bootstrapPath
					//"C:\\Users\\581395\\AppData\\Local\\Programs\\Appium\\resources\\app\\node_modules\\appium\\node_modules\\appium-xcuitest-driver\\WebDriverAgent");
					capabilities.setCapability("noReset", true);
					capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "60");
					capabilities.setCapability("clearSystemFiles", true);
					capabilities.setCapability("newCommandTimeout", 200);
					if (appiumDriver == null)
						appiumDriver = null;
						 appiumDriver = (AppiumDriver) new IOSDriver(new URL(appiumURL), capabilities);
						 appiumDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
						appiumDriver.context("NATIVE_APP");
					

				} else if ((platformName.equalsIgnoreCase("Android")) && (appType.equalsIgnoreCase("Native"))) {

					System.out.println("Android Native");
					DesiredCapabilities capabilities = new DesiredCapabilities();	
					if (System.getProperty("os.name").contains("Win")) 
					{
						appiumURL = "http://127.0.0.1:" + port + "/wd/hub";
					} else {
						appiumURL = "http://0.0.0.0:" + port + "/wd/hub";
					}
					System.out.println(port);					
					  capabilities.setCapability("appium-version", "1.0"); //
					  if (installApp =="Y")
					  {capabilities.setCapability("app", appName);}
					  capabilities.setCapability("platformName", platformName);
					  if (udid == null ) { udid =getAppProperties("device_UDID");}
					  capabilities.setCapability("deviceName", udid);
					  capabilities.setCapability("udid", udid);
					  capabilities.setCapability("appPackage", appPackage);
					  capabilities.setCapability("appActivity", appActivity);
					  capabilities.setCapability("--session-override", true);
					  capabilities.setCapability("noReset", true);
					  capabilities.setCapability("clearSystemFiles", true);
					  capabilities.setCapability("newCommandTimeout", 200); 
					  capabilities.setCapability("autoAcceptAlerts", true);
				      capabilities.setCapability("autoDismissAlerts", true);
					  System.out.println(appiumURL);
					  appiumDriver = new AndroidDriver(new URL(appiumURL), capabilities);
					  appiumDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);			  

				}

				else if ((platformName.equalsIgnoreCase("Android")) && (appType.equalsIgnoreCase("Web"))) {
					System.out.println("Android Browser");

					deviceVersion = getAppProperties("deviceVersion");
					deviceName = getAppProperties("deviceName");
					appiumPort = getAppProperties("appiumPort");
					if (System.getProperty("os.name").contains("Win")) {
						appiumURL = "http://127.0.0.1:" + port + "/wd/hub";
					} else {
						appiumURL = "http://127.0.0.1:" + port + "/wd/hub";
					}
					String host = "mobiletestlab.cognizant.com";
					DesiredCapabilities capabilities = new DesiredCapabilities();
					capabilities.setCapability(CapabilityType.BROWSER_NAME, "Chrome");
					capabilities.setCapability("newCommandTimeout", 300);
					capabilities.setCapability("appium-version", "1.0");
					capabilities.setCapability("platformName", platformName);
					capabilities.setCapability("deviceName", udid);
					capabilities.setCapability("udid", udid);

					appiumDriver = new AndroidDriver(new URL(appiumURL), capabilities);
					appiumDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				}

				else if ((platformName.equalsIgnoreCase("iOS")) && (appType.equalsIgnoreCase("Web"))) {
					System.out.println("iOS Browser");
					deviceVersion = getAppProperties("deviceVersion_Ios");
					deviceName = getAppProperties("device_UDID");
					appiumPort = getAppProperties("appiumPort");
					if (System.getProperty("os.name").contains("Win")) {
						appiumURL = "http://127.0.0.1:" + port + "/wd/hub";
					} else {
						appiumURL = "http://127.0.0.1:" + port + "/wd/hub";
					}

					DesiredCapabilities cap = new DesiredCapabilities();
					cap.setCapability("deviceName", "IME's iPhone");
					cap.setCapability("automationName", "XCUITest");
					cap.setCapability("browserName", "Safari");
					cap.setCapability("platformVersion", deviceVersion);
					cap.setCapability("platformName", platformName);
					cap.setCapability("udid", deviceName);
					cap.setCapability("newCommandTimeout", 20000);
					cap.setCapability("safariInitialUrl", startURL.trim());
					// cap.setCapability("safariInitialUrl","https://www.nbcnews.com");
					/*
					 * if(startURL.contains("https://www.nbcnews.com")){
					 * cap.setCapability("safariInitialUrl","https://www.nbcnews.com"); }else{
					 * cap.setCapability("safariInitialUrl", "https://www.universalorlando.com"); }
					 */
					// if (appiumDriver == null) {
					appiumDriver = new IOSDriver(new URL(appiumURL), cap);
					Thread.sleep(10000);
					// }
					/*
					 * Set<String> contextNames = ((AppiumDriver) appiumDriver)
					 * .getContextHandles();
					 * 
					 * for (String contextName : contextNames) {
					 * 
					 * System.out.println("******contextName " + contextName);
					 * 
					 * if (contextName.contains("WEBVIEW")) {
					 * 
					 * ((AppiumDriver) appiumDriver).context(contextName); break;
					 * 
					 * } }
					 */
					appiumDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

				}
			}
		} else if (locality.equalsIgnoreCase("Grid")) {
			if (!url.equalsIgnoreCase("NA")) {
				if (tool.equalsIgnoreCase("Appium")) {
					if ((platformName.equalsIgnoreCase("iOS")) && (appType.equalsIgnoreCase("Native"))) 
					{

						appiumPort_Ios = getAppProperties("appiumPort_Ios");
						deviceVersion_Ios = getAppProperties("deviceVersion_Ios");
						device_UDID = getAppProperties("udid");
						if (System.getProperty("os.name").contains("Win")) {
							appiumURL = "http://" + url + port + "/wd/hub";
						} else {
							appiumURL = "http://0.0.0.0:" + port + "/wd/hub";
						}
						DesiredCapabilities capabilities = new DesiredCapabilities();

						capabilities.setCapability("appium-version", "1.0");
						capabilities.setCapability("platformName", platformName);
						capabilities.setCapability("platformVersion", deviceVersion_Ios);
						capabilities.setCapability("deviceName", udid);
						capabilities.setCapability("app", applicationPath);
						if (appiumDriver == null)
							appiumDriver = null;

						remoteDriver = (AppiumDriver) new IOSDriver(new URL(appiumURL), capabilities);

					} else if ((platformName.equalsIgnoreCase("Android")) && (appType.equalsIgnoreCase("Native"))) {

						System.out.println("Android Native");

						appName = getAppProperties("appName");
						deviceVersion = getAppProperties("deviceVersion");
						appPackage = getAppProperties("appPackage");
						appActivity = getAppProperties("appActivity");
						if (System.getProperty("os.name").contains("Win")) {
							appiumURL = "http://" + url + port + "/wd/hub";
						} else {
							appiumURL = "http://0.0.0.0:" + port + "/wd/hub";
						}

						DesiredCapabilities capabilities = new DesiredCapabilities();
						capabilities.setCapability("appium-version", "1.0");
						capabilities.setCapability("app", appName);
						capabilities.setCapability("platformName", platformName);
						capabilities.setCapability("deviceName", udid);
						capabilities.setCapability("udid", udid);
						capabilities.setCapability("appPackage", appPackage);
						capabilities.setCapability("appActivity", appActivity);
						appiumDriver = new AndroidDriver(new URL(url), capabilities);
						// }
						appiumDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					}

					else if ((platformName.equalsIgnoreCase("Android")) && (appType.equalsIgnoreCase("Web"))) {

						deviceVersion = getAppProperties("deviceVersion");
						deviceName = getAppProperties("deviceName");
						appiumPort = getAppProperties("appiumPort");
						if (System.getProperty("os.name").contains("Win")) {
							appiumURL = "http://" + url + port + "/wd/hub";
						} else {
							appiumURL = "http://0.0.0.0:" + port + "/wd/hub";

						}
						String host = "mobiletestlab.cognizant.com";
						DesiredCapabilities capabilities = new DesiredCapabilities();
						capabilities.setCapability(CapabilityType.BROWSER_NAME, "Chrome");
						capabilities.setCapability("newCommandTimeout", "300");
						capabilities.setCapability("appium-version", "1.0");
						capabilities.setCapability("platformName", platformName);
						capabilities.setCapability("deviceName", udid);
						capabilities.setCapability("udid", udid);

						appiumDriver = new AndroidDriver(new URL(url), capabilities);
						appiumDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
					}

					else if ((platformName.equalsIgnoreCase("iOS")) && (appType.equalsIgnoreCase("Web"))) {
						System.out.println("iOS Browser");

						deviceVersion = getAppProperties("deviceVersion_Ios");
						deviceName = getAppProperties("device_UDID");
						appiumPort = getAppProperties("appiumPort");
						if (System.getProperty("os.name").contains("Win")) {
							appiumURL = "http://" + url + port + "/wd/hub";
						} else {
							appiumURL = "http://0.0.0.0:" + port + "/wd/hub";
						}

						DesiredCapabilities cap = new DesiredCapabilities();
						cap.setCapability("deviceName", "iPhone");
						cap.setCapability("browserName", "Safari");
						cap.setCapability("platformVersion", deviceVersion);
						cap.setCapability("platformName", platformName);
						cap.setCapability("udid", deviceName);

						if (appiumDriver == null) {
							appiumDriver = new IOSDriver(new URL(appiumURL), cap);

						}
						appiumDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
					}
				}
			}
		}

		else if (locality.equalsIgnoreCase("Cloud")) {
			Thread.sleep(20000);
			System.out.println("Mobile Cloud Execution Started");
			System.out.println("Mobile Cloud URL --"+url);
			System.out.println("Device SN / UDID --"+udid);
			String accessKey = getAppProperties("password");
			if (platformName.equalsIgnoreCase("Android")) {
				System.out.println("Android Cloud Device");
				DesiredCapabilities capabilities = new DesiredCapabilities();
								
				if (udid=="ANY")
				{ capabilities.setCapability("deviceQuery", "@os='android' and @category='PHONE'");}
				else
				{capabilities.setCapability("deviceQuery", "@serialNumber='"+udid+"'");}
				//This is to Install the application
				
				if (installApp =="Y") {
				capabilities.setCapability(MobileCapabilityType.APP, appName);}
				capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,appPackage);
				capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, appActivity);
				//capabilities.setCapability("appVersion", "05.00.40734");
				capabilities.setCapability("instrumentApp", false);
				capabilities.setCapability("accessKey", accessKey);
				//capabilities.setCapability("testRun", "1");
				capabilities.setCapability("testName",getAppProperties("projectName") );
				appiumDriver = new AndroidDriver<>(new URL(url), capabilities);
				appiumDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				Thread.sleep(3000);
		
			} else if (platformName.equalsIgnoreCase("iOS")) {
				DesiredCapabilities capabilities = new DesiredCapabilities();
				if (deviceSerialNumber=="ANY") {
				capabilities.setCapability("deviceQuery", "@os='ios' and @category='PHONE'");}
				else
					capabilities.setCapability("deviceQuery", "@serialNumber='"+udid+"'");
				if (installApp =="Y") {
				capabilities.setCapability(MobileCapabilityType.APP, appName);}
				capabilities.setCapability(IOSMobileCapabilityType.BUNDLE_ID, bundleID);
				//capabilities.setBrowserName(MobileBrowserType.SAFARI);
				capabilities.setCapability("noReset", true);
				capabilities.setCapability("accessKey", accessKey);
				capabilities.setCapability("instrumentApp", "true");
				appiumDriver = new IOSDriver<>(new URL(url), capabilities);
				Thread.sleep(10000);
	
			}else if ((platformName.equalsIgnoreCase("iOS")) && (appType.equalsIgnoreCase("Web"))) {
				System.out.println("iOS Browser");
				deviceVersion = getAppProperties("deviceVersion_Ios");
				deviceName = getAppProperties("device_UDID");
				appiumPort = getAppProperties("appiumPort");
				if (System.getProperty("os.name").contains("Win")) {
					appiumURL = "http://" + url + port + "/wd/hub";
				} else {
					appiumURL = "http://0.0.0.0:" + port + "/wd/hub";
				}

				DesiredCapabilities cap = new DesiredCapabilities();
				cap.setCapability("deviceName", "iPhone");
				cap.setCapability("browserName", "Safari");
				cap.setCapability("platformVersion", deviceVersion);
				cap.setCapability("platformName", platformName);
				cap.setCapability("udid", deviceName);

				if (appiumDriver == null) {
					appiumDriver = new IOSDriver(new URL(appiumURL), cap);
				}
				appiumDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			}
		}
		return appiumDriver;
	}

	public RemoteWebDriver launchSite(String browser, String locality, String url) throws Exception {
		if (locality.equalsIgnoreCase("Hub")) {
			if (browser.equalsIgnoreCase("chrome")) {
				chromeDriverPath = getAppProperties("chromeDriverPath");
				System.setProperty("webdriver.chrome.driver", chromeDriverPath);
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-extensions");
				options.addArguments("--test-type");
				options.addArguments("start-maximized");
				remoteDriver = new ChromeDriver(options);
				remoteDriver.manage().window().maximize();
				// Dimension targetSize = new Dimension(1450, 1080); // your screen
				// resolution
				// here
				// remoteDriver.manage().window().setSize(targetSize);
				System.out.println("chrome started");
			} else if (browser.equalsIgnoreCase("firefox")) {

//				fireFoxDriverPath = getAppProperties("fireFoxDriverPath");
//				FirefoxBinary binary = new FirefoxBinary(new File(fireFoxDriverPath));
//				remoteDriver = new FirefoxDriver(binary, null);
				System.out.println("came here");
				remoteDriver = new FirefoxDriver();
				remoteDriver.manage().window().maximize();

			} else if (browser.equalsIgnoreCase("IE")) {
				DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
				caps.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
				caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				caps.setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP, true);
				IEDriverPath = getAppProperties("ieDriverPath");
				System.setProperty("webdriver.ie.driver", IEDriverPath);
				System.out.println("ie driver");
				remoteDriver = new InternetExplorerDriver();
				remoteDriver.manage().window().maximize();
			}
		} else if (locality.equalsIgnoreCase("Grid")) {
			String port = getPort();
			if (!url.equalsIgnoreCase("NA")) {
				System.out.println("remote started");
				if (browser.equalsIgnoreCase("chrome")) {

					DesiredCapabilities caps = DesiredCapabilities.chrome();
					caps.setCapability(CapabilityType.BROWSER_NAME, "chrome");
					caps.setCapability(CapabilityType.VERSION, 60);
					caps.setCapability(CapabilityType.PLATFORM, "Vista");
					caps.setCapability("username", "imetestcloud");
					caps.setCapability("accesskey", "f56f6516-3056-4aac-8ff5-03e40719fe59");

					remoteDriver = new RemoteWebDriver(new URL(url), caps);
					remoteDriver.manage().window().maximize();

				} else if (browser.equalsIgnoreCase("firefox")) {
					String NodeURL;
					NodeURL = url;
					DesiredCapabilities capa = DesiredCapabilities.firefox();
					capa.setBrowserName("firefox");
					capa.setPlatform(Platform.ANY);
					remoteDriver = new RemoteWebDriver(new URL(NodeURL), capa);
					remoteDriver.manage().window().maximize();
				} else if (browser.equalsIgnoreCase("IE")) {
					String NodeURL;
					NodeURL = url;
					DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
					caps.setBrowserName("internet explorer");
					caps.setPlatform(Platform.ANY);
					remoteDriver = new RemoteWebDriver(new URL(NodeURL), caps);
					remoteDriver.manage().window().maximize();

				}
			}

			else {
				String mobileCloud = getAppProperties("mobileCloud");
				// Desktop Cloud in QPass Cloud
				if (mobileCloud.equalsIgnoreCase("No")) {
					DesiredCapabilities capabilities = DesiredCapabilities.firefox();
					capabilities.setPlatform(Platform.MAC);
					capabilities.setVersion("41");
					capabilities.setCapability("username", "Vignesh.Parameswari@cognizant.com");
					capabilities.setCapability("password", "VmlnbmVzaC5QYXJhbWVzd2FyaUBjb2duaXphbnQuY29tOg==");
					capabilities.setCapability("packagename", "HMHDemo");
					capabilities.setCapability("servicerequestid", "SR-01302-160624-0000664");
					remoteDriver = new RemoteWebDriver(new URL("http://fastpaasinchnp1.cognizant.com/wd/hub"),
							capabilities);
				}
				// Mobile Cloud in Perfecto Cloud
				else {
					String mobileHost = getAppProperties("mobileCloudHost");
					DesiredCapabilities capabilities = new DesiredCapabilities("mobileChrome", "", Platform.ANY);
					capabilities.setCapability("user", "Ramya.Santhanam@cognizant.com");
					capabilities.setCapability("password", "Password-1");
					capabilities.setCapability("deviceName", "0728FA70");
//					setExecutionIdCapability(capabilities, "https://mobiletestlab.cognizant.com");
					remoteDriver = new RemoteWebDriver(
							new URL("https://" + mobileHost + "/nexperience/perfectomobile/wd/hub"), capabilities);
					remoteDriver.get("https://my-review-cert.hrw.com/dashboard/home");
				}
			}

		} else if (locality.equalsIgnoreCase("Cloud")) {
			System.out.println("Cloud Desktop Execution started");

			DesiredCapabilities caps = DesiredCapabilities.chrome();
			caps.setCapability(CapabilityType.BROWSER_NAME, "chrome");
			caps.setCapability(CapabilityType.VERSION, 60);
			caps.setCapability(CapabilityType.PLATFORM, "Vista");
			caps.setCapability("username", "imetestcloud");
			caps.setCapability("accesskey", "f56f6516-3056-4aac-8ff5-03e40719fe59");
			remoteDriver = new RemoteWebDriver(new URL(url), caps);
			remoteDriver.manage().window().maximize();
		}
		return remoteDriver;
	}

	public String getPort() throws Exception {
		ServerSocket socket = new ServerSocket(0);
		socket.setReuseAddress(true);
		String port = Integer.toString(socket.getLocalPort());
		socket.close();
		return port;
	}

//	private static void setExecutionIdCapability(DesiredCapabilities capabilities, String host) throws IOException {
//		try {
//			EclipseConnector connector = new EclipseConnector();
//			String eclipseHost = connector.getHost();
//			if ((eclipseHost == null) || (eclipseHost.equalsIgnoreCase(host))) {
//				String executionId = connector.getExecutionId();
//				capabilities.setCapability(EclipseConnector.ECLIPSE_EXECUTION_ID, executionId);
//			}
//		} catch (Exception E) {
//			E.printStackTrace();
//		}
//
//	}

	public void checkVideoPlaying(String videoPlayerPath) throws Exception {
		Thread.sleep(1000);
		takeScreenshotVideo("Image1", videoPlayerPath);
		Thread.sleep(3000);
		takeScreenshotVideo("Image2", videoPlayerPath);

		String file1 = "VideoComparison\\Image1.png";
		String file2 = "VideoComparison\\Image2.png";

		processImage(file1, file2);

	}

	public void takeScreenshotVideo(String screenshotName, String videoPlayerPath) {

		try {
			WebElement ele = remoteDriver.findElement(By.xpath(videoPlayerPath));
			if (ele.isDisplayed()) {
				File screen = (File) ((TakesScreenshot) remoteDriver).getScreenshotAs(OutputType.FILE);

				int ImageWidth = ele.getSize().getWidth();
				int ImageHeight = ele.getSize().getHeight();
				Point point = ele.getLocation();
				int xcord = point.getX();
				int ycord = point.getY();
				BufferedImage img = ImageIO.read(screen);
				BufferedImage dest = img.getSubimage(xcord, ycord, ImageWidth, ImageHeight);
				ImageIO.write(dest, "png", screen);
				FileUtils.copyFile(screen, new File("VideoComparison\\" + screenshotName + ".png"));
			}
		} catch (Exception e) {

			try {
				System.out.println("e" + e);
				// rg.logException("Taking Screenshots Fails", e);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			e.printStackTrace();
		}
	}

	public ArrayList<Integer> getResolution(WebElement videoPlayerPathWE) {

		ArrayList<Integer> size = new ArrayList<Integer>();
		try {
			if (videoPlayerPathWE.isDisplayed()) {
				int ImageWidth = videoPlayerPathWE.getSize().getWidth();
				int ImageHeight = videoPlayerPathWE.getSize().getHeight();
				size.add(ImageWidth);
				size.add(ImageHeight);

			}
		} catch (Exception e) {

			try {
				// rg.logException("Taking Screenshots Fails", e);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			e.printStackTrace();
		}

		return size;
	}

	public void waitForVisibilityOfElement(String xpath) throws Exception {

		try {

			switch (toolName) {

			case "Appium":

				WebDriverWait wait = new WebDriverWait(appiumDriver, 60, 500);

				wait.until(ExpectedConditions.elementToBeClickable(appiumDriver.findElement(By.xpath(xpath))));

				break;

			case "Selenium":

				WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 120, 500);

				waitSelenium.until(ExpectedConditions.presenceOfElementLocated((By.xpath(xpath))));

				break;

			}

		} catch (Exception exc) {

			exc.printStackTrace();

		}

	}

	public void takeScreenshot(String screenshotName, String videoPlayerPath) {

		try {
			WebElement ele = remoteDriver.findElement(By.xpath(videoPlayerPath));
			if (ele.isDisplayed()) {
				File screen = (File) ((TakesScreenshot) remoteDriver).getScreenshotAs(OutputType.FILE);

				int ImageWidth = ele.getSize().getWidth();
				int ImageHeight = ele.getSize().getHeight();
				Point point = ele.getLocation();
				int xcord = point.getX();
				int ycord = point.getY();
				BufferedImage img = ImageIO.read(screen);
				BufferedImage dest = img.getSubimage(xcord, ycord, ImageWidth, ImageHeight);
				ImageIO.write(dest, "png", screen);
				FileUtils.copyFile(screen, new File("PhotoPassScreenshots/" + screenshotName + ".png"));
			}
		} catch (Exception e) {

			try {
				ExtentUtility.getTest().log(LogStatus.FAIL, e + " Taking Screenshots Fails ",
						ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
				throw new Exception(e);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			e.printStackTrace();
		}
	}

	public void takeFullScreenshot(String screenshotName) {

		try {
			File screen = (File) ((TakesScreenshot) remoteDriver).getScreenshotAs(OutputType.FILE);

			FileUtils.copyFile(screen, new File("PhotoPassScreenshots/" + screenshotName + ".png"));

		} catch (Exception e) {

			try {
				ExtentUtility.getTest().log(LogStatus.FAIL, e + " Taking Screenshots Fails ",
						ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
				throw new Exception(e);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			e.printStackTrace();
		}
	}

	public String takeScreenShot() throws IOException {
		Calendar cal = Calendar.getInstance();
		long s = cal.getTimeInMillis();
		File screen = null;
		try {
			switch (toolName) {
			case "Appium":
				screen = (File) ((TakesScreenshot) appiumDriver).getScreenshotAs(OutputType.FILE);
				break;
			case "Selenium":
				screen = (File) ((TakesScreenshot) remoteDriver).getScreenshotAs(OutputType.FILE);
				break;
			}
			FileUtils.copyFile(screen,
					new File("ReportGenerator/" + ExtentUtility.reportFolder + "/Screenshots/image" + s + ".png"));
		} catch (Exception e) {
			System.out.println(e);
		}

	//	return (new File("ReportGenerator//" + ExtentUtility.reportFolder + "//Screenshots//image" + s + ".png")
	//			.getAbsolutePath());
		
		return  "./Screenshots//image" + s + ".png";
	
	}

	public void saveImage(String screenshotName, String videoPlayerPath) throws Exception {
		WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 120, 250);
		waitSelenium.until(ExpectedConditions.visibilityOf(remoteDriver.findElement(By.xpath(videoPlayerPath))));
		String s1 = remoteDriver.findElement(By.xpath(videoPlayerPath)).getAttribute("src");
		URL url1 = new URL(s1);
		RenderedImage image1 = ImageIO.read(url1);
		ImageIO.write(image1, "png", new File("PhotoPassScreenshots/" + screenshotName + ".png"));
	}

	public void processImage(String file1, String file2) throws Exception {

		try {
			Image image1 = Toolkit.getDefaultToolkit().getImage(file1);
			Image image2 = Toolkit.getDefaultToolkit().getImage(file2);

			PixelGrabber grab1 = new PixelGrabber(image1, 0, 0, -1, -1, false);
			PixelGrabber grab2 = new PixelGrabber(image2, 0, 0, -1, -1, false);

			int[] data1 = null;

			if (grab1.grabPixels()) {
				int width = grab1.getWidth();
				int height = grab1.getHeight();
				data1 = new int[width * height];
				data1 = (int[]) grab1.getPixels();
			}

			int[] data2 = null;

			if (grab2.grabPixels()) {
				int width = grab2.getWidth();
				int height = grab2.getHeight();
				data2 = new int[width * height];
				data2 = (int[]) grab2.getPixels();
			}

			boolean result = java.util.Arrays.equals(data1, data2);

			if (result == false) {
				System.out.println("Result = Video is playing - PASS ");

			} else {
				System.out.println("Result = Video is not Playing - FALSE");
				ExtentUtility.getTest().log(LogStatus.FAIL, " Result = Video is not Playing - FALSE ",
						ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
				throw new Exception(" Result = Video is not Playing - FALSE ");

			}

		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}

	public void validateImage(String imageName1, String imageName2) throws Exception {

		try {
			String file1 = "PhotoPassScreenshots/" + imageName1 + "" + ".png";
			String file2 = "PhotoPassScreenshots/" + imageName2 + "" + ".png";
			Image image1 = Toolkit.getDefaultToolkit().getImage(file1);
			Image image2 = Toolkit.getDefaultToolkit().getImage(file2);

			PixelGrabber grab1 = new PixelGrabber(image1, 0, 0, -1, -1, false);
			PixelGrabber grab2 = new PixelGrabber(image2, 0, 0, -1, -1, false);

			int[] data1 = null;

			if (grab1.grabPixels()) {
				int width = grab1.getWidth();
				int height = grab1.getHeight();
				data1 = new int[width * height];
				data1 = (int[]) grab1.getPixels();
			}

			int[] data2 = null;

			if (grab2.grabPixels()) {
				int width = grab2.getWidth();
				int height = grab2.getHeight();
				data2 = new int[width * height];
				data2 = (int[]) grab2.getPixels();
			}

			boolean result = java.util.Arrays.equals(data1, data2);

			if (result) {
				System.out.println("Result = Image validation - Pass ");
			} else {
				System.out.println("Result = Image validation - Fail ");

			}

		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}

	public void validateSlideImage(String imageName1, String imageName2) throws Exception {

		try {
			String file1 = "PhotoPassScreenshots/" + imageName1 + "" + ".png";
			String file2 = "PhotoPassScreenshots/" + imageName2 + "" + ".png";
			Image image1 = Toolkit.getDefaultToolkit().getImage(file1);
			Image image2 = Toolkit.getDefaultToolkit().getImage(file2);

			PixelGrabber grab1 = new PixelGrabber(image1, 0, 0, -1, -1, false);
			PixelGrabber grab2 = new PixelGrabber(image2, 0, 0, -1, -1, false);

			int[] data1 = null;

			if (grab1.grabPixels()) {
				int width = grab1.getWidth();
				int height = grab1.getHeight();
				data1 = new int[width * height];
				data1 = (int[]) grab1.getPixels();
			}

			int[] data2 = null;

			if (grab2.grabPixels()) {
				int width = grab2.getWidth();
				int height = grab2.getHeight();
				data2 = new int[width * height];
				data2 = (int[]) grab2.getPixels();
			}

			boolean result = java.util.Arrays.equals(data1, data2);

			if (result) {
				System.out.println("Result = Slide Show Image validation - Fail ");

			} else {

				System.out.println("Result = Slide Show Image validation - Pass ");
			}

		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}

	public String getAppProperties(String key) throws IOException {
		String value = "";
		try {

			FileInputStream fileInputStream = new FileInputStream("data.properties");
			Properties property = new Properties();
			property.load(fileInputStream);

			value = property.getProperty(key);

			fileInputStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;

	}

	public void hideKeyboard() throws Exception {
		appiumDriver.hideKeyboard();
		System.out.println("back over");
	}
	
	
	
	public void enterUrl(String url) throws Exception {
	try {
			remoteDriver.get(url);
			assertTrue("Application URL " + url + " has been launched", true);
			
	} 
	catch (Exception exc) {
		exc.printStackTrace();

	}

	}
	
	
	
	
	
	
	

	/*public void enterUrl(String url) throws Exception {
		try {
			switch (toolName) {

			case "Selenium":
				remoteDriver.get(url);
				assertTrue("Application URL " + url + " has been launched", true);
				break;
			}

		} catch (Exception exc) {
			exc.printStackTrace();

		}
	}*/
	

	public void clickPoint(WebElement e, String elementName) throws Exception {

		int xx = e.getLocation().x;
		int yy = e.getLocation().y;

		System.out.println("X Position : " + xx);
		System.out.println("Y Position : " + yy);
		clickCoordinates(xx, yy);
	}

	public void clickSave(WebElement e, String elementName) throws Exception {
		try {
			switch (toolName) {

			case "Appium":
				WebDriverWait wait = new WebDriverWait(appiumDriver, 60, 500);
				wait.until(ExpectedConditions.elementToBeClickable(e));
				break;
			case "Selenium":
				WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 60, 500);
				waitSelenium.until(ExpectedConditions.elementToBeClickable(e));
				break;
			}
			Thread.sleep(3000);
			e.click();
			ExtentUtility.getTest().log(LogStatus.PASS, "Click on Element " + elementName + " successful",
					ExtentUtility.getTest().addScreenCapture(takeScreenShot()));

		} catch (Exception exc) {
			ExtentUtility.getTest().log(LogStatus.FAIL, elementName + " not found",
					exc.toString() + ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
			throw new Exception(elementName + " not found");

		}

	}


	public void click(WebElement e, String elementName) throws Exception {
		try {
			switch (toolName) {

			case "Appium":
				WebDriverWait wait = new WebDriverWait(appiumDriver, 100, 500);
				wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(e)));

				break;
			case "Selenium":
				WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 80, 500);
				waitSelenium.until(ExpectedConditions.elementToBeClickable(e));
				break;
			}

			e.click();
			System.out.println("Clicked " + elementName);
			Thread.sleep(1000);

			ExtentUtility.getTest().log(LogStatus.PASS, "Click on Element " + elementName + " successful",
			 ExtentUtility.getTest().addScreenCapture(takeScreenShot()));

		} catch (Exception exc) {
			ExtentUtility.getTest().log(LogStatus.FAIL, elementName + " not found",
					exc.toString() + ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
			throw new Exception(elementName + " not found");
		}
	}

	public void verifyelementpresence(String ladderScreen) throws Exception {

		try {
			switch (toolName) {

			case "Appium":

				WebDriverWait wait = new WebDriverWait(appiumDriver, 60, 500);

				wait.until(ExpectedConditions.elementToBeClickable(appiumDriver.findElement(By.cssSelector(ladderScreen))));

				break;

			case "Selenium":

				WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 120, 500);

				waitSelenium.until(ExpectedConditions.presenceOfElementLocated((By.cssSelector(ladderScreen))))
						.isDisplayed();

				break;

			}

		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void clickByJse(WebElement e, String elementName) throws Exception {
		try {
			switch (toolName) {

			case "Appium":
				// WebDriverWait wait = new WebDriverWait(appiumDriver, 60, 500);
				JavascriptExecutor jse = (JavascriptExecutor) appiumDriver;
				jse.executeScript("arguments[0].click();", e);
				Thread.sleep(3000);
				break;
			case "Selenium":
				WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 60, 250);
				JavascriptExecutor jse1 = (JavascriptExecutor) remoteDriver;
				jse1.executeScript("arguments[0].click();", e);
				break;
			}

			ExtentUtility.getTest().log(LogStatus.PASS, "Click on Element " + elementName + " successful",
					ExtentUtility.getTest().addScreenCapture(takeScreenShot()));

		} catch (Exception exc) {
			exc.printStackTrace();
			ExtentUtility.getTest().log(LogStatus.FAIL, elementName + " not found",
					exc.toString() + ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
			throw new Exception(elementName + " not found");

		}

	}

	public void clickWithoutSS(WebElement e) throws Exception {
		try {
			switch (toolName) {

			case "Appium":
				WebDriverWait wait = new WebDriverWait(appiumDriver, 60, 500);
				wait.until(ExpectedConditions.visibilityOf(e));
				break;
			case "Selenium":
				WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 60, 250);
				waitSelenium.until(ExpectedConditions.elementToBeClickable(e));
				break;
			}
			e.click();

		} catch (Exception exc) {
			exc.printStackTrace();
			ExtentUtility.getTest().log(LogStatus.FAIL, e + " not found",
					exc.toString() + ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
			throw new Exception(e + " not found");

		}

	}

	public void click_verifyurl(WebElement e, String elementName, String url) throws Exception {
		try {
			while (!remoteDriver.getCurrentUrl().equalsIgnoreCase(url)) {
				e.click();
			}
			ExtentUtility.getTest().log(LogStatus.PASS, "Click on Element " + elementName + " successful",
					ExtentUtility.getTest().addScreenCapture(takeScreenShot()));

		} catch (Exception exc) {
			ExtentUtility.getTest().log(LogStatus.FAIL, elementName + " not found",
					exc.toString() + ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
			throw new Exception(elementName + " not found");

		}

	}

	public void clickHiddentElement(WebElement e, String elementName) throws Exception {
		try {
			switch (toolName) {

			case "Appium":
				JavascriptExecutor executor = (JavascriptExecutor) appiumDriver;
				executor.executeScript("arguments[0].click();", e);
				break;
			case "Selenium":
				JavascriptExecutor executor1 = (JavascriptExecutor) remoteDriver;
				executor1.executeScript("arguments[0].click();", e);
				break;
			}

			ExtentUtility.getTest().log(LogStatus.PASS, "Click on Element " + e + " successful",
					ExtentUtility.getTest().addScreenCapture(takeScreenShot()));

		} catch (Exception exc) {
			exc.printStackTrace();
			ExtentUtility.getTest().log(LogStatus.FAIL, elementName + " not found",
					exc.toString() + ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
			throw new Exception(elementName + " not found");

		}

	}

	public void navigateToToC(String strToCLayer, String strToCItem) {
		try {
			switch (toolName) {

			case "Selenium":
				WebElement testElement = remoteDriver.findElement(By.cssSelector(
						"div[id='tocItemContainer" + strToCLayer + "'] > div > div > div[title='" + strToCItem + "']"));
				if (!testElement.isDisplayed()) {
					scrollTo(remoteDriver, testElement);
				}
				testElement.click();
				break;
			case "Appium":
				WebElement testElement1 = appiumDriver.findElement(By.cssSelector(
						"div[id='tocItemContainer" + strToCLayer + "'] > div > div > div[title='" + strToCItem + "']"));
				if (!testElement1.isDisplayed()) {
					scrollTo(appiumDriver, testElement1);
				}
				testElement1.click();
				break;

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public List<WebElement> getList_ByClassName(WebElement element, String byValue) {
		try {
			switch (toolName) {
			case "Appium":
				WebDriverWait wait = new WebDriverWait(appiumDriver, 30, 500);
				wait.until(ExpectedConditions.visibilityOf(element));
				break;
			case "Selenium":
				WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 60, 250);
				waitSelenium.until(ExpectedConditions.visibilityOf(element));
				break;

			}

		} catch (Exception exc) {

		}
		try {
			if (element.isDisplayed()) {
				List<WebElement> getList = element.findElements(By.className(byValue));

				return getList;
			} else
				return null;
		} catch (Exception exc) {

			return null;

		}
	}
	public List<WebElement> getList_ByXPath(WebElement element, String xPath) 
	{
		try {
			switch (toolName) {
			case "Appium":
				WebDriverWait wait = new WebDriverWait(appiumDriver, 30, 500);
				wait.until(ExpectedConditions.visibilityOf(element));
				break;
			case "Selenium":
				WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 60, 250);
				waitSelenium.until(ExpectedConditions.visibilityOf(element));
				break;

			}

		} catch (Exception exc) {

		}
		try {
			if (element.isDisplayed()) {
				List<WebElement> getList = element.findElements(By.xpath(xPath));
			return getList;
			} else
				return null;
		} catch (Exception exc) {

			return null;

		}
	}	

	public void takeScreenshot_Native(WebElement ele, String screenshotName) throws IOException {
		if (ele.isDisplayed()) {
			File screen = (File) ((TakesScreenshot) appiumDriver).getScreenshotAs(OutputType.FILE);

			int ImageWidth = ele.getSize().getWidth();
			int ImageHeight = ele.getSize().getHeight();
			Point point = ele.getLocation();
			int xcord = point.getX();
			int ycord = point.getY();
			BufferedImage img = ImageIO.read(screen);
			BufferedImage dest = img.getSubimage(xcord, ycord, ImageWidth, ImageHeight);
			ImageIO.write(dest, "png", screen);
			FileUtils.copyFile(screen, new File("NativeApp_Screenshots/" + screenshotName + ".png"));
		}
	}

	public void scrollTo(WebDriver driver, WebElement element)
	{
		((JavascriptExecutor) driver).executeScript("document.getElementById('container id').scrollTop += 250;", "");
	}

	public String getCurrentUrl() throws Exception {
		String url = null;
		try {
			switch (toolName) {

			case "Appium":
				url = appiumDriver.getCurrentUrl();
				break;
			case "Selenium":
				url = remoteDriver.getCurrentUrl();
				break;
			}
		} catch (Exception exc) {
			exc.printStackTrace();
			ExtentUtility.getTest().log(LogStatus.FAIL, exc + "Exception on getting Current Url ",
					exc.toString() + ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
			throw new Exception(exc);

		}
		return url;

	}

	public String fetchContentFromWebUI(String strCss) throws Exception {
		String strText = "";
		switch (toolName) {
		case "Selenium":
			System.out.println("Fetch content from web page");
			WebElement element = remoteDriver.findElement(By.cssSelector(strCss));
			WebElement testElement = remoteDriver.findElement(By.cssSelector("p[id^='p26']>span[id='word1']"));
			System.out.println("Font face/family in eBook for the text - The Language of Reaching Out: "
					+ testElement.getCssValue("font-family"));
			String UIfontface = testElement.getCssValue("font-family");
			assertTrue("Font face/family in eBook for the text - The Language of Reaching Out: "
					+ testElement.getCssValue("font-family"), true);
			System.out.println("Font size in eBook for the text - The Language of Reaching Out: "
					+ testElement.getCssValue("font-size"));
			String UIfontSize = testElement.getCssValue("font-size");
			assertTrue("Font size in eBook for the text - The Language of Reaching Out: "
					+ testElement.getCssValue("font-size"), true);
			strText = element.getText();
			break;
		case "Appium":
			System.out.println("Fetch content from web page");
			WebElement element1 = appiumDriver.findElement(By.cssSelector(strCss));
			WebElement testElement1 = appiumDriver.findElement(By.cssSelector("p[id^='p26']>span[id='word1']"));
			System.out.println("Font face/family in eBook for the text - The Language of Reaching Out: "
					+ testElement1.getCssValue("font-family"));
			UIfontface = testElement1.getCssValue("font-family");
			assertTrue("Font face/family in eBook for the text - The Language of Reaching Out: "
					+ testElement1.getCssValue("font-family"), true);
			System.out.println("Font size in eBook for the text - The Language of Reaching Out: "
					+ testElement1.getCssValue("font-size"));
			UIfontSize = testElement1.getCssValue("font-size");
			assertTrue("Font size in eBook for the text - The Language of Reaching Out: "
					+ testElement1.getCssValue("font-size"), true);
			strText = element1.getText();
			break;
		}
		return strText;
	}

	public void clickByCSS(String e, String text) throws Exception {
		try {
			switch (toolName) {
			case "Appium":

				List<WebElement> li = appiumDriver.findElementsByCssSelector(e);

				break;
			case "Selenium":
				remoteDriver.findElementByCssSelector(e).click();
				break;
			}
			ExtentUtility.getTest().log(LogStatus.PASS, "Click on Element " + e + " successful",
					ExtentUtility.getTest().addScreenCapture(takeScreenShot()));

		} catch (Exception exc) {
			exc.printStackTrace();
			ExtentUtility.getTest().log(LogStatus.FAIL, exc + "Exception on clicking webelement",
					exc.toString() + ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
			throw new Exception(exc);

		}

	}

	public void clickWithoutWait(WebElement e, String elementName) throws Exception {
		try {

			switch (toolName) {

			case "Appium":
				WebDriverWait wait = new WebDriverWait(appiumDriver, 60, 500);
				wait.until(ExpectedConditions.visibilityOf(e));
				break;
			case "Selenium":
				break;
			}
			e.click();
			ExtentUtility.getTest().log(LogStatus.PASS, "Click on Element " + e + " successful",
					ExtentUtility.getTest().addScreenCapture(takeScreenShot()));

		} catch (Exception exc) {
			exc.printStackTrace();
			ExtentUtility.getTest().log(LogStatus.FAIL, exc + "Exception on clicking webelement",
					exc.toString() + ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
			throw new Exception(exc);

		}

	}
	
	//public void clickWithWait(String xpath, String elementName,int seconds) throws Exception
	
	public void clickWithoutWait(String xpath, String elementName) throws Exception
	{
		String screenShot=this.getAppProperties("screenShotRequiredForSuccess");
		try {
			switch (toolName) {

			case "Appium":
				WebDriverWait wait = new WebDriverWait(appiumDriver, 60, 500);
				wait.until(ExpectedConditions.visibilityOf(appiumDriver.findElementByXPath(xpath)));
				appiumDriver.findElementByXPath(xpath).click();
				break;
			case "Selenium":
				Thread.sleep(3000);
				remoteDriver.findElementByXPath(xpath).click();
				break;
			}
			System.out.println("Clicking on Element Successful : "+elementName );
			if (screenShot.contains("Y"))
			{
			ExtentUtility.getTest().log(LogStatus.PASS, "Click on Element " + elementName + " successful",
					ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
			}
			else
			{
				ExtentUtility.getTest().log(LogStatus.PASS, "Click on Element " + elementName + " successful");
			}

		} catch (Exception exc) {
			System.out.println("Clicking on Element Failed : "+elementName );
			exc.printStackTrace();
			ExtentUtility.getTest().log(LogStatus.FAIL, exc + "Exception on clicking webelement",
					exc.toString() + ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
			throw new Exception(exc);
		}

	}

	public void clickWithWait(String xpath, String elementName,int seconds) throws Exception
	{
		appiumDriver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		String screenShot=this.getAppProperties("screenShotRequiredForSuccess");
		try {

			switch (toolName) {

			case "Appium":
				WebDriverWait wait = new WebDriverWait(appiumDriver, 60, 500);
				Thread.sleep(1000);
				wait.until(ExpectedConditions.visibilityOf(appiumDriver.findElementByXPath(xpath)));
				appiumDriver.findElementByXPath(xpath).click();
				Thread.sleep(3000);
				break;
			case "Selenium":
				Thread.sleep(3000);
				remoteDriver.findElementByXPath(xpath).click();
				break;
			}

			System.out.println("Clicking on Element Successful : "+elementName );
			if (screenShot.contains("Y"))
			{
			ExtentUtility.getTest().log(LogStatus.PASS, "Click on Element " + elementName + " successful",
					ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
			}
			else
			{
				ExtentUtility.getTest().log(LogStatus.PASS, "Click on Element " + elementName + " successful");
			}

		} catch (Exception exc) {
			System.out.println("Clicking on Element Failed : "+elementName );
			exc.printStackTrace();
			ExtentUtility.getTest().log(LogStatus.FAIL, exc + "Exception on clicking webelement",
					exc.toString() + ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
			throw new Exception(exc);
		}

	}

	public void clickLinkTest(String xpath, String elementName) throws Exception {
		try {
			switch (toolName) {

			case "Appium":
				WebDriverWait wait = new WebDriverWait(appiumDriver, 60, 500);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
				appiumDriver.findElementByXPath(xpath).click();
				break;
			case "Selenium":
				WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 60, 500);

				waitSelenium.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

				remoteDriver.findElementByLinkText(xpath).click();
				break;
			}
			ExtentUtility.getTest().log(LogStatus.PASS, "Click on Element " + elementName + " successful",
					ExtentUtility.getTest().addScreenCapture(takeScreenShot()));

		} catch (Exception exc) {
			ExtentUtility.getTest().log(LogStatus.FAIL, exc + "Exception on clicking webelement",
					exc.toString() + ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
			throw new Exception(exc);
		}
	}

	public void click(String xpath, String elementName) throws Exception {
		try {
			switch (toolName) {

			case "Appium":
				WebDriverWait wait = new WebDriverWait(appiumDriver, 60, 500);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
				appiumDriver.findElementByXPath(xpath).click();
				break;
			case "Selenium":
				WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 60, 500);

				waitSelenium.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

				remoteDriver.findElementByXPath(xpath).click();
				break;
			}
			ExtentUtility.getTest().log(LogStatus.PASS, "Click on Element " + elementName + " successful",
					ExtentUtility.getTest().addScreenCapture(takeScreenShot()));

		} catch (Exception exc) {
			ExtentUtility.getTest().log(LogStatus.FAIL, exc + "Exception on clicking webelement",
					exc.toString() + ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
			throw new Exception(exc);
		}
	}

	public void clickbyid(String id, String elementName) throws Exception {
		try {
			switch (toolName) {
			case "Appium":
				WebDriverWait wait = new WebDriverWait(appiumDriver, 60, 500);
				appiumDriver.findElementById(id).click();
				break;
			case "Selenium":
				WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 100, 1000);
				remoteDriver.findElementById(id).click();
				break;
			}
			ExtentUtility.getTest().log(LogStatus.PASS, "Click on Element " + elementName + " successful",
					ExtentUtility.getTest().addScreenCapture(takeScreenShot()));

		} catch (Exception exc) {

			exc.printStackTrace();
			ExtentUtility.getTest().log(LogStatus.FAIL, exc + "Exception on clicking webelement",
					exc.toString() + ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
			throw new Exception(exc);

		}
	}

	public void clickbyClassName(String className, String elementName) throws Exception {
		try {
			switch (toolName) {
			case "Appium":
				WebDriverWait wait = new WebDriverWait(appiumDriver, 60, 500);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(className)));
				appiumDriver.findElementByClassName(className).click();

				break;
			case "Selenium":
				WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 100, 1000);
				remoteDriver.findElementByClassName(className).click();
				break;
			}
			ExtentUtility.getTest().log(LogStatus.PASS, "Click on Element " + elementName + " successful",
					ExtentUtility.getTest().addScreenCapture(takeScreenShot()));

		} catch (Exception exc) {

			exc.printStackTrace();
			ExtentUtility.getTest().log(LogStatus.FAIL, exc + "Exception on clicking webelement",
					exc.toString() + ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
			throw new Exception(exc);

		}
	}

	public void clickByElementName(String name, String elementName) throws Exception {

		try {
			switch (toolName) {
			case "Appium":
				WebDriverWait wait = new WebDriverWait(appiumDriver, 60, 500);
				wait.until(ExpectedConditions.elementToBeClickable(By.name(name)));
				appiumDriver.findElementByName(name).click();
				break;
			case "Selenium":
				WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 60, 500);
				waitSelenium.until(ExpectedConditions.elementToBeClickable(By.name(name)));
				remoteDriver.findElementByName(name).click();
				break;
			}

			ExtentUtility.getTest().log(LogStatus.PASS, "Click on Element " + elementName + " successful",
					ExtentUtility.getTest().addScreenCapture(takeScreenShot()));

		} catch (Exception exc) {

			ExtentUtility.getTest().log(LogStatus.FAIL, exc + "Exception on clicking webelement",
					exc.toString() + ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
			throw new Exception(exc);

		}
	}

	public void clickAlert() throws Exception {

		try {
			switch (toolName) {
			case "Appium":
				WebDriverWait wait = new WebDriverWait(appiumDriver, 60, 500);
				Alert a2 = remoteDriver.switchTo().alert();
				a2.accept();
				break;
			case "Selenium":
				WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 60, 500);

				Alert a1 = remoteDriver.switchTo().alert();
				a1.accept();

				break;
			}

		} catch (Exception exc) {
			exc.printStackTrace();

			ExtentUtility.getTest().log(LogStatus.FAIL, exc + "Exception on clicking webelement",
					exc.toString() + ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
			throw new Exception(exc);

		}
	}

	public void dragAndDrop(WebElement e1, WebElement e2) throws Exception {

		try {
			switch (toolName) {
			case "Appium":
				Actions action = new Actions(appiumDriver);
				action.dragAndDrop(e1, e2).perform();

				break;
			case "Selenium":
				Actions action1 = new Actions(remoteDriver);
				action1.dragAndDrop(e1, e2).perform();

				break;
			}

		} catch (Exception exc) {
			exc.printStackTrace();

			ExtentUtility.getTest().log(LogStatus.FAIL, exc + "Exception on Drag element",
					exc.toString() + ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
			throw new Exception(exc);

		}
	}

	public String getPageTitle() throws Exception {

		String pageTitle = "";
		if (toolName.equalsIgnoreCase("Selenium")) {
			pageTitle = remoteDriver.getTitle();
		} else {
			Thread.sleep(5000);
			pageTitle = appiumDriver.getTitle();

		}

		return pageTitle;

	}

	public String getText(WebElement e, String elementName) throws Exception {

		switch (toolName) {
		case "Appium":
			WebDriverWait wait = new WebDriverWait(appiumDriver, 60, 500);
			wait.until(ExpectedConditions.visibilityOf(e));
			break;
		case "Selenium":
			WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 60, 500);
			waitSelenium.until(ExpectedConditions.visibilityOf(e));
			break;
		}
		String text = e.getText();
		ExtentUtility.getTest().log(LogStatus.PASS, "Click on Element " + elementName + " successful",
				ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
		return text;

	}

	public String getValue(WebElement e, String elementName) throws Exception {

		switch (toolName) {
		case "Appium":
			WebDriverWait wait = new WebDriverWait(appiumDriver, 60, 500);
			wait.until(ExpectedConditions.visibilityOf(e));
			break;
		case "Selenium":
			WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 60, 500);
			waitSelenium.until(ExpectedConditions.visibilityOf(e));
			break;
		}

		String text = e.getAttribute("value");
		ExtentUtility.getTest().log(LogStatus.PASS, "Click on Element " + elementName + " successful",
				ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
		return text;

	}

	public String getText(WebElement e) throws Exception {
		switch (toolName) {
		case "Appium":
			WebDriverWait wait = new WebDriverWait(appiumDriver, 60, 500);
			wait.until(ExpectedConditions.visibilityOf(e));
			break;
		case "Selenium":
			WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 60, 500);
			waitSelenium.until(ExpectedConditions.visibilityOf(e));
			break;
		}

		String text = e.getText().trim();
		return text;
	}

	public String getText(String xpath) throws Exception {
		String text = null;
		;
		switch (toolName) {
		case "Appium":
			WebDriverWait wait = new WebDriverWait(appiumDriver, 60, 500);
			wait.until(ExpectedConditions.visibilityOf(appiumDriver.findElementByXPath(xpath)));
			text = appiumDriver.findElementByXPath(xpath).getText();

			break;
		case "Selenium":

			JavascriptExecutor jse = (JavascriptExecutor) remoteDriver;
			WebElement element = remoteDriver.findElement(By.xpath(xpath));
			text = jse.executeScript("return arguments[0].text", element).toString();

			/*
			 * WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 60, 500); //
			 * waitSelenium.until(ExpectedConditions.visibilityOf(remoteDriver.
			 * findElementByXPath(xpath))); text =
			 * remoteDriver.findElementByXPath(xpath).getText();
			 */
			break;
		}

		return text;
	}

	public String getAttributeValue(WebElement e, String attribute) throws Exception {

		switch (toolName) {
		case "Appium":
			WebDriverWait wait = new WebDriverWait(appiumDriver, 60, 500);
			wait.until(ExpectedConditions.visibilityOf(e));
			break;
		case "Selenium":
			WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 60, 500);
			waitSelenium.until(ExpectedConditions.visibilityOf(e));
			break;
		}

		String text = e.getAttribute(attribute);

		return text;

	}

	public void clickMultipleButtons(WebElement tab, WebElement pause, String elementName) throws Exception {

		try {
			Thread.sleep(20000);
			if (elementIsDisplayed(pause, "pausebutton")) {
				pause.click();
			} else {
				tab.click();
				pause.click();
			}
			Thread.sleep(10000);

			ExtentUtility.getTest().log(LogStatus.PASS, "Click on Element " + elementName + " successful",
					ExtentUtility.getTest().addScreenCapture(takeScreenShot()));

		} catch (Exception exc) {
			exc.printStackTrace();
			ExtentUtility.getTest().log(LogStatus.FAIL, exc + "Exception on clicking webelement",
					exc.toString() + ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
			throw new Exception(exc);
		}

	}

	public void switchToCurrentWindowTitle() throws InterruptedException {
		try {

			switch (toolName) {
			case "Appium":
				Thread.sleep(10000);
				int size = appiumDriver.getWindowHandles().size();
				for (String winHandle : appiumDriver.getWindowHandles()) {
					appiumDriver.switchTo().window(winHandle);
				}

				break;
			case "Selenium":
				for (String winHandle : remoteDriver.getWindowHandles()) {
					remoteDriver.switchTo().window(winHandle);
					Thread.sleep(5000);
				}
				break;
			}
		}

		catch (org.openqa.selenium.NoSuchWindowException exc) {
			exc.printStackTrace();
		}

	}

	public String sendPostRequest(String apiName, String cookie, String payload) {
		StringBuffer jsonString = new StringBuffer();

		try {
			URL url = new URL(
					"https://disneydev7.service-now.com/api/x_wadm_wdpr_cast_c/v1/wdpr_cp_svc_castchoir/" + apiName);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Cookie", cookie);
			OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
			writer.write(payload);
			writer.close();
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = br.readLine()) != null) {
				jsonString.append(line);
			}
			br.close();
			connection.disconnect();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return jsonString.toString();
	}

	public StringBuffer getServiceResponse(String serviceUrl) throws Exception {
		String output = null;
		StringBuffer outputResponse = null;
		try {
			Thread.sleep(5000);
			URL url = new URL(serviceUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("source-appl-id", "6");
			conn.setRequestProperty("Cache-Control", "no-cache");
			conn.setRequestProperty("Accept-Resolution", "thumb, medium, high");
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			} else {

			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			// String output;
			System.out.println("Response from Server .... \n");
			PrintWriter out = new PrintWriter("Response/" + "response.txt");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
				if (output.contains("mediaThumb")) {
				}
				if (output.contains("width")) {
				}

				if (output.contains("height")) {
				}
				out.println(output);
			}

			conn.disconnect();
		} catch (Exception ex) {
		}

		return outputResponse;

	}

	public void switchToWindowTitle() throws Exception {

		try {

			switch (toolName) {
			case "Appium":
				Thread.sleep(10000);
				int size = appiumDriver.getWindowHandles().size();
				ParentWinhadleMob = appiumDriver.getWindowHandle();
				for (String winHandle : appiumDriver.getWindowHandles()) {
					appiumDriver.switchTo().window(winHandle);
				}

				break;
			case "Selenium":
				ParentWinhadle = remoteDriver.getWindowHandle();
				for (String winHandle : remoteDriver.getWindowHandles()) {
					remoteDriver.switchTo().window(winHandle);

				}
				break;
			}
		}

		catch (org.openqa.selenium.NoSuchWindowException exc) {
			exc.printStackTrace();
		}

	}

	public void switchToParentWindowTitle() throws Exception {

		try {

			switch (toolName) {
			case "Appium":
				Thread.sleep(10000);
				appiumDriver.close();
				appiumDriver.switchTo().window(ParentWinhadleMob);

				break;
			case "Selenium":
				remoteDriver.close();
				remoteDriver.switchTo().window(ParentWinhadle);
				break;
			}
		}

		catch (org.openqa.selenium.NoSuchWindowException exc) {
			exc.printStackTrace();
		}

	}

	public void selectOPtionByVisibleText(WebElement e, String text) throws InterruptedException {
		Thread.sleep(5000);
		e.click();
		Select sl = new Select(e);
		sl.selectByVisibleText(text);

	}

	public void selectOPtionByIndex(WebElement e, int value) throws InterruptedException {
		Thread.sleep(3000);
		Select sl = new Select(e);
		sl.selectByIndex(value);

	}

	public void switchToFrame(String frameId) throws Exception {

		try {

			switch (toolName) {
			case "Appium":
				Thread.sleep(5000);
				appiumDriver.switchTo().frame(frameId);

				break;
			case "Selenium":

				remoteDriver.switchTo().frame(frameId);
				break;
			}
		}

		catch (org.openqa.selenium.NoSuchWindowException exc) {
			exc.printStackTrace();
		}

	}

	public void switchToFrame(int frameId) throws Exception {

		try {

			switch (toolName) {
			case "Appium":
				Thread.sleep(5000);
				appiumDriver.switchTo().frame(frameId);

				break;
			case "Selenium":

				remoteDriver.switchTo().frame(frameId);
				break;
			}
		}

		catch (org.openqa.selenium.NoSuchWindowException exc) {
			exc.printStackTrace();
		}

	}

	public void switchToParentFrame() throws Exception {

		try {

			switch (toolName) {
			case "Appium":
				Thread.sleep(5000);
				appiumDriver.switchTo().parentFrame();

				break;
			case "Selenium":

				remoteDriver.switchTo().parentFrame();
				break;

			}
		}

		catch (org.openqa.selenium.NoSuchWindowException exc) {
			exc.printStackTrace();
		}

	}

	public String getParentWindow() throws Exception {
		String parentWindow = null;
		if (toolName.equalsIgnoreCase("Appium")) {
			parentWindow = appiumDriver.getWindowHandle();
		} else if (toolName.equalsIgnoreCase("Selenium")) {
			parentWindow = remoteDriver.getWindowHandle();
		}
		return parentWindow;
	}

	public void switchToParentWindow(String parentWindow) throws Exception {

		if (toolName.equalsIgnoreCase("Appium")) {
			appiumDriver.close();
			appiumDriver.switchTo().window(parentWindow);
		} else if (toolName.equalsIgnoreCase("Selenium")) {
			remoteDriver.close();
			remoteDriver.switchTo().window(parentWindow);
		}
	}

	public WebElement getElement(String xpath) throws Exception 
	{	WebElement we;
		try 
		{
		switch (toolName)
		{
		case "Appium":
			WebDriverWait wait = new WebDriverWait(appiumDriver, 60, 500);
			wait.until(ExpectedConditions.visibilityOf(appiumDriver.findElementByXPath(xpath)));
			we = appiumDriver.findElementByXPath(xpath);
			return we;
		case "Selenium":
			WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 60, 500);
			waitSelenium.until(ExpectedConditions.visibilityOf(remoteDriver.findElementByXPath(xpath)));
			WebElement weSelenium = remoteDriver.findElementByXPath(xpath);
			return weSelenium;
		}
	}catch (Exception exc) {}

		ExtentUtility.getTest().log(LogStatus.FAIL, " Get text on webelement successful",
				ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
		return null;

	}
	public List<WebElement> getElements(String xpath) throws Exception 
	{	List<WebElement> elements;
	
		try 
		{
		switch (toolName)
		{
		case "Appium":
			WebDriverWait wait = new WebDriverWait(appiumDriver, 60, 500);
			wait.until(ExpectedConditions.visibilityOf(appiumDriver.findElementByXPath(xpath)));
			elements = appiumDriver.findElementsByXPath(xpath);
			return elements;
		}
	}catch (Exception exc) {}

		ExtentUtility.getTest().log(LogStatus.FAIL, " Get text on webelement successful",
				ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
		return null;

	}

	
	
	
	public boolean verifyText(WebElement e, String value) throws Exception {
		switch (toolName) {
		case "Appium":
			WebDriverWait wait = new WebDriverWait(appiumDriver, 60, 500);
			wait.until(ExpectedConditions.visibilityOf(e));
			break;
		case "Selenium":
			WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 60, 500);
			waitSelenium.until(ExpectedConditions.visibilityOf(e));
			break;
		}
		if (e.getText().contains(value)) {
			ExtentUtility.getTest().log(LogStatus.PASS, "  Verified Element successful ",
					ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
			return true;
		} else {
			ExtentUtility.getTest().log(LogStatus.FAIL, "Exception on Verified webelement",
					ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
			return false;
		}

	}

	public void enterText(WebElement element, String cred, String elementName) throws Exception {
		try {
			switch (toolName) {
			case "Appium":
				WebDriverWait wait = new WebDriverWait(appiumDriver, 60, 500);
				wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(element)));
				break;
			case "Selenium":
				WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 60, 500);
				waitSelenium.until(ExpectedConditions.visibilityOf(element));
				break;
			}

			element.clear();
			element.sendKeys(cred);
			ExtentUtility.getTest().log(LogStatus.PASS, " Enter text in " + elementName + " successful",
					ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
		} catch (Exception exc) {

			ExtentUtility.getTest().log(LogStatus.FAIL, "Enter text failed",
					ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
			throw new Exception(exc + " Exception on Verified webelement");

		}

	}

	public void assertTrue(String message) throws Exception 
	{
		String screenShot = getAppProperties("screenShotRequiredForSuccess"); 
		if (screenShot.contains("Y"))
		{
			ExtentUtility.getTest().log(LogStatus.PASS, message,
				ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
		}
		else
		{
			ExtentUtility.getTest().log(LogStatus.PASS, message);
		}
				//ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
	}

	public void assertFalse(String message) throws Exception {
		ExtentUtility.getTest().log(LogStatus.FAIL, message,
				ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
		throw new Exception(message);
	}

	public boolean navToSubMenu(String subMenu) throws Exception {
		try {
			switch (toolName) {

			case "Appium":
				System.out.println("Navigate to" + subMenu + "menu.");
				appiumDriver.findElement(By.cssSelector("div[title='" + subMenu + "']")).click();
				System.out.println("Navigated to sub-menu '" + subMenu + "'");
				break;
			case "Selenium":
				System.out.println("Navigate to" + subMenu + "menu.");
				remoteDriver.findElement(By.cssSelector("div[title='" + subMenu + "']")).click();
				System.out.println("Navigated to sub-menu '" + subMenu + "'");
				break;
			}

		} catch (Exception exc) {
			System.out.println("Unable to navigate to the sub menu, due to - " + exc.getMessage());
			exc.printStackTrace();
			return false;
		}
		return true;
	}

	public void switchFrame() {
		if (remoteDriver.toString().contains("chrome")) {
			remoteDriver.switchTo().frame(2);
		} else {
			remoteDriver.switchTo().frame(0);
		}
	}

	public void switchToFrame() {
		System.out.println("remoteDriver.toString()" + remoteDriver.toString());
		if (remoteDriver.toString().contains("ie")) {
			remoteDriver.switchTo().frame(2);
		} else {
			remoteDriver.switchTo().frame(0);
		}
	}

	public void clearSystemCache() throws Exception {
		try {
			switch (toolName) {
			case "Appium":
				appiumDriver.manage().deleteAllCookies();
				break;
			case "Selenium":
				remoteDriver.manage().deleteAllCookies();
				break;
			}
		} catch (Exception exc) {
			ExtentUtility.getTest().log(LogStatus.FAIL, " Clear Cookies ",
					ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
			throw new Exception(exc + " Clear Cookies ");
		}

	}

	public boolean elementIsDisplayed(WebElement e, String ElementName) throws Exception {
		try {
			switch (toolName) {
			case "Appium":
				WebDriverWait wait = new WebDriverWait(appiumDriver, 60, 500);
				wait.until(ExpectedConditions.visibilityOf(e));
				break;
			case "Selenium":
				WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 60, 250);
				waitSelenium.until(ExpectedConditions.visibilityOf(e));
				break;

			}

			return true;

		} catch (Exception exc) {
			return false;
		}

	}

	public void isDisplayed(WebElement e, String ElementName) throws Exception {
		try {
			switch (toolName) {
			case "Appium":
				WebDriverWait wait = new WebDriverWait(appiumDriver, 30, 500);
				wait.until(ExpectedConditions.visibilityOf(e));
				break;
			case "Selenium":
				WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 30, 500);
				waitSelenium.until(ExpectedConditions.visibilityOf(e));
				break;

			}
			ExtentUtility.getTest().log(LogStatus.PASS, ElementName + " is displayed",
					ExtentUtility.getTest().addScreenCapture(takeScreenShot()));

		} catch (Exception exc) {
			ExtentUtility.getTest().log(LogStatus.FAIL, exc.toString(),
					ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
			throw new Exception(exc);
		}
	}

	public boolean elementIsDisplayed(WebElement e) throws Exception {
		try {
			switch (toolName) {
			case "Appium":
				WebDriverWait wait = new WebDriverWait(appiumDriver, 5, 50);
				wait.until(ExpectedConditions.visibilityOf(e));
				break;
			case "Selenium":
				WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 5, 50);
				waitSelenium.until(ExpectedConditions.visibilityOf(e));
				break;
			}
		} catch (Exception exc) {
			return false;
		}
		return true;

	}

	public boolean elementIsEnabled(WebElement e) throws Exception {
		try {
			switch (toolName) {
			/*case "Appium":
				WebDriverWait wait = new WebDriverWait(appiumDriver, 30, 500);
				wait.until(ExpectedConditions.visibilityOf(e));*/
			case "Selenium":
				WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 60, 500);
				waitSelenium.until(ExpectedConditions.visibilityOf(e));
			}

			return true;
		} catch (Exception exc) {
			ExtentUtility.getTest().log(LogStatus.FAIL, " Get element visibilty failed ",
					ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
			return false;
		}
	}
	public WebElement getElement(String xpath, String ElementName) throws Exception
	{
		WebElement element=null ;
		try {
			switch (toolName) 
			{
			case "Appium":
				WebDriverWait wait = new WebDriverWait(appiumDriver, 30, 500);
				wait.until(ExpectedConditions.visibilityOf(appiumDriver.findElementByXPath(xpath)));
				if (appiumDriver.findElementByXPath(xpath).isDisplayed())
				{
					element = appiumDriver.findElementByXPath(xpath);
					ExtentUtility.getTest().log(LogStatus.PASS, ElementName + " is displayed",
				    ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
				}				
				break;
			case "Selenium":
				WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 30, 500);
				waitSelenium.until(ExpectedConditions.visibilityOf(remoteDriver.findElementByXPath(xpath)));
				if (remoteDriver.findElementByXPath(xpath).isDisplayed()) 
				{
					element = remoteDriver.findElementByXPath(xpath);
					ExtentUtility.getTest().log(LogStatus.PASS, ElementName + " is displayed",
     				ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
				}

				break;
			}

		} catch (Exception exc) 
		{
			ExtentUtility.getTest().log(LogStatus.FAIL, " Element visibilty failed ",
					ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
		}
			return element;
	}
	public boolean elementIsDisplayed(String xpath, String ElementName) throws Exception {

		try {
			switch (toolName) {
			case "Appium":
				WebDriverWait wait = new WebDriverWait(appiumDriver, 30, 500);
				wait.until(ExpectedConditions.visibilityOf(appiumDriver.findElementByXPath(xpath)));
				if (appiumDriver.findElementByXPath(xpath).isDisplayed()) {
					ExtentUtility.getTest().log(LogStatus.PASS, ElementName + " is displayed",
							ExtentUtility.getTest().addScreenCapture(takeScreenShot()));

				}
				break;
			case "Selenium":
				WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 30, 500);
				waitSelenium.until(ExpectedConditions.visibilityOf(remoteDriver.findElementByXPath(xpath)));
				if (remoteDriver.findElementByXPath(xpath).isDisplayed()) {
					ExtentUtility.getTest().log(LogStatus.PASS, ElementName + " is displayed",
							ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
				}
				break;
			}

		} catch (Exception exc) {
			ExtentUtility.getTest().log(LogStatus.FAIL, " Element visibilty failed ",
					ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
			return false;

		}
		return true;

	}

	public boolean elementIsDisplayed(String xpath) throws Exception {

		try {
			switch (toolName) {
			case "Appium":
				WebDriverWait wait = new WebDriverWait(appiumDriver, 30, 500);
				wait.until(ExpectedConditions.visibilityOf(appiumDriver.findElementByXPath(xpath)));

				break;
			case "Selenium":
				WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 30, 500);
				waitSelenium.until(ExpectedConditions.visibilityOf(remoteDriver.findElementByXPath(xpath)));

				break;
			}

		} catch (Exception exc) {

			return false;

		}
		return true;

	}

	public boolean elementIsDisplayedByName(String name) throws Exception {

		try {
			switch (toolName) {
			case "Appium":
				WebDriverWait wait = new WebDriverWait(appiumDriver, 30, 500);
				wait.until(ExpectedConditions.visibilityOf(appiumDriver.findElementByName(name)));
				break;
			case "Selenium":
				WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 60, 500);
				waitSelenium.until(ExpectedConditions.visibilityOf(remoteDriver.findElementByName(name)));
				break;
			}

		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
		return true;

	}

	public void scroll(String key) {
		switch (toolName) {
		case "Appium":
			for (int i = 0;; i++) {
				boolean shouldBreak = false;

				List<WebElement> listObject = appiumDriver.findElements(By.name("dropdownViewCell_" + i + ""));
				while ((listObject.size()) == 0)
					break;

				for (WebElement wb : listObject) {

					if ((wb.getText().equalsIgnoreCase(key))) {
						wb.click();
						shouldBreak = true;
						break;
					}

				}
				if (shouldBreak)
					break;

			}

		case "Selenium":
			for (int i = 0;; i++) {
				boolean shouldBreak = false;

				List<WebElement> listObject = remoteDriver.findElements(By.name("dropdownViewCell_" + i + ""));
				while ((listObject.size()) == 0)
					break;

				for (WebElement wb : listObject) {

					if ((wb.getText().equalsIgnoreCase(key))) {
						wb.click();
						shouldBreak = true;
						break;
					}

				}
				if (shouldBreak)
					break;

			}

		}

	}

	public static void tapBack(AppiumDriver driver) 
	{
		driver.navigate().back();
	}

	public void clickCoordinates(final int x, final int y) {
		switch (toolName) {
		case "Appium":
			appiumDriver.executeScript("mobile: tap", new HashMap<String, Integer>() {
				{
					put("tapCount", (int) 1);
					put("touchCount", (int) 1);
					put("duration", (int) 0.5);
					put("x", x);
					put("y", y);
				}
			});
			break;
		case "Selenium":
			remoteDriver.executeScript("mobile: tap", new HashMap<String, Integer>() {
				{
					put("tapCount", (int) 2);
					put("touchCount", (int) 1);
					put("duration", (int) 0.5);
					put("x", x);
					put("y", y);
				}
			});
			break;
		}
	}

	public void keyBoardActions(String text) {
		switch (toolName) {
		case "Appium":
			if (text.equalsIgnoreCase("return"))
				appiumDriver.findElementByName(text).click();
			else {
				for (int i = 0; i < text.length(); i++) {
					String alp = text.substring(i, i + 1);
					appiumDriver.findElementByName(alp).click();
				}
			}
		case "Selenium":
			if (text.equalsIgnoreCase("return"))
				remoteDriver.findElementByName(text).click();
			else {
				for (int i = 0; i < text.length(); i++) {
					String alp = text.substring(i, i + 1);
					remoteDriver.findElementByName(alp).click();
				}
			}
		}

	}
	/*
	 * RemoteWebElement element =
	 * (RemoteWebElement)appiumDriver.findElement(By.xpath(
	 * "XCUIElementTypeStaticText")); String elementID = ((RemoteWebElement)
	 * element).getId(); HashMap<String, String> scrollObject = new HashMap<String,
	 * String>(); scrollObject.put("element", elementID); // Only for ‘scroll in
	 * element’ scrollObject.put("direction", "up");
	 * appiumDriver.executeScript("mobile:scroll", scrollObject);
	 */
	
	public void scrollDown(WebElement key) throws Exception {

		Map<String, Object> args = new HashMap<>();
		args.put("direction", "down");
		appiumDriver.executeScript("mobile: swipe", args);
		args.put("direction", "down");
		appiumDriver.executeScript("mobile: swipe", args);
	}
	public void singleScrollDown() throws Exception
	{
		boolean flag = false;		
		
		while (flag == false)
		{
			Map<String, Object> args = new HashMap<>();
			
			args.put("direction", "down");
			appiumDriver.executeScript("mobile: swipe", args);
			
			appiumDriver.executeScript("mobile: scroll", args);
			

		}
	}
	public void swipeDown() throws Exception {

		Map<String, Object> args = new HashMap<>();
		args.put("direction", "down");
		appiumDriver.executeScript("mobile: swipe", args);
	}
	
	public void scroll(WebElement key) throws Exception{
		Map<String,Object> args= new HashMap<>();
		args.put("direction", "up");
		appiumDriver.executeScript("mobile:swipe", args);
	}
	public void swipeUp() throws Exception 
	{
		Map<String, Object> args = new HashMap<>();
		args.put("direction", "up");
		appiumDriver.executeScript("mobile: swipe", args);
	}

	public void swipeLeft(WebElement key) throws Exception {

		Map<String, Object> args = new HashMap<>();
		args.put("direction", "left");
		appiumDriver.executeScript("mobile: swipe", args);
		args.put("direction", "left");
		appiumDriver.executeScript("mobile: swipe", args);

	}
	public void swipeLeft() throws Exception {

		Map<String, Object> args = new HashMap<>();
		args.put("direction", "left");
		appiumDriver.executeScript("mobile: swipe", args);
		args.put("direction", "left");
		appiumDriver.executeScript("mobile: swipe", args);

	}

	public void swipeRight(WebElement key) throws Exception {

		Map<String, Object> args = new HashMap<>();
		args.put("direction", "right");
		appiumDriver.executeScript("mobile: swipe", args);
		args.put("direction", "right");
		appiumDriver.executeScript("mobile: swipe", args);

	}
	public void singleSwipeRight() throws Exception 
	{

		Map<String, Object> args = new HashMap<>();
		args.put("direction", "right");
		appiumDriver.executeScript("mobile: swipe", args);
	}
	//Android
	public void swipeDown(String elementToBeDisplayed) throws Exception
	{
		TouchAction action = new TouchAction(appiumDriver);
		PointOption p1= new PointOption();
		PointOption p2= new PointOption();
		int startPos=600;
		int endPos=-10000;
		WebElement element = this.getElement(elementToBeDisplayed);
		scrollTo(appiumDriver, element);
		//this.scrollDown(element);
		for(int i=0;i<=20;i++)
			{
			if(this.isElementDisplayed(2,elementToBeDisplayed, "Element to be displayed"))
				break;
			//System.out.println("i=>"+i);
			action.press(p1.point(0, startPos))
					.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))) 
					.moveTo(p1.point(0,endPos))
					.release().perform();	
			}
	} 
	//iOS
	public void swipeDownNVerify(String elementToBeDisplayed) throws Exception
	{
		TouchAction action = new TouchAction(appiumDriver);
		PointOption p1= new PointOption();
		PointOption p2= new PointOption();
		int startPos=600;
		int endPos=-10000;
		for(int i=0;i<=20;i++)
			{
			action.press(p1.point(0, startPos))
					.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))) 
					.moveTo(p1.point(0,endPos))
					.release().perform();
			if(this.isElementDisplayed(2,elementToBeDisplayed, "Element to be displayed") && (i>=1))
				break;
			}
	} 
		//iOS
	public void swipeDownForNTimesVerify(String elementToBeDisplayed,int noOfSwipe) throws Exception
	{
		TouchAction action = new TouchAction(appiumDriver);
		PointOption p1= new PointOption();
		PointOption p2= new PointOption();
		int startPos=600;
		int endPos=-10000;
		for(int i=0;i<=7;i++)
			{
			action.press(p1.point(0, startPos))
					.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))) 
					.moveTo(p1.point(0,endPos))
					.release().perform();
			if(this.isElementDisplayed(2,elementToBeDisplayed, "Element to be displayed") && (i>=noOfSwipe))
				break;
			}
	} 
	public void swipeDownForNTimes(int noOfSwipe) throws Exception
	{
		TouchAction action = new TouchAction(appiumDriver);
		PointOption p1= new PointOption();
		PointOption p2= new PointOption();
		int startPos=600;
		int endPos=-10000;
		for(int i=0;i<=noOfSwipe;i++)
			{
			action.press(p1.point(0, startPos))
					.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))) 
					.moveTo(p1.point(0,endPos))
					.release().perform();
					break;
			}
	} 

	public enum DIRECTION {
	    DOWN, UP, LEFT, RIGHT;
	}
	
	public static void swipeMobile(AppiumDriver driver) 
	{
		Dimension size = driver.manage().window().getSize();
        System.out.println(size.height+"height");
        System.out.println(size.width+"width");
         System.out.println(size);
         int startPoint = (int) (size.width / 2);
         int endPoint = (int) (size.width * 0.130);
         int ScreenPlace =(int) (size.height*0.40);          
         int y=(int) ((int)size.height*0.7);
         
          TouchAction ts = new TouchAction(driver);
        //for(int i=0;i<=3;i++) {
        ts.longPress(PointOption.point(40,96 ))
       .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
       .moveTo(PointOption.point(64,1317)).release().perform();

	    }
	
	
	public void scrollToExact(WebElement key) throws Exception {
		try {
			boolean flag = false;
			int count = 0;
			switch (toolName) {
			case "Appium":
				Map<String, Object> args = new HashMap<>();
				while (flag == false && count <= 6)
				{
					args.put("direction", "down");
					appiumDriver.executeScript("mobile: swipe", args);
					count = count + 1;
					if (elementIsDisplayed(key)) {
						flag = true;
						break;
					}
				}
				break;
			case "Selenium":
				// ((ScrollsTo) remoteDriver).scrollToExact(key);
				break;
			}
			ExtentUtility.getTest().log(LogStatus.PASS, "Scroll to Element Successful",
					ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
		} catch (Exception exc) {
			ExtentUtility.getTest().log(LogStatus.FAIL, "Exception on scroll to element",
					ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
			throw new Exception(exc + "Exception on scroll to element" + key);
		}

	}

	public void scrollPage(WebElement key) throws Exception {
		try {
			switch (toolName) {
			case "Appium":
				Actions action = new Actions(appiumDriver);

				action.moveToElement(key).build().perform();

				break;
			case "Selenium":

				break;
			}
			/*
			 * ExtentUtility.getTest().log(LogStatus.PASS, "scroll to element  successful",
			 * ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
			 */
		} catch (Exception exc) {
			ExtentUtility.getTest().log(LogStatus.FAIL, " Exception on scroll to element ",
					ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
			throw new Exception(exc + "Get element visibilty failed ");

		}

	}

	// nfr scenarios
	public void accessNotification() {
		AndroidDriver android = (AndroidDriver) this.appiumDriver;
		android.openNotifications();

	}

	/*
	 * public void setDataConnection(boolean enable) { // TODO Auto-generated method
	 * stub if (this.appiumDriver instanceof AndroidDriver) { AndroidDriver android
	 * = (AndroidDriver) this.appiumDriver; NetworkConnectionSetting setting =
	 * android.getNetworkConnection(); setting.dataEnabled();
	 * android.setNetworkConnection(setting); String mode = enable ? "ON" : "OFF";
	 * System.out.println("Current Status of data network:" +
	 * setting.dataEnabled()); } }
	 * 
	 * public void setAirplaneConnection(boolean enable) { // TODO Auto-generated
	 * method stub if (this.appiumDriver instanceof AndroidDriver) { AndroidDriver
	 * android = (AndroidDriver) this.appiumDriver; NetworkConnectionSetting setting
	 * = android.getNetworkConnection();
	 * 
	 * setting.setAirplaneMode(true);
	 * 
	 * android.setNetworkConnection(setting); String mode = enable ? "ON" : "OFF"; }
	 * }
	 * 
	 * public void setWifiConnection(boolean enable) { // TODO Auto-generated method
	 * stub System.out.println("before +++++"); if (this.appiumDriver instanceof
	 * AndroidDriver) { System.out.println("inside +++++"); AndroidDriver android =
	 * (AndroidDriver) this.appiumDriver; NetworkConnectionSetting setting =
	 * android.getNetworkConnection(); setting.setWifi(enable);
	 * android.setNetworkConnection(setting);
	 * System.out.println("Current Status of wifi:" + setting.wifiEnabled()); String
	 * mode = enable ? "ON" : "OFF"; } }
	 */

	public void keyboardActions(WebElement e, Keys key) {

		try {
			switch (toolName) {

			case "Appium":
				WebDriverWait wait = new WebDriverWait(appiumDriver, 60, 500);
				wait.until(ExpectedConditions.visibilityOf(e));
				break;
			case "Selenium":
				WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 60, 500);
				waitSelenium.until(ExpectedConditions.visibilityOf(e));
				waitSelenium.until(ExpectedConditions.elementToBeClickable(e));
				break;
			}
			e.sendKeys(key);

		} catch (Exception exc) {

		}

	}

	public void dragAndDropElement(String dragFromXpath, String dragToXpath, int xOffset, int yOffset)
			throws Exception {
		//WebElement dragFrom = remoteDriver.findElementByXPath(dragFromXpath);
		WebElement dragFrom = remoteDriver.findElementById(dragFromXpath);
		System.out.println(dragFrom);
		//WebElement dragTo = remoteDriver.findElementByXPath(dragToXpath);
		WebElement dragTo = remoteDriver.findElementById(dragToXpath);
		System.out.println(
				"dragFrom =" + dragFrom + " dragTo = " + dragTo + "xOffset = " + xOffset + " yOffset =" + yOffset);
		// Setup robot
		Robot robot = new Robot();
		robot.setAutoDelay(500);

		// Fullscreen page so selenium coordinates work
		robot.mouseMove(250, 450);
		robot.mousePress(InputEvent.BUTTON1_MASK);

		// Get size of elements
		org.openqa.selenium.Dimension fromSize = dragFrom.getSize();
		org.openqa.selenium.Dimension toSize = dragTo.getSize();

		// Get centre distance
		int xCentreFrom = fromSize.width / 2;
		int yCentreFrom = fromSize.height / 2;
		int xCentreTo = toSize.width / 2;
		int yCentreTo = toSize.height / 2;

		Point toLocation = dragTo.getLocation();
		Point fromLocation = dragFrom.getLocation();
		System.out.println(fromLocation.toString());

		// Make Mouse coordinate centre of element
		toLocation.x += xOffset + xCentreTo;
		toLocation.y += yOffset + yCentreTo;
		fromLocation.x += xOffset + xCentreFrom;
		fromLocation.y += yOffset + yCentreFrom;

		System.out.println(fromLocation.toString());

		// Move mouse to drag from location
		robot.mouseMove(fromLocation.x, fromLocation.y);
		// Thread.sleep(10000);
		// robot.mouseMove(175,250);

		// Thread.sleep(1000);
		// Click and drag
		robot.mousePress(InputEvent.BUTTON1_MASK);

		// robot.mousePress(InputEvent.
		// Drag events require more than one movement to register
		// Just appearing at destination doesn't work so move halfway first
		robot.mouseMove(((toLocation.x - fromLocation.x) / 2) + fromLocation.x,
				((toLocation.y - fromLocation.y) / 2) + fromLocation.y);

		// Move to final position
		for (double i = (toLocation.x / 2); i < toLocation.x;) {
			robot.mouseMove((int) i, toLocation.y);
			i = i + 10;
		}
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
		ExtentUtility.getTest().log(LogStatus.PASS, " Drag element successful",
				ExtentUtility.getTest().addScreenCapture(takeScreenShot()));

	}
	
	public void dragAndDropElementiOS(String dragFromXpath, String dragToXpath, int xOffset, int yOffset)
			throws Exception {
		WebElement dragFrom = appiumDriver.findElementByXPath(dragFromXpath);
		WebElement dragTo = appiumDriver.findElementByXPath(dragToXpath);
		System.out.println(
				"dragFrom =" + dragFrom + " dragTo = " + dragTo + "xOffset = " + xOffset + " yOffset =" + yOffset);
		// Setup robot
		Robot robot = new Robot();
		robot.setAutoDelay(500);

		// Fullscreen page so selenium coordinates work
		robot.mouseMove(200, 200);
		robot.mousePress(InputEvent.BUTTON1_MASK);

		// Get size of elements
		org.openqa.selenium.Dimension fromSize = dragFrom.getSize();
		org.openqa.selenium.Dimension toSize = dragTo.getSize();

		// Get centre distance
		int xCentreFrom = fromSize.width / 2;
		int yCentreFrom = fromSize.height / 2;
		int xCentreTo = toSize.width / 2;
		int yCentreTo = toSize.height / 2;

		Point toLocation = dragTo.getLocation();
		Point fromLocation = dragFrom.getLocation();
		System.out.println(fromLocation.toString());

		// Make Mouse coordinate centre of element
		toLocation.x += xOffset + xCentreTo;
		toLocation.y += yOffset + yCentreTo;
		fromLocation.x += xOffset + xCentreFrom;
		fromLocation.y += yOffset + yCentreFrom;

		System.out.println(fromLocation.toString());

		// Move mouse to drag from location
		robot.mouseMove(fromLocation.x, fromLocation.y);
		// Thread.sleep(10000);
		// robot.mouseMove(175,250);

		// Thread.sleep(1000);
		// Click and drag
		robot.mousePress(InputEvent.BUTTON1_MASK);

		// robot.mousePress(InputEvent.
		// Drag events require more than one movement to register
		// Just appearing at destination doesn't work so move halfway first
		robot.mouseMove(((toLocation.x - fromLocation.x) / 2) + fromLocation.x,
				((toLocation.y - fromLocation.y) / 2) + fromLocation.y);

		// Move to final position
		for (double i = (toLocation.x / 2); i < toLocation.x;) {
			robot.mouseMove((int) i, toLocation.y);
			i = i + 10;
		}
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
		ExtentUtility.getTest().log(LogStatus.PASS, " Drag element successful",
				ExtentUtility.getTest().addScreenCapture(takeScreenShot()));

	}

	public void scrollTo(String xpath, String element) throws Exception {
		try {
			((JavascriptExecutor) remoteDriver).executeScript("arguments[0].scrollIntoView();",
					remoteDriver.findElement(By.xpath(xpath)));

			ExtentUtility.getTest().log(LogStatus.PASS, "Scroll to element " + "" + element + "" + " successful",
					ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
		} catch (Exception exc) {
			ExtentUtility.getTest().log(LogStatus.FAIL, "Exception on scroll to element" + element,
					ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
			throw new Exception(exc + "Exception on scroll to element" + element);
		}

	}

	public void scrollTo(WebElement e, String element) throws Exception {
		try {
			((JavascriptExecutor) remoteDriver).executeScript("arguments[0].scrollIntoView();", e);
			ExtentUtility.getTest().log(LogStatus.PASS, "Scroll to element " + "" + element + "" + " successful",
					ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
		} catch (Exception exc) {
			ExtentUtility.getTest().log(LogStatus.FAIL, "Exception on scroll to element" + element,
					ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
			throw new Exception(exc + "Exception on scroll to element" + element);

		}

	}

	public void moveToElement(String xpath1, String xpath2, String elementName) throws Exception {
		Actions actions;
		try {

			switch (toolName) {

			case "Appium":
				actions = new Actions(appiumDriver);
				actions.moveToElement(appiumDriver.findElementByXPath(xpath1)).build().perform();
				break;
			case "Selenium":
				actions = new Actions(remoteDriver);
				actions.moveToElement(remoteDriver.findElementByXPath(xpath1))
						.moveToElement(remoteDriver.findElementByXPath(xpath2)).build().perform();
				actions.click().build().perform();
				break;
			}

			ExtentUtility.getTest().log(LogStatus.PASS, "Move to element " + "" + elementName + "" + " successful",
					ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
		} catch (Exception exc) {

			ExtentUtility.getTest().log(LogStatus.FAIL, "Exception on Move to element" + elementName,
					ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
			throw new Exception(exc + "Exception on Move to element" + elementName);

		}
	}

	public void moveToElement(WebElement element, String elementName) throws Exception {
		Actions actions;
		try {

			switch (toolName) {

			case "Appium":
				actions = new Actions(appiumDriver);
				actions.moveToElement(element).build().perform();
				break;
			case "Selenium":
				actions = new Actions(remoteDriver);
				actions.moveToElement(element).build().perform();
				break;
			}

			ExtentUtility.getTest().log(LogStatus.PASS, "Move to element " + "" + element + "" + " successful",
					ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
		} catch (Exception exc) {
			ExtentUtility.getTest().log(LogStatus.FAIL, "Exception on Move to element" + elementName,
					ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
			throw new Exception(exc + "Exception on Move to element" + elementName);
		}
	}

	public void assertTrue(String msg, boolean cond) throws Exception 
	{   String screenShot=this.getAppProperties("screenShotRequiredForSuccess");
		System.out.println(msg);
		if (cond) {
			if (screenShot.contains("Y"))
			{ExtentUtility.getTest().log(LogStatus.PASS, msg,
					ExtentUtility.getTest().addScreenCapture(takeScreenShot()));}
			else 
			{ExtentUtility.getTest().log(LogStatus.PASS, msg);}
			
		} else {
			ExtentUtility.getTest().log(LogStatus.FAIL, msg,
					ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
			throw new Exception(msg);
		}
	}

	public void assertTrue(boolean condition, String message) throws Exception 
	{   String screenShot=this.getAppProperties("screenShotRequiredForSuccess");
		System.out.println(message);
		if(condition==true) 
		{
			if (screenShot.contains("Y"))
			{ExtentUtility.getTest().log(LogStatus.PASS, message, ExtentUtility.getTest().addScreenCapture(takeScreenShot()));}
			else 
			{ExtentUtility.getTest().log(LogStatus.PASS, message);}
		}
		else
		{
			ExtentUtility.getTest().log(LogStatus.FAIL, message, ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
		}

	}

	public void assertFalse(boolean condition, String message) throws Exception 
	{
		String screenShot=this.getAppProperties("screenShotRequiredForSuccess");
		System.out.println(message);
		if (condition==false )
		{
			if (screenShot.contains("Y"))
			{ExtentUtility.getTest().log(LogStatus.PASS, message, ExtentUtility.getTest().addScreenCapture(takeScreenShot()));}
			else 
			{ExtentUtility.getTest().log(LogStatus.PASS, message);}
		}
		else
		{
		ExtentUtility.getTest().log(LogStatus.FAIL, message,
				ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
		throw new Exception(message);
		}
	}

	public void assertEquals(String message, Object expected, Object actual) throws Exception {
		if (expected == null && actual == null) {
			return;
		}
		if (expected != null && expected.equals(actual)) {
			return;
		}
		ExtentUtility.getTest().log(LogStatus.FAIL,
				"Strings are not matched...Excepted is :" + expected + " but actual is :" + actual,
				ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
		throw new Exception("Strings are not matched...Excepted is :" + expected + " but actual is :" + actual);
	}

	public void assertEquals(String expected, String actual) throws Exception {
		if (expected == null && actual == null) {
			return;
		}
		if (expected != null && expected.equals(actual)) {
			return;
		}
		ExtentUtility.getTest().log(LogStatus.FAIL,
				"Strings are not matched...Excepted is :" + expected + " but actual is :" + actual,
				ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
		throw new Exception("Strings are not matched...Excepted is :" + expected + " but actual is :" + actual);
	}

	public void assertEquals(String message, String expected, String actual) throws Exception {
		if (expected == null && actual == null) {
			ExtentUtility.getTest().log(LogStatus.PASS,
					message + "...Excepted is :" + expected + " and actual is :" + actual + " is displayed",
					ExtentUtility.getTest().addScreenCapture(takeScreenShot()));

			return;
		}
		if (expected != null && expected.equals(actual)) {
			ExtentUtility.getTest().log(LogStatus.PASS,
					message + "...Excepted is :" + expected + " and actual is :" + actual + " is displayed",
					ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
			return;
		}
		ExtentUtility.getTest().log(LogStatus.FAIL,
				"Strings are not matched...Excepted is :" + expected + " but actual is :" + actual,
				ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
		throw new Exception("Strings are not matched...Excepted is :" + expected + " but actual is :" + actual);
	}

	public void assertEquals(String message, double expected, double actual, double delta) throws Exception {
		if (Double.compare(expected, actual) == 0) {
			return;
		}
		if (!(Math.abs(expected - actual) <= delta)) {
			ExtentUtility.getTest().log(LogStatus.FAIL, message + actual,
					ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
			throw new Exception(message);
		}
	}

	public void clickEscape() 
	{
		Actions action = new Actions(remoteDriver);
		action.sendKeys(Keys.ESCAPE);
	}

	public void assertEquals(String message, long expected, long actual) throws Exception {
		if (new Long(expected) != null && new Long(expected).equals(new Long(actual))) {
			return;
		}
		ExtentUtility.getTest().log(LogStatus.FAIL, message + actual,
				ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
		throw new Exception(message);
	}

	public void waitForPageLoad() {
		switch (toolName) {

		case "Appium":
			WebDriverWait wait = new WebDriverWait(appiumDriver, 60 * 15);
			wait.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver wdriver) {
					return ((JavascriptExecutor) appiumDriver).executeScript("return document.readyState")
							.equals("complete");
				}
			});
			break;
		case "Selenium":
			WebDriverWait wait1 = new WebDriverWait(remoteDriver, 60 * 15);
			wait1.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver wdriver) {
					return ((JavascriptExecutor) remoteDriver).executeScript("return document.readyState")
							.equals("complete");
				}
			});
			break;

		}
	}

	public void waitFor(int wait_time) throws InterruptedException {
		Thread.sleep(wait_time * 1000);
	}

	public void waitForVisibilityOfElement(WebElement e) throws Exception {
		try {
			switch (toolName) {

			case "Appium":
				WebDriverWait wait = new WebDriverWait(appiumDriver, 60, 500);
				wait.until(ExpectedConditions.visibilityOf(e));
				break;
			case "Selenium":
				WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 100, 500);
				waitSelenium.until(ExpectedConditions.visibilityOf(e));
				break;
			}

		} catch (Exception exc) {
			exc.printStackTrace();
			ExtentUtility.getTest().log(LogStatus.FAIL, "Exception on waiting for webelement",
					ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
			throw new Exception(exc + "Exception on waiting for webelement");
		}
	}

	public void waitForInvisibilityOfElement(String xpath) throws Exception {

		try {
			switch (toolName) {

			case "Appium":
				WebDriverWait wait = new WebDriverWait(appiumDriver, 60, 500);
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath)));
				break;
			case "Selenium":
				WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 100, 500);
				waitSelenium.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath)));
				break;
			}

		} catch (Exception exc) {
			exc.printStackTrace();
			ExtentUtility.getTest().log(LogStatus.FAIL, "Exception on waiting for webelement",
					ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
			throw new Exception(exc + "Exception on waiting for webelement");

		}
	}

	public void doubleClickOnString(WebElement element, String strNextWord) throws AWTException {
		JavascriptExecutor js = null;
		final String JS_GET_WORD_RECT = "var ele=arguments[0], word=arguments[1], rg=document.createRange();   "
				+ "for(var c=ele.firstChild, i; c; c=c.nextSibling){                     "
				+ "  if(c.nodeType != 3 || (i=c.nodeValue.indexOf(word)) < 0) continue;  "
				+ "  rg.setStart(c, i); rg.setEnd(c, i + word.length);                   "
				+ "  var r = ele.getBoundingClientRect(), rr = rg.getClientRects()[0];   "
				+ "  return { left: (rr.left-r.left) | 0, top: (rr.top-r.top) | 0,       "
				+ "           width: rr.width | 0, height: rr.height | 0 };              " + "};";

		switch (toolName) {

		case "Appium":
			js = (JavascriptExecutor) appiumDriver;
			break;

		case "Selenium":
			js = (JavascriptExecutor) remoteDriver;
			break;
		}
		// Get the text element
		// WebElement element = driver.findElement(By.cssSelector("#p4-textid2 >
		// span.p4-styleid2"));

		// Get the relative position/size {left, top, width, height} for the
		// word - strNextWord
		Map rect = (Map) js.executeScript(JS_GET_WORD_RECT, element, strNextWord);

		// Define a relative click point for the previous word "below"
		Long offset_x = (long) rect.get("left") - (long) rect.get("width") / 2;
		Long offset_y = (long) rect.get("top") + (long) rect.get("height") / 2;

		System.out.println(offset_x.intValue());
		System.out.println(offset_y.intValue());

		switch (toolName) {

		case "Appium":
			// Double click the word
			System.out.println("before press");
			new Actions(appiumDriver).moveToElement(element, offset_x.intValue(), offset_y.intValue()).doubleClick()
					.build().perform();

			System.out.println("after press");

			break;

		case "Selenium":
			// Double click the word
			new Actions(remoteDriver).moveToElement(element, offset_x.intValue(), offset_y.intValue()).doubleClick()
					.perform();
			break;
		}

	}

	public void waitForAjax(int timeoutInSeconds) {

		try {
			if (remoteDriver instanceof JavascriptExecutor) {
				JavascriptExecutor jsDriver = (JavascriptExecutor) remoteDriver;
				Boolean ajaxCondtn = false;
				for (int i = 0; i < timeoutInSeconds; i++) {
					for (int j = 0; j < 20; j++) {
						try {

							ajaxCondtn = (Boolean) jsDriver.executeScript("return window.jQuery != undefined");

							if (ajaxCondtn)
								break;
							else
								Thread.sleep(1000);
						} catch (Exception e) {

						}
					}

					if (!ajaxCondtn)
						continue;
					Object numberOfAjaxConnections = jsDriver.executeScript("return jQuery.active");

					if (numberOfAjaxConnections instanceof Long) {
						Long n = (Long) numberOfAjaxConnections;

						if (n.longValue() == 0L)
							break;
					}
					Thread.sleep(1000);
				}
			} else {
				System.out.println("Web driver: " + remoteDriver + " cannot execute javascript");
			}
		} catch (InterruptedException e) {
			System.out.println(e);
		}
	}

	public void WaitForAjax() throws InterruptedException {
		Thread.sleep(3000);
		JavascriptExecutor executor = (JavascriptExecutor) remoteDriver;
		if ((Boolean) executor.executeScript("return window.jQuery != undefined")) {
			while (!(Boolean) executor.executeScript("return jQuery.active == 0")) {
				Thread.sleep(1000);
			}
		}
		return;
	}

	public void scrollToAnElementByText(AppiumDriver<WebElement> driver, String text) {

		driver.findElement(MobileBy.AndroidUIAutomator(
				"new UiScrollable(new UiSelector())" + ".scrollIntoView(new UiSelector().text(\"" + text + "\"));"));
	}

	private boolean isElementPresent(WebElement text) {
		// TODO Auto-generated method stub
		return false;
	}

	@SuppressWarnings("deprecation")
	public void clickBackButton() {

		((AndroidDriver) appiumDriver).pressKeyCode(AndroidKeyCode.BACK);

	}

	public void scroll(int fromX, int fromY, int toX, int toY) {
		TouchAction touchAction = new TouchAction(appiumDriver);
		//touchAction.longPress(PointOption.point(fromX, fromY)).moveTo(PointOption.point(toX, toY)).release().perform();
		//touchAction.longPress(207, 582).moveTo(8, -360).release().perform();
	}

	public void scrollUp(WebElement element) throws Exception {
		boolean flag = false;
		int i = 0;
		int pressX = appiumDriver.manage().window().getSize().width / 2;
		int bottomY = appiumDriver.manage().window().getSize().height * 4 / 5;
		int topY = appiumDriver.manage().window().getSize().height / 8;

		// scroll with TouchAction by itself
		while (flag == false && i <= 5) {
			scroll(pressX, bottomY, pressX, topY);
			i = i++;
			if (elementIsDisplayed(element)) {
				flag = true;
			}
		}
	}

	public void scroll1(int fromX, int fromY, int toX, int toY) {
		TouchAction touchAction = new TouchAction(appiumDriver);
		//touchAction.longPress(PointOption.point(fromY, fromX)).moveTo(PointOption.point(toY, toX)).release().perform();
	}

	public void scrollDownAndroid(int durationInMs) throws Exception {
		Dimension size = appiumDriver.manage().window().getSize();
		System.out.println(size);
		int starty = (int) (size.height * 0.50);
		// Ending y location set to 20% of the height (near top)
		int endy = (int) (size.height * 0.20);
		// x position set to mid-screen horizontally
		int startx = size.width / 2;
		scroll(startx, endy, startx, starty);
		scroll(startx, endy, startx, starty);
		scroll(startx, endy, startx, starty);

	}

	public void rightLeftSwipe(int durationInMs) throws Exception {
		Dimension size = appiumDriver.manage().window().getSize();
		System.out.println(size);
		int startx = (int) (size.width * 0.90);
		// Ending y location set to 20% of the height (near top)
		int endx = (int) (size.width * 0.30);
		// x position set to mid-screen horizontally
		int starty = size.height / 2;
		scroll(startx, starty, endx, starty);
		scroll(startx, starty, endx, starty);
		scroll(startx, starty, endx, starty);
		scroll(startx, starty, endx, starty);
		scroll(startx, starty, endx, starty);
		scroll(startx, starty, endx, starty);

	}

	public void leftRightSwipe(int durationInMs) throws Exception {
		Dimension size = appiumDriver.manage().window().getSize();
		System.out.println(size);
		int startx = (int) (size.width * 0.70);
		// Ending y location set to 20% of the height (near top)
		int endx = (int) (size.width * 0.30);
		// x position set to mid-screen horizontally
		int starty = size.height / 2;
		scroll(endx, starty, startx, starty);

	}

	// ====================================================================================================

	public static void swipeHorizontal(AppiumDriver<WebElement> driver) throws Exception {
		// Vertical scroll down by 50 pixels :
		JavascriptExecutor jsx = (JavascriptExecutor) driver;
		jsx.executeScript("window.scrollBy(0,50)", "");

		// Vertical scroll up by 50 pixels :
		jsx.executeScript("window.scrollBy(0,-50)", "");

		// Horizontal scroll toward right by 50 pixels :
		jsx.executeScript("window.scrollBy(50,0)", "");

		// Horizontal scroll toward left by 50 pixels :
		jsx.executeScript("window.scrollBy(-50,0)", "");
	}

	public void swipeLeftOrRight(AppiumDriver driver, String someDirection, int numberOfSwipes) {
		{
			Dimension windowSize = driver.manage().window().getSize();
			System.out.println("Window dimensions: " + windowSize);

			// Get the width - X
			int widthX = windowSize.getWidth();
			System.out.println("Width: " + widthX);

			// Get the height - Y
			int heightY = windowSize.getHeight();
			System.out.println("Height: " + heightY);

			// For storing percentage
			Float percentage = null;

			percentage = (float) ((90 * 100) / widthX);
			System.out.println("90% of screen width equals: " + percentage + " in X pixel coordinate");

			// Will hold our direction
			float fromX = widthX - 30; // The full width minus 30 pixels
			float toX = percentage;

			if (someDirection.contains("left")) {
				fromX = widthX;
				toX = percentage;
			}
			if (someDirection.contains("right")) {
				fromX = percentage;
				toX = widthX;
			}

			JavascriptExecutor jse = (JavascriptExecutor) driver;
			System.out.println("Trying to swipe " + someDirection);

			// Swipe direction passed in as well as the number of swipes passed in
			for (int i = 0; i < numberOfSwipes; i++) {
				Map<String, Object> params = new HashMap<>();
				params.put("duration", .250);
				params.put("fromX", fromX);
				params.put("fromY", heightY / 2);
				params.put("toX", toX);
				params.put("toY", heightY / 2);
				// params.put("element", ((RemoteWebElement) element).getId());
				jse.executeScript("mobile: dragFromToForDuration", params);
				System.out
						.println("We think we swiped " + someDirection + " " + i + " of " + numberOfSwipes + " times.");
			}
		}
	}

	public WebElement check_AccessibilityElementPresent(String strElement, String updateLog) throws IOException {
		try {

			if (appiumDriver.findElementByAccessibilityId(strElement) != null) {

				return appiumDriver.findElementByAccessibilityId(strElement);
			} else {
//				ExtentUtility.getTest().log(LogStatus.FAIL, "The element " + updateLog + " is not displayed ",
//						ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
				return null;
			}

		} catch (Exception E) {
			System.out.println(E.toString());
//			ExtentUtility.getTest().log(LogStatus.FAIL, "The element " + updateLog + " is not displayed ",
//					ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
			return null;
		}
	}

	public void click_mobiletap_coordinates(int x, int y) throws Exception {
		TouchAction action = new TouchAction(appiumDriver);
		// action.tap(x,y).perform();
	}

	public void click_mobiletap_coordinates1(int x, int y) throws Exception {
		TouchAction action = new TouchAction(appiumDriver);
		// action.longPress(x,y).perform();
	}

	public void Page(WebElement e, String elementName) throws Exception {
		try {
			switch (toolName) {

			case "Appium":
				WebDriverWait wait = new WebDriverWait(appiumDriver, 80, 500);
				wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(e)));

				break;
			case "Selenium":
				WebDriverWait waitSelenium = new WebDriverWait(remoteDriver, 80, 500);
				waitSelenium.until(ExpectedConditions.visibilityOf(e));
				break;
			}

			e.getText();
			System.out.println("-----" + e.getText());
			ExtentUtility.getTest().log(LogStatus.PASS,
					"Verify AFL App -" + elementName + " : " + (e.getText()) + " is displayed",
					ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
			// System.out.println("Corresponding " + elementName + (e.getText())+ " is
			// displayed");

		} catch (Exception exc) {

			System.out.println("Page is not displayed");
			ExtentUtility.getTest().log(LogStatus.FAIL, "Verify AFL App -" + elementName + " not found",
					exc.toString() + ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
			throw new Exception(elementName + " not found");

		}

	}
	public void swipeDownElement(WebElement key) {
		Map<String, Object> args = new HashMap();
		args.put("direction", "down");
		appiumDriver.executeScript("mobile: swipe", args);
	
	}
	public boolean waitUntilAccessiblityID_InVisible(String element, int Milliseconds) {
		boolean present = false;
		for (int i = 0; isAccessibilityElementPresent(element) == true; i++) {
			if (i >= Milliseconds) {
				present = true;
				return present;
			}
		}
		return present;
	}

	public boolean isAccessibilityElementPresent(String strElement) {
		try {
			return appiumDriver.findElementByAccessibilityId(strElement).isDisplayed();

		} catch (Exception e) {
			return false;
		}
	}

	public WebElement waitUntil_AccessibilityIdVisible(String strElement, int Milliseconds, String elementName)
			throws Exception {
		WebElement element = null;
		for (int i = 0; element == null; i++) {
			element = chkisAccessibilityElementPresent(strElement);
			if (i > Milliseconds) {
				element = null;
				if (!elementName.equals("") && elementName != null) {

				}
				break;
			}
		}
		return element;
	}

	public WebElement chkisAccessibilityElementPresent(String strElement) {
		try {
			WebElement element = appiumDriver.findElementByAccessibilityId(strElement);
			if (element.isDisplayed()) {
				return element;
			} else
				return null;
		} catch (Exception E) {
			return null;
		}
	}

	public WebElement chkElement_ByAccessibilityId(String accId, int accId_Index) {
		try {
			List<WebElement> getaccId_List = appiumDriver.findElementsByAccessibilityId(accId);
			if (getaccId_List != null) {
				if (getaccId_List.size() >= 0) {
					// return getaccId_List.get(getaccId_List.size()-1);
					return getaccId_List.get(accId_Index);
				} else {
					ExtentUtility.getTest().log(LogStatus.FAIL, "Trying to access Out of Index " + accId,
							ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
					return null;
				}
			} else {
				ExtentUtility.getTest().log(LogStatus.FAIL, "No items available in the list" + accId,
						ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
				return null;
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return null;

	}
	// ============================================OpenCV
	// Methods=========================================

	public static Mat image = null;
	public static Mat image1 = null;
	public static Mat image2 = null;

	public void Match(Mat img2, org.openqa.selenium.Point location, Dimension size)
			throws IOException, InterruptedException {
		int l = 0, k = 0;
		int match = 0;
		img2 = Imgcodecs.imread("Errors/Black.png", Imgcodecs.CV_LOAD_IMAGE_COLOR);
		for (k = 0; k < 10; k++) {
			System.out.println("the function");
			String filename = "Errors/error1.png";
			File src = ((TakesScreenshot) remoteDriver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(src, new File(filename));
			System.out.println(filename);
			Thread.sleep(2000);
			Mat img1 = Imgcodecs.imread(filename, Imgcodecs.CV_LOAD_IMAGE_COLOR);
			System.out.println("face detect");

			double left = location.x;
			double top = location.y;
			double bottom = location.y + size.getHeight() - 100;
			double right = location.x + size.getWidth() - 130;

			Rect faceDetections = new Rect((int) left, (int) top, (int) right, (int) bottom);
			Mat img = new Mat(img1, faceDetections);

			filename = "Errors/match1.png";
			Imgcodecs.imwrite("Errors/match1.png", img);

			Mat img3 = Imgcodecs.imread("Errors/error.png", Imgcodecs.CV_LOAD_IMAGE_COLOR);

			FeatureDetector fd = FeatureDetector.create(FeatureDetector.BRISK);
			final MatOfKeyPoint keyPointsLarge = new MatOfKeyPoint();
			final MatOfKeyPoint keyPointsSmall = new MatOfKeyPoint();
			fd.detect(img3, keyPointsLarge);
			fd.detect(img2, keyPointsSmall);

			Mat descriptorsLarge = new Mat();
			Mat descriptorsSmall = new Mat();

			DescriptorExtractor extractor = DescriptorExtractor.create(DescriptorExtractor.BRISK);
			extractor.compute(img3, keyPointsLarge, descriptorsLarge);
			extractor.compute(img2, keyPointsSmall, descriptorsSmall);

			MatOfDMatch matches = new MatOfDMatch();

			DescriptorMatcher matcher = DescriptorMatcher.create(DescriptorMatcher.BRUTEFORCE_HAMMINGLUT);
			matcher.match(descriptorsLarge, descriptorsSmall, matches);

			MatOfDMatch matchesFiltered = new MatOfDMatch();

			List<DMatch> matchesList = matches.toList();
			List<DMatch> bestMatches = new ArrayList<DMatch>();

			Double max_dist = 0.0;
			Double min_dist = 100.0;

			for (int j = 0; j < matchesList.size(); j++) {
				Double dist = (double) matchesList.get(j).distance;

				if (dist < min_dist && dist != 0) {
					min_dist = dist;
				}

				if (dist > max_dist) {
					max_dist = dist;
				}

			}

			if (min_dist > 50) {
				System.out.println("No match found");

			}

			double threshold = 3 * min_dist;
			double threshold2 = 2 * min_dist;

			if (threshold > 75) {
				threshold = 75;
			} else if (threshold2 >= max_dist) {
				threshold = min_dist * 1.1;
			} else if (threshold >= max_dist) {
				threshold = threshold2 * 1.4;
			}

			System.out.println("Threshold : " + threshold);

			for (int i = 0; i < matchesList.size(); i++) {
				Double dist = (double) matchesList.get(i).distance;

				if (dist < threshold) {
					bestMatches.add(matches.toList().get(i));

				}
			}

			matchesFiltered.fromList(bestMatches);

			System.out.println("matchesFiltered.size() : " + matchesFiltered.size());

			if (matchesFiltered.rows() >= 1) {
				System.out.println("match found");
				match = 1;
				/*
				 * ExtentUtility.getTest().log(LogStatus.PASS, " Result =  Match Is Found ",
				 * ExtentUtility.getTest().addScreenCapture(takeScreenShot(appiumDriver)));
				 */
				break;

			} else {
				System.out.println("no match found");

			}

		}
		if (match == 0) {
			/*
			 * ExtentUtility.getTest().log(LogStatus.FAIL, " Result = No Match Is Found ",
			 * ExtentUtility.getTest().addScreenCapture(takeScreenShot(appiumDriver)));
			 */
			l++;
		}

	}

	/*
	 * public void AppDetect_VideoPlayDesktop(org.openqa.selenium.Point location,
	 * Dimension size) throws IOException, InterruptedException { int ret = 0; for
	 * (int i = 0; i < 4; i++) { // ret=0; String filename = "Errors/error.png";
	 * File file1=null; switch (toolName) {
	 * 
	 * case "Appium": file1 = (File) ((TakesScreenshot)
	 * appiumDriver).getScreenshotAs(OutputType.FILE); break; case "Selenium": file1
	 * = (File) ((TakesScreenshot) remoteDriver).getScreenshotAs(OutputType.FILE);
	 * break; } FileUtils.copyFile(file1, new File(filename)); Mat img1 =
	 * Imgcodecs.imread(filename, Imgcodecs.CV_LOAD_IMAGE_COLOR);
	 * 
	 * double left = location.x; double top = location.y; double bottom = location.y
	 * + size.getHeight(); double right = location.x + size.getWidth();
	 * System.out.println("left"+left+" top"+top+" right"+right+" bottom"+bottom);
	 * //Rect faceDetections = new Rect((int) left+50, (int) top+50 , (int) right,
	 * (int) bottom ); Rect faceDetections = new Rect(124,305,534,300); Mat img =
	 * new Mat(img1, faceDetections); Imgcodecs.imwrite("Errors/error1.png", img);
	 * ret = checkmotion(img); } if (ret == 0) {
	 * System.out.println("Video is not playing");
	 * ExtentUtility.getTest().log(LogStatus.FAIL,
	 * " Result = Video is not Playing - FALSE ",
	 * ExtentUtility.getTest().addScreenCapture(takeScreenShot())); } else {
	 * System.out.println("Video is playing");
	 * ExtentUtility.getTest().log(LogStatus.PASS, "  Video is  Playing  ",
	 * ExtentUtility.getTest().addScreenCapture(takeScreenShot())); } image = null;
	 * image1 = null; image2 = null; }
	 */
	public void AppDetect_VideoPlayDesktop(org.openqa.selenium.Point location, Dimension size)
			throws IOException, InterruptedException {
		int ret = 0;
		for (int i = 0; i < 7; i++) {
			// ret=0;
			String filename = "Errors/error.png";
			File file1 = null;
			switch (toolName) {

			case "Appium":
				file1 = (File) ((TakesScreenshot) appiumDriver).getScreenshotAs(OutputType.FILE);
				break;
			case "Selenium":
				file1 = (File) ((TakesScreenshot) remoteDriver).getScreenshotAs(OutputType.FILE);
				break;
			}
			FileUtils.copyFile(file1, new File(filename));
			Mat img1 = Imgcodecs.imread(filename, Imgcodecs.CV_LOAD_IMAGE_COLOR);

			double left = location.x;
			double top = location.y;
			double bottom = location.y + size.getHeight();
			double right = location.x + size.getWidth();
//			System.out.println("left"+left+" top"+top+" right"+right+" bottom"+bottom);
			// Rect faceDetections = new Rect((int) left+50, (int) top+50 , (int) right,
			// (int) bottom );
			// Rect faceDetections = new Rect(124,305,534,300);
//			Rect faceDetections = new Rect(140,350,196,170);
			Rect faceDetections = new Rect(480, 350, 196, 170);
			Mat img = new Mat(img1, faceDetections);
			Imgcodecs.imwrite("Errors/error5.png", img);
			ret = checkmotion(img);
		}
		if (ret == 0) {
			System.out.println("Video is not playing");
			ExtentUtility.getTest().log(LogStatus.FAIL, " Verify the Video motion when played - Video is not Playing",
					ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
		} else {
			System.out.println("Video is playing");
			ExtentUtility.getTest().log(LogStatus.PASS, "Verify the Video motion when played - Video is Playing",
					ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
		}
		image = null;
		image1 = null;
		image2 = null;
	}

	/*
	 * public void AppDetect_VideoPause_Desktop(org.openqa.selenium.Point location,
	 * Dimension size) throws IOException, InterruptedException { int ret = 0; for
	 * (int i = 0; i < 6; i++) { // ret=0; String filename = "Errors/error.png";
	 * File file1=null; switch (toolName) {
	 * 
	 * case "Appium": file1 = (File) ((TakesScreenshot)
	 * appiumDriver).getScreenshotAs(OutputType.FILE); break; case "Selenium": file1
	 * = (File) ((TakesScreenshot) remoteDriver).getScreenshotAs(OutputType.FILE);
	 * break; } FileUtils.copyFile(file1, new File(filename)); Mat img1 =
	 * Imgcodecs.imread(filename, Imgcodecs.CV_LOAD_IMAGE_COLOR); double left =
	 * location.x; double top = location.y; double bottom = location.y +
	 * size.getHeight(); double right = location.x + size.getWidth();
	 * 
	 * 
	 * //Rect faceDetections = new Rect((int) left+50, (int) top+50 , (int) right,
	 * (int) bottom); Rect faceDetections = new Rect(124,305,534,200); Mat img = new
	 * Mat(img1, faceDetections); Imgcodecs.imwrite("Errors/error2.png", img); ret =
	 * checkmotion(img); } if (ret == 0) {
	 * System.out.println("Video is not playing");
	 * ExtentUtility.getTest().log(LogStatus.PASS,
	 * " Video is not Playing After clicking on the Pause button",
	 * ExtentUtility.getTest().addScreenCapture(takeScreenShot())); } else {
	 * System.out.println("Video is playing");
	 * ExtentUtility.getTest().log(LogStatus.FAIL,
	 * "  Video is  Playing After clicking on the Pause button ",
	 * ExtentUtility.getTest().addScreenCapture(takeScreenShot())); } image = null;
	 * image1 = null; image2 = null; }
	 */
	public void AppDetect_VideoPause_Desktop(org.openqa.selenium.Point location, Dimension size)
			throws IOException, InterruptedException {
		int ret = 0;
		for (int i = 0; i < 6; i++) {
			// ret=0;
			String filename = "Errors/error.png";
			File file1 = null;
			switch (toolName) {

			case "Appium":
				file1 = (File) ((TakesScreenshot) appiumDriver).getScreenshotAs(OutputType.FILE);
				break;
			case "Selenium":
				file1 = (File) ((TakesScreenshot) remoteDriver).getScreenshotAs(OutputType.FILE);
				break;
			}
			FileUtils.copyFile(file1, new File(filename));
			Mat img1 = Imgcodecs.imread(filename, Imgcodecs.CV_LOAD_IMAGE_COLOR);
			double left = location.x;
			double top = location.y;
			double bottom = location.y + size.getHeight() - 100;
			double right = location.x + size.getWidth() - 130;

			// Rect faceDetections = new Rect(124,305,534,300);
			Rect faceDetections = new Rect(480, 350, 196, 170);
			// Rect faceDetections = new Rect((int) left, (int) top , (int) right, (int)
			// bottom);
			Mat img = new Mat(img1, faceDetections);

			ret = checkmotion(img);
		}
		if (ret == 0) {
			System.out.println("Video is not playing");
			ExtentUtility.getTest().log(LogStatus.PASS,
					" Verify the Video motion when paused - Video is not Playing After clicking on the Pause button",
					ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
		} else {
			System.out.println("Video is playing");
			ExtentUtility.getTest().log(LogStatus.FAIL,
					" Verify the Video motion when paused -  Video is  Playing After clicking on the Pause button ",
					ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
		}
		image = null;
		image1 = null;
		image2 = null;
	}

	public void AppDetect_VideoPause_Mobile(org.openqa.selenium.Point location, Dimension size)
			throws IOException, InterruptedException {
		int ret = 0;
		for (int i = 0; i < 6; i++) {
			// ret=0;
			String filename = "Errors/pause.png";
			File file1 = null;
			switch (toolName) {

			case "Appium":
				file1 = (File) ((TakesScreenshot) appiumDriver).getScreenshotAs(OutputType.FILE);
				break;
			case "Selenium":
				file1 = (File) ((TakesScreenshot) remoteDriver).getScreenshotAs(OutputType.FILE);
				break;
			}
			FileUtils.copyFile(file1, new File(filename));
			Mat img1 = Imgcodecs.imread(filename, Imgcodecs.CV_LOAD_IMAGE_COLOR);
			double left = location.x;
			double top = location.y;
			double bottom = location.y + size.getHeight() - 100;
			double right = location.x + size.getWidth() - 130;

			Rect faceDetections = new Rect((int) left, (int) top, (int) right, (int) bottom);
			Mat img = new Mat(img1, faceDetections);
			Imgcodecs.imwrite("Errors/pause.png", img);
			ret = checkmotion(img);

			// Rect faceDetections = new Rect((int) left, (int) top , (int) right, (int)
			// bottom);
			// Mat img = new Mat(img1, faceDetections);
			// ret = checkmotion(img);

		}
		if (ret == 0) {
			System.out.println("Video is not playing");
			ExtentUtility.getTest().log(LogStatus.PASS, " Video is not Playing After clicking on the Pause button",
					ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
		} else {
			System.out.println("Video is playing");
			ExtentUtility.getTest().log(LogStatus.FAIL, "  Video is  Playing After clicking on the Pause button ",
					ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
		}
		image = null;
		image1 = null;
		image2 = null;
	}

	public ArrayList<String> getimageloc(Mat templ) throws IOException {
		int match = 0;
		ArrayList<String> list = new ArrayList();

		org.opencv.core.Point loc = null;
		for (int k = 0; k < 20; k++) {
			String filename = "Errors/error1.png";
			File file1 = null;
			switch (toolName) {

			case "Appium":
				file1 = (File) ((TakesScreenshot) appiumDriver).getScreenshotAs(OutputType.FILE);
				break;
			case "Selenium":
				file1 = (File) ((TakesScreenshot) remoteDriver).getScreenshotAs(OutputType.FILE);
				break;
			}
			FileUtils.copyFile(file1, new File(filename));
			Mat img1 = Imgcodecs.imread(filename, Imgcodecs.CV_LOAD_IMAGE_COLOR);

			CascadeClassifier faceDetector = new CascadeClassifier();

			System.out.println("Running Template Matching");

			/*
			 * double left = location.x; double top = location.y; double bottom = location.y
			 * + size.getHeight(); double right = location.x + size.getWidth();
			 */

			int result_cols = img1.cols() - templ.cols() + 1;
			int result_rows = img1.rows() - templ.rows() + 1;
			Mat result = new Mat(result_rows, result_cols, CvType.CV_32FC1);

			Imgcodecs.imwrite("Errors/templatewe" + k + ".png", img1);
			String match_method = "Imgproc.TM_CCOEFF_NORMED";

			Imgproc.matchTemplate(img1, templ, result, Imgproc.TM_CCOEFF_NORMED);

			Imgproc.threshold(result, result, 0.5, 1.0, Imgproc.THRESH_TOZERO);

			MinMaxLocResult mmr = Core.minMaxLoc(result);

			org.opencv.core.Point matchLoc;
			if (match_method == "Imgproc.TM_SQDIFF" || match_method == "Imgproc.TM_SQDIFF_NORMED") {
				matchLoc = mmr.minLoc;
			} else {
				matchLoc = mmr.maxLoc;
			}
			System.out.println(matchLoc);
			System.out.println(mmr.maxVal);
			double threashhold = 0.70;
			if (mmr.maxVal > threashhold) {
				System.out.println(mmr.maxVal);
				System.out.println("match Found");
				loc = mmr.maxLoc;
				list = getPoints(loc);
				break;

			}

		}
		return list;
	}

	public boolean CheckImageinVideo(Mat templ, double d) throws IOException {

		boolean status = false;
		org.opencv.core.Point loc = null;
		for (int k = 0; k < d * 10; k++) {
			String filename = "Errors/error1.png";

			File file1 = null;
			switch (toolName) {

			case "Appium":
				file1 = (File) ((TakesScreenshot) appiumDriver).getScreenshotAs(OutputType.FILE);
				break;
			case "Selenium":
				file1 = (File) ((TakesScreenshot) remoteDriver).getScreenshotAs(OutputType.FILE);
				break;
			}
			FileUtils.copyFile(file1, new File(filename));
			Mat img1 = Imgcodecs.imread(filename, Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE);

			// System.out.println("Running Template Matching");

			int result_cols = img1.cols() - templ.cols() + 1;
			int result_rows = img1.rows() - templ.rows() + 1;
			Mat result = new Mat(result_rows, result_cols, CvType.CV_32FC1);

			// Imgcodecs.imwrite("Errors/templatewe"+k+".png", img1);
			String match_method = "Imgproc.TM_CCOEFF_NORMED";

			Imgproc.matchTemplate(img1, templ, result, Imgproc.TM_CCOEFF_NORMED);

			Imgproc.threshold(result, result, 0.5, 1.0, Imgproc.THRESH_TOZERO);

			MinMaxLocResult mmr = Core.minMaxLoc(result);

			org.opencv.core.Point matchLoc;
			if (match_method == "Imgproc.TM_SQDIFF" || match_method == "Imgproc.TM_SQDIFF_NORMED") {
				matchLoc = mmr.minLoc;
			} else {
				matchLoc = mmr.maxLoc;
			}
//			System.out.println(mmr.maxVal);
			double threashhold = 0.90;
			if (mmr.maxVal >= threashhold) {

				System.out.println("match Found");
				loc = mmr.maxLoc;
				status = true;
				break;

			}

		}
		return status;
	}

	public boolean CheckImageInPage(Mat templ) throws IOException {

		boolean status = false;
//			try{
		org.opencv.core.Point loc = null;
		String filename = "Errors/error1.png";

		File file1 = null;
		switch (toolName) {

		case "Appium":
			file1 = (File) ((TakesScreenshot) appiumDriver).getScreenshotAs(OutputType.FILE);
			break;
		case "Selenium":
			file1 = (File) ((TakesScreenshot) remoteDriver).getScreenshotAs(OutputType.FILE);
			break;
		}
		FileUtils.copyFile(file1, new File(filename));
		Mat img1 = Imgcodecs.imread(filename, Imgcodecs.CV_LOAD_IMAGE_COLOR);

		// System.out.println("Running Template Matching");

		int result_cols = img1.cols() - templ.cols() + 1;
		int result_rows = img1.rows() - templ.rows() + 1;
		Mat result = new Mat(result_rows, result_cols, CvType.CV_32FC1);

		// Imgcodecs.imwrite("Errors/templatewe"+k+".png", img1);
		String match_method = "Imgproc.TM_CCOEFF_NORMED";

		Imgproc.matchTemplate(img1, templ, result, Imgproc.TM_CCOEFF_NORMED);

		Imgproc.threshold(result, result, 0.5, 1.0, Imgproc.THRESH_TOZERO);

		MinMaxLocResult mmr = Core.minMaxLoc(result);

		org.opencv.core.Point matchLoc;
		if (match_method == "Imgproc.TM_SQDIFF" || match_method == "Imgproc.TM_SQDIFF_NORMED") {
			matchLoc = mmr.minLoc;
		} else {
			matchLoc = mmr.maxLoc;
		}
		// System.out.println(mmr.maxVal);
		double threashhold = 0.90;
		if (mmr.maxVal >= threashhold) {

			System.out.println("match Found");
			loc = mmr.maxLoc;
			status = true;
		}
//		}
//		catch(Exception e)
//		{
//			System.out.println(e.toString());
//		}
		return status;
	}

	public void CheckImageInPage_click(Mat templ, WebElement e) throws Exception {
		e.click();
		int a;
		int height = appiumDriver.manage().window().getSize().getHeight();
		if (height == 1536 || height == 2048) {
			a = 1;
		} else {
			a = 2;
			Thread.sleep(4000);
		}
		boolean status = false;
		org.opencv.core.Point loc = null;
		String filename = System.getProperty("user.dir") + "/ReportGenerator/" + ExtentUtility.reportFolder
				+ "/Screenshots/videoAd" + a + ".png";

		File file1 = null;
		switch (toolName) {

		case "Appium":
			file1 = (File) ((TakesScreenshot) appiumDriver).getScreenshotAs(OutputType.FILE);
			break;
		case "Selenium":
			file1 = (File) ((TakesScreenshot) remoteDriver).getScreenshotAs(OutputType.FILE);
			break;
		}
		FileUtils.copyFile(file1, new File(filename));
		Mat img1 = Imgcodecs.imread(filename, Imgcodecs.CV_LOAD_IMAGE_COLOR);

		// System.out.println("Running Template Matching");

		int result_cols = img1.cols() - templ.cols() + 1;
		int result_rows = img1.rows() - templ.rows() + 1;
		Mat result = new Mat(result_rows, result_cols, CvType.CV_32FC1);

//		Imgcodecs.imwrite("D:\\templatewe"+k+".png", img1);
		String match_method = "Imgproc.TM_CCOEFF_NORMED";

		Imgproc.matchTemplate(img1, templ, result, Imgproc.TM_CCOEFF_NORMED);

		Imgproc.threshold(result, result, 0.5, 1.0, Imgproc.THRESH_TOZERO);

		MinMaxLocResult mmr = Core.minMaxLoc(result);

		org.opencv.core.Point matchLoc;
		if (match_method == "Imgproc.TM_SQDIFF" || match_method == "Imgproc.TM_SQDIFF_NORMED") {
			matchLoc = mmr.minLoc;
		} else {
			matchLoc = mmr.maxLoc;
		}
		// System.out.println(mmr.maxVal);
		double threashhold = 0.90;
		if (mmr.maxVal >= threashhold) {

			System.out.println("match Found");
			loc = mmr.maxLoc;
			status = true;
		}
		if (status) {
			ExtentUtility.getTest().log(LogStatus.INFO,
					"Verify Video Sponsor Ad - Video Sponsor Ad is displayed in the video",
					ExtentUtility.getTest().addScreenCapture(filename));
		} else {
			ExtentUtility.getTest().log(LogStatus.INFO,
					"Verify Video Sponsor Ad - Video Sponsor Ad is not displayed in the video",
					ExtentUtility.getTest().addScreenCapture(filename));
		}
	}

	public void CheckImageInPage_click1(Mat templ, WebElement e) throws Exception {
//		e.click();
		clickCoordinates(120, 450);
//		waitUntilAccessiblityID_InVisible("Video Loading", 1000);
		Thread.sleep(3000);
		boolean status = false;
		org.opencv.core.Point loc = null;
		String filename = System.getProperty("user.dir") + "/ReportGenerator/" + ExtentUtility.reportFolder
				+ "/Screenshots/videoAd.png";

		File file1 = null;
		switch (toolName) {

		case "Appium":
			file1 = (File) ((TakesScreenshot) appiumDriver).getScreenshotAs(OutputType.FILE);
			break;
		case "Selenium":
			file1 = (File) ((TakesScreenshot) remoteDriver).getScreenshotAs(OutputType.FILE);
			break;
		}
		FileUtils.copyFile(file1, new File(filename));
		Mat img1 = Imgcodecs.imread(filename, Imgcodecs.CV_LOAD_IMAGE_COLOR);

		// System.out.println("Running Template Matching");

		int result_cols = img1.cols() - templ.cols() + 1;
		int result_rows = img1.rows() - templ.rows() + 1;
		Mat result = new Mat(result_rows, result_cols, CvType.CV_32FC1);

//		Imgcodecs.imwrite("D:\\templatewe"+k+".png", img1);
		String match_method = "Imgproc.TM_CCOEFF_NORMED";

		Imgproc.matchTemplate(img1, templ, result, Imgproc.TM_CCOEFF_NORMED);

		Imgproc.threshold(result, result, 0.5, 1.0, Imgproc.THRESH_TOZERO);

		MinMaxLocResult mmr = Core.minMaxLoc(result);

		org.opencv.core.Point matchLoc;
		if (match_method == "Imgproc.TM_SQDIFF" || match_method == "Imgproc.TM_SQDIFF_NORMED") {
			matchLoc = mmr.minLoc;
		} else {
			matchLoc = mmr.maxLoc;
		}
		// System.out.println(mmr.maxVal);
		double threashhold = 0.90;
		if (mmr.maxVal >= threashhold) {

			System.out.println("match Found");
			loc = mmr.maxLoc;
			status = true;
		}
		if (status) {
			ExtentUtility.getTest().log(LogStatus.INFO,
					"Verify Video Sponsor Ad - Video Sponsor Ad is displayed in the video",
					ExtentUtility.getTest().addScreenCapture(filename));
		} else {
			ExtentUtility.getTest().log(LogStatus.INFO,
					"Verify Video Sponsor Ad - Video Sponsor Ad is not displayed in the video",
					ExtentUtility.getTest().addScreenCapture(filename));
		}
	}

	public void CheckBlackScreen_mobile(Mat templ) throws Exception {
		boolean status = false;
		org.opencv.core.Point loc = null;
		String filename = System.getProperty("user.dir") + "/ReportGenerator/" + ExtentUtility.reportFolder
				+ "/Screenshots/blackScreenImage.png";

		File file1 = null;
		switch (toolName) {

		case "Appium":
			file1 = (File) ((TakesScreenshot) appiumDriver).getScreenshotAs(OutputType.FILE);
			break;
		case "Selenium":
			file1 = (File) ((TakesScreenshot) remoteDriver).getScreenshotAs(OutputType.FILE);
			break;
		}
		FileUtils.copyFile(file1, new File(filename));
		Mat img1 = Imgcodecs.imread(filename, Imgcodecs.CV_LOAD_IMAGE_COLOR);

		// System.out.println("Running Template Matching");

		int result_cols = img1.cols() - templ.cols() + 1;
		int result_rows = img1.rows() - templ.rows() + 1;
		Mat result = new Mat(result_rows, result_cols, CvType.CV_32FC1);

//		Imgcodecs.imwrite("D:\\templatewe"+k+".png", img1);
		String match_method = "Imgproc.TM_CCOEFF_NORMED";

		Imgproc.matchTemplate(img1, templ, result, Imgproc.TM_CCOEFF_NORMED);

		Imgproc.threshold(result, result, 0.5, 1.0, Imgproc.THRESH_TOZERO);

		MinMaxLocResult mmr = Core.minMaxLoc(result);

		org.opencv.core.Point matchLoc;
		if (match_method == "Imgproc.TM_SQDIFF" || match_method == "Imgproc.TM_SQDIFF_NORMED") {
			matchLoc = mmr.minLoc;
		} else {
			matchLoc = mmr.maxLoc;
		}
		// System.out.println(mmr.maxVal);
		double threashhold = 0.90;
		if (mmr.maxVal >= threashhold) {

			System.out.println("match Found");
			loc = mmr.maxLoc;
			status = true;
		}
		if (status) {
			ExtentUtility.getTest().log(LogStatus.INFO, "Verify Black Screen - Black Screen is displayed",
					ExtentUtility.getTest().addScreenCapture(filename));
		} else {
			ExtentUtility.getTest().log(LogStatus.INFO, "Verify Black Screen - Black Screen is not displayed",
					ExtentUtility.getTest().addScreenCapture(filename));
		}
	}

	public String extract_Text(org.openqa.selenium.Point location, Dimension size)
			throws IOException, TesseractException {
		StringBuffer result = new StringBuffer();
		for (int k = 0; k < 3; k++) {
			String filename = "Errors/error1.png";
			File file1 = null;
			switch (toolName) {

			case "Appium":
				file1 = (File) ((TakesScreenshot) appiumDriver).getScreenshotAs(OutputType.FILE);
				break;
			case "Selenium":
				file1 = (File) ((TakesScreenshot) remoteDriver).getScreenshotAs(OutputType.FILE);
				break;
			}

			FileUtils.copyFile(file1, new File(filename));
			Mat img1 = Imgcodecs.imread(filename, Imgcodecs.CV_LOAD_IMAGE_COLOR);

			double left = location.x;
			double top = location.y;
			double bottom = location.y + size.getHeight() + 500;
			double right = location.x + size.getWidth() + 700;

			Rect faceDetections = new Rect((int) left, (int) top, (int) right, (int) bottom);
			Mat img2 = new Mat(img1, faceDetections);

			System.out.println(img2.height() + img2.width());
			Mat imgGray = new Mat();
			Imgproc.cvtColor(img2, imgGray, Imgproc.COLOR_BGR2GRAY);

			Mat imgGaussianBlur = new Mat();
			Imgproc.GaussianBlur(imgGray, imgGaussianBlur, new Size(3, 3), 0);

			Mat imgAdaptiveThreshold = new Mat();

			Imgcodecs.imwrite("preprocess/adaptive_threshold.png", img2);

			File imageFile = new File("preprocess/adaptive_threshold.png");
			// System.out.println("tesseract");
			ITesseract instance = new Tesseract();
			instance.setLanguage("eng");

			result.append(instance.doOCR(imageFile));

		}

		String Text = result.toString();
		return Text;
	}
	/*
	 * public String extract_Text(org.openqa.selenium.Point location, Dimension
	 * size) throws IOException, TesseractException { StringBuffer result=new
	 * StringBuffer(); for (int k = 0; k <3; k++) { String filename =
	 * "Errors/error1.png"; File file1=null; switch (toolName) {
	 * 
	 * case "Appium": file1 = (File) ((TakesScreenshot)
	 * appiumDriver).getScreenshotAs(OutputType.FILE); break; case "Selenium": file1
	 * = (File) ((TakesScreenshot) remoteDriver).getScreenshotAs(OutputType.FILE);
	 * break; }
	 * 
	 * FileUtils.copyFile(file1, new File(filename)); Mat img1 =
	 * Imgcodecs.imread(filename, Imgcodecs.CV_LOAD_IMAGE_COLOR);
	 * 
	 * 
	 * double left = location.x; double top = location.y; double bottom = location.y
	 * + size.getHeight()+500; double right = location.x + size.getWidth()+700;
	 * 
	 * Rect faceDetections = new Rect((int) left, (int) top, (int) right , (int)
	 * bottom ); Mat img2 = new Mat(img1, faceDetections);
	 * 
	 * System.out.println(img2.height()+img2.width()); Mat imgGray = new Mat();
	 * Imgproc.cvtColor(img2, imgGray, Imgproc.COLOR_BGR2GRAY);
	 * 
	 * Mat imgGaussianBlur = new Mat(); Imgproc.GaussianBlur(imgGray,
	 * imgGaussianBlur, new Size(3, 3), 0);
	 * 
	 * Mat imgAdaptiveThreshold = new Mat();
	 * 
	 * Imgcodecs.imwrite("preprocess/adaptive_threshold.png", img2);
	 * System.out.println("runnew"); File imageFile = new
	 * File("preprocess/adaptive_threshold.png"); System.out.println("run");
	 * ITesseract instance = new Tesseract(); instance.setLanguage("eng");
	 * 
	 * result.append(instance.doOCR(imageFile)) ;
	 * 
	 * }
	 * 
	 * String Text=result.toString(); return Text; }
	 */

	public void clickCoordinates(final int x, final int y, String element) throws IOException {
		try {
			switch (toolName) {
			case "Appium":
				appiumDriver.executeScript("mobile: tap", new HashMap<String, Integer>() {
					{
						put("tapCount", (int) 1);
						put("touchCount", (int) 1);
						put("duration", (int) 0.5);
						put("x", x);
						put("y", y);
					}
				});
				break;
			case "Selenium":
				remoteDriver.executeScript("mobile: tap", new HashMap<String, Integer>() {
					{
						put("tapCount", (int) 1);
						put("touchCount", (int) 1);
						put("duration", (int) 0.5);
						put("x", x);
						put("y", y);
					}
				});
				break;
			}
			ExtentUtility.getTest().log(LogStatus.PASS, "Clicked successfully on " + element,
					ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
		} catch (Exception e) {
			ExtentUtility.getTest().log(LogStatus.FAIL, "Exception on clciking on element" + element,
					ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
		}

	}

	public String extract_Text_with_coordinates(int left, int top, int bottom, int right, int timeout)
			throws IOException, TesseractException {
		StringBuffer result = new StringBuffer();
		for (int k = 0; k < 1 * timeout; k++) {
			String filename = "preprocess/subtitle.png";
			// File file1=null;
			File file1 = new File("preprocess/subtitle.png");
			switch (toolName) {

			case "Appium":
				file1 = (File) ((TakesScreenshot) appiumDriver).getScreenshotAs(OutputType.FILE);
				break;
			case "Selenium":
				file1 = (File) ((TakesScreenshot) remoteDriver).getScreenshotAs(OutputType.FILE);
				break;
			}

			FileUtils.copyFile(file1, new File(filename));
			Mat img1 = Imgcodecs.imread(filename, Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE);

			Rect faceDetections = new Rect((int) left, (int) top, (int) bottom, (int) right);
			Mat img2 = new Mat(img1, faceDetections);

			Mat imgGaussianBlur = new Mat();
			Imgproc.GaussianBlur(img2, imgGaussianBlur, new Size(3, 3), 0);

			Imgcodecs.imwrite("preprocess/adaptive_threshold.png", imgGaussianBlur);

			File imageFile = new File("preprocess/adaptive_threshold.png");
			Tesseract instance = new Tesseract();
			instance.setLanguage("eng");

			result.append(instance.doOCR(imageFile));
		}

		String Text = result.toString();
		return Text;
	}

	public String extract_Text_Desktop(org.openqa.selenium.Point location, Dimension size)
			throws IOException, TesseractException {
		StringBuffer result = new StringBuffer();
		int ret = 0;
		for (int k = 0; k < 3; k++)// seconds
		{
			String filename = "Errors/subtitles.png";
			File file1 = null;
			switch (toolName) {

			case "Appium":
				file1 = (File) ((TakesScreenshot) appiumDriver).getScreenshotAs(OutputType.FILE);
				break;
			case "Selenium":
				file1 = (File) ((TakesScreenshot) remoteDriver).getScreenshotAs(OutputType.FILE);
				break;
			}

			FileUtils.copyFile(file1, new File(filename));
			Mat img1 = Imgcodecs.imread(filename, Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE);

			double left = location.x + 100;
			double top = location.y + 100;
			double bottom = location.y + size.getHeight() - 50;
			double right = location.x + size.getWidth() - 130;
			Rect faceDetections = new Rect((int) left, (int) top, (int) right, (int) bottom);
			// Rect faceDetections = new Rect((int) left, (int) top, (int) right , (int)
			// bottom );
			Mat img2 = new Mat(img1, faceDetections);
			Imgcodecs.imwrite("Errors/error.png", img2);
			// ret = checkmotion(img2);

			Mat imgGaussianBlur = new Mat();
			Imgproc.GaussianBlur(img2, imgGaussianBlur, new Size(3, 3), 0);

			Imgcodecs.imwrite("preprocess/subtitle.png", imgGaussianBlur);

			File imageFile = new File("preprocess/subtitle.png");
			// System.out.println("tesseract");
			ITesseract instance = new Tesseract();
			instance.setLanguage("eng");

			try {
				System.out.println(instance.doOCR(imageFile));
				result.append(instance.doOCR(imageFile));
				System.out.println("Result appended");
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}

		String Text = result.toString();
		return Text;
	}
	public boolean isElementEnabled(String xpath,String element)throws Exception 
	{	
		boolean result = false ;
		System.out.println("Verify is Element Enabled : "+element);
		appiumDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		try 
		{
			return result= appiumDriver.findElementByXPath(xpath).isEnabled();
		}catch (Exception e){}
		return result;
	}
	public boolean isElementDisplayed(int sec,String xpath,String element)throws Exception 
	{	
		boolean result = false ;
		System.out.println("Verify is Element Displayed : "+element);
		appiumDriver.manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);
		try 
		{
			return result= appiumDriver.findElementByXPath(xpath).isEnabled();
		}catch (Exception e){}
		return result;
	}
	public boolean isElementSelected(String xpath,String element)throws Exception 
	{	
		boolean result = false ;
		System.out.println("Verify is Element Selected : "+element);
		try 
		{
			return result= appiumDriver.findElementByXPath(xpath).isSelected();
		}catch (Exception e){}
		return result;
	}
	public boolean CheckImageinVideo(Mat templ, double d, org.openqa.selenium.Point location, Dimension size)
			throws IOException {

		ArrayList<String> list = new ArrayList();
		boolean status = false;
		org.opencv.core.Point loc = null;
		for (int k = 0; k < d * 10; k++) {
			String filename = "Errors/errorss.png";

			File file1 = null;
			switch (toolName) {

			case "Appium":
				file1 = (File) ((TakesScreenshot) appiumDriver).getScreenshotAs(OutputType.FILE);
				break;
			case "Selenium":
				file1 = (File) ((TakesScreenshot) remoteDriver).getScreenshotAs(OutputType.FILE);
				break;
			}
			FileUtils.copyFile(file1, new File(filename));
			Mat img1 = Imgcodecs.imread(filename, Imgcodecs.CV_LOAD_IMAGE_COLOR);

			System.out.println("Running Template Matching");
			double left = location.x;
			double top = location.y;
			double bottom = location.y + size.getHeight() - 100;
			double right = location.x + size.getWidth() - 130;
			// System.out.println("left"+left+" top"+top+" right"+right+" bottom"+bottom);
			Rect faceDetections = new Rect((int) left, (int) top, (int) right, (int) bottom);
			img1 = new Mat(img1, faceDetections);
			// Imgcodecs.imwrite("Errors/error"+k+".png", img1);

			int result_cols = img1.cols() - templ.cols() + 1;
			int result_rows = img1.rows() - templ.rows() + 1;
			Mat result = new Mat(result_rows, result_cols, CvType.CV_32FC1);

			// Imgcodecs.imwrite("Errors/templatewe"+k+".png", img1);
			String match_method = "Imgproc.TM_CCOEFF_NORMED";

			Imgproc.matchTemplate(img1, templ, result, Imgproc.TM_CCOEFF_NORMED);

			Imgproc.threshold(result, result, 0.5, 1.0, Imgproc.THRESH_TOZERO);

			MinMaxLocResult mmr = Core.minMaxLoc(result);

			org.opencv.core.Point matchLoc;
			if (match_method == "Imgproc.TM_SQDIFF" || match_method == "Imgproc.TM_SQDIFF_NORMED") {
				matchLoc = mmr.minLoc;
			} else {
				matchLoc = mmr.maxLoc;
			}

			// System.out.println(mmr.maxVal);
			double threashhold = 0.8;
			if (mmr.maxVal > threashhold) {
				// System.out.println(mmr.maxVal);
				// System.out.println("match Found");
				loc = mmr.maxLoc;
				list = getPoints(loc);
				status = true;
				break;

			}

		}
		if (status) {
			ExtentUtility.getTest().log(LogStatus.INFO, " Video is Buffering ",
					ExtentUtility.getTest().addScreenCapture(takeScreenShot(remoteDriver)));
		} else {
			System.out.println(" No match Found");
			ExtentUtility.getTest().log(LogStatus.INFO, "No Buffering of Video",
					ExtentUtility.getTest().addScreenCapture(takeScreenShot(remoteDriver)));
		}
		return status;
	}

	public void dragAndDropThumb(WebElement e1, int n) throws Exception {

		try {
			switch (toolName) {
			case "Appium":
				Actions action = new Actions(appiumDriver);
				action.dragAndDropBy(e1, n, 0).perform();

				break;
			case "Selenium":
				Actions action1 = new Actions(remoteDriver);
				action1.dragAndDropBy(e1, n, 0).perform();

				break;
			}

		} catch (Exception exc) {
			exc.printStackTrace();

			ExtentUtility.getTest().log(LogStatus.FAIL, exc + "Exception on Drag element",
					exc.toString() + ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
			throw new Exception(exc);

		}
	}

	public void dragAndDropSeekBar(WebElement element, double i, String fw_rw) throws Exception {
		try {
			// int xAxisStartPoint = element.getLocation().getX();
			// int xAxisEndPoint = xAxisStartPoint + element.getSize().getWidth();
			// int yAxis = element.getLocation().getY();
			// TouchAction act=new TouchAction(appiumDriver);
			// //pressed x axis & y axis of seekbar and move seekbar till the end
			// act.longPress(xAxisStartPoint,yAxis).moveTo(xAxisEndPoint-1,yAxis).release().perform();

			// Locating seekbar using resource id
			// WebElement seek_bar=driver.findElement(By.id("seek_bar"));
			// get start co-ordinate of seekbar
			int start = element.getLocation().getX();
			// Get width of seekbar
			int end = element.getSize().getWidth();
			// get location of seekbar vertically
			int y = element.getLocation().getY();

			// Select till which position you want to move the seekbar
			TouchAction action = new TouchAction(appiumDriver);

			// Move it will the end
			// action.longPress(start,y).moveTo(end,y).release().perform();

			// Move it 40%
//			int moveTo=(int)(end*i);
			if (fw_rw.equalsIgnoreCase("fw")) {
				int moveTo = (int) (end * i);
				// action.longPress(start,y).moveTo(moveTo,y).release().perform();
			} else {
				int moveTo = (int) (end * i);
				// action.longPress(moveTo,y).moveTo(start,y).release().perform();
			}
		} catch (Exception e) {

		}
	}
	/*
	 * public String extract_Text_Desktop(org.openqa.selenium.Point location,
	 * Dimension size) throws IOException, TesseractException { StringBuffer
	 * result=new StringBuffer(); for (int k = 0; k <4; k++) { String filename =
	 * "Errors/error1.png"; File file1=null; switch (toolName) {
	 * 
	 * case "Appium": file1 = (File) ((TakesScreenshot)
	 * appiumDriver).getScreenshotAs(OutputType.FILE); break; case "Selenium": file1
	 * = (File) ((TakesScreenshot) remoteDriver).getScreenshotAs(OutputType.FILE);
	 * break; }
	 * 
	 * FileUtils.copyFile(file1, new File(filename)); Mat img1 =
	 * Imgcodecs.imread(filename, Imgcodecs.CV_LOAD_IMAGE_COLOR);
	 * 
	 * double left = location.x; double top = location.y; double bottom = location.y
	 * + size.getHeight()-100; double right = location.x + size.getWidth()-130;
	 * 
	 * Rect faceDetections = new Rect((int) left+100, (int) top+150, (int) right ,
	 * (int) bottom ); Mat img2 = new Mat(img1, faceDetections);
	 * 
	 * Mat imgGray = new Mat(); Imgproc.cvtColor(img2, imgGray,
	 * Imgproc.COLOR_BGR2GRAY);
	 * 
	 * Mat imgGaussianBlur = new Mat(); Imgproc.GaussianBlur(imgGray,
	 * imgGaussianBlur, new Size(3, 3), 0);
	 * 
	 * 
	 * Imgcodecs.imwrite("preprocess/adaptive_threshold.png", img2);
	 * 
	 * File imageFile = new File("preprocess/adaptive_threshold.png");
	 * System.out.println("===================="); ITesseract instance = new
	 * Tesseract(); instance.setLanguage("eng");
	 * 
	 * result.append(instance.doOCR(imageFile)) ;
	 * 
	 * }
	 * 
	 * String Text=result.toString(); return Text; }
	 */

	public int checkmotion(Mat img) {
		int retVal = 0;
		int mistakes = 0;
		if (image == null) {
			image = img;
			// System.out.println("1");
		} else if (image1 == null) {
			image1 = img;
			// System.out.println("2");
		} else if (image2 == null) {
			image2 = img;
			// System.out.println("3");
		}

		if ((image != null) && (image1 != null) && (image2 != null)) {
			// System.out.println("sub func");
			Mat t_minus = image;
			Mat t = image1;
			Mat t_plus = image2;
			Mat dst = new Mat();

			Mat dst1 = new Mat();
			// System.out.println("mat = " + dst);

			Core.absdiff(t_minus, t, dst);
			Core.absdiff(t, t_plus, dst1);
			Mat reshaped = dst.reshape(1);
			Mat reshaped1 = dst1.reshape(1);
			mistakes = Core.countNonZero(reshaped) + Core.countNonZero(reshaped1);
			reshaped.release();
			dst.release();
			dst1.release();
			// System.out.println("mistakes = " + mistakes);
		}
		return mistakes;
	}

	public void AppDetect_VideoPlay(org.openqa.selenium.Point location, Dimension size)
			throws IOException, InterruptedException {
		int ret = 0;
		for (int i = 0; i < 4; i++) {
			Thread.sleep(1000);
			// ret=0;
			String imageFolder;
			int height = appiumDriver.manage().window().getSize().getHeight();
			if (height == 1536 || height == 2048)
				imageFolder = "Error"; // tablet
			else
				imageFolder = "Error1"; // phone
			String filename = imageFolder + "/error.png";
			File file1 = null;
			switch (toolName) {

			case "Appium":
				file1 = (File) ((TakesScreenshot) appiumDriver).getScreenshotAs(OutputType.FILE);
				break;
			case "Selenium":
				file1 = (File) ((TakesScreenshot) remoteDriver).getScreenshotAs(OutputType.FILE);
				break;
			}
			FileUtils.copyFile(file1, new File(filename));
			Mat img1 = Imgcodecs.imread(filename, Imgcodecs.CV_LOAD_IMAGE_COLOR);
//			double left = location.x;
//			double top = location.y;
//			double bottom = location.y + size.getHeight();
//			double right = location.x + size.getWidth();
//			System.out.println("left"+left+" top"+top+" right"+right+" bottom"+bottom);
//			Rect faceDetections = new Rect((int) left, (int) top, (int) right, (int) bottom + 390);
			Rect faceDetections = new Rect(40, 200, 2000, 800);
			Mat img = new Mat(img1, faceDetections);
			Imgcodecs.imwrite(imageFolder + "/error3" + i + ".png", img);
			ret = checkmotion(img);
//			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>motion "+i+": "+ret);
		}
		if (ret == 0) {
			System.out.println("Verify the Video motion when playing - Video is not playing");
			ExtentUtility.getTest().log(LogStatus.FAIL, "Verify the Video motion when played - Video is not Playing",
					ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
		} else {
			System.out.println("Verify the Video motion when playing - Video is Playing in Full Screen");
			ExtentUtility.getTest().log(LogStatus.PASS,
					"Verify the Video motion when playing - Video is Playing in Full Screen",
					ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
		}
		image = null;
		image1 = null;
		image2 = null;
	}

	public void AppDetect_VideoPlay_iOS(Dimension size) throws IOException, InterruptedException {
		int ret = 0;
		for (int i = 0; i < 3; i++) {
			// ret=0;
			String filename = "Errors/error.png";
			File file1 = null;
			switch (toolName) {

			case "Appium":
				file1 = (File) ((TakesScreenshot) appiumDriver).getScreenshotAs(OutputType.FILE);
				break;
			case "Selenium":
				file1 = (File) ((TakesScreenshot) remoteDriver).getScreenshotAs(OutputType.FILE);
				break;
			}
			FileUtils.copyFile(file1, new File(filename));
			Mat img1 = Imgcodecs.imread(filename, Imgcodecs.CV_LOAD_IMAGE_COLOR);
//			double left = location.x;
//			double top = location.y;
//			double bottom = location.y + size.getHeight();
//			double right = location.x + size.getWidth();
			// System.out.println("left"+left+" top"+top+" right"+right+" bottom"+bottom);
			// Rect faceDetections = new Rect((int) left, (int) top, (int) right, (int)
			// bottom + 390);
			Rect faceDetections = new Rect(30, 30, 900, 1400);
			Mat img = new Mat(img1, faceDetections);
			Imgcodecs.imwrite("Errors/error3.png", img);
			ret = checkmotion(img);
		}
		if (ret == 0) {
			System.out.println("Verify the Video motion when playing - Video is not playing");
			ExtentUtility.getTest().log(LogStatus.FAIL, "Verify the Video motion when played - Video is not Playing",
					ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
		} else {
			System.out.println("Verify the Video motion when playing - Video is playing");
			ExtentUtility.getTest().log(LogStatus.PASS,
					"Verify the Video motion when played - Video is Playing in Full Screen",
					ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
		}
		image = null;
		image1 = null;
		image2 = null;
	}

	public void AppDetect_VideoPause(org.openqa.selenium.Point location, Dimension size)
			throws IOException, InterruptedException {
		int ret = 0;
		for (int i = 0; i < 4; i++) {
			Thread.sleep(1000);
			String imageFolder;
			int height = appiumDriver.manage().window().getSize().getHeight();
			if (height == 1536 || height == 2048)
				imageFolder = "Error"; // tablet
			else
				imageFolder = "Error1"; // phone
			String filename = imageFolder + "/error.png";
			File file1 = null;
			switch (toolName) {

			case "Appium":
				file1 = (File) ((TakesScreenshot) appiumDriver).getScreenshotAs(OutputType.FILE);
				break;
			case "Selenium":
				file1 = (File) ((TakesScreenshot) remoteDriver).getScreenshotAs(OutputType.FILE);
				break;
			}
			FileUtils.copyFile(file1, new File(filename));
			Mat img1 = Imgcodecs.imread(filename, Imgcodecs.CV_LOAD_IMAGE_COLOR);
//			double left = location.x;
//			double top = location.y;
//			double bottom = location.y + size.getHeight();
//			double right = location.x + size.getWidth();
//			System.out.println("left"+left+" top"+top+" right"+right+" bottom"+bottom);
//			Rect faceDetections = new Rect((int) left, (int) top, (int) right, (int) bottom );
			Rect faceDetections = new Rect(40, 200, 2000, 800);
			Mat img = new Mat(img1, faceDetections);
			Imgcodecs.imwrite(imageFolder + "/error2" + i + ".png", img);
			ret = checkmotion(img);
//			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>pause "+i+": "+ret);
		}
		if (ret == 0) {
			System.out
					.println("Verify the Video motion when paused - Video is not Playing After tapping the Pause icon");
			ExtentUtility.getTest().log(LogStatus.PASS,
					"Verify the Video motion when paused - Video is not Playing After tapping the Pause icon",
					ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
		} else {
			System.out.println("Verify the Video motion when paused - Video is Playing After tapping the Pause icon");
			ExtentUtility.getTest().log(LogStatus.FAIL,
					"Verify the Video motion when paused - Video is Playing After tapping the Pause icon",
					ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
		}
		image = null;
		image1 = null;
		image2 = null;
	}

	public void matchimage(AppiumDriver driver, Mat templ, org.openqa.selenium.Point location, Dimension size)
			throws IOException {
		try {
			int match = 0;
			for (int k = 0; k < 20; k++) {
				String filename = "Errors/error1.png";
				File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(src, new File(filename));
				Mat img1 = Imgcodecs.imread(filename, Imgcodecs.CV_LOAD_IMAGE_COLOR);

				double left = location.x;
				double top = location.y;
				double bottom = location.y + size.getHeight() - 60;
				double right = location.x + size.getWidth() - 140;
				CascadeClassifier faceDetector = new CascadeClassifier();

				System.out.println("Running Template Matching");

				int result_cols = img1.cols() - templ.cols() + 1;
				int result_rows = img1.rows() - templ.rows() + 1;
				Mat result = new Mat(result_rows, result_cols, CvType.CV_32FC1);

				String match_method = "Imgproc.TM_CCOEFF_NORMED";
				// / Do the Matching and Normalize
				Imgproc.matchTemplate(img1, templ, result, Imgproc.TM_CCOEFF_NORMED);

				Imgcodecs.imwrite("Errors/templatewe" + k + ".png", img1);
				Imgproc.threshold(result, result, 0.5, 1.0, Imgproc.THRESH_TOZERO);
				// / Localizing the best match with minMaxLoc
				MinMaxLocResult mmr = Core.minMaxLoc(result);

				org.opencv.core.Point matchLoc;
				if (match_method == "Imgproc.TM_SQDIFF" || match_method == "Imgproc.TM_SQDIFF_NORMED") {
					matchLoc = mmr.minLoc;
				} else {
					matchLoc = mmr.maxLoc;
				}
				System.out.println(mmr.minLoc);
				System.out.println(mmr.maxLoc);
				System.out.println(mmr.maxVal);
				double threashhold = 0.70;
				if (mmr.maxVal > threashhold) {
					System.out.println(mmr.maxVal);
					System.out.println("match Found");
					match++;
					break;

				}

			}
			if (match == 1) {
				ExtentUtility.getTest().log(LogStatus.PASS, " Result = Match Is Found ",
						ExtentUtility.getTest().addScreenCapture(takeScreenShot(driver)));
			} else {
				System.out.println(" NO match Found");
				ExtentUtility.getTest().log(LogStatus.FAIL, " Result = No Match Is Found ",
						ExtentUtility.getTest().addScreenCapture(takeScreenShot(driver)));
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public void AppDetect_floatingVideoPlay() throws IOException, InterruptedException {
		try {
			int ret = 0;
			for (int i = 0; i < 3; i++) {
				// ret=0;
				String imageFolder;
				int height = appiumDriver.manage().window().getSize().getHeight();
				if (height == 1536 || height == 2048)
					imageFolder = "Error"; // tablet
				else
					imageFolder = "Error1"; // phone
				String filename = imageFolder + "/error.png";
				File file1 = null;
				switch (toolName) {

				case "Appium":
					file1 = (File) ((TakesScreenshot) appiumDriver).getScreenshotAs(OutputType.FILE);
					break;
				case "Selenium":
					file1 = (File) ((TakesScreenshot) remoteDriver).getScreenshotAs(OutputType.FILE);
					break;
				}
				FileUtils.copyFile(file1, new File(filename));
				Mat img1 = Imgcodecs.imread(filename, Imgcodecs.CV_LOAD_IMAGE_COLOR);
				// Rect faceDetections = new Rect((int) left, (int) top, (int) right, (int)
				// bottom + 390);
				Rect faceDetections = null;
//				int height = appiumDriver.manage().window().getSize().getHeight();
				if (height == 1536 || height == 2048)
					faceDetections = new Rect(800, 1600, 600, 400); // tablet
				else
					faceDetections = new Rect(600, 1900, 600, 400); // phone
//				Rect faceDetections = new Rect(600,1900,600,400); //phone	
				Mat img = new Mat(img1, faceDetections);
				Imgcodecs.imwrite(imageFolder + "/error3.png", img);
				ret = checkmotion(img);
			}
			if (ret == 0) {
				System.out.println(
						"Verify the Video motion in Screen View Mode - Video is not playing in Screen View Mode");
				ExtentUtility.getTest().log(LogStatus.FAIL,
						"Verify the Video motion in Screen View Mode - Video is not playing in Screen View Mode",
						ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
			} else {
				System.out
						.println("Verify the Video motion in Screen View Mode - Video is playing in Screen View Mode");
				ExtentUtility.getTest().log(LogStatus.PASS,
						"Verify the Video motion in Screen View Mode - Video is playing in Screen View Mode",
						ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
			}
			image = null;
			image1 = null;
			image2 = null;

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void Appmatchimage(WebDriver driver, Mat templ, org.openqa.selenium.Point location, Dimension size)
			throws IOException {

		int match = 0;
		for (int k = 0; k < 5; k++) {
			String filename = "Errors/match2.png";
			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(src, new File(filename));
			Mat img1 = Imgcodecs.imread(filename, Imgcodecs.CV_LOAD_IMAGE_COLOR);

			CascadeClassifier faceDetector = new CascadeClassifier();
			System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
			System.out.println("Running Template Matching__");

			double left = location.x;
			double top = location.y;
			double bottom = location.y + size.getHeight() - 100;
			double right = location.x + size.getWidth() - 130;

			System.out.println("face detedffffff");
			// / Create the result matrix
			int result_cols = img1.cols() - templ.cols() + 1;
			int result_rows = img1.rows() - templ.rows() + 1;
			Mat result = new Mat(result_rows, result_cols, CvType.CV_32FC1);
			System.out.println("face deted");
			Rect faceDetections = new Rect((int) left, (int) top, (int) right, (int) bottom);
			Mat img2 = new Mat(img1, faceDetections);

			System.out.println("face deted");
			String match_method = "Imgproc.TM_CCOEFF_NORMED";
			// / Do the Matching and Normalize
			Imgproc.matchTemplate(img2, templ, result, Imgproc.TM_CCOEFF_NORMED);

			Imgproc.threshold(result, result, 0.5, 1.0, Imgproc.THRESH_TOZERO);
			// / Localizing the best match with minMaxLoc
			MinMaxLocResult mmr = Core.minMaxLoc(result);
			System.out.println("face deted111");
			org.opencv.core.Point matchLoc;
			if (match_method == "Imgproc.TM_SQDIFF" || match_method == "Imgproc.TM_SQDIFF_NORMED") {
				matchLoc = mmr.minLoc;
			} else {
				matchLoc = mmr.maxLoc;
			}
			System.out.println(matchLoc);
			System.out.println(mmr.maxVal);
			double threashhold = 0.70;
			if (mmr.maxVal > threashhold) {
				System.out.println(mmr.maxVal);
				System.out.println("match Found");
				match = 1;
				break;

			}

		}
		if (match == 1) {
			ExtentUtility.getTest().log(LogStatus.PASS, " Result = Match Is Found ",
					ExtentUtility.getTest().addScreenCapture(takeScreenShot(driver)));
		} else {
			System.out.println(" NO match Found");
			ExtentUtility.getTest().log(LogStatus.FAIL, " Result = No Match Is Found ",
					ExtentUtility.getTest().addScreenCapture(takeScreenShot(driver)));
		}
	}

	public void Appmatchimage(WebDriver driver, Mat templ, Mat temp2, org.openqa.selenium.Point location,
			Dimension size) throws IOException {

		int match = 0;
		for (int k = 0; k < 5; k++) {
			String filename = "Errors/match2.png";
			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(src, new File(filename));
			Mat img1 = Imgcodecs.imread(filename, Imgcodecs.CV_LOAD_IMAGE_COLOR);

			CascadeClassifier faceDetector = new CascadeClassifier();
			System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
			System.out.println("Running Template Matching__");

			double left = location.x;
			double top = location.y;
			double bottom = location.y + size.getHeight() - 100;
			double right = location.x + size.getWidth() - 130;

			System.out.println("face detedffffff");
			// / Create the result matrix
			int result_cols = img1.cols() - templ.cols() + 1;
			int result_rows = img1.rows() - templ.rows() + 1;
			Mat result = new Mat(result_rows, result_cols, CvType.CV_32FC1);
			System.out.println("face deted");
			Rect faceDetections = new Rect((int) left, (int) top, (int) right, (int) bottom);
			Mat img2 = new Mat(img1, faceDetections);

			System.out.println("face deted");
			String match_method = "Imgproc.TM_CCOEFF_NORMED";
			// / Do the Matching and Normalize
			Imgproc.matchTemplate(img2, templ, result, Imgproc.TM_CCOEFF_NORMED);

			Imgproc.threshold(result, result, 0.5, 1.0, Imgproc.THRESH_TOZERO);
			// / Localizing the best match with minMaxLoc
			MinMaxLocResult mmr = Core.minMaxLoc(result);
			System.out.println("face deted111");
			org.opencv.core.Point matchLoc;
			if (match_method == "Imgproc.TM_SQDIFF" || match_method == "Imgproc.TM_SQDIFF_NORMED") {
				matchLoc = mmr.minLoc;
			} else {
				matchLoc = mmr.maxLoc;
			}
			System.out.println(matchLoc);
			System.out.println(mmr.maxVal);
			double threashhold = 0.70;
			if (mmr.maxVal > threashhold) {
				System.out.println(mmr.maxVal);
				System.out.println("match Found");
				match = 1;
				break;

			}

		}
		if (match == 1) {
			ExtentUtility.getTest().log(LogStatus.PASS, " Result = Match Is Found ",
					ExtentUtility.getTest().addScreenCapture(takeScreenShot(driver)));
		} else {
			System.out.println(" NO match Found");
			ExtentUtility.getTest().log(LogStatus.FAIL, " Result = No Match Is Found ",
					ExtentUtility.getTest().addScreenCapture(takeScreenShot(driver)));
		}
	}

	public void BlackScreen_Match(org.openqa.selenium.Point location, Dimension size) throws IOException {
		String imageFolder;
		int imageNum;
		int height = appiumDriver.manage().window().getSize().getHeight();
		if (height == 1536 || height == 2048) {
			imageFolder = "images_tablet"; // tablet
			imageNum = 1;
		} else {
			imageFolder = "images_phone"; // phone
			imageNum = 2;
		}
		Mat templ = Imgcodecs.imread(imageFolder + "/blackScreen.png", Imgcodecs.CV_LOAD_IMAGE_COLOR);
		int match = 0;
		String filename = System.getProperty("user.dir") + "/ReportGenerator/" + ExtentUtility.reportFolder
				+ "/Screenshots/match" + imageNum + ".png";
		for (int k = 0; k < 3; k++) {
			File src = ((TakesScreenshot) appiumDriver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(src, new File(filename));
			Mat img1 = Imgcodecs.imread(filename, Imgcodecs.CV_LOAD_IMAGE_COLOR);

			CascadeClassifier faceDetector = new CascadeClassifier();
			System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
			System.out.println("Running Template Matching");

			double left = location.x;
			double top = location.y;
			double bottom = location.y + size.getHeight();
			double right = location.x + size.getWidth();
			Rect faceDetections = new Rect((int) left, (int) top, (int) right, (int) bottom);
			System.out.println("left" + left + " top" + top + " right" + right + " bottom" + bottom);
			Mat img2 = new Mat(img1, faceDetections);
			String imageFolder1;
			// int height = appiumDriver.manage().window().getSize().getHeight();
			if (height == 1536 || height == 2048)
				imageFolder1 = "Error"; // tablet
			else
				imageFolder1 = "Error1"; // phone
			Imgcodecs.imwrite(imageFolder1 + "/screen" + k + ".png", img2);
			// System.out.println("face detected");
			// / Create the result matrix
			int result_cols = img2.cols() - templ.cols() + 1;
			int result_rows = img2.rows() - templ.rows() + 1;
			Mat result = new Mat(result_rows, result_cols, CvType.CV_32FC1);
			// System.out.println("face detected1");
			String match_method = "Imgproc.TM_CCOEFF_NORMED";
			// / Do the Matching and Normalize
			Imgproc.matchTemplate(img2, templ, result, Imgproc.TM_CCOEFF_NORMED);

			Imgproc.threshold(result, result, 0.5, 1.0, Imgproc.THRESH_TOZERO);
			// / Localizing the best match with minMaxLoc
			MinMaxLocResult mmr = Core.minMaxLoc(result);
			// System.out.println("face deted111");
			org.opencv.core.Point matchLoc;
			if (match_method == "Imgproc.TM_SQDIFF" || match_method == "Imgproc.TM_SQDIFF_NORMED") {
				matchLoc = mmr.minLoc;
			} else {
				matchLoc = mmr.maxLoc;
			}
			// System.out.println(matchLoc);
			// System.out.println(mmr.maxVal);
			double threashhold = 0.70;
			if (mmr.maxVal > threashhold) {
				System.out.println(mmr.maxVal);
				System.out.println("BlackScreen Found");
				match = 1;
				break;

			}
		}
		if (match == 1) {
			ExtentUtility.getTest().log(LogStatus.INFO, "Verify Black Screen - Black Screen is displayed",
					ExtentUtility.getTest().addScreenCapture(filename));
		} else {
			System.out.println("No match Found");
			ExtentUtility.getTest().log(LogStatus.INFO, "Verify Black Screen - Black Screen is not displayed",
					ExtentUtility.getTest().addScreenCapture(filename));
		}
	}

	public void BlackScreen_Match_ios(org.openqa.selenium.Point location, Dimension size) throws IOException {
		Mat templ = Imgcodecs.imread("images_ios/blackScreen1.png", Imgcodecs.CV_LOAD_IMAGE_COLOR);
		int match = 0;
		String filename = System.getProperty("user.dir") + "/ReportGenerator/" + ExtentUtility.reportFolder
				+ "/Screenshots/match2.png";
		for (int k = 0; k < 3; k++) {
			File src = ((TakesScreenshot) appiumDriver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(src, new File(filename));
			Mat img1 = Imgcodecs.imread(filename, Imgcodecs.CV_LOAD_IMAGE_COLOR);

			CascadeClassifier faceDetector = new CascadeClassifier();
			System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
			System.out.println("Running Template Matching");

//			double left = location.x;
//			double top = location.y;
//			double bottom = location.y + size.getHeight();
//			double right = location.x + size.getWidth();
//			Rect faceDetections = new Rect((int) left, (int) top , (int) right, (int) bottom );
			Rect faceDetections = new Rect(30, 30, 900, 1400);
			Mat img2 = new Mat(img1, faceDetections);
			Imgcodecs.imwrite("Errors/screen" + k + ".png", img2);
//			System.out.println("face detected");
			// / Create the result matrix
			int result_cols = img2.cols() - templ.cols() + 1;
			int result_rows = img2.rows() - templ.rows() + 1;
			Mat result = new Mat(result_rows, result_cols, CvType.CV_32FC1);
//			System.out.println("face detected1");
			String match_method = "Imgproc.TM_CCOEFF_NORMED";
			// / Do the Matching and Normalize
			Imgproc.matchTemplate(img2, templ, result, Imgproc.TM_CCOEFF_NORMED);

			Imgproc.threshold(result, result, 0.5, 1.0, Imgproc.THRESH_TOZERO);
			// / Localizing the best match with minMaxLoc
			MinMaxLocResult mmr = Core.minMaxLoc(result);
//			System.out.println("face deted111");
			org.opencv.core.Point matchLoc;
			if (match_method == "Imgproc.TM_SQDIFF" || match_method == "Imgproc.TM_SQDIFF_NORMED") {
				matchLoc = mmr.minLoc;
			} else {
				matchLoc = mmr.maxLoc;
			}
//			System.out.println(matchLoc);
//			System.out.println(mmr.maxVal);
			double threashhold = 0.70;
			if (mmr.maxVal > threashhold) {
				System.out.println(mmr.maxVal);
				System.out.println("BlackScreen Found");
				match = 1;
				break;

			}
		}
		if (match == 1) {
			ExtentUtility.getTest().log(LogStatus.INFO, "Verify Black Screen - Black Screen is displayed",
					ExtentUtility.getTest().addScreenCapture(filename));
		} else {
			System.out.println("No match Found");
			ExtentUtility.getTest().log(LogStatus.INFO, "Verify Black Screen - Black Screen is not displayed",
					ExtentUtility.getTest().addScreenCapture(filename));
		}
	}

	public ArrayList<String> getPoints(org.opencv.core.Point loc2) {
		String point = "" + loc2;
		String[] split = point.split("}");
		System.out.println(split[0] + "--");
		String[] split1 = split[0].split("}");
		System.out.println(split1[0] + "--");
		String[] coords = split1[0].split(",");
		System.out.println(coords[0] + "---" + coords[1]);
		String x = coords[0].substring(1);
		String y = coords[1].replace(" ", "");
		System.out.println(x);
		System.out.println(y);
		ArrayList<String> list = new ArrayList<>();
		list.add(x);
		list.add(y);
		return list;
	}

	public void getScreenShot(AppiumDriver driver) throws IOException {
		String filename = "Errors/error1.png";

		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(filename));
		Mat img1 = Imgcodecs.imread(filename, Imgcodecs.CV_LOAD_IMAGE_COLOR);

	}

	public void Detect_VideoPlay(WebDriver driver, org.openqa.selenium.Point location, Dimension size)
			throws IOException, InterruptedException {
		int ret = 0;
		for (int i = 0; i < 6; i++) {
			// ret=0;
			String filename = "Errors/error.png";
			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(src, new File(filename));
			Mat img1 = Imgcodecs.imread(filename, Imgcodecs.CV_LOAD_IMAGE_COLOR);
			double left = location.x;
			double top = location.y;
			double bottom = location.y + size.getHeight() - 60;
			double right = location.x + size.getWidth() - 140;
			CascadeClassifier faceDetector = new CascadeClassifier();
			faceDetector.load("C:/opencv-2.4.10/opencv/sources/data/haarcascades/haarcascade_frontalface_alt.xml");
			Rect faceDetections = new Rect((int) left, (int) top, (int) right, (int) bottom);
			Mat img = new Mat(img1, faceDetections);
			filename = "Errors/player" + i + ".png";
			Imgcodecs.imwrite("Errors/player" + i + ".png", img);
			Thread.sleep(3000);
			Mat img2 = Imgcodecs.imread(filename, Imgcodecs.CV_LOAD_IMAGE_COLOR);
			ret = checkmotion(img2);
		}
		if (ret == 0) {
			System.out.println("Video is not playing");
			ExtentUtility.getTest().log(LogStatus.FAIL, " Result = Video is not Playing - FALSE ",
					ExtentUtility.getTest().addScreenCapture(takeScreenShot(driver)));
		} else {
			System.out.println("Video is playing");
			ExtentUtility.getTest().log(LogStatus.PASS, " Result = Video is  Playing - TRUE ",
					ExtentUtility.getTest().addScreenCapture(takeScreenShot(driver)));
		}
		image = null;
		image1 = null;
		image2 = null;
	}

	public void Detect_VideoPause(WebDriver driver, org.openqa.selenium.Point location, Dimension size)
			throws IOException, InterruptedException {
		int ret = 0;
		for (int i = 0; i < 6; i++) {
			// ret=0;
			String filename = "Errors/error.png";
			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(src, new File(filename));
			Mat img1 = Imgcodecs.imread(filename, Imgcodecs.CV_LOAD_IMAGE_COLOR);
			double left = location.x;
			double top = location.y;
			double bottom = location.y + size.getHeight() - 60;
			double right = location.x + size.getWidth() - 140;
			CascadeClassifier faceDetector = new CascadeClassifier();
			faceDetector.load("C:/opencv-2.4.10/opencv/sources/data/haarcascades/haarcascade_frontalface_alt.xml");
			Rect faceDetections = new Rect((int) left, (int) top, (int) right, (int) bottom);
			Mat img = new Mat(img1, faceDetections);
			filename = "Errors/player" + i + ".png";
			Imgcodecs.imwrite("Errors/player" + i + ".png", img);
			Thread.sleep(3000);
			Mat img2 = Imgcodecs.imread(filename, Imgcodecs.CV_LOAD_IMAGE_COLOR);
			ret = checkmotion(img2);
		}
		if (ret == 0) {
			System.out.println("Video is not playing");
			ExtentUtility.getTest().log(LogStatus.PASS, " Result = Video is not Playing - FALSE ",
					ExtentUtility.getTest().addScreenCapture(takeScreenShot(driver)));
		} else {
			System.out.println("Video is playing");
			ExtentUtility.getTest().log(LogStatus.FAIL, " Result = Video is  Playing - TRUE ",
					ExtentUtility.getTest().addScreenCapture(takeScreenShot(driver)));
		}
		image = null;
		image1 = null;
		image2 = null;
	}

	public String takeScreenShot(WebDriver driver) throws IOException {
		Calendar cal = Calendar.getInstance();
		long s = cal.getTimeInMillis();
		File screen = null;
		screen = (File) ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		FileUtils.copyFile(screen, new File("D:/" + ExtentUtility.reportFolder + "/Screenshots/image" + s + ".png"));

		return (new File("D://" + ExtentUtility.reportFolder + "//Screenshots//image" + s + ".png").getAbsolutePath());

	}

	public void AppMatch(WebElement element, AppiumDriver driver, Mat img2, org.openqa.selenium.Point location,
			Dimension size) throws IOException, InterruptedException {
		int l = 0, k = 0;
		int match = 0;
		for (k = -0; k < 10; k++) {
			String filename = "Errors/error1.png";

			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(src, new File(filename));
			Mat img1 = Imgcodecs.imread(filename, Imgcodecs.CV_LOAD_IMAGE_COLOR);

			double left = location.x;
			double top = location.y;
			double bottom = location.y + size.getHeight();
			double right = location.x + size.getWidth();

			Rect faceDetections = new Rect(46, 650, 50, 50);
			Mat img = new Mat(img1, faceDetections);

			filename = "Errors/match1.png";
			Imgcodecs.imwrite("Errors/match1.png", img);
			Thread.sleep(1000);
			Mat img3 = Imgcodecs.imread(filename, Imgcodecs.CV_LOAD_IMAGE_COLOR);

			FeatureDetector fd = FeatureDetector.create(FeatureDetector.BRISK);
			final MatOfKeyPoint keyPointsLarge = new MatOfKeyPoint();
			final MatOfKeyPoint keyPointsSmall = new MatOfKeyPoint();
			fd.detect(img3, keyPointsLarge);
			fd.detect(img2, keyPointsSmall);

			Mat descriptorsLarge = new Mat();
			Mat descriptorsSmall = new Mat();

			DescriptorExtractor extractor = DescriptorExtractor.create(DescriptorExtractor.BRISK);
			extractor.compute(img3, keyPointsLarge, descriptorsLarge);
			extractor.compute(img2, keyPointsSmall, descriptorsSmall);

			MatOfDMatch matches = new MatOfDMatch();

			DescriptorMatcher matcher = DescriptorMatcher.create(DescriptorMatcher.BRUTEFORCE_HAMMINGLUT);
			matcher.match(descriptorsLarge, descriptorsSmall, matches);

			MatOfDMatch matchesFiltered = new MatOfDMatch();

			List<DMatch> matchesList = matches.toList();
			List<DMatch> bestMatches = new ArrayList<DMatch>();

			Double max_dist = 0.0;
			Double min_dist = 100.0;

			for (int j = 0; j < matchesList.size(); j++) {
				Double dist = (double) matchesList.get(j).distance;

				if (dist < min_dist && dist != 0) {
					min_dist = dist;
				}

				if (dist > max_dist) {
					max_dist = dist;
				}

			}

			if (min_dist > 50) {
				System.out.println("No match found");

			}

			double threshold = 3 * min_dist;
			double threshold2 = 2 * min_dist;

			if (threshold > 75) {
				threshold = 75;
			} else if (threshold2 >= max_dist) {
				threshold = min_dist * 1.1;
			} else if (threshold >= max_dist) {
				threshold = threshold2 * 1.4;
			}

			System.out.println("Threshold : " + threshold);

			for (int i = 0; i < matchesList.size(); i++) {
				Double dist = (double) matchesList.get(i).distance;

				if (dist < threshold) {
					bestMatches.add(matches.toList().get(i));

				}
			}

			matchesFiltered.fromList(bestMatches);

			System.out.println("matchesFiltered.size() : " + matchesFiltered.size());

			if (matchesFiltered.rows() >= 1) {

				System.out.println("match found");
				match = 1;
				ExtentUtility.getTest().log(LogStatus.PASS, " Result =  Match Is Found ",
						ExtentUtility.getTest().addScreenCapture(takeScreenShot(driver)));
				break;

			} else {
				System.out.println("no match found");

			}

		}

	}

	public String getresponse() {

		String BaseURl = "http://10.242.193.101:3000/";
		String url = "posts";

		HttpResponse response = null;
		HttpClient client = new DefaultHttpClient();

		HttpGet get = new HttpGet(url);
		get.addHeader("Content-Type", "application/json");

		try {
			response = client.execute(get);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("HTTP RESPONSE : " + response);

		System.out.println(response.getStatusLine());

		HttpEntity entity = response.getEntity();
		String text = "";
		try {
			text = EntityUtils.toString(entity);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("text--" + text);

		String responses = response.toString();
		System.out.println(responses);

		return responses;

	}

	public void AppDetect_VideoPause(int left, int top, int right, int bottom)
			throws IOException, InterruptedException {
		int ret = 0;
		for (int i = 0; i < 3; i++) {

			String filename = "Errors/error.png";
			File file1 = null;
			switch (toolName) {

			case "Appium":
				file1 = (File) ((TakesScreenshot) appiumDriver).getScreenshotAs(OutputType.FILE);
				break;
			case "Selenium":
				file1 = (File) ((TakesScreenshot) remoteDriver).getScreenshotAs(OutputType.FILE);
				break;
			}
			FileUtils.copyFile(file1, new File(filename));
			Mat img1 = Imgcodecs.imread(filename, Imgcodecs.CV_LOAD_IMAGE_COLOR);

			// System.out.println("left"+left+" top"+top+" right"+right+" bottom"+bottom);
			// Rect faceDetections = new Rect((int) left, (int) top, (int) right, (int)
			// bottom );
			Rect faceDetections = new Rect(left, top, right, bottom);
			Mat img = new Mat(img1, faceDetections);
			Imgcodecs.imwrite("Errors/error2.png", img);
			ret = checkmotion(img);
		}
		if (ret == 0) {
			System.out.println("Verify the Video motion when paused - Video is not playing");
			ExtentUtility.getTest().log(LogStatus.PASS,
					"Verify the Video motion when paused - Video is not Playing After tapping the Pause icon",
					ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
		} else {
			System.out.println("Verify the Video motion when paused - Video is playing");
			ExtentUtility.getTest().log(LogStatus.FAIL,
					"Verify the Video motion when paused - Video is Playing After tapping the Pause icon",
					ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
		}
		image = null;
		image1 = null;
		image2 = null;
	}

	public void dragAndDroplocation(double i, String fw_rw) throws Exception {
		try {
			// int xAxisStartPoint = element.getLocation().getX();
			// int xAxisEndPoint = xAxisStartPoint + element.getSize().getWidth();
			// int yAxis = element.getLocation().getY();
			// TouchAction act=new TouchAction(appiumDriver);
			// //pressed x axis & y axis of seekbar and move seekbar till the end
			// act.longPress(xAxisStartPoint,yAxis).moveTo(xAxisEndPoint-1,yAxis).release().perform();

			// Locating seekbar using resource id
			// WebElement seek_bar=driver.findElement(By.id("seek_bar"));
			// get start co-ordinate of seekbar
//			int start=element.getLocation().getX();
//			//Get width of seekbar
//			int end=element.getSize().getWidth();
//			//get location of seekbar vertically
//			int y=element.getLocation().getY();

			// Select till which position you want to move the seekbar
			TouchAction action = new TouchAction(appiumDriver);

			// Move it will the end
			// action.longPress(start,y).moveTo(end,y).release().perform();

			// Move it 40%
//			int moveTo=(int)(end*i);
			if (fw_rw.equalsIgnoreCase("fw")) {
				// int moveTo=(int)(end*i);
				// action.longPress(25,383).moveTo(250,383).release().perform();
			} else {
				// int moveTo=(int)(end*i);
				// action.longPress(moveTo,12).moveTo(start,12).release().perform();
			}
		} catch (Exception e) {}

		}
	
	//New codes by Elavarasan
	
	//Window handle by Elavarasan
	
	public boolean toSwitchWarningWindow(int Value) {
        boolean flag = false;
        try {
               ArrayList<String> listid = new ArrayList<String>(remoteDriver.getWindowHandles());
               System.out.println("No of windows opened "+listid.size());
               if(listid.size()>1){
            	   remoteDriver.switchTo().window(listid.get(Value));
                     System.out.println("Successfully switched to new "+Value+" window");
               }else{
                     if(checkIfPopupWindowClosed()){
                            System.out.println("Window is closed-------");
                            remoteDriver.switchTo().window(listid.get(0));
                     }
               }
                     
        }
        catch (Exception e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
        }
        return flag;
 }



     private  Boolean checkIfPopupWindowClosed() {
          try {
            return (new WebDriverWait(remoteDriver, 10)).until(new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(WebDriver d) {
                    return d.getWindowHandles().size() < 2;
                }
            });
          } catch (TimeoutException e) {
              return false;
          }
        }

   //down key by Elavarasan
     
     public void downKey(int noOfTimes)throws Exception{
 		Actions action = new Actions(remoteDriver);
 		Thread.sleep(1000);
 		for (int i = 0; i < noOfTimes; i++) {
 			action.sendKeys(Keys.ARROW_DOWN).build().perform();
 			Thread.sleep(1000);
 		}
 	} 
     
     
	
   //Up key by Elavarasan
     
     public void upKey(int noOfTimes)throws Exception{
 		Actions action = new Actions(remoteDriver);
 		Thread.sleep(1000);
 		for (int i = 0; i < noOfTimes; i++) {
 			action.sendKeys(Keys.ARROW_UP).build().perform();
 			Thread.sleep(1000);
 		}
 	}
     
     public void fileopen(String text) throws Exception {
			try {
				
				System.out.println("PDF is download ");		
				Thread.sleep(3000);
				String path = System.getProperty("user.home");
				Runtime rt = Runtime.getRuntime();
				rt.exec("cmd.exe /c start "+path+"\\Downloads\\"+text+".pdf");
				
				Thread.sleep(5000);
				Robot max = new Robot();
				//max.keyPress(KeyEvent.VK_ALT);
				//max.keyPress(KeyEvent.VK_SPACE);
				//max.keyPress(KeyEvent.VK_X);
				

				ExtentUtility.getTest().log(LogStatus.PASS, "PDF is opened successful",
						
				 ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
				Thread.sleep(3000);


			} catch (IOException exc) {
				ExtentUtility.getTest().log(LogStatus.FAIL, " PDF is not found",
						exc.toString() + ExtentUtility.getTest().addScreenCapture(takeScreenShot()));
				throw new Exception("PDF is not found");
			}
	}
  
}




