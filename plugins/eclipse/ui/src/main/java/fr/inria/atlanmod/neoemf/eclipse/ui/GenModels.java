/*
 * Copyright (c) 2008-2017 Eike Stepper (Berlin, Germany) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Eike Stepper - initial API and implementation
 *     Atlanmod INRIA LINA Mines Nantes - Adapted to NeoEMF models
 */

package fr.inria.atlanmod.neoemf.eclipse.ui;

import fr.inria.atlanmod.neoemf.core.DefaultPersistentEObject;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;

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

    private static final String ROOT_EXTENDS_CLASS = DefaultPersistentEObject.class.getName();

    private static final String NEOEMF_MODEL_PLUGIN_NAME = "fr.inria.atlanmod.neoemf.core";

    private static final String ROOT_EXTENDS_INTERFACE = PersistentEObject.class.getName();

    private static final String PLUGIN_VARIABLE_NEOEMF = "NEOEMF=" + NEOEMF_MODEL_PLUGIN_NAME;

    /**
     * This class should not be instantiated.
     *
     * @throws IllegalStateException every time
     */
    private GenModels() {
        throw new IllegalStateException("This class should not be instantiated");
    }

    public static String adjust(GenModel genModel) {
        return adjust(genModel, GenDelegationKind.REFLECTIVE_LITERAL);
    }

    private static String adjust(GenModel genModel, GenDelegationKind featureDelegation) {
        StringBuilder builder = new StringBuilder();

        if (genModel.getFeatureDelegation() != featureDelegation) {
            genModel.setFeatureDelegation(featureDelegation);
            builder.append("Set Feature Delegation = ").append(featureDelegation).append('\n');
        }

        if (!Objects.equals(ROOT_EXTENDS_CLASS, genModel.getRootExtendsClass())) {
            genModel.setRootExtendsClass(ROOT_EXTENDS_CLASS);
            builder.append("Set Root Extends Class = ").append(ROOT_EXTENDS_CLASS).append('\n');
        }

        if (!Objects.equals(ROOT_EXTENDS_INTERFACE, genModel.getRootExtendsInterface())) {
            genModel.setRootExtendsInterface(ROOT_EXTENDS_INTERFACE);
            builder.append("Set Root Extends Interface = ").append(ROOT_EXTENDS_INTERFACE).append('\n');
        }

        List<String> pluginVariables = genModel.getModelPluginVariables();
        if (!pluginVariables.contains(PLUGIN_VARIABLE_NEOEMF)) {
            pluginVariables.add(PLUGIN_VARIABLE_NEOEMF);
            builder.append("Added Model Plugin Variables = ").append(PLUGIN_VARIABLE_NEOEMF).append('\n');
        }

        IProject project = ResourcesPlugin.getWorkspace()
                .getRoot()
                .getFolder(new Path(genModel.getModelDirectory()))
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

        return builder.length() == 0 ? null : builder.toString();
    }
}
