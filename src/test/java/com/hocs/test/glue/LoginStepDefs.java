package com.hocs.test.glue;

import static config.Users.*;
import static config.Usernames.*;
import static config.Passwords.*;
import static jnr.posix.util.MethodName.getMethodName;
import static junit.framework.TestCase.fail;
import static org.junit.Assume.assumeNotNull;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.login.LoginPage;
import com.hocs.test.pages.homepage.Homepage;

import config.*;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.core.Serenity;

import static net.serenitybdd.core.Serenity.setSessionVariable;
import static net.serenitybdd.core.Serenity.sessionVariableCalled;
import static net.serenitybdd.core.Serenity.pendingStep;

import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.openqa.selenium.WebDriver;

public class LoginStepDefs {

    @Managed
    WebDriver driver;

    Homepage homepage;

    LoginPage loginPage;

    @Steps(shared = true)
    NavigationStepDefs navigationStepDefs;

    private Page page;


    @Then("^An invalid username or password error is displayed$")
    public void invalidUsernamePasswordErrorDisplayed() {
        loginPage.assertInvalidUsernamePassword();
    }

    @Given("^I am user \"([^\"]*)\"")
    public void iLoginAs(String user) {
        navigateToHocs();
        setSessionVariable("user").to(user);
        switch (user.toUpperCase()) {
            case "DCU":
                enterHocsLoginDetails(DCU);
                break;
            case "TEST":
                enterHocsLoginDetails(TEST);
                break;
            case "DANNY":
                enterHocsLoginDetails(DANNY);
                break;
            case "EAMON":
                enterHocsLoginDetails(EAMON);
                break;
            default:
                pendingStep(user + " is not defined within " + getMethodName());

        }
        loginPage.clickContinueButton();
    }

    @Given("^I am on the Home Office Correspondence Login Page")
    public void homeUrl() {
        navigateToHocs();
    }


    @When("^I enter my login credentials \"([^\"]*)\" and click the login button$")
    public void enterCredentialsAndClickLogin(String credentials) {
        setSessionVariable("credentials").to(credentials);
        switch (credentials) {
            case "DANNY LARGE":
                enterHocsUsername(DANNYLARGE);
                enterHocsPassword(DANNYPASS);
                break;
            case "DCU":
                enterHocsUsername(DCUSER);
                enterHocsPassword(DCUPASS);
                break;
            case "TESTER":
                enterHocsUsername(TESTER);
                enterHocsPassword(TESTERPASS);
                break;
            case "EAMON DROKO":
                enterHocsUsername(EAMONDROKO);
                enterHocsPassword(EAMONPASS);
                break;
            default:
                pendingStep(credentials + " is not defined within " + getMethodName());

        }

        loginPage.clickContinueButton();
    }

    @And("^I enter my password \"([^\"]*)\" in the password field$")
    public void enterHocsPassword(String password) {
        setSessionVariable("password").to(password);
        switch (password) {
            case "DANNY PASS":
                enterHocsPassword(DANNYPASS);
                break;
            case "DCU PASS":
                enterHocsPassword(DCUPASS);
                break;
            case "TESTER PASS":
                enterHocsPassword(TESTERPASS);
                break;
            case "EAMON PASS":
                enterHocsPassword(EAMONPASS);
                break;
            default:
                pendingStep(password + " is not defined within " + getMethodName());

        }
    }

    @When("^I enter invalid login credentials on the login screen$")
    public void enterInvalidLoginCredentials() {
        enterHocsLoginDetails(FAKE);
        homepage.clickContinueButton();
    }

    @Then("^an error message should be displayed as the credentials are invalid$")
    public void assertThatInvalidCredentialsErrorMessageIsShown() {
        loginPage.assertLoginErrorMessage();
    }


    @And("^Select the login button$")
    public void selectLoginButton() {
        loginPage.clickContinueButton();
    }

    @Then("^I should be taken to the homepage$")
    public void assertHomePage() {
        homepage.assertPageTitle();
    }

    private void enterHocsLoginDetails(Users user) {
        loginPage.enterUsername(user.getUsername());
        loginPage.enterPassword(user.getPassword());
    }

    private void enterHocsUsername(Usernames username) {
        loginPage.enterUsername(username.getUsername());
    }

    private void enterHocsPassword(Passwords password) {
        loginPage.enterPassword(password.getPassword());
    }


    private void navigateToHocs() {
        String env = System.getProperty("environment");
        String baseUrl = "";

        if (env == null) {
            System.out.println("Environment parameter not set. Defaulting to 'QA'");
            baseUrl = Environments.QA.getEnvironmentURL();
        } else {
            switch (env.toUpperCase()) {
                case "DEV":
                    baseUrl = Environments.DEV.getEnvironmentURL();
                    break;
                case "LOCAL":
                    baseUrl = Environments.LOCAL.getEnvironmentURL() + Services.HOCS.getPort();
                    break;
                case "QA":
                    baseUrl = Environments.QA.getEnvironmentURL();
                    break;
                default:
                    fail("Environment must be set to LOCAL, DEV or QA");
            }
        }
        driver.get(baseUrl);
    }

}
