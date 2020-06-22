package com.testvagrant.ndtv.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGListener implements ITestListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestNGListener.class);

	@Override
	public void onTestStart(ITestResult result) {
		LOGGER.info("Running test {}", result.getName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		LOGGER.info("Test case {} is passed", result.getName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		LOGGER.info("Test case {} is failed", result.getName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

	}

}
