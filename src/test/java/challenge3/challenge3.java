package challenge3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.util.List;

public class challenge3 {
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
    public void printListOfPopularItems() throws Exception {
        driver.get("https://www.copart.com/");
       // Assert.assertEquals(driver.getTitle(), "100% Online Auto Auction - Copart USA - Salvage Cars for Sale in Online Car Auctions");
        List<WebElement> popularItems = driver.findElements(By.xpath("(//div[@class='row'])[6]//a"));
        for (WebElement item : popularItems) {
            System.out.println(item.getText());
            System.out.println(item.getAttribute("href"));
        }
    }

    @Test()
    public void printListOfCategories() throws Exception {
        driver.get("https://www.copart.com/");
       // Assert.assertEquals(driver.getTitle(), "Salvage Cars for Sale | Online Car Auction - Copart USA Auto Auction");
        List<WebElement> categories = driver.findElements(By.xpath("(//div[@class='row'])[8]//a"));
        for (WebElement category : categories) {
            System.out.println(category.getText());
            System.out.println(category.getAttribute("href"));
        }
    }

}

