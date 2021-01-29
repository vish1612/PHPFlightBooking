package PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class flightBookingPage {

	public WebDriver driver;

	public flightBookingPage(WebDriver driver) {
		this.driver = driver;
	}

	By bookingOptions = By.xpath("//a[@data-name='flights']");
	By cabinClass = By.xpath("//div[@class='form-icon-left flightclass']");
	By cabinClassOptions = By.xpath("//ul[@class='chosen-results']/li");
	By fromLoc = By.xpath("//div[@id='s2id_location_from']/a");
	By cityText = By.xpath("//div[@class='select2-result-label']");
	By cityPicker = By.xpath("//span[@class='select2-match']");

	By toLoc = By.xpath("//div[@id='s2id_location_to']/a");
	By datePicker = By.id("FlightsDateStart");

	By validateDateMonth = By
			.xpath("//div[@id='datepickers-container']/descendant::div[@class='datepicker--nav-title'][9]");
	By nextMonthButton = By.xpath("//div[@id='datepickers-container']/descendant::div[@data-action='next'][9]");
	By dateSelection = By.xpath("//div[@data-month='1']");
	By adultsSelection = By.xpath("//input[@name='fadults']/following-sibling::span[1]//button[contains(@class,'up')]");
	By childSelection = By
			.xpath("//input[@name='fchildren']/following-sibling::span[1]//button[contains(@class,'up')]");
	By searchButton = By
			.xpath("//div[@class='col-lg-1 col-sm-12 col-xs-12']//button[@class='btn-primary btn btn-block']");

	public WebElement bookingOptions() {
		return driver.findElement(bookingOptions);
	}

	public WebElement cabinClass() {
		return driver.findElement(cabinClass);
	}

	public List<WebElement> cabinClassOptions() {
		return driver.findElements(cabinClassOptions);
	}

	public WebElement fromLoc() {
		return driver.findElement(fromLoc);
	}

	public WebElement cityText() {
		return driver.findElement(cityText);
	}

	public WebElement toLoc() {
		return driver.findElement(toLoc);
	}

	public WebElement cityPicker() {
		return driver.findElement(cityPicker);
	}

	public WebElement datePicker() {
		return driver.findElement(datePicker);
	}

	public WebElement validateDateMonth() {
		return driver.findElement(validateDateMonth);
	}

	public WebElement nextMonthButton() {
		return driver.findElement(nextMonthButton);
	}

	public List<WebElement> dateSelection() {
		return driver.findElements(dateSelection);
	}

	public WebElement adultsSelection() {
		return driver.findElement(adultsSelection);
	}

	public WebElement childSelection() {
		return driver.findElement(childSelection);
	}
	
	public WebElement searchButton() {
		return driver.findElement(searchButton);
	}

}
