package challenge2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

public class challenge2 {
    public WebDriver driver;
    private WebDriverWait wait;

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
    public void verifyPorscheInExoticSearch() throws Exception {
        driver.get("https://www.copart.com/");
        Assert.assertEquals(driver.getTitle(), "Salvage Cars for Sale | Online Car Auction - Copart USA Auto Auction");
        WebElement input = driver.findElement(By.cssSelector("input[id='input-search']"));
        input.sendKeys("Exotics");
        WebElement submitButton = driver.findElement(By.cssSelector("button[data-uname='homepageHeadersearchsubmit']"));
        submitButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//tr[@role='row'])[2]")));
        String searchResults = driver.findElement(By.id("serverSideDataTable")).getText();
//        Assert.assertEquals(searchResults, "PORSCHE");
        Assert.assertTrue(searchResults.contains("PORSCHE"), "Checking for 'PORSCHE' inside of the search results.");
    }

}

