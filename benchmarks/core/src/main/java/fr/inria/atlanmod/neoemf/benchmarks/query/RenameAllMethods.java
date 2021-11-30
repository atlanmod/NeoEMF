/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.benchmarks.query;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmt.modisco.java.MethodDeclaration;
import org.eclipse.gmt.modisco.java.emf.meta.JavaPackage;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static org.atlanmod.commons.Guards.checkNotNull;

/**
 * A {@link Query} that renames all methods.
 */
@ParametersAreNonnullByDefault
class RenameAllMethods extends AbstractQuery<Long> {

    /**
     * The new name of all methods.
     */
    @Nonnull
    private final String name;

    /**
     * Constructs a new {@code RenameAllMethods}.
     *
     * @param name the new name of all methods
     */
    public RenameAllMethods(String name) {
        checkNotNull(name, "name");

        this.name = name;
    }

    @Nonnull
    @Override
    public Long executeOn(Resource resource) {
        long count = 0;

        Iterable<MethodDeclaration> methodDeclarations = allInstancesOf(resource, JavaPackage.eINSTANCE.getMethodDeclaration());

        for (MethodDeclaration method : methodDeclarations) {
            method.setName(name);
            count++;
        }

        return count;
    }
}
