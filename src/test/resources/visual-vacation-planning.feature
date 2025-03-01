Feature: visual-vacation-planning
  As Olivier a busy young father
  I want to visually plan my vacations using a calendar
  So that I can easily see my time off and manage my vacation days

  Background:
    Given the vacation tracking system is running
    And Olivier has set his yearly vacation allocation to 20 days

  Scenario: Olivier views the vacation on the calendar
    When Olivier opens the vacation planner
    Then Olivier should see a calendar view of the current year
    And weekend days should be visually distinct
    And Olivier should see he has 20 vacation days available

  Scenario: Olivier selects vacation days on the calendar
    When Olivier views the calendar for June 2024
    And Olivier selects the period from June 10 to June 14
    Then those days should be highlighted on the calendar
    And Olivier should see a summary showing 5 selected days
    And Olivier should see he will have 15 vacation days remaining if confirmed

  Scenario: Olivier confirms a vacation period
    Given Olivier has selected June 10 to June 14 on the calendar
    When Olivier enters "Summer Break" as the vacation title
    And Olivier confirms this vacation period
    Then those days should be marked as vacation days on the calendar
    And "Summer Break" should appear in Olivier's list of planned vacations
    And Olivier should see he has 15 vacation days remaining

  Scenario: Olivier cancels a planned vacation
    Given Olivier has a confirmed vacation "Summer Break" from June 10 to June 14
    And Olivier has 15 vacation days remaining
    When Olivier selects this vacation period on the calendar
    And Olivier cancels this vacation
    Then those days should no longer be marked as vacation days
    And Olivier should see he has 20 vacation days remaining