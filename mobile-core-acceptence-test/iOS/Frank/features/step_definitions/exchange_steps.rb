Then /^I should see a list of exchange rates$/ do
  steps %Q{
    Then I should see a navigation bar titled "Exchange Rates"
  }
end

And /^I should see "([^\"]*)" with the value of "([\d\.]+)"$/ do |currencyCode, exchangeRate|
  steps %Q{
    Then I should see an element of class "UITableViewCell" with name "#{currencyCode}" with the following labels: "#{exchangeRate}"
  }  
end