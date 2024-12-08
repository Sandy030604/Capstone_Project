package com.makemytrip.pomfactory;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.makemytrip.utilities.ReportManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;
import java.time.Duration;

public class TestCase {
    WebDriver driver;
    WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));

    public TestCase(WebDriver driver){
        this.driver=driver;
    }
    public  void testCase(WebElement element, ExtentTest test, String valid, String invalid, ReportManager reportz) throws IOException {
        wait.until(ExpectedConditions.visibilityOf(element));
        try{
            Assert.assertTrue(element.isDisplayed(),invalid);
            test.log(Status.PASS,valid);
        }catch(Exception e){
            test.log(Status.FAIL,invalid).addScreenCaptureFromPath(reportz.takeScreenShot(driver));
        }
    }

    public void Validatecontact(String phoneno, ExtentTest test,WebElement element,ReportManager reportz) throws IOException {
        wait.until(ExpectedConditions.visibilityOf(element));

        try {
            Assert.assertEquals(phoneno, "7550010537", "Invalid Number");
            test.log(Status.PASS, "Valid Contact Number");
        } catch (AssertionError e) {
            test.log(Status.FAIL, "Invalid Contact Number: " + e.getMessage()).addScreenCaptureFromPath(reportz.takeScreenShot(driver));
        } catch (Exception e) {
            test.log(Status.FAIL, "An error occurred: " + e.getMessage()).addScreenCaptureFromPath(reportz.takeScreenShot(driver));
        }
    }


//    public void testCaseClick(WebElement element, ExtentTest test, String valid, String invalid) {
//        wait.until(ExpectedConditions.elementToBeClickable(element)); // Wait until the element is clickable
//        try {
//            element.click(); // Attempt to click the element
//            Assert.assertTrue(true, valid); // Assert success
//            test.log(Status.PASS, valid); // Log success in extent report
//        } catch (Exception e) {
//            Assert.assertTrue(false, invalid); // Assert failure with a custom message
//            test.log(Status.FAIL, invalid + ": " + e.getMessage()); // Log failure in extent report
//        }
//    }

    public void testCaseClick(WebElement element, ExtentTest test, String valid, String invalid,ReportManager reportz) throws IOException {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element)); // Wait until the element is clickable
            element.click(); // Attempt to click the element
            test.log(Status.PASS, valid); // Log success if the click works
        } catch (Exception e) {
            try {
                // Fallback: Use JavaScript to click if regular click fails
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", element);
                test.log(Status.PASS, valid + " (via JavaScript)"); // Log JavaScript fallback success
            } catch (Exception jsException) {
                test.log(Status.FAIL, invalid + ": " + jsException.getMessage()).addScreenCaptureFromPath(reportz.takeScreenShot(driver)); // Log failure
                Assert.assertTrue(false, invalid); // Fail the test
            }
        }
    }


}
