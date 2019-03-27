package com.wikipedia.android_app.tests;

import com.wikipedia.android_app.pages.ArticlePage;
import com.wikipedia.android_app.pages.SearchWidget;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import util.ExtentReporter;

/**
 * Created by on 02/10/2016.
 */
public class SearchTest extends BaseScenario {

    private static final String SEARCH_TERM = "Software testing";
    private static final String INVALID_SEARCH = "klhsdkalsf";

    @BeforeSuite
    public void setUpSuite() {
        ExtentReporter.startReport();
    }

    @Test(priority = 2)
    public void search_topic_should_return_relevant_article() {
        SearchWidget searchWidget = new SearchWidget(driver);
        searchWidget.searchForTopic(SEARCH_TERM);

        Assert.assertTrue(searchWidget.getNumberOfSearchResults() > 0);
        Assert.assertTrue(searchWidget.SearchResultContains(SEARCH_TERM));

        ArticlePage articlePage = searchWidget.selectFirstResult();
        Assert.assertTrue(articlePage.getArticleTitle().contains(SEARCH_TERM));
    }

    //@Test(priority = 1)
    public void invalid_search_should_return_no_results() {
        SearchWidget searchWidget = new SearchWidget(driver);
        searchWidget.searchForInvalid(INVALID_SEARCH);

        Assert.assertTrue(searchWidget.searchResultsIsEmpty());
        searchWidget.closeSearch();
    }

    // @Test(priority = 3)
    public void handle_two_apps() {
        SearchWidget searchWidget = new SearchWidget(driver);
        searchWidget.searchForTopic(SEARCH_TERM);

        Activity activity = new Activity("com.example.test", "com.example.LaunchApp");
        activity.setAppWaitPackage("app wait package goes here");
        activity.setAppWaitActivity("app wait activity goes here");
        ((AndroidDriver) driver).startActivity(activity);

        Assert.assertTrue(searchWidget.searchResultsIsEmpty());
        searchWidget.closeSearch();

    }

    @AfterSuite
    public void tearDownSuite() {
        ExtentReporter.endReport();
    }
}
