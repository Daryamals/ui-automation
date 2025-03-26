package com.github.daryamals.ui_automation.pages;

import io.qameta.allure.Step;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class FeedbackFormPage {
	private WebDriver driver;
	private WebDriverWait wait;
	List<WebElement> automationTools;

	@FindBy(id = "name-input")
	private WebElement nameInput;

	@FindBy(xpath = "//label[input[@type='password']]")
	private WebElement passwordInput;

	@FindBy(css = "select[name='automation']")
	private WebElement automationSelect;

	@FindBy(id = "email")
	private WebElement emailInput;

	@FindBy(id = "message")
	private WebElement messageInput;

	@FindBy(id = "submit-btn")
	private WebElement submitButton;

	public FeedbackFormPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}

	@Step("Open feedback form page")
	public FeedbackFormPage open() {
		driver.get("https://practice-automation.com/form-fields/");
		return this;
	}

	@Step("Enter name: {name}")
	public FeedbackFormPage enterName(String name) {
		nameInput.sendKeys(name);
		return this;
	}

	@Step("Enter password: {password}")
	public FeedbackFormPage enterPassword(String password) {
		passwordInput.sendKeys(password);
		return this;
	}

	@Step("Select drinks: {drinks}")
	public FeedbackFormPage selectDrinks(String... drinks) {
		for (String drink : drinks) {
			WebElement drinkElement = driver.findElement(By.id(drink));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", drinkElement);
		}
		return this;
	}

	@Step("Select color: {colorId}")
	public FeedbackFormPage selectColor(String colorId) {
		WebElement colorOption = driver.findElement(By.id(colorId));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", colorOption);
		return this;
	}

	@Step("Select automation option: {option}")
	public FeedbackFormPage selectAutomationOption(String option) {
		automationSelect.sendKeys(option);
		return this;
	}

	@Step("Enter email: {email}")
	public FeedbackFormPage enterEmail(String email) {
		emailInput.sendKeys(email);
		return this;
	}

	@Step("Get automation tools count")
	public int getAutomationToolsCount() {
		automationTools = driver.findElements(By.xpath("//label[text()='Automation tools']/following-sibling::ul/li"));
		return automationTools.size();
	}

	@Step("Get longest automation tool name")
	public String getLongestAutomationToolName() {
		automationTools = driver.findElements(By.xpath("//label[text()='Automation tools']/following-sibling::ul/li"));
		return automationTools.stream().map(WebElement::getText).max((a, b) -> Integer.compare(a.length(), b.length()))
				.orElse("");
	}

	@Step("Enter message: {message}")
	public FeedbackFormPage enterMessage(String message) {
		messageInput.sendKeys(message);
		return this;
	}

	@Step("Submit form")
	public FeedbackFormPage submitForm() {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitButton);
		return this;
	}

	@Step("Get alert text")
	public String getAlertText() {
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		return alert.getText();
	}

	@Step("Accept alert")
	public FeedbackFormPage acceptAlert() {
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		alert.accept();
		return this;
	}
}
