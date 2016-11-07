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

package fr.inria.atlanmod.neoemf.benchmarks.io;

import fr.inria.atlanmod.neoemf.benchmarks.Creator;
import fr.inria.atlanmod.neoemf.benchmarks.query.Query;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class XmiCreator extends AbstractCreator {

    private static Creator INSTANCE;

    private XmiCreator() {
    }

    public static Creator getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new XmiCreator();
        }
        return INSTANCE;
    }

    @Override
    public String getBaseName() {
        return "xmi";
    }

    @Override
    public String getResourceName() {
        return "xmi";
    }

    @Override
    public Class<?> getAssociatedClass() {
        return org.eclipse.gmt.modisco.java.emf.impl.JavaPackageImpl.class;
    }

    @Override
    public File create(String in, String out) throws Exception {
        File destFile = new File(out);

        if (destFile.exists()) {
            return destFile;
        }

        try {
            URI sourceUri = URI.createFileURI(in);
            URI targetUri = URI.createFileURI(out);

            org.eclipse.gmt.modisco.java.emf.impl.JavaPackageImpl.init();

            ResourceSet resourceSet = new ResourceSetImpl();
            resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
            resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("zxmi", new XMIResourceFactoryImpl());

            Resource sourceResource = resourceSet.createResource(sourceUri);
            Map<String, Object> loadOpts = new HashMap<>();
            if ("zxmi".equals(sourceUri.fileExtension())) {
                loadOpts.put(XMIResource.OPTION_ZIP, Boolean.TRUE);
            }

            ((Query<Void>) () -> {
                Query.LOG.info("Loading source resource");
                sourceResource.load(loadOpts);
                Query.LOG.info("Source resource loaded");
                return null;
            }).callWithMemory();

            Resource targetResource = resourceSet.createResource(targetUri);

            Map<String, Object> saveOpts = new HashMap<>();
            targetResource.save(saveOpts);
            targetResource.getContents().clear();

            {
                LOG.info("Start moving elements");
                targetResource.getContents().addAll(sourceResource.getContents());
                LOG.info("End moving elements");
                LOG.info("Start saving");
                targetResource.save(saveOpts);
                LOG.info("Saved");
            }

            sourceResource.unload();
            targetResource.unload();
        }
        catch (Exception e) {
            LOG.error(e);
            throw e;
        }
        return destFile;
    }
}
