package challenge6;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.io.File;

public class challenge6 {
    public WebDriver driver;
    private WebDriverWait wait;
    public TakesScreenshot scrShot =((TakesScreenshot)driver);

    @BeforeSuite
    public void startSuite() throws Exception {
    }

    @AfterSuite
    public void stopSuite() throws Exception {
        System.out.println("All done!!!");
    }

    @BeforeClass
    public void startClass() throws Exception {
        System.setProperty("webdriver.chrome.driver", "./bin/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 20);
    }

    @AfterClass
    public void stopSelenium() {
        driver.quit();
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() throws Exception {
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
    }

    @Test()
    public void challenge6() throws Exception {
        driver.get("https://www.copart.com/");
        WebElement searchInput = driver.findElement(By.cssSelector("input[id='input-search']"));
        searchInput.sendKeys("Nissan");
        WebElement submitButton = driver.findElement(By.cssSelector("button[data-uname='homepageHeadersearchsubmit']"));
        submitButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Model')]")));
        WebElement modelText = driver.findElement(By.xpath("//a[contains(text(),'Model')]"));
        modelText.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"collapseinside4\"]/form/div/input")));
        WebElement modelInput = driver.findElement(By.xpath("//*[@id=\"collapseinside4\"]/form/div/input"));
        modelInput.sendKeys("Skyline");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='lot_model_descSKYLINE']")));

        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='lot_model_descSKYLINE']")));
        }
        catch (Exception e) {
            TakesScreenshot scrShot=(TakesScreenshot)driver;
            File source=scrShot.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File("./Screenshots/copart.png"));
            System.out.println("Screenshot taken!");
        }
    }
}


