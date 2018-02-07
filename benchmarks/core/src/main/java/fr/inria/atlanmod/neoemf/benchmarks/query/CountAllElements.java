/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.benchmarks.query;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A query that counts the number of elements in a {@link Resource} by using {@link Resource#getAllContents()}.
 */
@ParametersAreNonnullByDefault
class CountAllElements extends AbstractQuery<Long> {

    @Override
    public Long apply(Resource resource) {
        long count = 0L;

        Iterable<EObject> allContents = resource::getAllContents;
        for (EObject o : allContents) {
            count++;
        }

        return count;
    }
}
