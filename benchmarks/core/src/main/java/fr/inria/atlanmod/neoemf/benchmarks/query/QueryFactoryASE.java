/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.benchmarks.query;

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
    public static Query<Integer> grabatsASE() {
        return new GrabatsASE();
    }

    /**
     * @return a new query
     */
    @Nonnull
    public static Query<Integer> countThrownExceptions() {
        return new CountThrownExceptions();
    }

    /**
     * @return a new query
     */
    @Nonnull
    public static Query<Integer> countInvisibleMethodsSpecific() {
        return new CountInvisibleMethodsSpecific();
    }

    /**
     * @return a new query
     */
    @Nonnull
    public static Query<Integer> countTagComments() {
        return new CountTagComments();
    }

    /**
     * @return a new query
     */
    @Nonnull
    public static Query<Integer> countBranchStatements() {
        return new CountBranchStatements();
    }

    // endregion
}
