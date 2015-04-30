/*******************************************************************************
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 *******************************************************************************/
package fr.inria.atlanmod.neoemf.graph.blueprints.datastore;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.eclipse.emf.ecore.InternalEObject.EStore;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.tinkerpop.blueprints.impls.tg.TinkerGraph;

import fr.inria.atlanmod.neoemf.datastore.AbstractPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.datastore.InvalidDataStoreException;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackend;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.datastore.estores.SearcheableResourceEStore;
import fr.inria.atlanmod.neoemf.graph.blueprints.datastore.estores.impl.AutocommitGraphResourceEStoreImpl;
import fr.inria.atlanmod.neoemf.graph.blueprints.datastore.estores.impl.DirectWriteGraphResourceEStoreImpl;
import fr.inria.atlanmod.neoemf.graph.blueprints.resources.BlueprintsResourceOptions;
import fr.inria.atlanmod.neoemf.graph.blueprints.util.NeoGraphURI;
import fr.inria.atlanmod.neoemf.resources.PersistentResourceOptions;

public class GraphPersistenceBackendFactoryTest {
    
    private AbstractPersistenceBackendFactory persistenceBackendFactory = null;
    private File testFile = null;
    @SuppressWarnings("rawtypes")
    private Map options = new HashMap();
    private List<PersistentResourceOptions.StoreOption> storeOptions = new ArrayList<PersistentResourceOptions.StoreOption>();
    
    @SuppressWarnings("unchecked")
    @Before
    public void setUp() {
        persistenceBackendFactory = new BlueprintsPersistenceBackendFactory();
        PersistenceBackendFactoryRegistry.getFactories().put(NeoGraphURI.NEO_GRAPH_SCHEME, persistenceBackendFactory);
        testFile = new File("src/test/resources/graphPersistenceBackendFactoryTestFile");
        options.put(PersistentResourceOptions.STORE_OPTIONS, storeOptions);
        
    }
    
    @After
    public void tearDown() {
        PersistenceBackendFactoryRegistry.getFactories().clear();
        if(testFile != null) {
            try {
                FileUtils.forceDelete(testFile);
            }catch(IOException e) {
                
            }
            testFile = null;
        }
    }
    
    private PersistenceBackend getInnerBackend(EStore store) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        assert store instanceof DirectWriteGraphResourceEStoreImpl : "Invalid call, can not get the inner backend if the given EStore is not a DirectWriteGraphResourceEStoreImpl";
        Field graphStoreField = DirectWriteGraphResourceEStoreImpl.class.getDeclaredField("graph");
        graphStoreField.setAccessible(true);
        return (PersistenceBackend)graphStoreField.get(store);
    }
    
    @Test
    public void testCreateTransientBackend() {
        PersistenceBackend transientBackend = persistenceBackendFactory.createTransientBackend();
        assert transientBackend instanceof BlueprintsPersistenceBackend : "Invalid backend created";
        BlueprintsPersistenceBackend graph = (BlueprintsPersistenceBackend)transientBackend;
        assert graph.getBaseGraph() instanceof TinkerGraph : "The base graph is not a TinkerGraph";
    }
    
    @Test
    public void testCreateTransientEStore() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        PersistenceBackend transientBackend = persistenceBackendFactory.createTransientBackend();
        SearcheableResourceEStore eStore = persistenceBackendFactory.createTransientEStore(null, transientBackend);
        assert eStore instanceof DirectWriteGraphResourceEStoreImpl : "Invalid EStore created";
        PersistenceBackend innerBackend = getInnerBackend(eStore);
        assert innerBackend == transientBackend: "The backend in the EStore is not the created one";
    }
    
    @Test
    public void testCreatePersistentBackendNoOptionNoConfigFile() throws InvalidDataStoreException {
        PersistenceBackend persistentBackend = persistenceBackendFactory.createPersistentBackend(testFile, Collections.EMPTY_MAP);
        assert persistentBackend instanceof BlueprintsPersistenceBackend : "Invalid backend created";
        BlueprintsPersistenceBackend graph = (BlueprintsPersistenceBackend)persistentBackend;
        assert graph.getBaseGraph() instanceof TinkerGraph : "The base graph is not the default TinkerGraph";
    }
    
    @Test
    public void testCreatePersistentEStoreNoOption() throws InvalidDataStoreException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        PersistenceBackend persistentBackend = persistenceBackendFactory.createPersistentBackend(testFile, Collections.EMPTY_MAP);
        SearcheableResourceEStore eStore = persistenceBackendFactory.createPersistentEStore(null, persistentBackend, Collections.EMPTY_MAP);
        assert eStore instanceof DirectWriteGraphResourceEStoreImpl : "Invalid EStore created";
        PersistenceBackend innerBackend = getInnerBackend(eStore);
        assert innerBackend == persistentBackend;
    }
    
    @Test
    public void testCreatePersistentEStoreDirectWriteOption() throws InvalidDataStoreException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        storeOptions.add(BlueprintsResourceOptions.EStoreGraphOption.DIRECT_WRITE);
        PersistenceBackend persistentBackend = persistenceBackendFactory.createPersistentBackend(testFile, Collections.EMPTY_MAP);
        SearcheableResourceEStore eStore = persistenceBackendFactory.createPersistentEStore(null, persistentBackend,options);
        assert eStore instanceof DirectWriteGraphResourceEStoreImpl : "Invalid EStore created";
        PersistenceBackend innerBackend = getInnerBackend(eStore);
        assert innerBackend == persistentBackend : "The backend in the EStore is not the created one";
    }
    
    @Test
    public void testCreatePersistentEStoreAutocommitOption() throws InvalidDataStoreException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        storeOptions.add(BlueprintsResourceOptions.EStoreGraphOption.AUTOCOMMIT);
        PersistenceBackend persistentBackend = persistenceBackendFactory.createPersistentBackend(testFile, Collections.EMPTY_MAP);
        SearcheableResourceEStore eStore = persistenceBackendFactory.createPersistentEStore(null, persistentBackend, options);
        assert eStore instanceof AutocommitGraphResourceEStoreImpl : "Invalid EStore created";
        PersistenceBackend innerBackend = getInnerBackend(eStore);
        assert innerBackend == persistentBackend : "The backend in the EStore is not the created one";
    }

}
