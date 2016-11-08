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

import com.google.common.base.CaseFormat;

import fr.inria.atlanmod.neoemf.benchmarks.backend.Backend;
import fr.inria.atlanmod.neoemf.benchmarks.backend.CdoBackend;
import fr.inria.atlanmod.neoemf.benchmarks.backend.NeoGraphBackend;
import fr.inria.atlanmod.neoemf.benchmarks.backend.NeoMapBackend;
import fr.inria.atlanmod.neoemf.benchmarks.backend.XmiBackend;

import org.eclipse.emf.ecore.resource.Resource;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;

import java.io.File;
import java.util.Objects;

@State(Scope.Thread)
public class RunnerState {

    private static final String CLASS_PREFIX = Backend.class.getPackage().getName() + ".";
    private static final String CLASS_SUFFIX = Backend.class.getSimpleName();

    /**
     * The {@link Backend} instance.
     */
    private Backend backendInst;

    /**
     * The {@link Resource} instance.
     */
    private Resource resourceInst;

    private File resourceFile;

    @Param({
            XmiBackend.NAME,
            CdoBackend.NAME,
            NeoMapBackend.NAME,
            NeoGraphBackend.NAME,
//            NeoGraphNeo4jBackend.NAME, // FIXME: dependencies issue
    })
    private String backend;

    @Param({
            "fr.inria.atlanmod.kyanos.tests",
//            "fr.inria.atlanmod.neo4emf.neo4jresolver",
//            "org.eclipse.gmt.modisco.java.kyanos",
//            "org.eclipse.jdt.core",
//            "org.eclipse.jdt.source.all",
    })
    private String resource;

    public Backend getBackend() {
        if (Objects.isNull(backendInst)) {
            throw new NullPointerException();
        }
        return backendInst;
    }

    public Resource getResource() {
        if (Objects.isNull(resourceInst)) {
            throw new NullPointerException();
        }
        return resourceInst;
    }

    @Setup(Level.Trial)
    public void setupTrial() throws Exception {
        String className = CLASS_PREFIX + CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_CAMEL, backend) + CLASS_SUFFIX;
        backendInst = (Backend) Runner.class.getClassLoader().loadClass(className).newInstance();
        resourceFile = backendInst.create(resource);
    }

    @Setup(Level.Invocation)
    public void setupInvocation() throws Exception {
        resourceInst = backendInst.load(resourceFile);
    }

    @TearDown(Level.Invocation)
    public void tearDownInvocation() throws Exception {
        backendInst.unload(resourceInst);
        resourceInst = null;
    }
}