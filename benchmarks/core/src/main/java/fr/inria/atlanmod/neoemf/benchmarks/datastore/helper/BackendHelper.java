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

package fr.inria.atlanmod.neoemf.benchmarks.datastore.helper;

import fr.inria.atlanmod.neoemf.benchmarks.datastore.InternalBackend;
import fr.inria.atlanmod.neoemf.util.log.Log;

import org.apache.commons.io.FileUtils;
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
import java.io.OutputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static fr.inria.atlanmod.neoemf.util.Preconditions.checkArgument;
import static fr.inria.atlanmod.neoemf.util.Preconditions.checkNotNull;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * A class that provides static methods for managing resources.
 */
public final class BackendHelper {

    private static final String XMI = "xmi";
    private static final String ZXMI = "zxmi";

    /**
     * The name of the default ZIP file.
     */
    private static final String ZIP_FILENAME = "resources.zip";

    /**
     * A {@link Map} that holds all available resources in {@link #ZIP_FILENAME}.
     */
    private static List<String> AVAILABLE_RESOURCES;

    /**
     * A {@link Map} that holds all registered resources.
     */
    private static Map<String, String> REGISTERED_RESOURCES;

    private BackendHelper() {
    }

    /**
     * Checks that the resource, identified by its {@code filename}, is valid, i.e. if its extension is recognized.
     *
     * @param filename the name of the resource file
     *
     * @throws IllegalArgumentException if the resource is not valid
     */
    private static void checkValidResource(String filename) {
        checkNotNull(filename);

        checkArgument(filename.endsWith("." + XMI) || filename.endsWith("." + ZXMI),
                "'%s' is an invalid resource file. Only *.%s and *.%s files are allowed.", filename, XMI, ZXMI);
    }

    /**
     * Copies the given {@code sourceFile} to the temporary directory.
     *
     * @param sourceFile the file to copy
     *
     * @return the created file
     *
     * @throws IOException if an I/O error occurs during the copy
     */
    public static File copyStore(File sourceFile) throws IOException {
        File outputFile = Workspace.newTempDirectory().resolve(sourceFile.getName()).toFile();

        Log.debug("Copy {0} to {1}", sourceFile, outputFile);

        if (sourceFile.isDirectory()) {
            FileUtils.copyDirectory(sourceFile, outputFile, true);
        }
        else {
            FileUtils.copyFile(sourceFile, outputFile);
        }

        return outputFile;
    }

    public static File createTempStore(File sourceFile, InternalBackend targetBackend) throws Exception {
        return createStore(sourceFile, targetBackend, Workspace.newTempDirectory());
    }

    public static File createStore(File sourceFile, InternalBackend targetBackend) throws Exception {
        return createStore(sourceFile, targetBackend, Workspace.getStoreDirectory());
    }

    private static File createStore(File sourceFile, InternalBackend targetBackend, Path targetDir) throws Exception {
        checkValidResource(sourceFile.getName());
        checkArgument(sourceFile.exists(), "Resource '%s' does not exist", sourceFile);

        String targetFileName = getNameWithoutExtension(sourceFile.getAbsolutePath()) + "." + targetBackend.getStoreExtension();
        File targetFile = targetDir.resolve(targetFileName).toFile();

        if (targetFile.exists()) {
            Log.debug("Already existing store {0}", targetFile);
            return targetFile;
        }

        ResourceSet resourceSet = loadResourceSet();

        URI sourceUri = URI.createFileURI(sourceFile.getAbsolutePath());

        Resource sourceResource = resourceSet.createResource(sourceUri);
        Resource targetResource = targetBackend.createResource(targetFile, resourceSet);

        targetBackend.initAndGetEPackage();

        Log.debug("Loading '{0}'", sourceUri);
        Map<String, Object> loadOpts = new HashMap<>();
        if (Objects.equals(ZXMI, sourceUri.fileExtension())) {
            loadOpts.put(XMIResource.OPTION_ZIP, Boolean.TRUE);
        }
        sourceResource.load(loadOpts);

        Log.debug("Migrating");
        targetBackend.save(targetResource);

        targetResource.getContents().addAll(EcoreUtil.copyAll(sourceResource.getContents()));

        sourceResource.unload();

        Log.debug("Saving to '{0}'", targetResource.getURI());
        targetBackend.save(targetResource);

        targetBackend.unload(targetResource);

        return targetFile;
    }

