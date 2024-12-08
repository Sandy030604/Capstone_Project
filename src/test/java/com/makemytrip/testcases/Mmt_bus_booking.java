package com.makemytrip.testcases;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.makemytrip.pomfactory.PomObject;
import com.makemytrip.pomfactory.TestCase;
import com.makemytrip.testdata.ReadLogin;
import com.makemytrip.utilities.ReportManager;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class Mmt_bus_booking extends BaseClass{
    PomObject mmtPom;
    ExtentReports extentReports;
    ExtentSparkReporter extentSparkReporter;
    ReportManager report;
    TestCase test;
    @Getter
    @Setter
    String phoneno;
    JavascriptExecutor js;
    @BeforeClass
    public void setup()
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

        mmtPom=new PomObject(driver);
        js=(JavascriptExecutor) driver;
        test=new TestCase(driver);
    }
 @BeforeMethod
    public void setupBefore()
    {
        System.out.println("Before Method");

    }
    @Test(dataProvider = "loginz",dataProviderClass = ReadLogin.class,priority = 1)
    public void userLogin(String phoneno) throws InterruptedException, IOException {

        ExtentTest loginTestCase = report.createTestCase("Login","To verify the working of the login credentials");
        try {
            WebElement username = mmtPom.getElementByXpath("//input[@data-cy='userName'] ");
            username.sendKeys(phoneno);
            test.Validatecontact("7550022233",loginTestCase,username,report);
            report.passTestCase(loginTestCase,"Username Validated");
            WebElement continueBtn = mmtPom.getElementByXpath("//button[@data-cy='continueBtn']");
            test.testCase(continueBtn,loginTestCase,"Continue Button is Present","Continue Button is not present",report);
            continueBtn.click();
            Thread.sleep(20000);
            System.out.println("Byee");
            WebElement loginBtn = mmtPom.getElementByXpath("//button[@data-cy='login']");
            loginBtn.click();
            System.out.println("zzzz");
            Thread.sleep(5000);
            loginTestCase.log(Status.PASS,"hello");
        }
        catch(Exception e)
        {
            Assert.fail("Phone number is not valid");
            report.failTestCase(loginTestCase,driver,"Login failed","Phone number does not meet actual data");
        }
    }
    @Test(priority = 2)
    public void selection() {
        ExtentTest SelectionCase = report.createTestCase("selection", "To verify the selection of the Bus Booking page");
        try {
            WebElement Bus = mmtPom.getElementByXpath("(//span[@data-cy=\"item-wrapper\"])[6]");
            test.testCase(Bus, SelectionCase, "Bus button is present", "Bus button is not present",report);
            test.testCaseClick(Bus, SelectionCase, "Filter button clicked successfully", "Failed to click the filter button",report);

            WebElement Search = mmtPom.getElementByXpath("//button[@data-cy='submit']");
            test.testCase(Search, SelectionCase, "Search button is present", "Search button is not present",report);
            test.testCaseClick(Search, SelectionCase, "Search button clicked successfully", "Failed to click the search button",report);
        } catch (Exception e) {
            SelectionCase.log(Status.FAIL, "An exception occurred: " + e.getMessage());
        }
    }
    @Test(priority = 3)
    public void filters()
    {
        ExtentTest filterstestCase = report.createTestCase("filter", "To verify the filteration of the Bus Booking page");
        try{
            WebElement Ac = mmtPom.getElementByXpath("(//div[@class='makeFlex']/div)[1]");
            test.testCase(Ac, filterstestCase, "AC filter is present", "AC filter is not present",report);
            test.testCaseClick(Ac, filterstestCase, "AC filter clicked successfully", "Failed to click the AC filter",report);
            WebElement Seattype = mmtPom.getElementByXpath("(//div[@class='makeFlex']/div)[3]");
            test.testCase(Seattype, filterstestCase, "Seat type filter is present", "Seat type filter is not present",report);
            test.testCaseClick(Seattype, filterstestCase, "Seat type filter clicked successfully", "Failed to click the seat type filter",report);
        } catch (Exception e) {
            filterstestCase.log(Status.FAIL, "An exception occurred: " + e.getMessage());
        }
    }
    @Test(priority = 4)
    public void confirmation() {
        ExtentTest confirmationTest = report.createTestCase("Confirmation", "To verify the bus seat booking confirmation process");
        try {
            WebElement selectSeat = mmtPom.getElementByXpath("(//div[@data-test-id='select-seats'])[1]");
            test.testCase(selectSeat, confirmationTest, "Select seat button is present", "Select seat button is not present",report);
            test.testCaseClick(selectSeat, confirmationTest, "Select seat button clicked successfully", "Failed to click the select seat button",report);

            WebElement seatBooking = mmtPom.getElementByXpath("(//span[@data-testid='seat_horizontal_sleeper_available'])[1]");
            test.testCase(seatBooking, confirmationTest, "Seat booking option is present", "Seat booking option is not present",report);
            test.testCaseClick(seatBooking, confirmationTest, "Seat booking option clicked successfully", "Failed to click the seat booking option",report);

            js.executeScript("window.scrollBy(0,900)");

            WebElement continues = mmtPom.getElementByXpath("(//div[@class='summaryContainer'])/div[2]");
            test.testCase(continues, confirmationTest, "Continue button is present", "Continue button is not present",report);
            test.testCaseClick(continues, confirmationTest, "Continue button clicked successfully", "Failed to click the continue button",report);
        } catch (Exception e) {
            confirmationTest.log(Status.FAIL, "An exception occurred: " + e.getMessage());
            Assert.fail("Test failed due to an exception: " + e.getMessage());
        }
    }
    @Test(priority = 5)
    public void details()
    {
        ExtentTest DetailsTest = report.createTestCase("Details", "To verify the bus seat booking details process");
        try{
            WebElement name = mmtPom.getElementByXpath("//input[@id='fname']");
            test.testCase(name, DetailsTest, "Name input field is present", "Name input field is not present",report);
            name.sendKeys("Santhosh");
            DetailsTest.log(Status.PASS, "Name entered successfully");

            WebElement age = mmtPom.getElementByXpath("//input[@id='age']");
            test.testCase(age, DetailsTest, "Age input field is present", "Age input field is not present",report);
            age.sendKeys("24");
            DetailsTest.log(Status.PASS, "Age entered successfully");

            WebElement gender = mmtPom.getElementByXpath("(//div[contains(@class,'male')])[1]");
            test.testCase(gender, DetailsTest, "Gender option is present", "Gender option is not present",report);
            test.testCaseClick(gender, DetailsTest, "Gender option clicked successfully", "Failed to click the gender option",report);

            WebElement email = mmtPom.getElementByXpath("//input[@type='email']");
            test.testCase(email, DetailsTest, "Email input field is present", "Email input field is not present",report);
            email.sendKeys("santhoshs8483@gmail.com");
            DetailsTest.log(Status.PASS, "Email entered successfully");

            WebElement phoneNo = mmtPom.getElementByXpath("//input[@id='mobileNumber']");
            test.testCase(phoneNo, DetailsTest, "Phone number input field is present", "Phone number input field is not present",report);
            phoneNo.sendKeys("7550022233");
            DetailsTest.log(Status.PASS, "Phone number entered successfully");

            System.out.println("Number Entered");
            js.executeScript("window.scrollBy(0,500)");

            WebElement tripAssured = mmtPom.getElementByXpath("/html/body/div/div[4]/div/section[1]/div[4]/div/div[3]/div[2]/span[1]");
            test.testCase(tripAssured, DetailsTest, "Trip assured checkbox is present", "Trip assured checkbox is not present",report);
            test.testCaseClick(tripAssured, DetailsTest, "Trip assured checkbox clicked successfully", "Failed to click the trip assured checkbox",report);
        }
        catch (Exception e) {
            DetailsTest.log(Status.FAIL, "An exception occurred: " + e.getMessage());
            Assert.fail("Test failed due to an exception: " + e.getMessage());
        }
    }
    @Test(priority = 6)
    public void payment()
    {
        ExtentTest PaymentTest = report.createTestCase("Payment", "To verify the bus seat booking payment process");
        try{
            WebElement paymentCont = mmtPom.getElementByXpath("/html/body/div/div[4]/div/section[2]/div[2]/div[2]/div[2]");
            test.testCase(paymentCont, PaymentTest, "Payment continue button is present", "Payment continue button is not present",report);
            test.testCaseClick(paymentCont, PaymentTest, "Payment continue button clicked successfully", "Failed to click the payment continue button",report);

        }
        catch (Exception e) {
            PaymentTest.log(Status.FAIL, "An exception occurred: " + e.getMessage());
            Assert.fail("Test failed due to an exception: " + e.getMessage());
        }
    }
    @AfterClass
    public void dfdf()
    {
        report.closeReport();
    }
}
