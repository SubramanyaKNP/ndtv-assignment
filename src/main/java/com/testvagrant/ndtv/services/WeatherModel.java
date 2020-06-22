package com.testvagrant.ndtv.services;

public class WeatherModel {

	private String condition;

	private String wind;

	private Double humidity;

	private Double temperature;

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getWind() {
		return wind;
	}

	public void setWind(String wind) {
		this.wind = wind;
	}

	public Double getHumidity() {
		return humidity;
	}

	public void setHumidity(Double humidity) {
		this.humidity = humidity;
	}

	public Double getTemparature() {
		return temperature;
	}

	public void setTemparature(Double temparature) {
		this.temperature = temparature;
	}

}
