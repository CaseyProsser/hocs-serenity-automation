package com.hocs.test.glue;

import static org.junit.Assert.fail;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.create_case.AddDocuments;
import com.hocs.test.pages.mark_up.MarkUp;
import com.hocs.test.pages.create_case.CreateCase;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CreateCaseStepDefs {

    private AddDocuments addDocuments;

    private MarkUp markUp;

    private CreateCase createCase;

    private Page page;

    @Given("^I am presented with \"([^\"]*)\"")
    public void iAmPresentedWith(String userView) {
        switch (userView.toUpperCase()) {
            case "NO CASE TYPES":
                createCase.radioButtonsNotDisplayed();
                break;
            default:
                fail(userView + " is not defined with CreateCaseStepDefs.iAmPresentedWith");
        }
    }

    @When("^I create a case$")
    public void iCreateACase() {
        createCase.clickDcuMinRadioButton();
        createCase.clickNextButton();
        addDocuments.uploadDocument();
        page.clickSubmitButton();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Then("^the correspondence type is the \"([^\"]*)\" correspondent$")
    public void theCorrespondenceTypeIsTheCorrespondent(String ordinal) {
        switch (ordinal.toUpperCase()) {
            case "PRIMARY":
                break;
            case "SECONDARY":
                break;
            default:
                fail("Please select PRIMARY or SECONDARY");
        }
    }

    @And("^a case has a \"([^\"]*)\" correspondent$")
    public void aCaseHasACorrespondent(String ordinal) {
        switch (ordinal.toUpperCase()) {
            case "PRIMARY":
                break;
            case "SECONDARY":
                break;
            default:
                fail("Please select PRIMARY or SECONDARY");
        }
    }

    @When("^I enter correspondence data manually$")
    public void iEnterCorrespondenceDataManually() {

    }

    @When("^I add an additional correspondent$")
    public void iAddAnAdditionalCorrespondent() {

    }

    @Then("^the member is the \"([^\"]*)\" correspondent$")
    public void theMemberIsTheCorrespondent(String ordinal) {
        switch (ordinal.toUpperCase()) {
            case "PRIMARY":
                break;
            case "SECONDARY":
                break;
            default:
                fail("Please select PRIMARY or SECONDARY");
        }

    }

    @When("^I select to correspond with a member from the dropdown$")
    public void iSelectToCorrespondWithAMemberFromTheDropdown() {
        markUp.selectSecondSignOffMinisterFromDropdown();
    }

    @When("^I select to correspond with \"([^\"]*)\" from the search function$")
    public void iSelectToCorrespondWithAMemberFromTheSearchFunction(String minister) {
        markUp.enterSignOffMinisterTypeFunction(minister);
    }
}
