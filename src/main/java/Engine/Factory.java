package Engine;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;

public class Factory
{
    public static WebDriver driver;
    @Step("Initialize Session")
    public static WebDriver initiateSession()
    {
        // Initialize the ChromeDriver
        driver = new ChromeDriver();

        return driver;

    }
    @Step("Navigate to url: '{url}'")
    public static void navigateTo(String url)
    {
        // Navigate to a website
        driver.navigate().to(url);
        //Maximize current window
        driver.manage().window().maximize();
    }
    @Step("Terminate Session")
    public static void terminateSession() {

        driver.quit();

//Start then stop Allure serve after test finish
        try {
            Utils.startAndStopAllureServe();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
