Feature: Review Draft

  Background:
    Given I am user "<string>"
    And I am at the "<string>" stage

  @HOCS-309, @HOCS-240
  Scenario: Draft QA user approves case for Private Office
    When I click 'Approve'
    And optionally complete a free text field with approval notes
    Then the case moves to 'Private Office' Stage (see BPMN link in link section)
    And I am taken to the "to do" Page

  @HOCS-310, @HOCS-240
  Scenario: User rejects a case
    When I reject a case
    Then the case is returned to the Draft stage (BR - DCU MIN – 15) (see Validation for details on 'Reject' button)
    And the drafter becomes the owner
    And I am taken to the "to do" Page

  @HOCS-310, @HOCS-240
  Scenario: User does not complete a rejection note
    When I attempt to reject a case without entering a reason
    Then an error message appears instructing me to enter my rejection reasons in the free text field
