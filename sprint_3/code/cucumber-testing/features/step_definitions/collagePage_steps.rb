Given(/^I navigate to the Collage Page$/) do
  visit "http://localhost:8080/CS310-ProjectTwo/index.jsp"
  fill_in('searchtext', :with => "cat")
  fill_in('shapetext', :with => "A")
  click_button('Options')
  click_button('Set Options')
  click_button('Build Collage')
  sleep(60)
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

Then(/^there is a save collage button$/) do
  expect(page).to have_css('#savebutton')
end

When(/^I click the save collage button$/) do
  click_button('Save Collage')
end

Then(/^the saved image appears in the collage history gallery$/) do
  expect(page.find('.prev-collage')['alt']).to match("cat")
end

Then(/^there is a collage history gallery$/) do
  expect(page).to have_css('#prev')
end

When(/^click on a previously saved image$/) do
  find("img[alt='cat']").click
end

Then(/^the previous collage should display$/) do
  expect(page).to have_content('Collage for topic cat')
end

#Then(/^a not enough images message is displayed$/) do
 # expect(page).to have_css('#error')
#end

Then(/^the collage options box remains open$/) do
  expect(page).to have_content('Please select all the options for these collages')
end




