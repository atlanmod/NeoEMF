package fr.inria.atlanmod.neoemf.benchmarks.io;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.util.Objects.isNull;

public class Workspace {

    private static final Logger LOG = LogManager.getLogger();

    private static Path BASE_DIRECTORY;
    private static Path RESOURCES_DIRECTORY;
    private static Path STORES_DIRECTORY;
    private static Path TEMP_DIRECTORY;

    private Workspace() {
    }

    public static Path getBaseDirectory() {
        if (isNull(BASE_DIRECTORY)) {
            try {
                String BASE_DIRECTORY_NAME = "neoemf-benchmarks";
                BASE_DIRECTORY = Files.createDirectories(Paths.get(System.getProperty("java.io.tmpdir"), BASE_DIRECTORY_NAME));
            }
            catch (IOException e) {
                LOG.warn(e);
            }
        }
        return BASE_DIRECTORY;
    }

    public static Path getResourcesDirectory() {
        if (isNull(RESOURCES_DIRECTORY)) {
            try {
                String RESOURCES_DIRECTORY_NAME = "resources";
                RESOURCES_DIRECTORY = Files.createDirectories(getBaseDirectory().resolve(RESOURCES_DIRECTORY_NAME));
            }
            catch (IOException e) {
                LOG.warn(e);
            }
        }
        return RESOURCES_DIRECTORY;
    }

    public static Path getStoreDirectory() {
        if (isNull(STORES_DIRECTORY)) {
            try {
                STORES_DIRECTORY = Files.createDirectories(getBaseDirectory().resolve("stores"));
            }
            catch (IOException e) {
                LOG.warn(e);
            }
        }
        return STORES_DIRECTORY;
    }

    private static Path getTempDirectory() {
        if (isNull(TEMP_DIRECTORY)) {
            try {
                TEMP_DIRECTORY = Files.createDirectories(getBaseDirectory().resolve("tmp"));
            }
            catch (IOException e) {
                LOG.warn(e);
            }
        }
        return TEMP_DIRECTORY;
    }

    public static Path newTempDirectory(String prefix) {
        Path tempDirectory = null;
        try {
            tempDirectory = Files.createTempDirectory(getTempDirectory(), prefix);
        }
        catch (IOException e) {
            LOG.error(e);
        }
        return tempDirectory;
    }

    public static void cleanTempDirectory() {
        if (!isNull(TEMP_DIRECTORY)) {
            if(!FileUtils.deleteQuietly(TEMP_DIRECTORY.toFile())) {
                try {
                    FileUtils.forceDeleteOnExit(TEMP_DIRECTORY.toFile());
                }
                catch (IOException ignore) {
                }
            }
        }
    }
}
