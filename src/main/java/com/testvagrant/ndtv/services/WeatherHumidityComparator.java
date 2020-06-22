package com.testvagrant.ndtv.services;

import java.util.Comparator;

import org.slf4j.LoggerFactory;

import com.testvagrant.ndtv.enums.ConfigEnums;
import com.testvagrant.ndtv.helper.Variance;

/**
 * The Class WeatherTempComparator.
 */
public class WeatherHumidityComparator implements Comparator<WeatherModel> {

	/** The Constant Logger. */
	private static final org.slf4j.Logger Logger = LoggerFactory.getLogger(WeatherHumidityComparator.class);

	/**
	 * Compare.
	 *
	 * @param o1 the o 1
	 * @param o2 the o 2
	 * @return the int
	 */
	@Override
	public int compare(WeatherModel o1, WeatherModel o2) {
		if (o1.getHumidity() == o2.getHumidity()) {
			Logger.info("Humidity {} and {} are equal", o1.getHumidity(), o2.getHumidity());
			return 0;
		} else if (o1.getHumidity() > o2.getHumidity()) {
			Logger.info("Humidity {} is greater than {}", o1.getHumidity(), o2.getHumidity());
			return Variance.calculateVariance(o1.getHumidity(), o2.getHumidity(),
					Double.valueOf(ConfigEnums.HUMIDITY_VARIANCE_DIFF.getData()));
		} else {
			Logger.info("Humidity {} is less than {}", o1.getHumidity(), o2.getHumidity());
			return Variance.calculateVariance(o1.getHumidity(), o2.getHumidity(),
					Double.valueOf(ConfigEnums.HUMIDITY_VARIANCE_DIFF.getData()));
		}
	}

}
