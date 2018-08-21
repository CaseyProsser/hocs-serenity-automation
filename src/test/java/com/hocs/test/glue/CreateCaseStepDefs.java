package com.hocs.test.glue;

import static org.junit.Assert.fail;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.create_case.AddDocuments;
import com.hocs.test.pages.create_case.CreateCase;
import com.hocs.test.pages.homepage.Homepage;
import com.hocs.test.pages.mark_up.MarkUp;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CreateCaseStepDefs {

    AddDocuments addDocuments;

    Homepage homepage;

    MarkUp markUp;

    CreateCase createCase;

    Page page;

    @Given("^I am presented with \"([^\"]*)\"")
    public void iAmPresentedWith(String userView) {
        switch (userView.toUpperCase()) {
            case "NO CASE TYPES":
                createCase.assertNoOptionsAvailable();
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

    @When("^I create a \"([^\"]*)\" case \"([^\"]*)\" a document$")
    public void iCreateACaseADocument(String caseType, String document) {
        homepage.clickCreateSingleCase();

        switch (caseType.toUpperCase()) {
            case "DCU MIN":
                createCase.clickDcuMinRadioButton();
                break;
            case "DCU TRO":
                createCase.clickDcuTroRadioButton();
                break;
            default:
                fail(caseType + " is not defined in CreateCaseStepDefs.iCreateACaseADocument.");
        }

        page.clickNextButton();

        switch (document.toUpperCase()) {
            case "WITH":
                addDocuments.uploadDocument();
                page.clickSubmitButton();
                break;
            case "WITHOUT":
                page.clickSubmitButton();
                break;
            default:
                fail("Please set " + document + " to be either WITH OR WITHOUT");
        }
    }

}
