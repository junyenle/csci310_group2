Feature: Main Page


Background:
	Given I am on the Main Page

Scenario: Website Title

	Then then there is a title

Scenario: Topic Input Box

	Then there is a topic input box

Scenario: Collage Shape Input Box

	Then there is a collage shape input box

Scenario: Build Collage Button

	Then there is a build collage button

Scenario: Collage Options Button
	
	Then there is a collage options button

Scenario: Collage Options Button is Pressed

	When I click on the collage options button
	And click on the set options button

Scenario: Build Collage Button is Pressed
	
	When I enter "cat" in the input
	And enter "A" for collage shape
	And click on the collage options button
	And click on the set options button
	And click the build collage button
	And wait 60 seconds
	Then I am on the Collage Page


