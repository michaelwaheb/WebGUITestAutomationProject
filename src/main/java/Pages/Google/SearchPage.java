package Pages.Google;

import Pages.Page;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.io.ByteArrayInputStream;

public class SearchPage extends Page
{
    //Locators
    private final By SearchTxt = By.xpath("//textarea[@id='APjFqb' and @name='q' and @aria-label='Search']");
    private final By SearchResult = By.className("MjjYud");


    public SearchPage(WebDriver driver)
    {
        super(driver);
    }
    //Methods to interact
    @Step("Search for '{keyword}'")
    public SearchPage searchFor(String keyword)
    {
        bot.typeAndEnter(SearchTxt, keyword);
        return new SearchPage(driver);
    }
    @Step("Check that Search Result {searchresultorder} text contains '{text}'")
    public SearchPage aseertSearchResultOrderContainsText(int searchresultorder, String text)
    {
        Assert.assertTrue(bot.assertTextInSearchResultByIndex(driver,SearchResult , text,searchresultorder), "The target text("+ text + ")not found in search result " + searchresultorder + ".");
        // Attach Result and snapshot to Allure report
        Allure.step("Success Result with Snapshot");
        Allure.addAttachment("The Target Text Found", "The target text("+ text + ") found in search result " + searchresultorder + ".");
        Allure.addAttachment("Text Found Successfully SnapShot", new ByteArrayInputStream(((ChromeDriver)driver).getScreenshotAs(OutputType.BYTES)));
        return new SearchPage(driver);
    }


}
