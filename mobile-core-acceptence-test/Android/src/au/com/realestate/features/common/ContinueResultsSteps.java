package au.com.realestate.features.common;

import au.com.realestate.features.ReaStepDefinition;
import cuke4duke.StepMother;
import cuke4duke.annotation.I18n.EN.Given;
import cuke4duke.annotation.I18n.EN.When;
import cuke4duke.annotation.I18n.EN.Then;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class ContinueResultsSteps extends ReaStepDefinition {
    public ContinueResultsSteps(StepMother stepMother) {
        super(stepMother);
    }

    @Given("^there are 2 pages of results for \"([^\"]*)\"$")
    public void thereAre2PagesOfResultsForRoma_(String arg1) {
    }

    @When("^I search for listings in \"([^\"]*)\"$")
    public void iSearchForListingsInRoma_(String term) {
        Given("I started the app and have activated the search view");
        When("I enter '" + term + "' into the search bar using the keyboard");
        When("I press the search button");
    }

    @When("^I scroll to the end of the list$")
    public void iScrollToTheEndOfTheList() throws InterruptedException {
        waitUntilSearchFinished("search_bar_loading_img_pending");
        helper.scrollDown(35);
        Thread.sleep(10000);
    }

    @Then("^I should see an activity spinner which indicates that more results are loading$")
    public void iShouldSeeAnActivitySpinnerWhichIndicatesThatMoreResultsAreLoading() {
    }

    @Then("^the network indicator should animate while this query is running$")
    public void theNetworkIndicatorShouldAnimateWhileThisQueryIsRunning() {
    }

    @Then("^the new results should be displayed after the original results$")
    public void theNewResultsShouldBeDisplayedAfterTheOriginalResults() {
        waitUntilSearchFinished("search_bar_loading_img_pending");
    }

    @Then("^the first new listing should be visible above\\/ahead of the fold$")
    public void theFirstNewListingShouldBeVisibleAboveaheadOfTheFold() throws InterruptedException {
        helper.scrollDown(35);
        Thread.sleep(10000);
        assertThat(helper.findElementById("listing_screen_list_view").findElementByPartialText("Via Capo le Case"), not(nullValue()));
    }

    @Then("^I should not see an activity spinner$")
    public void iShouldNotSeeAnActivitySpinner() throws InterruptedException {
    }
}
