/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.bean;

import fr.inria.atlanmod.neoemf.core.PersistentEObject;

import org.atlanmod.commons.LazyReference;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;

import static org.atlanmod.commons.Preconditions.checkNotNull;

/**
 * A simple representation of a {@link org.eclipse.emf.ecore.EClass}.
 */
@Immutable
@ParametersAreNonnullByDefault
public class ClassBean implements Serializable {

    private static final long serialVersionUID = 3630220484508625215L;

    /**
     * The name of the meta-class.
     */
    @Nonnull
    private final String name;

    /**
     * The literal representation of the {@link URI} of the meta-class.
     */
    @Nonnull
    private final String uri;

    /**
     * The cached {@link EClass} of this bean.
     */
    @Nonnull
    private final transient LazyReference<EClass> lazyClass;

    /**
     * Constructs a new {@code ClassBean} with the given {@code name} and {@code uri}, which are used as a simple
     * representation of a an {@link org.eclipse.emf.ecore.EClass}.
     *
     * @param name the name of the {@link org.eclipse.emf.ecore.EClass}
     * @param uri  the literal representation of the {@link org.eclipse.emf.common.util.URI} of the {@link
     *             org.eclipse.emf.ecore.EClass}
     */
    protected ClassBean(String name, String uri) {
        this.name = checkNotNull(name, "name");
        this.uri = checkNotNull(uri, "uri");

        lazyClass = LazyReference.soft(() -> {
            EPackage p = EPackage.Registry.INSTANCE.getEPackage(uri);
            checkNotNull(p, "Unable to find EPackage associated with URI: %s. " +
                    "Make sure it is registered in EPackage.Registry", uri);

            EClass c = (EClass) p.getEClassifier(name);
            checkNotNull(c, "Unable to find EClass '%s' from EPackage '%s'", name, uri);

            return c;
        });
    }

    /**
     * Constructs a new {@code ClassBean} for the represented {@code reference}.
     *
     * @param reference the represented {@link EClass}
     */
    private ClassBean(EClass reference) {
        this(reference.getName(), reference.getEPackage().getNsURI());

        lazyClass.update(reference);
    }

    /**
     * Creates a new {@code ClassBean} from the given {@code object}. The {@link org.eclipse.emf.ecore.EClass} will be
     * found by calling the {@link fr.inria.atlanmod.neoemf.core.PersistentEObject#eClass()} method.
     * <p>
     * This method behaves like: {@code of(reference.getName(), reference.getEPackage().getNsURI())}.
     *
     * @param object the object from which the {@link org.eclipse.emf.ecore.EClass} has to be retrieve with the {@link
     *               PersistentEObject#eClass()} method
     *
     * @return a new {@code ClassBean}
     *
     * @throws NullPointerException if any argument is {@code null}
     * @see #from(EClass)
     */
    @Nonnull
    public static ClassBean from(PersistentEObject object) {
        return from(object.eClass());
    }

    /**
     * Creates a new {@code ClassBean} from the given {@code reference}.
     * <p>
     * This method behaves like: {@code of(reference.getName(), reference.getEPackage().getNsURI())}.
     *
     * @param eClass the {@link org.eclipse.emf.ecore.EClass}
     *
     * @return a new {@code ClassBean}
     *
     * @throws NullPointerException if any argument is {@code null}
     */
    @Nonnull
    public static ClassBean from(EClass eClass) {
        return new ClassBean(eClass);
    }

    /**
     * Creates a new {@code ClassBean} with the given {@code name} and {@code uri}, which are used as a simple
     * representation of a an {@link org.eclipse.emf.ecore.EClass}.
     *
     * @param name the name of the {@link org.eclipse.emf.ecore.EClass}
     * @param uri  the literal representation of the {@link org.eclipse.emf.common.util.URI} of the {@link
     *             org.eclipse.emf.ecore.EClass}
     *
     * @return a new {@code ClassBean}
     *
     * @throws NullPointerException if any argument is {@code null}
     */
    @Nonnull
    public static ClassBean of(String name, String uri) {
        return new ClassBean(name, uri);
    }

    /**
     * Returns the name of this {@code ClassBean}.
     *
     * @return the name
     */
    @Nonnull
    public String name() {
        return name;
    }

    /**
     * Returns the literal representation of the {@link org.eclipse.emf.common.util.URI} of this {@code ClassBean}.
     *
     * @return the URI
     */
    @Nonnull
    public String uri() {
        return uri;
    }

    /**
     * Returns whether this {@code ClassBean} represents an abstract class.
     *
     * @return {@code true} if this {@code ClassBean} represents an abstract class, {@code false} otherwise
     */
    public boolean isAbstract() {
        return get().isAbstract();
    }

    /**
     * Returns whether this {@code ClassBean} represents an interface.
     *
     * @return {@code true} if this {@code ClassBean} represents an interface, {@code false} otherwise
     */
    public boolean isInterface() {
        return get().isInterface();
    }

    /**
     * Retrieves the superclass of this {@code ClassBean}.
     *
     * @return an {@link Optional} containing the representation of the direct superclass, or {@link Optional#empty()}
     * if the class has no superclass
     */
    @Nonnull
    public Optional<ClassBean> inheritFrom() {
        return get().getESuperTypes()
                .parallelStream()
                .filter(c -> !c.isInterface())
                .map(ClassBean::from)
                .findAny();
    }

    /**
     * Retrieves all subclasses of this {@code ClassBean}.
     *
     * @return a immutable {@link Set} containing the representation of all non-abstract subclasses that inherit,
     * directly and indirectly, from this {@code ClassBean}
     */
    @Nonnull
    public Set<ClassBean> inheritedBy() {
        final EClass eClass = get();

        final Predicate<EClass> isInheritedBy = c -> !c.isInterface()
                && !c.isAbstract()
                && eClass.isSuperTypeOf(c);

        return eClass.getEPackage().getEClassifiers()
                .parallelStream()
                .filter(EClass.class::isInstance)
                .map(EClass.class::cast)
                .filter(isInheritedBy)
                .map(ClassBean::from)
                .collect(Collectors.toSet());
    }

    /**
     * Retrieves the {@link org.eclipse.emf.ecore.EClass} corresponding to this {@code ClassBean}.
     *
     * @return a class, or {@code null} if it cannot be found
     */
    @Nonnull
    public EClass get() {
        return lazyClass.get();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, uri);
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ClassBean that = (ClassBean) o;
        return Objects.equals(name, that.name)
                && Objects.equals(uri, that.uri);
    }

    @Override
    public String toString() {
        return String.format("ClassBean {%s @ %s}", name, uri);
    }
}

