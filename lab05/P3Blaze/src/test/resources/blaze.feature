Feature: BlazeDemo Travel Choice

  Background: A Website
    Given the website 'blazedemo.com'

  Scenario: Travel Choice
    When I choose the departure 1
    And I choose the destination 3
    And I click the submit button
    And I choose the flight 3
    And I insert the name 'Pedro Laranjinha'
    And I insert the credit card 3819273
    And I click the submit button
    Then I make a successful travel choice