Feature: Main Page

Background:
	Given I navigate to the Main Page

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

Scenario: Placeholder Text
	
	Then the input box has placeholder for Enter topic
	Then the shape input box has placeholder for Enter shape

Scenario: Presence of Input Options
	
	When I click on the options button
	Then there is a collage border width text field
	Then there is a collage border color text field
	Then there is a photo border width text field
	Then there is a photo border color text field
	Then there is a minimum rotation text field
	Then there is a maximum rotation text field
	Then there is a collage width text field
	Then there is a collage height text field
	Then there is a filter dropdown field

Scenario: Insufficient Number of Images Found
	
	When I enter "asdjbaigdhab" in the input
	And enter "A" for collage shape
	And click the build collage button
	And wait 30 seconds
	Then there is an insufficient number of images found error

Scenario: Collage with No Collage Border, No Photo Border, 45 Degree Rotation, 800x600 Dimensions, and No Filter

	When I enter "cat" in the input
	And enter "A" for collage shape
	And I click on the options button
	And enter "0" for collage border width
	And enter "white" for collage border color
	And enter "0" for photo border width
	And enter "white" for photo border color
	And enter "45" for minimum rotation
	And enter "45" for maximum rotation
	And click on the set options button
	And click on the build collage button
	And wait 30 seconds
	Then there is a collage with those features

Scenario: Collage with No Collage Border, No Photo Border, -35 to 35 Degree Rotation, 800x600 Dimensions, and No Filter

	When I enter "cat" in the input
	And enter "A" for collage shape
	And I click on the options button
	And enter "0" for collage border width
	And enter "white" for collage border color
	And enter "0" for photo border width
	And enter "white" for photo border color
	And enter "-35" for minimum rotation
	And enter "35" for maximum rotation
	And click on the set options button
	And click on the build collage button
	And wait 30 seconds
	Then there is a collage with those features

Scenario: Collage with a Red Collage Border, No Photo Border, No Rotation, 800x600 Dimensions, and No Filter

	When I enter "cat" in the input
	And enter "A" for collage shape
	And I click on the options button
	And enter "5" for collage border width
	And enter "red" for collage border color
	And enter "0" for photo border width
	And enter "white" for photo border color
	And enter "0" for minimum rotation
	And enter "0" for maximum rotation
	And click on the set options button
	And click on the build collage button
	And wait 30 seconds
	Then there is a collage with those features

Scenario: Collage with a Red Photo Border, No Collage Border, No Rotation, 800x600 Dimensions, and No Filter

	When I enter "cat" in the input
	And enter "A" for collage shape
	And I click on the options button
	And enter "0" for collage border width
	And enter "white" for collage border color
	And enter "5" for photo border width
	And enter "red" for photo border color
	And enter "0" for minimum rotation
	And enter "0" for maximum rotation
	And click on the set options button
	And click on the build collage button
	And wait 30 seconds
	Then there is a collage with those features

Scenario: Collage with a Red Photo Border, Red Collage Border, No Rotation, 800x600 Dimensions, and No Filter

	When I enter "cat" in the input
	And enter "A" for collage shape
	And I click on the options button
	And enter "5" for collage border width
	And enter "red" for collage border color
	And enter "5" for photo border width
	And enter "red" for photo border color
	And enter "0" for minimum rotation
	And enter "0" for maximum rotation
	And click on the set options button
	And click on the build collage button
	And wait 30 seconds
	Then there is a collage with those features

Scenario: Collage with a Red Collage Border, No Photo Border, 45 Rotation, 800x600 Dimensions, and No Filter

	When I enter "cat" in the input
	And enter "A" for collage shape
	And I click on the options button
	And enter "5" for collage border width
	And enter "red" for collage border color
	And enter "0" for photo border width
	And enter "white" for photo border color
	And enter "45" for minimum rotation
	And enter "45" for maximum rotation
	And click on the set options button
	And click on the build collage button
	And wait 30 seconds
	Then there is a collage with those features

