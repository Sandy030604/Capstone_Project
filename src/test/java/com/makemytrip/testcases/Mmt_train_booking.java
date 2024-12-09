package com.makemytrip.testcases;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.makemytrip.pomfactory.PomObject;
import com.makemytrip.pomfactory.TestCase;
import com.makemytrip.testdata.ReadLogin;
import com.makemytrip.utilities.ReportManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class Mmt_train_booking extends BaseClass{
    PomObject mmtPom;
    ExtentReports extentReports;
    ExtentSparkReporter extentSparkReporter;
    ReportManager report;
    TestCase test;

    JavascriptExecutor js;

    @BeforeClass()
    public void setzz()
    {
        System.out.println("Before Class");
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        extentReports = new ExtentReports();
        String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
        extentSparkReporter = new ExtentSparkReporter("C:\\Users\\santhosh.krishnan\\Downloads\\Capstone_Project_MakeMyTrip\\src\\test\\extentreport\\report_"+dateTime+".html");

        report = new ReportManager(extentReports,extentSparkReporter);

        report.initiateReport("Test Automation","Capstone MakeMyTrip");


        //step 6: go to pomobject
        mmtPom=new PomObject(driver);
        js=(JavascriptExecutor) driver;

        test=new TestCase(driver);
    }

   @Test(priority = 1)
   public void login() throws IOException {
       ExtentTest loginTrainTestCase = report.createTestCase("Logintrain","To verify the working of the login credentials");
       try {
           WebElement username = mmtPom.getElementByXpath("//input[@data-cy='userName'] ");

           username.sendKeys("7550010537");
           test.Validatecontact("938460489",loginTrainTestCase,username,report);
           report.passTestCase(loginTrainTestCase,"Username Validated");
           WebElement continueBtn = mmtPom.getElementByXpath("//button[@data-cy='continueBtn']");
           test.testCase(continueBtn,loginTrainTestCase,"Continue Button is Present","Continue Button is not present",report);
           continueBtn.click();
           Thread.sleep(20000);
           System.out.println("Byee");
           WebElement loginBtn = mmtPom.getElementByXpath("//button[@data-cy='login']");
           loginBtn.click();
           System.out.println("zzzz");
           Thread.sleep(5000);
           loginTrainTestCase.log(Status.PASS,"hello");
       }
       catch(Exception e)
       {

           Assert.fail("Phone number is not valid");
           report.failTestCase(loginTrainTestCase,driver,"Login failed","Phone number does not meet actual data");
       }
   }





    @Test(priority = 2)
    public void Search_Train() {
        ExtentTest trainSearchTest = report.createTestCase("Train Search", "To verify the search of train is working");

        try {
            // Verify Train tab presence and click it
            WebElement trainTab = mmtPom.getElementByXpath("(//span[@data-cy='item-wrapper'])[5]");
            test.testCase(trainTab, trainSearchTest, "Train tab is present", "Train tab is not present",report);
            test.testCaseClick(trainTab, trainSearchTest, "Train tab clicked successfully", "Failed to click the Train tab",report);

            // Verify Search button presence and click it
            WebElement searchButton = mmtPom.getElementByXpath("//a[@data-cy='submit']");
            test.testCase(searchButton, trainSearchTest, "Search button is present", "Search button is not present",report);
            test.testCaseClick(searchButton, trainSearchTest, "Search button clicked successfully", "Failed to click the Search button",report);



        } catch (Exception e) {
            trainSearchTest.log(Status.FAIL, "An exception occurred: " + e.getMessage());
            Assert.fail("Test failed due to an exception: " + e.getMessage());
        }
    }



    @Test(priority = 3)
    public void Trainfilters() {
        ExtentTest trainFilterTestCase = report.createTestCase("Train Filter", "To verify the filter of train is working");

        try {
            // Verify AC Train filter presence and apply it
            WebElement acTrainFilter = mmtPom.getElementByXpath("/html/body/div/div[2]/main/div[3]/div[1]/div[1]/div[1]/div[2]/ul/li[1]/label/div");
            test.testCase(acTrainFilter, trainFilterTestCase, "AC Train filter is present", "AC Train filter is not present",report);
            test.testCaseClick(acTrainFilter, trainFilterTestCase, "AC Train filter clicked successfully", "Failed to click the AC Train filter",report);

            // Verify Availability filter presence and apply it
            WebElement availabilityFilter = mmtPom.getElementByXpath("/html/body/div/div[2]/main/div[3]/div[1]/div[1]/div[2]/div[2]/ul/li[1]/label/div");
            test.testCase(availabilityFilter, trainFilterTestCase, "Availability filter is present", "Availability filter is not present",report);
            test.testCaseClick(availabilityFilter, trainFilterTestCase, "Availability filter clicked successfully", "Failed to click the Availability filter",report);

        } catch (Exception e) {
            trainFilterTestCase.log(Status.FAIL, "An exception occurred: " + e.getMessage());
            Assert.fail("Test failed due to an exception: " + e.getMessage());
        }
    }



    @Test(priority = 4)
    public void TrainSelection() {
        ExtentTest trainSelectionTestCase = report.createTestCase("Train Selection", "To verify the selection of a train is working");

        try {
            // Verify Train Selection element presence and click it
            js.executeScript("window.scrollBy(0,300)");
            WebElement trainSelect = mmtPom.getElementByXpath("/html/body/div/div[2]/main/div[3]/div[1]/div[2]/div[4]/div[1]/div/div[2]/div/div/div/a");
            test.testCase(trainSelect, trainSelectionTestCase, "Train selection element is present", "Train selection element is not present",report);
            test.testCaseClick(trainSelect, trainSelectionTestCase, "Train selection element clicked successfully", "Failed to click the train selection element",report);

        } catch (Exception e) {
            trainSelectionTestCase.log(Status.FAIL, "An exception occurred: " + e.getMessage());
            Assert.fail("Test failed due to an exception: " + e.getMessage());
        }
    }



    @Test(priority = 5)
    public void TrainRefundSelection() {
        ExtentTest trainRefundTestCase = report.createTestCase("Train Refund", "To verify the refund selection of a train is working");

        try {
            // Verify Refund Selection element presence and click it
            WebElement refund = mmtPom.getElementByXpath("/html/body/div[1]/div/div[2]/div[2]/div/section[1]/div[2]/div/div[2]/div[1]/div/label/div");
            test.testCase(refund, trainRefundTestCase, "Refund selection element is present", "Refund selection element is not present",report);
            test.testCaseClick(refund, trainRefundTestCase, "Refund selection element clicked successfully", "Failed to click the refund selection element",report);

            trainRefundTestCase.log(Status.PASS, "Refund option clicked successfully");
            System.out.println("Refund clicked");

        } catch (Exception e) {
            trainRefundTestCase.log(Status.FAIL, "An exception occurred: " + e.getMessage());
            Assert.fail("Test failed due to an exception: " + e.getMessage());
        }
    }


    @Test(priority = 6,dataProvider = "detailz",dataProviderClass = ReadLogin.class)
    public void AddTravellerTrain(String nameInput, String ageInput, String emailInput, String phoneInput)
    {
        ExtentTest AddTravellerTestCase = report.createTestCase("Train Traveller", "To verify the train traveller selection of a train is working");
        WebElement AddTraveller= mmtPom.getElementByXpath("/html/body/div[1]/div/div[2]/div[2]/div/section[1]/div[3]/div[2]/div/a");
        AddTraveller.click();
        WebElement TravellerName= mmtPom.getElementByXpath("//input[@id='name']");
        TravellerName.sendKeys(nameInput);
        WebElement TravellerAge= mmtPom.getElementByXpath("//input[@id='age']");
        TravellerAge.sendKeys(ageInput);
        WebElement GenderTrain= mmtPom.getElementByXpath("(//div[@role='button'])[5]");
        GenderTrain.click();
        WebElement GenderMale= mmtPom.getElementByXpath("/html/body/div[5]/div/div/div/li/div[3]/div[2]/div/form/div[3]/div/ul/li[1]");
        GenderMale.click();
        WebElement Continue= mmtPom.getElementByXpath("//button[normalize-space()='Add']");
        Continue.click();
        WebElement emailtrain= mmtPom.getElementByXpath("//input[@type='email']");
        emailtrain.sendKeys(emailInput);
        WebElement phonetrain=mmtPom.getElementByXpath("(//input[@type='text'])[2]");
        phonetrain.sendKeys(phoneInput);



    }

    @AfterClass
    public void dfdf()
    {
        report.closeReport();
    }







}
