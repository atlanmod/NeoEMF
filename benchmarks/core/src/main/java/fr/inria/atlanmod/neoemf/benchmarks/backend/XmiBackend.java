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

package fr.inria.atlanmod.neoemf.benchmarks.backend;

import fr.inria.atlanmod.neoemf.benchmarks.query.Query;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;

public class XmiBackend extends AbstractBackend {

    public static final String NAME = "xmi";

    @Override
    protected String getResourceImportExtension() {
        return "xmi";
    }

    @Override
    protected String getResourceExportExtension() {
        return "xmi";
    }

    @Override
    protected Class<?> getEPackageClass() {
        return org.eclipse.gmt.modisco.java.emf.impl.JavaPackageImpl.class;
    }

    @Override
    protected File createResource(File inputFile, Path outputPath) throws Exception {
        File outputFile = outputPath.toFile();

        if (outputFile.exists()) {
            return outputFile;
        }

        URI sourceUri = URI.createFileURI(inputFile.getAbsolutePath());
        URI targetUri = URI.createFileURI(outputPath.toString());

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

        targetResource.getContents().clear();

        {
            LOG.info("Start moving elements");
            targetResource.getContents().addAll(sourceResource.getContents());
            LOG.info("End moving elements");
        }

        {
            LOG.info("Start saving");
            targetResource.save(getSaveOptions());
            LOG.info("Saved");
        }

        sourceResource.unload();
        targetResource.unload();

        return outputFile;
    }

    @Override
    public Resource loadResource(Path path) throws Exception {
        Resource resource;

        URI uri = URI.createFileURI(path.toString());

        getEPackageClass().getMethod("init").invoke(null);

        ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("zxmi", new XMIResourceFactoryImpl());

        resource = resourceSet.createResource(uri);
        resource.load(getLoadOptions());

        return resource;
    }

    @Override
    public void unloadResource(Resource resource) {
        if (!isNull(resource) && resource.isLoaded()) {
            resource.unload();
        }
    }
}
