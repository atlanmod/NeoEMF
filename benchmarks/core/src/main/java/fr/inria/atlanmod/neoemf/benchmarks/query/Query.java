/*
 * Copyright (c) 2013-2017 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.benchmarks.query;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.Callable;

import static java.util.Objects.nonNull;

/**
 * @param <V> the result type of method {@code call}
 */
@FunctionalInterface
public interface Query<V> extends Callable<V> {

    Logger log = LogManager.getLogger();

    @Override
    V call() throws Exception;

    default V callWithResult() throws Exception {
        V result;

        log.info("Start query");

        result = call();

        log.info("End query");

        if (nonNull(result) && !Void.class.isInstance(result)) {
            log.info("Query returns: {}", result);
        }

        return result;
    }

    default V callWithTime() throws Exception {
        V result;

        Instant begin = Instant.now();

        result = callWithResult();

        Instant end = Instant.now();

        log.info("Time spent: {}", Duration.between(begin, end));

        return result;
    }

    default V callWithMemoryUsage() throws Exception {
        V result;

        Runtime runtime = Runtime.getRuntime();

        runtime.gc();
        long initialUsedMemory = runtime.totalMemory() - runtime.freeMemory();
        log.info("Used memory before call: {}", FileUtils.byteCountToDisplaySize(initialUsedMemory));

        result = callWithTime();

        runtime.gc();
        long finalUsedMemory = runtime.totalMemory() - runtime.freeMemory();
        log.info("Used memory after call: {}", FileUtils.byteCountToDisplaySize(finalUsedMemory));
        log.info("Memory use increase: {}", FileUtils.byteCountToDisplaySize(finalUsedMemory - initialUsedMemory));

        return result;
    }
}
