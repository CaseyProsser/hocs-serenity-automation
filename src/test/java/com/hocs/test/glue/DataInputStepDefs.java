package com.hocs.test.glue;

import static jnr.posix.util.MethodName.getMethodName;
import static net.serenitybdd.core.Serenity.pendingStep;

import com.hocs.test.pages.Page;
import com.hocs.test.pages.data_input.DataInput;
import com.hocs.test.pages.homepage.Homepage;
import com.hocs.test.pages.data_input.DataInputQADecision;
import com.hocs.test.pages.data_input.RecordCorrespondentDetails;
import com.hocs.test.pages.workstacks.Workstacks;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

import static net.serenitybdd.core.Serenity.pendingStep;

public class DataInputStepDefs {

    @Steps(shared = true)
    GenericInputStepDefs genericInputStepDefs;

    DataInput dataInput;

    Homepage homepage;

    DataInputQADecision dataInputQADecision;

    Page page;

    RecordCorrespondentDetails recordCorrespondentDetails;

    Workstacks workstacks;

    @When("^I complete the Data Input stage$")
    public void completeDataInputStage() {
        dataInput.dataInputFullFlow();
    }

    @When("^I complete the Data Input stage and send a copy to Number Ten$")
    public void completeDataInputStageWCopyToN10() {
        dataInput.dataInputFullFlowWithCopyToN10();
    }

    @When("^the Data Input Stage is completed for \"([^\"]*)\" caseType$")
    public void completeDataInputPerCaseType(String caseType) {
        switch (caseType.toUpperCase()) {
            case "DCU MIN":
                page.clickOn(homepage.home);
                page.clickOn(homepage.performanceProcessTeam);
                break;
            case "DCU N10":
                page.clickOn(homepage.transferN10Team);
                break;
            case "DCU TRO":
                page.clickOn(homepage.performanceProcessTeam);
                break;
            default:
                pendingStep(caseType + " is not defined within " + getMethodName());
        }
        dataInput.dataInputFullFlow();
    }

    @When("^I add an additional correspondent$")
    public void iAddAnAdditionalCorrespondent() {
        recordCorrespondentDetails.clickAdditionalCorrespondentYes();
        page.clickOn(dataInput.continueButton);
        page.clickOn(dataInput.correspondentMemberNoRadioButton);
        page.clickOn(dataInput.continueButton);
        recordCorrespondentDetails.fillMandatoryCorrespondentFields();
        page.clickOn(dataInput.continueButton);
    }

    @When("^I enter correspondence data manually$")
    public void iEnterCorrespondenceDataManually() {
        genericInputStepDefs.fillMandatoryFields("Data Input");
        page.clickOn(dataInput.continueButton);
        page.clickOn(dataInput.addCorrespondentLink);
        recordCorrespondentDetails.fillMandatoryCorrespondentFields();
        page.clickOn(dataInput.addButton);
    }

    @When("^I select to correspond with a member from the dropdown$")
    public void iSelectToCorrespondWithAMemberFromTheDropdown() {
        genericInputStepDefs.fillMandatoryFields("Data Input");
        page.clickOn(dataInput.continueButton);
        page.clickOn(dataInput.correspondentMemberYesRadioButton);
        page.clickOn(dataInput.continueButton);
        recordCorrespondentDetails.selectMemberFromDropdownByIndex(1);
        page.clickOn(dataInput.continueButton);
    }

    @When("^I select to correspond with \"([^\"]*)\" from the search function$")
    public void iSelectToCorrespondWithAMemberFromTheSearchFunction(String minister) {
        genericInputStepDefs.fillMandatoryFields("Data Input");
        page.clickOn(dataInput.continueButton);
        page.clickOn(dataInput.correspondentMemberYesRadioButton);
        page.clickOn(dataInput.continueButton);
        recordCorrespondentDetails.selectMemberFromDropdownByName(minister);
        page.clickOn(dataInput.continueButton);
    }

    @When("^I select the primary correspondent radio button for a different correspondent$")
    public void iSelectThePrimaryCorrespondentRadioButtonForADifferentCorrespondent() {
    }

