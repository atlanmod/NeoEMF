/*
 * Copyright (c) 2013 Atlanmod.
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
import java.util.Collections;
import java.util.Objects;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static java.util.Objects.nonNull;
import static org.eclipse.gmt.modisco.java.VisibilityKind.PRIVATE;
import static org.eclipse.gmt.modisco.java.VisibilityKind.PROTECTED;

/**
 * A {@link Query}
 */
@ParametersAreNonnullByDefault
class GetInvisibleMethods extends AbstractQuery<Collection<MethodDeclaration>> {

    /**
     * All {@link ClassDeclaration} instances of the current resource.
     */
    @Nonnull
    private Iterable<ClassDeclaration> classDeclarations = Collections.emptyList();

    @Nonnull
    @Override
    public Collection<MethodDeclaration> executeOn(Resource resource) {
        Collection<MethodDeclaration> result = createOrderedCollection();

        classDeclarations = allInstancesOf(resource, JavaPackage.eINSTANCE.getClassDeclaration());

        for (ClassDeclaration type : classDeclarations) {
            for (BodyDeclaration body : type.getBodyDeclarations()) {
                if (body instanceof MethodDeclaration) {
                    MethodDeclaration method = (MethodDeclaration) body;

                    if (isInvisible(type, method)) {
                        result.add(method);
                    }
                }
            }
        }

        return result;
    }

    /**
     * Checks that the {@code method} is not visible from the {@code type}.
     *
     * @param type   the type that owns the {@code method}
     * @param method the method to test
     *
     * @return {@code true} if the method is a private method or a non-overridden protected method
     */
    protected boolean isInvisible(ClassDeclaration type, MethodDeclaration method) {
        Modifier modifier = method.getModifier();

        return nonNull(modifier)
                && (modifier.getVisibility() == PRIVATE || modifier.getVisibility() == PROTECTED && hasNoChildTypes(type));

    }

    /**
     * Checks that the {@code superType} is not inherited by another type.
     *
     * @param superType the type to test
     *
     * @return {@code true} if the type is not inherited
     */
    protected boolean hasNoChildTypes(ClassDeclaration superType) {
        for (ClassDeclaration type : classDeclarations) {
            if (Objects.equals(superType, type.getSuperClass().getType())) {
                return false;
            }
        }
        return true;
    }
}
