package manager;

import dto.AddCarDto;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Paths;
import java.time.Duration;

public class CarHelper extends BaseHelper {

    private static final By carAddLink = By.xpath("//a[contains(@href, '/let-car-work')]");
    private static final By locationInputLocator = By.xpath("//input[@id='pickUpPlace']");
    private static final By manufactureInputLocator = By.xpath("//input[@id='make']");
    private static final By modelInputLocator = By.xpath("//input[@id='model']");
    private static final By yearInputLocator = By.xpath("//input[@id='year']");
    private static final By fuelSelectLocator = By.xpath("//select[@id='fuel']");
    private static final By seatsInputLocator = By.xpath("//input[@id='seats']");
    private static final By carClassInputLocator = By.xpath("//input[@id='class']");
    private static final By serialNumberInputLocator = By.xpath("//input[@id='serialNumber']");
    private static final By priceInputLocator = By.xpath("//input[@id='price']");
    private static final By aboutTextareaLocator = By.xpath("//textarea[@id='about']");
    private static final By submitButtonLocator = By.xpath("//button[@type='submit']");
    private static final By successAddCarLocator = By.xpath("//div[@class='dialog-container']//h1[@class='title'and text()='Car added']");

    public CarHelper(WebDriver driver) {
        super(driver);
    }
    public void fillCarForm(AddCarDto car) {
        clickAddCarLink();
        enterLocationWithAutocomplete(car.getLocation());
        typeTextBase(manufactureInputLocator, car.getManufacture());
        typeTextBase(modelInputLocator, car.getModel());
        typeTextBase(yearInputLocator, String.valueOf(car.getYear()));
        selectFuelType(car.getFuel());

        typeTextBase(seatsInputLocator, String.valueOf(car.getSeats()));
        typeTextBase(carClassInputLocator, car.getCarClass());
        typeTextBase(serialNumberInputLocator, car.getSerialNumber());
        typeTextBase(priceInputLocator, String.valueOf(car.getPrice()));
        typeTextBase(aboutTextareaLocator, car.getAbout());
        uploadPhoto(car.getPhoto());
        clickBase(submitButtonLocator);
    }
    private void uploadPhoto(String photoPath) {
        String absolutePath = Paths.get(photoPath).toAbsolutePath().toString();
        WebElement uploadElement = driver.findElement(By.cssSelector("input[type='file']"));
        uploadElement.sendKeys(absolutePath);
    }

    private void enterLocationWithAutocomplete(String location) {
        typeTextBase(locationInputLocator, location);
        selectFirstOptionFromGooglePlacesDropdown();
    }

    private void selectFirstOptionFromGooglePlacesDropdown() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10).toMillis());
        WebElement autoCompleteResult = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'pac-container pac-logo hdpi') and not(contains(@style, 'display: none'))]")));
        Actions builder = new Actions(driver);
        builder.moveToElement(autoCompleteResult).perform();
        builder.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
    }

    private void selectFuelType(String fuelType) {
        WebElement fuelSelect = driver.findElement(fuelSelectLocator);
        Select select = new Select(fuelSelect);
        select.selectByVisibleText(fuelType);
    }
    public void clickAddCarLink() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50).toMillis());
        wait.until(ExpectedConditions.urlToBe("https://ilcarro.web.app/search"));
        clickBase(carAddLink);
    }
    public boolean isCarAddedSuccessfully() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10).toMillis());
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(successAddCarLocator));
        return successMessage != null;
    }
}

