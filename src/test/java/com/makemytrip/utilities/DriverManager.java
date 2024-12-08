package com.makemytrip.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverManager {
    private WebDriver driver1;


    //step 3: instance creation
    public WebDriver createInstance(String browser)
    {
        System.out.println("browser"+browser);
        switch(browser) {
            case "chrome" -> {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options=new ChromeOptions();
//                options.addArguments("user/agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML,like Gecko) Chrome/58.0.3029.110 Safari/537.36");
                options.addArguments("--disable-blink-features=AutomationControlled");
                driver1=new ChromeDriver(options);
                return driver1;
            }
            case "edge" ->{
                WebDriverManager.edgedriver().setup();
                driver1=new EdgeDriver();
                return driver1;
            }
            default -> {
                throw new IllegalArgumentException("browser not valid");
            }
        }
        //step 4: go to Base Class.
    }


    //step 4 cont:
    public void close(WebDriver driver)
    {
//        driver.quit();
    }
    //step 5 go to Mmt_bus_booking



}
