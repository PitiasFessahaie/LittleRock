package com.bdd.stepdefinitions;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.bdd.helper.DriverInitializer;
import com.cucumber.listener.Reporter;
import com.google.common.io.Files;

import cucumber.api.Scenario;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class WalmartSearchSteps {

	public WebDriver driver;
	
	
	@Given("^I open a browser$")
	public void i_open_a_browser() throws Throwable {
		driver = DriverInitializer.startDriver();
	}

	@When("^I navigate to Walmart website$")
	public void i_navigate_to_Walmart_website() throws Throwable {
		driver.get("http://www.walmart.com/");
	}

	@When("^I enter XBox in the search field$")
	public void i_enter_XBox_in_the_search_field() throws Throwable {
		WebElement searchBox = driver.findElement(By.id("global-search-input"));
		searchBox.sendKeys("XBox");
	}

	@When("^I click search button$")
	public void i_click_search_button() throws Throwable {
		WebElement searchButton = driver.findElement(By.cssSelector("#global-search-submit > span > img"));
		searchButton.click();

	}

	@Then("^I should see XBox result$")
	public void i_should_see_XBox_result() throws Throwable {
		String title = driver.getTitle();
		System.out.println("Title :"+title);
		//WebElement result = driver.findElement(By.partialLinkText("XBox"));
		assertEquals(title,"");
	}
	
	@After
	public void endTest(Scenario scenario){
		if (scenario.isFailed()) {
			String testName = scenario.getName().replaceAll(" ", "_");
			String imagePath = "target/screenshot/"+testName+".png";
			File file = new File(imagePath);
			String imageAbsolutePath = file.getAbsolutePath();
			try {
				File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);				
				Files.copy(src, new File(imagePath));
				Reporter.addScreenCaptureFromPath(imageAbsolutePath);	
				
				// --- Adding screenshot directly to the cucumber report
				//byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
				//scenario.embed(screenshot, "image/png");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		if(driver != null){
			driver.close();
			System.out.println("closing browser..");
		}
	}
	
	
}























