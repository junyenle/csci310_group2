Feature: Collage Page

Background: 

	Given I navigate to the Collage Page

Scenario: Export Button
	
	Then there is an export button
	When the export button is clicked
	Then .png is downloaded

Scenario: Collage History Gallery

	Then there is a collage history gallery

#Scenario: Animated Busy Symbol
	
	#When I enter "cat" in the input
	#And enter "A" for collage shape
	#And click on the collage options button
	#And click on the set options button
	#And click the build collage button
	#Then the busy animation is shown
	#Then a collage is generated
	

