package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static Properties props = new Properties();

    static {
        try (InputStream input = Config.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("No se encontró config.properties en resources");
            }
            props.load(input);
        } catch (IOException ex) {
            throw new RuntimeException("Error cargando configuración", ex);
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }
}