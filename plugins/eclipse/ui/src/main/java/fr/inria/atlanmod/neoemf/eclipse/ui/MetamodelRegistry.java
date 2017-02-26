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

package fr.inria.atlanmod.neoemf.eclipse.ui;

import fr.inria.atlanmod.neoemf.util.logging.Log;

import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class MetamodelRegistry {

    private static final String STORE_KEY = "metamodels";
    private static final String STORE_DELIMITER = ";";

    private final HashMap<String, List<EPackage>> managedMetamodels;

    private MetamodelRegistry() {
        this.managedMetamodels = new HashMap<>();

        registerMetamodels();
        initChangeListener();
    }

    public static MetamodelRegistry getInstance() {
        return Holder.INSTANCE;
    }

    private static void setDataTypesInstanceClasses(Resource metamodel) {
        Iterable<EObject> allContents = metamodel::getAllContents;
        for (EObject eObject : allContents) {
            if (eObject instanceof EDataType) {
                EDataType eDataType = (EDataType) eObject;
                String instanceClass = "";

                if (Objects.equals(eDataType.getName(), String.class.getSimpleName())) {
                    instanceClass = String.class.getName();
                }
                else if (Objects.equals(eDataType.getName(), Boolean.class.getSimpleName())) {
                    instanceClass = Boolean.class.getName();
                }
                else if (Objects.equals(eDataType.getName(), Integer.class.getSimpleName())) {
                    instanceClass = Integer.class.getName();
                }
                else if (Objects.equals(eDataType.getName(), Float.class.getSimpleName())) {
                    instanceClass = Float.class.getName();
                }
                else if (Objects.equals(eDataType.getName(), Double.class.getSimpleName())) {
                    instanceClass = Double.class.getName();
                }

                if (!instanceClass.trim().isEmpty()) {
                    eDataType.setInstanceClassName(instanceClass);
                }
            }
        }
    }

    private void initChangeListener() {
        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        IResourceChangeListener listener = event -> {

            if (event.getType() != IResourceChangeEvent.POST_CHANGE) {
                return;
            }

            IResourceDelta rootDelta = event.getDelta();

            final ArrayList<IResourceDelta> changed = new ArrayList<>();
            IResourceDeltaVisitor visitor = delta -> {
                changed.add(delta);
                return true;
            };

            try {
                rootDelta.accept(visitor);
            }
            catch (CoreException e) {
                Log.error(e);
            }

            if (changed.isEmpty()) {
                return;
            }

            for (IResourceDelta delta : changed) {
                String metamodel = delta.getResource().getFullPath().toOSString();
                List<String> metamodels = getMetamodels();
                if (metamodels.contains(metamodel)) {
                    if (delta.getKind() == IResourceDelta.REMOVED) {
                        removeMetamodel(metamodel);
                        if (delta.getMovedToPath() != null) {
                            try {
                                addMetamodel(delta.getMovedToPath().toOSString());
                            }
                            catch (Exception e) {
                                Log.error(e);
                            }
                        }
                    }
                    else {
                        try {
                            registerMetamodel(metamodel);
                        }
                        catch (Exception e) {
                            Log.error(e);
                        }
                    }
                }
            }
        };
        workspace.addResourceChangeListener(listener);
    }

    public void addMetamodel(String fileName) throws Exception {
        registerMetamodel(fileName);
        List<String> metamodels = getMetamodels();
        if (!metamodels.contains(fileName)) {
            metamodels.add(fileName);
            setMetamodels(metamodels);
        }
    }

    public void removeMetamodel(String fileName) {
        List<EPackage> ePackages = managedMetamodels.get(fileName);

        if (ePackages == null) {
            return;
        }

        for (EPackage ePackage : ePackages) {
            EPackage.Registry.INSTANCE.remove(ePackage.getNsURI());
        }

        managedMetamodels.remove(fileName);
        List<String> metamodels = getMetamodels();
        metamodels.remove(fileName);
        setMetamodels(metamodels);
    }

    private List<String> getMetamodels() {
        List<String> metamodels = new ArrayList<>();
        String value = NeoUIPlugin.getDefault().getPreferenceStore().getString(STORE_KEY);
        StringTokenizer st = new StringTokenizer(value, STORE_DELIMITER);
        while (st.hasMoreTokens()) {
            metamodels.add(st.nextToken());
        }
        return metamodels;
    }

    private void setMetamodels(List<String> metamodels) {
        StringBuilder sb = new StringBuilder();
        Iterator<String> iterator = metamodels.listIterator();
        while (iterator.hasNext()) {
            sb.append(iterator.next());
            if (iterator.hasNext()) {
                sb.append(STORE_DELIMITER);
            }
        }
        NeoUIPlugin.getDefault().getPreferenceStore().setValue(STORE_KEY, sb.toString());
    }

    private void registerMetamodels() {
        for (String metamodel : getMetamodels()) {
            try {
                registerMetamodel(metamodel);
            }
            catch (Exception e) {
                Log.error(e);
            }
        }
    }

    private void registerMetamodel(String fileName) throws Exception {
        List<EPackage> packages = registerMetamodel(URI.createPlatformResourceURI(fileName, true), EPackage.Registry.INSTANCE);
        managedMetamodels.put(fileName, packages);
    }

    private List<EPackage> registerMetamodel(URI uri, EPackage.Registry registry) throws Exception {
        List<EPackage> ePackages = new ArrayList<>();

        final Map<String, Object> extensionToFactoryMap = Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap();
        if (!extensionToFactoryMap.containsKey("*")) {
            extensionToFactoryMap.put("*", new XMIResourceFactoryImpl());
        }

        ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getPackageRegistry().put(EcorePackage.eINSTANCE.getNsURI(), EcorePackage.eINSTANCE);

        Resource metamodel = resourceSet.createResource(uri);
        metamodel.load(Collections.emptyMap());
        setDataTypesInstanceClasses(metamodel);

        Iterable<EObject> allContents = metamodel::getAllContents;
        for (EObject obj : allContents) {
            if (obj instanceof EPackage) {
                EPackage pkg = (EPackage) obj;

                if (isNull(pkg.getNsURI()) || pkg.getNsURI().trim().isEmpty()) {
                    if (isNull(pkg.getESuperPackage())) {
                        pkg.setNsURI(pkg.getName());
                    }
                    else {
                        pkg.setNsURI(pkg.getESuperPackage().getNsURI() + "/" + pkg.getName());
                    }
                }

                if ((isNull(pkg.getNsPrefix()) || pkg.getNsPrefix().trim().isEmpty()) && nonNull(pkg.getESuperPackage())) {
                    if (nonNull(pkg.getESuperPackage().getNsPrefix())) {
                        pkg.setNsPrefix(pkg.getESuperPackage().getNsPrefix() + "." + pkg.getName());
                    }
                    else {
                        pkg.setNsPrefix(pkg.getName());
                    }
                }

                if (isNull(pkg.getNsPrefix())) {
                    pkg.setNsPrefix(pkg.getName());
                }

                registry.put(pkg.getNsURI(), pkg);
                metamodel.setURI(URI.createURI(pkg.getNsURI()));
                ePackages.add(pkg);
            }
        }

        return ePackages;
    }

    private static final class Holder {

        private static final MetamodelRegistry INSTANCE = new MetamodelRegistry();
    }
}
