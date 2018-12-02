import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

public class FirstTest
{
    private AppiumDriver driver;

    @Before

    public void setUp() throws Exception

    {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","7.0");
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

    public void saveTwoArticlesToMyList()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find element",
                5

        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains (@text, 'Search…')]"),
                "Java",
                "Cannot find element",
                5

        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find topic 'Object-oriented programming language' searching by Java",
                5
        );

        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article",
                15
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button to open article options",
                5

        );

        Assert.assertTrue("Cannot find option to add article to reading list", !driver.findElements( By.xpath("//*[@text='Add to reading list']")).isEmpty());

        waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
        "Cannot find option to add article to reading list",
        5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find 'Got it tip overlay",
                5
        );
        waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find input to set name of articles folder",
                5
        );

        String name_of_folder = "Learning programming";

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Cannot put text into articles folder input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Cannot press OK button",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article, cannot find X button",
                5

        );



        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find element",
                5

        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains (@text, 'Search…')]"),
                "Java",
                "Cannot find element",
                5

        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Set of several computer software products and specifications']"),
                "Cannot find topic 'Set of several computer software products and specifications' searching by Java",
                5
        );

        WebElement title_second_article = waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article",
                15
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button to open article options",
                5

        );

        Assert.assertTrue("Cannot find option to add article to reading list", !driver.findElements( By.xpath("//*[@text='Add to reading list']")).isEmpty());

        waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Cannot find option to add article to reading list",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='" + name_of_folder + "']"),
                "Cannot find folder for add this article",
                15
        );





        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article, cannot find X button",
                5

        );




        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find navigation button to my list",
                5

        );

        Assert.assertTrue("Cannot find created folder", !driver.findElements( By.xpath("//*[@text='" + name_of_folder + "']")).isEmpty());

        waitForElementAndClick(
                By.xpath("//*[@text='" + name_of_folder + "']"),
                "Cannot find created folder",
                10

        );

        swipeElementToLeft(
                By.xpath("//*[@text='Java (programming language)']"),
                "Cannot find saved article"



        );


       Assert.assertTrue("Cannot find saved article", !driver.findElements(By.xpath("//*[@text='Java (software platform)']")).isEmpty());

        waitForElementAndClick(
                By.xpath("//*[@text='Java (software platform)']"),
                "Cannot find topic 'Set of several computer software products and specifications' searching by Java",
                10
        );

        WebElement title_saved_article = waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article",
                15
        );

        String article_title_before = title_second_article.getAttribute("text");
        String article_title_after = title_saved_article.getAttribute("text");
        Assert.assertEquals(
                "We see unexpected title",
                article_title_before,
                article_title_after
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



    private boolean waitForElemmentNotPresent(By by, String error_message, long timeOutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(error_message +"\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeOutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeOutInSeconds);
        element.clear();
        return element;

    }

    protected void swipeUp(int timeOfSwipe)
    {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);

        action.press(x, start_y).waitAction(timeOfSwipe).moveTo(x, end_y).release().perform();
    }

    protected void swipeUpQuick()
    {
        swipeUp(2000);
    }

    protected void swipeUpToElement(By by, String error_message, int max_swipes)
    {
        int already_swiped = 0;
        while(driver.findElements(by).size() == 0){
            if (already_swiped > max_swipes) {
                waitForElementPresent(by, "Cannot find element by swiping \n" + error_message, 0);
                return;
            }
        swipeUpQuick();
        ++already_swiped;
        }

    }

    protected void swipeElementToLeft(By by, String error_message)
    {
        WebElement element = waitForElementPresent(by, error_message, 10);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;
        TouchAction action = new TouchAction(driver);
        action.press(right_x, middle_y).waitAction(300).moveTo(left_x, middle_y).release().perform();


    }

    private int getAmountOfElements (By by)
    {
        List elements = driver.findElements(by);
        return elements.size();
    }

    private void assertElementsNotPresent(By by, String error_message)
    {
        int amount_of_elements = getAmountOfElements(by);
        if (amount_of_elements > 0) {
            String default_message = "An element " + by.toString() + "supposed not be to present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

}
