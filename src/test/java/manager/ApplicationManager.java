package manager;

import lombok.Getter;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ConfigProperties;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    Logger logger = LoggerFactory.getLogger(ApplicationManager.class);
    static String browser;
    private EventFiringWebDriver driver;
    @Getter
    UserHelper userHelper;
    @Getter

    CarHelper carHelper;

    public ApplicationManager(){
        browser = System.getProperty("browser", BrowserType.CHROME);
    }

    public void init() {
        if(browser.equals(BrowserType.CHROME)){
            driver = new EventFiringWebDriver(new ChromeDriver());
            logger.info("created chrome browser");
        } else if (browser.equals(BrowserType.FIREFOX)) {
            driver = new EventFiringWebDriver(new FirefoxDriver());
            logger.info("created fireFox browser");

        }

        driver.navigate().to(ConfigProperties.getProperty("url"));
        logger.info("open page" + ConfigProperties.getProperty("url"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.register(new WDListener());


        userHelper = new UserHelper(driver);
        carHelper = new CarHelper(driver);
    }
    public boolean signedIn = false;

    public void tearDown() {
        driver.quit();
    }

}
