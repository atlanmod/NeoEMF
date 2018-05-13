/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.benchmarks.runner;

import fr.inria.atlanmod.neoemf.benchmarks.query.QueryFactory;
import fr.inria.atlanmod.neoemf.benchmarks.runner.state.ReadWriteResourceState;

import org.eclipse.emf.ecore.resource.Resource;
import org.openjdk.jmh.annotations.Benchmark;

import java.io.IOException;
import java.util.UUID;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link BaseRunner} that provides benchmark methods for read-write queries.
 */
@ParametersAreNonnullByDefault
public class ReadWriteRunner extends BaseRunner {

    @Benchmark
    public Long renameAllMethods(ReadWriteResourceState state) throws IOException {
        String name = UUID.randomUUID().toString();
        Resource resource = state.resource();
        Long result = QueryFactory.renameAllMethods(name).executeOn(resource);
        state.adapter().save(resource, state.baseConfig());
        return result;
    }
}
