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

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static java.util.Objects.nonNull;

/**
 * A factory that creates instances of {@link Query}.
 */
@ParametersAreNonnullByDefault
public class QueryFactory {

    /**
     * Counts the number of elements in a {@link Resource} by using {@link Resource#getAllContents()}.
     *
     * @return a new query
     *
     * @see Resource#getAllContents()
     */
    @Nonnull
    public static Query<Long> queryCountAllElements() {
        return r -> {
            long count = 0L;

            Iterator<EObject> allContents = r.getAllContents();
            while (allContents.hasNext()) {
                allContents.next();
                count++;
            }

            return count;
        };
    }

    /**
     * Returns the orphan and non-primitive types of a {@link Model}.
     * This is a common query to all both standard and customized methods.
     *
     * @return a new query
     *
     * @see Model#getOrphanTypes()
     * @see PrimitiveType
     */
    @Nonnull
    public static Query<Integer> queryOrphanNonPrimitivesTypes() {
        return r -> {
            List<Type> orphanTypes = new BasicEList<>();

            Model model = Model.class.cast(r.getContents().get(0));
            for (Type t : model.getOrphanTypes()) {
                if (!PrimitiveType.class.isInstance(t)) {
                    orphanTypes.add(t);
                }
            }

            return orphanTypes.size();
        };
    }

    /**
     * ???
     *
     * @return a new query
     */
    @Nonnull
    public static Query<Integer> queryClassDeclarationAttributes() {
        return r -> {
            HashMap<String, Iterable<NamedElement>> resultMap = new HashMap<>();

            Iterable<ClassDeclaration> classDeclarations = allInstancesOf(r, JavaPackage.eINSTANCE.getClassDeclaration());
            for (ClassDeclaration cd : classDeclarations) {
                Iterable<NamedElement> declarations = separateFields(cd.getBodyDeclarations());
                resultMap.put(cd.getName(), declarations);
            }

            return resultMap.size();
        };
    }

    /**
     * ???
     *
     * @return a new query
     */
    @Nonnull
    public static Query<Integer> queryThrownExceptionsPerPackage() {
        return r -> {
            HashMap<String, Iterable<TypeAccess>> resultMap = new HashMap<>();

            Iterable<Package> packages = allInstancesOf(r, JavaPackage.eINSTANCE.getPackage());
            for (Package p : packages) {
                Set<TypeAccess> thrownExceptions = new HashSet<>();

                Iterable<AbstractTypeDeclaration> typeDeclarations = p.getOwnedElements();
                for (AbstractTypeDeclaration td : typeDeclarations) {
                    if (ClassDeclaration.class.isInstance(td)) {
                        appendThrownExceptions(ClassDeclaration.class.cast(td), thrownExceptions);
                    }
                }
                resultMap.put(p.getName(), thrownExceptions);
            }

            return resultMap.size();
        };
    }

