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

import fr.inria.atlanmod.common.collect.MoreIterables;
import fr.inria.atlanmod.common.log.Log;

import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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
        MoreIterables.stream(metamodel::getAllContents)
                .filter(EDataType.class::isInstance)
                .map(EDataType.class::cast)
                .forEach(type -> {
                    Optional<String> inst = Optional.empty();

                    if (Objects.equals(type.getName(), String.class.getSimpleName())) {
                        inst = Optional.of(String.class.getName());
                    }
                    else if (Objects.equals(type.getName(), Boolean.class.getSimpleName())) {
                        inst = Optional.of(Boolean.class.getName());
                    }
                    else if (Objects.equals(type.getName(), Integer.class.getSimpleName())) {
                        inst = Optional.of(Integer.class.getName());
                    }
                    else if (Objects.equals(type.getName(), Float.class.getSimpleName())) {
                        inst = Optional.of(Float.class.getName());
                    }
                    else if (Objects.equals(type.getName(), Double.class.getSimpleName())) {
                        inst = Optional.of(Double.class.getName());
                    }

                    inst.ifPresent(type::setInstanceClassName);
                });
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
        String value = NeoUIPlugin.getDefault().getPreferenceStore().getString(STORE_KEY);

        return Arrays.stream(value.split(STORE_DELIMITER))
                .collect(Collectors.toList());
    }

    private void setMetamodels(List<String> metamodels) {
        String value = MoreIterables.stream(metamodels::listIterator)
                .collect(Collectors.joining(STORE_DELIMITER));

        NeoUIPlugin.getDefault().getPreferenceStore().setValue(STORE_KEY, value);
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
        final Map<String, Object> extensionToFactoryMap = Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap();
        if (!extensionToFactoryMap.containsKey("*")) {
            extensionToFactoryMap.put("*", new XMIResourceFactoryImpl());
        }

        ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getPackageRegistry().put(EcorePackage.eINSTANCE.getNsURI(), EcorePackage.eINSTANCE);

        Resource metamodel = resourceSet.createResource(uri);
        metamodel.load(Collections.emptyMap());
        setDataTypesInstanceClasses(metamodel);

        return MoreIterables.stream(metamodel::getAllContents)
                .filter(EPackage.class::isInstance)
                .map(EPackage.class::cast)
                .peek(pkg -> {
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
                })
                .peek(pkg -> registry.put(pkg.getNsURI(), pkg))
                .peek(pkg -> metamodel.setURI(URI.createURI(pkg.getNsURI())))
                .collect(Collectors.toList());
    }

    private static final class Holder {

        private static final MetamodelRegistry INSTANCE = new MetamodelRegistry();
    }
}
