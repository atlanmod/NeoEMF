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

import fr.inria.atlanmod.neoemf.benchmarks.QueryExecutor;
import fr.inria.atlanmod.neoemf.benchmarks.Traverser;
import fr.inria.atlanmod.neoemf.benchmarks.query.QueryFactory;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.map.datastore.MapPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.map.util.NeoMapURI;
import fr.inria.atlanmod.neoemf.resources.PersistentResourceFactory;
import fr.inria.atlanmod.neoemf.resources.impl.PersistentResourceImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import java.io.File;
import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

// in = BenchmarkUtil.getTestDirectory()/*.neoemfmapresource

public class NeoMapExecutor implements QueryExecutor, Traverser {

    private static final Logger LOG = LogManager.getLogger();

    @Override
    public void queryClassDeclarationAttributes(String in) {
        try {
            PersistenceBackendFactoryRegistry.register(NeoMapURI.NEO_MAP_SCHEME, MapPersistenceBackendFactory.getInstance());

            URI uri = NeoMapURI.createNeoMapURI(new File(in));

            org.eclipse.gmt.modisco.java.neoemf.impl.JavaPackageImpl.init();

            ResourceSet resourceSet = new ResourceSetImpl();
            resourceSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(NeoMapURI.NEO_MAP_SCHEME, PersistentResourceFactory.getInstance());

            Resource resource = resourceSet.createResource(uri);

            Map<String, Object> loadOpts = new HashMap<>();

            resource.load(loadOpts);

            QueryFactory.queryClassDeclarationAttributes(resource).callWithTime();

            if (resource instanceof PersistentResourceImpl) {
                PersistentResourceImpl.shutdownWithoutUnload((PersistentResourceImpl) resource);
            }
            else {
                resource.unload();
            }
        }
        catch (Exception e) {
            LOG.error(e);
        }
    }

    @Override
    public void queryGrabats(String in) {
        try {
            PersistenceBackendFactoryRegistry.register(NeoMapURI.NEO_MAP_SCHEME, MapPersistenceBackendFactory.getInstance());

            URI uri = NeoMapURI.createNeoMapURI(new File(in));

            org.eclipse.gmt.modisco.java.neoemf.impl.JavaPackageImpl.init();

            ResourceSet resourceSet = new ResourceSetImpl();
            resourceSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(NeoMapURI.NEO_MAP_SCHEME, PersistentResourceFactory.getInstance());

            Resource resource = resourceSet.createResource(uri);

            Map<String, Object> loadOpts = new HashMap<>();

            resource.load(loadOpts);

            QueryFactory.queryGrabats09(resource).callWithTime();

            if (resource instanceof PersistentResourceImpl) {
                PersistentResourceImpl.shutdownWithoutUnload((PersistentResourceImpl) resource);
            }
            else {
                resource.unload();
            }
        }
        catch (Exception e) {
            LOG.error(e);
        }
    }

    @Override
    public void queryInvisibleMethodDeclarations(String in) {
        try {
            PersistenceBackendFactoryRegistry.register(NeoMapURI.NEO_MAP_SCHEME, MapPersistenceBackendFactory.getInstance());

            URI uri = NeoMapURI.createNeoMapURI(new File(in));

            org.eclipse.gmt.modisco.java.neoemf.impl.JavaPackageImpl.init();

            ResourceSet resourceSet = new ResourceSetImpl();
            resourceSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(NeoMapURI.NEO_MAP_SCHEME, PersistentResourceFactory.getInstance());

            Resource resource = resourceSet.createResource(uri);

            Map<String, Object> loadOpts = new HashMap<>();

            resource.load(loadOpts);

            QueryFactory.queryInvisibleMethodDeclarations(resource).callWithTime();

            if (resource instanceof PersistentResourceImpl) {
                PersistentResourceImpl.shutdownWithoutUnload((PersistentResourceImpl) resource);
            }
            else {
                resource.unload();
            }
        }
        catch (Exception e) {
            LOG.error(e);
        }
    }

    @Override
    public void queryOrphanNonPrimitiveTypes(String in) {
        try {
            PersistenceBackendFactoryRegistry.register(NeoMapURI.NEO_MAP_SCHEME, MapPersistenceBackendFactory.getInstance());

            URI uri = NeoMapURI.createNeoMapURI(new File(in));

            org.eclipse.gmt.modisco.java.neoemf.impl.JavaPackageImpl.init();

            ResourceSet resourceSet = new ResourceSetImpl();
            resourceSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(NeoMapURI.NEO_MAP_SCHEME, PersistentResourceFactory.getInstance());

            Resource resource = resourceSet.createResource(uri);

            Map<String, Object> loadOpts = new HashMap<>();

            resource.load(loadOpts);

            QueryFactory.queryOrphanNonPrimitivesTypes(resource).callWithTime();

            if (resource instanceof PersistentResourceImpl) {
                PersistentResourceImpl.shutdownWithoutUnload((PersistentResourceImpl) resource);
            }
            else {
                resource.unload();
            }
        }
        catch (Exception e) {
            LOG.error(e);
        }
    }

