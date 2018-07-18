package com.ndelius.test.glue;

import static org.junit.Assert.fail;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.WebDriver;

public class NavigationStepDefs {

    @Managed
    WebDriver driver;

    @Given("^I navigate to the \"([^\"]*)\" Page$")
    public void iNavigateToThePage(String page) {
        switch (page.toUpperCase()) {
            case "HOCS DEMO FORM":
                driver.get("http://localhost:8080/action/test/form");
                break;
            default:
                fail(page + " is not defined with NavigationStepDefs.iNavigateToThePage");
        }
    }

    @Then("^I am taken to the \"([^\"]*)\" Page$")
    public void iAmTakenToThePage(String page) {

        switch (page.toUpperCase()) {
            case "HOCS DEMO FORM":
                driver.get("http://localhost:8080/action/test/form");
                break;
            default:
                fail(page + " is not defined with NavigationStepDefs.iNavigateToThePage");
        }
    }
}
