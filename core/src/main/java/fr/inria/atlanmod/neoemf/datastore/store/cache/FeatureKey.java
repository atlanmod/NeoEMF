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

import java.io.Serializable;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkNotNull;

public class FeatureKey implements Comparable<FeatureKey>, Serializable {

    private static final long serialVersionUID = 1L;

    private final Id id;
    private final String name;

    protected FeatureKey(Id id, String name) {
        this.id = checkNotNull(id);
        this.name = checkNotNull(name);
    }

    public static FeatureKey from(InternalEObject object, EStructuralFeature feature) {
        return from(PersistentEObject.from(object), feature);
    }

    public static FeatureKey from(PersistentEObject object, EStructuralFeature feature) {
        return of(object.id(), feature.getName());
    }

    public static FeatureKey of(Id id, String name) {
        return new FeatureKey(id, name);
    }

    public Id id() {
        return id;
    }

    public String name() {
        return name;
    }

    /**
     * Returns a new instance of this feature key, with a position.
     */
    public MultivaluedFeatureKey withPosition(int position) {
        return MultivaluedFeatureKey.of(id, name, position);
    }

    /**
     * Compares two feature keys.
     *
     * @return 0 if equal, -1 if before, 1 if after.
     */
    @Override
    public int compareTo(FeatureKey other) {
        final int EQUAL = 0;

        if (this == other) {
            return EQUAL;
        }
        int result = id.compareTo(other.id);

        if (result == EQUAL) {
            return name.compareTo(other.name);
        }
        else {
            return result;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    /**
     * Defines equality between feature keys.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FeatureKey)) {
            return false;
        }

        FeatureKey that = (FeatureKey) other;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public String toString() {
        return "FK:{" + id + ", " + name + "}";
    }
}
