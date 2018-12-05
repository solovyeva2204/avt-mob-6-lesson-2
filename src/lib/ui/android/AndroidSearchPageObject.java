package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;

public class AndroidSearchPageObject extends SearchPageObject
{
   static {
       SEARCH_INIT_ELEMENT = "xpath://*[contains(@text,'Search Wikipedia')]";
               SEARCH_INPUT = "xpath://*[contains (@text, 'Search…')]";
               SEARCH_RESULT_BY_RESULTS_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']";
               SEARCH_LINE_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn";
               SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
               SEARCH_EMPTY_RESULT_ELEMENT = "xpath://*[@text='No results found']";
               SEARCH_EMPTY_MESSAGE = "id:org.wikipedia:id/search_empty_message";
   }

   public AndroidSearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }
}
