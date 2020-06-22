package com.testvagrant.ndtv.enums;

public enum ConfigEnums {

	PAGE_TIME_OUT(20),

	SCRIPT_TIME_OUT(20),

	TEMP_VARIANCE_DIFF(20),
	
	HUMIDITY_VARIANCE_DIFF(1);

	int data;

	ConfigEnums(int data) {
		this.data = data;
	}

	public int getData() {
		return this.data;
	}

}
