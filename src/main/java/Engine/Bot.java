package Engine;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import java.io.ByteArrayInputStream;
import java.time.Duration;
import java.util.List;

public class Bot
{
    private final WebDriver driver;
    private final Wait<WebDriver> wait;
    String failureMessage = "Element found by '${LOCATOR}' was not found.";
    String SuccessMessage = "Test Passed Successfully";
    public Bot(final WebDriver driver) {
        this.driver = driver;
        this.wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);
    }
    @Step("Click element found by {locator}")
    public Bot click(By locator){
        try{
            wait.until( d->{
                driver.findElement(locator).click();
                return true;
            });
        } catch (TimeoutException timeoutException) {
            Allure.addAttachment(failureMessage.replace("${LOCATOR}",locator.toString()), new ByteArrayInputStream(((ChromeDriver)driver).getScreenshotAs(OutputType.BYTES)));
            throw timeoutException;
        }
        return this;
    }
    @Step("Type '{text}' Then Enter")
    public Bot typeAndEnter(By locator,String text){
        try{
            wait.until( d->{
                driver.findElement(locator).sendKeys(text+Keys.ENTER);
                return true;
            });
        } catch (TimeoutException timeoutException) {
            Allure.addAttachment(failureMessage.replace("${LOCATOR}",locator.toString()), new ByteArrayInputStream(((ChromeDriver)driver).getScreenshotAs(OutputType.BYTES)));
            throw timeoutException;
        }
        return this;
    }
    @Step("Assert that Specific Search Result contains the text: {targetText}")
    public boolean assertTextInSearchResultByIndex(WebDriver driver,By locator ,String targetText, int resultIndex) {
        try {
            // Wait for search results to load

            wait.until( d->{
                driver.findElement(locator).isDisplayed();
                return true;
            });

            // Locate all search result titles
            List<WebElement> searchResults = driver.findElements(locator);

            // Check if the resultIndex is valid
            if (resultIndex < 1 || resultIndex > searchResults.size()) {
                System.err.println("Invalid result index: " + resultIndex);
                return false;
            }

            // Get the specific search result based on 1-based index
            WebElement specificResult = searchResults.get(resultIndex - 1);
            String resultText = specificResult.getText();

            // Check if the specific result contains the target text
            if (resultText.contains(targetText)) {
                System.out.println("Found the target text: (" + targetText + ") in search result: " + resultIndex + ": " + resultText);
                // Attach a screenshot to the Allure report if test passes
                Allure.step("ScreenShot");
                Allure.addAttachment(SuccessMessage, new ByteArrayInputStream(((ChromeDriver)driver).getScreenshotAs(OutputType.BYTES)));
                return true;
            } else {
                System.out.println("The target text ("+ targetText + ") was not found in result " + resultIndex + ".");
                return false;
            }
        } catch (Exception e) {
            Allure.addAttachment(failureMessage.replace("${LOCATOR}",locator.toString()), new ByteArrayInputStream(((ChromeDriver)driver).getScreenshotAs(OutputType.BYTES)));
            System.err.println("An error occurred while verifying text in a specific result: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    public void switchToNewPage() {
        // Store the current window handle
        String currentWindow = driver.getWindowHandle();

        // Switch to the new tab
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(currentWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }
}
