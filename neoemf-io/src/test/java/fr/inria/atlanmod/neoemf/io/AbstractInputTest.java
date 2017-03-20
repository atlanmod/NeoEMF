/*
 * Copyright (c) 2013-2017 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.io;

import fr.inria.atlanmod.neoemf.AbstractTest;
import fr.inria.atlanmod.neoemf.io.mock.DummyWriter;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class AbstractInputTest extends AbstractTest {

    protected static final int UNKNOWN_INDEX = -1;

    protected DummyWriter persistanceHandler;

    protected File sample;

    protected static File getXmiStandard() {
        return getResourceFile("/io/xmi/sampleStandard.xmi");
    }

    protected static File getXmiWithId() {
        return getResourceFile("/io/xmi/sampleWithId.xmi");
    }

    protected static File getResourceFile(String path) {
        return new File(AbstractInputTest.class.getResource(path).getFile());
    }

    /**
     * Registers a EPackage in {@link Registry} according to its {@code prefix} and {@code uri}, from an Ecore file.
     * <p>
     * The targeted Ecore file must be present in {@code /resources/ecore}.
     */
    protected static void registerEPackageFromEcore(String prefix, String uri) {
        File file = getResourceFile("/io/ecore/{name}.ecore".replaceAll("\\{name\\}", prefix));

        EPackage ePackage = null;

        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());

        ResourceSet rs = new ResourceSetImpl();

        final ExtendedMetaData extendedMetaData = new BasicExtendedMetaData(rs.getPackageRegistry());
        rs.getLoadOptions().put(XMLResource.OPTION_EXTENDED_META_DATA, extendedMetaData);

        Resource r = rs.getResource(URI.createFileURI(file.toString()), true);
        EObject eObject = r.getContents().get(0);
        if (eObject instanceof EPackage) {
            ePackage = (EPackage) eObject;
            rs.getPackageRegistry().put(ePackage.getNsURI(), ePackage);
        }

        assertThat(ePackage).isNotNull();

        Registry.INSTANCE.put(uri, ePackage);
    }
}
