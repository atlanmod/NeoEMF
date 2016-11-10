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

import com.google.common.collect.Iterators;

import org.eclipse.emf.common.util.BasicEList;
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
import java.util.List;
import java.util.Set;

import static java.util.Objects.isNull;

public class QueryFactory {

    public static Query<Integer> queryCountAllElements(Resource resource) {
        return () -> Iterators.size(resource.getAllContents());
    }

    /**
     * Returns the orphan and non Primitive Types of a java model this is a common query to all both standard and
     * customized methods.
     */
    public static Query<Integer> queryOrphanNonPrimitivesTypes(Resource resource) {
        return () -> {
            Model model;
            List<Type> orphanTypes = new BasicEList<>();
            model = (Model) resource.getContents().get(0);
            for (Type currentType : model.getOrphanTypes()) {
                if (!(currentType instanceof PrimitiveType)) {
                    orphanTypes.add(currentType);
                }
            }
            return orphanTypes.size();
        };
    }

    /**
     *
     */
    public static Query<Integer> queryClassDeclarationAttributes(Resource resource) {
        return () -> {
            HashMap<String, List<NamedElement>> resultMap = new HashMap<>();
            List<? extends EObject> classes = getAllInstances(resource, JavaPackage.eINSTANCE.getClassDeclaration());
            for (EObject object : classes) {
                List<NamedElement> declarations = separateFields(((ClassDeclaration) object).getBodyDeclarations());
                resultMap.put(((ClassDeclaration) object).getName(), declarations);
            }
            return resultMap.size();
        };
    }

    /**
     *
     */
    public static Query<Integer> queryThrownExceptionsPerPackage(Resource resource) {
        return () -> {
            HashMap<String, List<TypeAccess>> resultMap = new HashMap<>();
            List<? extends EObject> packages = getAllInstances(resource, JavaPackage.eINSTANCE.getPackage());
            for (EObject object : packages) {
                List<AbstractTypeDeclaration> elements = ((Package) object).getOwnedElements();
                List<TypeAccess> thrownExceptions = new BasicEList<>();
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
        };
    }

    /**
     * Renames all the method names with the given String.
     */
    public static Query<Void> queryRenameAllMethods(Resource resource, String name) {
        return () -> {
            List<? extends EObject> methodList = getAllInstances(resource, JavaPackage.eINSTANCE.getMethodDeclaration());
            for (EObject eObject : methodList) {
                MethodDeclaration method = (MethodDeclaration) eObject;
                method.setName(name);
            }
            return null;
        };
    }

    /**
     * Returns the list of classes.
     */
    public static Query<Integer> queryGrabats09(Resource resource) {
        return () -> {
            List<ClassDeclaration> listResult = new BasicEList<>();
            for (EObject owner : getAllInstances(resource, JavaPackage.eINSTANCE.getTypeDeclaration())) {
                TypeDeclaration typeOwner = (TypeDeclaration) owner;
                for (BodyDeclaration method : typeOwner.getBodyDeclarations()) {
                    if (method instanceof MethodDeclaration) {
                        if (!isNull(method.getModifier())) {
                            if (method.getModifier().isStatic()) {
                                if (((MethodDeclaration) method).getReturnType() == typeOwner) {
                                    listResult.add((ClassDeclaration) typeOwner);
                                }
                            }
                        }
                    }
                }
            }
            return listResult.size();
        };
    }

    /**
     *
     */
    public static Query<Integer> queryInvisibleMethodDeclarations(Resource resource) {
        return () -> {
            List<MethodDeclaration> methodDeclarations = new BasicEList<>();
            List<? extends EObject> allClasses = getAllInstances(resource, JavaPackage.eINSTANCE.getClassDeclaration());
            for (EObject cls : allClasses) {
                for (BodyDeclaration method : ((ClassDeclaration) cls).getBodyDeclarations()) {

                    if (method instanceof MethodDeclaration && !isNull(method.getModifier())) {
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
            }
            return methodDeclarations.size();
        };
    }

    /**
     *
     */
    public static Query<Integer> queryUnusedMethodsWithList(Resource resource) {
        return () -> {
            List<MethodDeclaration> unusedMethods = new BasicEList<>();
            List<? extends EObject> methodInvocations = getAllInstances(resource, JavaPackage.eINSTANCE.getMethodInvocation());
            List<? extends EObject> allInstances = getAllInstances(resource, JavaPackage.eINSTANCE.getMethodDeclaration());
            Set<AbstractMethodDeclaration> hasBeenInvoked = new HashSet<>();
            for (EObject methodInv : methodInvocations) {
                hasBeenInvoked.add(((MethodInvocation) methodInv).getMethod());
            }
            for (EObject method : allInstances) {
                if (method instanceof MethodDeclaration && !isNull(((BodyDeclaration) method).getModifier())) {
                    if (((MethodDeclaration) method).getModifier().getVisibility() == VisibilityKind.PRIVATE) {
                        if (!hasBeenInvoked.contains(method)) {
                            unusedMethods.add((MethodDeclaration) method);
                        }
                    }
                }
            }
            return unusedMethods.size();
        };
    }

    /**
     *
     */
    public static Query<Integer> queryUnusedMethodsWithLoop(Resource resource) {
        return () -> {
            List<MethodDeclaration> unusedMethods = new BasicEList<>();
            List<? extends EObject> methodInvocations = getAllInstances(resource, JavaPackage.eINSTANCE.getMethodInvocation());
            List<? extends EObject> allInstances = getAllInstances(resource, JavaPackage.eINSTANCE.getMethodDeclaration());
            for (EObject method : allInstances) {
                if ((method instanceof MethodDeclaration) && !isNull(((BodyDeclaration) method).getModifier())) {
                    if (((MethodDeclaration) method).getModifier().getVisibility() == VisibilityKind.PRIVATE) {
                        if (!hasBeenInvoked(methodInvocations, method)) {
                            unusedMethods.add((MethodDeclaration) method);
                        }
                    }
                }
            }
            return unusedMethods.size();
        };
    }

    /**
     *
     */
    protected static List<? extends EObject> getAllInstances(Resource resource, EClass eClass) {
        List<EObject> resultList = new BasicEList<>();
        Iterator<EObject> iterator = resource.getAllContents();
        while (iterator.hasNext()) {
            EObject eObject = iterator.next();
            if (eClass.isInstance(eObject)) {
                resultList.add(eObject);
            }
        }
        return resultList;
    }

    private static List<NamedElement> separateFields(List<BodyDeclaration> bodyDeclarations) {
        List<NamedElement> fields = new BasicEList<>();
        for (BodyDeclaration declaration : bodyDeclarations) {
            if (!isNull(declaration)) {
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

    private static boolean hasNoChildTypes(List<? extends EObject> allClasses, EObject cls) {
        for (EObject obj : allClasses) {
            if (((ClassDeclaration) obj).getSuperClass() == cls) {
                return false;
            }
        }
        return true;
    }

    private static boolean hasBeenInvoked(List<? extends EObject> methodInvocations, EObject method) {
        for (EObject methodInv : methodInvocations) {
            if (((MethodInvocation) methodInv).getMethod().equals(method)) {
                return true;
            }
        }
        return false;
    }
}
