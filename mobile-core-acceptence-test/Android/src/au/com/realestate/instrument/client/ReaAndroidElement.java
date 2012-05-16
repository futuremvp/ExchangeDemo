package au.com.realestate.instrument.client;

import com.google.android.testing.nativedriver.client.AndroidNativeDriver;
import com.google.android.testing.nativedriver.client.AndroidNativeElement;
import com.google.common.collect.ImmutableMap;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Response;

import java.util.ArrayList;
import java.util.List;

public class ReaAndroidElement extends AndroidNativeElement {


    private AndroidNativeDriver driver;

    public ReaAndroidElement(AndroidNativeElement element, AndroidNativeDriver driver) {
        super(driver);
        this.driver = driver;
        super.setId(element.getId());
    }

    public boolean isDisplayed() {
        final Response response = execute(ReaNativeCommand.IS_DISPLAYED, ImmutableMap.of("id", getId()));
        return (Boolean) response.getValue();
    }

    @Override
    public ReaAndroidElement findElementByPartialText(String text) {
        return new ReaAndroidElement((AndroidNativeElement) super.findElementByPartialText(text), driver);
    }

    @Override
    public ReaAndroidElement findElementById(String using) {
        return new ReaAndroidElement((AndroidNativeElement) super.findElementById(using), driver);
    }

    public List<ReaAndroidElement> findAndroidNativeElementsById(String id) {
        List<ReaAndroidElement> reaAndroidElements = new ArrayList<ReaAndroidElement>();
        List<WebElement> elementsById = super.findElementsById(id);

        for (WebElement element : elementsById) {
            reaAndroidElements.add(new ReaAndroidElement((AndroidNativeElement) element, driver));
        }
        return reaAndroidElements;
    }
}
