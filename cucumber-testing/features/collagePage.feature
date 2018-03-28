Feature: Collage Page

Background: 

	Given I am on the Collage Page

Scenario: Export Button
	
	Then there is an export button

Scenario: Export Button Function

	When the export button is clicked
	Then .png is downloaded

Scenario: Collage History Gallery

	Then there is a collage history gallery

Scenario: Animated Busy Symbol
	
	When I enter "puppies" in the input
	And select A for collage shape
	And set collage options
	And click the build collage button
	Then a busy animation is displayed
	Then a collage is generated and returned
	

