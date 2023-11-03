package manager;

import dto.UserDtoLombok;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UserHelper extends BaseHelper {

    public UserHelper(WebDriver driver) {
        super(driver);
    }

    By inputEmailLoginForm = By.xpath("//input[@id='email']");
    By inputPasswordLoginForm = By.xpath("//input[@id='password']");
    By btnYallaLoginForm = By.xpath("//button[@type='submit']");
    By textSuccessLoginPopUp = By.xpath("//h2[@class='message']");
    By btnOpenRegForm = By.xpath("//a[contains(@href, '/registration')]");
    By btnOpenLogForm = By.xpath("//a[contains(@href, '/login')]");
    By inputNameReg = By.xpath("//input[@id='name']");
    By inputLastNameReg = By.xpath("//input[@id='lastName']");
    By inputEmailReg = By.xpath("//input[@id='email']");
    By inputPasswordReg = By.xpath("//input[@id='password']");
//    String btnRegNewUser = "document.querySelector('#terms-of-use');";
    By btnRegNewUser = By.id("terms-of-use");
//    String btnOkPopUpStr = "document.querySelector(`[type='button']`).click();";
    By btnOkPopUpStr = By.xpath("//button[@class='positive-button ng-star-inserted']");
    By checkBoxReg = By.xpath("//label[@for='terms-of-use']");
    By btnUallaReg = By.xpath("//button[@type='submit']");
    By textPopUpSuccessRegH1 = By.xpath("//div[@class='dialog-container']//h1[@class='title']");
    By btnLogout = By.xpath("//a[text()=' Logout ']");
    By btnOkPopUp = By.xpath("//button[@type='button']");
    By errorMessageWrongEmailReg = By.xpath("//input[@autocomplete='email']/..//div//div");
    By errorMessageIncorrectPasswordReg = By.xpath("//input[@autocomplete='new-password']/..//div//div");
    By registrationLinkIsOpened = By.xpath("//h1[text()='Registration']");
    By logInLinkIsOpened = By.xpath("//h1[text()='Log in']");

    public void loginUserDtoLombok(UserDtoLombok user) {
        waitForElementToAppear(btnOpenLogForm);
        clickBase(btnOpenLogForm);
        waitForElementToAppear(logInLinkIsOpened);
        typeTextBase(inputEmailLoginForm, user.getEmail());
        typeTextBase(inputPasswordLoginForm, user.getPassword());
        clickBase(btnYallaLoginForm);
    }

    public void fillRegistrationForm(UserDtoLombok user) {
        waitForElementToAppear(btnOpenRegForm);
        clickBase(btnOpenRegForm);
        waitForElementToAppear(registrationLinkIsOpened);
        typeTextBase(inputNameReg, user.getName());
        typeTextBase(inputLastNameReg, user.getLastName());
        typeTextBase(inputEmailReg, user.getEmail());
        typeTextBase(inputPasswordReg, user.getPassword());
        clickBase(btnRegNewUser);
        clickBase(btnUallaReg);
        clickBase(btnRegNewUser);
    }

    public boolean validatePopUpMessageSuccessAfterLogin() {
        return isTextEqual(textSuccessLoginPopUp, "Logged in success");
    }

    public boolean validatePopUpMessageLoginIncorrect() {
        return isTextEqual(textSuccessLoginPopUp, "\"Login or Password incorrect\"");
    }

    protected WebElement findElement(By locator) {
        return driver.findElement(locator);
    }

    public boolean isPopUpSuccessMessageDisplayed() {
        waitForElementToAppear(textPopUpSuccessRegH1);
        return findElement(textPopUpSuccessRegH1).isDisplayed();
    }

    public boolean validatePopUpMessageSuccessAfterRegistration() {
        return isPopUpSuccessMessageDisplayed();
    }

    public boolean validateMessageIncorrectEmailReg() {
        return isTextEqual(errorMessageWrongEmailReg, "Wrong email format");
    }

    public boolean validateMessageWrongPasswordReg() {
        return isTextEqual(errorMessageIncorrectPasswordReg, "PASSWORD MUST CONTAIN 1 UPPERCASE LETTER, 1 LOWERCASE LETTER, 1 NUMBER AND ONE SPECIAL SYMBOL OF [@$#^&*!]");
    }

    public boolean validateErrorEmptyEmailReg() {
        return isTextEqual(errorMessageWrongEmailReg, "Email is required");
    }


    public void logout() {
        clickBase(btnLogout);
    }

    public void clickOkPopUpSuccessLogin() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000).toMillis());
        wait.until(ExpectedConditions.visibilityOfElementLocated((btnOkPopUpStr)));
        clickBase(btnOkPopUpStr);
    }


    public void clickRegistrationLink() {
        clickBase(btnOpenRegForm);
    }

    public void clickLoginLink() {
        clickBase(btnOpenLogForm);
    }
}
