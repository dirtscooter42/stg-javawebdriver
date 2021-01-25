package challenge5;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class challenge5 {
    public WebDriver driver;
    private WebDriverWait wait;
    public static<K> void increment(Map<K, Integer> map, K key) {
        map.putIfAbsent(key, 0);
        map.put(key, map.get(key) +1);
    }

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
//        Assert.assertEquals(driver.getTitle(), "Online Car Auction | Salvage Cars for Sale - Copart USA Auto Auction");
        WebElement input = driver.findElement(By.cssSelector("input[id='input-search']"));
        input.sendKeys("Porsche");
        WebElement submitButton = driver.findElement(By.cssSelector("button[data-uname='homepageHeadersearchsubmit']"));
        submitButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//tr[@role='row'])[2]")));
        String searchResults = driver.findElement(By.id("serverSideDataTable")).getText();
        Assert.assertTrue(searchResults.contains("PORSCHE"), "Checking for 'PORSCHE' inside of the search results.");
        WebElement showEntries = driver.findElement(By.xpath("//*[@id='serverSideDataTable_length']/label/select//option[3]"));
        showEntries.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='serverSideDataTable']/tbody/tr[100]")));

        List<WebElement> modelsOfPorsche = driver.findElements(By.xpath(("//*[@id='serverSideDataTable']/tbody/tr/td[6]")));
        HashMap<String, Integer> mapList = new HashMap<>();

        for (WebElement model: modelsOfPorsche) {
            String nameOfModel = model.getText();
            mapList.putIfAbsent(nameOfModel, 0);
            mapList.put(nameOfModel,mapList.get(nameOfModel) + 1);
        }

        System.out.println("\n*********************\nVehicles by model\n*********************");
        for (String nameOfModel : mapList.keySet()){
            System.out.println(nameOfModel + " - " + mapList.get(nameOfModel));
        }
        System.out.println("*********************");

        List<WebElement> damageToPorsches = driver.findElements(By.xpath(("//*[@id='serverSideDataTable']/tbody/tr/td[12]")));
        HashMap<String, Integer> mapDamage = new HashMap<>();

        for (WebElement damage: damageToPorsches) {
            ArrayList<String> damageTypes = new ArrayList<>();
            damageTypes.add("REAR END");
            damageTypes.add("FRONT END");
            damageTypes.add("MINOR DENT/SCRATCHES");
            damageTypes.add("UNDERCARRIAGE");
            String damageString = damage.getText();
            if(damageTypes.contains(damageString)){
                mapDamage.putIfAbsent(damageString, 0);
                mapDamage.put(damageString,mapDamage.get(damageString) + 1);
            }else{
                mapDamage.putIfAbsent("MISC", 0);
                mapDamage.put("MISC",mapDamage.get("MISC") + 1);
            }
//            switch(damageString){
//                case "REAR END":
//                    mapDamage.putIfAbsent(damageString, 0);
//                    mapDamage.put(damageString,mapDamage.get(damageString) + 1);
//                    break;
//                case "FRONT END":
//                    mapDamage.putIfAbsent(damageString, 0);
//                    mapDamage.put(damageString,mapDamage.get(damageString) + 1);
//                    break;
//                case "MINOR DENT/SCRATCHES":
//                    mapDamage.putIfAbsent(damageString, 0);
//                    mapDamage.put(damageString,mapDamage.get(damageString) + 1);
//                    break;
//                case "UNDERCARRIAGE":
//                    mapDamage.putIfAbsent(damageString, 0);
//                    mapDamage.put(damageString,mapDamage.get(damageString) + 1);
//                    break;
//                default:
//                    mapDamage.putIfAbsent("MISC", 0);
//                    mapDamage.put("MISC",mapDamage.get("MISC") + 1);
//            }
        }
        System.out.println("\n*********************\nDamage counts by type\n*********************");
        for (String damageString : mapDamage.keySet()){
            System.out.println(damageString + " - " + mapDamage.get(damageString));
        }
        System.out.println("*********************");
    }
}

//For this challenge, go to https://www.copart.com and do a search for “porsche” and change the  drop down for “Show
// Entries” to 100 from 20.  Count how many different models of porsche is in the results on the first page and
// return in the terminal how many of each type exists.
//
//        Possible values can be “CAYENNE S”, “BOXSTER S”, etc.
//
//        *hint:  Look at adding every model into an array and then sort the array and count.
//
//        For the 2nd part of this challenge, create a switch statement to count the types of damages.
//        Here’s the types:
//        REAR END
//        FRONT END
//        MINOR DENT/SCRATCHES
//        UNDERCARRIAGE
//        And any other types can be grouped into one of MISC.

//    *****hashmap******

