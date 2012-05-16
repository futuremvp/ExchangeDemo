package au.com.realestate.features.aus;

import au.com.realestate.features.ReaStepDefinition;
import au.com.realestate.test.framework.Function;
import cuke4duke.StepMother;
import cuke4duke.annotation.I18n.EN.Given;
import cuke4duke.annotation.I18n.EN.Then;
import cuke4duke.annotation.I18n.EN.When;
import junit.framework.Assert;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class PropertydetailsSteps extends ReaStepDefinition {
    public PropertydetailsSteps(StepMother stepMother) {
        super(stepMother);
    }

    @Given("^the listing \"([^\"]*)\" in \"([^\"]*)\" has a property no\\. 1$")
    public void theListing18GrantullaRoadInThePatchHasAPropertyNo12345678(String address, String term) {
        searchByTerm(term);
    }

    @When("^I view the property details for \"([^\"]*)\"$")
    public void iViewThePropertyDetailsFor18GrantullaRoad_(String address) {
        helper.findElementByText(address).click();
        helper.waitUntil(new Function<Object>() {
            @Override
            public boolean apply() {
                return helper.isInternalActivity();
            }

            @Override
            public Object getValue() {
                return null;
            }
        });
    }

    @Then("^I should see the property no\\. ([\\d]+) show exist on property details page$")
    public void iShouldSeeThePropertyNo12345678ShowExistOnPropertyDetailsPage(String id) {
        assertTrue(helper.findElementById("property_detail_property_no").getText().contains(id));
    }

    @Given("^I have searched for properties in \"([^\"]*)\"$")
    public void iHaveSearchedForPropertiesInThePatch_(String term) {
        searchByTerm(term);
    }

    @Then("^I should see the following in the core details section:$")
    public void iShouldSeeTheFollowingInTheCoreDetailsSectionWithTable(cuke4duke.Table table) {
        List<List<String>> rows = table.rows();
        HashMap hashMap = composeAttributeIdHashMap();

        for (List<String> row : rows) {
            String elementText = getElementText(hashMap, row);
            Assert.assertTrue(elementText.contains(row.get(1)));
        }
    }

    private HashMap composeAttributeIdHashMap() {
        HashMap hashMap = new HashMap();
        hashMap.put("street address", "property_detail_address_first_row");
        hashMap.put("land size", "property_detail_land_size");
        hashMap.put("locality", "property_detail_address_second_row");
        hashMap.put("price", "property_detail_price");
        hashMap.put("property type", "property_detail_property_type");
        return hashMap;
    }

    private String getElementText(HashMap hashMap, List<String> row) {
        return helper.findElementById((String) hashMap.get(row.get(0))).getText();
    }
}
