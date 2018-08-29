Feature: HOCS User is able to create a case

  Background:
    Given I am user "Dom"
    And I am at the "Data Input" stage

  @HOCS-274, @HOCS-238
  Scenario: DCU data entry user selects correspondence channel and date of correspondence
    When I fill all mandatory fields on the "<string>" page with valid data
    And I click the "Next" button
    Then I am taken to the "<string>" Page

  @HOCS-274, @HOCS-238
  Scenario: DCU data entry user selects correspondence channel but does not input valid date of correspondence
    When I fill all mandatory fields on the "<string>" page with valid data
    But I enter an invalid date
    And I click the "Next" button
    Then I see the "invalid date of correspondence" message

  @HOCS-274, @HOCS-238
  Scenario: DCU data entry user selects a reference
    When I fill all mandatory fields on the "<string>" page with valid data
    And I select a reference type from the list of reference type options
    And I enter a reference in the reference field
    And I click the "Next" button
    Then I am taken to the "<string>" Page

  @HOCS-276, @HOCS-238
  Scenario: User can select a member from the dropdown
    When I select to correspond with a member from the dropdown
    Then the member is the "primary" correspondent

  @HOCS-276, @HOCS-238
  Scenario: User can select a member from the search function
    When I select to correspond with "<minister>" from the search function
    Then the member is the "primary" correspondent

  @HOCS-277, @HOCS-238
  Scenario: User adds a correspondent manually
    When I enter correspondence data manually
    Then the correspondence type is the "primary" correspondent

  @HOCS-394, @HOCS-238
  Scenario: User chooses to add another correspondent
    Given a case has a "Primary" correspondent
    When I add an additional correspondent
    Then the correspondence type is the "secondary" correspondent

  @HOCS-394, @HOCS-238
  Scenario: User chooses to make a secondary correspondent the primary correspondent
    Given a case has a "Secondary" correspondent
    When I select the primary correspondent radio button for a different correspondent
    Then the correspondence type is the "primary" correspondent