package com.testvagrant.ndtv.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * The Class WeatherPageObject.
 */
public class WeatherPageObject {

	/** The search box element. */
	@FindBy(css = ".searchBox")
	protected WebElement searchBoxElement;

	/** The city element. */
	@FindBy(css = ".comment_cont > .messages > div:not([style]) > label")
	protected WebElement cityElement;

	/** The city element grid. */
	@FindBy(css = ".comment_cont > .messages")
	protected WebElement cityElementGrid;

	/** The map element. */
	@FindBy(css = "#map_canvas")
	protected WebElement mapElement;

	@FindBy(xpath = "//*[@class='leaflet-pane leaflet-marker-pane']/div/div")
	protected List<WebElement> mapCityElements;

	@FindBy(css = ".leaflet-popup-content > div > span > b")
	protected List<WebElement> popUpModelElements;

	/**
	 * Instantiates page elements.
	 *
	 * @param driver the driver
	 */
	protected WeatherPageObject(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

}
