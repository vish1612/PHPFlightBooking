package Resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

public class base {
	public static WebDriver driver = null;
	public Properties prop;
	public static JavascriptExecutor js;
	public static XSSFSheet sheet;

	public WebDriver InitializeDriver() throws IOException {

		prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\Resources\\data.properties");
		prop.load(fis);
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

	public static String city(String City) {
		String expected = null;
		String[] arrOfStr = City.split("\\(");
		for (String actual : arrOfStr) {
			if (actual.contains(")")) {
				String[] arrOfStr1 = actual.split("\\)");
				for (String a : arrOfStr1) {
					expected = a;
				}
			}
		}
		return expected;
	}

	public static XSSFSheet ReadTestData() throws IOException {
		File TestData = new File(System.getProperty("user.dir") + "\\src\\main\\java\\Resources\\TestData.xlsx");
		FileInputStream fis = new FileInputStream(TestData);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheet("Sheet1");
		return sh;
	}

	public static void waitForElement(WebElement element) {
		WebDriverWait wb = new WebDriverWait(driver, 20);
		wb.until(ExpectedConditions.elementToBeClickable(element));
	}

}
