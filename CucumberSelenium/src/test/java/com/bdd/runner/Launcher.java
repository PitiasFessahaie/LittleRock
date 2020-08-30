package com.bdd.runner;
import java.io.File;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import com.cucumber.listener.Reporter;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions( 
		plugin = { "com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html"},
		snippets = SnippetType.CAMELCASE,
		features = {"src/test/resources/features"},
		glue = {"com.bdd.stepdefinitions"},
		monochrome = true)
		//tags = {"@single and "})
		// tags = {"@multiple"})
public class Launcher {
	
 @AfterClass
 public static void writeExtentReport() {
 Reporter.loadXMLConfig(new File("src/test/resources/extentConfig.xml"));
     Reporter.setSystemInfo("User Name", System.getProperty("user.name"));
     Reporter.setSystemInfo("Time Zone", System.getProperty("user.timezone"));
 }
}
