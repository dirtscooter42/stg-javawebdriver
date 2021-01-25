package challenge7;

import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;

public class challenge7 {

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
    public void makesLinkVerification() throws Exception {
        driver.get("https://www.copart.com/");
        ArrayList<WebElement> makesLinks = (ArrayList<WebElement>) driver.findElements(By.cssSelector(".make-items>span>a"));

        ArrayList<ArrayList<String>> makesList = new ArrayList(2);
        makesList.add(new ArrayList<>());
        makesList.add(new ArrayList<>());

        for (WebElement makes : makesLinks) {
            makesList.get(0).add(makes.getText());
            makesList.get(1).add(makes.getAttribute("href"));
        }

        for (int i = 0; i < makesList.get(0).size()-1; i++) {
            driver.get(makesList.get(1).get(i));
            By firstItemMake = By.xpath("(//span[@data-uname='lotsearchLotmake'])[1]");
            wait.until(ExpectedConditions.visibilityOfElementLocated(firstItemMake));
            Assert.assertTrue(driver.findElement(firstItemMake).getText().toLowerCase().contains(makesList.get(0).get
                    (i).toLowerCase()), "The " + "element contains the make as expected.");
        }
    }
}

// For this challenge, take a look at https://www.copart.com main page.  Go to the Makes/Models
// section of the page.  Create a 2 dimensional array or arraylist that stores all the values
// displayed on the page along w/ the URL for that link.  Once you have this array, you can verify
// all the elements in the array navigates to the correct page.  To get started, inspect the code
// and notice the section of the page is built using angular.  There is no static id or element
// class that identifies each element in this section.  Everything is generic.  The only way to
// build a function/object for this section is to loop through each element.  Hint: xpath is
// easiest.  ***Note, you did part of this in challenge 3. ***
