package au.com.realestate.test.framework;

import au.com.realestate.instrument.client.ReaHttpCommandExecutor;
import com.google.android.testing.nativedriver.client.AndroidNativeDriver;
import com.google.android.testing.nativedriver.client.AndroidNativeDriverBuilder;

import java.net.MalformedURLException;
import java.net.URL;

public class ReaTestPlatform {

    private static AndroidNativeDriver driver;

    public synchronized static AndroidNativeDriver getDefaultDriver() {
        if (driver == null) {
            try {
                driver = new AndroidNativeDriverBuilder().withCommandExecutor(new ReaHttpCommandExecutor(new URL("http://localhost:" + System.getProperty("port") + "/hub"))).build();
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }
        return driver;

    }
}
