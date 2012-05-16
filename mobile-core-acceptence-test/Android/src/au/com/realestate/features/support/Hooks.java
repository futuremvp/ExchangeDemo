package au.com.realestate.features.support;

import au.com.realestate.features.ReaStepDefinition;
import au.com.realestate.test.operations.ClickSearchPropertiesTo;
import au.com.realestate.test.operations.OpenRefineSearchScreen;
import au.com.realestate.test.operations.ClickSecondTitleBarButton;
import cuke4duke.StepMother;
import cuke4duke.annotation.After;

public class Hooks extends ReaStepDefinition {
    public Hooks(StepMother stepMother) {
        super(stepMother);
    }

    @After("@quit_app_from_main_screen")
    public void runAfterEachScenario() {
        helper.quitAppFromMainActivity();
    }

    @After("@reset_search_channel_to_buy")
    public void reset_search_channel_to_buy() {
        new OpenRefineSearchScreen().action();
        new ClickSearchPropertiesTo().action();
        new ClickSecondTitleBarButton().action();
        waitUntilSearchFinished("search_bar_loading_img_pending");
    }
}
