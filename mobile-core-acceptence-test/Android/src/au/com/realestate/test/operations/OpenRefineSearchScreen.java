package au.com.realestate.test.operations;

public class OpenRefineSearchScreen extends ReaBaseScreen {
    @Override
    public void action() {
        helper.findDisplayedElementById("search_filter_click_bar").click();
    }
}
