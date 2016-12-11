package fr.inria.atlanmod.neoemf.extension;

import org.apache.commons.io.FileUtils;
import org.junit.rules.ExternalResource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.util.Objects.nonNull;

public class Workspace extends ExternalResource {

    private static final String PREFIX = "neoemf";

    private File temporaryFolder;

    @Override
    public void before() throws IOException {
        temporaryFolder = Files.createTempDirectory(PREFIX).toFile();
    }

    @Override
    public void after() {
        if (nonNull(temporaryFolder) && temporaryFolder.exists() && !FileUtils.deleteQuietly(temporaryFolder)) {
            try {
                FileUtils.forceDeleteOnExit(temporaryFolder);
            }
            catch (IOException ignore) {
            }
        }
    }

    public File newFile(String name) throws IOException {
        Path createdFolder = Files.createTempDirectory(temporaryFolder.toPath(), getClass().getSimpleName() + name);
        Files.deleteIfExists(createdFolder);
        return createdFolder.toFile();
    }
}
