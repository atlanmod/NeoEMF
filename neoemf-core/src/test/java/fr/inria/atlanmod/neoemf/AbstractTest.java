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

import fr.inria.atlanmod.neoemf.logging.NeoLogger;

import org.apache.commons.io.FileUtils;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.io.File;
import java.io.IOException;
import java.time.Instant;

public abstract class AbstractTest {

    private static final int MB = 1024 * 1024;

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

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
            System.out.println("\n[INFO] --- Running " + description.getMethodName());
        }

        @Override
        protected void finished(Description description) {
            System.out.println();
        }
    };

    protected void printMemoryUsage() {
        // Getting the runtime reference from system
        Runtime runtime = Runtime.getRuntime();
        System.out.println("[INFO] --- Heap utilization statistics [MB] #####");

        // Print used memory
        System.out.println(formatMemoryUsage("[INFO] Used memory", (runtime.totalMemory() - runtime.freeMemory()) / MB));

        // Print free memory
        System.out.println(formatMemoryUsage("[INFO] Free memory", runtime.freeMemory() / MB));

        // Print total available memory
        System.out.println(formatMemoryUsage(" [INFO] Total memory", runtime.totalMemory() / MB));

        // Print Maximum available memory
        System.out.println(formatMemoryUsage("[INFO] Max memory", runtime.maxMemory() / MB));

        System.out.println("[INFO] ---");
    }

    private String formatMemoryUsage(String msg, long value) {
        return String.format("   %-12s  :  %4d", msg, value);
    }

    protected File tempFile(String name) {
        File file = temporaryFolder.getRoot().toPath().resolve(getClass().getSimpleName() + name + String.valueOf(Instant.now().toEpochMilli())).toFile();

        try {
            FileUtils.forceDeleteOnExit(file);
        }
        catch (IOException e) {
            NeoLogger.warn(e);
        }

        return file;
    }
}
