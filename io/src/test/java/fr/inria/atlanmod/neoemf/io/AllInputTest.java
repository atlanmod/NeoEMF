/*
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.io;

import fr.inria.atlanmod.neoemf.AllTest;
import fr.inria.atlanmod.neoemf.io.bench.XmiStreamReaderBench;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.junit.After;
import org.junit.Before;

import java.io.File;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 *
 */
public abstract class AllInputTest extends AllTest {

    private static final String JAVA_ECORE = "/metamodel/java.ecore";

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    protected File getResourceFile(String path) {
        return new File(XmiStreamReaderBench.class.getResource(path).getFile());
    }

    protected void registerJavaEPackage() {
        File file = getResourceFile(JAVA_ECORE);
        String prefix = "java";

        EPackage ePackage = null;

        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());

        ResourceSet rs = new ResourceSetImpl();

        final ExtendedMetaData extendedMetaData = new BasicExtendedMetaData(rs.getPackageRegistry());
        rs.getLoadOptions().put(XMLResource.OPTION_EXTENDED_META_DATA, extendedMetaData);

        Resource r = rs.getResource(URI.createFileURI(file.toString()), true);
        EObject eObject = r.getContents().get(0);
        if (eObject instanceof EPackage) {
            ePackage = (EPackage)eObject;
            rs.getPackageRegistry().put(ePackage.getNsURI(), ePackage);
        }

        checkNotNull(ePackage, "EPackage '" + prefix + "' does not exist.");

        EPackage.Registry.INSTANCE.put("java", ePackage);
    }
}
