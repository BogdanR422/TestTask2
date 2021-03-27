package webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchResultsPage extends BasePage{
    private WebDriver mDriver;

    @FindBy(css = "input[name='q']")
    private WebElement mSearchField;


    public SearchResultsPage(WebDriver driver) {
        mDriver = driver;
        PageFactory.initElements(driver, this);
    }


    public boolean searchResultsContainSearchQuery() {
        String query = mSearchField.getAttribute("value").trim().toLowerCase();

        List<WebElement> searchResults = mDriver.findElements(By
                .xpath("//div[contains(concat(' ', @class, ' '), ' g ')]//*[contains(translate(text(), " +
                        "'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'),'" + query + "')]"));

        return searchResults.size() > 0;
    }

    public boolean searchSuccessful() {
        return !mDriver.findElements(By.cssSelector("div#result-stats")).isEmpty();
    }

    public boolean pageIsOpened() {
        return !mDriver.findElements(By.cssSelector("div#top_nav")).isEmpty();
    }
}