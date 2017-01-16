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

import java.util.Objects;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * A simple representation of a multi-valued {@link EStructuralFeature} of a {@link PersistentEObject}. The
 * "multi-valued" characteristic is identified with a position.
 */
public class MultivaluedFeatureKey extends FeatureKey {

    @SuppressWarnings("JavaDoc")
    private static final long serialVersionUID = 1L;

    /**
     * The position of this key.
     */
    @Nonnegative
    private final int position;

    /**
     * Constructs a new {@code MultivaluedFeatureKey} with the given {@code id} and the given {@code name}, which are
     * used as a simple representation of a feature of an object. The "multi-valued" characteristic is identified with
     * the {@code position}.
     *
     * @param id       the identifier of the {@link PersistentEObject}
     * @param name     the name of the {@link EStructuralFeature} of the {@link PersistentEObject}
     * @param position the position of the {@link EStructuralFeature}
     */
    protected MultivaluedFeatureKey(@Nonnull Id id, @Nonnull String name, @Nonnegative int position) {
        super(id, name);
        checkArgument(position >= 0, "Position must be >= 0");
        this.position = position;
    }

    /**
     * Creates a new {@code MultivaluedFeatureKey} from the given {@code internalObject} and the given {@code feature}.
     * <p>
     * This method behaves like: {@code from(PersistentEObject.from(internalObject), feature,
     * position)}.
     *
     * @param internalObject the {@link InternalEObject} that will be adapted as {@link PersistentEObject} to have its
     *                       identifier
     * @param feature        the {@link EStructuralFeature} of the {@link PersistentEObject} from which the name will be
     *                       extracted
     * @param position       the position of the {@link EStructuralFeature}
     *
     * @return a new {@code MultivaluedFeatureKey}
     *
     * @see #from(PersistentEObject, EStructuralFeature, int)
     * @see PersistentEObject#from(Object)
     * @see EStructuralFeature#getName()
     */
    public static MultivaluedFeatureKey from(@Nonnull InternalEObject internalObject, @Nonnull EStructuralFeature feature, @Nonnegative int position) {
        return from(PersistentEObject.from(internalObject), feature, position);
    }

    /**
     * Creates a new {@code MultivaluedFeatureKey} from the given {@code object} and the given {@code feature}.
     * <p>
     * This method behaves like: {@code of(object.id(), feature.getName(), position)}.
     *
     * @param object   the {@link PersistentEObject} from which the identifier will be extracted
     * @param feature  the {@link EStructuralFeature} of the {@link PersistentEObject} from which the name will be
     *                 extracted
     * @param position the position of the {@link EStructuralFeature}
     *
     * @return a new {@code MultivaluedFeatureKey}
     *
     * @see #of(Id, String, int)
     * @see PersistentEObject#id()
     * @see EStructuralFeature#getName()
     */
    public static MultivaluedFeatureKey from(@Nonnull PersistentEObject object, @Nonnull EStructuralFeature feature, @Nonnegative int position) {
        return of(object.id(), feature.getName(), position);
    }

    /**
     * Creates a new {@code MultivaluedFeatureKey} with the given {@code id} and the given {@code name}, which are used
     * as a simple representation of a feature of an object. The "multi-valued" characteristic is identified with the
     * {@code position}.
     *
     * @param id       the identifier of the {@link PersistentEObject}
     * @param name     the name of the {@link EStructuralFeature} of the {@link PersistentEObject}
     * @param position the position of the {@link EStructuralFeature}
     *
     * @return a new {@code MultivaluedFeatureKey}
     */
    public static MultivaluedFeatureKey of(@Nonnull Id id, @Nonnull String name, @Nonnegative int position) {
        return new MultivaluedFeatureKey(id, name, position);
    }

    /**
     * Returns the position of the {@link EStructuralFeature}.
     *
     * @return the position of the feature
     */
    @Nonnegative
    public int position() {
        return position;
    }

    @Override
    public int compareTo(@Nonnull FeatureKey o) {
        final int BEFORE = -1;
        final int EQUAL = 0;
        final int AFTER = 1;

        if (!(o instanceof MultivaluedFeatureKey)) {
            return AFTER;
        }
        int comparison = super.compareTo(o);
        if (comparison == EQUAL) {
            MultivaluedFeatureKey that = (MultivaluedFeatureKey) o;
            return (position > that.position) ? AFTER : (position < that.position) ? BEFORE : EQUAL;
        }
        else {
            return comparison;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(id(), name(), position);
    }

    @Override
    public boolean equals(@Nullable Object o) {
        return super.equals(o) && position == ((MultivaluedFeatureKey) o).position;
    }
}
