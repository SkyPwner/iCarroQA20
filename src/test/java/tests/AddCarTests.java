package tests;

import data.DataProviderCar;
import dto.AddCarDto;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.BeforeMethod;

public class AddCarTests extends BaseTest{
    @BeforeMethod(alwaysRun = true)
    public void preconditionsLogin()  {
        app.getUserHelper().clickLoginLink();
        app.getUserHelper().loginUserDtoLombok(userDtoLombok);
        app.getUserHelper().clickOkPopUpSuccessLogin();

    }

    @Test(dataProvider = "carAddYaml", dataProviderClass = DataProviderCar.class)
    public void positiveAddCar(AddCarDto addCarDto) {
        app.getCarHelper().fillCarForm(addCarDto);
        Assert.assertTrue(app.getCarHelper().isCarAddedSuccessfully());
    }
}
