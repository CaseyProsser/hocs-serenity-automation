Feature: Team members can allocate work

  # single quotes represent future unknown vars

    @HOCS-402
    Scenario: A user is in no teams
      Given I am user "NoTEAM"
      When I view my workstacks
      Then I see a sign up message

    @HOCS-402
    Scenario: A user is in a team with no or zero cases
      #Setup on UI and set correct users per test
      Given I am user "TEAM_A"
      And My team has 0 cases
      When I view my workstacks
      Then I see a X value for 'thisTeam'
      # I see a 0 value for 'team A'

    @HOCS-402
      # Another team member should be 'assign to' some specific user
    Scenario: A user is in one team
      Given I am user "TEAM_A"
      And I create 'created' cases
      # I create 5 cases
      And I assign X to me
      # I assign 3 to me
      # And leave one unassigned (this is implied)
      And assign 1 to another team member
      When I view my workstacks
      Then I see a X value for 'thisTeam'
      # I see a 5 value for 'team A'
      #Do I view multiple teams or only my team?
      And I see a X value for assigned to me
      # I see a 3 value for assigned to me
      And I see a Z value for unassigned
      # I see a 1 value for unassigned

    @HOCS-402
    Scenario: A user is in a team with cases and a team without cases
      Given I am user "TEAM_AandB"
      And I create 'created' 'team A' cases
      # I create '5' 'team A' cases
      # And 'team B' has 0 cases this is implied
      When I view my workstacks
      Then I see a X value for 'thisTeam'
      # I see a 5 value for 'team A'
      And I do not see 'thisTeam'
      # I do not see 'team B'


    @HOCS-402
    Scenario: A user is in two teams and both teams have cases
      #Teams are assigned by business rules IE DCU MIN cases goto Team A so I should name the teams DCU/TRO/PolR etc
      Given I am user "TEAM_AandC"
      And I create 'created' 'team A' cases
      # Where created is 5
      And I create 'created' 'team C' cases
      # Where created is 3
      And assign 'assigned' 'thisTeam' cases to me
      # assign '3' 'team A' cases to me
      And assign 'assigned' 'team A' to another team member
      # assign '1' 'team A' to another team member
      # And leave 1 unassigned (this is implied)
      And assign 'assigned' 'thisTeam' cases to me
      # assign '2' 'team C' cases to myself
      #And leave 1 unassigned (this is implied)
      When I view my workstacks
      Then I see that I have X cases assigned to me
      # I see that I have 5 cases assigned to me
      And I see 'thisTeam' has X cases total
      # I see 'team A' has 5 cases total
      And I see 'thisTeam' has X unassigned case
      # I see 'team A' has 1 unassigned case
      And I see 'thisTeam' has X cases total
      # I see 'team C' has 3 cases total
      # Step def available for this but does not include variable parameter of teams yet.
      And I see 'thisTeam' has X unassigned case
      # I see 'team C' has 1 unassigned case

    @HOCS-402
    Scenario: A user has no allocated work
      Given I am user "TEAM_A"
      And I create 5 'team A' cases
      And assign 4 to another team member
      # StepDef Available without variable parameters
      # And leave 1 unassigned
      When I view my workstacks
      Then I see that I have X cases assigned to me
      # I see that I have 0 cases assigned to me
      And I see 'thisTeam' has X cases total
      # I see team A has 5 cases total
      And I see 'thisTeam' has X unassigned case
      # I see team A has 1 unassigned case

    @HOCS-402
    Scenario: A user has an overdue case 
      #How do you make a case overdue?  deadline of -1 day
      Given I am user "TEAM_A"
      And I create 1 'team A' cases with "deadline -1 day"
      # Add to CreateCase StepDefs
      And assign 'assigned' 'thisTeam' cases to me
      # I assign 1 'team A' case to me
      And I create 4 'team A cases
      # CreateCase Stepdef needed
      And assign 'assigned' 'thisTeam' cases to me
      # assign 2 'team A' cases to me
      When I view my workstacks
      Then I see that I have X cases assigned to me
      # I see that I have 3 cases assigned to me
      And I see 1 of my cases is overdue
      And I see 'thisTeam' has X cases total
      # I see team A has 5 cases total

    @HOCS-402
    Scenario: A user is in a team with an overdue case
      Given I am user "TEAM_A"
      And I create 1 'team A' case with 'deadline -1 day'
      #Add to CreateCase StepDefs
      And I create 4 more cases
      When I view my workstacks
      And I see 'thisTeam' has X cases total
      # I see team A has 5 cases total
      And I see 1 of my cases is overdue

    @HOCS-402
    Scenario: A user has a priority case
      Given I am user "TEAM_A"
      And I create 4 'team a' cases
      #CreateCase StepDefs
      And I create 1 PRIORITY 'team a' case
      # CreateCase StepDefs
      And I assign X to me
      # assign 3 to myself
      # Assign priority case to myself as well
      # How is priority given and shown to the user?
      When I view my workstacks
      Then I see that I have X cases assigned to me
      # I see that I have 3 cases assigned to me
      And I see 1 of my cases is priority
      And I see 'thisTeam' has X cases total
      # I see team A has 5 cases total

    @HOCS-402
    Scenario: A user is in a team with a priority case
      Given I am user "TEAM_A"
      And I create 4 'team A' cases
      And I create 1 PRIORITY 'team a' case
      # CreateCaseStepDefs ^^
      And assign 'assigned' 'thisTeam' cases to me
      # assign 3 'team A' cases to me
      And A case not assigned to me is priority
      When I view my workstacks
      Then I see that I have X cases assigned to me
      # I see that I have 3 cases assigned to me
      And I see 'thisTeam' has X cases total
      # I see team A has 5 cases total
      And A case not assigned to me is priority
      # I see 1 unassigned case is priority