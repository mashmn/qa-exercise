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
 *              It opens swtestacademy homepage and prints and checks its title.
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
    @Test
    public void firstTest () {
        //Get page title
        // String title = driver.findElement(By.xpath("//*[@id=\"label-first\"]/b")).getText();

        String title = webObjects.getHeader(driver);
        
        //Print page's title
        System.out.println("Page Title: " + title);

        //Assertion
        Assert.assertEquals(title, "NSS-TODO List v 1.1", "Title assertion is failed!");
    }
    
	
	
	@Test(description = "Create a Todo task - no category and no due date")
	public void createTodo() {
		webObjects.setTodoData(driver).sendKeys("Homework");
		webObjects.todoSubmit(driver).click();
		String formFetch = webObjects.formFetch(driver).getText();
		Assert.assertEquals(formFetch, "Homework (None)", "Title assertion is failed!");
	}
    
    //-----------------------------------Test TearDown-----------------------------------
	@AfterSuite
	public void teardownTest () throws InterruptedException{
	    //Close browser and end the session
		Thread.sleep(4000);
	    driver.quit();
	}


}