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
import java.io.File;

public class FileUploadPage extends Page
{
    private final By ChooseFile = By.cssSelector("input[type=file]");
    private final By UploadBtn = By.id("file-submit");
    private final By UploadedFileName = By.id("uploaded-files");

    public FileUploadPage(WebDriver driver)
    {
        super(driver);
    }

    @Step("Upload File")
    public FileUploadPage uploadFile(String FilePath)
    {
        bot.chooseFile(ChooseFile, FilePath);
        bot.click(UploadBtn);
        return new FileUploadPage(driver);
    }

    @Step("Check That File Uploaded Successfully")
    public FileUploadPage checkThatFileUploadedSuccessfully(String FileName)
    {
        File FileDir = new File(FileName);

        Assert.assertEquals(bot.getText(UploadedFileName),FileDir.getName(), "File is not uploaded successfully");
        // Attach Result and snapshot to Allure report
        Allure.step("Success Result with Snapshot");
        Allure.addAttachment("Uploaded File Name", bot.getText(UploadedFileName));
        Allure.addAttachment("File Uploaded Successfully SnapShot", new ByteArrayInputStream(((ChromeDriver)driver).getScreenshotAs(OutputType.BYTES)));
        return new FileUploadPage(driver);
    }
}
