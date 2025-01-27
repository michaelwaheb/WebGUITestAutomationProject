package e2e.Tests;

import Engine.Factory;
import Engine.Utils;
import Pages.Google.SearchPage;
import e2e.TestBase;
import org.testng.annotations.Test;

public class Tests extends TestBase
{
    // Use the base class method to initiate the session for Google
    @Test
    public void test1()
    {
        String BaseUrl = Utils.getTestData("SearchTest.BaseUrl");
        String GivenKeyword = Utils.getTestData("SearchTest.SearchFor");
        String TextInResult = Utils.getTestData("SearchTest.ExpectedText");
        Factory.navigateTo(BaseUrl);
        new SearchPage(driver)
                .searchFor(GivenKeyword)
                .aseertSearchResultOrderContainsText(3, TextInResult);

    }

    @Test
    public void test2()
    {

    }
    @Test
    public void test3()
    {

    }
}
