package au.com.realestate.features.common;

import au.com.realestate.features.ReaStepDefinition;
import au.com.realestate.instrument.client.ReaAndroidElement;
import au.com.realestate.test.operations.ClickSecondTitleBarButton;
import au.com.realestate.test.operations.MainScreen;
import com.google.android.testing.nativedriver.common.AndroidKeys;
import cuke4duke.StepMother;
import cuke4duke.annotation.I18n.EN.Given;
import cuke4duke.annotation.I18n.EN.Then;
import cuke4duke.annotation.I18n.EN.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class RecentLocalitiesSteps extends ReaStepDefinition {
    public RecentLocalitiesSteps(StepMother stepMother) {
        super(stepMother);
    }

    @Given("^I have just performed a search for \"([^\"]*)\"$")
    public void iHaveJustPerformedASearchForBarra_(String suggest) throws InterruptedException {
        Given("I started the app and have activated the search view");
        When("I enter '" + suggest + "' into the search bar using the keyboard");
        waitUntilSearchFinished("search_area_loading_img_pending");

        List<WebElement> suggestionItems = helper.findElementById("search_area_suggestions").findElementsByClassName("au.com.realestate.components.ReaSuggestionItemView");
        suggestionItems.get(0).click();

        new ClickSecondTitleBarButton().action();
    }

    @When("^I tap the search bar$")
    public void iTapTheSearchBar() {
        new MainScreen().openSearchScreen();
    }

    @Then("^I should see \"([^\"]*)\" in the \"([^\"]*)\" list$")
    public void iShouldSeeBarraNapoliInTheLocalitRecentiList(String suggest, String arg2) {
        ReaAndroidElement location = helper.findElementById("search_area_suggestions_recent_location").findElementByPartialText(suggest);
        assertThat(location, not(nullValue()));
    }
}
