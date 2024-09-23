package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import myAutomationProject.Croma.BaseTest;
import pageObjects.HomePage;
import pageObjects.ProductPage;
import pageObjects.SearchResultsPage;


public class ProductSearchTest extends BaseTest {
    ExtentSparkReporter sparkReporter;
    ExtentReports extent;
    ExtentTest test;
    Logger logger = LogManager.getLogger(ProductSearchTest.class);
    @BeforeClass
    public void setup() throws IOException {
        initializeDriver();
        launchApplication();
       sparkReporter = new ExtentSparkReporter("C:\\Users\\user\\eclipse-workspace\\Croma\\Reports\\ExtentReport.html");
        //sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setDocumentTitle("Automation Test Report");
        sparkReporter.config().setReportName("Croma Product Search Test");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

		extent.setSystemInfo("Tester", "Growitskill Tester-Saurav Singh Negi");
		extent.setSystemInfo("Batch", "April 2024");
		extent.setSystemInfo("Tools", "Selenium 4");
		extent.setSystemInfo("JDK", "15");
    
        test = extent.createTest("Product Search Test");
    }

    @Test
    public void searchAndFilterProducts() throws IOException, InterruptedException {
       
    	try {
    		test.info("Navigating to Croma");
        // Read search keyword from Excel
        FileInputStream fis = new FileInputStream("C:\\Users\\user\\eclipse-workspace\\Croma\\src\\test\\java\\resources\\data.xlsx.xlsx");
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);
        

        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
        	
        	Row row = rowIterator.next();
        	String searchKeyword = row.getCell(0).getStringCellValue();
            test.info("Searching for keyword: " + searchKeyword);
       
        
     //   Row row = sheet.getRow(0);
    //    String searchKeyword = row.getCell(0).getStringCellValue();
        workbook.close();

        // Page Object Model Usage
        HomePage homePage = new HomePage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='searchV2']")));
        homePage.searchProduct(searchKeyword);
        Thread.sleep(3000);

        
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        Thread.sleep(3000);
        searchResultsPage.filterByBrand();
        Thread.sleep(3000);
        searchResultsPage.sortByDiscount();
        
        ProductPage productPage = new ProductPage(driver);
        List<Integer> topPrices = productPage.getTopProductPrices(10);

        double sum = topPrices.stream().mapToInt(Integer::intValue).sum();
        double averagePrice = sum / topPrices.size();
        test.info("Average price of top 10 products: " + averagePrice);

        // Assertion to verify prices were captured
        Assert.assertTrue(topPrices.size() > 0, "Prices were not captured.");
       

        
        test.pass("Prices captured successfully.");

        // Take screenshot
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("test-output/screenshot.png"));

        // Log information
        logger.info("Test completed successfully.");
        
    	
        }
        workbook.close();
    	} catch (Exception e) {
			// TODO: handle exception
    		 test.fail("Test failed due to: " + e.getMessage());
            Assert.fail(e.getMessage());
}
    }
  
    
    @AfterClass
    public void tearDown() {
        driver.quit();
        extent.flush();
    }
   
    }
    

