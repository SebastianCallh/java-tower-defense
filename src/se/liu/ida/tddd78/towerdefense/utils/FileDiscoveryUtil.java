package se.liu.ida.tddd78.towerdefense.utils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;

public final class FileDiscoveryUtil {
    private static final String[] SEARCH_PATHS = new String[] {
            "data/layout",
            "data/theme",
            "."
    };

    private FileDiscoveryUtil() {

    }

    public static List<URL> retrieveExistingFiles(FileType type) {
        List<URL> files = new ArrayList<>();
        for (String searchPath : SEARCH_PATHS) {
            files.addAll(retrieveExistingFiles(retrieveInternalDirectory(searchPath), type));
            files.addAll(retrieveExistingFiles(retrieveExternalDirectory(searchPath), type));
        }

        return files;
    }

    private static Collection<URL> retrieveExistingFiles(File directory, FileType type) {
        Collection<URL> matches = new ArrayList<>();
        if (directory != null && directory.exists() && directory.isDirectory()) {
            for (File file : directory.listFiles()) {
                if (file.getName().endsWith(type.getExtension())) {
                    try {
                        matches.add(file.toURI().toURL());
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return matches;
    }

    private static File retrieveExternalDirectory(String searchPath) {
        return new File(searchPath);
    }

    private static File retrieveInternalDirectory(String searchPath) {
        URL resourceURL = FileDiscoveryUtil.class.getClassLoader().getResource(searchPath);
        if (resourceURL != null) {
            try {
                return new File(resourceURL.toURI());
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public enum FileType {
        LAYOUT(".layout"),
        THEME(".theme");

        private final String extension;
        private FileType(String extension) {
            this.extension = extension;
        }

        public String getExtension() {
            return extension;
        }
    }

}
