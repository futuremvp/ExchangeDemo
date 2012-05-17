Feature: Exchange Display
  In order to find exchange rates
  As a User
  I want to see the rate in the exchange screen

@automated @quit_app_from_main_screen
Scenario: Term Search from list view
  Given I started the app
  When  I click "Start" button
  And I wait for 3 second
  Then  I should see a list of exchange rates
  And   I should see "Europe (EUR)" with the value of "0.7491"
