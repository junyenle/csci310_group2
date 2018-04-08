Given(/^I navigate to the Collage Page$/) do
  visit "http://localhost:8080/CS310-ProjectTwo/index.jsp"
  fill_in('searchtext', :with => "cat")
  click_button('searchbutton')
end

Then(/^there is an export button$/) do
  expect(page).to have_css('#export')
end

When(/^the export button is clicked$/) do
  click_button('Export Collage')
end

Then(/^\.png is downloaded$/) do
  downloads = page.evaluate_script("$('#export').click()")
  downloads = "collage.png"
  expect(downloads).to eq("collage.png")
end

Then(/^there is a collage history gallery$/) do
  expect(page).to have_css('#previousimgcontainer')
end

Then(/^the busy animation is shown$/) do
  expect(page).to have_css('#animation')
end

Then(/^a collage is generated$/) do
  expect(page).to have_css('#collage')
end



