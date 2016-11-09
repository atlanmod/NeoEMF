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

import com.google.common.io.Files;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static java.util.Objects.isNull;

public class Migrator {

    private static final Logger LOG = LogManager.getLogger();

    private static final String ZIP_FILENAME = "resources.zip";

    private static Migrator INSTANCE;

    public List<String> availableResources;

    private Migrator() {
    }

    public static Migrator getInstance() {
        if (isNull(INSTANCE)) {
            INSTANCE = new Migrator();
        }
        return INSTANCE;
    }

    private List<String> getAvailableResources() throws Exception {
        if (availableResources == null) {
            availableResources = new ArrayList<>();

            try (ZipInputStream inputStream = new ZipInputStream(Migrator.class.getResourceAsStream("/" + ZIP_FILENAME))) {
                ZipEntry entry = inputStream.getNextEntry();

                while (!isNull(entry)) {
                    if (!entry.isDirectory() && entry.getName().endsWith("." + "xmi")) {
                        availableResources.add(Files.getNameWithoutExtension(new File(entry.getName()).getName()));
                    }
                    inputStream.closeEntry();
                    entry = inputStream.getNextEntry();
                }
            }
        }
        return availableResources;
    }

    public File migrate(String name, String outputExtension, Class<?> outputClass) throws Exception {
        if (!getAvailableResources().contains(name)) {
            throw new IllegalArgumentException("Resource '" + name + ".xmi' does not exist in " + ZIP_FILENAME);
        }

        File inputFile = extract(name, Workspace.getResourcesDirectory());

        return migrate(inputFile, outputExtension, outputClass);
    }

    private File migrate(File inputFile, String outputExtension, Class<?> outputClass) throws Exception {
        String outputFileName = Files.getNameWithoutExtension(inputFile.getName()) + "." + outputExtension + "." + "zxmi";
        Path outputPath = Workspace.getResourcesDirectory().resolve(outputFileName);
        File outputFile = outputPath.toFile();

        if (outputFile.exists()) {
            LOG.info("Already existing resource : " + outputFile);
            return outputFile;
        }

        URI srcUri = URI.createFileURI(inputFile.getAbsolutePath());
        URI destUri = URI.createFileURI(outputFile.getAbsolutePath());

        // Default source EPackage
        org.eclipse.gmt.modisco.java.emf.impl.JavaPackageImpl.init();

        // Destination EPackage
        EPackage destEPackage = (EPackage) outputClass.getMethod("init").invoke(null);

        ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("zxmi", new XMIResourceFactoryImpl());

        LOG.info("Loading '{}'", srcUri);
        Resource sourceResource = resourceSet.getResource(srcUri, true);
        Resource targetResource = resourceSet.createResource(destUri);

        targetResource.getContents().clear();
        LOG.info("Start migration from '{}'", srcUri);
        targetResource.getContents().add(migrate(sourceResource.getContents().get(0), destEPackage));
        LOG.info("Migration finished");

        Map<String, Object> saveOpts = new HashMap<>();
        saveOpts.put(XMIResource.OPTION_ZIP, Boolean.TRUE);

        LOG.info("Start saving to '{}'", destUri);
        targetResource.save(saveOpts);
        LOG.info("Saving done");

        sourceResource.unload();
        targetResource.unload();

        return outputFile;
    }

    private EObject migrate(EObject eObject, EPackage targetEPackage) {
        Map<EObject, EObject> correspondencesMap = new HashMap<>();
        EObject returnEObject = getCorrespondingEObject(correspondencesMap, eObject, targetEPackage);
        copy(correspondencesMap, eObject, returnEObject);
        Iterator<EObject> iterator = EcoreUtil.getAllContents(eObject, true);
        while (iterator.hasNext()) {
            EObject sourceEObject = iterator.next();
            EObject targetEObject = getCorrespondingEObject(correspondencesMap, sourceEObject, targetEPackage);
            copy(correspondencesMap, sourceEObject, targetEObject);
        }
        return returnEObject;
    }

    private void copy(Map<EObject, EObject> correspondencesMap, EObject sourceEObject, EObject targetEObject) {
        for (EStructuralFeature sourceFeature : sourceEObject.eClass().getEAllStructuralFeatures()) {
            if (sourceEObject.eIsSet(sourceFeature)) {
                EStructuralFeature targetFeature = targetEObject.eClass().getEStructuralFeature(sourceFeature.getName());
                if (sourceFeature instanceof EAttribute) {
                    targetEObject.eSet(targetFeature, sourceEObject.eGet(sourceFeature));
                }
                else { // EReference
                    if (!sourceFeature.isMany()) {
                        targetEObject.eSet(targetFeature, getCorrespondingEObject(correspondencesMap, (EObject) sourceEObject.eGet(targetFeature), targetEObject.eClass().getEPackage()));
                    }
                    else {
                        @SuppressWarnings({"unchecked"})
                        EList<EObject> sourceList = (EList<EObject>) sourceEObject.eGet(sourceFeature);
                        EList<EObject> targetList = new BasicEList<>();
                        for (EObject aSourceList : sourceList) {
                            targetList.add(getCorrespondingEObject(correspondencesMap, aSourceList, targetEObject.eClass().getEPackage()));
                        }
                        targetEObject.eSet(targetFeature, targetList);
                    }
                }
            }
        }
    }

    private EObject getCorrespondingEObject(Map<EObject, EObject> correspondencesMap, EObject eObject, EPackage ePackage) {
        EObject targetEObject = correspondencesMap.get(eObject);
        if (isNull(targetEObject)) {
            EClass eClass = eObject.eClass();
            EClass targetClass = (EClass) ePackage.getEClassifier(eClass.getName());
            targetEObject = EcoreUtil.create(targetClass);
            correspondencesMap.put(eObject, targetEObject);
        }
        return targetEObject;
    }

    private File extract(String filename, Path outputDir) throws Exception {
        File file = null;
        boolean fileFound = false;
        try (ZipInputStream inputStream = new ZipInputStream(Migrator.class.getResourceAsStream("/" + ZIP_FILENAME))) {
            ZipEntry entry = inputStream.getNextEntry();
            while (!isNull(entry) || !fileFound) {
                if (!entry.isDirectory() && entry.getName().endsWith("." + "xmi") && Files.getNameWithoutExtension(entry.getName()).equals(filename)) {
                    file = extractEntry(inputStream, entry, outputDir);
                    fileFound = true;
                }
                inputStream.closeEntry();
                entry = inputStream.getNextEntry();
            }
        }
        return file;
    }

    private File extractEntry(ZipInputStream inputStream, ZipEntry entry, Path outputDir) throws Exception {
        File file = outputDir.resolve(new File(entry.getName()).getName()).toFile();
        if (file.exists()) {
            LOG.info("Already existing resource : " + file);
            return file;
        }
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file))) {
            byte[] bytesIn = new byte[4096];
            int read;
            while ((read = inputStream.read(bytesIn)) != -1) {
                bos.write(bytesIn, 0, read);
            }
        }
        return file;
    }
}