    @When("^I select a Data Input QA decision of \"([^\"]*)\"$")
    public void iSelectADataInputQADecisionOf(String decision) {
        switch (decision.toUpperCase()) {
            case "ACCEPT":
                dataInputQADecision.acceptDataInputQa();
                break;
            case "REJECT":
                dataInputQADecision.rejectDataInputQa();
                break;
            default:
                pendingStep(decision + " is not defined within " + getMethodName());
        }
        dataInput.clickFinishButton();
    }

    @When("^I do not select a Data Input QA response$")
    public void iDoNotSelectADataInputQAResponse() {
        dataInput.clickFinishButton();
    }

    @And("^I set the correspondence channel to \"([^\"]*)\"$")
    public void iSetTheCorrespondenceChannelTo(String channel) {
        switch (channel.toUpperCase()) {
            case "EMAIL":
                page.clickOn(dataInput.emailOriginalChannelRadioButton);
                break;
            case "POST":
                page.clickOn(dataInput.postOriginalChannelRadioButton);
                break;
            case "PHONE":
                page.clickOn(dataInput.phoneOriginalChannelRadioButton);
                break;
            case "NO. 10":
                page.clickOn(dataInput.numberTenOriginalChannelRadioButton);
                break;
            default:
                pendingStep(channel + " is not defined within " + getMethodName());
        }
    }

    @When("^I select to add a correspondent that \"([^\"]*)\" a member of parliament$")
    public void addACorrespondentThatIsOrIsNotAnMP(String isOrIsNot) {
        dataInput.selectAddACorrespondentLink();


        if (isOrIsNot.toUpperCase().equals("IS")) {
            dataInput.selectCorrespondentIsAMemberRadioButton();
        } else if (isOrIsNot.toUpperCase().equals("IS NOT")) {
            dataInput.selectCorrespondentIsNotAMemberRadioButton();
        }
        clickContinueButton();
    }

    @When("^they complete the first data input screen$")
    public void completeFirstDataInputScreen() {
        dataInput.enterDayOfCorrespondenceSent("01");
        dataInput.enterMonthOfCorrespondenceSent("01");
        dataInput.enterYearOfCorrespondenceSent("2019");
        page.clickOn(dataInput.emailOriginalChannelRadioButton);
        page.clickOn(dataInput.continueButton);
        page.clickOn(dataInput.continueButton);
        page.clickOn(dataInput.finishButton);
    }

    @When("^they click the continue button$")
    public void clickContinueButton() {
        page.clickOn(dataInput.continueButton);
    }

    @When("^I click the continue button at the data input stage$")
    public void userDoesNotEnterDateCorrespondenceWasSentDataInputStage() {
        page.clickOn(workstacks.allocateToMeButton);
        page.clickOn(dataInput.continueButton);
    }


    @When("^I click the finish button on the which is the primary correspondent screen$")
    public void userDoesNotAddPrimaryCorrespondentDataInputStage() {
        page.clickOn(workstacks.allocateToMeButton);
        dataInput.enterDayOfCorrespondenceSent("01");
        dataInput.enterMonthOfCorrespondenceSent("01");
        dataInput.enterYearOfCorrespondenceSent("2019");
        page.clickOn(dataInput.emailOriginalChannelRadioButton);
        page.clickOn(dataInput.shouldResponseBeCopiedN10NoRadioButton);
        page.clickOn(dataInput.continueButton);
        page.clickOn(dataInput.finishButton);
    }

    @When("^I click the continue button on the is the correspondent an MP screen$")
    public void userDoesNotSelectPrimaryCorrespondentTypeRadioButton() {
        page.clickOn(workstacks.allocateToMeButton);
        dataInput.enterDayOfCorrespondenceSent("01");
        dataInput.enterMonthOfCorrespondenceSent("01");
        dataInput.enterYearOfCorrespondenceSent("2019");
        page.clickOn(dataInput.emailOriginalChannelRadioButton);
        page.clickOn(dataInput.shouldResponseBeCopiedN10NoRadioButton);
        page.clickOn(dataInput.continueButton);
        dataInput.sleep(500);
        page.clickOn(dataInput.addCorrespondentLink);
        page.clickOn(dataInput.continueButton);
    }

