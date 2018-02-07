/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.benchmarks.query;

import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.MethodDeclaration;
import org.eclipse.gmt.modisco.java.Statement;
import org.eclipse.gmt.modisco.java.TextElement;
import org.eclipse.gmt.modisco.java.TypeAccess;

import java.util.Collection;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link QueryFactory} that creates instances of {@link Query} related to the {@code ASE 2015} conference.
 */
@ParametersAreNonnullByDefault
public class QueryFactoryASE extends QueryFactory {

    // region Read-Only

    /**
     * @return a new query
     */
    @Nonnull
    public static Query<Collection<ClassDeclaration>> grabatsASE() {
        return new GrabatsASE();
    }

    /**
     * @return a new query
     */
    @Nonnull
    public static Query<Collection<TypeAccess>> getThrownExceptions() {
        return new GetThrownExceptions();
    }

    /**
     * @return a new query
     */
    @Nonnull
    public static Query<Collection<MethodDeclaration>> getInvisibleMethodsSpecific() {
        return new GetInvisibleMethodsSpecific();
    }

    /**
     * @return a new query
     */
    @Nonnull
    public static Query<Collection<TextElement>> getTagComments() {
        return new GetTagComments();
    }

    /**
     * @return a new query
     */
    @Nonnull
    public static Query<Collection<Statement>> getBranchStatements() {
        return new GetBranchStatements();
    }

    // endregion
}
