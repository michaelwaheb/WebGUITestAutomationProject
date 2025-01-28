package e2e;


import Engine.Factory;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public abstract class  TestBase
{
    public static WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public void initializeNewDriverSession()
    {
       driver = Factory.initiateSession();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownDriverSession()
    {
        Factory.terminateSession();

    }
}
