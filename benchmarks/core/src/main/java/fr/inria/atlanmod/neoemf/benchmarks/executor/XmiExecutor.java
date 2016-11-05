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

package fr.inria.atlanmod.neoemf.benchmarks.executor;

import com.google.common.collect.Iterators;

import fr.inria.atlanmod.neoemf.benchmarks.Traverser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

// in = ${java.io.tmpdir}/neoemf-benchmarks/temp/*.xmi

public class XmiExecutor implements Traverser {

    private static final Logger LOG = LogManager.getLogger();

    @Override
    public void traverse(String in) {
        try {
            URI uri = URI.createFileURI(in);

            org.eclipse.gmt.modisco.java.emf.impl.JavaPackageImpl.init();

            ResourceSet resourceSet = new ResourceSetImpl();
            resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
            resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("zxmi", new XMIResourceFactoryImpl());

            Resource resource = resourceSet.createResource(uri);

            Map<String, Object> loadOpts = new HashMap<>();
            resource.load(loadOpts);

            LOG.info("Start counting");
            Instant begin = Instant.now();
            int count = Iterators.size(resource.getAllContents());
            Instant end = Instant.now();
            LOG.info("End counting");
            LOG.info("Resource {0} contains {1} elements", uri, count);
            LOG.info("Time spent: {0}", Duration.between(begin, end));

            resource.unload();
        }
        catch (Exception e) {
            LOG.error(e);
        }
    }
}
