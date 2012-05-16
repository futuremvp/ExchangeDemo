package au.com.realestate.test.operations;

import au.com.realestate.test.framework.ReaNativeDriverHelper;
import au.com.realestate.test.framework.ReaTestPlatform;
import com.google.android.testing.nativedriver.client.AndroidNativeDriver;

public abstract class ReaBaseScreen {

    protected AndroidNativeDriver driver;
    protected ReaNativeDriverHelper helper;

    public ReaBaseScreen() {
        this.driver = ReaTestPlatform.getDefaultDriver();
        helper = new ReaNativeDriverHelper(driver);
    }

    public abstract void action();
}
