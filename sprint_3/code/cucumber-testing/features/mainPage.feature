Feature: Main Page


Background:
	Given I am on the Main Page

Scenario: Website Title

	Then there is a title

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

Scenario: Build Collage Button is Pressed without Collage Options Set
		
	When I enter "cat" in the input
	And enter "A" for collage shape
	And click the build collage button
	And wait 60 seconds
	Then I am on the Collage Page

Scenario: Empty Collage Topic
	
	When I generate a collage for "" saying "A"
	Then I stay on the Main Page

Scenario: Empty Collage Shape

	When I generate a collage for "sharks" saying ""
	Then I stay on the Main Page

Scenario: Empty Collage Border Width
	
	When I clear Collage Border Width
	And click on the set options button
	Then collage options is open

Scenario: Empty Collage Border Color

	When I clear Collage Border Color
	And click on the set options button
	Then collage options is open

Scenario: Empty Photo Border Width
	
	When I clear Photo Border Width
	And click on the set options button
	Then collage options is open

Scenario: Empty Photo Border Color
	
	When I clear Photo Border Color
	And click on the set options button
	Then collage options is open

Scenario: Empty Minimum Rotation
	
	When I clear Minimum Rotation
	And click on the set options button
	Then collage options is open

Scenario: Empty Maximum Rotation
	
	When I clear Maximum Rotation
	And click on the set options button
	Then collage options is open

Scenario: Empty Collage Width

	When I clear Collage Width
	And click on the set options button
	Then collage options is open

Scenario: Empty Collage Height

	When I clear Collage Height
	And click on the set options button
	Then collage options is open

Scenario: Empty Filter

	When I clear Filter
	And click on the set options button
	Then collage options is open

