Feature: Collage Page

Background: 

	Given I navigate to the Collage Page

Scenario: Export Button
	
	Then there is an export button
	When the export button is clicked
	Then .png is downloaded

Scenario: Save Collage Button
	
	Then there is a save collage button
	When I click the save collage button
	Then the saved image appears in the collage history gallery

Scenario: Not Enough Images
	
	When I generate a collage for "ejausdnvirnjsad" saying "A"
	And wait 60 seconds
	Then a not enough images message is displayed

Scenario: Collage History Gallery

	Then there is a collage history gallery
	When I click the save collage button
	And I enter "dog" in the input
	And enter "B" for collage shape
	And click on the collage options button
	And click on the set options button
	And click the build collage button
	And wait 60 seconds
	And click on a previously saved image
	Then the previous collage should display

Scenario: Empty Collage Border Width
	
	When I clear Collage Border Width
	And click on the set options button
	Then the collage options box remains open

Scenario: Empty Collage Border Color

	When I clear Collage Border Color
	And click on the set options button
	Then the collage options box remains open

Scenario: Empty Photo Border Width
	
	When I clear Photo Border Width
	And click on the set options button
	Then the collage options box remains open

Scenario: Empty Photo Border Color
	
	When I clear Photo Border Color
	And click on the set options button
	Then the collage options box remains open

Scenario: Empty Minimum Rotation
	
	When I clear Minimum Rotation
	And click on the set options button
	Then the collage options box remains open

Scenario: Empty Maximum Rotation
	
	When I clear Maximum Rotation
	And click on the set options button
	Then the collage options box remains open

Scenario: Empty Collage Width

	When I clear Collage Width
	And click on the set options button
	Then the collage options box remains open

Scenario: Empty Collage Height

	When I clear Collage Height
	And click on the set options button
	Then the collage options box remains open

Scenario: Empty Filter

	When I clear Filter
	And click on the set options button
	Then the collage options box remains open

Scenario: Selecting Black and White filter
	When I set a "Black and White" filter
	And click on the set options button
	And I generate a collage for "cat" saying "A"
	Then the collage is Black and White

Scenario: Selecting Greyscale filter
	When I set a "Grayscale" filter
	And click on the set options button
	And I generate a collage for "cat" saying "A"
	Then the collage is Grayscale

Scenario: Selecting Sepia filter
	When I set a "Sepia" filter
	And click on the set options button
	And I generate a collage for "cat" saying "A"
	Then the collage is Sepia

Scenario: Persistent History

	When there is a collage in the history gallery
	
	And I am on the login page
	And I enter "jun" in the username box
	And I enter "july" in the password box
	And click the login button
	And I generate a collage for "shark" saying "A" 
	Then the collage history gallery is empty
	
	When I am on the login page
	And I am on the login page
	And I enter "username" in the username box
	And I enter "password" in the password box
	And click the login button
	And I generate a collage for "shark" saying "C" 
	Then the collage history gallery is not empty
	 

Scenario: Deleting from History

	When there is a collage in the history gallery
	And click on a previously saved image
	And click the delete button
	Then the collage is deleted from the gallery 

Scenario: Downloading as PDF

	When I click the download as PDF button
	Then a PDF collage is downloaded
	


