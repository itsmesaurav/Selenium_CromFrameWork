package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FileUpload {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		        // Set the path to your WebDriver executable
		        //System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

		        // Initialize WebDriver
		        WebDriver driver = new ChromeDriver();

		        try {
		           
		        	// Navigate to the SmallPDF JPG to PDF page
		            driver.get("https://smallpdf.com/jpg-to-pdf");

		            // Locate the file input element
		            WebElement fileInput = driver.findElement(By.xpath("//input[@type='file']"));

		            // Provide the path to the file you want to upload
		            // Update this to the specific file you want to upload, not just the directory
		            String filePath = "C:\\Users\\user\\Desktop\\pexels-jonaskakaroto-736230.jpg"; // Update this path
		            fileInput.sendKeys(filePath);

		            // Wait for some time to see the upload process (optional)
		            Thread.sleep(5000);

		        } catch (InterruptedException e) {
		            e.printStackTrace();
		        } 
		   

	}

}