    /**
     * Renames all the method names with the given {@code name}.
     *
     * @return a new query
     */
    @Nonnull
    public static Query<Integer> queryRenameAllMethods(String name) {
        return r -> {
            int count = 0;

            Iterable<MethodDeclaration> methodDeclarations = allInstancesOf(r, JavaPackage.eINSTANCE.getMethodDeclaration());
            for (MethodDeclaration md : methodDeclarations) {
                md.setName(name);
                count++;
            }

            return count;
        };
    }

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
            for (TypeDeclaration td : typeDeclarations) {
                for (BodyDeclaration bd : td.getBodyDeclarations()) {
                    if (MethodDeclaration.class.isInstance(bd)) {
                        MethodDeclaration md = MethodDeclaration.class.cast(bd);
                        if (nonNull(md.getModifier()) && md.getModifier().isStatic() && md.getReturnType() == td) {
                            listResult.add((ClassDeclaration) td);
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
    public static Query<Integer> queryInvisibleMethodDeclarations() {
        return r -> {
            List<MethodDeclaration> methodDeclarations = new BasicEList<>();

            Iterable<ClassDeclaration> classDeclarations = allInstancesOf(r, JavaPackage.eINSTANCE.getClassDeclaration());
            for (ClassDeclaration cd : classDeclarations) {
                for (BodyDeclaration bd : cd.getBodyDeclarations()) {
                    if (MethodDeclaration.class.isInstance(bd) && nonNull(bd.getModifier())) {
                        MethodDeclaration md = MethodDeclaration.class.cast(bd);
                        if (bd.getModifier().getVisibility() == VisibilityKind.PRIVATE) {
                            methodDeclarations.add(md);
                        }
                        else if (bd.getModifier().getVisibility() == VisibilityKind.PROTECTED) {
                            if (hasNoChildTypes(classDeclarations, cd)) {
                                methodDeclarations.add(md);
                            }
                        }
                    }
                }
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
    public static Query<Integer> queryUnusedMethodsWithList() {
        return r -> {
            List<MethodDeclaration> unusedMethods = new BasicEList<>();

            Set<AbstractMethodDeclaration> hasBeenInvoked = new HashSet<>();

            Iterable<MethodInvocation> methodInvocations = allInstancesOf(r, JavaPackage.eINSTANCE.getMethodInvocation());
            for (MethodInvocation mi : methodInvocations) {
                hasBeenInvoked.add(mi.getMethod());
            }

            Iterable<MethodDeclaration> methodDeclarations = allInstancesOf(r, JavaPackage.eINSTANCE.getMethodDeclaration());
            for (MethodDeclaration md : methodDeclarations) {
                if (nonNull(md.getModifier())) {
                    if (md.getModifier().getVisibility() == VisibilityKind.PRIVATE) {
                        if (!hasBeenInvoked.contains(md)) {
                            unusedMethods.add(md);
                        }
                    }
                }
            }

            return unusedMethods.size();
        };
    }

    /**
     * ???
     *
     * @return a new query
     */
    @Nonnull
    public static Query<Integer> queryUnusedMethodsWithLoop() {
        return r -> {
            List<MethodDeclaration> unusedMethods = new BasicEList<>();

            Iterable<MethodInvocation> methodInvocations = allInstancesOf(r, JavaPackage.eINSTANCE.getMethodInvocation());

            Iterable<MethodDeclaration> methodDeclarations = allInstancesOf(r, JavaPackage.eINSTANCE.getMethodDeclaration());
            for (MethodDeclaration md : methodDeclarations) {
                if (nonNull(md.getModifier())) {
                    if (md.getModifier().getVisibility() == VisibilityKind.PRIVATE) {
                        if (!hasBeenInvoked(methodInvocations, md)) {
                            unusedMethods.add(md);
                        }
                    }
                }
            }

            return unusedMethods.size();
        };
    }

    /**
     * Retrieves all elements within the {@code resource} that is instance of the given {@code type}.
     *
     * @param resource the resource to explore
     * @param type     the instance of the expected objects
     * @param <T>      the type of the expected objects
     *
     * @return an iterable
     */
    @Nonnull
    @SuppressWarnings("unchecked")
    protected static <T> Iterable<T> allInstancesOf(Resource resource, EClass type) {
        List<T> resultList = new BasicEList<>();

        Iterable<EObject> allContents = resource::getAllContents;
        for (EObject o : allContents) {
            if (type.isInstance(o)) {
                resultList.add((T) o);
            }
        }

        return resultList;
    }

    /**
     * ???
     *
     * @param bodyDeclarations ???
     *
     * @return ???
     */
    @Nonnull
    private static Iterable<NamedElement> separateFields(Iterable<BodyDeclaration> bodyDeclarations) {
        List<NamedElement> fields = new BasicEList<>();

        for (BodyDeclaration bd : bodyDeclarations) {
            if (FieldDeclaration.class.isInstance(bd)) {
                FieldDeclaration fd = FieldDeclaration.class.cast(bd);
                if (fd.getFragments().isEmpty()) {
                    fields.add(bd);
                }
                else {
                    fields.addAll(fd.getFragments());
                }
            }
        }

        return fields;
    }

    /**
     * ???
     *
     * @param cd               ???
     * @param thrownExceptions ???
     */
    protected static void appendThrownExceptions(ClassDeclaration cd, Collection<TypeAccess> thrownExceptions) {
        for (BodyDeclaration bd : cd.getBodyDeclarations()) {
            if (MethodDeclaration.class.isInstance(bd)) {
                for (TypeAccess ta : MethodDeclaration.class.cast(bd).getThrownExceptions()) {
                    if (!thrownExceptions.contains(ta)) {
                        thrownExceptions.add(ta);
                    }
                }
            }
        }
    }

    /**
     * ???
     *
     * @param allClasses ???
     * @param superClass ???
     *
     * @return {@code true} if ???
     */
    private static boolean hasNoChildTypes(Iterable<ClassDeclaration> allClasses, ClassDeclaration superClass) {
            for (ClassDeclaration c : allClasses) {
                if (c.getSuperClass() == superClass) {
                    return false;
                }
            }
            return true;
    }

    /**
     * ???
     *
     * @param methodInvocations ???
     * @param method            ???
     *
     * @return {@code true} if ???
     */
    private static boolean hasBeenInvoked(Iterable<MethodInvocation> methodInvocations, EObject method) {
            for (MethodInvocation methodInvocation : methodInvocations) {
                if (Objects.equals(methodInvocation.getMethod(), method)) {
                    return true;
                }
            }
            return false;
    }
}
