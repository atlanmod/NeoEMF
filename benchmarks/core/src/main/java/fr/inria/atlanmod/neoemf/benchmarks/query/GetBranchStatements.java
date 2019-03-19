/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.benchmarks.query;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmt.modisco.java.AbstractTypeDeclaration;
import org.eclipse.gmt.modisco.java.Block;
import org.eclipse.gmt.modisco.java.BodyDeclaration;
import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.MethodDeclaration;
import org.eclipse.gmt.modisco.java.Model;
import org.eclipse.gmt.modisco.java.Package;
import org.eclipse.gmt.modisco.java.Statement;
import org.eclipse.gmt.modisco.java.emf.meta.JavaPackage;

import java.util.Collection;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static java.util.Objects.nonNull;

/**
 * A {@link Query}
 */
@ParametersAreNonnullByDefault
class GetBranchStatements extends AbstractQuery<Collection<Statement>> {

    @Nonnull
    @Override
    public Collection<Statement> executeOn(Resource resource) {
        Collection<Statement> result = createOrderedCollection();

        Model model = getRoot(resource);

        for (Package pkg : model.getOwnedElements()) {
            appendAccessedTypes(pkg, result);
        }

        return result;
    }

    /**
     * @param basePackage
     * @param accessedTypes
     */
    protected void appendAccessedTypes(Package basePackage, Collection<Statement> accessedTypes) {
        EClass eClass = JavaPackage.eINSTANCE.getClassDeclaration();

        for (AbstractTypeDeclaration abstractType : basePackage.getOwnedElements()) {
            if (eClass.isInstance(abstractType)) {
                ClassDeclaration type = (ClassDeclaration) abstractType;
                appendAccessedTypes(type, accessedTypes);
            }
        }

        for (Package subPackage : basePackage.getOwnedPackages()) {
            appendAccessedTypes(subPackage, accessedTypes);
        }
    }

    /**
     * @param type
     * @param accessedTypes
     */
    protected void appendAccessedTypes(ClassDeclaration type, Collection<Statement> accessedTypes) {
        for (BodyDeclaration body : type.getBodyDeclarations()) {
            if (body instanceof MethodDeclaration) {
                MethodDeclaration method = (MethodDeclaration) body;

                if (nonNull(method.getBody())) {
                    appendAccessedTypes(method.getBody().getStatements(), accessedTypes);
                }
            }
        }
    }

    /**
     * @param statements
     * @param accessedTypes
     */
    protected void appendAccessedTypes(Iterable<Statement> statements, Collection<Statement> accessedTypes) {
        for (Statement statement : statements) {
            if (statement instanceof Block) {
                Block block = (Block) statement;
                appendAccessedTypes(block.getStatements(), accessedTypes);
            }
            accessedTypes.add(statement);
        }
    }
}
