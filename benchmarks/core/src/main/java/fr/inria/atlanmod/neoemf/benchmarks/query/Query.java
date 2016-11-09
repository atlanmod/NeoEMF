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

package fr.inria.atlanmod.neoemf.benchmarks.query;

import fr.inria.atlanmod.neoemf.benchmarks.util.BytesUtil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.Callable;

/**
 * @param <V> the result type of method {@code call}
 */
@FunctionalInterface
public interface Query<V> extends Callable<V> {

    Logger LOG = LogManager.getLogger();

    @Override
    V call() throws Exception;

    default V callWithTime() throws Exception {
        V result;

        LOG.info("Start query");
        Instant begin = Instant.now();

        result = call();

        Instant end = Instant.now();
        LOG.info("End query");

        if (Number.class.isInstance(result)) {
            LOG.info("Query result contains {} elements", result);
        }

        LOG.info("Time spent: {}", Duration.between(begin, end));

        return result;
    }

    default V callWithMemoryAndTime() throws Exception {
        V result;

        Runtime.getRuntime().gc();
        long initialUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        LOG.info("Used memory before call: {}", BytesUtil.toMegabytes(initialUsedMemory));

        result = callWithTime();

        Runtime.getRuntime().gc();
        long finalUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        LOG.info("Used memory after call: {}", BytesUtil.toMegabytes(finalUsedMemory));
        LOG.info("Memory use increase: {}", BytesUtil.toMegabytes(finalUsedMemory - initialUsedMemory));

        return result;
    }
}
