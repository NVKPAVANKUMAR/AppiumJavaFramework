package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigParser {
    public static String getProperty(String key) throws IOException {
        FileInputStream fis = new FileInputStream(new File("config/config.properties"));
        Properties properties = new Properties();
        properties.load(fis);
        return properties.getProperty(key);
    }
}
