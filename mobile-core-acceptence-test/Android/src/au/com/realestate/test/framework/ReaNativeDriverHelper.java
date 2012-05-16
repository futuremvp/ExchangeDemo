package au.com.realestate.test.framework;

import au.com.realestate.instrument.client.ReaAndroidElement;
import com.google.android.testing.nativedriver.client.AndroidNativeDriver;
import com.google.android.testing.nativedriver.client.AndroidNativeElement;
import com.google.android.testing.nativedriver.common.AndroidKeys;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ReaNativeDriverHelper {

    private int retryMaxCount = 30;
    private int retryIntervalTime = 4;
    private AndroidNativeDriver driver;

    private String[] entryActivityNames = {"CasaMainActivity", "AusMainActivity"};
    private String[] internalActivityNames = {"PropertyDetailsActivity",
            "RefineSearchActivity", "ContactAgentActivity", "MapAndDirectionActivity"
            , "SearchAreaActivity", "ReaPreferenceActivity"};

    public ReaNativeDriverHelper(AndroidNativeDriver driver) {
        this.driver = driver;
    }

    public void scrollDown(int scrollStep) {
        String steps = "";
        for (int i = 0; i < scrollStep; i++) {
            steps += AndroidKeys.DPAD_DOWN;
        }
        driver.getKeyboard().sendKeys(steps);
    }

    public ReaAndroidElement findElementByTextWithScrollDown(final String text) {
        return waitUntil(new Function<ReaAndroidElement>() {
            ReaAndroidElement element;

            @Override
            public boolean apply() {
                final AndroidNativeElement androidNativeElement = findElementByText(text);
                if (androidNativeElement == null) {
                    scrollDown(5);
                    return false;
                }
                element = new ReaAndroidElement(androidNativeElement, driver);
                return true;
            }

            @Override
            public ReaAndroidElement getValue() {
                return element;
            }
        });
    }

    public void quitAppFromMainActivity() {
        while (isInternalActivity()) {
            driver.navigate().back();
            sleep(1);
            String currentUrl = driver.getCurrentUrl();
            if (isFirstLevelActivity(currentUrl)) break;

            currentUrl = driver.getCurrentUrl();
        }

        driver.close();
    }

    public boolean isInternalActivity() {
        String currentUrl = driver.getCurrentUrl();
        for (String activityName : internalActivityNames) {
            if (currentUrl.contains(activityName)) return true;
        }
        return false;
    }

    private boolean isFirstLevelActivity(String currentUrl) {
        for (String activityName : entryActivityNames) {
            if (currentUrl.contains(activityName)) return true;
        }
        return false;
    }

    public ReaAndroidElement findElementById(final String id) {
        return waitUntil(new Function<ReaAndroidElement>() {
            ReaAndroidElement element;

            @Override
            public boolean apply() {
                try {
                    final AndroidNativeElement androidNativeElement = driver.findElement(By.id(id));
                    if (androidNativeElement == null) return false;
                    element = new ReaAndroidElement(androidNativeElement, driver);
                    return true;
                } catch (NoSuchElementException e) {
                    return false;
                }
            }

            @Override
            public ReaAndroidElement getValue() {
                return element;
            }
        });
    }

    public <T> T waitUntil(Function<T> function) {
        int retryCount = 0;
        do {
            retryCount++;
            if (function.apply()) return function.getValue();
            sleep(retryIntervalTime);
        } while (retryCount < retryMaxCount);
        throw new RuntimeException("Error: execute waitUntil timeout!");
    }

    protected void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public ReaAndroidElement findDisplayedElementById(final String id) {
        return waitUntil(new Function<ReaAndroidElement>() {
            ReaAndroidElement element;

            @Override
            public boolean apply() {
                try {
                    final AndroidNativeElement androidNativeElement = driver.findElement(By.id(id));

                    if (androidNativeElement == null) return false;
                    element = new ReaAndroidElement(androidNativeElement, driver);
                    if (element.isDisplayed()) return true;
                } catch (NoSuchElementException e) {
                }
                return false;
            }

            @Override
            public ReaAndroidElement getValue() {
                return element;
            }
        });
    }

    public List<ReaAndroidElement> findElementsByPartialText(final String text) {

        return waitUntil(new Function<List<ReaAndroidElement>>() {

            private List<WebElement> elementsByPartialText;

            @Override
            public boolean apply() {
                elementsByPartialText = driver.findElementsByPartialText(text);
                return elementsByPartialText != null;
            }

            @Override
            public List<ReaAndroidElement> getValue() {
                return getReaAndroidElements(elementsByPartialText);
            }
        });
    }

    public ReaAndroidElement findElementByText(final String text) {
        try {
            AndroidNativeElement elementByText = (AndroidNativeElement) driver.findElementByText(text);
            return new ReaAndroidElement(elementByText, driver);
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public List<ReaAndroidElement> findElementsByClassName(String className) {
        return getReaAndroidElements(driver.findElements(By.className(className)));
    }

    public List<ReaAndroidElement> getReaAndroidElements(List<WebElement> elements) {

        List<ReaAndroidElement> reaAndroidElements = new ArrayList<ReaAndroidElement>();

        for (WebElement element : elements) {
            reaAndroidElements.add(new ReaAndroidElement((AndroidNativeElement) element, driver));
        }

        return reaAndroidElements;
    }

    public void displayMenu() {
        driver.getKeyboard().sendKeys(AndroidKeys.MENU);
    }

    public void scrollUp() {
        String steps = "";
        for (int i = 0; i < 10; i++) {
            steps += AndroidKeys.DPAD_UP;
        }
        driver.getKeyboard().sendKeys(steps);
    }


    public ReaAndroidElement findElementByIdWithoutWaiting(String id) {
        try {
            return new ReaAndroidElement(driver.findElement(By.id(id)), driver);
        } catch (NoSuchElementException e) {
            return null;
        }
    }
}
