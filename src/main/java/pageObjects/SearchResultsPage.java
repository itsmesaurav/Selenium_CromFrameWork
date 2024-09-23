package pageObjects;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



public class SearchResultsPage {
    WebDriver driver;
    By branFilter = By.xpath("//*[@id=\"panel1bh-header\"]/div[1]/p");
    By samsungFilter = By.xpath("//*[@id=\"panel1bh-content\"]/div/p/div/ul/li[1]/div/label/span[2]");
    By lgFilter = By.xpath("//*[@id=\"panel1bh-content\"]/div/p/div/ul/li[2]/div/label/span[2]");
    By whirlpoolFilter = By.xpath("//*[@id=\"panel1bh-content\"]/div/p/div/ul/li[3]/div/label/span[2]");
    By sortDropdown = By.className("selected-item");
   

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        
    }

    public void filterByBrand() {
    	driver.findElement(branFilter).click();
        driver.findElement(samsungFilter).click();
        driver.findElement(lgFilter).click();
        driver.findElement(whirlpoolFilter).click();
    }

    public void sortByDiscount() {
        driver.findElement(sortDropdown).click();
        driver.findElement(By.xpath("//*[@id=\"container\"]/div/div[3]/div[1]/div[2]/div/div/div[4]/div[3]/div/div/div[2]/ul/li[6]")).click();
    }
}
