package au.com.realestate.test.operations;

import au.com.realestate.instrument.client.ReaAndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GotoMapScreen extends ReaBaseScreen {
    @Override
    public void action() {
        ReaAndroidElement buttonContainer = helper.findElementById("title_bar_button_container");
        List<WebElement> elements = buttonContainer.findElements(By.className("android.widget.ImageButton"));
        elements.get(3).click();
    }
}
