package com.makemytrip.testcases;

import com.makemytrip.utilities.DriverManager;
import com.makemytrip.utilities.PropertyManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;

public class BaseClass {
    private DriverManager driverManager;
    private PropertyManager propertyManager;
    protected String url;
    protected String browser;
    protected WebDriver driver;
    //step 1:
    @BeforeSuite
    public void start() throws IOException {
        System.out.println("Before Suite");
        //step 2: go to PropertyManager()
        propertyManager=new PropertyManager("C:\\Users\\santhosh.krishnan\\Downloads\\Capstone_Project_MakeMyTrip\\src\\test\\resources\\makeMyTrip.properties");
        url=propertyManager.geturl();
        browser=propertyManager.getbrowser();
        System.out.println("browser"+browser);
        //step 3: go to driverManager()
        driverManager=new DriverManager();
        driver=driverManager.createInstance(browser);
    }

    //step 4:go to driver manager
    @AfterSuite
    public void end()
    {
        System.out.println("After Suite");
        if(driver!=null){
            driverManager.close(driver);
        }
    }
}
