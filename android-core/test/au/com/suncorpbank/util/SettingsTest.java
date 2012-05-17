package au.com.suncorpbank.util;

import org.junit.Test;

import static au.com.suncorpbank.util.Settings.getProperty;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SettingsTest {
    @Test
    public void testGetProperty() throws Exception {
        String url = getProperty("exchange.server.url");

        assertThat(url, is("http://10.167.114.27:3000/home/exchange.xml"));
    }
}
