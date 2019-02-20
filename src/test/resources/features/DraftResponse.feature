Feature: HOCS User is able to draft a response

  Background:
    Given I am user "EAMON"
    And I am at the "DRAFT" stage

  @HOCS-287, @HOCS-239
  Scenario: User decides the case is not for them, and completes a rejection note
    When I select a case "should not" answered by my team
    And I "complete" the rejection note
    Then the case should be moved to the "Markup" stage
    And the "initial draft reject"email is sent to the "Mark Up user" and "Nominated Person in the "Markup Team"
    And I return to the home page

  @HOCS-287, @HOCS-239
  Scenario: User decides the case is not for them, does not complete a rejection note
    When I select a case "should not" answered by my team
    And I "do not complete" the rejection note
    Then an error message appears instructing me to add rejection reasons

  @HOCS-288, @HOCS-239
  Scenario: User responds by Phone and fills out call details to complete the stage
    Given I select to reply by "phone"
    When I "complete" the call details
    Then I am taken to the "home" page
    And the case is completed
    And I return to the home page

  @HOCS-288, @HOCS-239
  Scenario: Phone response does not have info in free text field
    Given I select to reply by "phone"
    And I "do not complete" the call details
    Then I see an error message instructing me to enter call notes

  @HOCS-296, @HOCS-239
  Scenario: A user selects not to offline a 
    Given I click the "no" to offline button
    Then the case will progress to the QA stage
    And the nominated person of the team that own the case receives an email
    And I return to the home page

  @HOCS-297, @HOCS-298, @HOCS-239
  Scenario: User selects offline QA
    Given I click the "offline QA" button
    And I select an "offline" Quality Assurer from a list of members of the team that own the case
    When I progress the case
    Then the Quality Assurer will receive a notification to say they have QA’d that case
    And the case will progress to the "Private Office" stage
    And I am taken to the home page

  @Validation
  Scenario: User must select a radio button when asked whether correspondence can be answered by their team at the Draft stage
    And I click the continue button on the correspondence answer screen
    Then an error message should be displayed as I have not selected radio buttons on this screen

  @Validation
  Scenario: User must enter the reason that their team cannot answer a case in the text box at the Draft stage
    And I click the finish button on the case rejection screen
    Then an error message should be displayed as I have not entered a reason in the text box

  @Validation
  Scenario: User must select a radio button when asked how they intend to respond at the Draft stage
    And I click the continue button on how do you intend to respond screen
    Then an error message should be displayed as I have not selected a response on this screen

  @Validation
  Scenario: User must summarise their call in the text box at the Draft stage after selecting phone response
    And I click the finish button on the summarise your call screen
    Then an error message should be displayed as I have not summarised the call

  @Validation
  Scenario: User must add a primary draft document at the Draft stage
    And I click the continue button on the which is the primary draft document screen
    Then an error message should be displayed as I have not added a primary draft document

  @Validation
  Scenario: User must select the Draft document type and add a document on the add document screen at the Draft stage
    And I click the add button on the add documents screen
    Then an error message should be displayed as I have not selected a document type and added a document

  @Validation
  Scenario: User must select a radio button when asked whether they want to QA the case offline
    And I click the continue button on the do you want QA this offline screen
    Then an error message should be displayed as I have not selected whether the case should be QA offline or not

  @Validation
  Scenario: User must select the user that has done the Offline QA from the drop down at the Draft stage
    And I click the finish button on the who has done the offline QA screen
    Then an error message should be displayed as I have not selected the user that did the offline QA