package au.com.realestate.features.common;

import au.com.realestate.features.ReaStepDefinition;
import au.com.realestate.test.operations.GotoMapScreen;
import au.com.realestate.test.operations.GotoSearchScreen;
import au.com.realestate.test.operations.MainScreen;
import au.com.realestate.test.operations.TextInput;
import com.google.android.testing.nativedriver.common.AndroidKeys;
import cuke4duke.StepMother;
import cuke4duke.annotation.I18n.EN.Given;
import cuke4duke.annotation.I18n.EN.Then;
import cuke4duke.annotation.I18n.EN.When;
import cuke4duke.annotation.Pending;

import java.net.MalformedURLException;

import static org.junit.Assert.*;

public class TermSearchSteps extends ReaStepDefinition {

    public TermSearchSteps(StepMother stepMother) {
        super(stepMother);
    }

    @Given("^performing a search for '([^']*)' has a 5 second delay$")
    public void performing_a_search_for_SeventeenMileRocks_has_a_5_second_delay(String term) {
    }

    @When("^I press the search button$")
    public void iPressTheSearchButton() throws MalformedURLException {
        helper.findElementById("search_edit_text").sendKeys(AndroidKeys.ENTER);
    }

    @Then("^the network icon animation should activate$")
    public void theNetworkIconAnimationShouldActivate() {
    }

    @Then("^I should see a loading spinner$")
    public void iShouldSeeALoadingSpinner() throws MalformedURLException {
        assertTrue(helper.findElementById("search_bar_loading_img_pending").isDisplayed());
    }

    @Then("^the Cancel button should disappear with transition$")
    public void theCancelButtonShouldDisappearWithTransition() {
    }

    @Then("^the keyboard slides out to the bottom of the screen$")
    public void theKeyboardSlidesOutToTheBottomOfTheScreen() {
    }

    @Then("^a list of search results for '([^']*)' should appear with transition$")
    public void aListOfSearchResultsForSeventeenMileRocksShouldAppearWithTransition(String term) {
        waitUntilSearchFinished("search_bar_loading_img_pending");
        assertFalse("Should have search result", helper.findElementsByPartialText(term).isEmpty());
    }

    @Then("^the search bar text field should display '([A-Za-z\\/, 0-9]*)' \\(which is returned via API\\)$")
    public void theSearchBarTextFieldShouldDisplaySeventeenMileRocksQLD4073whichIsReturnedViaAPI(String title) throws InterruptedException {
        assertEquals("The search bar text field should display '" + title + "'",
                title, helper.findDisplayedElementById("search_bar_hint").getText());
    }

    @Given("^I have performed a search for \"([^\"]*)\"$")
    @Pending
    public void iHavePerformedASearchForThePatch_(String arg1) {
    }

    @Given("^I am on the Map View$")
    public void iAmOnTheMapView() {
        new MainScreen().action();
        new GotoMapScreen().action();
    }

    @When("^I tap in the search bar$")
    @Pending
    public void iTapInTheSearchBar() {
    }

    @When("^perform a search for \"([^\"]*)\"$")
    @Pending
    public void performASearchForPrahran_(String arg1) {
    }

    @Then("^the map should adjust to display results in \"([^\"]*)\"$")
    @Pending
    public void theMapShouldAdjustToDisplayResultsInPrahran_(String arg1) {
    }
}
