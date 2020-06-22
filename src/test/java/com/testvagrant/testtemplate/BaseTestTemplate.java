package com.testvagrant.testtemplate;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTestTemplate {
	/** The driver. */
	protected static WebDriver driver;

	private static final Logger LOGGER = LoggerFactory.getLogger(BaseTestTemplate.class);

	@BeforeTest
	public void initDriver() {
		LOGGER.info("Instantiation driver");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@AfterTest
	public void TearDown() {
		driver.close();
	}

}
