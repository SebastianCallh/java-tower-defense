package se.liu.ida.tddd78.towerdefense.utils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Util for finding themes and layouts packaged with the program or relative to the execution path. Enables
 * customization without re-compiling and minimizes the steps needed to add a new pre-packaged theme or layout.
 */
public final class FileDiscoveryUtil {
    private static final String[] SEARCH_PATHS = new String[] {
            "resources/layout",
            "resources/theme",
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
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.getName().endsWith(type.getExtension())) {
                        try {
                            matches.add(file.toURI().toURL());
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
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
