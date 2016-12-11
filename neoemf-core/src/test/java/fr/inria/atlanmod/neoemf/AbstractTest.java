/*
 * Copyright (c) 2013-2016 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf;

import fr.inria.atlanmod.neoemf.logging.Logger;
import fr.inria.atlanmod.neoemf.logging.NeoLogger;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.util.Objects.nonNull;

public abstract class AbstractTest {

    private static final String PREFIX = "neoemf";

    private static File temporaryFolder;

    @Rule
    public TestRule watcher = new TestLogger();

    @BeforeClass
    public static void createTemporaryFolder() throws IOException {
        temporaryFolder = Files.createTempDirectory(PREFIX).toFile();
    }

    @AfterClass
    public static void deleteTemporaryFolder() {
        if (nonNull(temporaryFolder) && temporaryFolder.exists() && !FileUtils.deleteQuietly(temporaryFolder)) {
            try {
                FileUtils.forceDeleteOnExit(temporaryFolder);
            }
            catch (IOException ignore) {
            }
        }
    }

    protected File newFile(String name) throws IOException {
        Path createdFolder = Files.createTempDirectory(temporaryFolder.toPath(), getClass().getSimpleName() + name);
        Files.deleteIfExists(createdFolder);
        return createdFolder.toFile();
    }

    private static class TestLogger extends TestWatcher {

        private static final Logger LOG = NeoLogger.custom("test");

        @Override
        protected void succeeded(Description description) {
            LOG.info("[INFO] --- Succeeded");
        }

        @Override
        protected void failed(Throwable e, Description description) {
            LOG.warn("[WARN] --- Failed");
        }

        @Override
        protected void starting(Description description) {
            LOG.info("");
            LOG.info("[INFO] --- Running " + description.getMethodName());
        }

        @Override
        protected void finished(Description description) {
            LOG.info("");
        }
    }
}
