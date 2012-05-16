package au.com.realestate.test.operations;

import org.openqa.selenium.By;

public class ClickSecondTitleBarButton extends ReaBaseScreen {
    @Override
    public void action() {
        helper.findDisplayedElementById("title_bar_button_container").findElements(By.className("android.widget.ImageButton")).get(1).click();
    }
}
