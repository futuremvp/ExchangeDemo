package au.com.realestate.features.aus;

import au.com.realestate.features.ReaStepDefinition;
import au.com.realestate.test.framework.Function;
import cuke4duke.StepMother;
import cuke4duke.annotation.I18n.EN.Given;
import cuke4duke.annotation.I18n.EN.Then;
import cuke4duke.annotation.I18n.EN.When;

import static org.junit.Assert.fail;

public class TapToDialAgentSteps extends ReaStepDefinition {

    public TapToDialAgentSteps(StepMother stepMother) {
        super(stepMother);
    }

    @Given("^the agent number \"([^\"]*)\" is malformed$")
    public void theAgentNumberBobIsMalformed(String number) {
    }

    @When("^I search for the suburb \"([^\"]*)\"$")
    public void iSearchForTheSuburbBadPhone_(String term) {
        searchByTerm(term);
    }

    @When("^I tap the property of \"([^\"]*)\"$")
    public void iTapThePropertyOfAddress(String address) {
        helper.findElementByTextWithScrollDown(address).click();
        waitUntilPropertyDetailScreenDisplayed();
    }

    @When("^I tap on the call to action for \"([^\"]*)\"$")
    public void iTapOnTheCallToActionForBob_(String number) {
    }

    @Then("^the call to action \"([^\"]*)\" highlighted$")
    public void theCallToActionIsHighlighted(String highlightedStatus) {
    }

    @Then("^the following \"([^\"]*)\" should occur$")
    public void theFollowingCallBobShouldOccur(String result) {
    }
}