    @Override
    public void queryRenameAllMethods(String in) {
        try {
            PersistenceBackendFactoryRegistry.register(NeoMapURI.NEO_MAP_SCHEME, MapPersistenceBackendFactory.getInstance());

            URI uri = NeoMapURI.createNeoMapURI(new File(in));

            org.eclipse.gmt.modisco.java.neoemf.impl.JavaPackageImpl.init();

            ResourceSet resourceSet = new ResourceSetImpl();
            resourceSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(NeoMapURI.NEO_MAP_SCHEME, PersistentResourceFactory.getInstance());

            Resource resource = resourceSet.createResource(uri);

            Map<String, Object> loadOpts = new HashMap<>();

            resource.load(loadOpts);

            String name = UUID.randomUUID().toString();
            QueryFactory.queryRenameAllMethods(resource, name).callWithTime();
            resource.save(Collections.emptyMap());

            if (resource instanceof PersistentResourceImpl) {
                PersistentResourceImpl.shutdownWithoutUnload((PersistentResourceImpl) resource);
            }
            else {
                resource.unload();
            }
        }
        catch (Exception e) {
            LOG.error(e);
        }
    }

    @Override
    public void queryThrownExceptionsPerPackage(String in) {
        try {
            PersistenceBackendFactoryRegistry.register(NeoMapURI.NEO_MAP_SCHEME, MapPersistenceBackendFactory.getInstance());

            URI uri = NeoMapURI.createNeoMapURI(new File(in));

            org.eclipse.gmt.modisco.java.neoemf.impl.JavaPackageImpl.init();

            ResourceSet resourceSet = new ResourceSetImpl();
            resourceSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(NeoMapURI.NEO_MAP_SCHEME, PersistentResourceFactory.getInstance());

            Resource resource = resourceSet.createResource(uri);

            Map<String, Object> loadOpts = new HashMap<>();

            resource.load(loadOpts);

            QueryFactory.queryThrownExceptionsPerPackage(resource).callWithTime();

            if (resource instanceof PersistentResourceImpl) {
                PersistentResourceImpl.shutdownWithoutUnload((PersistentResourceImpl) resource);
            }
            else {
                resource.unload();
            }
        }
        catch (Exception e) {
            LOG.error(e);
        }
    }

    @Override
    public void queryUnusedMethodsList(String in) {
        try {
            PersistenceBackendFactoryRegistry.register(NeoMapURI.NEO_MAP_SCHEME, MapPersistenceBackendFactory.getInstance());

            URI uri = NeoMapURI.createNeoMapURI(new File(in));

            org.eclipse.gmt.modisco.java.neoemf.impl.JavaPackageImpl.init();

            ResourceSet resourceSet = new ResourceSetImpl();
            resourceSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(NeoMapURI.NEO_MAP_SCHEME, PersistentResourceFactory.getInstance());

            Resource resource = resourceSet.createResource(uri);

            Map<String, Object> loadOpts = new HashMap<>();

            resource.load(loadOpts);

            QueryFactory.queryUnusedMethodsList(resource).callWithTime();

            if (resource instanceof PersistentResourceImpl) {
                PersistentResourceImpl.shutdownWithoutUnload((PersistentResourceImpl) resource);
            }
            else {
                resource.unload();
            }
        }
        catch (Exception e) {
            LOG.error(e);
        }
    }

    @Override
    public void queryUnusedMethodsLoop(String in) {
        try {
            PersistenceBackendFactoryRegistry.register(NeoMapURI.NEO_MAP_SCHEME, MapPersistenceBackendFactory.getInstance());

            URI uri = NeoMapURI.createNeoMapURI(new File(in));

            org.eclipse.gmt.modisco.java.neoemf.impl.JavaPackageImpl.init();

            ResourceSet resourceSet = new ResourceSetImpl();
            resourceSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(NeoMapURI.NEO_MAP_SCHEME, PersistentResourceFactory.getInstance());

            Resource resource = resourceSet.createResource(uri);

            Map<String, Object> loadOpts = new HashMap<>();

            resource.load(loadOpts);

            QueryFactory.queryUnusedMethodsLoop(resource).callWithTime(); // Query result (loops)

            if (resource instanceof PersistentResourceImpl) {
                PersistentResourceImpl.shutdownWithoutUnload((PersistentResourceImpl) resource);
            }
            else {
                resource.unload();
            }
        }
        catch (Exception e) {
            LOG.error(e);
        }
    }

    @Override
    public void traverse(String in) {
        try {
            PersistenceBackendFactoryRegistry.register(NeoMapURI.NEO_MAP_SCHEME, MapPersistenceBackendFactory.getInstance());

            URI uri = NeoMapURI.createNeoMapURI(new File(in));

            org.eclipse.gmt.modisco.java.neoemf.impl.JavaPackageImpl.init();

            ResourceSet resourceSet = new ResourceSetImpl();
            resourceSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(NeoMapURI.NEO_MAP_SCHEME, PersistentResourceFactory.getInstance());

            Resource resource = resourceSet.createResource(uri);

            Map<String, Object> loadOpts = new HashMap<>();

            resource.load(loadOpts);
            resource.getContents().get(0);

            LOG.info("Start counting");
            Instant begin = Instant.now();
            int count = Iterators.size(resource.getAllContents());
            Instant end = Instant.now();
            LOG.info("End counting");
            LOG.info("Resource {0} contains {1} elements", uri, count);
            LOG.info("Time spent: {0}", Duration.between(begin, end));

            if (resource instanceof PersistentResourceImpl) {
                PersistentResourceImpl.shutdownWithoutUnload((PersistentResourceImpl) resource);
            }
            else {
                resource.unload();
            }
        }
        catch (Exception e) {
            LOG.error(e);
        }
    }
}
