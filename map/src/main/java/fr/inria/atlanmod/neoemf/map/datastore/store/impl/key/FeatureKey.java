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

package fr.inria.atlanmod.neoemf.map.datastore.store.impl.key;

import fr.inria.atlanmod.neoemf.core.Id;

import java.io.Serializable;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkNotNull;

public class FeatureKey implements Comparable<FeatureKey>, Serializable {

    private static final long serialVersionUID = 1L;

    final private Id id;
    final private String name;

    public FeatureKey(Id anId, String aString) {
        checkNotNull(anId, "FeatureKey Id should not be null");
        checkNotNull(aString, "FeatureKey feature name should not be null");

        id = anId;
        name = aString;
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
        return id.equals(that.id) && name.equals(that.name);
    }

    public String toString() {
        return "FK:{" + id + ", " + name + "}";
    }

    public Id id() {
        return id;
    }

    public String name() {
        return name;
    }
}
