package manager;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

public class BaseHelper {
    WebDriver driver;
    public BaseHelper(WebDriver driver) {
        this.driver = driver;
    }

    private WebElement findElementBase(By locator) {
        System.out.println(locator);
        return driver.findElement(locator);
    }

    public void clickBase(By locator) {
        WebElement el = findElementBase(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", el);
    }

    public String getTextBase(By locator) {
        WebElement el = findElementBase(locator);
        return el.getText().trim().toUpperCase();
    }

    public void typeTextBase(By locator, String text) {
        WebElement el = findElementBase(locator);
        el.click();
        el.clear();
        el.sendKeys(text);
    }

    public boolean isTextEqual(By locator, String expectedResult) {
        String actualResult = getTextBase(locator);
        expectedResult = expectedResult.toUpperCase();

        if (expectedResult.equals(actualResult)) {
            return true;
        } else {
            System.out.println("expected result: " + expectedResult +
                    "actual result: " + actualResult);
            return false;
        }
    }
    public void waitForElementToAppear(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(500).toMillis());
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
