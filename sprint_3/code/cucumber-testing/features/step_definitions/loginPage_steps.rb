Given(/^I am on the login page$/) do
  visit "http://localhost:8080/CS310-ProjectTwo/login.jsp"
end

Then(/^there is a username box$/) do
  expect(page).to have_css('#username')
end

Then(/^there is a password box$/) do
  expect(page).to have_css('#password')
end

Then(/^there is a login button$/) do
  expect(page).to have_css('#loginbutton')
end

When(/^I enter "([^"]*)" in the username box$/) do |arg1|
  fill_in('username', :with => arg1)
end

When(/^I enter "([^"]*)" in the password box$/) do |arg1|
  fill_in('password', :with => arg1)
end

When(/^click the login button$/) do
  click_button('Log In')
end

Then(/^I am on the Main Page for the first time$/) do
  expect(page).to have_content('Build Your Collage')
end

Then (/^I am on the Login Page still$/) do
  expect(page).to have_content('Login')
end

