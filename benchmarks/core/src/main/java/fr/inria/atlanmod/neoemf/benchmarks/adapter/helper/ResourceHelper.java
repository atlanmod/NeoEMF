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

package fr.inria.atlanmod.neoemf.benchmarks.adapter.helper;

import fr.inria.atlanmod.neoemf.benchmarks.adapter.Adapter;
import fr.inria.atlanmod.neoemf.util.log.Log;

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
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collection;
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
 * A class that provides static methods for {@link Resource} management.
 */
public final class ResourceHelper {

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

    private ResourceHelper() {
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
     * Copies the given {@code file} to the temporary directory.
     *
     * @param file the file to copy
     *
     * @return the created file
     *
     * @throws IOException if an I/O error occurs during the copy
     */
    public static File copyStore(File file) throws IOException {
        Path outputFile = Workspace.newTempDirectory().resolve(file.getName());

        Log.info("Copying {0} to {1}", file, outputFile);

        Files.walkFileTree(file.toPath(), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                Path targetPath = outputFile.resolve(file.toPath().relativize(dir));
                if (!Files.exists(targetPath)) {
                    Files.createDirectory(targetPath);
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
                Files.copy(path, outputFile.resolve(file.toPath().relativize(path)), StandardCopyOption.REPLACE_EXISTING);
                return FileVisitResult.CONTINUE;
            }
        });

        return outputFile.toFile();
    }

    /**
     * Creates a new {@link Resource} (a {@link fr.inria.atlanmod.neoemf.resource.PersistentResource} in case of NeoEMF)
     * from the given {@code resourceFile}, and stores it to the given {@code adapter}, located in a temporary
     * directory.
     *
     * @param resourceFile the resource resourceFile
     * @param adapter      the adapter where to store the resource
     *
     * @return the created resourceFile
     *
     * @throws Exception if a error occurs during the creation of the store
     * @see Workspace#newTempDirectory()
     */
    public static File createTempStore(File resourceFile, Adapter.Internal adapter) throws Exception {
        return createStore(resourceFile, adapter, Workspace.newTempDirectory());
    }

    /**
     * Creates a new {@link Resource} (a {@link fr.inria.atlanmod.neoemf.resource.PersistentResource} in case of NeoEMF)
     * from the given {@code resourceFile}, and stores it to the given {@code targetAdapter}, located in the workspace.
     *
     * @param resourceFile the resource resourceFile
     * @param adapter      the adapter where to store the resource
     *
     * @return the created resourceFile
     *
     * @throws Exception if a error occurs during the creation of the store
     * @see Workspace#getStoreDirectory()
     */
    public static File createStore(File resourceFile, Adapter.Internal adapter) throws Exception {
        return createStore(resourceFile, adapter, Workspace.getStoreDirectory());
    }

    /**
     * Creates a new {@link Resource} (a {@link fr.inria.atlanmod.neoemf.resource.PersistentResource} in case of NeoEMF)
     * from the given {@code resourceFile}, and stores it to the given {@code targetAdapter}, located in {@code
     * dir}.
     *
     * @param resourceFile the resource resourceFile
     * @param adapter      the adapter where to store the resource
     * @param dir          the location of the adapter
     *
     * @return the created resourceFile
     *
     * @throws Exception if a error occurs during the creation of the store
     */
    private static File createStore(File resourceFile, Adapter.Internal adapter, Path dir) throws Exception {
        checkValidResource(resourceFile.getName());
        checkArgument(resourceFile.exists(), "Resource '%s' does not exist", resourceFile);

        String targetFileName = getNameWithoutExtension(resourceFile.getAbsolutePath()) + "." + adapter.getStoreExtension();
        File targetFile = dir.resolve(targetFileName).toFile();

        if (targetFile.exists()) {
            Log.info("Already existing resource: {0}", targetFile);
            return targetFile;
        }

        ResourceSet resourceSet = loadResourceSet();

        URI sourceUri = URI.createFileURI(resourceFile.getAbsolutePath());
        Resource sourceResource = resourceSet.createResource(sourceUri);

        adapter.initAndGetEPackage();

        Log.info("Loading resource from: {0}", sourceUri);

        Map<String, Object> loadOpts = new HashMap<>();
        if (Objects.equals(ZXMI, sourceUri.fileExtension())) {
            loadOpts.put(XMIResource.OPTION_ZIP, true);
        }
        sourceResource.load(loadOpts);

        Log.info("Copying resource content");

        Collection<EObject> targetContents = EcoreUtil.copyAll(sourceResource.getContents());
        sourceResource.unload();

        Log.info("Migrating resource content");

        Resource targetResource = adapter.createResource(targetFile, resourceSet);
        adapter.save(targetResource);

        targetResource.getContents().addAll(targetContents);

        Log.info("Saving resource to: {0}", targetResource.getURI());
        adapter.save(targetResource);

        adapter.unload(targetResource);

        return targetFile;
    }

