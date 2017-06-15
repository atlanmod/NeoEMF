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

package fr.inria.atlanmod.neoemf.benchmarks.query;

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
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static java.util.Objects.nonNull;

@ParametersAreNonnullByDefault
public class QueryFactory {

    @Nonnull
    public static Query<Long> queryCountAllElements(Resource resource) {
        return () -> ((Iterable<EObject>) resource::getAllContents).spliterator().estimateSize();
    }

    /**
     * Returns the orphan and non Primitive Types of a Java model. This is a common query to all both standard and
     * customized methods.
     */
    @Nonnull
    public static Query<Integer> queryOrphanNonPrimitivesTypes(Resource resource) {
        return () -> {
            List<Type> orphanTypes = new BasicEList<>();

            Model model = (Model) resource.getContents().get(0);
            for (Type t : model.getOrphanTypes()) {
                if (!(t instanceof PrimitiveType)) {
                    orphanTypes.add(t);
                }
            }

            return orphanTypes.size();
        };
    }

    @Nonnull
    public static Query<Integer> queryClassDeclarationAttributes(Resource resource) {
        return () -> {
            HashMap<String, Iterable<NamedElement>> resultMap = new HashMap<>();

            Iterable<ClassDeclaration> classDeclarations = allInstancesOf(resource, JavaPackage.eINSTANCE.getClassDeclaration());
            for (ClassDeclaration cd : classDeclarations) {
                Iterable<NamedElement> declarations = separateFields(cd.getBodyDeclarations());
                resultMap.put(cd.getName(), declarations);
            }

            return resultMap.size();
        };
    }

    @Nonnull
    public static Query<Integer> queryThrownExceptionsPerPackage(Resource resource) {
        return () -> {
            HashMap<String, Iterable<TypeAccess>> resultMap = new HashMap<>();

            Iterable<Package> packages = allInstancesOf(resource, JavaPackage.eINSTANCE.getPackage());
            for (Package pack : packages) {
                List<TypeAccess> thrownExceptions = new BasicEList<>();

                Iterable<AbstractTypeDeclaration> elements = pack.getOwnedElements();
                for (AbstractTypeDeclaration element : elements) {
                    if (element instanceof ClassDeclaration) {
                        appendThrownExceptions((ClassDeclaration) element, thrownExceptions);
                    }
                }
                resultMap.put(pack.getName(), thrownExceptions);
            }

            return resultMap.size();
        };
    }

    /**
     * Renames all the method names with the given {@code name}.
     */
    @Nonnull
    public static Query<Integer> queryRenameAllMethods(Resource resource, String name) {
        return () -> {
            int count = 0;

            Iterable<MethodDeclaration> methodDeclarations = allInstancesOf(resource, JavaPackage.eINSTANCE.getMethodDeclaration());
            for (MethodDeclaration m : methodDeclarations) {
                m.setName(name);
                count++;
            }

            return count;
        };
    }

    @Nonnull
    public static Query<Integer> queryGrabats(Resource resource) {
        return () -> {
            List<ClassDeclaration> listResult = new BasicEList<>();

            Iterable<TypeDeclaration> typeDeclarations = allInstancesOf(resource, JavaPackage.eINSTANCE.getTypeDeclaration());
            for (TypeDeclaration owner : typeDeclarations) {
                for (BodyDeclaration method : owner.getBodyDeclarations()) {
                    if (method instanceof MethodDeclaration) {
                        MethodDeclaration methDecl = (MethodDeclaration) method;
                        if (nonNull(methDecl.getModifier()) && methDecl.getModifier().isStatic() && methDecl.getReturnType() == owner) {
                            listResult.add((ClassDeclaration) owner);
                        }
                    }
                }
            }

            return listResult.size();
        };
    }

    @Nonnull
    public static Query<Integer> queryInvisibleMethodDeclarations(Resource resource) {
        return () -> {
            List<MethodDeclaration> methodDeclarations = new BasicEList<>();

            Iterable<ClassDeclaration> classDeclarations = allInstancesOf(resource, JavaPackage.eINSTANCE.getClassDeclaration());
            for (ClassDeclaration clazz : classDeclarations) {
                for (BodyDeclaration method : clazz.getBodyDeclarations()) {
                    if (method instanceof MethodDeclaration && nonNull(method.getModifier())) {
                        if (method.getModifier().getVisibility() == VisibilityKind.PRIVATE) {
                            methodDeclarations.add((MethodDeclaration) method);
                        }
                        else if (method.getModifier().getVisibility() == VisibilityKind.PROTECTED) {
                            if (hasNoChildTypes(classDeclarations, clazz)) {
                                methodDeclarations.add((MethodDeclaration) method);
                            }
                        }
                    }
                }
            }

            return methodDeclarations.size();
        };
    }

