/**
 * 
 */
package com.TestBase;

import org.testng.annotations.AfterMethod;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.xml.DOMConfigurator;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import com.Logs.Log;
import com.Tudu.ActionDriver.Action;
import com.Tudu.utility.ExtentManager;
import com.Tudu.utility.ListenerClass;
import com.Tudu.utility.allureListenerClass;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * @author Vikesh
 *
 */

@Listeners(allureListenerClass.class)
public class TestBase {
	
	public static Properties prop;
    public static WebDriver driver;
   // public static ExtentReports report;
    //public static ExtentTest test;
    
    
  @BeforeSuite
	
    public void loadConfig() throws IOException {
	  	  
	  ExtentManager.setExtent();
		
	  DOMConfigurator.configure("log4j.xml");
	 
	  Log.info("Browser selected");
	  
	  
		/*try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "\\Configuration\\config.properties");
			prop.load(ip);
		
			System.out.println(prop.getProperty("browser"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	  
	  prop = new Properties();
		
		InputStream ip= ClassLoader.getSystemResourceAsStream("config.Properties");
		prop.load(ip);
		ip.close();
	}
    
	
	   public void invoke() throws Throwable {
		   
		  Log.info("before suite Executing"); 
		
       // WebDriverManager.chromedriver().setup();
       
       	String Launch=prop.getProperty("browser");
		
        if(Launch.equalsIgnoreCase("chrome")){
        	WebDriverManager.chromedriver().setup();
			
			ChromeOptions chromeOptions = new ChromeOptions();
		 // chromeOptions.setBinary("/usr/bin/google-chrome");  //Azure working
			 
			//chromeOptions.addArguments("--headless"); //Azure working
			 driver = new ChromeDriver(chromeOptions);
		 
		 Log.info(Launch +" browser open");
		 
		}else if(Launch.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}else if(Launch.equalsIgnoreCase("Edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
        
        Action act =new Action();
                
        getCellDataString("URL", 1, 0);
        Log.info("selected navigated to application URL");
       
		driver.manage().window().maximize();
		Log.info("maximizing" +Launch+ "window");
	
		act.pageLoadTimeOut(driver, 5);
		
		driver.manage().deleteAllCookies();
	}
	   
	  public void getCellDataString(String sheetName,int rownum,int cellnum) {
    	
    	try {
    		
    		FileInputStream fis=new FileInputStream("C:\\Users\\Vikesh\\Vikesh_eclipse\\TuduApplication\\TestData\\Business configuration.xlsx");
			XSSFWorkbook workbook=new XSSFWorkbook(fis);
			XSSFSheet sheet=workbook.getSheet(sheetName);
			int rowcount=sheet.getPhysicalNumberOfRows();
						
			DataFormatter formatter = new DataFormatter();
			String URL=formatter.formatCellValue(sheet.getRow(rownum).getCell(cellnum));
					
			System.out.println(URL);
			driver.get(URL);
					
    	} catch (IOException e) {
			
			e.getMessage();
			e.printStackTrace();
		}
			
    }
	/*
	 * public void setBrowser(String sheetName,int rownum,int cellnum) {
	 * 
	 * try {
	 * 
	 * XSSFWorkbook workbook=new
	 * XSSFWorkbook("C:\\Users\\Vikesh\\Vikesh_eclipse\\TuduApplication\\TestData\\Business configuration.xlsx"
	 * ); XSSFSheet sheet=workbook.getSheet("Browser"); int
	 * rowcount=sheet.getPhysicalNumberOfRows(); System.out.println(rowcount);
	 * 
	 * DataFormatter formatter = new DataFormatter(); String
	 * value=formatter.formatCellValue(sheet.getRow(rownum).getCell(cellnum));
	 * 
	 * }catch (IOException e) { // TODO Auto-generated catch block e.getMessage();
	 * e.printStackTrace(); }
	 * 
	 * 
	 * 
	 * }
	 */
	
	@AfterMethod
	@AfterSuite
	
	public void tearDown() {
		
		Log.info("Closing Browser");
		//report.flush();
		ExtentManager.endReport();
		Log.info("Extent Report flush");
		
		Log.info("After suite Exiting");
		
		
		
	}
	     
}
