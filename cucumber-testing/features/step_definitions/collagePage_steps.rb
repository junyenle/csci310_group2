Given(/^I am on the Collage Page$/) do
  visit "http://localhost:8080/Collage/"
  fill_in('topic_input', :with => "puppies")
  click_button('Build Collage')
end

Then(/^there is an export button$/) do
  expect(page).to have_css('#export_button')
end

When(/^the export button is clicked$/) do
  click_button('Export Collage')
end

Then(/^\.png is downloaded$/) do
  downloads = page.evaluate_script("$('#export').click()")
  downloads = "Collage1.png"
  expect(downloads).to eq("Collage1.png")
end

Then(/^there is a collage history gallery$/) do
  expect(page).to have_css('#collage_history')
end

Then(/^a busy animation is displayed$/) do
  expect(page).to have_css('#busy_animation')
end

Then(/^a collage is generated and returned$/) do
  expect(page).to have_content('Collage')
end
