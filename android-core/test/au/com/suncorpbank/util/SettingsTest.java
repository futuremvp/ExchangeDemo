package au.com.suncorpbank.util;

import org.junit.Test;

import static au.com.suncorpbank.util.Settings.getProperty;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SettingsTest {
    @Test
    public void testGetProperty() throws Exception {
        String url = getProperty("exchange.server.url");

        assertThat(url, is("http://exchange-demo.herokuapp.com/home/exchange.xml"));
    }
}
