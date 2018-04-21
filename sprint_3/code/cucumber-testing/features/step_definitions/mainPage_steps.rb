Given(/^I navigate to the Main Page$/) do
  visit "http://localhost:8080/CS310-ProjectTwo/login.jsp"
  fill_in('username', :with => "username")
  fill_in('password', :with => "password")
  click_button('Log In')
end

When(/^I click on the options button$/) do
  click_button('Options')
end

Then(/^there is a collage border width text field$/) do
  expect(page).to have_field('collageBorderWidth')
end

Then(/^there is a collage border color text field$/) do
  expect(page).to have_field('collageBorderColor')
end

Then(/^there is a photo border width text field$/) do
  expect(page).to have_field('photoBorderWidth')
end

Then(/^there is a photo border color text field$/) do
  expect(page).to have_field('photoBorderColor')
end

Then(/^there is a minimum rotation text field$/) do
  expect(page).to have_field('minRotation')
end

Then(/^there is a maximum rotation text field$/) do
  expect(page).to have_field('maxRotation')
end

Then(/^there is a collage width text field$/) do
  expect(page).to have_field('collageWidth')
end

Then(/^there is a collage height text field$/) do
  expect(page).to have_field('collageHeight')
end

Then(/^there is a filter dropdown field$/) do
  expect(page).to have_field('filter')
end

When(/^wait (\d+) seconds$/) do |arg1|
  sleep(30)
end

Then(/^there is an insufficient number of images found error$/) do
  expect(page).to have_css('#error')
end

Then(/^the input box has placeholder for Enter topic$/) do
  expect(page).to have_xpath("//input[@placeholder='Enter topic']")
end

Then(/^the shape input box has placeholder for Enter shape$/) do
  expect(page).to have_xpath("//input[@placeholder='Enter shape']")
end

When(/^enter "([^"]*)" for collage border width$/) do |arg1|
  fill_in('collageBorderWidth', :with => arg1)
end

When(/^enter "([^"]*)" for collage border color$/) do |arg1|
  fill_in('collageBorderColor', :with => arg1)
end

When(/^enter "([^"]*)" for photo border width$/) do |arg1|
  fill_in('photoBorderWidth', :with => arg1)
end

When(/^enter "([^"]*)" for photo border color$/) do |arg1|
  fill_in('photoBorderColor', :with => arg1)
end

When(/^enter "([^"]*)" for minimum rotation$/) do |arg1|
  fill_in('minRotation', :with => arg1)
end

When(/^enter "([^"]*)" for maximum rotation$/) do |arg1|
  fill_in('maxRotation', :with => arg1)
end

When(/^click on the build collage button$/) do
  click_button('Build Collage')
end

Then(/^there is a collage with those features$/) do
  expect(page).to have_css('#collage')
end

When(/^enter "([^"]*)" for collage width$/) do |arg1|
  fill_in('collageWidth', :with => arg1)
end

When(/^enter "([^"]*)" for collage height$/) do |arg1|
  fill_in('collageHeight', :with => arg1)
end

When(/^select "([^"]*)" for filter$/) do |arg1|
  fill_in('filter', :with => arg1)
end
Then(/^there is a title$/) do
  expect(page).to have_css('#title')
end

Then(/^there is a topic input box$/) do
  expect(page).to have_css('#searchtext')
end

Then(/^there is a collage shape input box$/) do
  expect(page).to have_css('#shapetext')
end

Then(/^there is a build collage button$/) do
  expect(page).to have_css('#searchbutton')
end

Then(/^there is a collage options button$/) do
  expect(page).to have_css('#optionbutton')
end

When(/^I click on the collage options button$/) do
  click_button('Options')
end

When(/^click on the set options button$/) do
  click_button('Set Options')
end

When(/^I enter "([^"]*)" in the input$/) do |arg1|
  fill_in('searchtext', :with => arg1)
end

When(/^enter "([^"]*)" for collage shape$/) do |arg1|
  fill_in('shapetext', :with => arg1)
end

When(/^click on the collage options button$/) do
  click_button('Options')
end

When(/^click the build collage button$/) do
  click_button('Build Collage')
end

When(/^wait 60 seconds$/) do
  sleep(60)
end

Then(/^I am on the Collage Page$/) do
  expect(page).to have_content('Collage for topic')
end

When(/^I generate a collage for "([^"]*)" saying "([^"]*)"$/) do |arg1, arg2|
  fill_in('searchtext', :with => arg1)
  fill_in('shapetext', :with => arg2)
  click_button('Options')
  click_button('Set Options')
  click_button('Build Collage')
end

Then(/^I stay on the Main Page$/) do
  page.should have_no_content('Collage for topic')
end

When(/^I clear Collage Border Width$/) do
  fill_in('collageBorderWidth', :with => "")
end

When(/^I clear Collage Border Color$/) do
  fill_in('collageBorderColor', :with => "")
end

When(/^I clear Photo Border Width$/) do
  fill_in('photoBorderWidth', :with => "")
end

When(/^I clear Photo Border Color$/) do
  fill_in('photoBorderColor', :with => "")
end

When(/^I clear Minimum Rotation$/) do
  fill_in('minRotation', :with => "")
end

When(/^I clear Maximum Rotation$/) do
  fill_in('maxRotation', :with => "")
end

When(/^I clear Collage Width$/) do
  fill_in('collageWidth', :with => "")
end

When(/^I clear Collage Height$/) do
  fill_in('collageHeight', :with => "")
end

When(/^I clear Filter$/) do
  fill_in('filter', :with => "")
end

Then(/^collage options is open$/) do
  expect(page).to have_css('.vex-dialog-input')
end

Then(/^a loading animation appears$/) do
  expect(page).to have_css('#animation')
end
