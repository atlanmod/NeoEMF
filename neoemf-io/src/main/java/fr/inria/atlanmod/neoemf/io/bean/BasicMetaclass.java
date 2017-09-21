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

package fr.inria.atlanmod.neoemf.io.bean;

import org.eclipse.emf.ecore.EClass;

import java.util.Objects;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * A simple representation of a meta-class.
 */
@ParametersAreNonnullByDefault
public class BasicMetaclass extends AbstractNamedElement {

    /**
     * The instance of the default meta-class.
     */
    @Nonnull
    private static final BasicMetaclass DEFAULT = new BasicMetaclass(BasicNamespace.getDefault(), "EObject");

    /**
     * The namespace of this meta-class.
     */
    @Nonnull
    private final BasicNamespace ns;

    /**
     * The {@link EClass} associated to this meta-class.
     */
    private EClass eClass;

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
        this.ns = checkNotNull(ns);

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
     * Returns the {@link EClass} associated to this meta-class.
     *
     * @return the {@link EClass}
     */
    @Nonnull
    public EClass eClass() {
        if (isNull(eClass)) {
            eClass = checkNotNull(EClass.class.cast(ns().ePackage().getEClassifier(name())));
        }
        return eClass;
    }

    /**
     * Defines the {@link EClass} associated to this meta-class.
     *
     * @param eClass the {@link EClass}
     */
    public void eClass(EClass eClass) {
        this.eClass = checkNotNull(eClass);
        name(eClass.getName());
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