    /**
     * Creates a new {@link Resource} from the given {@code resourceFileName}, and adapts it for the given
     * {@code targetAdapter}. The resource file can be placed in the resource ZIP, or in the file system.
     *
     * @param resourceFileName the name of the resource file
     * @param adapter          the adapter where to store the resource
     *
     * @return the created file
     *
     * @throws Exception if a error occurs during the creation of the resource
     */
    public static File createResource(String resourceFileName, Adapter.Internal adapter) throws Exception {
        if (getRegisteredResources().containsKey(resourceFileName.toLowerCase())) {
            resourceFileName = getRegisteredResources().get(resourceFileName.toLowerCase());
        }

        File sourceFile;
        if (getZipResources().contains(resourceFileName)) {
            // Get file from the resources/resource.zip
            sourceFile = extractFromZip(resourceFileName, Workspace.getResourcesDirectory());
        }
        else {
            // Get the file from the file system
            sourceFile = new File(resourceFileName);
        }

        checkValidResource(sourceFile.getName());
        checkArgument(sourceFile.exists(), "Resource '%s' does not exist", sourceFile);
        return createResource(sourceFile, adapter);
    }

    /**
     * Creates a new {@link Resource} from the given {@code file}, and adapts it for the given {@code targetAdapter}.
     *
     * @param resourceFile the resource file
     * @param adapter      the adapter where to store the resource
     *
     * @return the created file
     *
     * @throws Exception if a error occurs during the creation of the resource
     */
    private static File createResource(File resourceFile, Adapter.Internal adapter) throws Exception {
        String targetFileName = getNameWithoutExtension(resourceFile.getName()) + "." + adapter.getResourceExtension() + "." + ZXMI;
        File targetFile = Workspace.getResourcesDirectory().resolve(targetFileName).toFile();

        if (targetFile.exists()) {
            Log.info("Already existing resource: {0}", targetFile);
            return targetFile;
        }

        ResourceSet resourceSet = loadResourceSet();

        URI sourceUri = URI.createFileURI(resourceFile.getAbsolutePath());

        Log.info("Loading resource from: {0}", sourceUri);

        Resource sourceResource = resourceSet.getResource(sourceUri, true);

        Log.info("Copying resource content");

        EObject targetContent = migrate(sourceResource.getContents().get(0), adapter.initAndGetEPackage());
        sourceResource.unload();

        Log.info("Migrating resource content");

        URI targetUri = URI.createFileURI(targetFile.getAbsolutePath());
        Resource targetResource = resourceSet.createResource(targetUri);

        targetResource.getContents().add(targetContent);

        Log.info("Saving resource to: {0}", targetResource.getURI());

        Map<String, Object> saveOpts = new HashMap<>();
        saveOpts.put(XMIResource.OPTION_ZIP, true);
        targetResource.save(saveOpts);

        targetResource.unload();

        return targetFile;
    }

    //region EMF migration

    /**
     * Creates a new pre-configured {@link ResourceSet} able to handle registered extensions.
     *
     * @return a new {@link ResourceSet}
     */
    private static ResourceSet loadResourceSet() {
        org.eclipse.gmt.modisco.java.emf.impl.JavaPackageImpl.init();

        ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(XMI, new XMIResourceFactoryImpl());
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(ZXMI, new XMIResourceFactoryImpl());

        return resourceSet;
    }

