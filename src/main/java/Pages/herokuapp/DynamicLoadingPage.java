package Pages.herokuapp;

import Pages.Page;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.io.ByteArrayInputStream;

public class DynamicLoadingPage extends Page
{
    private final By Example2 = By.xpath("//a[@href='/dynamic_loading/2' and contains(text(),'Example 2')]");
    private final By StartBtn = By.xpath("//div[@id='start']/button[text()='Start']");
    private final By FinalMessage = By.id("finish");

    public DynamicLoadingPage(WebDriver driver)
    {
        super(driver);
    }
    @Step("Click On Example 2")
    public DynamicLoadingPage clickOnExample2()
    {
        bot.click(Example2);
        return new DynamicLoadingPage(driver);
    }
    @Step("Click Start")
    public DynamicLoadingPage clickStart()
    {
        bot.click(StartBtn);
        return new DynamicLoadingPage(driver);
    }

    @Step("Check That The Text Displayed is: {TargetText}")
    public DynamicLoadingPage checkThatTextDisplayed(String TargetText)
    {
        Assert.assertEquals(bot.getText(FinalMessage), TargetText, "The Text Displayed isn't " +TargetText+ ".");
        // Attach Result and snapshot to Allure report
        Allure.step("Success Result with Snapshot");
        Allure.addAttachment("Text Displayed Name", bot.getText(FinalMessage));
        Allure.addAttachment("Text Displayed Successfully SnapShot", new ByteArrayInputStream(((ChromeDriver)driver).getScreenshotAs(OutputType.BYTES)));

        return new DynamicLoadingPage(driver);
    }


}
