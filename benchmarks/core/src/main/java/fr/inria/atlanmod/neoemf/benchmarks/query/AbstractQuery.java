/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.benchmarks.query;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An abstract {@link Query}.
 *
 * @param <T> the result type of method {@code apply}
 */
@ParametersAreNonnullByDefault
public abstract class AbstractQuery<T> implements Query<T> {

    /**
     * Retrieves the root object of the {@code resource}.
     *
     * @param resource the resource
     * @param <U>      the type of the expected root object
     *
     * @return the root object
     */
    @Nonnull
    @SuppressWarnings("unchecked")
    protected <U extends EObject> U getRoot(Resource resource) {
        return (U) resource.getContents().get(0);
    }

    /**
     * Retrieves all elements within the {@code resource} that is instance of the given {@code type}.
     *
     * @param resource the resource to explore
     * @param type     the instance of the expected objects
     * @param <U>      the type of the expected objects
     *
     * @return an iterable
     */
    @Nonnull
    @SuppressWarnings("unchecked")
    protected <U extends EObject> Iterable<U> allInstancesOf(Resource resource, EClass type) {
        List<U> result = new ArrayList<>();

        Iterable<EObject> allContents = resource::getAllContents;
        for (EObject e : allContents) {
            if (type.isInstance(e)) {
                result.add((U) e);
            }
        }

        return Collections.unmodifiableList(result);
    }
}
