package ru.rbc.kskabort.tests;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Stupid_joke {

    private static WebDriver driver;
    private Object response;

    @BeforeTest
    public void setup()
    {
        System.setProperty("webdriver.chrome.driver", "C:/Users/kskabort/Documents/webdrivers/chrome_driver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void main_test()
    {
        driver.get("test.rbc.ru");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        response = js.executeScript("return console.log('I logged something to the Javascript console');");
    }

    @AfterTest
    public void after(){
        driver.quit();
    }
}
