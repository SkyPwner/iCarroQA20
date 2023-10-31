package tests;

import dto.UserDtoLombok;
import manager.ApplicationManager;
import manager.TestNGListener;
import org.testng.annotations.*;
import utils.RandomUtils;

@Listeners(TestNGListener.class)
public class BaseTest {
    protected final ApplicationManager app = new ApplicationManager();
    RandomUtils randomUtils = new RandomUtils();

    UserDtoLombok userDtoLombok = UserDtoLombok.builder()
            .email("testqa20@gmail.com")
            .password("123456Aa$")
            .build();

    @BeforeClass(alwaysRun = true)
    public void setup() {
        app.init();
    }

    @AfterClass(alwaysRun = true)
    public void stop() {
        app.tearDown();
    }

    public void logoutIfLogin() {
        if (app.signedIn){
            app.getUserHelper().logout();
            app.signedIn = false;
        }
    }
}
