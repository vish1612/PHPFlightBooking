package PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class flightResultPage {

	public WebDriver driver;

	public flightResultPage(WebDriver driver) {
		this.driver = driver;
	}

	By verifyFromCity = By
			.xpath("//div[@class='row']/div[1]//p[@class='theme-search-results-item-flight-section-meta-city']");
	By verifyToCity = By
			.xpath("//div[@class='row']/div[3]//p[@class='theme-search-results-item-flight-section-meta-city']");
	By searchPage = By.xpath("//button[text()='Modify Search']");

	public List<WebElement> verifyFromCity() {
		return driver.findElements(verifyFromCity);
	}

	public List<WebElement> verifyToCity() {
		return driver.findElements(verifyToCity);
	}

	public WebElement searchPage() {
		return driver.findElement(searchPage);
	}

}