Scenario: Collage with a Red Photo Border, No Collage Border, 45 Rotation, 800x600 Dimensions, and No Filter

	When I enter "cat" in the input
	And enter "A" for collage shape
	And I click on the options button
	And enter "0" for collage border width
	And enter "white" for collage border color
	And enter "5" for photo border width
	And enter "red" for photo border color
	And enter "45" for minimum rotation
	And enter "45" for maximum rotation
	And click on the set options button
	And click on the build collage button
	And wait 30 seconds
	Then there is a collage with those features

Scenario: Collage with a Red Photo Border, Red Collage Border, 45 Rotation, 800x600 Dimensions, and No Filter

	When I enter "cat" in the input
	And enter "A" for collage shape
	And I click on the options button
	And enter "5" for collage border width
	And enter "red" for collage border color
	And enter "5" for photo border width
	And enter "red" for photo border color
	And enter "45" for minimum rotation
	And enter "45" for maximum rotation
	And click on the set options button
	And click on the build collage button
	And wait 30 seconds
	Then there is a collage with those features

Scenario: Collage with No Collage Border, No Photo Border, No Rotation, 1000x800 Dimensions, and No Filter

	When I enter "cat" in the input
	And enter "A" for collage shape
	And I click on the options button
	And enter "0" for collage border width
	And enter "white" for collage border color
	And enter "0" for photo border width
	And enter "white" for photo border color
	And enter "0" for minimum rotation
	And enter "0" for maximum rotation
	And enter "1000" for collage width
	And enter "800" for collage height
	And click on the set options button
	And click on the build collage button
	And wait 30 seconds
	Then there is a collage with those features

Scenario: Collage with No Collage Border, No Photo Border, 45 Rotation, 1000x800 Dimensions, and No Filter

	When I enter "cat" in the input
	And enter "A" for collage shape
	And I click on the options button
	And enter "0" for collage border width
	And enter "white" for collage border color
	And enter "0" for photo border width
	And enter "white" for photo border color
	And enter "45" for minimum rotation
	And enter "45" for maximum rotation
	And enter "1000" for collage width
	And enter "800" for collage height
	And click on the set options button
	And click on the build collage button
	And wait 30 seconds
	Then there is a collage with those features

Scenario: Collage with Red Collage Border, No Photo Border, 45 Rotation, 1000x800 Dimensions, and No Filter

	When I enter "cat" in the input
	And enter "A" for collage shape
	And I click on the options button
	And enter "5" for collage border width
	And enter "red" for collage border color
	And enter "0" for photo border width
	And enter "white" for photo border color
	And enter "45" for minimum rotation
	And enter "45" for maximum rotation
	And enter "1000" for collage width
	And enter "800" for collage height
	And click on the set options button
	And click on the build collage button
	And wait 30 seconds
	Then there is a collage with those features

Scenario: Collage with No Collage Border, Red Photo Border, 45 Rotation, 1000x800 Dimensions, and No Filter

	When I enter "cat" in the input
	And enter "A" for collage shape
	And I click on the options button
	And enter "0" for collage border width
	And enter "white" for collage border color
	And enter "5" for photo border width
	And enter "red" for photo border color
	And enter "45" for minimum rotation
	And enter "45" for maximum rotation
	And enter "1000" for collage width
	And enter "800" for collage height
	And click on the set options button
	And click on the build collage button
	And wait 30 seconds
	Then there is a collage with those features

Scenario: Collage with No Collage Border, No Photo Border, No Rotation, 800x600 Dimensions, and Black and White Filter

	When I enter "cat" in the input
	And enter "A" for collage shape
	And I click on the options button
	And enter "0" for collage border width
	And enter "white" for collage border color
	And enter "0" for photo border width
	And enter "white" for photo border color
	And enter "0" for minimum rotation
	And enter "0" for maximum rotation
	And select "Black and White" for filter
	And click on the set options button
	And click on the build collage button
	And wait 30 seconds
	Then there is a collage with those features

Scenario: Collage with No Collage Border, No Photo Border, 45 Rotation, 800x600 Dimensions, and Black and White Filter

	When I enter "cat" in the input
	And enter "A" for collage shape
	And I click on the options button
	And enter "0" for collage border width
	And enter "white" for collage border color
	And enter "0" for photo border width
	And enter "white" for photo border color
	And enter "45" for minimum rotation
	And enter "45" for maximum rotation
	And select "Black and White" for filter
	And click on the set options button
	And click on the build collage button
	And wait 30 seconds
	Then there is a collage with those features

