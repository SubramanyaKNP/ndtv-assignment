package com.testvagrant.ndtv.pages;

import java.util.Map;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.testvagrant.ndtv.pageobjects.WeatherPageObject;
import com.testvagrant.ndtv.services.WeatherModel;

/**
 * The Class WeatherPage.
 */
public class WeatherPage extends WeatherPageObject {

	/** The driver. */
	private WebDriver driver;

	/** The Constant logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(WeatherPage.class);

	/**
	 * Instantiates a new weather page.
	 *
	 * @param driver the driver
	 */
	public WeatherPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	/**
	 * Search city.
	 *
	 * @param cityName the city name
	 */
	public void searchCity(String cityName) {
		ExpectedConditions.visibilityOf(mapElement).apply(driver);
		searchBoxElement.sendKeys(cityName);
	}

	/**
	 * Check city name.
	 *
	 * @return the string
	 */
	public String checkCityName() {
		return cityElement.getText();
	}

	/**
	 * Select city.
	 *
	 * @param city the city
	 */
	public void selectCity(String city) {
		WebElement cityCheckBoxElement = cityElement.findElement(By.tagName("input"));
		String checkedAttr = cityCheckBoxElement.getAttribute("checked");
		if (checkedAttr == null || !checkedAttr.equalsIgnoreCase("true")) {
			cityCheckBoxElement.click();
		} else {
			LOGGER.info("City {} already selected", city);
		}
	}

	/**
	 * Verify selected city in map.
	 *
	 * @param city the city
	 * @return true, if successful
	 */
	public boolean verifyCityInMap(String city) {
		return mapCityElements.stream().map(e -> e.getAttribute("title")).filter(s -> s.equalsIgnoreCase(city))
				.findFirst().isPresent();
	}

	/**
	 * Click on city in map.
	 *
	 * @param city the city
	 */
	public void clickOnCityInMap(String city) {
		mapCityElements.stream().forEach(e -> {
			if (e.getAttribute("title").equalsIgnoreCase(city)) {
				e.click();
			}
		});
	}

	/**
	 * Gets the pop up model elements.
	 *
	 * @return the pop up model elements
	 */
	public WeatherModel getPopUpModelElements(String units) {
		ExpectedConditions.visibilityOfAllElements(popUpModelElements).apply(driver);
		Map<String, String> map = popUpModelElements.stream().map(e -> e.getText().split(":"))
				.collect(Collectors.toMap(s -> s[0].trim(), s -> s[1].trim()));
		return buildWeatherModel(map, units);
	}

	/**
	 * Builds the weather model.
	 *
	 * @param map the map
	 * @return the weather model
	 */
	private WeatherModel buildWeatherModel(Map<String, String> map, String units) {
		final WeatherModel wm = new WeatherModel();
		wm.setCondition(map.get("Condition"));
		wm.setWind(map.get("Wind"));
		wm.setHumidity(Double.valueOf(map.get("Humidity").replaceAll("%", "")));
		if (units.equalsIgnoreCase("metric")) {
			wm.setTemparature(Double.valueOf(map.get("Temp in Degrees")));
		} else {
			wm.setTemparature(Double.valueOf(map.get("Temp in Fahrenheit")));
		}
		return wm;
	}
}
