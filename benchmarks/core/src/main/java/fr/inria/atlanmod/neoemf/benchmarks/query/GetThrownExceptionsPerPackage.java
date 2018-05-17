/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.benchmarks.query;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmt.modisco.java.AbstractTypeDeclaration;
import org.eclipse.gmt.modisco.java.BodyDeclaration;
import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.MethodDeclaration;
import org.eclipse.gmt.modisco.java.Package;
import org.eclipse.gmt.modisco.java.TypeAccess;
import org.eclipse.gmt.modisco.java.emf.meta.JavaPackage;

import java.util.Collection;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link Query}
 */
@ParametersAreNonnullByDefault
class GetThrownExceptionsPerPackage extends AbstractQuery<Map<String, Iterable<TypeAccess>>> {

    @Nonnull
    @Override
    public Map<String, Iterable<TypeAccess>> executeOn(Resource resource) {
        Map<String, Iterable<TypeAccess>> result = createMultiMap();

        Iterable<Package> packages = allInstancesOf(resource, JavaPackage.eINSTANCE.getPackage());

        for (Package pkg : packages) {
            Collection<TypeAccess> thrownExceptions = createUniqueCollection();
            for (AbstractTypeDeclaration abstractType : pkg.getOwnedElements()) {
                if (ClassDeclaration.class.isInstance(abstractType)) {
                    ClassDeclaration type = ClassDeclaration.class.cast(abstractType);
                    appendThrownExceptions(type, thrownExceptions);
                }
            }
            result.put(pkg.getName(), thrownExceptions);
        }

        return result;
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
