/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.io.bean;

import fr.inria.atlanmod.commons.LazyReference;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import java.util.Objects;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;
import static java.util.Objects.nonNull;

/**
 * A simple representation of a {@link org.eclipse.emf.ecore.EClass}.
 */
@ParametersAreNonnullByDefault
public class BasicMetaclass extends AbstractNamedElement<BasicMetaclass> {

    /**
     * The instance of the default meta-class.
     */
    @Nonnull
    private static final BasicMetaclass DEFAULT = new BasicMetaclass(BasicNamespace.getDefault(), EObject.class.getSimpleName());

    /**
     * The namespace of this meta-class.
     */
    @Nonnull
    private final BasicNamespace ns;

    /**
     * The {@link EClass} associated to this meta-class.
     */
    @Nonnull
    private final LazyReference<EClass> eClass = LazyReference.soft(() -> {
        final EPackage p = ns().ePackage();
        final String name = name();

        EClass c = EClass.class.cast(p.getEClassifier(name));
        checkNotNull(c, "Unable to find EClass '%s' from EPackage '%s'", name, p.getNsURI());
        return c;
    });

    /**
     * Constructs a new {@code BasicMetaclass} with the given {@code ns}.
     *
     * @param ns the namespace of this meta-class
     */
    public BasicMetaclass(BasicNamespace ns) {
        this(ns, null);
    }

    /**
     * Constructs a new {@code BasicMetaclass} with the given {@code ns} and {@code name}.
     *
     * @param ns   the namespace of this meta-class
     * @param name the name of this meta-class
     */
    public BasicMetaclass(BasicNamespace ns, @Nullable String name) {
        this.ns = checkNotNull(ns, "ns");

        if (nonNull(name)) {
            name(name);
        }
    }

    /**
     * Returns the default meta-class.
     *
     * @return the {@code BasicMetaclass} representing "ecore:EObject"
     */
    @Nonnull
    public static BasicMetaclass getDefault() {
        return DEFAULT;
    }

    /**
     * Returns the namespace of this meta-class.
     *
     * @return the namespace
     */
    @Nonnull
    public BasicNamespace ns() {
        return ns;
    }

    /**
     * Returns the {@link org.eclipse.emf.ecore.EClass} associated to this meta-class.
     *
     * @return the {@link org.eclipse.emf.ecore.EClass}
     */
    @Nonnull
    public EClass eClass() {
        return eClass.get();
    }

    /**
     * Defines the {@link org.eclipse.emf.ecore.EClass} associated to this meta-class.
     *
     * @param eClass the {@link org.eclipse.emf.ecore.EClass}
     *
     * @return this instance (for chaining)
     */
    public BasicMetaclass eClass(EClass eClass) {
        checkNotNull(eClass, "eClass");
        this.eClass.update(eClass);

        return name(eClass.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), ns);
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        BasicMetaclass that = BasicMetaclass.class.cast(o);
        return Objects.equals(ns, that.ns);
    }

    @Override
    public String toString() {
        return ns.prefix() + ':' + name();
    }
}
