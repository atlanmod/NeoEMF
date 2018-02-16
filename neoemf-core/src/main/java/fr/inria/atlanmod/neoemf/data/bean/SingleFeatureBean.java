/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.bean;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;

/**
 * A simple representation of a single-valued {@link org.eclipse.emf.ecore.EStructuralFeature} of a {@link
 * fr.inria.atlanmod.neoemf.core.PersistentEObject}.
 */
@Immutable
@ParametersAreNonnullByDefault
public class SingleFeatureBean extends AbstractFeatureBean {

    @SuppressWarnings("JavaDoc")
    private static final long serialVersionUID = -2197099155190693261L;

    /**
     * Constructs a new {@code SingleFeatureBean} with the given {@code owner} and the given {@code id}, which are used
     * as a simple representation of a feature of an object.
     *
     * @param owner the identifier of the {@link fr.inria.atlanmod.neoemf.core.PersistentEObject} using the feature
     * @param id    the identifier of the {@link org.eclipse.emf.ecore.EStructuralFeature} of the {@code owner}
     *
     * @throws NullPointerException if any argument is {@code null}
     */
    protected SingleFeatureBean(Id owner, int id) {
        super(owner, id);
    }

    /**
     * Creates a new {@code SingleFeatureBean} from the given {@code owner} and the given {@code feature}.
     * <p>
     * This method behaves like: {@code from(PersistentEObject.from(owner), feature)}.
     *
     * @param owner   the {@link org.eclipse.emf.ecore.InternalEObject} that will be adapted as {@link
     *                fr.inria.atlanmod.neoemf.core.PersistentEObject}
     * @param feature the {@link org.eclipse.emf.ecore.EStructuralFeature} of the {@code owner}
     *
     * @return a new {@code SingleFeatureBean}
     *
     * @throws NullPointerException if any argument is {@code null}
     * @see #from(PersistentEObject, EStructuralFeature)
     * @see PersistentEObject#from(Object)
     * @see EStructuralFeature#getName()
     */
    @Nonnull
    public static SingleFeatureBean from(InternalEObject owner, EStructuralFeature feature) {
        return from(PersistentEObject.from(owner), feature);
    }

    /**
     * Creates a new {@code SingleFeatureBean} from the given {@code owner} and the given {@code feature}.
     * <p>
     * This method behaves like: {@code of(owner.id(), feature.getName())}.
     *
     * @param owner   the {@link fr.inria.atlanmod.neoemf.core.PersistentEObject}
     * @param feature the {@link org.eclipse.emf.ecore.EStructuralFeature} of the {@link
     *                fr.inria.atlanmod.neoemf.core.PersistentEObject}
     *
     * @return a new {@code SingleFeatureBean}
     *
     * @throws NullPointerException if any argument is {@code null}
     * @see #of(Id, int)
     * @see PersistentEObject#id()
     * @see EStructuralFeature#getName()
     */
    @Nonnull
    public static SingleFeatureBean from(PersistentEObject owner, EStructuralFeature feature) {
        return of(owner.id(), owner.eClass().getFeatureID(feature));
    }

    /**
     * Creates a new {@code SingleFeatureBean} with the given {@code owner} and the given {@code id}, which are used as
     * a simple representation of a feature of an object.
     *
     * @param owner the identifier of the {@link fr.inria.atlanmod.neoemf.core.PersistentEObject} using the feature
     * @param id    the identifier of the {@link org.eclipse.emf.ecore.EStructuralFeature} of the {@code owner}
     *
     * @return a new {@code SingleFeatureBean}
     *
     * @throws NullPointerException if any argument is {@code null}
     */
    @Nonnull
    public static SingleFeatureBean of(Id owner, int id) {
        return new SingleFeatureBean(owner, id);
    }

    @Override
    public boolean isMany() {
        return false;
    }

    @Override
    public String toString() {
        return String.format("SingleFeatureBean {%s # %s}", owner.toHexString(), id);
    }
}
