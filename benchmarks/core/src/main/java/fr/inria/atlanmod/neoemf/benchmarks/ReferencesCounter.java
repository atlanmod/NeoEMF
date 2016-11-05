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

package fr.inria.atlanmod.neoemf.benchmarks;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import java.io.File;
import java.io.FileWriter;
import java.util.Iterator;

public class ReferencesCounter {

    private static final Logger LOG = LogManager.getLogger();

    public void count(String in, String out, String inClassName, String label) {
        try {
            URI sourceUri = URI.createFileURI(in);
            ReferencesCounter.class.getClassLoader().loadClass(inClassName).getMethod("init").invoke(null);

            ResourceSet resourceSet = new ResourceSetImpl();
            resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
            resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("zxmi", new XMIResourceFactoryImpl());

            Resource sourceResource = resourceSet.getResource(sourceUri, true);

            FileWriter writer = new FileWriter(new File(out));
            try {
                writer.write(label);
                writer.write("\n");

                for (Iterator<EObject> iterator = sourceResource.getAllContents(); iterator.hasNext(); ) {
                    EObject eObject = iterator.next();
                    for (EStructuralFeature feature : eObject.eClass().getEAllStructuralFeatures()) {
                        if (feature.isMany() && eObject.eIsSet(feature)) {
                            EList<?> value = (EList<?>) eObject.eGet(feature);
//							if (value.size() > 10) 
                            writer.write(String.format("%d\n", value.size()));
                        }
                    }
                }
            }
            finally {
                IOUtils.closeQuietly(writer);
            }
        }
        catch (Exception e) {
            LOG.error(e);
        }
    }
}
