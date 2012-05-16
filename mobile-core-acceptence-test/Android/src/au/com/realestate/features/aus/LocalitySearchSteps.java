package au.com.realestate.features.aus;

import au.com.realestate.features.ReaStepDefinition;
import au.com.realestate.instrument.client.ReaAndroidElement;
import au.com.realestate.test.operations.GotoSearchScreen;
import au.com.realestate.test.operations.MainScreen;
import au.com.realestate.test.operations.TextInput;
import com.google.android.testing.nativedriver.common.AndroidKeys;
import cuke4duke.StepMother;
import cuke4duke.Table;
import cuke4duke.annotation.I18n;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class LocalitySearchSteps extends ReaStepDefinition {
    public LocalitySearchSteps(StepMother stepMother) {
        super(stepMother);
    }

    @I18n.EN.Given("^there is a listing \"([^\"]*)\"$")
    public void thereIsAListing42NapierStreet(String locality) {
        new MainScreen().action();
        new GotoSearchScreen().action();
    }

    @I18n.EN.Given("^there is a second listing \"([^\"]*)\"$")
    public void thereIsASecondListing25DGrantStreet(String arg1) {
    }

    @I18n.EN.When("^I tap on the \"([^\"]*)\" icon to add \"([^\"]*)\"$")
    public void iTapOnThePlusIconToAddFitzroy(String icon, String locality) throws InterruptedException {
        waitUntilSearchFinished("search_area_loading_img_pending");
        List<ReaAndroidElement> suggestions = helper.findElementsByClassName("au.com.realestate.components.ReaSuggestionItemView");
        String postcodeWithSuburb = locality.split(",")[1].trim().toLowerCase();

        for (ReaAndroidElement suggestion : suggestions) {
            String suggestionText = suggestion.findElementById("search_area_suggest_item_title").getText().toLowerCase();
            if (suggestionText.contains(postcodeWithSuburb)) {
                suggestion.findElementById("search_area_suggest_icon").click();
                break;
            }
        }

        Thread.sleep(1000);
    }

    @I18n.EN.When("^I tap the \"([^\"]*)\" button on the keyboard$")
    public void iTapTheSearchButtonOnTheKeyboard(String arg1) {
        helper.findElementById("search_edit_text").sendKeys(AndroidKeys.ENTER);
        waitUntilSearchFinished("search_bar_loading_img_pending");
    }

    @I18n.EN.Then("^I should see the following search results$")
    public void iShouldSeeTheFollowingSearchResultsWithTable(cuke4duke.Table table) {
        List<ReaAndroidElement> resultItems = getHelper().findElementsByClassName("au.com.realestate.components.SearchResultItem");
        List<String> titles = getTitles(table);
        for (ReaAndroidElement resultItem : resultItems) {
            String searchResultItemTitle = resultItem.findElementById("search_item_title").getText();
            assertThat(titles.contains(searchResultItemTitle), is(true));
        }
    }

    private List<String> getTitles(Table table) {
        List<String> titles = new ArrayList<String>();
        for (List<String> row : table.rows()) {
            titles.add(row.get(0));
        }
        return titles;
    }

}

