package com.bdd.stepdefinitions;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.bdd.helper.DriverInitializer;
import com.cucumber.listener.Reporter;
import com.google.common.io.Files;

import cucumber.api.PendingException;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SearchStesps {

	WebDriver driver = null;

	
	@Given("^I open chrome browser$")
	public void iOpenChromeBrowser() throws Throwable {
		driver = DriverInitializer.startDriver();
	}

	@When("^I navigate to google page$")
	public void iNavigateToGooglePage() throws Throwable {
		driver.get(DriverInitializer.getProperty("login.url"));
	}

	@When("^I enter laptop in the search field$")
	public void iEnterLaptopInTheSearchField() throws Throwable {
		driver.findElement(By.name("q")).sendKeys("laptop");
	}

	@When("^I click on search button$")
	public void iClickOnSearchButton() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
	}

	@When("^I enter \"([^\"]*)\" in the search field$")
	public void i_enter_in_the_search_field(String itemList) throws Throwable {
		driver.findElement(By.name("q")).sendKeys(itemList);
	}

	@Then("^I should see search results$")
	public void iShouldSeeSearchResults() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		WebElement link = driver.findElement(By.partialLinkText("laptop"));
		assertThat(link.getText().equalsIgnoreCase("laptop"));
	}

	@Then("^I should see search results with \"([^\"]*)\"$")
	public void iShouldSeeSearchResultsWith(String expectedResult) throws Throwable {
		WebElement link = driver.findElement(By.partialLinkText(expectedResult));
		assertThat(link.getText().equalsIgnoreCase(expectedResult));
	}

	@After
	public void embedScreenshot(Scenario scenario) throws Exception {
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
