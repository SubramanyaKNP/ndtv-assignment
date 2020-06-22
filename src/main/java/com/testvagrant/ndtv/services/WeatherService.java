package com.testvagrant.ndtv.services;

import io.restassured.RestAssured;
import io.restassured.response.Response;

/**
 * The Class WeatherService.
 */
public class WeatherService {

	/**
	 * Gets the weather for city.
	 *
	 * @param city  the city
	 * @param units the units
	 * @param appId the app id
	 * @return the weather for city
	 */
	public Response getWeatherForCity(String city, String units, String appId) {
		RestAssured.baseURI = "https://api.openweathermap.org/data/2.5/weather";
		return RestAssured.given().log().all().queryParam("q", city).queryParam("appid", appId)
				.queryParam("units", units).get().andReturn();
	}

	/**
	 * Builds the WeatherService model.
	 *
	 * @param response the response
	 * @param units    the units
	 * @return the weather model
	 */
	public static WeatherModel buildWsModel(Response response, String units) {
		final WeatherModel wm = new WeatherModel();
		wm.setTemparature(Double.valueOf(response.jsonPath().getFloat("main.temp")));
		wm.setHumidity(Double.valueOf(response.jsonPath().getFloat("main.humidity")));
		return wm;
	}

}
