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

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;

/**
 * A simple representation of a multi-valued {@link org.eclipse.emf.ecore.EStructuralFeature} of a {@link
 * fr.inria.atlanmod.neoemf.core.PersistentEObject}. The "multi-valued" characteristic is identified with a position.
 */
@Immutable
@ParametersAreNonnullByDefault
public class ManyFeatureBean extends AbstractFeatureBean {

    private static final long serialVersionUID = 7159493156068733506L;

    /**
     * Constructs a new {@code ManyFeatureBean} with the given {@code owner} and the given {@code id}, which are used as
     * a simple representation of a feature of an object. The "multi-valued" characteristic is identified with the
     * {@code position}.
     *
     * @param owner    the identifier of the object using the feature
     * @param id       the identifier of the feature of the {@code owner}
     * @param position the position of the feature
     *
     * @throws NullPointerException     if any argument is {@code null}
     * @throws IllegalArgumentException if the {@code position} is negative
     */
    protected ManyFeatureBean(Id owner, int id, @Nonnegative int position) {
        super(owner, id, position);
    }

    /**
     * Creates a new {@code ManyFeatureBean} from the given {@code owner} and the given {@code feature}.
     * <p>
     * This method behaves like: {@code from(PersistentEObject.from(owner), feature, position)}.
     *
     * @param owner    the object that will be adapted to retrieve its identifier
     * @param feature  the feature of the {@code owner} from which the name will be extracted
     * @param position the position of the feature
     *
     * @return a new {@code ManyFeatureBean}
     *
     * @throws NullPointerException     if any argument is {@code null}
     * @throws IllegalArgumentException if the {@code position} is negative
     * @see #from(PersistentEObject, EStructuralFeature, int)
     * @see PersistentEObject#from(Object)
     * @see EStructuralFeature#getName()
     */
    @Nonnull
    public static ManyFeatureBean from(InternalEObject owner, EStructuralFeature feature, @Nonnegative int position) {
        return from(PersistentEObject.from(owner), feature, position);
    }

    /**
     * Creates a new {@code ManyFeatureBean} from the given {@code owner} and the given {@code feature}.
     * <p>
     * This method behaves like: {@code of(owner.id(), feature.getName(), position)}.
     *
     * @param owner    the object from which the identifier will be extracted
     * @param feature  the feature of the {@code owner} from which the name will be extracted
     * @param position the position of the feature
     *
     * @return a new {@code ManyFeatureBean}
     *
     * @throws NullPointerException     if any argument is {@code null}
     * @throws IllegalArgumentException if the {@code position} is negative
     * @see #of(Id, int, int)
     * @see PersistentEObject#id()
     * @see EStructuralFeature#getName()
     */
    @Nonnull
    public static ManyFeatureBean from(PersistentEObject owner, EStructuralFeature feature, @Nonnegative int position) {
        return of(owner.id(), owner.eClass().getFeatureID(feature), position);
    }

    /**
     * Creates a new {@code ManyFeatureBean} with the given {@code owner} and the given {@code id}, which are used as a
     * simple representation of a feature of an object. The "multi-valued" characteristic is identified with the {@code
     * position}.
     *
     * @param owner    the identifier of the object
     * @param id       the identifier of the feature of the {@code owner}
     * @param position the position of the feature
     *
     * @return a new {@code ManyFeatureBean}
     *
     * @throws NullPointerException     if any argument is {@code null}
     * @throws IllegalArgumentException if the {@code position} is negative
     */
    @Nonnull
    public static ManyFeatureBean of(Id owner, int id, @Nonnegative int position) {
        return new ManyFeatureBean(owner, id, position);
    }

    /**
     * Creates a new {@link SingleFeatureBean} with the {@link fr.inria.atlanmod.neoemf.core.Id} and the name of this
     * {@code ManyFeatureBean}, without its position.
     *
     * @return a new {@link SingleFeatureBean}
     *
     * @see SingleFeatureBean#of(Id, int)
     */
    @Nonnull
    public SingleFeatureBean withoutPosition() {
        return SingleFeatureBean.of(owner, id);
    }

    @Override
    public boolean isMany() {
        return true;
    }

    @Override
    public String toString() {
        return String.format("ManyFeatureBean {%s # %s [%d]}", owner.toHexString(), id, position);
    }
}
