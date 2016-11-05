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

package fr.inria.atlanmod.neoemf.benchmarks.query;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmt.modisco.java.AbstractMethodDeclaration;
import org.eclipse.gmt.modisco.java.AbstractTypeDeclaration;
import org.eclipse.gmt.modisco.java.BodyDeclaration;
import org.eclipse.gmt.modisco.java.ClassDeclaration;
import org.eclipse.gmt.modisco.java.FieldDeclaration;
import org.eclipse.gmt.modisco.java.MethodDeclaration;
import org.eclipse.gmt.modisco.java.MethodInvocation;
import org.eclipse.gmt.modisco.java.Model;
import org.eclipse.gmt.modisco.java.NamedElement;
import org.eclipse.gmt.modisco.java.Package;
import org.eclipse.gmt.modisco.java.PrimitiveType;
import org.eclipse.gmt.modisco.java.Type;
import org.eclipse.gmt.modisco.java.TypeAccess;
import org.eclipse.gmt.modisco.java.TypeDeclaration;
import org.eclipse.gmt.modisco.java.VisibilityKind;
import org.eclipse.gmt.modisco.java.emf.meta.JavaPackage;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class QueryFactory {

    /**
     * Returns the orphan and non Primitive Types of a java model this is a common query to all both standard and
     * customized methods.
     */
    public static Query getOrphanNonPrimitivesTypes(Resource resource) {
        return new Query() {
            @Override
            public Integer call() throws Exception {
                Model model;
                EList<Type> orphanTypes = new BasicEList<>();
                model = (Model) resource.getContents().get(0);
                for (Type currentType : model.getOrphanTypes()) {
                    if (!(currentType instanceof PrimitiveType)) {
                        orphanTypes.add(currentType);
                    }
                }
                return orphanTypes.size();
            }
        };
    }

    public static Query getClassDeclarationAttributes(Resource resource) {
        return new Query() {
            @Override
            public Integer call() throws Exception {
                HashMap<String, EList<NamedElement>> resultMap = new HashMap<>();
                EList<? extends EObject> classes = getAllInstances(resource, JavaPackage.eINSTANCE.getClassDeclaration());
                for (EObject object : classes) {
                    EList<NamedElement> declarations = separateFields(((ClassDeclaration) object).getBodyDeclarations());
                    resultMap.put(((ClassDeclaration) object).getName(), declarations);
                }
                return resultMap.size();
            }
        };
    }

    private static EList<NamedElement> separateFields(EList<BodyDeclaration> bodyDeclarations) {
        EList<NamedElement> fields = new BasicEList<>();
        for (BodyDeclaration declaration : bodyDeclarations) {
            if (declaration != null) {
                if (declaration instanceof FieldDeclaration) {
                    if (((FieldDeclaration) declaration).getFragments().isEmpty()) {
                        fields.add(declaration);
                    }
                    else {
                        fields.addAll(((FieldDeclaration) declaration).getFragments());
                    }
                }
            }
        }
        return fields;
    }

    public static Query getThrownExceptionsPerPackage(Resource resource) {
        return new Query() {
            @Override
            public Integer call() throws Exception {
                HashMap<String, EList<TypeAccess>> resultMap = new HashMap<>();
                EList<? extends EObject> packages = getAllInstances(resource, JavaPackage.eINSTANCE.getPackage());
                for (EObject object : packages) {
                    EList<AbstractTypeDeclaration> elements = ((Package) object).getOwnedElements();
                    EList<TypeAccess> thrownExceptions = new BasicEList<>();
                    for (AbstractTypeDeclaration element : elements) {
                        if (element instanceof ClassDeclaration) {
                            for (BodyDeclaration declaration : element.getBodyDeclarations()) {
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
                    resultMap.put(((Package) object).getName(), thrownExceptions);
                }
                return resultMap.size();
            }
        };
    }

    /**
     * Renames all the method names with the given String.
     */
    public static Query renameAllMethods(Resource resource, String name) {
        return new Query() {
            @Override
            public Integer call() throws Exception {
                EList<? extends EObject> methodList = getAllInstances(resource, JavaPackage.eINSTANCE.getMethodDeclaration());
                for (EObject eObject : methodList) {
                    MethodDeclaration method = (MethodDeclaration) eObject;
                    method.setName(name);
                }
                return methodList.size();
            }
        };
    }

    /**
     * Returns the list of classes.
     */
    public static Query grabats09(Resource resource) {
        return new Query() {
            @Override
            public Integer call() throws Exception {
                EList<ClassDeclaration> listResult = new BasicEList<>();
                for (EObject owner : getAllInstances(resource, JavaPackage.eINSTANCE.getTypeDeclaration())) {
                    TypeDeclaration typeOwner = (TypeDeclaration) owner;
                    for (BodyDeclaration method : typeOwner.getBodyDeclarations()) {
                        if (!(method instanceof MethodDeclaration)) {
                            continue;
                        }
                        if (method.getModifier() != null) {
                            if (method.getModifier().isStatic()) {
                                if (((MethodDeclaration) method).getReturnType() == typeOwner) {
                                    listResult.add((ClassDeclaration) typeOwner);
                                }
                            }
                        }
                    }
                }
                return listResult.size();
            }
        };
    }

    public static Query getInvisibleMethodDeclarations(Resource resource) {
        return new Query() {
            @Override
            public Integer call() throws Exception {
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
                            if (hasNoChildTypes(allClasses, cls)) {
                                methodDeclarations.add((MethodDeclaration) method);
                            }
                        }
                    }
                }
                return methodDeclarations.size();
            }
        };
    }

    private static boolean hasNoChildTypes(EList<? extends EObject> allClasses, EObject cls) {
        for (EObject obj : allClasses) {
            if (((ClassDeclaration) obj).getSuperClass() == cls) {
                return false;
            }
        }
        return true;
    }

    public static Query getUnusedMethodsList(Resource resource) {
        return new Query(){
            @Override
            public Integer call() throws Exception {
                EList<MethodDeclaration> unusedMethods = new BasicEList<>();
                EList<? extends EObject> methodInvocations = getAllInstances(resource, JavaPackage.eINSTANCE.getMethodInvocation());
                EList<? extends EObject> allInstances = getAllInstances(resource, JavaPackage.eINSTANCE.getMethodDeclaration());
                Set<AbstractMethodDeclaration> hasBeenInvoked = new HashSet<>();
                for (EObject methodInv : methodInvocations) {
                    hasBeenInvoked.add(((MethodInvocation) methodInv).getMethod());
                }
                for (EObject method : allInstances) {
                    if (!(method instanceof MethodDeclaration)) {
                        continue;
                    }
                    if (((BodyDeclaration) method).getModifier() == null) {
                        continue;
                    }
                    if (((MethodDeclaration) method).getModifier().getVisibility() == VisibilityKind.PRIVATE) {
                        if (!hasBeenInvoked.contains(method)) {
                            unusedMethods.add((MethodDeclaration) method);
                        }
                    }
                }
                return unusedMethods.size();
            }
        };
    }

    public static Query getUnusedMethodsLoop(Resource resource) {
        return new Query(){
            @Override
            public Integer call() throws Exception {
                EList<MethodDeclaration> unusedMethods = new BasicEList<>();
                EList<? extends EObject> methodInvocations = getAllInstances(resource, JavaPackage.eINSTANCE.getMethodInvocation());
                EList<? extends EObject> allInstances = getAllInstances(resource, JavaPackage.eINSTANCE.getMethodDeclaration());
                for (EObject method : allInstances) {
                    if (!(method instanceof MethodDeclaration)) {
                        continue;
                    }
                    if (((BodyDeclaration) method).getModifier() == null) {
                        continue;
                    }
                    if (((MethodDeclaration) method).getModifier().getVisibility() == VisibilityKind.PRIVATE) {
                        if (!hasBeenInvoked(methodInvocations, method)) {
                            unusedMethods.add((MethodDeclaration) method);
                        }
                    }
                }
                return unusedMethods.size();
            }
        };
    }

    private static boolean hasBeenInvoked(EList<? extends EObject> methodInvocations, EObject method) {
        for (EObject methodInv : methodInvocations) {
            if (((MethodInvocation) methodInv).getMethod().equals(method)) {
                return true;
            }
        }
        return false;
    }

    public static EList<? extends EObject> getAllInstances(Resource resource, EClass eClass) {
        EList<EObject> resultList = new BasicEList<>();
        Iterator<EObject> iterator = resource.getAllContents();
        while (iterator.hasNext()) {
            EObject eObject = iterator.next();
            if (eClass.isInstance(eObject)) {
                resultList.add(eObject);
            }
        }
        return resultList;
    }
}
