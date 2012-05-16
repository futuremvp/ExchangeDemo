package au.com.realestate.features.aus;

import au.com.realestate.features.ReaStepDefinition;
import au.com.realestate.instrument.client.ReaAndroidElement;
import cuke4duke.StepMother;
import cuke4duke.annotation.I18n;

import java.util.List;

public class MapSearchResultSteps extends ReaStepDefinition {
    public MapSearchResultSteps(StepMother stepMother) {
        super(stepMother);
    }

    @I18n.EN.When("^I  enter \"([^\"]*)\" in the search bar$")
    public void iEnterNoResultsInTheSearchBar(String term) {
//        When("I enter '" + term + "' into the search bar using the keyboard");
//        new GotoSearchScreen().action();
//        new SearchScreen("the patch" + AndroidKeys.ENTER).action();
    }

    @I18n.EN.When("^I tap the search button$")
    public void iTapTheSearchButton() {
//        When("I press the search button");
//        waitUntilSearchFinished();
//        System.out.println("search finished");
    }

    @I18n.EN.Then("^I should see not see any pins on the map$")
    public void iShouldSeeNotSeeAnyPinsOnTheMap() throws InterruptedException {
        Thread.sleep(2000);
        List<ReaAndroidElement> maps = getHelper().findElementsByClassName("au.com.realestate.components.map.ReaMapView");
        System.out.println("maps count: " + maps.size());
        Thread.sleep(2000);
        List<ReaAndroidElement> pins = getHelper().findElementsByClassName("au.com.realestate.app.BasicItemizedOverlay");
        System.out.println(pins.size());
    }

    @I18n.EN.Then("^the map zoom level will not change$")
    public void theMapZoomLevelWillNotChange() {
    }

}
