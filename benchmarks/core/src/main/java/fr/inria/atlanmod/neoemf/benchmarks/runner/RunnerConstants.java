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

package fr.inria.atlanmod.neoemf.benchmarks.runner;

class RunnerConstants {

    /**
     * Default number of warm-up iterations.
     */
    public static final int DEFAULT_WARMUP_ITERATIONS = 5;

    /**
     * Default number of measurement iterations.
     */
    public static final int DEFAULT_MEASUREMENT_ITERATIONS = 10;

    /**
     * Default number of operations for each iterations.
     */
    public static final int DEFAULT_OPERATIONS = 50;

    /**
     * Default number of fork for each iterations.
     */
    public static final int DEFAULT_FORKS = 1;

    /**
     * Default timeout for each iterations, in minutes.
     */
    public static final int DEFAULT_ITERATION_TIMEOUT = 60;

    /**
     * Default Xmx java argument.
     */
    public static final String DEFAULT_XMX = "-Xmx8g";

    private RunnerConstants() {
    }
}
