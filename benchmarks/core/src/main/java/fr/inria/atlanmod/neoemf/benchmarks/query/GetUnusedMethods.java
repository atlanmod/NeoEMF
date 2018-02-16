/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.benchmarks.query;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmt.modisco.java.AbstractMethodDeclaration;
import org.eclipse.gmt.modisco.java.MethodDeclaration;
import org.eclipse.gmt.modisco.java.MethodInvocation;
import org.eclipse.gmt.modisco.java.Modifier;
import org.eclipse.gmt.modisco.java.emf.meta.JavaPackage;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static java.util.Objects.nonNull;
import static org.eclipse.gmt.modisco.java.VisibilityKind.PRIVATE;

/**
 *
 */
@ParametersAreNonnullByDefault
abstract class GetUnusedMethods extends AbstractQuery<Collection<MethodDeclaration>> {

    @Nonnull
    @Override
    public Collection<MethodDeclaration> executeOn(Resource resource) {
        Collection<MethodDeclaration> result = createOrderedCollection();

        prepareInvokedMethods(resource);

        Iterable<MethodDeclaration> methodDeclarations = allInstancesOf(resource, JavaPackage.eINSTANCE.getMethodDeclaration());

        for (MethodDeclaration method : methodDeclarations) {
            Modifier modifier = method.getModifier();
            if (nonNull(modifier) && modifier.getVisibility() == PRIVATE && !hasBeenInvoked(method)) {
                result.add(method);
            }
        }

        return result;
    }

    /**
     * Prepares the invoked methods.
     *
     * @param resource the current resource
     *
     * @see #hasBeenInvoked(MethodDeclaration)
     */
    protected abstract void prepareInvokedMethods(Resource resource);

    /**
     * Checks that the {@code method} has been invoked.
     *
     * @param method the method to test
     *
     * @return {@code true} if the method has been invoked at least once
     */
    protected abstract boolean hasBeenInvoked(MethodDeclaration method);

    /**
     *
     */
    @ParametersAreNonnullByDefault
    public static class WithList extends GetUnusedMethods {

        /**
         * All invoked methods.
         */
        @Nonnull
        private Collection<AbstractMethodDeclaration> invokedMethods = Collections.emptySet();

        @Override
        protected void prepareInvokedMethods(Resource resource) {
            Iterable<MethodInvocation> methodInvocations = allInstancesOf(resource, JavaPackage.eINSTANCE.getMethodInvocation());

            invokedMethods = createUniqueCollection();
            for (MethodInvocation invocation : methodInvocations) {
                invokedMethods.add(invocation.getMethod());
            }
        }

        @Override
        protected boolean hasBeenInvoked(MethodDeclaration method) {
            return invokedMethods.contains(method);
        }
    }

    /**
     *
     */
    @ParametersAreNonnullByDefault
    public static class WithLoop extends GetUnusedMethods {

        /**
         * All {@link MethodInvocation} instances of the current resource.
         */
        @Nonnull
        private Iterable<MethodInvocation> methodInvocations = Collections.emptyList();

        @Override
        protected void prepareInvokedMethods(Resource resource) {
            methodInvocations = allInstancesOf(resource, JavaPackage.eINSTANCE.getMethodInvocation());
        }

        /**
         * Checks that the {@code method} has been invoked.
         *
         * @param method the method to test
         *
         * @return {@code true} if the method has been invoked at least once
         */
        @Override
        protected boolean hasBeenInvoked(MethodDeclaration method) {
            for (MethodInvocation invocation : methodInvocations) {
                if (Objects.equals(invocation.getMethod(), method)) {
                    return true;
                }
            }
            return false;
        }
    }
}
