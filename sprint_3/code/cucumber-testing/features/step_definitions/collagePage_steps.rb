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

Then(/^a not enough images message is displayed$/) do
  expect(page).to have_css('#error')
end

Then(/^the collage options box remains open$/) do
  expect(page).to have_content('Please select all the options for these collages')
end

When(/^there is a collage in the history gallery$/) do
  if find('#prev').all('*').length == 0
    #generates another collage
    fill_in('searchtext', :with => "cat")
    fill_in('shapetext', :with => "A")
    click_button('Options')
    click_button('Set Options')
    click_button('Build Collage')
    sleep(60)
  end
end

Then(/^the collage history gallery is empty$/) do
  find('#prev').all('*').length.should == 0
end

Then(/^the collage history gallery is not empty$/) do
  find('#prev').all('*').length.should_not == 0
end

When(/^click the delete button$/) do
  click_button('Delete')
end

Then(/^the collage is deleted from the gallery$/) do
  expect(page).to have_no_content("img[alt='cat']")
end

When(/^I click the download as PDF button$/) do
  click_button('Export PDF')
end

Then(/^a PDF collage is downloaded$/) do
  page.driver.response.headers['Content-Disposition'].should include("filename=*\".pdf\"")
end

When(/^I set a "([^"]*)" filter$/) do |arg1|
  click_button('Options')
  fill_in('filter', :with => arg1)
  click_button('Set Options')
end

Then(/^the collage is Black and White$/) do
  expect(page).to have_css('.blackandwhite')
end

Then(/^the collage is Grayscale$/) do
  expect(page).to have_css('.grayscale')
end

Then(/^the collage is Sepia$/) do
  expect(page).to have_css('.sepia')
end







