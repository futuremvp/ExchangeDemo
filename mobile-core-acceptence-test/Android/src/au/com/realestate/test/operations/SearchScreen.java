package au.com.realestate.test.operations;

import au.com.realestate.test.framework.Function;

public class SearchScreen extends ReaBaseScreen {

    private String term;

    public SearchScreen(String term) {
        this.term = term;
    }

    public SearchScreen() {
    }

    @Override
    public void action() {
        new TextInput("search_edit_text", term).action();
        waitSearchToEnd();
    }

    private void waitSearchToEnd() {
        helper.waitUntil(new Function<Object>() {
            @Override
            public boolean apply() {
                return !helper.findElementById("search_bar_progress_bar").isDisplayed();
            }

            @Override
            public Object getValue() {
                return null;
            }
        });
    }

    public void searchBy(String term) {
        new TextInput("search_edit_text", term).action();
        waitSearchToEnd();
    }
}
