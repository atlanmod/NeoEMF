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

package fr.inria.atlanmod.neoemf.benchmarks.datastore.helper;

import com.google.common.io.Files;

import fr.inria.atlanmod.neoemf.benchmarks.datastore.InternalBackend;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.emf.common.util.BasicEList;
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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Objects.isNull;

public class BackendHelper {

    private static final Logger log = LogManager.getLogger();

    private static final String XMI = "xmi";
    private static final String ZXMI = "zxmi";

    private static final String ZIP_FILENAME = "resources.zip";

    private static List<String> AVAILABLE_RESOURCES;

    private BackendHelper() {
    }

    private static boolean checkValidResource(String filename) {
        checkNotNull(filename);

        checkArgument(filename.endsWith("." + XMI) || filename.endsWith("." + ZXMI),
                "'%s' is an invalid resource file. Only *.%s and *.%s files are allowed.", filename, XMI, ZXMI);

        return true;
    }

    public static File copyStore(File sourceFile) throws IOException {
        File outputFile = Workspace.newTempDirectory().resolve(sourceFile.getName()).toFile();

        log.info("Copy {} to {}", sourceFile, outputFile);

        if (sourceFile.isDirectory()) {
            FileUtils.copyDirectory(sourceFile, outputFile, true);
        }
        else {
            FileUtils.copyFile(sourceFile, outputFile);
        }

        return outputFile;
    }

    public static File createStore(File sourceFile, InternalBackend targetBackend) throws Exception {
        checkValidResource(sourceFile.getName());
        checkArgument(sourceFile.exists(), "Resource '%s' does not exist", sourceFile);

        String targetFileName = Files.getNameWithoutExtension(sourceFile.getAbsolutePath()) + "." + targetBackend.getStoreExtension();
        File targetFile = Workspace.getStoreDirectory().resolve(targetFileName).toFile();

        if (targetFile.exists()) {
            log.info("Already existing resource : {}", targetFile);
            return targetFile;
        }

        ResourceSet resourceSet = loadResourceSet();

        URI sourceUri = URI.createFileURI(sourceFile.getAbsolutePath());

        Resource sourceResource = resourceSet.createResource(sourceUri);
        Resource targetResource = targetBackend.createResource(targetFile, resourceSet);

        targetBackend.initAndGetEPackage();

        log.info("Loading '{}'", sourceUri);
        Map<Object, Object> loadOpts = new HashMap<>();
        if (ZXMI.equals(sourceUri.fileExtension())) {
            loadOpts.put(XMIResource.OPTION_ZIP, Boolean.TRUE);
        }
        sourceResource.load(loadOpts);

        log.info("Migrate to '{}'", targetResource.getURI(), sourceResource.getURI());
        targetResource.getContents().addAll(sourceResource.getContents());
        targetResource.save(targetBackend.getSaveOptions());

        targetBackend.unload(targetResource);
        sourceResource.unload();

        return targetFile;
    }

    public static File createResource(String sourceFilename, InternalBackend targetBackend) throws Exception {
        File sourceFile;
        if (getZipResources().contains(sourceFilename)) {
            // Get file from the resources/resource.zip
            sourceFile = extractFromZip(sourceFilename, Workspace.getResourcesDirectory());
        }
        else {
            // Get the file from the file system
            sourceFile = new File(sourceFilename);
        }
        checkValidResource(sourceFile.getName());
        checkArgument(sourceFile.exists(), "Resource '%s' does not exist", sourceFile);
        return createResource(sourceFile, targetBackend);
    }

    private static File createResource(File sourceFile, InternalBackend targetBackend) throws Exception {
        String targetFileName = Files.getNameWithoutExtension(sourceFile.getName()) + "." + targetBackend.getResourceExtension() + "." + ZXMI;
        File targetFile = Workspace.getResourcesDirectory().resolve(targetFileName).toFile();

        if (targetFile.exists()) {
            log.info("Already existing resource : {}", targetFile);
            return targetFile;
        }

        ResourceSet resourceSet = loadResourceSet();

        URI sourceURI = URI.createFileURI(sourceFile.getAbsolutePath());

        log.info("Loading '{}'", sourceURI);
        Resource sourceResource = resourceSet.getResource(sourceURI, true);

        URI targetURI = URI.createFileURI(targetFile.getAbsolutePath());
        Resource targetResource = resourceSet.createResource(targetURI);

        log.info("Migrate to '{}'", sourceResource.getURI(), targetResource.getURI());
        targetResource.getContents().add(migrate(sourceResource.getContents().get(0), targetBackend.initAndGetEPackage()));

        Map<Object, Object> saveOpts = new HashMap<>();
        saveOpts.put(XMIResource.OPTION_ZIP, Boolean.TRUE);
        targetResource.save(saveOpts);

        targetResource.unload();
        sourceResource.unload();

        return targetFile;
    }

