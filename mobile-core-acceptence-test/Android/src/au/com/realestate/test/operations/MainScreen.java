package au.com.realestate.test.operations;

import au.com.realestate.instrument.client.ReaAndroidElement;
import au.com.realestate.test.framework.Function;
import com.google.android.testing.nativedriver.common.AndroidNativeBy;

import java.util.List;

public class MainScreen extends ReaBaseScreen {

    private String activityString;
    public static final String AUS_MAIN_ACTIVITY = "au.com.realestate.aus.activity.AusMainActivity";
    public static final String CASA_MAIN_ACTIVITY = "it.casa.app.activity.CasaMainActivity";


    public MainScreen() {
        this.activityString = getMainActivityString();
    }
    
    protected String getMainActivityString() {
        String app = System.getProperty("app");
        if ("aus".equalsIgnoreCase(app)) {
            return AUS_MAIN_ACTIVITY;
        }
        return CASA_MAIN_ACTIVITY;
    }


    public void action() {
        driver.startActivity(activityString);
        helper.waitUntil(new Function() {
            @Override
            public boolean apply() {
                return !helper.findElementById("main_screen_landing_page").isDisplayed();
            }

            @Override
            public Object getValue() {
                return null;
            }
        });
    }

    public void open() {
        driver.startActivity(activityString);
        helper.waitUntil(new Function() {
            @Override
            public boolean apply() {
                return !helper.findElementById("main_screen_landing_page").isDisplayed();
            }

            @Override
            public Object getValue() {
                return null;
            }
        });
    }

    public void openSearchScreen() {
        helper.findDisplayedElementById("search_bar_hint").click();
    }

    public ReaAndroidElement getSearchResultItemMatchTheAddress(String address) {
        final List<ReaAndroidElement> elements = helper.findElementsByClassName("au.com.realestate.components.SearchResultItem");
        ReaAndroidElement targetElement = null;
        for (ReaAndroidElement searchResultItem : elements) {
            try {
                searchResultItem.findElement(AndroidNativeBy.text(address));
            } catch (Exception e) {
                continue;
            }
            targetElement = searchResultItem;
            break;
        }
        return targetElement;
    }
}
