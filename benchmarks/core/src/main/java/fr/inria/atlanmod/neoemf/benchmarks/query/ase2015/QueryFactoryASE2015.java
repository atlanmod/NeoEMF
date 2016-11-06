/*
 * Copyright (c) 2013-2016 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.benchmarks.query.ase2015;

import fr.inria.atlanmod.neoemf.benchmarks.query.Query;
import fr.inria.atlanmod.neoemf.benchmarks.query.QueryFactory;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
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
import java.util.Set;

public class QueryFactoryASE2015 extends QueryFactory {

    public static Query<Integer> queryAse2015Grabats09(Resource resource) {
        return new Query<Integer>() {
            @Override
            public Integer call() throws Exception {
                EList<ClassDeclaration> listResult = new BasicEList<>();
                EList<? extends EObject> allTypeDeclarations = getAllInstances(resource, JavaPackage.eINSTANCE.getTypeDeclaration());
                for (EObject eObj : allTypeDeclarations) {
                    TypeDeclaration typeDecl = (TypeDeclaration) eObj;
                    for (BodyDeclaration method : typeDecl.getBodyDeclarations()) {
                        if ((method instanceof MethodDeclaration)) {
                            MethodDeclaration methDecl = (MethodDeclaration) method;
                            if (methDecl.getModifier() != null && methDecl.getModifier().isStatic() && methDecl.getReturnType() != null && methDecl.getReturnType().getType() == typeDecl) {
                                listResult.add((ClassDeclaration) typeDecl);
                            }
                        }
                    }
                }
                return listResult.size();
            }
        };
    }

    public static Query<Integer> queryAse2015ThrownExceptions(Resource resource) {
        return new Query<Integer>() {
            @Override
            public Integer call() throws Exception {
                EList<? extends EObject> classes = getAllInstances(resource, JavaPackage.eINSTANCE.getClassDeclaration());
                EList<TypeAccess> thrownExceptions = new BasicEList<>();
                for (EObject object : classes) {
                    if (object instanceof ClassDeclaration) {
                        for (BodyDeclaration declaration : ((ClassDeclaration) object).getBodyDeclarations()) {
                            if (declaration instanceof MethodDeclaration) {
                                for (TypeAccess type : ((MethodDeclaration) declaration).getThrownExceptions()) {
                                    if (!thrownExceptions.contains(type)) {
                                        thrownExceptions.add(type);
                                    }
                                }
                            }
                        }
                    }
                }
                return thrownExceptions.size();
            }
        };
    }

    public static Query<Integer> queryAse2015SpecificInvisibleMethodDeclarations(Resource resource) {
        return new Query<Integer>() {
            @Override
            public Integer call() throws Exception {
                EList<MethodDeclaration> methodDeclarations = new BasicEList<>();
                Model model = (Model) resource.getContents().get(0);
                if (model.getName().equals("org.eclipse.gmt.modisco.java.neoemf") || model.getName().equals("org.eclipse.jdt.core")) {
                    try {
                        for (org.eclipse.gmt.modisco.java.Package pack : model.getOwnedElements()) {
                            getInvisibleMethods(pack, methodDeclarations);
                        }
                    }
                    catch (NullPointerException e) {
                        LOG.error(e);
                    }
                    return methodDeclarations.size();
                }
                else {
                    return methodDeclarations.size();
                }
            }
        };
    }

    public static Query<Integer> queryAse2015GetCommentsTagContent(Resource resource) {
        return new Query<Integer>() {
            @Override
            public Integer call() throws Exception {
                Set<TextElement> result = new HashSet<>();
                try {
                    Model model = (Model) resource.getContents().get(0);
                    if (model.getName().equals("org.eclipse.gmt.modisco.java.neoemf") || model.getName().equals("org.eclipse.jdt.core")) {
                        for (CompilationUnit cu : model.getCompilationUnits()) {
                            for (Comment com : cu.getCommentList()) {
                                if (com instanceof Javadoc) {
                                    Javadoc jd = (Javadoc) com;
                                    for (TagElement te : jd.getTags()) {
                                        for (ASTNode node : te.getFragments()) {
                                            if (node instanceof TextElement) {
                                                result.add((TextElement) node);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        return result.size();
                    }
                    else {
                        return result.size();
                    }
                }
                catch (NullPointerException e) {
                    LOG.error(e);
                    return result.size();
                }
            }
        };
    }

    @SuppressWarnings("unused")
    public static Query<Integer> queryAse2015BranchStatements(Resource resource) {
        return new Query<Integer>() {
            @Override
            public Integer call() throws Exception {
                EList<Statement> result = new BasicEList<>();
                Model model = (Model) resource.getContents().get(0);
                if (model.getName().equals("org.eclipse.gmt.modisco.java.neoemf") || model.getName().equals("org.eclipse.jdt.core")) {
                    try {
                        for (org.eclipse.gmt.modisco.java.Package pack : model.getOwnedElements()) {
                            getAccessedTypes(pack, result);
                        }
                    }
                    catch (NullPointerException e) {
                        LOG.error(e);
                    }
                    return result.size();
                }
                else {
                    return result.size();
                }
            }
        };
    }

    private static void getAccessedTypes(org.eclipse.gmt.modisco.java.Package pack, EList<Statement> result) {
        for (AbstractTypeDeclaration el : pack.getOwnedElements()) {
            if (JavaPackage.eINSTANCE.getClassDeclaration().isInstance(el)) {
                getAccessedTypes((ClassDeclaration) el, result);
            }
        }
        for (org.eclipse.gmt.modisco.java.Package subPack : pack.getOwnedPackages()) {
            getAccessedTypes(subPack, result);
        }
    }

    private static void getAccessedTypes(ClassDeclaration cd, EList<Statement> result) {
        for (BodyDeclaration method : cd.getBodyDeclarations()) {
            if ((method instanceof MethodDeclaration)) {
                MethodDeclaration meth = (MethodDeclaration) method;
                if (meth.getBody() != null) {
                    getAccessedTypesFromBody(meth.getBody().getStatements(), result);
                }
            }
        }
    }

    private static void getAccessedTypesFromBody(EList<Statement> statements, EList<Statement> result) {
        for (Statement s : statements) {
            getAccessedTypesFromStatement(s, result);
        }
    }

    private static void getAccessedTypesFromStatement(Statement statement, EList<Statement> result) {
        if (statement instanceof Block) {
            getAccessedTypesFromBody(((Block) statement).getStatements(), result);
        }
        result.add(statement);
    }

    private static void getInvisibleMethods(org.eclipse.gmt.modisco.java.Package pack, EList<MethodDeclaration> result) {
        for (AbstractTypeDeclaration el : pack.getOwnedElements()) {
            if (JavaPackage.eINSTANCE.getClassDeclaration().isInstance(el)) {
                getInvisibleMethodsInClassDeclaration((ClassDeclaration) el, result);
            }
        }
        for (org.eclipse.gmt.modisco.java.Package subPack : pack.getOwnedPackages()) {
            getInvisibleMethods(subPack, result);
        }
    }

    private static void getInvisibleMethodsInClassDeclaration(ClassDeclaration cd, EList<MethodDeclaration> result) {
        for (BodyDeclaration method : cd.getBodyDeclarations()) {
            if ((method instanceof MethodDeclaration) && method.getModifier() != null) {
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
