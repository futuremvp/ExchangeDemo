package au.com.realestate.features.common;

import au.com.realestate.features.ReaStepDefinition;
import au.com.realestate.test.operations.MainScreen;
import au.com.realestate.test.operations.TextInput;
import cuke4duke.StepMother;
import cuke4duke.annotation.I18n;
import cuke4duke.annotation.I18n.EN.Given;
import cuke4duke.annotation.I18n.EN.When;
import cuke4duke.annotation.I18n.EN.Then;

public class SearchSteps extends ReaStepDefinition {
    public SearchSteps(StepMother stepMother) {
        super(stepMother);
    }

    @Given("^I started the app and have activated the search view$")
    public void I_have_activated_the_search_view() {
        MainScreen mainScreen = new MainScreen();
        mainScreen.open();
        mainScreen.openSearchScreen();
    }

    @Given("^there are no location pills in the search bar$")
    public void thereAreNoLocationPillsInTheSearchBar() {

    }

    @Given("^I have typed \"([^\"]*)\" into the search bar$")
    public void iHaveTypedThePIntoTheSearchBar(String suggest) {
        enterTextToSearchBar(suggest);
    }

    @When("^I enter '([^']*)' into the search bar using the keyboard$")
    public void iEnterSeventeenMileRocksIntoTheSearchBarUsingTheKeyboard(String term) {
        enterTextToSearchBar(term);
    }

    @When("^I enter \"([^\"]*)\" into the search bar$")
    public void iEnterIntoSearchBar(String term) {
        enterTextToSearchBar(term);
    }

    private void enterTextToSearchBar(String term) {
        new TextInput("search_edit_text", term).action();
    }

}

