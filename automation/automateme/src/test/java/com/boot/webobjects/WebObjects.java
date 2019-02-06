package com.boot.webobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class WebObjects {
	Select dropSelector;
	
	public String getHeader(WebDriver driver) {
		return driver.findElement(By.xpath("//*[@id=\"label-first\"]/b")).getText();
	}
	
	/**
	 * List of todo
	 */
	public WebElement formFetch(WebDriver driver) {
		return driver.findElement(By.xpath("//div[@id=\"todos-content\"]/form/ul//input"));
	}
	
	/**
	 * Controls
	 */
	public WebElement todoRemove(WebDriver driver) {
		return driver.findElement(By.xpath("//div[@class=\"controls\"]/input[@value=\"Remove\"]"));
	}
	
	public WebElement todoComplete(WebDriver driver) {
		return driver.findElement(By.xpath("//div[@class=\"controls\"]/input[@value=\"Complete\"]"));
	}
	
	public WebElement todoToggleAll(WebDriver driver) {
		return driver.findElement(By.xpath("//div[@class=\"controls\"]/input[@name=\"allbox\"]"));
	}
	
	/**
	 * Advanced Controls - Data [Add TODO]
	 */
	public WebElement setTodoData(WebDriver driver) {
		return driver.findElement(By.xpath("//div[@class=\"advance-controls\"]/input[@name=\"data\"]"));
	}
	
	public WebElement todoSubmit(WebDriver driver) {
		return driver.findElement(By.xpath("//div[@class=\"advance-controls\"]/input[@value=\"Add\"]"));
	}
	
	public void setNewTodoCategoryColor(WebDriver driver, String color) {
		dropSelector = new Select(
				driver.findElement(By.xpath("//div[@class=\"advance-controls\"]//select[@name=\"category\"]")));
		dropSelector.selectByVisibleText(color);
	}
	
	public void setNewTodoDueDay(WebDriver driver) {
		dropSelector = new Select(
				driver.findElement(By.xpath("//div[@class=\"advance-controls\"]//select[@name=\"due_day\"]")));
		dropSelector.selectByVisibleText("None");
	}
	
	public void setNewTodoDueDay(WebDriver driver, Integer day) {
		dropSelector = new Select(
				driver.findElement(By.xpath("//div[@class=\"advance-controls\"]//select[@name=\"due_day\"]")));
		if (day > 0 & day <= 31) {
			dropSelector.selectByVisibleText(day.toString());
		}
		else {
			dropSelector.selectByVisibleText("None");
		}
	}
	
	public void setNewTodoDueMonth(WebDriver driver) {
		dropSelector = new Select(
				driver.findElement(By.xpath("//div[@class=\"advance-controls\"]//select[@name=\"due_month\"]")));
		dropSelector.selectByVisibleText("None");
	}
	
	public void setNewTodoDueMonth(WebDriver driver, String month) {
		// TODO - check month value
		dropSelector = new Select(
				driver.findElement(By.xpath("//div[@class=\"advance-controls\"]//select[@name=\"due_month\"]")));
		dropSelector.selectByVisibleText(month);
	}
	
	public void setNewTodoDueYear(WebDriver driver) {
		dropSelector = new Select(
				driver.findElement(By.xpath("//div[@class=\"advance-controls\"]//select[@name=\"due_year\"]")));
		dropSelector.selectByVisibleText("None");
	}
	
	public void setNewTodoDueYear(WebDriver driver, Integer year) {
		// TODO - check year value
		dropSelector = new Select(
				driver.findElement(By.xpath("//div[@class=\"advance-controls\"]//select[@name=\"due_year\"]")));
		dropSelector.selectByVisibleText(year.toString());
	}
	
	/**
	 * Advanced Controls - Category [Add Category]
	 */
	public Object todoNewCategoryData(WebDriver driver) {
		return driver.findElement(By.xpath("//div[@class=\"advance-controls\"]/input[@name=\"categorydata\"]"));
	}
	
	public Object todoNewCategorySubmit(WebDriver driver) {
		return driver.findElement(By.xpath("//div[@class=\"advance-controls\"]//input[@value=\"Add category\"]"));
	}
	
	public void setNewCategoryColor(WebDriver driver, String color) {
		dropSelector = new Select(
				driver.findElement(By.xpath("//div[@class=\"advance-controls\"]//select[@name=\"colour\"]")));
		dropSelector.selectByVisibleText(color);
	}
}
