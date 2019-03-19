/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.benchmarks.query;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import java.util.Iterator;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link Query} that counts the number of elements in a {@link Resource} by using {@link Resource#getAllContents()}.
 */
@ParametersAreNonnullByDefault
class CountAllElements extends AbstractQuery<Long> {

    @Nonnull
    @Override
    public Long executeOn(Resource resource) {
        long count = 0L;

        for (Iterator<EObject> iterator = resource.getAllContents(); iterator.hasNext(); iterator.next()) {
            count++;
        }

        return count;
    }
}
