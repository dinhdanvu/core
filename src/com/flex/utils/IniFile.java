package com.flex.utils;

import org.ini4j.InvalidFileFormatException;
import org.ini4j.Profile;
import org.ini4j.Wini;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class IniFile {
    public static <T> T readConfigFileString(String iniFilePath, String sectionName, String key, String defaultValues, Class<T> clazz) {
        try {
            Wini ini = new Wini(new File(iniFilePath));
            return ini.get(sectionName, key, clazz);
        } catch (InvalidFileFormatException e) {
            return clazz.cast(defaultValues);
        } catch (IOException e) {
            return clazz.cast(defaultValues);
        }
    }

    /**
     * Read all configs in section name.
     *
     * @param iniFilePath
     * @param sectionName
     * @return
     */
    public static Properties readConfigsInSectionName(String iniFilePath, String sectionName) {
        try {
            Wini ini = new Wini(new File(iniFilePath));

            // get section
            Profile.Section section = ini.get(sectionName);
            Properties properties = new Properties();
            if (section != null) {
                for (Map.Entry<String, String> entry : section.entrySet()) {
                    properties.put(entry.getKey(), entry.getValue());
                }
            }
            return properties;
        } catch (Exception e) {
            return null;
        }
    }
}