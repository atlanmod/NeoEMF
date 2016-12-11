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

    private static File temporaryFolder;

    @Rule
    public TestRule watcher = new TestWatcher() {
        @Override
        protected void succeeded(Description description) {
            System.out.println("[INFO] --- Succeeded");
        }

        @Override
        protected void failed(Throwable e, Description description) {
            System.out.println("[WARN] --- Failed");
        }

        @Override
        protected void starting(Description description) {
            System.out.println();
            System.out.println("[INFO] --- Running " + description.getMethodName());
        }

        @Override
        protected void finished(Description description) {
            System.out.println();
        }
    };

    @BeforeClass
    public static void createTemporaryFolder() throws IOException {
        temporaryFolder = Files.createTempDirectory("neoemf").toFile();
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

    protected File newFile(String name) {
        try {
            Path createdFolder = Files.createTempDirectory(temporaryFolder.toPath(), getClass().getSimpleName() + name);
            Files.deleteIfExists(createdFolder);
            return createdFolder.toFile();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
