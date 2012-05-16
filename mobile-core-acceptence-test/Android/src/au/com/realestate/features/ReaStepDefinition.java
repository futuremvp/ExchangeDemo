package au.com.realestate.features;

import au.com.realestate.test.framework.Function;
import au.com.realestate.test.framework.ReaNativeDriverHelper;
import au.com.realestate.test.framework.ReaTestPlatform;
import au.com.realestate.test.operations.GotoSearchScreen;
import au.com.realestate.test.operations.SearchScreen;
import au.com.realestate.test.operations.MainScreen;
import com.google.android.testing.nativedriver.common.AndroidKeys;
import cuke4duke.StepMother;
import cuke4duke.Steps;

import static au.com.realestate.test.framework.ReaTestPlatform.getDefaultDriver;

public class ReaStepDefinition extends Steps {

    protected ReaNativeDriverHelper helper = new ReaNativeDriverHelper(getDefaultDriver());

    public ReaStepDefinition(StepMother stepMother) {
        super(stepMother);
    }

    protected ReaNativeDriverHelper getHelper() {
        return helper;
    }

    protected String getMainActivityString() {
        String app = System.getProperty("app");
        if ("aus".equalsIgnoreCase(app)) {
            return ReaTestPlatform.AUS_MAIN_ACTIVITY;
        }
        return ReaTestPlatform.CASA_MAIN_ACTIVITY;
    }

    protected void searchByTerm(String term) {
        new MainScreen().action();
        new GotoSearchScreen().action();
        new SearchScreen(term + AndroidKeys.ENTER).action();
    }

    protected void waitUntilPropertyDetailScreenDisplayed() {
        helper.waitUntil(new Function() {
            @Override
            public boolean apply() {
                return helper.findDisplayedElementById("property_detail_view").isDisplayed();
            }

            @Override
            public Object getValue() {
                return null;
            }
        });
    }

    protected void waitUntilSearchFinished(final String loadingImageId) {
        helper.waitUntil(new Function<Object>() {
            @Override
            public boolean apply() {
                return !helper.findElementById(loadingImageId).isDisplayed();
            }

            @Override
            public Object getValue() {
                return null;
            }
        });
    }
}
