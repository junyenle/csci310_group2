Feature: Collage Page

Background: 

	Given I navigate to the Collage Page

Scenario: Persistent History and User-Specific History

	When I am on the login page
	And I enter "jun" in the username box
	And I enter "july" in the password box
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
	And I enter "jun" in the username box
	And I enter "july" in the password box
	And click the login button
	And I generate a collage for "shark" saying "C" 
	And wait 30 seconds
	And I reload the page
	Then the collage history gallery is not empty
	And there is a "dog" image
	And I click on the "dog" image
	And click the delete button