    @When("^I click the add button on the add member of parliament screen$")
    public void userDoesNotSelectMPFromDownDownBox() {
        page.clickOn(workstacks.allocateToMeButton);
        dataInput.enterDayOfCorrespondenceSent("01");
        dataInput.enterMonthOfCorrespondenceSent("01");
        dataInput.enterYearOfCorrespondenceSent("2019");
        page.clickOn(dataInput.emailOriginalChannelRadioButton);
        page.clickOn(dataInput.shouldResponseBeCopiedN10NoRadioButton);
        page.clickOn(dataInput.continueButton);
        dataInput.sleep(500);
        page.clickOn(dataInput.addCorrespondentLink);
        page.clickOn(dataInput.correspondentMemberYesRadioButton);
        page.clickOn(dataInput.continueButton);
        page.clickOn(dataInput.addButton);
    }

    @When("^I click the add button on the record correspondent details screen$")
    public void userDoesNotSelectCorrespondentTypeFromDropDownBox() {
        page.clickOn(workstacks.allocateToMeButton);
        dataInput.enterDayOfCorrespondenceSent("01");
        dataInput.enterMonthOfCorrespondenceSent("01");
        dataInput.enterYearOfCorrespondenceSent("2019");
        page.clickOn(dataInput.emailOriginalChannelRadioButton);
        page.clickOn(dataInput.shouldResponseBeCopiedN10NoRadioButton);
        page.clickOn(dataInput.continueButton);
        dataInput.sleep(500);
        page.clickOn(dataInput.addCorrespondentLink);
        page.clickOn(dataInput.correspondentMemberNoRadioButton);
        page.clickOn(dataInput.continueButton);
        page.clickOn(dataInput.addButton);
    }

    @Then("^an error message should be displayed as I have not added any text into the case note text box$")
    public void assertThatCaseNoteMustNotBeBlankErrorMessageIsShown() {
        workstacks.assertCaseNoteMustNotBeBlankErrorMessage();
    }

    @Then("^an error message should be displayed as I have not entered text in the full name field")
    public void assertThatCorrespondentNameNotEnteredErrorMessageIsShown() {
        dataInput.assertCorrespondentFullNameErrorMessage();
    }

    @Then("^an error message should be displayed as I have not selected the correspondent type$")
    public void assertThatCorrespondentTypeNotSelectedErrorMessageIsShown() {
        dataInput.assertCorrespondentTypeDropDownErrorMessage();
    }

    @Then("^an error message should be displayed as I must select a member of parliament from the drop down$")
    public void assertThatMemberIsRequiredErrorMessageIsShown() {
        dataInput.assertMemberIsRequiredErrorMessage();
    }

    @Then("^an error message should be displayed as I must select a correspondent type on this screen$")
    public void assertThatCorrespondentTypeErrorMessageIsShown() {
        dataInput.assertCorrespondentTypeMustBeSelectedErrorMessage();
    }

    @Then("^they should be added to the list of correspondents$")
    public void theyShouldBeAddedToTheListOfCorrespondents() {
        recordCorrespondentDetails.assertPrimaryCorrespondent();
    }

    @Then("^an error message should be displayed as I have not added a primary correspondent$")
    public void assertThatWhichIsPrimaryCorrespondentErrorMessageIsDisplayed() {
        dataInput.assertWhichIsThePrimaryCorrespondentErrorMessage();
    }

    @Then("^an error message should be displayed as I have not entered a \"([^\"]*)\"$")
    public void assertValidationMessagesOnDataInputForm(String field) {
        switch(field.toUpperCase()){
            case "CORRESPONDENCE DATE" :
                dataInput.assertCorrespondenceDateErrorMessage();
                break;
            case "CORRESPONDENCE TYPE" :
                dataInput.assertHowWasCorrespondenceReceivedErrorMessage();
                break;
            case "COPY TO NUMBER TEN" :
                dataInput.assertShouldResponseBeCopiedN10ErrorMessage();
                break;
            default:
                pendingStep(field + " is not defined within " + getMethodName());
        }
    }
}
