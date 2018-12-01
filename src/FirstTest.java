import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class FirstTest
{
    private AppiumDriver driver;

    @Before

    public void setUp() throws Exception

    {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","6.0");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","/Users/SOLOVYEVA/Desktop/JavaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown()
    {
        driver.quit();
    }


   @Test
    public  void TestCompareSearchLabel()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find element",
                5

        );

        WebElement search_label = waitForElementPresent(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search element",
                5
        );



        String element_label = search_label.getAttribute("text");
        Assert.assertEquals(
                "We see unexpected label",
                "Search…",
                element_label
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains (@text, 'Search…')]"),
                "Java",
                "Cannot find element",
                5

        );



    }

    @Test
    public void TestCancelSearch()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' element",
                5

        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains (@text, 'Search…')]"),
                "iOS",
                "Cannot find search element",
                5

        );

        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Mobile operating system by Apple']"),
                "Cannot find topic 'iOS' searching by iOS",
                15
        );

        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Eleventh major release of iOS, the mobile operating system by Apple Inc.']"),
                "Cannot find topic 'iOS 11' searching by iOS",
                10
        );

        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Tenth major release of iOS, the mobile operating system by Apple Inc.']"),
                "Cannot find topic 'iOS 10' searching by iOS",
                5
        );



        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X for cancel searching results",
                15
        );

        waitForElementPresent(
                By.id("org.wikipedia:id/search_empty_message"),
                "Cannot find search empty element",
                5
        );

    }


    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds)
{
    WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
    wait.withMessage(error_message + "\n");
    return wait.until(
            ExpectedConditions.presenceOfElementLocated(by)
    );
}
    private WebElement waitForElementPresent(By by, String error_message)
    {
        return waitForElementPresent(by, error_message, 5);
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeOutAndSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeOutAndSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeOutAndSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeOutAndSeconds);
        element.sendKeys(value);
        return element;
    }



}