    /*
     * EMF migration
     */

    private static ResourceSet loadResourceSet() {
        org.eclipse.gmt.modisco.java.emf.impl.JavaPackageImpl.init();

        ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(XMI, new XMIResourceFactoryImpl());
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(ZXMI, new XMIResourceFactoryImpl());
        return resourceSet;
    }

    private static EObject migrate(EObject eObject, EPackage targetEPackage) {
        Map<EObject, EObject> correspondencesMap = new HashMap<>();
        EObject returnEObject = getCorrespondingEObject(correspondencesMap, eObject, targetEPackage);
        copy(correspondencesMap, eObject, returnEObject);

        Iterable<EObject> allContents = () -> EcoreUtil.getAllContents(eObject, true);

        for (EObject sourceEObject : allContents) {
            EObject targetEObject = getCorrespondingEObject(correspondencesMap, sourceEObject, targetEPackage);
            copy(correspondencesMap, sourceEObject, targetEObject);
        }

        return returnEObject;
    }

    private static void copy(Map<EObject, EObject> correspondencesMap, EObject sourceEObject, EObject targetEObject) {
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
                        List<EObject> targetList = new BasicEList<>();

                        @SuppressWarnings({"unchecked"})
                        Iterable<EObject> sourceList = (Iterable<EObject>) sourceEObject.eGet(sourceFeature);
                        for (EObject aSourceList : sourceList) {
                            targetList.add(getCorrespondingEObject(correspondencesMap, aSourceList, targetEObject.eClass().getEPackage()));
                        }
                        targetEObject.eSet(targetFeature, targetList);
                    }
                }
            }
        }
    }

    private static EObject getCorrespondingEObject(Map<EObject, EObject> correspondencesMap, EObject eObject, EPackage ePackage) {
        EObject targetEObject = correspondencesMap.get(eObject);
        if (isNull(targetEObject)) {
            EClass eClass = eObject.eClass();
            EClass targetClass = (EClass) ePackage.getEClassifier(eClass.getName());
            targetEObject = EcoreUtil.create(targetClass);
            correspondencesMap.put(eObject, targetEObject);
        }
        return targetEObject;
    }

    /*
     * ZIP extraction
     */

    private static List<String> getZipResources() throws IOException {
        if (AVAILABLE_RESOURCES == null) {
            AVAILABLE_RESOURCES = new ArrayList<>();

            try (ZipInputStream inputStream = new ZipInputStream(BackendHelper.class.getResourceAsStream("/" + ZIP_FILENAME))) {
                ZipEntry entry = inputStream.getNextEntry();
                while (!isNull(entry)) {
                    if (!entry.isDirectory() && checkValidResource(entry.getName())) {
                        AVAILABLE_RESOURCES.add(new File(entry.getName()).getName());
                    }
                    inputStream.closeEntry();
                    entry = inputStream.getNextEntry();
                }
            }
        }
        return AVAILABLE_RESOURCES;
    }

    private static File extractFromZip(String filename, Path outputDir) throws IOException {
        File outputFile = null;
        boolean fileFound = false;
        try (ZipInputStream inputStream = new ZipInputStream(BackendHelper.class.getResourceAsStream("/" + ZIP_FILENAME))) {
            ZipEntry entry = inputStream.getNextEntry();
            while (!isNull(entry) || !fileFound) {
                if (!entry.isDirectory() && new File(entry.getName()).getName().equals(filename)) {
                    outputFile = extractEntryFromZip(inputStream, entry, outputDir);
                    fileFound = true;
                }
                inputStream.closeEntry();
                entry = inputStream.getNextEntry();
            }
        }
        return outputFile;
    }

    private static File extractEntryFromZip(ZipInputStream inputStream, ZipEntry entry, Path outputDir) throws IOException {
        File outputFile = outputDir.resolve(new File(entry.getName()).getName()).toFile();
        if (outputFile.exists()) {
            log.info("Already existing resource : {}", outputFile);
            return outputFile;
        }
        IOUtils.copy(inputStream, new FileOutputStream(outputFile));
        return outputFile;
    }
}
