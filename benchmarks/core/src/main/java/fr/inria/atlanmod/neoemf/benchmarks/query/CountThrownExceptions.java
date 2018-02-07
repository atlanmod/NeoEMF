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
import org.eclipse.gmt.modisco.java.TypeAccess;
import org.eclipse.gmt.modisco.java.emf.meta.JavaPackage;

import java.util.Collection;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 *
 */
@ParametersAreNonnullByDefault
class CountThrownExceptions extends AbstractQuery<Integer> {

    @Override
    public Integer apply(Resource resource) {
        Collection<TypeAccess> result = createUniqueCollection();

        Iterable<ClassDeclaration> classDeclarations = allInstancesOf(resource, JavaPackage.eINSTANCE.getClassDeclaration());

        for (ClassDeclaration type : classDeclarations) {
            appendThrownExceptions(type, result);
        }

        return result.size();
    }

    /**
     * @param type
     * @param thrownExceptions
     */
    protected void appendThrownExceptions(ClassDeclaration type, Collection<TypeAccess> thrownExceptions) {
        for (BodyDeclaration body : type.getBodyDeclarations()) {
            if (MethodDeclaration.class.isInstance(body)) {
                MethodDeclaration method = MethodDeclaration.class.cast(body);
                thrownExceptions.addAll(method.getThrownExceptions());
            }
        }
    }
}
