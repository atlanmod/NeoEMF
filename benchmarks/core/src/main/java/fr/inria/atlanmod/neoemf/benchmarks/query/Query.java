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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.Callable;

import static java.util.Objects.isNull;

/**
 * @param <V> the result type of method {@code call}
 */
@FunctionalInterface
public interface Query<V> extends Callable<V> {

    Logger LOG = LogManager.getLogger();

    @Override
    V call() throws Exception;

    default V callWithResult() throws Exception {
        V result;

        LOG.info("Start query");

        result = call();

        LOG.info("End query");

        if (!isNull(result) && !Void.class.isInstance(result)) {
            LOG.info("Query returns: {}", result);
        }

        return result;
    }

    default V callWithTime() throws Exception {
        V result;

        Instant begin = Instant.now();

        result = callWithResult();

        Instant end = Instant.now();

        LOG.info("Time spent: {}", Duration.between(begin, end));

        return result;
    }

    default V callWithMemoryUsage() throws Exception {
        V result;

        Runtime.getRuntime().gc();
        long initialUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        LOG.info("Used memory before call: {}", QueryHelper.toMegabytes(initialUsedMemory));

        result = callWithTime();

        Runtime.getRuntime().gc();
        long finalUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        LOG.info("Used memory after call: {}", QueryHelper.toMegabytes(finalUsedMemory));
        LOG.info("Memory use increase: {}", QueryHelper.toMegabytes(finalUsedMemory - initialUsedMemory));

        return result;
    }
}
