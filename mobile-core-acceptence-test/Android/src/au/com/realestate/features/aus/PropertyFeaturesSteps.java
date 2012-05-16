package au.com.realestate.features.aus;

import au.com.realestate.features.ReaStepDefinition;
import cuke4duke.StepMother;
import cuke4duke.Table;
import cuke4duke.annotation.I18n.EN.Given;
import cuke4duke.annotation.I18n.EN.Then;
import cuke4duke.annotation.I18n.EN.When;

import java.util.List;

import static junit.framework.Assert.*;

public class PropertyFeaturesSteps extends ReaStepDefinition {

    public PropertyFeaturesSteps(StepMother stepMother) {
        super(stepMother);
    }

    @Given("^the property at \"([^\"]*)\" in \"([^\"]*)\" has the following features$")
    public void iHaveAPropertyWithAllFeatureTypes(String address, String term, Table table) {
        searchByTerm(term);
    }

    @When("^I select the property at \"([^\"]*)\" in \"([^\"]*)\"$")
    public void iShouldSeePropertyAtHasTheFollowingFeatures(String address, String term) {
        helper.findElementByText(address).click();
        waitUntilPropertyDetailScreenDisplayed();
    }

    @Then("^I will see the features headings \"([^\"]*)\", \"([^\"]*)\"$")
    public void iWillSeeTheFeaturesHeadingsIndoorOutdoor(String indoor, String outdoor) {
        assertFalse(helper.findElementsByPartialText(indoor).isEmpty());
        assertFalse(helper.findElementsByPartialText(outdoor).isEmpty());
    }

    @Then("^I will see the \"([^\"]*)\" features and all feature names will be in title case$")
    public void iWillSeeTheIndoorFeaturesWithTable(String arg1, cuke4duke.Table table) {
        for (List<String> list : table.rows()) {
            for (int i = 0; i < list.size(); i++) {
                String feature = list.get(i);
                assertNotNull(helper.findElementByText(feature));
            }
        }
    }

    @Then("^I will see the features heading \"([^\"]*)\"$")
    public void iWillSeeTheFeaturesHeadingOutdoorFeatures(String featureHeading) {
        assertNotNull(helper.findElementByText(featureHeading));
    }

    @Then("^I will not see any \"([^\"]*)\" features$")
    public void iWillNotSeeAnyIndoorFeatures(String featureHeading) {
        assertNull(helper.findElementByText(featureHeading));
    }

    @Given("^the property in \"([^\"]*)\" in \"([^\"]*)\" has no features$")
    public void iHaveAPropertyHasNoFeatures(String address, String term) {
        searchByTerm(term);
    }

    @Then("^I will not see any features listed$")
    public void iWillNotSeeAnyFeaturesListed() {
        assertNull(helper.findElementByText("Indoor Features"));
        assertNull(helper.findElementByText("Outdoor Features"));
    }


}