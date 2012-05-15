Feature: App navigation
  In order to exchange through the app
  As a consumer
  I want the start buttons to be easy to tap

@automated @quit_app_from_main_screen
  Scenario: Start button is in the launch screen
    Given I started the app
	Then I can see a "Start" button in the launch screen