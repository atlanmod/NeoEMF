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
import org.eclipse.gmt.modisco.java.FieldDeclaration;
import org.eclipse.gmt.modisco.java.NamedElement;
import org.eclipse.gmt.modisco.java.emf.meta.JavaPackage;

import java.util.Collection;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * ???
 */
@ParametersAreNonnullByDefault
class GetClassFields extends AbstractQuery<Map<String, Iterable<NamedElement>>> {

    @Nonnull
    @Override
    public Map<String, Iterable<NamedElement>> executeOn(Resource resource) {
        Map<String, Iterable<NamedElement>> result = createMultiMap();

        Iterable<ClassDeclaration> classDeclarations = allInstancesOf(resource, JavaPackage.eINSTANCE.getClassDeclaration());

        for (ClassDeclaration type : classDeclarations) {
            result.put(type.getName(), separateFields(type.getBodyDeclarations()));
        }

        return result;
    }

    /**
     * @param bodyDeclarations
     *
     * @return
     */
    @Nonnull
    protected Iterable<NamedElement> separateFields(Iterable<BodyDeclaration> bodyDeclarations) {
        Collection<NamedElement> fields = createOrderedCollection();

        for (BodyDeclaration body : bodyDeclarations) {
            if (FieldDeclaration.class.isInstance(body)) {
                FieldDeclaration field = FieldDeclaration.class.cast(body);
                if (field.getFragments().isEmpty()) {
                    fields.add(field);
                }
                else {
                    fields.addAll(field.getFragments());
                }
            }
        }

        return fields;
    }
}
