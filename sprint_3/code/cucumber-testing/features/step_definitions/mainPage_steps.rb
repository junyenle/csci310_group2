Given(/^I am on the Main Page$/) do
  visit "http://localhost:8080/CS310-ProjectTwo/index.jsp"
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
  click_button('Options')
  fill_in('collageBorderWidth', :with => "")
end

When(/^I clear Collage Border Color$/) do
  click_button('Options')
  fill_in('collageBorderColor', :with => "")
end

When(/^I clear Photo Border Width$/) do
  click_button('Options')
  fill_in('photoBorderWidth', :with => "")
end

When(/^I clear Photo Border Color$/) do
  click_button('Options')
  fill_in('photoBorderColor', :with => "")
end

When(/^I clear Minimum Rotation$/) do
  click_button('Options')
  fill_in('minRotation', :with => "")
end

When(/^I clear Maximum Rotation$/) do
  click_button('Options')
  fill_in('maxRotation', :with => "")
end

When(/^I clear Collage Width$/) do
  click_button('Options')
  fill_in('collageWidth', :with => "")
end

When(/^I clear Collage Height$/) do
  click_button('Options')
  fill_in('collageHeight', :with => "")
end

When(/^I clear Filter$/) do
  click_button('Options')
  fill_in('filter', :with => "")
end

Then(/^collage options is open$/) do
  expect(page).to have_css('.vex-dialog-input')
end
