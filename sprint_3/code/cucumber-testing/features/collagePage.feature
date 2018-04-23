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
	And I reload the page
	Then the collage history gallery is not empty

Scenario: Not Enough Images
	
	When I generate a collage for "ejausdnvirnjsad" saying "A"
	And wait 30 seconds
	And I reload the page
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


Scenario: Empty Collage Border Width, Empty Collage Border Color, Empty Photo Border Width, Empty Photo Border Color, Empty Minimum Rotation, Empty Maximum Rotation, Empty Collage Width, Empty Collage Height, Empty Filter

	When I click on the options button
	And I clear Collage Border Width
	And click on the set options button
	Then collage options is open
	And enter "0" for collage border width
	And I clear Collage Border Color
	And click on the set options button
	Then collage options is open
	And enter "white" for collage border color
	And I clear Photo Border Width
	And click on the set options button
	Then collage options is open
	And enter "0" for photo border width
	And I clear Photo Border Color
	And click on the set options button
	Then collage options is open
	And enter "white" for photo border color
	And I clear Minimum Rotation
	And click on the set options button
	Then collage options is open
	And enter "45" for minimum rotation
	And I clear Maximum Rotation
	And click on the set options button
	Then collage options is open
	And enter "45" for maximum rotation
	And I clear Collage Width
	And click on the set options button
	Then collage options is open
	And enter "1000" for collage width
	And I clear Collage Height
	And click on the set options button
	Then collage options is open
	And enter "800" for collage height
	And I clear Filter
	And click on the set options button
	And click on the build collage button
	And wait 30 seconds
	Then there is a collage with those features


Scenario: Selecting Black and White filter
	
	When I click on the options button
	When I set a "Black and White" filter
	And click on the set options button
	And I generate a collage for "cat" saying "A"
	Then there is a collage with those features

Scenario: Selecting Greyscale filter
	
	When I click on the options button
	When I set a "Greyscale" filter
	And click on the set options button
	And I generate a collage for "cat" saying "A"
	Then there is a collage with those features

Scenario: Selecting Sepia filter
	
	When I click on the options button
	When I set a "Sepia" filter
	And click on the set options button
	And I generate a collage for "cat" saying "A"
	Then there is a collage with those features

Scenario: Persistent History and User-Specific History

	When I am on the login page
	And I enter "503191" in the username box
	And I enter "password" in the password box
	And click the login button
	And I generate a collage for "dog" saying "A"
	And wait 30 seconds
	And I reload the page
	And I click the save collage button
	And I reload the page
	And I am on the login page
	And I enter "username" in the username box
	And I enter "password" in the password box
	And click the login button
	And I generate a collage for "shark" saying "C" 
	And wait 30 seconds
	And I reload the page
	Then there is no "dog" image
	And I am on the login page
	And I enter "503191" in the username box
	And I enter "password" in the password box
	And click the login button
	And I generate a collage for "shark" saying "C" 
	And wait 30 seconds
	And I reload the page
	Then the collage history gallery is not empty
	 
Scenario: Deleting from History

	When I am on the login page
	And I enter "username" in the username box
	And I enter "password" in the password box
	And click the login button
	And I generate a collage for "usc" saying "C"
	And wait 30 seconds
	And I reload the page
	And I click the save collage button
	And I reload the page
	Then the collage history gallery is not empty
	And I generate a collage for "ucla" saying "C"
	And wait 30 seconds
	And I reload the page
	Then the collage history gallery is not empty
	And I click on the "usc" image
	And click the delete button
	Then there is no "usc" image

Scenario: Downloading as PDF

	When I click the download as PDF button
	Then a PDF collage is downloaded
	


