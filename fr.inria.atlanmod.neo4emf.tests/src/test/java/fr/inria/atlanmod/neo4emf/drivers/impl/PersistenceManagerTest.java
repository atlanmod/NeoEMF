package fr.inria.atlanmod.neo4emf.drivers.impl;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Collections;

import fr.inria.atlanmod.neo4emf.drivers.IPersistenceManager;
import fr.inria.atlanmod.neo4emf.drivers.NESession;
import mgraph.MgraphPackage;
import org.apache.commons.io.FileUtils;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.inria.atlanmod.neo4emf.INeo4emfResource;
import fr.inria.atlanmod.neo4emf.INeo4emfResourceFactory;
import fr.inria.atlanmod.neo4emf.drivers.NEConfiguration;
import fr.inria.atlanmod.neo4emf.testdata.TestPackage;

public class PersistenceManagerTest {

	private static final File DB_FOLDER = new File("/tmp/PersistentManagerTest");
    private static final URI uri = URI.createURI("neo4emf:"+DB_FOLDER.getAbsolutePath());
    private static NESession session;
	private static IPersistenceManager pm;

    private INeo4emfResource resource;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		FileUtils.forceMkdir(DB_FOLDER);
        session = new NESession(TestPackage.eINSTANCE);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		FileUtils.forceDelete(DB_FOLDER);
	}

	@Before
	public void setUp() throws Exception {
        resource = session.createResource(uri,10);

        IPersistenceManager pm = resource.getPersistenceManager();
	}

	@After
	public void tearDown() throws Exception {
        session.close();
	}

    @Test
    public void testNothing() throws Exception {

    }

}
