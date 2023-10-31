package tests;

import data.DataProviderLogin;
import dto.UserDTO;
import dto.UserDtoLombok;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTests extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void preconditionsLogin() {
        app.getUserHelper().clickLoginLink();
    }

    @AfterMethod(alwaysRun = true)
    public void postconditionsLogin() {
        app.getUserHelper().clickOkPopUpSuccessLogin();
        logoutIfLogin();
    }

    @Test(dataProvider = "loginYAML", dataProviderClass = DataProviderLogin.class)
    public void positiveLogin(UserDtoLombok userDtoLombok) {
        app.getUserHelper().loginUserDtoLombok(userDtoLombok);
        app.signedIn = true;
        Assert.assertTrue(app.getUserHelper().validatePopUpMessageSuccessAfterLogin());
    }

    @Test(dataProvider = "negativePasswordDataLogin", dataProviderClass = DataProviderLogin.class)
    public void negativePasswordWithoutSymbol(UserDtoLombok userDtoLombok) {
        app.getUserHelper().loginUserDtoLombok(userDtoLombok);
        Assert.assertTrue(app.getUserHelper().validatePopUpMessageLoginIncorrect());
    }

    @Test
    public void negativePasswordWithoutNumbers() {
        UserDtoLombok userDtoLombok = UserDtoLombok.builder()
                .email("testqa20@gmail.com")
                .password("ddsdhjAa$")
                .build();
        app.getUserHelper().loginUserDtoLombok(userDtoLombok);
        Assert.assertTrue(app.getUserHelper().validatePopUpMessageLoginIncorrect());
    }

    @Test
    public void negativePasswordWithoutLetters() {
        UserDtoLombok userDtoLombok = UserDtoLombok.builder()
                .email("testqa20@gmail.com")
                .password("12345678$")
                .build();
        app.getUserHelper().loginUserDtoLombok(userDtoLombok);
        Assert.assertTrue(app.getUserHelper().validatePopUpMessageLoginIncorrect());
    }
}