Scenario: Collage with No Collage Border, No Photo Border, -35 to 35 Degree Rotation, 800x600 Dimensions, and Greyscale Filter

	When I enter "cat" in the input
	And enter "A" for collage shape
	And I click on the options button
	And enter "0" for collage border width
	And enter "white" for collage border color
	And enter "0" for photo border width
	And enter "white" for photo border color
	And enter "-35" for minimum rotation
	And enter "35" for maximum rotation
	And select "Greyscale" for filter
	And click on the set options button
	And click on the build collage button
	And wait 30 seconds
	Then there is a collage with those features

Scenario: Collage with No Collage Border, No Photo Border, -35 to 35 Degree Rotation, 800x600 Dimensions, and Sepia Filter

	When I enter "cat" in the input
	And enter "A" for collage shape
	And I click on the options button
	And enter "0" for collage border width
	And enter "white" for collage border color
	And enter "0" for photo border width
	And enter "white" for photo border color
	And enter "-35" for minimum rotation
	And enter "35" for maximum rotation
	And select "Sepia" for filter
	And click on the set options button
	And click on the build collage button
	And wait 30 seconds
	Then there is a collage with those features

Scenario: Collage with No Collage Border, No Photo Border, -35 to 35 Degree Rotation, 800x600 Dimensions, and Black and White Filter

	When I enter "cat" in the input
	And enter "A" for collage shape
	And I click on the options button
	And enter "0" for collage border width
	And enter "white" for collage border color
	And enter "0" for photo border width
	And enter "white" for photo border color
	And enter "-35" for minimum rotation
	And enter "35" for maximum rotation
	And select "Black and White" for filter
	And click on the set options button
	And click on the build collage button
	And wait 30 seconds
	Then there is a collage with those features

Scenario: Collage with a Red Collage Border, No Photo Border, No Rotation, 800x600 Dimensions, and Black and White Filter

	When I enter "cat" in the input
	And enter "A" for collage shape
	And I click on the options button
	And enter "5" for collage border width
	And enter "red" for collage border color
	And enter "0" for photo border width
	And enter "white" for photo border color
	And enter "0" for minimum rotation
	And enter "0" for maximum rotation
	And select "Black and White" for filter
	And click on the set options button
	And click on the build collage button
	And wait 30 seconds
	Then there is a collage with those features

Scenario: Collage with a Red Photo Border, No Collage Border, No Rotation, 800x600 Dimensions, and Black and White Filter

	When I enter "cat" in the input
	And enter "A" for collage shape
	And I click on the options button
	And enter "0" for collage border width
	And enter "white" for collage border color
	And enter "5" for photo border width
	And enter "red" for photo border color
	And enter "0" for minimum rotation
	And enter "0" for maximum rotation
	And select "Black and White" for filter
	And click on the set options button
	And click on the build collage button
	And wait 30 seconds
	Then there is a collage with those features

Scenario: Collage with a Red Photo Border, Red Collage Border, No Rotation, 800x600 Dimensions, and Black and White Filter

	When I enter "cat" in the input
	And enter "A" for collage shape
	And I click on the options button
	And enter "5" for collage border width
	And enter "red" for collage border color
	And enter "5" for photo border width
	And enter "red" for photo border color
	And enter "0" for minimum rotation
	And enter "0" for maximum rotation
	And select "Black and White" for filter
	And click on the set options button
	And click on the build collage button
	And wait 30 seconds
	Then there is a collage with those features

Scenario: Collage with a Red Collage Border, No Photo Border, 45 Rotation, 800x600 Dimensions, and Black and White Filter

	When I enter "cat" in the input
	And enter "A" for collage shape
	And I click on the options button
	And enter "5" for collage border width
	And enter "red" for collage border color
	And enter "0" for photo border width
	And enter "white" for photo border color
	And enter "45" for minimum rotation
	And enter "45" for maximum rotation
	And select "Black and White" for filter
	And click on the set options button
	And click on the build collage button
	And wait 30 seconds
	Then there is a collage with those features

