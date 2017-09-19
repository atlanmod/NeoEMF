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

package fr.inria.atlanmod.neoemf.data.bean;

import fr.inria.atlanmod.commons.LazyReference;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;

/**
 * A simple representation of a {@link EClass}.
 */
@Immutable
@ParametersAreNonnullByDefault
public class ClassBean implements Serializable {

    @SuppressWarnings("JavaDoc")
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
     * representation of a an {@link EClass}.
     *
     * @param name the name of the {@link EClass}
     * @param uri  the literal representation of the {@link URI} of the {@link EClass}
     */
    protected ClassBean(String name, String uri) {
        this.name = checkNotNull(name);
        this.uri = checkNotNull(uri);

        lazyClass = LazyReference.soft(() ->
                Optional.ofNullable(EPackage.Registry.INSTANCE.getEPackage(uri))
                        .map(p -> p.getEClassifier(name))
                        .map(EClass.class::cast)
                        .orElse(null));
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
     * Creates a new {@code ClassBean} from the given {@code object}. The {@link EClass} will be found by calling the
     * {@link PersistentEObject#eClass()} method.
     * <p>
     * This method behaves like: {@code of(reference.getName(), reference.getEPackage().getNsURI())}.
     *
     * @param object the object from which the {@link EClass} has to be retrieve with the {@link
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
     * @param eClass the {@link EClass}
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
     * representation of a an {@link EClass}.
     *
     * @param name the name of the {@link EClass}
     * @param uri  the literal representation of the {@link URI} of the {@link EClass}
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
     * Returns the literal representation of the {@link URI} of this {@code ClassBean}.
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
        return get().getEPackage().getEClassifiers()
                .parallelStream()
                .filter(EClass.class::isInstance)
                .map(EClass.class::cast)
                .filter(c -> get().isSuperTypeOf(c))
                .filter(c -> !c.isAbstract())
                .filter(c -> !c.isInterface())
                .map(ClassBean::from)
                .collect(Collectors.toSet());
    }

    /**
     * Retrieves the {@link EClass} corresponding to this {@code ClassBean}.
     *
     * @return a class, or {@code null} if it cannot be found
     */
    @Nonnull
    public EClass get() {
        return checkNotNull(lazyClass.get(),
                "Unable to find the EPackage associated with URI: %s. " +
                        "Make sure it is registered in EPackage.Registry.", uri);
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
        if (!ClassBean.class.isInstance(o)) {
            return false;
        }

        ClassBean that = ClassBean.class.cast(o);
        return Objects.equals(name, that.name)
                && Objects.equals(uri, that.uri);
    }

    @Override
    public String toString() {
        return String.format("ClassBean {%s @ %s}", name, uri);
    }
}

