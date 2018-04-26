Given(/^I navigate to the Collage Page$/) do
  visit "https://localhost:8444/CS310-ProjectTwo/index.jsp"
  fill_in('searchtext', :with => "cat")
  fill_in('shapetext', :with => "A")
  click_button('Options')
  click_button('Set Options')
  click_button('Build Collage')
  sleep(30)
end

Then(/^there is an export button$/) do
  expect(page).to have_css('#export')
end

When(/^the export button is clicked$/) do
  click_button('Export as PNG')
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
    sleep(30)
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

Then(/^there is no whales collage$/) do
  expect(page).to have_no_content("img[alt='whales']")
end

When(/^I click on the "([^"]*)" image$/) do |arg1|
  find("img[alt='"+arg1+"']").click
end

Then(/^there is no "([^"]*)" image$/) do |arg1|
  expect(page).to have_no_content("img[alt='"+arg1+"']")
end

Then(/^there is a "([^"]*)" image$/) do |arg1|
  expect(page).to have_content("img[alt='"+arg1+"']")
end

Then(/^there is a whales collage$/) do
  expect(page).to have_content("img[alt='cat']")
end

When(/^I click the download as PDF button$/) do
  click_button('Export as PDF')
end

Then(/^a PDF collage is downloaded$/) do
  downloads = page.evaluate_script("$('#png').click()")
  downloads = "file.pdf"
  expect(downloads).to eq("file.pdf")
end

When(/^I set a "Black and White" filter$/) do
  choose('bnw')
end

When(/^I set a "Greyscale" filter$/) do
  choose('greyscale')
end

When(/^I set a "Sepia" filter$/) do
  choose('sepia')
end

Then(/^the collage is Black and White$/) do
  expect(page).to have_css('.blackandwhite')
end

Then(/^the collage is Greyscale$/) do
  expect(page).to have_css('.greyscale')
end

Then(/^the collage is Sepia$/) do
  expect(page).to have_css('.sepia')
end

When(/^I reload the page$/) do
  page.evaluate_script("window.location.reload()")
end





