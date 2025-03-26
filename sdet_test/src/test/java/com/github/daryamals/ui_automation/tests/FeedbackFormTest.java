package com.github.daryamals.ui_automation.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.daryamals.ui_automation.pages.FeedbackFormPage;
import com.github.daryamals.ui_automation.utils.TestData;
import com.github.daryamals.ui_automation.utils.WebDriverManager;

public class FeedbackFormTest {
	private WebDriver driver;
	private FeedbackFormPage feedbackFormPage;

	@BeforeClass
	public void setUp() {
		driver = WebDriverManager.createDriver();
		driver.manage().window().maximize();
		feedbackFormPage = new FeedbackFormPage(driver).open();
	}

	@Test
	public void testFeedbackFormSubmission() throws InterruptedException {
		int toolsCount = feedbackFormPage.getAutomationToolsCount();
		String longestTool = feedbackFormPage.getLongestAutomationToolName();

		feedbackFormPage.enterName(TestData.NAME).enterPassword(TestData.PASSWORD)
				.selectDrinks(TestData.FAVORITE_DRINKS).selectColor(TestData.COLOR)
				.selectAutomationOption(TestData.AUTOMATION_OPTION).enterEmail(TestData.EMAIL)
				.enterMessage(toolsCount + " " + longestTool).submitForm();
		// Задержка для удобства
		// Thread.sleep(2000);
		String alertText = feedbackFormPage.getAlertText();
		Assert.assertEquals(alertText, "Message received!");
		feedbackFormPage.acceptAlert();
		// Задержка для удобства
		// Thread.sleep(2000);
	}

	@AfterClass
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
