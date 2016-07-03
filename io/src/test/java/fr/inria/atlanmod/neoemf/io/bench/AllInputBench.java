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

package fr.inria.atlanmod.neoemf.io.bench;

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

import static com.google.common.base.Preconditions.checkNotNull;

public abstract class AllInputBench {

    private static final String ECORE = "ecore";
    private static final String ECORE_PATH = "/ecore/{name}." + ECORE;

    private static final String THIN_XMI = "/xmi/bench/fr.inria.atlanmod.kyanos.tests.xmi";
    private static final String LIGHT_XMI = "/xmi/bench/fr.inria.atlanmod.neo4emf.neo4jresolver.xmi";
    private static final String MEDIUM_XMI = "/xmi/bench/org.eclipse.gmt.modisco.java.kyanos.xmi";
    private static final String HEAVY_XMI = "/xmi/bench/org.eclipse.jdt.core.xmi";
    private static final String MONSTER_XMI = "/xmi/bench/org.eclipse.jdt.source.all.xmi";

    protected File getSet1() {
        return getResourceFile(THIN_XMI);
    }

    protected File getSet2() {
        return getResourceFile(LIGHT_XMI);
    }

    protected File getSet3() {
        return getResourceFile(MEDIUM_XMI);
    }

    protected File getSet4() {
        return getResourceFile(HEAVY_XMI);
    }

    protected File getSet5() {
        return getResourceFile(MONSTER_XMI);
    }

    protected File getResourceFile(String path) {
        return new File(XmiStreamReaderBench.class.getResource(path).getFile());
    }

    protected void registerEPackageFromEcore(String name) {
        File file = getResourceFile(ECORE_PATH.replaceAll("\\{name\\}", name));

        EPackage ePackage = null;

        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(ECORE, new EcoreResourceFactoryImpl());

        ResourceSet rs = new ResourceSetImpl();

        final ExtendedMetaData extendedMetaData = new BasicExtendedMetaData(rs.getPackageRegistry());
        rs.getLoadOptions().put(XMLResource.OPTION_EXTENDED_META_DATA, extendedMetaData);

        Resource r = rs.getResource(URI.createFileURI(file.toString()), true);
        EObject eObject = r.getContents().get(0);
        if (eObject instanceof EPackage) {
            ePackage = (EPackage)eObject;
            rs.getPackageRegistry().put(ePackage.getNsURI(), ePackage);
        }

        checkNotNull(ePackage, "EPackage '" + name + "' does not exist.");

        EPackage.Registry.INSTANCE.put(name, ePackage);
    }
}
