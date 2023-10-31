package tests;

import data.DataProviderLogin;
import dto.UserDtoLombok;
import org.testng.Assert;
import org.testng.annotations.*;

public class RegistrationTests extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void preConditionsRegistration() {
        app.getUserHelper().clickRegistrationLink();
    }

    @AfterMethod(alwaysRun = true)
    public void postConditionsLogin() {
        app.getUserHelper().clickOkPopUpSuccessLogin();
        logoutIfLogin();
    }

    @Test(dataProvider = "loginYAML", dataProviderClass = DataProviderLogin.class)
    public void positiveRegistration(UserDtoLombok userDtoLombok) {
        app.getUserHelper().fillRegistrationForm(userDtoLombok);
        app.signedIn = true;
        Assert.assertTrue(app.getUserHelper().validatePopUpMessageSuccessAfterRegistration());
    }

//    @Test
//    public void negativeRegistrationWrongEmail() {
//        UserDtoLombok user = UserDtoLombok.builder()
//                .email("abc@")
//                .password("123456Aa$")
//                .lastName("abdfg")
//                .name("test")
//                .build();
//
//        app.getUserHelper().fillRegistrationForm(user);
//        Assert.assertTrue(app.getUserHelper().validateMessageIncorrectEmailReg());
//    }
//
//    @Test(groups = "smoke")
//    public void negativeRegistrationWrongPassword() {
//        String email = randomUtils.generateEmail(7);
//
//        UserDtoLombok user = UserDtoLombok.builder()
//                .email(email)
//                .password("123456Aa")
//                .lastName("abdfg")
//                .name("test")
//                .build();
//
//        app.getUserHelper().fillRegistrationForm(user);
//        Assert.assertTrue(app.getUserHelper().validateMessageWrongPasswordReg());
//    }
//
//    @Test
//    public void negativeRegistrationBlankEmail() {
//        UserDtoLombok user = UserDtoLombok.builder()
//                .email("")
//                .password("123456Aa$")
//                .lastName("abdfg")
//                .name("test")
//                .build();
//
//        app.getUserHelper().fillRegistrationForm(user);
//        Assert.assertTrue(app.getUserHelper().validateErrorEmptyEmailReg());
//    }
}
