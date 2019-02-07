package com.boot.automateme;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.boot.webobjects.WebObjects;

import io.github.bonigarcia.wdm.WebDriverManager;

/*****************************************************************************
 * Author:      Mahesh Nair
 * Description: This is the first Selenium TestNG test.
 *              
*******************************************************************************/

public class AppTest {

    //-----------------------------------Global Variables-----------------------------------
    //Declare a Webdriver variable
    public WebDriver driver;
    // Port number for the qa-exercise
    public String apachePort = "89";
    //Declare a test URL variable
    public String testURL = "http://localhost:"+apachePort+"/qa-exercise/";
    WebObjects webObjects = new WebObjects();
    
    @BeforeSuite
    public void setupClass() {
    	WebDriverManager.chromedriver().setup();
        //Create a new ChromeDriver
        driver = new ChromeDriver();
    }

    //-----------------------------------Test Setup-----------------------------------
    @BeforeMethod
    public void setupTest() {
        //Go to localhost:[port]/qa-exercise
        driver.navigate().to(testURL);        
    }

    //-----------------------------------Tests-----------------------------------
    @Test(description = "1. Simple test to get the header")
    public void firstTest () {
        //Get page title
        // String title = driver.findElement(By.xpath("//*[@id=\"label-first\"]/b")).getText();

        String title = webObjects.getHeader(driver);
        
        //Print page's title
        System.out.println("Page Title: " + title);

        //Assertion
        Assert.assertEquals(title, "NSS-TODO List v 1.1", "Title assertion is failed!");
    }
    
	
	
	@Test(description = "2.a. Create a Todo task - no category and no due date")
	public void setTodoDataNoCatNoDue() {
		String task = "Heyo";
		
		webObjects.setTodoData(driver).sendKeys(task);
		webObjects.todoSubmit(driver).click();
		
		// TODO - reuse this block to check if todo tasks are already existing
		if (webObjects.todoAlreadyExists(driver)) {
			// Click Back
			webObjects.todoAlreadyExistsGoBack(driver);
			System.out.println("The TODO item exists already.");
		}
		
		// Text is no wrapped in a span or such tags?
		String formFetch = webObjects.formFetch(driver,task).getText();
		Assert.assertEquals(formFetch, task+" (None)", "Title assertion is failed!");
	}
	
	@Test(description = "2.b. Create a Todo task - assign category and no due date")
	public void setTodoDataAssignCatNoDue() {
		String task = "Buy Chipotle";
		String cat = "Play";
		
		webObjects.setTodoData(driver).sendKeys(task);
		webObjects.setNewTodoCategoryColor(driver, cat);
		webObjects.todoSubmit(driver).click();
		String formFetch = webObjects.formFetch(driver,task).getText();
		Assert.assertEquals(formFetch, task +" (None)", "Title assertion is failed!");
	}
    
    //-----------------------------------Test TearDown-----------------------------------
	@AfterSuite
	public void teardownTest () throws InterruptedException{
	    //Close browser and end the session
		Thread.sleep(4000);
	    driver.quit();
	}


}