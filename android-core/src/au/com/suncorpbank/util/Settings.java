package au.com.suncorpbank.util;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Settings {
    private static Properties properties;

    public synchronized static String getProperty(String key) {
        if (properties == null) {
            properties = new Properties();
            readFile("/settings.properties");
        }
        return properties.getProperty(key);
    }

    private static void readFile(String resName) {
        InputStream inputStream = null;
        try {
            inputStream = Settings.class.getResourceAsStream(resName);
            properties.load(inputStream);
        } catch (IOException e) {
            Log.d("properties", properties + "");
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

