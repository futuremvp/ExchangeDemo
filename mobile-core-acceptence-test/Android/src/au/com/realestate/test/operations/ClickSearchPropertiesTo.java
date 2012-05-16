package au.com.realestate.test.operations;

public class ClickSearchPropertiesTo extends ReaBaseScreen {

    @Override
    public void action() {
        helper.findDisplayedElementById("refine_search_channel").click();
    }
}
