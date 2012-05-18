Then /^I should see a list of exchange rates$/ do
  steps %Q{
    When I scroll to the bottom of the table
    When I scroll to the top of the table
    Then I should see a navigation bar titled "Exchange Rates"
  }
end

And /^I should see "([^\"]*)" with the value of "([\d\.]+)"$/ do |currencyCode, exchangeRate|
  steps %Q{
    Then I should see an element of class "UITableViewCell" with name "#{currencyCode}" with the following labels: "#{exchangeRate}"
    And I touch the table cell marked "#{currencyCode}"
  }  
end

When /^I scroll to the bottom of the table$/ do
  tables_scrolled = frankly_map( "tableView", "scrollToBottom" )
  raise "no table could be found to scroll" if tables_scrolled.empty?
  sleep 0.5 # give the UI a chance to animate the scrolling
end

When /^I scroll to the top of the table$/ do
  num_rows_array = frankly_map( "tableView first", "numberOfRowsInSection:", 0 ) 
  tables_scrolled = frankly_map( "tableView", "scrollDownRows:", - num_rows_array.first + 1 )
  raise "no table could be found to scroll" if tables_scrolled.empty?
  sleep 0.5 # give the UI a chance to animate the scrolling
end