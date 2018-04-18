Given(/^I am on the webpage$/) do
  visit "http://localhost:8080/CS310/index.html"
end

Then(/^there is a topic input box$/) do
  expect(page).to have_css('#topic_input')
end

Then(/^there is a collage shape input box$/) do
  expect(page).to have_css('#collage_shape_input')
end

Then(/^there is a collage options box$/) do
  expect(page).to have_css('#collage_options')
end

Then(/^there is a save to history button$/) do
  expect(page).to have_css('#save_to_history_button')
end

Then(/^there is a build collage button$/) do
  expect(page).to have_css('#build_collage_button')
end

When(/^I enter "([^"]*)" in the input$/) do |arg1|
  fill_in('topic_input', :with => arg1)
end

When(/^select A for collage shape$/) do
  select('A', : from => 'collage_shape_input')
end

When(/^set collage options$/) do
  #TODO
end

When(/^click the build collage button$/) do
  click_button('Build Collage')
end

Then(/^I am on the Collage Page$/) do
  expect(page).to have_content('Collage for Topic')
end

