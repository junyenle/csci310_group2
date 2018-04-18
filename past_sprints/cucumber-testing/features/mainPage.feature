Feature: Main Page


Background:
	Given I am on the webpage

Scenario: Topic Input Box

	Then there is a topic input box

Scenario: Collage Shape Input Box

	Then there is a collage shape input box

Scenario: Collage Options
	
	Then there is a collage options box

Scenario: Save to History Button

	Then there is a save to history button

Scenario: Build Collage Button

	Then there is a build collage button

Scenario: Build Collage Button is Pressed
	
	When I enter "puppies" in the input
	And select A for collage shape
	And set collage options
	And click the build collage button
	Then I am on the Collage Page

# Might need one more test for username and password

