package pageObjects;

import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductPage {
    WebDriver driver;

    By productPrices = By.className("plp-srp-new-amount");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<Integer> getTopProductPrices(int limit) {
        List<WebElement> prices = driver.findElements(productPrices)
                                        .stream()
                                        .limit(limit)
                                        .collect(Collectors.toList());
        return prices.stream()
                     .map(e -> Integer.parseInt(e.getText().replaceAll("[^0-9]", "")))
                     .collect(Collectors.toList());
    }
}
