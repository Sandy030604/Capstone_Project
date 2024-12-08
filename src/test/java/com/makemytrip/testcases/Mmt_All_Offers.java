//package com.makemytrip.testcases;
//
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.reporter.ExtentSparkReporter;
//import com.makemytrip.pomfactory.PomObject;
//import com.makemytrip.testdata.ReadLogin;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.Actions;
//import org.testng.Assert;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
//import java.util.Set;
//import java.util.concurrent.TimeUnit;
//
//public class Mmt_All_Offers extends BaseClass {
//    PomObject mmtPom;
//    ExtentReports extentReports;
//    ExtentSparkReporter extentSparkReporter;
//    JavascriptExecutor js;
//
//    @BeforeClass
//    public void setup() {
//        System.out.println("Before Class");
//        driver.get(url);
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//        //step 6: go to pomobject
//        mmtPom = new PomObject(driver);
//        js = (JavascriptExecutor) driver;
//    }
//
//
//    @BeforeMethod
//    public void setupBefore() {
//        System.out.println("Before Method");
//
//    }
//
//    @Test(dataProvider = "loginz", dataProviderClass = ReadLogin.class, priority = 1)
//    public void userLogin(String phoneno) throws InterruptedException {
//        try {
////            WebElement google= mmtPom.getElementByXpath("//div[@class='nsm7Bb-HzV7m-LgbsSe-Bz112c']");
//////            google.click();
////            js.executeScript("arguments[0].click();", google);
////            Thread.sleep(10000);
////            String mainWindowHandle = driver.getWindowHandle();
////            Set<String> allWindowHandles = driver.getWindowHandles();
////
////            // Loop through all window handles and switch to the new one
////            for (String windowHandle : allWindowHandles) {
////                if (!windowHandle.equals(mainWindowHandle)) {
////                    driver.switchTo().window(windowHandle);
////                    System.out.println("Switched to new window/tab: " + driver.getTitle());
////                    break;
////                }
////            }
////            WebElement gmail= mmtPom.getElementByXpath("(//input[@jsname='YPqjbf'])[1]");
////            gmail.sendKeys("santhoshs8483@gmail.com");
////            WebElement next= mmtPom.getElementByXpath("/html/body/div[1]/div[1]/div[2]/c-wiz/div/div[3]/div/div[1]/div/div/button");
////            next.click();
////            WebElement password= mmtPom.getElementByXpath("//input[@name='Passwd']");
////            password.sendKeys("aaradhana28");
////            WebElement next1= mmtPom.getElementByXpath("(//div[@class='VfPpkd-RLmnJb'])[2]");
////            next1.click();
////            Thread.sleep(5000);
////
////            WebElement username = mmtPom.getElementByXpath("//input[@data-cy='userName'] ");
////
////            System.out.println("---------------------------------------");
////            //username is visible .
////            //Assert eqal
////            boolean a=username.isDisplayed();
//////            Assert.assertTrue(a);
////            username.sendKeys("9384604089");
//////            Assert.assertEquals(phoneno, "9384604089");
////            WebElement continueBtn = mmtPom.getElementByXpath("//button[@data-cy='continueBtn']");
////            continueBtn.click();
////            Thread.sleep(20000);
////            System.out.println("Byee");
////            WebElement loginBtn = mmtPom.getElementByXpath("//button[@data-cy='login']");
////            loginBtn.click();
////            System.out.println("zzzz");
////            Thread.sleep(5000);
////        }
////        catch(Exception e)
////        {
////            Assert.fail("Phone number is not valid");
////        }
//            WebElement username = mmtPom.getElementByXpath("//input[@data-cy='userName'] ");
//            //username is visible .
//            //Assert eqal
//            boolean a = username.isDisplayed();
//            Assert.assertTrue(a);
//            username.sendKeys(phoneno);
//            Assert.assertEquals(phoneno, "7550010537");
//            WebElement continueBtn = mmtPom.getElementByXpath("//button[@data-cy='continueBtn']");
//            continueBtn.click();
//            Thread.sleep(20000);
//            System.out.println("Byee");
//            WebElement loginBtn = mmtPom.getElementByXpath("//button[@data-cy='login']");
//            loginBtn.click();
//            System.out.println("zzzz");
//            Thread.sleep(5000);
//        } catch (Exception e) {
//            Assert.fail("Phone number is not valid");
//        }
//    }
//
//    @Test(priority = 2)
//    public void AllOffers() throws InterruptedException {
//        System.out.println("Hiii");
//        Thread.sleep(3000);
//        js.executeScript("window.scrollBy(0,600)");
//        Thread.sleep(3000);
//        WebElement Alloffers= mmtPom.getElementByXpath("//li[@data-cy='ALL']");
//        Alloffers.click();
//        WebElement booknow= mmtPom.getElementByXpath("//a[@data-cy='superOfferCtaText2']");
//        booknow.click();
//        Thread.sleep(3000);
//        js.executeScript("window.scrollBy(0,400)");
//        Thread.sleep(3000);
//        WebElement Hotelz= mmtPom.getElementByXpath("//*[text()='BOOK ABU DHABI HOTELS']");
////        Actions actions=new Actions(driver);
////        actions.moveToElement(mmtPom.getElementByXpath("//*[text()='BOOK ABU DHABI HOTELS']"))
//        js.executeScript("window.scrollBy(0,400)");
//        WebElement Hotels= mmtPom.getElementByXpath("//*[text()='BOOK ABU DHABI HOTELS']");
//        Hotels.click();
//    }
//}
