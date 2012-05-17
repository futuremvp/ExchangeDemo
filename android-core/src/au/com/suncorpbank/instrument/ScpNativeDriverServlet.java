package au.com.suncorpbank.instrument;

import au.com.suncorpbank.instrument.handler.IsElementDisplayed;
import com.google.android.testing.nativedriver.server.AndroidNativeDriverServlet;
import org.openqa.selenium.remote.server.rest.ResultType;

import javax.servlet.ServletException;

public class ScpNativeDriverServlet extends AndroidNativeDriverServlet {
    @Override
    public void init() throws ServletException {
        super.init();
        addNewPostMapping(AndroidNativeDriverServlet.SESSION_PATH + "element/:id/isDisplayed", IsElementDisplayed.class).on(ResultType.SUCCESS, newJsonResult());
    }
}
