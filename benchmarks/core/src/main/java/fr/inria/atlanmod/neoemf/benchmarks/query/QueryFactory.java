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
import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.MethodDeclaration;
import org.eclipse.gmt.modisco.java.Model;
import org.eclipse.gmt.modisco.java.NamedElement;
import org.eclipse.gmt.modisco.java.Type;
import org.eclipse.gmt.modisco.java.TypeAccess;

import java.util.Collection;
import java.util.Map;

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
    public static Query<Collection<Type>> getOrphanNonPrimitiveTypes() {
        return new GetOrphanNonPrimitiveTypes();
    }

    /**
     * @return a new query
     */
    @Nonnull
    public static Query<Map<String, Iterable<NamedElement>>> getClassFields() {
        return new GetClassFields();
    }

    /**
     * @return a new query
     */
    @Nonnull
    public static Query<Map<String, Iterable<TypeAccess>>> getThrownExceptionsPerPackage() {
        return new GetThrownExceptionsPerPackage();
    }

    /**
     * @return a new query
     */
    @Nonnull
    public static Query<Collection<ClassDeclaration>> grabats() {
        return new Grabats();
    }

    /**
     * @return a new query
     */
    @Nonnull
    public static Query<Collection<MethodDeclaration>> getInvisibleMethods() {
        return new GetInvisibleMethods();
    }

    /**
     * @return a new query
     */
    @Nonnull
    public static Query<Collection<MethodDeclaration>> getUnusedMethodsWithList() {
        return new GetUnusedMethods.WithList();
    }

    /**
     * @return a new query
     */
    @Nonnull
    public static Query<Collection<MethodDeclaration>> getUnusedMethodsWithLoop() {
        return new GetUnusedMethods.WithLoop();
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
    public static Query<Long> renameAllMethods(String name) {
        return new RenameAllMethods(name);
    }

    // endregion
}
