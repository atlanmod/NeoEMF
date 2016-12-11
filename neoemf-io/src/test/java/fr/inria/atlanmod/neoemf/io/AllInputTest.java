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

package fr.inria.atlanmod.neoemf.io;

import fr.inria.atlanmod.neoemf.AllTest;
import fr.inria.atlanmod.neoemf.io.mock.StructuralPersistanceHandler;

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

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class AllInputTest extends AllTest {

    protected static final int UNKNOWN_INDEX = -1;

    private static final String XMI_STANDARD = "/io/xmi/sampleStandard.xmi";
    private static final String XMI_WITH_ID = "/io/xmi/sampleWithId.xmi";

    private static final String ECORE = "ecore";
    private static final String ECORE_PATH = "/io/ecore/{name}." + ECORE;

    protected StructuralPersistanceHandler persistanceHandler;

    protected File sample;

    protected File getXmiStandard() {
        return getResourceFile(XMI_STANDARD);
    }

    protected File getXmiWithId() {
        return getResourceFile(XMI_WITH_ID);
    }

    protected File getResourceFile(String path) {
        return new File(AllInputTest.class.getResource(path).getFile());
    }

    /**
     * Registers a EPackage in {@link org.eclipse.emf.ecore.EPackage.Registry} according to its {@code prefix} and
     * {@code uri}, from an Ecore file.
     * <p/>
     * The targetted Ecore file must be present in {@code /resources/ecore}.
     */
    protected void registerEPackageFromEcore(String prefix, String uri) {
        File file = getResourceFile(ECORE_PATH.replaceAll("\\{name\\}", prefix));

        EPackage ePackage = null;

        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(ECORE, new EcoreResourceFactoryImpl());

        ResourceSet rs = new ResourceSetImpl();

        final ExtendedMetaData extendedMetaData = new BasicExtendedMetaData(rs.getPackageRegistry());
        rs.getLoadOptions().put(XMLResource.OPTION_EXTENDED_META_DATA, extendedMetaData);

        Resource r = rs.getResource(URI.createFileURI(file.toString()), true);
        EObject eObject = r.getContents().get(0);
        if (eObject instanceof EPackage) {
            ePackage = (EPackage) eObject;
            rs.getPackageRegistry().put(ePackage.getNsURI(), ePackage);
        }

        assertThat(ePackage).isNotNull(); // "EPackage does not exist"

        EPackage.Registry.INSTANCE.put(uri, ePackage);
    }
}
