package FlightBooking.FlexiLoans_FlightBooking;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import PageObject.flightBookingPage;
import PageObject.flightResultPage;
import Resources.base;

public class flightBooking extends base {

	int i, j = 0;
	public static JavascriptExecutor js;
	public static XSSFSheet sheet;

	@BeforeTest
	public void Initialization() throws IOException {
		driver = InitializeDriver();
		js = (JavascriptExecutor) driver;
		sheet = ReadTestData();
	}

	@Test
	public void flightBookings() throws Exception {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
		flightBookingPage fb = new flightBookingPage(driver);
		flightResultPage fr = new flightResultPage(driver);
		fb.bookingOptions().click();
		fb.cabinClass().click();
		List<WebElement> cabins = fb.cabinClassOptions();

		for (WebElement cabin : cabins) {
			if (cabin.getText().equalsIgnoreCase(sheet.getRow(1).getCell(0).getStringCellValue())) {
				waitForElement(cabin);
				cabin.click();
				break;
			}
		}
		waitForElement(fb.fromLoc());
		fb.fromLoc().click();
		waitForElement(fb.fromLoc());
		fb.fromLoc().sendKeys(sheet.getRow(1).getCell(1).getStringCellValue());
		String fromCity = fb.cityText().getText();
		fb.cityPicker().click();
		String fromCityCode = city(fromCity);
		waitForElement(fb.toLoc());
		fb.toLoc().click();
		fb.toLoc().sendKeys(sheet.getRow(1).getCell(2).getStringCellValue());
		waitForElement(fb.cityText());
		String toCity = fb.cityText().getText();
		fb.cityPicker().click();
		String toCityCode = city(toCity);
		waitForElement(fb.datePicker());
		fb.datePicker().click();
		while (!fb.validateDateMonth().getText().contains(sheet.getRow(1).getCell(3).getStringCellValue())) {
			waitForElement(fb.nextMonthButton());
			fb.nextMonthButton().click();
		}

		List<WebElement> date = fb.dateSelection();
		for (WebElement date1 : date) {
			if (date1.getText().equalsIgnoreCase(sheet.getRow(1).getCell(4).getStringCellValue())) {
				js.executeScript("arguments[0].scrollIntoView(true);", date1);
				date1.click();
				break;
			}
		}
		while (i < sheet.getRow(1).getCell(5).getNumericCellValue() - 1) {

			fb.adultsSelection().click();
			i++;
		}
		while (j < sheet.getRow(1).getCell(6).getNumericCellValue()) {
			fb.childSelection().click();
			j++;
		}
		fb.searchButton().click();
		waitForElement(fr.searchPage());

		List<Boolean> ActualCityList = new ArrayList<Boolean>();
		List<Integer> count = new ArrayList<Integer>();
		List<WebElement> fromLoc = fr.verifyFromCity();
		List<WebElement> toLoc = fr.verifyToCity();

		for (int i = 0; i < fromLoc.size(); i++) {

			if (fromLoc.get(i).getText().equalsIgnoreCase(fromCityCode)
					& toLoc.get(i).getText().equalsIgnoreCase(toCityCode)) {
				ActualCityList.add(true);
			} else {
				ActualCityList.add(false);
				count.add(i);

			}
		}
		if (ActualCityList.contains(false)) {
			AssertJUnit.assertTrue("Incorrect Record at " + count.toString() + " index",false);
		}
	}

	@AfterTest
	public void closeSession() {
		driver.quit();
	}

}
