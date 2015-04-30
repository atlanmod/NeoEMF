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
package fr.inria.atlanmod.neoemf.graph.blueprints.util;

import java.io.File;

import org.eclipse.emf.common.util.URI;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.inria.atlanmod.neoemf.datastore.AbstractPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.graph.blueprints.datastore.BlueprintsPersistenceBackendFactory;

public class NeoGraphURITest {

	private AbstractPersistenceBackendFactory persistenceBackendFactory = new BlueprintsPersistenceBackendFactory();
	private File testFile = null;
	
	@Before
	public void setUp() {
		PersistenceBackendFactoryRegistry.getFactories().clear();
		PersistenceBackendFactoryRegistry.getFactories().put(NeoGraphURI.NEO_GRAPH_SCHEME, persistenceBackendFactory);
		testFile = new File("src/test/resource/neoGraphURITestFile");
	}
	
	@After
	public void tearDown() {
		if(testFile != null) {
			testFile.delete();
			testFile = null;
		}
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testCreateNeoGraphURIFromStandardURIInvalidScheme() {
		URI invalidURI = URI.createURI("invalid:/test");
		NeoGraphURI.createNeoGraphURI(invalidURI);
	}
	
	@Test
	public void testCreateNeoGraphURIFromStandardURIValidScheme() {
		URI validURI = URI.createURI(NeoGraphURI.NEO_GRAPH_SCHEME+":/test");
		URI neoURI = NeoGraphURI.createNeoGraphURI(validURI);
		assert neoURI.scheme().equals(NeoGraphURI.NEO_GRAPH_SCHEME);
	}
	
	@Test
	public void testCreateNeoGraphURIFromFileURI() {
		URI fileURI = URI.createFileURI(testFile.getAbsolutePath());
		URI neoURI = NeoGraphURI.createNeoGraphURI(fileURI);
		assert neoURI.scheme().equals(NeoGraphURI.NEO_GRAPH_SCHEME);
	}
	
	@Test
	public void testCreateNeoURIFromFile() {
		URI neoURI = NeoGraphURI.createNeoGraphURI(testFile);
		assert neoURI.scheme().equals(NeoGraphURI.NEO_GRAPH_SCHEME);
	}
	
}