    @Nonnull
    public static Query<Integer> queryUnusedMethodsWithList(Resource resource) {
        return () -> {
            List<MethodDeclaration> unusedMethods = new BasicEList<>();

            Set<AbstractMethodDeclaration> hasBeenInvoked = new HashSet<>();

            Iterable<MethodInvocation> methodInvocations = allInstancesOf(resource, JavaPackage.eINSTANCE.getMethodInvocation());
            for (MethodInvocation method : methodInvocations) {
                hasBeenInvoked.add(method.getMethod());
            }

            Iterable<MethodDeclaration> methodDeclarations = allInstancesOf(resource, JavaPackage.eINSTANCE.getMethodDeclaration());
            for (MethodDeclaration method : methodDeclarations) {
                if (nonNull(method.getModifier())) {
                    if (method.getModifier().getVisibility() == VisibilityKind.PRIVATE) {
                        if (!hasBeenInvoked.contains(method)) {
                            unusedMethods.add(method);
                        }
                    }
                }
            }

            return unusedMethods.size();
        };
    }

    @Nonnull
    public static Query<Integer> queryUnusedMethodsWithLoop(Resource resource) {
        return () -> {
            List<MethodDeclaration> unusedMethods = new BasicEList<>();

            Iterable<MethodInvocation> methodInvocations = allInstancesOf(resource, JavaPackage.eINSTANCE.getMethodInvocation());

            Iterable<MethodDeclaration> methodDeclarations = allInstancesOf(resource, JavaPackage.eINSTANCE.getMethodDeclaration());
            for (MethodDeclaration method : methodDeclarations) {
                if (nonNull(method.getModifier())) {
                    if (method.getModifier().getVisibility() == VisibilityKind.PRIVATE) {
                        if (!hasBeenInvoked(methodInvocations, method)) {
                            unusedMethods.add(method);
                        }
                    }
                }
            }

            return unusedMethods.size();
        };
    }

    @Nonnull
    @SuppressWarnings("unchecked") // <Any>: Allows the use of generics without type check
    protected static <Any> Iterable<Any> allInstancesOf(Resource resource, EClass eClass) {
        List<Any> resultList = new BasicEList<>();

        Iterable<EObject> allContents = resource::getAllContents;
        for (EObject eObject : allContents) {
            if (eClass.isInstance(eObject)) {
                resultList.add((Any) eObject);
            }
        }

        return resultList;
    }

    @Nonnull
    private static Iterable<NamedElement> separateFields(Iterable<BodyDeclaration> bodyDeclarations) {
        List<NamedElement> fields = new BasicEList<>();

        for (BodyDeclaration declaration : bodyDeclarations) {
            if (nonNull(declaration) && declaration instanceof FieldDeclaration) {
                FieldDeclaration field = (FieldDeclaration) declaration;
                if (field.getFragments().isEmpty()) {
                    fields.add(declaration);
                }
                else {
                    fields.addAll(field.getFragments());
                }
            }
        }

        return fields;
    }

    protected static void appendThrownExceptions(ClassDeclaration cd, List<TypeAccess> thrownExceptions) {
        for (BodyDeclaration declaration : cd.getBodyDeclarations()) {
            if (declaration instanceof MethodDeclaration) {
                for (TypeAccess type : ((MethodDeclaration) declaration).getThrownExceptions()) {
                    if (!thrownExceptions.contains(type)) {
                        thrownExceptions.add(type);
                    }
                }
            }
        }
    }

    private static boolean hasNoChildTypes(Iterable<ClassDeclaration> allClasses, ClassDeclaration superClass) {
        for (ClassDeclaration c : allClasses) {
            if (c.getSuperClass() == superClass) {
                return false;
            }
        }
        return true;
    }

    private static boolean hasBeenInvoked(Iterable<MethodInvocation> methodInvocations, EObject method) {
        for (MethodInvocation methodInvocation : methodInvocations) {
            if (Objects.equals(methodInvocation.getMethod(), method)) {
                return true;
            }
        }
        return false;
    }
}
