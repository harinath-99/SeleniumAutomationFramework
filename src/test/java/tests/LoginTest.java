package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {


    @DataProvider(name = "loginData")
    public Object[][] getData() {

        return new Object[][]{
                {"standard_user", "secret_sauce"},
                {"locked_out_user", "secret_sauce"},
                {"problem_user", "wrong_password"}
        };
    }


    @Test(dataProvider = "loginData")
    public void loginTest(String username, String password) {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.openSite();
        loginPage.login(username, password);

        if (username.equals("standard_user") && password.equals("secret_sauce")) {

            Assert.assertTrue(loginPage.getCurrentUrl().contains("inventory"));

        } else {

            Assert.assertTrue(loginPage.getErrorMessage().contains("Epic sadface"));

        }
    }
}