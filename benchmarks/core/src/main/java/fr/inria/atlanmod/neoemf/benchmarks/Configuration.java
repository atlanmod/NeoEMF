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

package fr.inria.atlanmod.neoemf.benchmarks;

public class Configuration {

    /**
     * Default execution time of an iteration.
     */
    public static final int DEFAULT_ITERATION_TIME_MS = 2;

    /**
     * Default number of warm-up iterations.
     */
    public static final int DEFAULT_WARMUP_ITERATIONS = 2;

    /**
     * Default number of measurement iterations.
     */
    public static final int DEFAULT_MEASUREMENT_ITERATIONS = 5;

    /**
     * Default number of operations by iterations.
     */
    public static final int DEFAULT_BATCH_SIZE = 50;

    /**
     * Default number of fork for an iteration.
     */
    public static final int DEFAULT_FORKS = 1;

    private Configuration() {
    }
}
