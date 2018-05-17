/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.benchmarks.query;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmt.modisco.java.BodyDeclaration;
import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.MethodDeclaration;
import org.eclipse.gmt.modisco.java.Modifier;
import org.eclipse.gmt.modisco.java.emf.meta.JavaPackage;

import java.util.Collection;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static java.util.Objects.nonNull;

/**
 * A {@link Query}
 */
@ParametersAreNonnullByDefault
class Grabats extends AbstractQuery<Collection<ClassDeclaration>> {

    @Nonnull
    @Override
    public Collection<ClassDeclaration> executeOn(Resource resource) {
        Collection<ClassDeclaration> result = createOrderedCollection();

        Iterable<ClassDeclaration> classDeclarations = allInstancesOf(resource, JavaPackage.eINSTANCE.getClassDeclaration());

        for (ClassDeclaration type : classDeclarations) {
            for (BodyDeclaration body : type.getBodyDeclarations()) {
                if (MethodDeclaration.class.isInstance(body)) {
                    MethodDeclaration method = MethodDeclaration.class.cast(body);
                    if (isStaticAndReturns(type, method)) {
                        result.add(type);
                    }
                }
            }
        }

        return result;
    }

    /**
     * Checks that the {@code method} is static and returns an instance of {@code type}.
     *
     * @param type   the type that owns the {@code method}, and the expected type of the {@code method}
     * @param method the method to test
     *
     * @return {@code true} if the method is static and returns an instance of {@code type}
     */
    protected boolean isStaticAndReturns(ClassDeclaration type, MethodDeclaration method) {
        Modifier modifier = method.getModifier();

        return nonNull(modifier)
                && modifier.isStatic()
                && method.getReturnType() == type;
    }
}
