/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.benchmarks.query;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmt.modisco.java.AbstractTypeDeclaration;
import org.eclipse.gmt.modisco.java.BodyDeclaration;
import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.MethodDeclaration;
import org.eclipse.gmt.modisco.java.Model;
import org.eclipse.gmt.modisco.java.Modifier;
import org.eclipse.gmt.modisco.java.Package;
import org.eclipse.gmt.modisco.java.emf.meta.JavaPackage;

import java.util.Collection;

import javax.annotation.ParametersAreNonnullByDefault;

import static java.util.Objects.nonNull;
import static org.eclipse.gmt.modisco.java.VisibilityKind.PRIVATE;
import static org.eclipse.gmt.modisco.java.VisibilityKind.PROTECTED;

@ParametersAreNonnullByDefault
class CountInvisibleMethodsSpecific extends CountInvisibleMethods {

    @Override
    public Integer apply(Resource resource) {
        Collection<MethodDeclaration> result = createOrderedCollection();

        Model model = (Model) resource.getContents().get(0);

        for (Package pkg : model.getOwnedElements()) {
            appendInvisibleMethods(pkg, result);
        }

        return result.size();
    }

    /**
     * @param basePackage
     * @param invisibleMethods
     */
    protected void appendInvisibleMethods(Package basePackage, Collection<MethodDeclaration> invisibleMethods) {
        EClass eClass = JavaPackage.eINSTANCE.getClassDeclaration();

        for (AbstractTypeDeclaration abstractType : basePackage.getOwnedElements()) {
            if (eClass.isInstance(abstractType)) {
                ClassDeclaration type = ClassDeclaration.class.cast(abstractType);
                appendInvisibleMethods(type, invisibleMethods);
            }
        }

        for (Package subPackage : basePackage.getOwnedPackages()) {
            appendInvisibleMethods(subPackage, invisibleMethods);
        }
    }

    /**
     * @param type
     * @param invisibleMethods
     */
    protected void appendInvisibleMethods(ClassDeclaration type, Collection<MethodDeclaration> invisibleMethods) {
        for (BodyDeclaration bd : type.getBodyDeclarations()) {
            if (MethodDeclaration.class.isInstance(bd)) {
                MethodDeclaration md = MethodDeclaration.class.cast(bd);
                if (isInvisible(md)) {
                    invisibleMethods.add(md);
                }
            }
        }
    }

    /**
     * @param method
     *
     * @return
     */
    protected boolean isInvisible(MethodDeclaration method) {
        Modifier modifier = method.getModifier();

        return nonNull(modifier)
                && (modifier.getVisibility() == PRIVATE || modifier.getVisibility() == PROTECTED);

    }
}
