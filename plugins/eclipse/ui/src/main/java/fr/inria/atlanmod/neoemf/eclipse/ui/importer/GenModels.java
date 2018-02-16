/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.eclipse.ui.importer;

import fr.inria.atlanmod.neoemf.core.DefaultPersistentEObject;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.eclipse.ui.NeoUIPlugin;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.codegen.ecore.genmodel.GenDelegationKind;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.common.util.WrappedException;

import java.util.List;
import java.util.Objects;

/**
 * Static utility methods related to {@link GenModel}s.
 */
public final class GenModels {

    /**
     * The base interface of {@link org.eclipse.emf.ecore.EObject} to use with NeoEMF.
     */
    private static final String ROOT_EXTENDS_INTERFACE = PersistentEObject.class.getName();

    /**
     * The base class of {@link org.eclipse.emf.ecore.EObject} to use with NeoEMF.
     */
    private static final String ROOT_EXTENDS_CLASS = DefaultPersistentEObject.class.getName();

    /**
     * ???
     */
    private static final String PLUGIN_VARIABLE_NEOEMF = "NEOEMF=" + NeoUIPlugin.CORE_ID;

    /**
     * This class should not be instantiated.
     *
     * @throws IllegalStateException every time
     */
    private GenModels() {
        throw new IllegalStateException("This class should not be instantiated");
    }

    /**
     * Adjusts the {@code model} to be compatible with NeoEMF.
     *
     * @param model the model to adjust
     *
     * @return a string representing the different steps of the adjustment
     */
    public static String adjust(GenModel model) {
        return adjust(model, GenDelegationKind.REFLECTIVE_LITERAL);
    }

    /**
     * Adjusts the {@code model} to be compatible with NeoEMF.
     *
     * @param model             the model to adjust
     * @param featureDelegation the kind of feature delegation to use
     *
     * @return a string representing the different steps of the adjustment
     */
    private static String adjust(GenModel model, GenDelegationKind featureDelegation) {
        StringBuilder builder = new StringBuilder();

        if (model.getFeatureDelegation() != featureDelegation) {
            model.setFeatureDelegation(featureDelegation);
            builder.append("Set Feature Delegation = ").append(featureDelegation).append('\n');
        }

        if (!Objects.equals(ROOT_EXTENDS_CLASS, model.getRootExtendsClass())) {
            model.setRootExtendsClass(ROOT_EXTENDS_CLASS);
            builder.append("Set Root Extends Class = ").append(ROOT_EXTENDS_CLASS).append('\n');
        }

        if (!Objects.equals(ROOT_EXTENDS_INTERFACE, model.getRootExtendsInterface())) {
            model.setRootExtendsInterface(ROOT_EXTENDS_INTERFACE);
            builder.append("Set Root Extends Interface = ").append(ROOT_EXTENDS_INTERFACE).append('\n');
        }

        List<String> pluginVariables = model.getModelPluginVariables();
        if (!pluginVariables.contains(PLUGIN_VARIABLE_NEOEMF)) {
            pluginVariables.add(PLUGIN_VARIABLE_NEOEMF);
            builder.append("Added Model Plugin Variables = ").append(PLUGIN_VARIABLE_NEOEMF).append('\n');
        }

        IProject project = ResourcesPlugin.getWorkspace()
                .getRoot()
                .getFolder(new Path(model.getModelDirectory()))
                .getProject();

        if (!project.exists()) {
            try {
                project.create(new NullProgressMonitor());
                builder.append("Created target model project").append('\n');
            }
            catch (CoreException ex) {
                throw new WrappedException(ex);
            }
        }

        if (!project.isOpen()) {
            try {
                project.open(new NullProgressMonitor());
                builder.append("Opened target model project").append('\n');
            }
            catch (CoreException ex) {
                throw new WrappedException(ex);
            }
        }

        return builder.length() == 0
                ? null
                : builder.toString();
    }
}
