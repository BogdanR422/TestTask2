package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import webpages.*;

import java.time.Duration;

public class TestCase1 {
    private WebDriver mDriver;

    private SearchPage mSearchPage;
    private SearchResultsPage mSearchResultsPage;

    @BeforeTest
    @Parameters("browser")
    public void setup(String browser) throws Exception {
        mDriver = WebDriverHelper.getDriver(browser);

        mSearchPage = new SearchPage(mDriver);
        mSearchResultsPage = new SearchResultsPage(mDriver);

        mDriver.manage().window().maximize();
        mDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        mDriver.get(ConfProperties.getProperty("page.google.url"));
    }

    @Test
    public void testGoogleSearchCapability() {
        System.out.println("STEP: Assert Google search page is opened");
        Assert.assertTrue(mSearchPage.pageIsOpened());
        System.out.println("STEP: Input \"selenium\" search query");
        mSearchPage.inputSearchQuery("selenium");
        System.out.println("STEP: Initiate search");
        mSearchPage.initiateSearch();

        System.out.println("STEP: Assert the page with Google search results is opened");
        Assert.assertTrue(mSearchResultsPage.pageIsOpened());
        System.out.println("STEP: Assert the search results page contains results");
        Assert.assertTrue(mSearchResultsPage.searchSuccessful());
        System.out.println("STEP: Assert the search results contain search query");
        Assert.assertTrue(mSearchResultsPage.searchResultsContainSearchQuery());
    }

    @AfterTest
    public void close() {
        mDriver.quit();
    }

}