Scenario: Collage with a Red Photo Border, No Collage Border, 45 Rotation, 800x600 Dimensions, and Black and White Filter

	When I enter "cat" in the input
	And enter "A" for collage shape
	And I click on the options button
	And enter "0" for collage border width
	And enter "white" for collage border color
	And enter "5" for photo border width
	And enter "red" for photo border color
	And enter "45" for minimum rotation
	And enter "45" for maximum rotation
	And select "Black and White" for filter
	And click on the set options button
	And click on the build collage button
	And wait 30 seconds
	Then there is a collage with those features

Scenario: Collage with a Red Photo Border, Red Collage Border, 45 Rotation, 800x600 Dimensions, and Black and White Filter

	When I enter "cat" in the input
	And enter "A" for collage shape
	And I click on the options button
	And enter "5" for collage border width
	And enter "red" for collage border color
	And enter "5" for photo border width
	And enter "red" for photo border color
	And enter "45" for minimum rotation
	And enter "45" for maximum rotation
	And select "Black and White" for filter
	And click on the set options button
	And click on the build collage button
	And wait 30 seconds
	Then there is a collage with those features

Scenario: Collage with No Collage Border, No Photo Border, No Rotation, 1000x800 Dimensions, and Black and White Filter

	When I enter "cat" in the input
	And enter "A" for collage shape
	And I click on the options button
	And enter "0" for collage border width
	And enter "white" for collage border color
	And enter "0" for photo border width
	And enter "white" for photo border color
	And enter "0" for minimum rotation
	And enter "0" for maximum rotation
	And enter "1000" for collage width
	And enter "800" for collage height
	And select "Black and White" for filter
	And click on the set options button
	And click on the build collage button
	And wait 30 seconds
	Then there is a collage with those features

Scenario: Collage with No Collage Border, No Photo Border, 45 Rotation, 1000x800 Dimensions, and Black and White Filter

	When I enter "cat" in the input
	And enter "A" for collage shape
	And I click on the options button
	And enter "0" for collage border width
	And enter "white" for collage border color
	And enter "0" for photo border width
	And enter "white" for photo border color
	And enter "45" for minimum rotation
	And enter "45" for maximum rotation
	And enter "1000" for collage width
	And enter "800" for collage height
	And select "Black and White" for filter
	And click on the set options button
	And click on the build collage button
	And wait 30 seconds
	Then there is a collage with those features

Scenario: Collage with Red Collage Border, No Photo Border, 45 Rotation, 1000x800 Dimensions, and Black and White Filter

	When I enter "cat" in the input
	And enter "A" for collage shape
	And I click on the options button
	And enter "5" for collage border width
	And enter "red" for collage border color
	And enter "0" for photo border width
	And enter "white" for photo border color
	And enter "45" for minimum rotation
	And enter "45" for maximum rotation
	And enter "1000" for collage width
	And enter "800" for collage height
	And select "Black and White" for filter
	And click on the set options button
	And click on the build collage button
	And wait 30 seconds
	Then there is a collage with those features

Scenario: Collage with No Collage Border, Red Photo Border, 45 Rotation, 1000x800 Dimensions, and Black and White Filter

	When I enter "cat" in the input
	And enter "A" for collage shape
	And I click on the options button
	And enter "0" for collage border width
	And enter "white" for collage border color
	And enter "5" for photo border width
	And enter "red" for photo border color
	And enter "45" for minimum rotation
	And enter "45" for maximum rotation
	And enter "1000" for collage width
	And enter "800" for collage height
	And select "Black and White" for filter
	And click on the set options button
	And click on the build collage button
	And wait 30 seconds
	Then there is a collage with those features

Scenario: Presence of Website title, Topic input box, Collage shape input box, Build collage button and Collage options button

	Then there is a title
	Then there is a topic input box
	Then there is a collage shape input box
	Then there is a build collage button
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

Scenario: Loading Animation
	When I enter "cat" in the input
	And enter "A" for collage shape
	And click the build collage button
	Then a loading animation appears

