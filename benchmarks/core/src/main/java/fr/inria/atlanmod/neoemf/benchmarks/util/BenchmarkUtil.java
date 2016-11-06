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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class BenchmarkUtil {

    /**
     * Default JVM arguments for creating test resources.
     */
    private static final String DEFAULT_CREATOR_VMARGS = "" +
            "-Dfile.encoding=utf-8 " +
            "-Xmx8g";

    /**
     * Default JVM argument for traversing and querying test resources.
     */
    private static final String DEFAULT_TESTER_VMARGS = "" +
            "-Dfile.encoding=utf-8 " +
            "-server " +
            "-Xmx250m " +
            "-XX:+UseConcMarkSweepGC " +
            "-XX:+DisableExplicitGC " +
            "-XX:+CMSClassUnloadingEnabled";

    private static final int DEFAULT_TIMEOUT = 7200000;

    private static Path BENCHMARKS_DIR;

    private BenchmarkUtil() {
    }

    public static Path getBaseDirectory() {
        if (BENCHMARKS_DIR == null) {
            try {
                BENCHMARKS_DIR = Files.createTempDirectory("neoemf-benchmarks");
            }
            catch (IOException e) {
                LogManager.getLogger().error(e);
            }
        }
        return BENCHMARKS_DIR;
    }

    public static Path getTestDirectory() {
        return getBaseDirectory().resolve("temp");
    }

    public static Path getResourcesDirectory() {
        return getBaseDirectory().resolve("resources");
    }

    public static int getDefaultTimeout() {
        return DEFAULT_TIMEOUT;
    }

    public static String getDefaultCreatorVmargs() {
        return DEFAULT_CREATOR_VMARGS;
    }

    public static String getDefaultTesterVmargs() {
        return DEFAULT_TESTER_VMARGS;
    }
}
