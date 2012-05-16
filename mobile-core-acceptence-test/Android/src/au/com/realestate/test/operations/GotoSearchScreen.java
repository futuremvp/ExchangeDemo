package au.com.realestate.test.operations;

public class GotoSearchScreen extends ReaBaseScreen {

    public void action() {
        helper.findDisplayedElementById("search_bar_hint").click();
    }
}
