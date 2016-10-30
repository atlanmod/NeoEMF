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

package fr.inria.atlanmod.neoemf.benchmarks.ase2015.queries;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
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
import org.eclipse.gmt.modisco.java.emf.JavaPackage;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ASE2015JavaQueries {

    public static EList<MethodDeclaration> getInvisibleMethodDeclarations(Resource resource) {
        EList<MethodDeclaration> methodDeclarations = new BasicEList<>();
        EList<? extends EObject> allClasses = getAllInstances(resource, JavaPackage.eINSTANCE.getClassDeclaration());
        for (EObject cls : allClasses) {
            for (BodyDeclaration method : ((ClassDeclaration) cls).getBodyDeclarations()) {

                if (!(method instanceof MethodDeclaration)) {
                    continue;
                }
                if (method.getModifier() == null) {
                    continue;
                }
                if (method.getModifier().getVisibility() == VisibilityKind.PRIVATE) {
                    methodDeclarations.add((MethodDeclaration) method);
                }
                else if (method.getModifier().getVisibility() == VisibilityKind.PROTECTED) {
                    methodDeclarations.add((MethodDeclaration) method);
                }
            }
        }
        return methodDeclarations;
    }

    public static EList<ClassDeclaration> grabats09(Resource resource) {
        EList<ClassDeclaration> listResult = new BasicEList<>();
        EList<? extends EObject> allTypeDeclarations = getAllInstances(resource, JavaPackage.eINSTANCE.getTypeDeclaration());
        for (EObject eObj : allTypeDeclarations) {
            TypeDeclaration typeDecl = (TypeDeclaration) eObj;
            for (BodyDeclaration method : typeDecl.getBodyDeclarations()) {
                if (!(method instanceof MethodDeclaration)) {
                    continue;
                }
                MethodDeclaration methDecl = (MethodDeclaration) method;
                if (methDecl.getModifier() != null && methDecl.getModifier().isStatic() && methDecl.getReturnType() != null && methDecl.getReturnType().getType() == typeDecl) {
                    listResult.add((ClassDeclaration) typeDecl);
                }
            }
        }
        return listResult;
    }

    public static EList<TypeAccess> getThrownExceptions(Resource resource) {
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
        return thrownExceptions;
    }

    public static EList<MethodDeclaration> getSpecificInvisibleMethodDeclarations(Resource resource) {
        EList<MethodDeclaration> methodDeclarations = new BasicEList<>();
        Model model = (Model) resource.getContents().get(0);
        if (model.getName().equals("org.eclipse.gmt.modisco.java.neoemf") || model.getName().equals("org.eclipse.jdt.core")) {
            try {
                for (org.eclipse.gmt.modisco.java.Package pack : model.getOwnedElements()) {
                    getInvisibleMethods(pack, methodDeclarations);
                }
            }
            catch (NullPointerException e) {
                e.printStackTrace();
            }
            return methodDeclarations;
        }
        else {
            return methodDeclarations;
        }
    }

    public static Set<TextElement> getCommentsTagContent(Resource resource) {
        try {
            Set<TextElement> result = new HashSet<>();
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
                return result;
            }
            else {
                return result;
            }
        }
        catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static EList<Statement> getBranchStatements(Resource resource) {
        EList<Statement> result = new BasicEList<>();
        Model model = (Model) resource.getContents().get(0);
        if (model.getName().equals("org.eclipse.gmt.modisco.java.neoemf") || model.getName().equals("org.eclipse.jdt.core")) {
            try {
                for (org.eclipse.gmt.modisco.java.Package pack : model.getOwnedElements()) {
                    getAccessedTypes(pack, result);
                }
            }
            catch (NullPointerException e) {
                e.printStackTrace();
            }
            return result;
        }
        else {
            return result;
        }
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
            if (!(method instanceof MethodDeclaration)) {
                continue;
            }
            MethodDeclaration meth = (MethodDeclaration) method;
            if (meth.getBody() == null) {
                continue;
            }
            getAccessedTypesFromBody(meth.getBody().getStatements(), result);
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
            if (!(method instanceof MethodDeclaration)) {
                continue;
            }
            if (method.getModifier() == null) {
                continue;
            }
            if (method.getModifier().getVisibility() == VisibilityKind.PRIVATE) {
                result.add((MethodDeclaration) method);
            }
            else if (method.getModifier().getVisibility() == VisibilityKind.PROTECTED) {
                result.add((MethodDeclaration) method);
            }
        }
    }

    public static EList<? extends EObject> getAllInstances(Resource resource, EClass eClass) {
        Iterator<EObject> iterator = resource.getAllContents();
        EList<EObject> resultList = new BasicEList<>();
        while (iterator.hasNext()) {
            EObject eObject = iterator.next();
            if (eClass.isInstance(eObject)) {
                resultList.add(eObject);
            }
        }
        return resultList;
    }
}
