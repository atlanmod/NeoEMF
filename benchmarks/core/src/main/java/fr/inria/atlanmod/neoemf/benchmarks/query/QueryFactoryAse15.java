/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.benchmarks.query;

import org.eclipse.emf.common.util.BasicEList;
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
import org.eclipse.gmt.modisco.java.Package;
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

/**
 * A {@link QueryFactory} that creates instances of {@link Query} related to the {@code ASE 2015} conference.
 */
@ParametersAreNonnullByDefault
public class QueryFactoryAse15 extends QueryFactory {

    /**
     * ???
     *
     * @return a new query
     */
    @Nonnull
    public static Query<Integer> queryGrabats() {
        return r -> {
            List<ClassDeclaration> listResult = new BasicEList<>();

            Iterable<TypeDeclaration> typeDeclarations = allInstancesOf(r, JavaPackage.eINSTANCE.getTypeDeclaration());
            for (TypeDeclaration type : typeDeclarations) {
                for (BodyDeclaration body : type.getBodyDeclarations()) {
                    if (MethodDeclaration.class.isInstance(body)) {
                        MethodDeclaration method = MethodDeclaration.class.cast(body);
                        if (nonNull(method.getModifier()) && method.getModifier().isStatic() && nonNull(method.getReturnType()) && method.getReturnType().getType() == type) {
                            listResult.add((ClassDeclaration) type);
                        }
                    }
                }
            }

            return listResult.size();
        };
    }

    /**
     * ???
     *
     * @return a new query
     */
    @Nonnull
    public static Query<Integer> queryThrownExceptions() {
        return r -> {
            List<TypeAccess> thrownExceptions = new BasicEList<>();

            Iterable<ClassDeclaration> classDeclarations = allInstancesOf(r, JavaPackage.eINSTANCE.getClassDeclaration());
            for (ClassDeclaration cd : classDeclarations) {
                appendThrownExceptions(cd, thrownExceptions);
            }
            return thrownExceptions.size();
        };
    }

    /**
     * ???
     *
     * @return a new query
     */
    @Nonnull
    public static Query<Integer> querySpecificInvisibleMethodDeclarations() {
        return r -> {
            List<MethodDeclaration> methodDeclarations = new BasicEList<>();

            Model model = (Model) r.getContents().get(0);
            for (Package pack : model.getOwnedElements()) {
                appendInvisibleMethods(pack, methodDeclarations);
            }
            return methodDeclarations.size();
        };
    }

    /**
     * ???
     *
     * @return a new query
     */
    @Nonnull
    public static Query<Integer> queryCommentsTagContent() {
        return r -> {
            Set<TextElement> result = new HashSet<>();

            Model model = Model.class.cast(r.getContents().get(0));
            for (CompilationUnit cu : model.getCompilationUnits()) {
                for (Comment comment : cu.getCommentList()) {
                    if (Javadoc.class.isInstance(comment)) {
                        for (TagElement te : Javadoc.class.cast(comment).getTags()) {
                            for (ASTNode node : te.getFragments()) {
                                if (TextElement.class.isInstance(node)) {
                                    result.add(TextElement.class.cast(node));
                                }
                            }
                        }
                    }
                }
            }
            return result.size();
        };
    }

    /**
     * ???
     *
     * @return a new query
     */
    @Nonnull
    public static Query<Integer> queryBranchStatements() {
        return r -> {
            List<Statement> result = new BasicEList<>();

            Model model = (Model) r.getContents().get(0);
            for (Package p : model.getOwnedElements()) {
                appendAccessedTypes(p, result);
            }

            return result.size();
        };
    }

    /**
     * ???
     *
     * @param pack   ???
     * @param result ???
     */
    private static void appendAccessedTypes(org.eclipse.gmt.modisco.java.Package pack, List<Statement> result) {
        for (AbstractTypeDeclaration td : pack.getOwnedElements()) {
            if (JavaPackage.eINSTANCE.getClassDeclaration().isInstance(td)) {
                appendAccessedTypes((ClassDeclaration) td, result);
            }
        }
        for (Package subPack : pack.getOwnedPackages()) {
            appendAccessedTypes(subPack, result);
        }
    }

    /**
     * ???
     *
     * @param cd     ???
     * @param result ???
     */
    private static void appendAccessedTypes(ClassDeclaration cd, List<Statement> result) {
        for (BodyDeclaration bd : cd.getBodyDeclarations()) {
            if (MethodDeclaration.class.isInstance(bd)) {
                MethodDeclaration md = MethodDeclaration.class.cast(bd);
                if (nonNull(md.getBody())) {
                    appendAccessedTypesFromBody(md.getBody().getStatements(), result);
                }
            }
        }
    }

    /**
     * ???
     *
     * @param statements ???
     * @param result     ???
     */
    private static void appendAccessedTypesFromBody(List<Statement> statements, List<Statement> result) {
        for (Statement s : statements) {
            appendAccessedTypesFromStatement(s, result);
        }
    }

    /**
     * ???
     *
     * @param statement ???
     * @param result    ???
     */
    private static void appendAccessedTypesFromStatement(Statement statement, List<Statement> result) {
        if (Block.class.isInstance(statement)) {
            appendAccessedTypesFromBody(((Block) statement).getStatements(), result);
        }
        result.add(statement);
    }

    /**
     * ???
     *
     * @param pack   ???
     * @param result ???
     */
    private static void appendInvisibleMethods(Package pack, List<MethodDeclaration> result) {
        for (AbstractTypeDeclaration el : pack.getOwnedElements()) {
            if (JavaPackage.eINSTANCE.getClassDeclaration().isInstance(el)) {
                appendInvisibleMethodsInClassDeclaration((ClassDeclaration) el, result);
            }
        }

        for (Package subPack : pack.getOwnedPackages()) {
            appendInvisibleMethods(subPack, result);
        }
    }

    /**
     * ???
     *
     * @param cd     ???
     * @param result ???
     */
    private static void appendInvisibleMethodsInClassDeclaration(ClassDeclaration cd, List<MethodDeclaration> result) {
        for (BodyDeclaration m : cd.getBodyDeclarations()) {
            if (MethodDeclaration.class.isInstance(m) && nonNull(m.getModifier())) {
                if (m.getModifier().getVisibility() == VisibilityKind.PRIVATE || m.getModifier().getVisibility() == VisibilityKind.PROTECTED) {
                    result.add(MethodDeclaration.class.cast(m));
                }
            }
        }
    }
}
