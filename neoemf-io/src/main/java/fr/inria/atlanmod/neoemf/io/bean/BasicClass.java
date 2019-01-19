/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.io.bean;

import org.atlanmod.commons.LazyReference;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;

import java.util.Objects;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static java.util.Objects.nonNull;
import static org.atlanmod.commons.Preconditions.checkNotNull;

/**
 * A simple representation of a {@link org.eclipse.emf.ecore.EClass}.
 */
@ParametersAreNonnullByDefault
public class BasicClass extends AbstractNamedElement<BasicClass> implements Basic<BasicClass, EClass> {

    /**
     * The instance of the default meta-class: {@code http://www.eclipse.org/emf/2002/Ecore # EObject}.
     */
    @Nonnull
    public static final BasicClass DEFAULT = new BasicClass(EcorePackage.eINSTANCE.getEObject());

    /**
     * The namespace of this meta-class.
     */
    @Nonnull
    private final BasicNamespace ns;

    /**
     * The {@link EClass} represented by this object.
     */
    @Nonnull
    private final LazyReference<EClass> eClass = LazyReference.soft(() -> {
        final EPackage p = getNamespace().getReal();
        final EClass c = (EClass) p.getEClassifier(getName());
        checkNotNull(c, "Unable to find EClass '%s' from EPackage '%s'", getName(), p.getNsURI());
        return c;
    });

    /**
     * Constructs a new {@code BasicClass} from the represented meta-class.
     *
     * @param eClass the represented meta-class
     */
    public BasicClass(EClass eClass) {
        this(BasicNamespace.Registry.getInstance().get(eClass.getEPackage()), eClass.getName());
        this.eClass.update(eClass);
    }

    /**
     * Constructs a new {@code BasicClass} with the given {@code ns}.
     *
     * @param ns the namespace of this meta-class
     */
    public BasicClass(BasicNamespace ns) {
        this(ns, null);
    }

    /**
     * Constructs a new {@code BasicClass} with the given {@code ns} and {@code name}.
     *
     * @param ns   the namespace of this meta-class
     * @param name the name of this meta-class
     */
    public BasicClass(BasicNamespace ns, @Nullable String name) {
        this.ns = checkNotNull(ns, "ns");

        if (nonNull(name)) {
            setName(name);
        }
    }

    @Override
    public EClass getReal() {
        return eClass.get();
    }

    @Nonnull
    @Override
    public BasicClass setReal(EClass eClass) {
        checkNotNull(eClass, "eClass");
        this.eClass.update(eClass);

        return setName(eClass.getName());
    }

    /**
     * Returns the namespace of this meta-class.
     *
     * @return the namespace
     */
    @Nonnull
    public BasicNamespace getNamespace() {
        return ns;
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

        BasicClass that = (BasicClass) o;
        return Objects.equals(ns, that.ns);
    }

    @Override
    public String toString() {
        return ns.getPrefix() + ':' + getName();
    }
}
