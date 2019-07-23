package ru.rbc.kskabort.tests;

import com.sun.org.apache.xml.internal.utils.res.StringArrayWrapper;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.rbc.kskabort.Just_for_trottling;

import java.awt.*;
import java.text.StringCharacterIterator;
import java.util.concurrent.TimeUnit;

public class Stupid_joke {

    private static WebDriver driver;
    private boolean response;
    private boolean p;
    private String NameOfTrottle = "Test";
    private String DownSpeed = "20";
    private String UploadSpeed = "20";
    private String Latency = "7";

    @BeforeTest
    public void setup() throws AWTException, InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:/Users/Kosta/Documents/webdrivers/chrome_driver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
        Just_for_trottling.SetTrottling(NameOfTrottle, DownSpeed, UploadSpeed, Latency, driver);
    }

    @Test
    public void main_test()
    {
        driver.get("https://test.rbc.ru");
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
        if (!response) {
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
