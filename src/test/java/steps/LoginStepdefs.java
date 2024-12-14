package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import swagLabsPages.LoginElements;
import utilities.Methods;

public class LoginStepdefs {
    LoginElements loginElements = new LoginElements();


    @When("Click the password")
    public void clickThePassword() {
        Methods.click(loginElements.button);
    }

    @And("Click login button")
    public void clickLoginButton() {
    }
}
