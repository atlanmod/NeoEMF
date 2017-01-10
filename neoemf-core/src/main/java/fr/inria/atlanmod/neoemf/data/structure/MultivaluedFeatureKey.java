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

public class MultivaluedFeatureKey extends FeatureKey {

    private static final long serialVersionUID = 1L;

    @Nonnegative
    private final int position;

    protected MultivaluedFeatureKey(@Nonnull Id id, @Nonnull String name, @Nonnegative int position) {
        super(id, name);
        checkArgument(position >= 0, "Position must be >= 0");
        this.position = position;
    }

    public static MultivaluedFeatureKey from(@Nonnull InternalEObject internalObject, @Nonnull EStructuralFeature feature, @Nonnegative int position) {
        return from(PersistentEObject.from(internalObject), feature, position);
    }

    public static MultivaluedFeatureKey from(@Nonnull PersistentEObject object, @Nonnull EStructuralFeature feature, @Nonnegative int position) {
        return of(object.id(), feature.getName(), position);
    }

    public static MultivaluedFeatureKey of(@Nonnull Id id, @Nonnull String name, @Nonnegative int position) {
        return new MultivaluedFeatureKey(id, name, position);
    }

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

    /**
     * Defines equality between multivalued feature keys.
     */
    @Override
    public boolean equals(@Nullable Object o) {
        return super.equals(o) && position == ((MultivaluedFeatureKey) o).position;
    }
}
