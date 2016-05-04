package fr.inria.atlanmod.neoemf.issues;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Internal;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.inria.atlanmod.neoemf.datastore.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.map.datastore.MapPersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.map.util.NeoMapURI;
import fr.inria.atlanmod.neoemf.resources.impl.PersistentResourceFactoryImpl;
import fr.inria.atlanmod.neoemf.resources.impl.PersistentResourceImpl;
import fr.inria.atlanmod.neoemf.test.commons.models.mapSample.MapSampleFactory;
import fr.inria.atlanmod.neoemf.test.commons.models.mapSample.MapSamplePackage;
import fr.inria.atlanmod.neoemf.test.commons.models.mapSample.Pack;
import fr.inria.atlanmod.neoemf.test.commons.models.mapSample.PackContent;

public class SodiIssue {

    private static final Path TEST_DIR = Paths.get(System.getProperty("java.io.tmpdir"), "NeoEMF");
    private static final String TEST_FILENAME = "sodiMapResource";

	protected MapSampleFactory mFac;
	protected MapSamplePackage mPack;
	protected Resource resource;

    protected File testFile;
	
	@Before
	public void setUp() {
        testFile = TEST_DIR.resolve(TEST_FILENAME + String.valueOf(new Date().getTime())).toFile();
		PersistenceBackendFactoryRegistry.getFactories().put(NeoMapURI.NEO_MAP_SCHEME, new MapPersistenceBackendFactory());
		mPack = MapSamplePackage.eINSTANCE;
		mFac = MapSampleFactory.eINSTANCE;
		ResourceSet rSet = new ResourceSetImpl();
		rSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put(NeoMapURI.NEO_MAP_SCHEME, new PersistentResourceFactoryImpl());
		resource = rSet.createResource(NeoMapURI.createNeoMapURI(testFile));
	}
	
	@After
	public void tearDown() throws Exception {
		PersistentResourceImpl.shutdownWithoutUnload((PersistentResourceImpl)resource);
		if(testFile.exists()) {
            try {
                FileUtils.forceDelete(testFile);
            } catch (IOException e) {
                //System.err.println(e);
            }
		}
	}
	
	@Test
	public void test() {
		Pack p1 = mFac.createPack();
		Pack p2 = mFac.createPack();
		p2.setParentPack(p1);
		resource.getContents().add(p1);
		PackContent c1 =  mFac.createPackContent();
		c1.setParentPack(p2);
		p2.eContainer();
	}
	
	@Test
	public void test2() {
		Pack p1 = mFac.createPack();
		Pack p2 = mFac.createPack();
		Pack p3 = mFac.createPack();
		p3.setParentPack(p2);
		p1.getPacks().add(p2);
		resource.getContents().add(p1);
		PackContent c1 =  mFac.createPackContent();
		c1.setParentPack(p3);
		p2.eContainer();
		p3.eContainer();
		c1.eContainer();
	}
	
	@Test
	public void test3() {
		Pack p1 = mFac.createPack();
		Pack p2 = mFac.createPack();
		p2.setParentPack(p1);
		try {
			resource.save(Collections.EMPTY_MAP);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		PackContent pc1 = mFac.createPackContent();
		PackContent pc2 = mFac.createPackContent();
		PackContent pc3 = mFac.createPackContent();
		PackContent pc4 = mFac.createPackContent();
		PackContent pc5 = mFac.createPackContent();
		
		p2.getOwnedContents().add(pc1);
		p2.getOwnedContents().add(pc2);
		p2.getOwnedContents().add(pc3);
		p2.getOwnedContents().add(pc4);
		p2.getOwnedContents().add(pc5);
		
		System.out.println(resource.isLoaded());
		InternalEObject internalEObject = (InternalEObject)p2;
		internalEObject.eSetResource((Internal) resource, null);
		p2.eResource();
		p2.eContainer();
		
		p2.getOwnedContents().add(mFac.createPackContent());
		
	}
	
	@Test
	public void test4() {
		Pack p1 = mFac.createPack();
		Pack p2 = mFac.createPack();
		p2.setParentPack(p1);
		
		PackContent pc1 = mFac.createPackContent();
		PackContent pc2 = mFac.createPackContent();
		PackContent pc3 = mFac.createPackContent();
		PackContent pc4 = mFac.createPackContent();
		PackContent pc5 = mFac.createPackContent();
		
		p2.getOwnedContents().add(pc1);
		p2.getOwnedContents().add(pc2);
		p2.getOwnedContents().add(pc3);
		p2.getOwnedContents().add(pc4);
		
		
		try {
			resource.save(Collections.EMPTY_MAP);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		resource.getContents().add(p1);
		
		p2.getOwnedContents().add(pc5);
		
		System.out.println(p2.eContainer());
		System.out.println(pc4.eContainer());
		System.out.println(pc5.eContainer());
		
		
	}
	
	@Test
	public void test5() {
		Pack p1 = mFac.createPack();
		Pack p2 = mFac.createPack();
//		p2.setParentPack(p1);
		
		PackContent pc1 = mFac.createPackContent();
		PackContent pc2 = mFac.createPackContent();
		PackContent pc3 = mFac.createPackContent();
		PackContent pc4 = mFac.createPackContent();
		PackContent pc5 = mFac.createPackContent();
		
		p2.getOwnedContents().add(pc1);
		p2.getOwnedContents().add(pc2);
		p2.getOwnedContents().add(pc3);
		p2.getOwnedContents().add(pc4);
		
		
		try {
			resource.save(Collections.EMPTY_MAP);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		resource.getContents().add(p1);
		
		p2.setParentPack(p1);
//		p1.getPacks().add(p2);
		
//		p2.getOwnedContents().add(pc5);
		
		System.out.println(p2.eContainer());
		System.out.println(pc4.eContainer());
		System.out.println(pc5.eContainer());
		
		
	}
	
}
