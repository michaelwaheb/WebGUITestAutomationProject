package Pages.herokuapp;
import Pages.Page;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends Page
{
    //Locators
    private final By FileUpload = By.xpath("//a[@href='/upload' and text()='File Upload']");
    private final By DynamicLoading = By.xpath("//a[@href='/dynamic_loading' and text()='Dynamic Loading']");

    public HomePage(WebDriver driver)
    {
        super(driver);
    }

    @Step("Click On File Upload")
    public FileUploadPage clickOnFileUpload()
    {
        bot.click(FileUpload);
        return new FileUploadPage(driver);
    }
    @Step("Click On Dynamic Loading")
    public DynamicLoadingPage clickOnDynamicLoading()
    {
        bot.click(DynamicLoading);
        return new DynamicLoadingPage(driver);
    }
}
