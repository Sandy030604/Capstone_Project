package com.makemytrip.pomfactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PomObject {

    //step 6:
    WebDriver driver;
    public PomObject(WebDriver driver) {
        this.driver=driver;
    }

    public WebElement getElementByXpath(String xpath)
    {
        WebElement driverz=driver.findElement(By.xpath(xpath));
        return driverz;
    }


}
