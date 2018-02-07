/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.benchmarks.query;

import fr.inria.atlanmod.commons.Throwables;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmt.modisco.java.Model;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A factory that creates instances of {@link Query}.
 */
@ParametersAreNonnullByDefault
public class QueryFactory {

    protected QueryFactory() {
        throw Throwables.notInstantiableClass(getClass());
    }

    // region Read-Only

    /**
     * Counts the number of elements in a {@link Resource} by using {@link Resource#getAllContents()}.
     *
     * @return a new query
     */
    @Nonnull
    public static Query<Long> countAllElements() {
        return new CountAllElements();
    }

    /**
     * Returns the orphan and non-primitive types of a {@link Model}.
     * This is a common query to all both standard and customized methods.
     *
     * @return a new query
     */
    @Nonnull
    public static Query<Integer> countOrphanNonPrimitiveTypes() {
        return new CountOrphanNonPrimitiveTypes();
    }

    /**
     * @return a new query
     */
    @Nonnull
    public static Query<Integer> countClassFields() {
        return new CountClassFields();
    }

    /**
     * @return a new query
     */
    @Nonnull
    public static Query<Integer> countThrownExceptionsPerPackage() {
        return new CountThrownExceptionsPerPackage();
    }

    /**
     * @return a new query
     */
    @Nonnull
    public static Query<Integer> grabats() {
        return new Grabats();
    }

    /**
     * @return a new query
     */
    @Nonnull
    public static Query<Integer> countInvisibleMethods() {
        return new CountInvisibleMethods();
    }

    /**
     * @return a new query
     */
    @Nonnull
    public static Query<Integer> countUnusedMethodsWithList() {
        return new CountUnusedMethods.WithList();
    }

    /**
     * @return a new query
     */
    @Nonnull
    public static Query<Integer> countUnusedMethodsWithLoop() {
        return new CountUnusedMethods.WithLoop();
    }

    // endregion

    // region Read-Write

    /**
     * Renames all the method names with the given {@code name}.
     *
     * @param name the new name of all methods
     *
     * @return a new query
     */
    @Nonnull
    public static Query<Integer> renameAllMethods(String name) {
        return new RenameAllMethods(name);
    }

    // endregion
}
