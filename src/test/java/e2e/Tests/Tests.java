package e2e.Tests;

import Engine.Factory;
import Engine.Utils;
import Pages.Google.SearchPage;
import Pages.herokuapp.HomePage;
import e2e.TestBase;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

public class Tests extends TestBase
{

    @Test(priority = 1)
    @Description("This test will navigate to the google homepage and search for a keyword")
    public void SearchTest()
    {
        //Get the test data from the json file
        String BaseUrl = Utils.getTestData("SearchTest.BaseUrl");
        String GivenKeyword = Utils.getTestData("SearchTest.SearchFor");
        String TextInResult = Utils.getTestData("SearchTest.ExpectedText");

        //Navigate to the base url
        Factory.navigateTo(BaseUrl);
        new SearchPage(driver)
                .searchFor(GivenKeyword)
                .aseertSearchResultOrderContainsText(3, TextInResult);
    }

   @Test(priority = 2)
   @Description("This test will navigate to the herokuapp website and test the file upload functionality")
    public void UploadFileTest()
    {
        //Get the test data from the json file
        String BaseUrl = Utils.getTestData("UploadFileTest.BaseUrl");
        String Filepath = Utils.getTestData("UploadFileTest.FilePath");

        //Navigate to the base url
        Factory.navigateTo(BaseUrl);
        new HomePage(driver)
                .clickOnFileUpload()
                .uploadFile(Filepath)
                .checkThatFileUploadedSuccessfully(Filepath);
    }

    @Test(priority = 3)
    @Description("This test will navigate to the herokuapp website and test the dynamic loading functionality")
    public void DynamicLoadTest()
    {
        //Get the test data from the json file
        String BaseUrl = Utils.getTestData("DynamicLoadTest.BaseUrl");
        String TextToLoad = Utils.getTestData("DynamicLoadTest.TextToLoad");

        //Navigate to the base url
        Factory.navigateTo(BaseUrl);
        new HomePage(driver)
                .clickOnDynamicLoading()
                .clickOnExample2()
                .clickStart()
                .checkThatTextDisplayed(TextToLoad);
    }
}