    public static File createResource(String sourceFilename, InternalBackend targetBackend) throws Exception {
        if (getRegisteredResources().containsKey(sourceFilename.toLowerCase())) {
            sourceFilename = getRegisteredResources().get(sourceFilename.toLowerCase());
        }

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
        String targetFileName = getNameWithoutExtension(sourceFile.getName()) + "." + targetBackend.getResourceExtension() + "." + ZXMI;
        File targetFile = Workspace.getResourcesDirectory().resolve(targetFileName).toFile();

        if (targetFile.exists()) {
            Log.debug("Already existing resource {0}", targetFile);
            return targetFile;
        }

        ResourceSet resourceSet = loadResourceSet();

        URI sourceURI = URI.createFileURI(sourceFile.getAbsolutePath());

        Log.debug("Loading '{0}'", sourceURI);
        Resource sourceResource = resourceSet.getResource(sourceURI, true);

        URI targetURI = URI.createFileURI(targetFile.getAbsolutePath());
        Resource targetResource = resourceSet.createResource(targetURI);

        Log.debug("Migrating");
        targetResource.getContents().add(migrate(sourceResource.getContents().get(0), targetBackend.initAndGetEPackage()));

        sourceResource.unload();

        Log.debug("Saving to '{0}'", targetResource.getURI());
        Map<String, Object> saveOpts = new HashMap<>();
        saveOpts.put(XMIResource.OPTION_ZIP, Boolean.TRUE);
        targetResource.save(saveOpts);

        targetResource.unload();

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

    /**
     * Returns all resources contained in the default ZIP file.
     *
     * @return a {@link List} of the file name of the resources
     *
     * @throws IOException if the ZIP file cannot be found
     */
    private static List<String> getZipResources() throws IOException {
        if (isNull(AVAILABLE_RESOURCES)) {
            AVAILABLE_RESOURCES = new ArrayList<>();

            try (ZipInputStream inputStream = new ZipInputStream(BackendHelper.class.getResourceAsStream("/" + ZIP_FILENAME))) {
                ZipEntry entry = inputStream.getNextEntry();
                while (nonNull(entry)) {
                    if (!entry.isDirectory()) {
                        checkValidResource(entry.getName());
                        AVAILABLE_RESOURCES.add(new File(entry.getName()).getName());
                    }
                    inputStream.closeEntry();
                    entry = inputStream.getNextEntry();
                }
            }
        }
        return AVAILABLE_RESOURCES;
    }

    /**
     * Returns all registered resources.
     *
     * @return a {@link Map} containing all registered resources identified by their name
     *
     * @throws IOException if the properties file cannot be found
     */
    private static Map<String, String> getRegisteredResources() throws IOException {
        if (isNull(REGISTERED_RESOURCES)) {
            Properties properties = new Properties();
            properties.load(BackendHelper.class.getResourceAsStream("/resources.properties"));
            REGISTERED_RESOURCES = properties.entrySet().stream()
                    .collect(Collectors.toMap(e -> e.getKey().toString(), e -> e.getValue().toString()));
        }
        return REGISTERED_RESOURCES;
    }

    /**
     * Extracts a {@link ZipEntry}, named {@code filename}, from the default ZIP file to the {@code outputDir}.
     *
     * @param filename  the file name of the {@link ZipEntry} to extract
     * @param outputDir the directory where to extract the file
     *
     * @return the extracted file
     *
     * @throws IOException if an I/O error occurs during the extraction
     */
    private static File extractFromZip(String filename, Path outputDir) throws IOException {
        File outputFile = null;
        boolean fileFound = false;
        try (ZipInputStream inputStream = new ZipInputStream(BackendHelper.class.getResourceAsStream("/" + ZIP_FILENAME))) {
            ZipEntry entry = inputStream.getNextEntry();
            while (nonNull(entry) || !fileFound) {
                if (!entry.isDirectory() && Objects.equals(new File(entry.getName()).getName(), filename)) {
                    outputFile = extractEntryFromZip(inputStream, entry, outputDir);
                    fileFound = true;
                }
                inputStream.closeEntry();
                entry = inputStream.getNextEntry();
            }
        }
        return outputFile;
    }

    /**
     * Extracts a {@link ZipEntry} from the given {@code input} to the {@code outputDir}.
     *
     * @param input the input stream of the ZIP file
     * @param entry the entry in the ZIP file
     * @param outputDir the directory where to extract the file
     *
     * @return the extracted file
     *
     * @throws IOException if an I/O error occurs during the extraction
     */
    private static File extractEntryFromZip(ZipInputStream input, ZipEntry entry, Path outputDir) throws IOException {
        File outputFile = outputDir.resolve(new File(entry.getName()).getName()).toFile();
        if (outputFile.exists()) {
            Log.debug("Already extracted resource {0}", outputFile);
            return outputFile;
        }

        try (OutputStream output = new FileOutputStream(outputFile)) {
            final byte[] buffer = new byte[4096];
            int count;
            while (-1 != (count = input.read(buffer))) {
                output.write(buffer, 0, count);
            }
        }

        return outputFile;
    }

    /**
     * Retrieves the file name without its extension of {@code file}.
     *
     * @param file the file name
     *
     * @return the filename without its extension
     */
    public static String getNameWithoutExtension(String file) {
        checkNotNull(file);

        String fileName = new File(file).getName();
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? fileName : fileName.substring(0, dotIndex);
    }
}
