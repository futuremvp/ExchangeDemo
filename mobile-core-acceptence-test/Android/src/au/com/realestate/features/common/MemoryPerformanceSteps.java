package au.com.realestate.features.common;

import au.com.realestate.features.ReaStepDefinition;
import au.com.realestate.instrument.client.ReaAndroidElement;
import au.com.realestate.test.framework.Function;
import au.com.realestate.test.operations.MainScreen;
import cuke4duke.StepMother;
import cuke4duke.annotation.I18n;

import java.util.List;

import static java.lang.Thread.sleep;

public class MemoryPerformanceSteps extends ReaStepDefinition {
    public MemoryPerformanceSteps(StepMother stepMother) {
        super(stepMother);
    }

    @I18n.EN.When("^I search properties in \"([^\"]*)\" and show the next \"([^\"]*)\" pages$")
    public void iSearchPropertiesInVicAndShowTheNext20Pages(String term, int pageCount) {
        When("I enter '" + term + "' into the search bar using the keyboard");
        When("I press the search button");
        waitUntilSearchFinished("search_bar_loading_img_pending");

        for (int i = 0; i < pageCount; i++) {
            helper.displayMenu();
            clickShowMoreButton();
            System.out.println("Show more: [" + i + "]");
            waitUntilShowMoreFinished();
        }
    }

    private void clickShowMoreButton() {
        List<ReaAndroidElement> menus = helper.findElementsByClassName("com.android.internal.view.menu.IconMenuItemView");
        menus.get(1).click();
    }

    private void waitUntilShowMoreFinished() {
        helper.waitUntil(new Function<Object>() {
            @Override
            public boolean apply() {
                ReaAndroidElement loading = helper.findElementByIdWithoutWaiting("notice_loading_text");
                return loading == null || !loading.isDisplayed();
            }

            @Override
            public Object getValue() {
                return null;
            }
        });
    }

    @I18n.EN.Then("^I scroll to top and should see app working normally$")
    public void iScrollToTopAndShouldSeeAppWorkingNormally() {
    }

    @I18n.EN.Given("^I started the app$")
    public void iStartedTheApp() {
        new MainScreen().action();
    }

    @I18n.EN.When("^show the next \"([^\"]*)\" pages$")
    public void showTheNext30Pages(int pageCount) {
        for (int i = 0; i < pageCount; i++) {
            helper.displayMenu();
            clickShowMoreButton();
            System.out.println("Show more: [" + i + "]");
            waitUntilShowMoreFinished();
        }
    }

    @I18n.EN.When("^I scroll down and bookmark \"([^\"]*)\" properties$")
    public void iScrollDownAndBookmark10Properties(int bookmarkCount) throws InterruptedException {
        for (int i = 0; i < bookmarkCount; ) {
            List<ReaAndroidElement> bookmarkButtons = helper.findElementsByClassName("au.com.realestate.widget.BookmarkButton");
            for (ReaAndroidElement button : bookmarkButtons) {
                button.click();
                i++;
            }
            helper.scrollDown(10);
            sleep(1000);
        }
    }

    @I18n.EN.Then("^I switch to bookmark screen and should see app working normally$")
    public void iSwitchToBookmarkScreenAndShouldSeeAppWorkingNormally() {
    }
}
