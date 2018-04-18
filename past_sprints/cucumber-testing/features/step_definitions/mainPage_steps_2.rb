Given(/^I am on the Main Page$/) do
  visit "http://localhost:8080/CS310-ProjectTwo/index.jsp"
end

Then(/^then there is a title$/) do
  expect(page).to have_css('#title')
end

Then(/^there is a topic input box$/) do
  expect(page).to have_css('#searchtext')
end

Then(/^there is a collage shape input box$/) do
  expect(page).to have_css('#shapetext')
end

Then(/^there is a collage options selection$/) do
  expect(page).to have_css('#collageoptions')
end

Then(/^there is a save to history button$/) do
  expect(page).to have_css('#savetohistory')
end

Then(/^there is a build collage button$/) do
  expect(page).to have_css('#searchbutton')
end

When(/^enter "([^"]*)" for collage shape$/) do |arg1|
  fill_in('shapetext', :with => arg1)
end

Then(/^I am on the Collage Page$/) do
  expect(page).to have_content('Collage for Topic')
end

When(/^I enter "([^"]*)" in the input$/) do |arg1|
  fill_in('searchtext', :with => arg1)
end

When(/^set collage options$/) do
  #TODO
end

When(/^click the build collage button$/) do
  click_button('Build Collage')
end
