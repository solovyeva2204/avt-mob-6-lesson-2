package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject {

    private static final String
        SEARCH_INIT_ELEMENT = "//*[contains(@text,'Search Wikipedia')]",
        SEARCH_INPUT = "//*[contains (@text, 'Search…')]",
        SEARCH_RESULT_BY_RESULTS_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']",
        SEARCH_LINE_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
        SEARCH_RESULT_ELEMENT = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']",
        SEARCH_EMPTY_RESULT_ELEMENT = "//*[@text='No results found']",
        SEARCH_EMPTY_MESSAGE = "org.wikipedia:id/search_empty_message";


    public SearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    /*Templates methods*/
    private static String getResultSearchElement(String substring)
    {
        return SEARCH_RESULT_BY_RESULTS_TPL.replace("{SUBSTRING}", substring);
    }
    /*Templates methods*/

    public void initSearchInput()
    {
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT),"Cannot find and init element", 5);
        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find search input element after clicking search input element");
    }

    public void waitForCancelButtonToAppear()
    {
        this.waitForElementPresent(By.id(SEARCH_LINE_CANCEL_BUTTON), "Cannot find search cancel button", 5);
    }

    public void waitForCancelButtonToDisappear()
    {
        this.waitForElementNotPresent(By.id(SEARCH_LINE_CANCEL_BUTTON), "Search cancel button is still present", 5);
    }

    public void clickCancelSearch()
    {
        this.waitForElementAndClick(By.id(SEARCH_LINE_CANCEL_BUTTON), "Cannot find search and click cancel button", 10);
    }

    public void searchLine(String search_line)
    {
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT), search_line, "Cannot find and type into search input", 10);
    }

    public void waitForSearchResult(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(search_result_xpath), "Cannot find search result with substring " + substring);
    }

    public void clickByArticleWithSubString(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(search_result_xpath), "Cannot find and click search result with substring " + substring, 10);
    }

    public int getAmountOfFoundArticles()
    {

        this.waitForElementPresent(
                By.xpath(SEARCH_RESULT_ELEMENT),
                "Cannot find anything request",
                15
        );

        return this.getAmountOfElements(By.xpath(SEARCH_RESULT_ELEMENT));

    }

    public void waitForEmptyResultsLabel()
    {
        this.waitForElementPresent(By.xpath(SEARCH_EMPTY_RESULT_ELEMENT), "Cannot find result element", 15);

    }

    public  void assertThereIsNoResultOfSearch()
    {
        this.assertElementsNotPresent(By.xpath(SEARCH_RESULT_ELEMENT), "We supposed no to find any results");
    }

    public void waitSearchEmptyMessage()
    {
        this.waitForElementPresent(By.id(SEARCH_EMPTY_MESSAGE), "Cannot find search empty message", 5);
    }
}
