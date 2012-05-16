package au.com.realestate.features.aus;

import au.com.realestate.features.ReaStepDefinition;
import au.com.realestate.test.operations.ClickSearchPropertiesTo;
import au.com.realestate.test.operations.OpenRefineSearchScreen;
import au.com.realestate.test.operations.ClickSecondTitleBarButton;
import cuke4duke.StepMother;
import cuke4duke.annotation.I18n.EN.Given;
import cuke4duke.annotation.I18n.EN.Then;
import cuke4duke.annotation.I18n.EN.When;
import org.junit.Assert;

public class FilterSummarySteps extends ReaStepDefinition {

    public FilterSummarySteps(StepMother stepMother) {
        super(stepMother);
    }

    @Given("^I have searched for properties to \"([^\"]*)\" in \"([^\"]*)\"$")
    public void iHaveSearchedForPropertiesToRentInRichmond_(String channel, String term) {
        Given("I started the app and have activated the search view");
        When("I enter '" + term + "' into the search bar using the keyboard");
        When("I press the search button");
        waitUntilSearchFinished("search_bar_loading_img_pending");


        new OpenRefineSearchScreen().action();
        new ClickSearchPropertiesTo().action();
        new ClickSecondTitleBarButton().action();
    }

    @When("^the search result page is shown$")
    public void theSearchResultPageIsShown() {
        waitUntilSearchFinished("search_bar_loading_img_pending");
    }

    @Then("^I should see \"([^\"]*)\" within properties$")
    public void iShouldSeePerWeekWithinProperties(String text) {
        Assert.assertFalse(getHelper().findElementsByPartialText(text).isEmpty());
    }
}
