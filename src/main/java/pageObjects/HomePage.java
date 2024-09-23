package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    WebDriver driver;

    By searchBox = By.xpath("//input[@id='searchV2']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void searchProduct(String product) {
        WebElement search = driver.findElement(searchBox);
        search.sendKeys(product);
        //search.submit();
        search.sendKeys(Keys.ENTER); 
    }
}
