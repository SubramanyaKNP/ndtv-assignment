package com.testvagrant.ndtv.services;

import java.util.Comparator;

import org.slf4j.LoggerFactory;

import com.testvagrant.ndtv.enums.ConfigEnums;
import com.testvagrant.ndtv.helper.Variance;

/**
 * The Class WeatherTempComparator.
 */
public class WeatherTempComparator implements Comparator<WeatherModel> {

	/** The Constant Logger. */
	private static final org.slf4j.Logger Logger = LoggerFactory.getLogger(WeatherTempComparator.class);

	/**
	 * Compare.
	 *
	 * @param o1 the o 1
	 * @param o2 the o 2
	 * @return the int
	 */
	@Override
	public int compare(WeatherModel o1, WeatherModel o2) {
		if (o1.getTemparature() == o2.getTemparature()) {
			Logger.info("Weathers {} and {} are equal", o1.getTemparature(), o2.getTemparature());
			return 0;
		} else if (o1.getTemparature() > o2.getTemparature()) {
			Logger.info("Weather {} is greater than {}", o1.getTemparature(), o2.getTemparature());
			return Variance.calculateVariance(o1.getTemparature(), o2.getTemparature(),
					Double.valueOf(ConfigEnums.TEMP_VARIANCE_DIFF.getData()));
		} else {
			Logger.info("Weather {} is less than {}", o1.getTemparature(), o2.getTemparature());
			return Variance.calculateVariance(o1.getTemparature(), o2.getTemparature(),
					Double.valueOf(ConfigEnums.TEMP_VARIANCE_DIFF.getData()));
		}
	}

}
