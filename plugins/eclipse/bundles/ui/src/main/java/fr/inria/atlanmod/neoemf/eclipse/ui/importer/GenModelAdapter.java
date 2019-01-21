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

import java.util.Collection;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * A {@link GenModel} adapter.
 */
public class GenModelAdapter {

    /**
     * The base interface of {@code EObject} to use with NeoEMF.
     */
    private static final String ROOT_EXTENDS_INTERFACE = PersistentEObject.class.getName();

    /**
     * The base class of {@code EObject} to use with NeoEMF.
     */
    private static final String ROOT_EXTENDS_CLASS = DefaultPersistentEObject.class.getName();

    /**
     * ???
     */
    private static final String PLUGIN_VARIABLE_NEOEMF = "NEOEMF=" + NeoUIPlugin.CORE_ID;

    /**
     * The model to adapt.
     */
    private final GenModel model;

    /**
     * Constructs a new {@code GenModelAdapter} for the specified {@code model}.
     *
     * @param model the model to adapt
     */
    public GenModelAdapter(GenModel model) {
        this.model = model;
    }

    /**
     * Adjusts the model to be compatible with NeoEMF.
     *
     * @return a string representing the different steps of the adjustment
     */
    public String adapt() {
        return adapt(GenDelegationKind.REFLECTIVE_LITERAL);
    }

    /**
     * Adjusts the {@code model} to be compatible with NeoEMF.
     *
     * @param featureDelegation the kind of feature delegation to use
     *
     * @return a string representing the different steps of the adjustment
     */
    private String adapt(GenDelegationKind featureDelegation) {
        final StringBuilder sb = applyModifications(featureDelegation);

        final IProject project = ResourcesPlugin.getWorkspace().getRoot().getFolder(new Path(model.getModelDirectory())).getProject();

        try {
            if (!project.exists()) {
                project.create(new NullProgressMonitor());
                sb.append("Created target model project").append('\n');
            }

            if (!project.isOpen()) {
                project.open(new NullProgressMonitor());
                sb.append("Opened target model project").append('\n');
            }
        }
        catch (CoreException ex) {
            throw new WrappedException(ex);
        }

        return sb.length() == 0 ? null : sb.toString();
    }

    /**
     * Applies all modifications on the {@code model}.
     *
     * @param featureDelegation the kind of feature delegation to use
     *
     * @return a string representing the different modifications
     */
    private StringBuilder applyModifications(GenDelegationKind featureDelegation) {
        StringBuilder sb = new StringBuilder();

        setIfDifferent("Importer ID", NeoModelImporter.IMPORTER_ID, GenModel::getImporterID, GenModel::setImporterID, sb);
        setIfDifferent("Feature Delegation", featureDelegation, GenModel::getFeatureDelegation, GenModel::setFeatureDelegation, sb);
        setIfDifferent("Root Extends Class", ROOT_EXTENDS_CLASS, GenModel::getRootExtendsClass, GenModel::setRootExtendsClass, sb);
        setIfDifferent("Root Extends Interface", ROOT_EXTENDS_INTERFACE, GenModel::getRootExtendsInterface, GenModel::setRootExtendsInterface, sb);

        addIfNotPresent("Model Plugin Variables", PLUGIN_VARIABLE_NEOEMF, GenModel::getModelPluginVariables, sb);

        return sb;
    }

    /**
     * Defines the {@code expected} value if {@code actual != expected}.
     *
     * @param name            the name of the field
     * @param expected        the expected value
     * @param getActualFunc   the function to retrieve the actual value
     * @param setExpectedFunc the function to set the expected value
     * @param sb              the string builder where to append modifications
     * @param <T>             the type of the value
     */
    private <T> void setIfDifferent(String name, T expected, Function<GenModel, T> getActualFunc, BiConsumer<GenModel, T> setExpectedFunc, StringBuilder sb) {
        if (!Objects.equals(expected, getActualFunc.apply(model))) {
            setExpectedFunc.accept(model, expected);
            sb.append("Set '").append(name).append("' = ").append(expected).append('\n');
        }
    }

    /**
     * Adds the {@code expected} value if {@code !actuals.contains(expected)}.
     *
     * @param name           the name of the field
     * @param expected       the expected value
     * @param getActualsFunc the function to retrieve the actual values
     * @param sb             the string builder where to append modifications
     * @param <T>            the type of the value
     */
    private <T> void addIfNotPresent(String name, T expected, Function<GenModel, Collection<T>> getActualsFunc, StringBuilder sb) {
        Collection<T> actuals = getActualsFunc.apply(model);
        if (!actuals.contains(expected)) {
            actuals.add(expected);
            sb.append("Added '").append(name).append("' = ").append(expected).append('\n');
        }
    }
}
