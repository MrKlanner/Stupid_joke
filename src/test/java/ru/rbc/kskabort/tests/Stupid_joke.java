package ru.rbc.kskabort.tests;

import com.google.common.collect.ImmutableMap;
import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.Command;
import org.openqa.selenium.remote.CommandExecutor;
import org.openqa.selenium.remote.Response;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.rbc.kskabort.DriverSetting;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Stupid_joke {

    private static ChromeDriver driver;
    private boolean response;
    private boolean p;

    @BeforeTest
    public void setup() throws IOException {
        System.setProperty("webdriver.chrome.driver", "D:/Users/Kosta/Documents/webdrivers/chrome_driver/chromedriver.exe");
        //driver = new ChromeDriver();
        driver = DriverSetting.CreateDriver("spec", driver);
        DriverSetting.SetNetConnection(50000, 50000, 5, driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
    }

    @Test
    public void main_test()
    {
        driver.get("https://rbc.ru");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        try {response = (boolean) js.executeScript("function T(placeName) {" +
                "var ret = false;" +
                "if (RA.repo.banner) {" +
                "var place = RA.repo.banner.getService('dfp').getPlaces(placeName);" +
                "if (place.isDisplayed && !place.isCleared) ret = true;" +
                "}" +
                "return ret;}" +
                "return T(\"right_1\");");}
        catch (Exception c)
        {System.out.println(c);}
        if (response) {
            p = (boolean) js.executeAsyncScript("var p = false;" +
                    "RA.repo.banner.addEventListener('creativeShow', function(){window.p = true;}, 'right_1', null, 'dfp');"+
                    "return p");
            String error=(String) js.executeScript("return window.jsErrors");
            System.out.println("Windowerrors  :   "+error);
        }

        /*var p = false;
        RA.repo.banner.addEventListener('creativeShow', function(){window.p = true; console.log("Is Banner are loading - "+p)}, 'right_1', null, 'dfp');*/
    }

    @AfterTest
    public void after(){
        driver.quit();
    }
}
