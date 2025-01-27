package e2e;


import Engine.Factory;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class  TestBase
{
    public static WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void initializeNewDriverSession()
    {
       driver = Factory.initiateSession();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownDriverSession()
    {
        Factory.terminateSession();

    }
}
