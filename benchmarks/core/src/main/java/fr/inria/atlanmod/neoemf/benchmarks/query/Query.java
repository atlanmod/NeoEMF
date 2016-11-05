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

import fr.inria.atlanmod.neoemf.benchmarks.util.MessageUtil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.Callable;

/**
 *
 */
public abstract class Query implements Callable<Integer> {

    private static final Logger LOG = LogManager.getLogger();

    @Override
    public abstract Integer call() throws Exception;

    public Integer callWithTimeSpent() throws Exception {
        int result;

        LOG.info("Start query");
        Instant begin = Instant.now();

        result = call();

        Instant end = Instant.now();
        LOG.info("End query");
        LOG.info("Query result contains {0} elements", result);
        LOG.info("Time spent: {0}", Duration.between(begin, end));

        return result;
    }

    public Integer callWithMemoryUsage() throws Exception {
        int result;

        Runtime.getRuntime().gc();
        long initialUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        LOG.info("Used memory before query: {0}", MessageUtil.byteCountToDisplaySize(initialUsedMemory));

        result = callWithTimeSpent();

        Runtime.getRuntime().gc();
        long finalUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        LOG.info("Used memory after query: {0}", MessageUtil.byteCountToDisplaySize(finalUsedMemory));
        LOG.info("Memory use increase: {0}", MessageUtil.byteCountToDisplaySize(finalUsedMemory - initialUsedMemory));

        return result;
    }
}
