Feature: Login Page


Background:
	Given I am on the login page

Scenario: Username Box

	Then there is a username box

Scenario: Password Box

	Then there is a password box

Scenario: Login Button

	Then there is a login button

Scenario: Login Button is Pressed
	
	When I enter "username" in the username box
	And I enter "password" in the password box
	And click the login button
	Then I am on the Main Page for the first time

Scenario: Login Failed
	
	When I enter "invalid" in the username box
	And I enter "invalid" in the password box
	And click the login button
	Then I am on the Login Page still
