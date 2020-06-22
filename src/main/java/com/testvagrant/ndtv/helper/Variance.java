package com.testvagrant.ndtv.helper;

import org.slf4j.LoggerFactory;

import com.testvagrant.ndtv.enums.ConfigEnums;
import com.testvagrant.ndtv.services.WeatherTempComparator;

public class Variance {

	private static final org.slf4j.Logger Logger = LoggerFactory.getLogger(WeatherTempComparator.class);

	/**
	 * Calculates the Variance. If variance is less than specified values the return
	 * 0. variance data is the configurable. Stored in {@link ConfigEnums}
	 *
	 * @return the int
	 */
	public static int calculateVariance(Double d1, Double d2, Double varianceData) {
		if (Math.abs(d1 - d2) > varianceData) {
			Logger.info("Got a variance of {} for {} and {}", Math.abs(d1 - d2), d1, d2);
			return -1;
		}
		Logger.info("Got a variance of {} for {} and {}", Math.abs(d1 - d2), d1, d2);
		return 0;
	}
}
