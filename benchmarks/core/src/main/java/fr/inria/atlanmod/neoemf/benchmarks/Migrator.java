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

import com.google.common.io.Files;

import fr.inria.atlanmod.neoemf.benchmarks.util.BenchmarkUtil;

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

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class Migrator {

    private static final Logger LOG = LogManager.getLogger();

    private static Migrator INSTANCE;

    private Migrator() {
    }

    public static Migrator getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Migrator();
        }
        return INSTANCE;
    }

    /**
     * Migrates all XMI files present in {@code resources.zip} and returns a list of newly created files.
     */
    public Iterable<File> migrateAll(String destExtension, Class<?> destClass) {
        List<File> files = new ArrayList<>();
        try {
            URL zipUrl = Migrator.class.getResource("/resources.zip");
            String rootUrl = "jar:" + zipUrl + "!/";

            ZipFile zipFile = new ZipFile(new File(zipUrl.toURI()));
            zipFile.stream()
                    .filter(e -> !e.isDirectory() && e.getName().endsWith(".xmi"))
                    .map(ZipEntry::getName)
                    .forEach(e -> {
                        File f = migrate(rootUrl + e, destExtension, destClass);
                        if (f != null && f.exists()) {
                            files.add(f);
                        }
                    });
        }
        catch (IOException | URISyntaxException e) {
            LOG.error(e);
        }
        return files;
    }

    /**
     * Migrates a XMI file and returns the newly created file.
     */
    public File migrate(String in, String destExtension, Class<?> destClass) {
        File destFile = new File(getDestFile(in, destExtension));

        if (destFile.exists()) {
            return destFile;
        }

        try {
            URI srcUri = URI.createURI(in);
            URI destUri = URI.createFileURI(destFile.getAbsolutePath());

            // Default source EPackage
            org.eclipse.gmt.modisco.java.emf.impl.JavaPackageImpl.init();

            // Destination EPackage
            EPackage destEPackage = (EPackage) destClass.getMethod("init").invoke(null);

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

            destFile.deleteOnExit();

            sourceResource.unload();
            targetResource.unload();
        }
        catch (Exception e) {
            LOG.error(e);
            return null;
        }
        return destFile;
    }

    private EObject migrate(EObject eObject, EPackage targetEPackage) {
        Map<EObject, EObject> correspondencesMap = new HashMap<>();
        EObject returnEObject = getCorrespondingEObject(correspondencesMap, eObject, targetEPackage);
        copy(correspondencesMap, eObject, returnEObject);
        for (Iterator<EObject> iterator = EcoreUtil.getAllContents(eObject, true); iterator.hasNext(); ) {
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
        if (targetEObject == null) {
            EClass eClass = eObject.eClass();
            EClass targetClass = (EClass) ePackage.getEClassifier(eClass.getName());
            targetEObject = EcoreUtil.create(targetClass);
            correspondencesMap.put(eObject, targetEObject);
        }
        return targetEObject;
    }

    private String getDestFile(String in, String destExtension) {
        return BenchmarkUtil.getBaseDirectory().resolve(Files.getNameWithoutExtension(in) + "." + destExtension + ".zxmi").toString();
    }
}
