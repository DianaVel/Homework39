package tbc.java.homework39;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfManager {
    private static final Logger lgg = LogManager.getLogger();

    private static final String CONFIG_FILE_LOCATION = "\\config.properties";
    private volatile static ConfManager singleton = null;
    private static URL url = null;
    protected long lastModified;

    private Setting setting;

    private ConfManager(URLConnection conn) throws IOException {
        this.lastModified = conn.getLastModified();

        try (InputStream in = conn.getInputStream()){
            Properties props = new Properties();
            props.load(conn.getInputStream());
            lgg.info("configuration reloaded");
            fillSetting(props);
        }
    }

    public static ConfManager getConfiguration() throws IOException {
        if (url == null) {
            url = ConfManager.class.getClassLoader().getResource(CONFIG_FILE_LOCATION);
        }
        if (url == null) {
            throw new IOException("Configuration file not found");
        }

        URLConnection conn = url.openConnection();

        long lastModified = conn.getLastModified();
        if (singleton == null || lastModified > singleton.lastModified) {
            synchronized (CONFIG_FILE_LOCATION) {
                if (singleton == null || lastModified > singleton.lastModified) {
                    singleton = new ConfManager(conn);
                }
            }
        }

        return singleton;
    }

    private void fillSetting(Properties props) {
        setting = new Setting();
        setting.setUserName(props.getProperty("user-name", ""));
        setting.setPassword(props.getProperty("password", ""));
        setting.setPersonsAdd(props.getProperty("persons", ""));
        setting.setIp(props.getProperty("ip", ""));

    }

    public Setting getSetting() {
        return setting;
    }
}
