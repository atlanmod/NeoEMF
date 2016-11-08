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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.util.Objects.isNull;

public class BenchmarkUtil {

    public static final int DEFAULT_ITERATION_TIME_SEC = 2;
    public static final int DEFAULT_WARMUP_ITERATIONS = 2;
    public static final int DEFAULT_MEASUREMENT_ITERATIONS = 5;

    public static final int DEFAULT_BATCH_SIZE = 50;
    public static final int DEFAULT_FORKS = 1;

    private static final Logger LOG = LogManager.getLogger();

    private static Path BASE_DIR;
    private static Path RESOURCE_DIR;
    private static Path STORE_DIR;

    private BenchmarkUtil() {
    }

    public static Path getBaseDirectory() {
        if (isNull(BASE_DIR)) {
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
        Path tempDirectory = null;
        try {
            tempDirectory = Files.createTempDirectory(getBaseDirectory(), "bench");
        }
        catch (IOException e) {
            LOG.error(e);
        }
        return tempDirectory;
    }

    public static Path getResourcesDirectory() {
        if (isNull(RESOURCE_DIR)) {
            try {
                RESOURCE_DIR = Files.createDirectories(getBaseDirectory().resolve("resources"));
            }
            catch (IOException e) {
                LOG.warn(e);
            }
        }
        return RESOURCE_DIR;
    }

    public static Path getStoreDirectory() {
        if (isNull(STORE_DIR)) {
            try {
                STORE_DIR = Files.createDirectories(getBaseDirectory().resolve("stores"));
            }
            catch (IOException e) {
                LOG.warn(e);
            }
        }
        return STORE_DIR;
    }
}
