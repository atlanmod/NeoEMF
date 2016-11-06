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

package fr.inria.atlanmod.neoemf.benchmarks.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openjdk.jmh.annotations.Mode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BenchmarkUtil {

    private static final Logger LOG = LogManager.getLogger();

    /**
     * Default JVM arguments for creating test resources.
     */
    public static final String[] DEFAULT_CREATOR_VMARGS = {
            "-Dfile.encoding=utf-8",
            "-Xmx8g"};

    /**
     * Default JVM argument for traversing and querying test resources.
     */
    public static final String[] DEFAULT_TESTER_VMARGS = {
            "-Dfile.encoding=utf-8",
            "-server",
            "-Xmx250m",
            "-XX:+UseConcMarkSweepGC",
            "-XX:+DisableExplicitGC",
            "-XX:+CMSClassUnloadingEnabled"};

    public static final int DEFAULT_TIMEOUT = 7200000;

    public static final int DEFAULT_ITERATIONS = 5;
    public static final int DEFAULT_BATCH_SIZE = 1;
    public static final int DEFAULT_FORKS = 1;

    private static Path BASE_DIR;
    private static Path BENCH_DIR;

    private BenchmarkUtil() {
    }

    public static Path getBaseDirectory() {
        if (BASE_DIR == null) {
            try {
                BASE_DIR = Files.createDirectories(Paths.get(System.getProperty("java.io.tmpdir"), "neoemf-benchmarks"));
            }
            catch (IOException e) {
                LOG.warn(e);
            }
        }
        return BASE_DIR;
    }

    public static Path getBenchDirectory() {
        if (BENCH_DIR == null) {
            try {
                BENCH_DIR = Files.createTempDirectory(getBaseDirectory(), "bench");
            }
            catch (IOException e) {
                LOG.error(e);
            }
        }
        return BENCH_DIR;
    }

    public static Path getResourcesDirectory() {
        return getBaseDirectory().resolve("resources");
    }

    public static Path getStoreDirectory() {
        return getBaseDirectory().resolve("stores");
    }
}
