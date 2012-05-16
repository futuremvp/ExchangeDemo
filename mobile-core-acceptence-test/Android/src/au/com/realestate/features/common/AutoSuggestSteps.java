package au.com.realestate.features.common;

import au.com.realestate.features.ReaStepDefinition;
import au.com.realestate.instrument.client.ReaAndroidElement;
import au.com.realestate.test.operations.GotoSearchScreen;
import au.com.realestate.test.operations.MainScreen;
import cuke4duke.StepMother;
import cuke4duke.annotation.I18n;
import cuke4duke.annotation.I18n.EN.Given;
import cuke4duke.annotation.I18n.EN.When;
import cuke4duke.annotation.I18n.EN.Then;
import org.hamcrest.Matchers;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class AutoSuggestSteps extends ReaStepDefinition {
    public AutoSuggestSteps(StepMother stepMother) {
        super(stepMother);
    }

    @Then("^I should see a list of matching suggestions$")
    public void iShouldSeeAListOfMatchingSuggestions() {
        waitUntilSearchFinished("search_area_loading_img_pending");
        List<WebElement> suggestionItems = helper.findElementById("search_area_suggestions").findElementsByClassName("au.com.realestate.components.ReaSuggestionItemView");
        assertThat(suggestionItems.size(), is(7));
    }

    @Then("^each suggestion should show an \"Add\" accessory button \\(icon\\)$")
    public void eachSuggestionShouldShowAnAddAccessoryButtonIcon() {
    }

    @Given("^the search bar is currently empty$")
    public void theSearchBarIsCurrentlyEmpty() {
        Given("I started the app and have activated the search view");
    }

    @When("^I enter \"([^\"]*)\" which does not match any locations$")
    public void iEnterZzzWhichDoesNotMatchAnyLocations(String suggest) {
        When("I enter '" + suggest + "' into the search bar using the keyboard");
    }

    @Then("^I will briefly see a loading spinner in the suggestions heading$")
    public void iWillBrieflySeeALoadingSpinnerInTheSuggestionsHeading() {
        helper.findDisplayedElementById("search_area_loading_img_pending");
    }

    @Then("^I should see a \"([^\"]*)\" message$")
    public void iShouldSeeANoneAvailableMessage(String noneAvailable) {
        waitUntilSearchFinished("search_area_loading_img_pending");
        assertThat(helper.findElementByText(noneAvailable), not(nullValue()));
    }

    @Given("^I have tapped the search bar$")
    public void iHaveTappedTheSearchBar() {
        Given("I started the app and have activated the search view");
    }

    @When("^I type a multi\\-word suburb name such as \"([^\"]*)\"$")
    public void iTypeAMultiwordSuburbNameSuchAsKewEast_(String suggest) {
        When("I enter '" + suggest + "' into the search bar using the keyboard");
    }

    @Then("^the auto\\-suggest list should contain \"([^\"]*)\"$")
    public void theAutosuggestListShouldContainKewEastVIC3102(String suggestionResult) {
        waitUntilSearchFinished("search_area_loading_img_pending");
        assertThat(helper.findElementByText(suggestionResult), not(nullValue()));
    }
}
