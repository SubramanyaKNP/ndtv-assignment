package com.testvagrant.ndtv;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.testvagrant.ndtv.enums.ConfigEnums;
import com.testvagrant.ndtv.pages.WeatherPage;
import com.testvagrant.ndtv.services.WeatherHumidityComparator;
import com.testvagrant.ndtv.services.WeatherModel;
import com.testvagrant.ndtv.services.WeatherService;
import com.testvagrant.ndtv.services.WeatherTempComparator;
import com.testvagrant.testtemplate.BaseTestTemplate;

import io.restassured.response.Response;

/**
 * The Class WeatherTest.
 */
@Listeners(com.testvagrant.ndtv.listeners.TestNGListener.class)
public class WeatherTest extends BaseTestTemplate {

	/** The weather page. */
	private WeatherPage weatherPage;

	/** The weather serv. */
	private WeatherService weatherServ;

	private WeatherModel weatherModel1;

	private WeatherModel weatherModel2;

	/**
	 * Inits the driver.
	 */

	/**
	 * Visit ndtv web site.
	 */
	@Test(description = "Navigate to NDTV website")
	public void visitNdtvWebSite() {
		BaseTestTemplate.driver.get("https://social.ndtv.com/static/Weather/report/");
	}

	/**
	 * Test.
	 *
	 * @param city the city
	 */
	@Test(description = "Fetch weather data for a city", dependsOnMethods = { "visitNdtvWebSite" })
	@Parameters({ "city", "units" })
	public void getWeatherDataFromNdtvTest(@Optional("Bengaluru") String city, @Optional("imperial") String units) {
		weatherPage = new WeatherPage(driver);
		weatherPage.searchCity(city);
		Assert.assertTrue(weatherPage.checkCityName().contains(city), "City not available");
		weatherPage.selectCity(city);
		Assert.assertTrue(weatherPage.verifyCityInMap(city));
		weatherPage.clickOnCityInMap(city);
		weatherModel1 = weatherPage.getPopUpModelElements(units);
	}

	@Test(description = "Fetch weather data for a city using API", dependsOnMethods = { "visitNdtvWebSite" })
	@Parameters({ "city", "units" })
	public void getAPIResponseTest(@Optional("Bengaluru") String city, @Optional("imperial") String units) {
		weatherServ = new WeatherService();
		Response response = weatherServ.getWeatherForCity(city, units,
				System.getProperty("APPID", "7fe67bf08c80ded756e598d6f8fedaea"));
		Assert.assertEquals(response.getStatusCode(), 200);
		weatherModel2 = WeatherService.buildWsModel(response, units);
	}

	@Test(description = "Compare weather temperature from API and UI", dependsOnMethods = { "getAPIResponseTest",
			"getWeatherDataFromNdtvTest" })
	public void compareWeatherTempTest() {
		WeatherTempComparator wtc = new WeatherTempComparator();
		int value = wtc.compare(weatherModel1, weatherModel2);
		Assert.assertEquals(value, 0, "Temperature's from API and UI has exceeded the variance of "
				+ ConfigEnums.TEMP_VARIANCE_DIFF.getData());
	}

	@Test(description = "Compare weather humidity from API and UI", dependsOnMethods = { "getAPIResponseTest",
			"getWeatherDataFromNdtvTest" })
	public void compareWeatherHumidityTest() {
		WeatherHumidityComparator whcComparator = new WeatherHumidityComparator();
		int value = whcComparator.compare(weatherModel1, weatherModel2);
		Assert.assertEquals(value, 0, "Humidity from API and UI has exceeded the variance of "
				+ ConfigEnums.HUMIDITY_VARIANCE_DIFF.getData());
	}
}
