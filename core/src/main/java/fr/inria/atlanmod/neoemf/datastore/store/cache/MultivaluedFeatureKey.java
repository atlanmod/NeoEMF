/*
 * Copyright (c) 2013-2016 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.datastore.store.cache;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import java.util.Objects;

public class MultivaluedFeatureKey extends FeatureKey {

    private static final long serialVersionUID = 1L;

    private final int position;

    protected MultivaluedFeatureKey(Id id, String name, int position) {
        super(id, name);
        this.position = position;
    }

    public static MultivaluedFeatureKey from(InternalEObject object, EStructuralFeature feature, int position) {
        return from(PersistentEObject.from(object), feature, position);
    }

    public static MultivaluedFeatureKey from(PersistentEObject object, EStructuralFeature feature, int position) {
        return of(object.id(), feature.getName(), position);
    }

    public static MultivaluedFeatureKey of(Id id, String name, int position) {
        return new MultivaluedFeatureKey(id, name, position);
    }

    public int position() {
        return position;
    }

    @Override
    public int compareTo(FeatureKey other) {
        final int BEFORE = -1;
        final int EQUAL = 0;
        final int AFTER = 1;

        if (!(other instanceof MultivaluedFeatureKey)) {
            return AFTER;
        }
        int result = super.compareTo(other);
        if (result == EQUAL) {
            MultivaluedFeatureKey that = (MultivaluedFeatureKey) other;
            return (position > that.position) ? AFTER : (position < that.position) ? BEFORE : EQUAL;
        }
        else {
            return result;
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
    public boolean equals(Object other) {
        return super.equals(other) && position == ((MultivaluedFeatureKey) other).position;
    }
}
