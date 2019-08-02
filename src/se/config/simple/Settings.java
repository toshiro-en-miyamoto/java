package se.config.simple;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class Settings {
    private static final Properties settings;
    private static final String FILENAME = "data/config/simple.properties";
    private static final String CHARSET = "UTF-8";
    private static final String FILE_UNAVAILABLE = "%s is unavailable%n";

    static {
        Path path = Paths.get(FILENAME);
        Charset charset = Charset.forName(CHARSET);

        Properties defaultSettings = new Properties();
        defaultSettings.setProperty("Simple", "is the best");

        settings = new Properties(defaultSettings);
        try (BufferedReader reader = Files.newBufferedReader(path, charset))
        {
            settings.load(reader);
        } catch (IOException e) {
            System.err.printf(FILE_UNAVAILABLE, FILENAME);
        }
    }

    public static void main(String[] args)
    {
        settings.stringPropertyNames().stream()
            .map(k -> k + "=" + settings.getProperty(k))
            .forEach(e -> System.out.println(e));
    }
}