    /**
     * Adapts the given {@code rootObject} in a particular implementation, specified by the {@code targetPackage}.
     *
     * @param rootObject    the root {@link EObject} to adapt
     * @param targetPackage the {@link EPackage}
     *
     * @return the adapted {@code rootObject}
     */
    private static EObject migrate(EObject rootObject, EPackage targetPackage) {
        Map<EObject, EObject> correspondences = new HashMap<>();
        EObject adaptedObject = getCorrespondingEObject(correspondences, rootObject, targetPackage);
        copy(correspondences, rootObject, adaptedObject);

        Iterable<EObject> allContents = () -> EcoreUtil.getAllContents(rootObject, true);

        for (EObject sourceEObject : allContents) {
            EObject targetEObject = getCorrespondingEObject(correspondences, sourceEObject, targetPackage);
            copy(correspondences, sourceEObject, targetEObject);
        }

        return adaptedObject;
    }

    /**
     * Copies the {@code sourceObject} to the {@code targetObject}, by using the {@code correspondences} {@link Map}.
     *
     * @param correspondences the {@link Map} holding the link between the original {@link EObject} and its adaptation
     * @param sourceObject    the source {@link EObject}
     * @param targetObject    the corresponding {@link EObject}
     *
     * @see #getCorrespondingEObject(Map, EObject, EPackage)
     */
    private static void copy(Map<EObject, EObject> correspondences, EObject sourceObject, EObject targetObject) {
        for (EStructuralFeature sourceFeature : sourceObject.eClass().getEAllStructuralFeatures()) {
            if (sourceObject.eIsSet(sourceFeature)) {
                EStructuralFeature targetFeature = targetObject.eClass().getEStructuralFeature(sourceFeature.getName());
                if (sourceFeature instanceof EAttribute) {
                    targetObject.eSet(targetFeature, sourceObject.eGet(sourceFeature));
                }
                else { // EReference
                    if (!sourceFeature.isMany()) {
                        targetObject.eSet(targetFeature, getCorrespondingEObject(correspondences, (EObject) sourceObject.eGet(targetFeature), targetObject.eClass().getEPackage()));
                    }
                    else {
                        List<EObject> targetList = new BasicEList<>();

                        @SuppressWarnings({"unchecked"})
                        Iterable<EObject> sourceList = (Iterable<EObject>) sourceObject.eGet(sourceFeature);
                        for (EObject aSourceList : sourceList) {
                            targetList.add(getCorrespondingEObject(correspondences, aSourceList, targetObject.eClass().getEPackage()));
                        }
                        targetObject.eSet(targetFeature, targetList);
                    }
                }
            }
        }
    }

    /**
     * Adapts the given {@code sourceObject} in a particular implementation, specified by the {@code targetPackage}, and
     * stores the correspondence in the given {@code correspondences} {@link Map}.
     *
     * @param correspondences the {@link Map} where to store the link between the original {@link EObject} and its
     *                        adaptation
     * @param sourceObject    the {@link EObject} to adapt
     * @param targetPackage   the {@link EPackage} used to retrieve the corresponding {@link EObject}
     *
     * @return the corresponding {@link EObject}
     */
    private static EObject getCorrespondingEObject(Map<EObject, EObject> correspondences, EObject sourceObject, EPackage targetPackage) {
        return correspondences.computeIfAbsent(sourceObject, o -> {
            EClass eClass = sourceObject.eClass();
            EClass targetClass = (EClass) targetPackage.getEClassifier(eClass.getName());
            return EcoreUtil.create(targetClass);
        });
    }

    //endregion

    //region ZIP extraction

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

            try (ZipInputStream inputStream = new ZipInputStream(ResourceHelper.class.getResourceAsStream("/" + ZIP_FILENAME))) {
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
            properties.load(ResourceHelper.class.getResourceAsStream("/resources.properties"));
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
        try (ZipInputStream inputStream = new ZipInputStream(ResourceHelper.class.getResourceAsStream("/" + ZIP_FILENAME))) {
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
     * @param input     the input stream of the ZIP file
     * @param entry     the entry in the ZIP file
     * @param outputDir the directory where to extract the file
     *
     * @return the extracted file
     *
     * @throws IOException if an I/O error occurs during the extraction
     */
    private static File extractEntryFromZip(ZipInputStream input, ZipEntry entry, Path outputDir) throws IOException {
        File outputFile = outputDir.resolve(new File(entry.getName()).getName()).toFile();
        if (outputFile.exists()) {
            Log.info("Already extracted resource: {0}", outputFile);
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

    //endregion

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
