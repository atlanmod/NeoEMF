/*
 * Copyright (c) 2008-2016 Eike Stepper (Berlin, Germany) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Eike Stepper - initial API and implementation
 *     Atlanmod INRIA LINA Mines Nantes - Adapted to NeoEMF models
 */

package fr.inria.atlanmod.neoemf.eclipse.ui.migrator;

import fr.inria.atlanmod.neoemf.core.DefaultPersistentEObject;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.codegen.ecore.genmodel.GenDelegationKind;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.WrappedException;

import java.util.Objects;

public abstract class NeoImporterUtil {

    private static final String ROOT_EXTENDS_CLASS = DefaultPersistentEObject.class.getName();
    private static final String NEOEMF_MODEL_PLUGIN_NAME = "fr.inria.atlanmod.neoemf.core";
    private static final String ROOT_EXTENDS_INTERFACE = PersistentEObject.class.getName();

    private static final String PLUGIN_VARIABLE_NEOEMF = "NEOEMF=" + NEOEMF_MODEL_PLUGIN_NAME;

    private NeoImporterUtil() {
    }

    public static String adjustGenModel(GenModel genModel) {
        return adjustGenModel(genModel, GenDelegationKind.REFLECTIVE_LITERAL);
    }

    private static String adjustGenModel(GenModel genModel, GenDelegationKind featureDelegation) {
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

        EList<String> pluginVariables = genModel.getModelPluginVariables();
        if (!pluginVariables.contains(PLUGIN_VARIABLE_NEOEMF)) {
            pluginVariables.add(PLUGIN_VARIABLE_NEOEMF);
            builder.append("Added Model Plugin Variables = ").append(PLUGIN_VARIABLE_NEOEMF).append('\n');
        }

        IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
        IFolder modelFolder = root.getFolder(new Path(genModel.getModelDirectory()));
        IProject modelProject = modelFolder.getProject();
        if (!modelProject.exists()) {
            try {
                modelProject.create(new NullProgressMonitor());
                builder.append("Created target model project").append('\n');
            }
            catch (CoreException ex) {
                throw new WrappedException(ex);
            }
        }

        if (!modelProject.isOpen()) {
            try {
                modelProject.open(new NullProgressMonitor());
                builder.append("Opened target model project").append('\n');
            }
            catch (CoreException ex) {
                throw new WrappedException(ex);
            }
        }

        return builder.length() == 0 ? null : builder.toString();
    }
}
