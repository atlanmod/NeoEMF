/*
 * Copyright (c) 2013-2017 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.benchmarks.query.ase2015;

import fr.inria.atlanmod.common.log.Log;
import fr.inria.atlanmod.neoemf.benchmarks.query.Query;
import fr.inria.atlanmod.neoemf.benchmarks.query.QueryFactory;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmt.modisco.java.ASTNode;
import org.eclipse.gmt.modisco.java.AbstractTypeDeclaration;
import org.eclipse.gmt.modisco.java.Block;
import org.eclipse.gmt.modisco.java.BodyDeclaration;
import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.Comment;
import org.eclipse.gmt.modisco.java.CompilationUnit;
import org.eclipse.gmt.modisco.java.Javadoc;
import org.eclipse.gmt.modisco.java.MethodDeclaration;
import org.eclipse.gmt.modisco.java.Model;
import org.eclipse.gmt.modisco.java.Statement;
import org.eclipse.gmt.modisco.java.TagElement;
import org.eclipse.gmt.modisco.java.TextElement;
import org.eclipse.gmt.modisco.java.TypeAccess;
import org.eclipse.gmt.modisco.java.TypeDeclaration;
import org.eclipse.gmt.modisco.java.VisibilityKind;
import org.eclipse.gmt.modisco.java.emf.meta.JavaPackage;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static java.util.Objects.nonNull;

@ParametersAreNonnullByDefault
public class QueryFactoryASE2015 extends QueryFactory {

    @Nonnull
    public static Query<Integer> queryGrabats(Resource resource) {
        return () -> {
            List<ClassDeclaration> listResult = new BasicEList<>();

            Iterable<TypeDeclaration> typeDeclarations = allInstancesOf(resource, JavaPackage.eINSTANCE.getTypeDeclaration());
            for (TypeDeclaration type : typeDeclarations) {
                for (BodyDeclaration body : type.getBodyDeclarations()) {
                    if ((body instanceof MethodDeclaration)) {
                        MethodDeclaration method = (MethodDeclaration) body;
                        if (nonNull(method.getModifier()) && method.getModifier().isStatic() && nonNull(method.getReturnType()) && method.getReturnType().getType() == type) {
                            listResult.add((ClassDeclaration) type);
                        }
                    }
                }
            }

            return listResult.size();
        };
    }

    @Nonnull
    public static Query<Integer> queryThrownExceptions(Resource resource) {
        return () -> {
            List<TypeAccess> thrownExceptions = new BasicEList<>();

            Iterable<ClassDeclaration> classDeclarations = allInstancesOf(resource, JavaPackage.eINSTANCE.getClassDeclaration());
            for (ClassDeclaration cd : classDeclarations) {
                appendThrownExceptions(cd, thrownExceptions);
            }
            return thrownExceptions.size();
        };
    }

    @Nonnull
    public static Query<Integer> querySpecificInvisibleMethodDeclarations(Resource resource) {
        return () -> {
            List<MethodDeclaration> methodDeclarations = new BasicEList<>();

            Model model = (Model) resource.getContents().get(0);
            if (model.getName().equals("org.eclipse.gmt.modisco.java.neoemf") || model.getName().equals("org.eclipse.jdt.core")) {
                try {
                    for (org.eclipse.gmt.modisco.java.Package pack : model.getOwnedElements()) {
                        appendInvisibleMethods(pack, methodDeclarations);
                    }
                }
                catch (NullPointerException e) {
                    Log.error(e);
                }
            }
            return methodDeclarations.size();
        };
    }

    @Nonnull
    public static Query<Integer> queryCommentsTagContent(Resource resource) {
        return () -> {
            Set<TextElement> result = new HashSet<>();

            try {
                Model model = (Model) resource.getContents().get(0);
                if (model.getName().equals("org.eclipse.gmt.modisco.java.neoemf") || model.getName().equals("org.eclipse.jdt.core")) {
                    for (CompilationUnit cu : model.getCompilationUnits()) {
                        for (Comment comment : cu.getCommentList()) {
                            if (comment instanceof Javadoc) {
                                Javadoc javadoc = (Javadoc) comment;
                                for (TagElement te : javadoc.getTags()) {
                                    for (ASTNode node : te.getFragments()) {
                                        if (node instanceof TextElement) {
                                            result.add((TextElement) node);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            catch (NullPointerException e) {
                Log.error(e);
            }

            return result.size();
        };
    }

    @Nonnull
    @SuppressWarnings("unused")
    public static Query<Integer> queryBranchStatements(Resource resource) {
        return () -> {
            List<Statement> result = new BasicEList<>();

            Model model = (Model) resource.getContents().get(0);
            if (model.getName().equals("org.eclipse.gmt.modisco.java.neoemf") || model.getName().equals("org.eclipse.jdt.core")) {
                try {
                    for (org.eclipse.gmt.modisco.java.Package pack : model.getOwnedElements()) {
                        appendAccessedTypes(pack, result);
                    }
                }
                catch (NullPointerException e) {
                    Log.error(e);
                }
            }

            return result.size();
        };
    }

    private static void appendAccessedTypes(org.eclipse.gmt.modisco.java.Package pack, List<Statement> result) {
        for (AbstractTypeDeclaration el : pack.getOwnedElements()) {
            if (JavaPackage.eINSTANCE.getClassDeclaration().isInstance(el)) {
                appendAccessedTypes((ClassDeclaration) el, result);
            }
        }
        for (org.eclipse.gmt.modisco.java.Package subPack : pack.getOwnedPackages()) {
            appendAccessedTypes(subPack, result);
        }
    }

    private static void appendAccessedTypes(ClassDeclaration cd, List<Statement> result) {
        for (BodyDeclaration method : cd.getBodyDeclarations()) {
            if ((method instanceof MethodDeclaration)) {
                MethodDeclaration meth = (MethodDeclaration) method;
                if (nonNull(meth.getBody())) {
                    appendAccessedTypesFromBody(meth.getBody().getStatements(), result);
                }
            }
        }
    }

    private static void appendAccessedTypesFromBody(List<Statement> statements, List<Statement> result) {
        for (Statement s : statements) {
            appendAccessedTypesFromStatement(s, result);
        }
    }

    private static void appendAccessedTypesFromStatement(Statement statement, List<Statement> result) {
        if (statement instanceof Block) {
            appendAccessedTypesFromBody(((Block) statement).getStatements(), result);
        }
        result.add(statement);
    }

    private static void appendInvisibleMethods(org.eclipse.gmt.modisco.java.Package pack, List<MethodDeclaration> result) {
        for (AbstractTypeDeclaration el : pack.getOwnedElements()) {
            if (JavaPackage.eINSTANCE.getClassDeclaration().isInstance(el)) {
                appendInvisibleMethodsInClassDeclaration((ClassDeclaration) el, result);
            }
        }
        for (org.eclipse.gmt.modisco.java.Package subPack : pack.getOwnedPackages()) {
            appendInvisibleMethods(subPack, result);
        }
    }

    private static void appendInvisibleMethodsInClassDeclaration(ClassDeclaration cd, List<MethodDeclaration> result) {
        for (BodyDeclaration method : cd.getBodyDeclarations()) {
            if ((method instanceof MethodDeclaration) && nonNull(method.getModifier())) {
                if (method.getModifier().getVisibility() == VisibilityKind.PRIVATE) {
                    result.add((MethodDeclaration) method);
                }
                else if (method.getModifier().getVisibility() == VisibilityKind.PROTECTED) {
                    result.add((MethodDeclaration) method);
                }
            }
        }
    }
}
