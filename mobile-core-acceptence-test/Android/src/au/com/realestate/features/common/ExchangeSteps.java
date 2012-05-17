package au.com.realestate.features.common;

import au.com.realestate.features.ReaStepDefinition;
import au.com.realestate.test.framework.Function;
import au.com.realestate.test.operations.MainScreen;
import cuke4duke.StepMother;
import cuke4duke.annotation.I18n.EN.*;
import org.hamcrest.core.IsNull;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ExchangeSteps extends ReaStepDefinition {

    public ExchangeSteps(StepMother stepMother) {
        super(stepMother);
    }

    @Given("^I started the app$")
    public void iStartedTheApp() {
        new MainScreen().action();
    }

    @When("^I click \"([^\"]*)\" button$")
    public void iClickStartButton(String arg1) {
        helper.findElementById("main_start_button").click();
    }

    @When("^I wait for 3 second$")
    public void iWaitForSecond() {
        waitUntilScreenDisplayed();
    }

    @Then("^I should see a list of exchange rates$")
    public void iShouldSeeAListOfExchangeRates() throws InterruptedException {
        assertThat(helper.findElementById("exchange_screen_exchange_rates").isDisplayed(), is(true));
    }

    @Then("^I should see \"([^\"]*)\" with the value of \"([^\"]*)\"$")
    public void iShouldSeeEuropeWithTheValue(String label, String value) {
        helper.scrollDown(10);
        helper.scrollUp();
        assertThat(helper.findElementByText(value), IsNull.notNullValue());
    }

    @Then("^I can see a \"([^\"]*)\" button in the launch screen$")
    public void iCanSeeAStartButtonInTheLaunchScreen(String arg1) {
        assertThat(helper.findElementById("main_start_button").isDisplayed(), is(true));
    }

    private void waitUntilScreenDisplayed() {
        helper.waitUntil(new Function() {
            @Override
            public boolean apply() {
                return helper.findDisplayedElementById("exchange_screen_exchange_rates").isDisplayed();
            }

            @Override
            public Object getValue() {
                return null;
            }
        });
    }
}
