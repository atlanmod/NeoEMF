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

package fr.inria.atlanmod.neoemf.data.structure;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.Immutable;

/**
 * A simple representation of a multi-valued {@link EStructuralFeature} of a {@link PersistentEObject}. The
 * "multi-valued" characteristic is identified with a position.
 */
@Immutable
@ParametersAreNonnullByDefault
public class ManyFeatureKey extends AbstractFeatureKey {

    @SuppressWarnings("JavaDoc")
    private static final long serialVersionUID = 7159493156068733506L;

    /**
     * Constructs a new {@code ManyFeatureKey} with the given {@code id} and the given {@code name}, which are
     * used as a simple representation of a feature of an object. The "multi-valued" characteristic is identified with
     * the {@code position}.
     *
     * @param id       the identifier of the {@link PersistentEObject}
     * @param name     the name of the {@link EStructuralFeature} of the {@link PersistentEObject}
     * @param position the position of the {@link EStructuralFeature}
     */
    protected ManyFeatureKey(Id id, String name, @Nonnegative int position) {
        super(id, name, position);
    }

    /**
     * Creates a new {@code ManyFeatureKey} from the given {@code object} and the given {@code feature}.
     * <p>
     * This method behaves like: {@code from(PersistentEObject.from(object), feature, position)}.
     *
     * @param object   the {@link InternalEObject} that will be adapted as {@link PersistentEObject} to have its
     *                 identifier
     * @param feature  the {@link EStructuralFeature} of the {@link PersistentEObject} from which the name will be
     *                 extracted
     * @param position the position of the {@link EStructuralFeature}
     *
     * @return a new {@code ManyFeatureKey}
     *
     * @throws NullPointerException     if any argument is {@code null}
     * @throws IllegalArgumentException if the {@code position} is negative
     * @see #from(PersistentEObject, EStructuralFeature, int)
     * @see PersistentEObject#from(Object)
     * @see EStructuralFeature#getName()
     */
    public static ManyFeatureKey from(InternalEObject object, EStructuralFeature feature, @Nonnegative int position) {
        return from(PersistentEObject.from(object), feature, position);
    }

    /**
     * Creates a new {@code ManyFeatureKey} from the given {@code object} and the given {@code feature}.
     * <p>
     * This method behaves like: {@code of(object.id(), feature.getName(), position)}.
     *
     * @param object   the {@link PersistentEObject} from which the identifier will be extracted
     * @param feature  the {@link EStructuralFeature} of the {@link PersistentEObject} from which the name will be
     *                 extracted
     * @param position the position of the {@link EStructuralFeature}
     *
     * @return a new {@code ManyFeatureKey}
     *
     * @throws NullPointerException     if any argument is {@code null}
     * @throws IllegalArgumentException if the {@code position} is negative
     * @see #of(Id, String, int)
     * @see PersistentEObject#id()
     * @see EStructuralFeature#getName()
     */
    public static ManyFeatureKey from(PersistentEObject object, EStructuralFeature feature, @Nonnegative int position) {
        return of(object.id(), feature.getName(), position);
    }

    /**
     * Creates a new {@code ManyFeatureKey} with the given {@code id} and the given {@code name}, which are used
     * as a simple representation of a feature of an object. The "multi-valued" characteristic is identified with the
     * {@code position}.
     *
     * @param id       the identifier of the {@link PersistentEObject}
     * @param name     the name of the {@link EStructuralFeature} of the {@link PersistentEObject}
     * @param position the position of the {@link EStructuralFeature}
     *
     * @return a new {@code ManyFeatureKey}
     *
     * @throws NullPointerException     if any argument is {@code null}
     * @throws IllegalArgumentException if the {@code position} is negative
     */
    public static ManyFeatureKey of(Id id, String name, @Nonnegative int position) {
        return new ManyFeatureKey(id, name, position);
    }

    /**
     * Creates a new {@link SingleFeatureKey} with the {@link Id} and the name of this {@code ManyFeatureKey}, without
     * its position.
     *
     * @return a new {@link SingleFeatureKey}
     *
     * @see SingleFeatureKey#of(Id, String)
     */
    @Nonnull
    public SingleFeatureKey withoutPosition() {
        return SingleFeatureKey.of(id, name);
    }

    @Override
    public boolean isMany() {
        return true;
    }

    @Override
    public String toString() {
        return String.format("ManyFeatureKey {%s # %s [%d]}", id, name, position);
    }
}
