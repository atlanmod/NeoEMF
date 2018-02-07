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
import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.Package;
import org.eclipse.gmt.modisco.java.TypeAccess;
import org.eclipse.gmt.modisco.java.emf.meta.JavaPackage;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 *
 */
@ParametersAreNonnullByDefault
class CountThrownExceptionsPerPackage extends CountThrownExceptions {

    @Override
    public Integer apply(Resource resource) {
        Map<String, Iterable<TypeAccess>> result = new HashMap<>();

        Iterable<Package> packages = allInstancesOf(resource, JavaPackage.eINSTANCE.getPackage());

        for (Package pkg : packages) {
            Set<TypeAccess> thrownExceptions = new HashSet<>();
            for (AbstractTypeDeclaration abstractType : pkg.getOwnedElements()) {
                if (ClassDeclaration.class.isInstance(abstractType)) {
                    ClassDeclaration type = ClassDeclaration.class.cast(abstractType);
                    appendThrownExceptions(type, thrownExceptions);
                }
            }
            result.put(pkg.getName(), thrownExceptions);
        }

        return result.size();
    }
}
