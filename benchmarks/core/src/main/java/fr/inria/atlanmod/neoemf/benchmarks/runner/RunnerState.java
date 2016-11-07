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

import fr.inria.atlanmod.neoemf.benchmarks.backend.Backend;

import org.eclipse.emf.ecore.resource.Resource;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;

import java.io.File;
import java.util.List;
import java.util.Objects;

@State(Scope.Thread)
public class RunnerState {

    /**
     * The {@link Backend} instance.
     */
    private Backend backendInst;

    /**
     * The {@link Resource} instance.
     */
    private Resource resourceInst;

    private List<File> paths;

    @Param({
            "XmiBackend",
            "CdoBackend",
            "NeoMapBackend",
            "NeoGraphBackend",
//          "NeoGraphNeo4jBackend",
    })
    private String backend;

//  @Param({
//          "fr.inria.atlanmod.kyanos.tests",
//          "fr.inria.atlanmod.neo4emf.neo4jresolver",
//          "org.eclipse.gmt.modisco.java.kyanos",
//          "org.eclipse.jdt.core",
//          "org.eclipse.jdt.source.all",
//  })
//  private String resource;

    @Param({"0", "1"})
    private int resource;

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
        backendInst = (Backend) Runner.class.getClassLoader().loadClass(Backend.class.getPackage().getName() + "." + backend).newInstance();
        paths = backendInst.createAll();
    }

    @Setup(Level.Invocation)
    public void setupInvocation() throws Exception {
        resourceInst = backendInst.loadResource(paths.get(resource).getAbsolutePath());
    }

    @TearDown(Level.Invocation)
    public void tearDownInvocation() throws Exception {
        backendInst.unloadResource(resourceInst);
        resourceInst = null;
    }
}