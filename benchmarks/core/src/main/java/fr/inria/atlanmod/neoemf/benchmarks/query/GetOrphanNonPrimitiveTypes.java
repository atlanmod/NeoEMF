/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.benchmarks.query;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmt.modisco.java.Model;
import org.eclipse.gmt.modisco.java.PrimitiveType;
import org.eclipse.gmt.modisco.java.Type;

import java.util.Collection;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A query that counts the orphan and non-primitive types of a {@link Model}.
 * <p>
 * This is a common query to all both standard and customized methods.
 *
 * @see PrimitiveType
 * @see Model#getOrphanTypes()
 */
@ParametersAreNonnullByDefault
class GetOrphanNonPrimitiveTypes extends AbstractQuery<Collection<Type>> {

    @Nonnull
    @Override
    public Collection<Type> executeOn(Resource resource) {
        Collection<Type> result = createOrderedCollection();

        Model model = Model.class.cast(resource.getContents().get(0));

        for (Type type : model.getOrphanTypes()) {
            if (!PrimitiveType.class.isInstance(type)) {
                result.add(type);
            }
        }

        return result;
    }
}
