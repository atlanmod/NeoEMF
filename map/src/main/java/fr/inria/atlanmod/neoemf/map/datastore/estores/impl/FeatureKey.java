/*
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */
package fr.inria.atlanmod.neoemf.map.datastore.estores.impl;

import fr.inria.atlanmod.neoemf.core.Id;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by sunye on 13/10/2016.
 */
public class FeatureKey implements Comparable<FeatureKey>, Serializable {
    final private Id id;
    final private String name;

    public FeatureKey(Id anId, String aString) {
        assert anId != null: "FeatureKey Id should not be null";
        assert aString != null: "FeatureKey feature name should not be null";

        id = anId;
        name = aString;
    }

    /**
     * Compares two feature keys.
     * @param other
     * @return 0 if equal, -1 if before, 1 if after.
     */
    @Override
    public int compareTo(FeatureKey other) {
        final int EQUAL = 0;

        if (this == other) return EQUAL;
        int result = id.compareTo(other.id);

        if (result == EQUAL) {
            return name.compareTo(other.name);
        } else {
            return result;
        }
    }

    /**
     * Defines equality between feature keys.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof FeatureKey)) return false;

        FeatureKey that = (FeatureKey) other;
        return id.equals(that.id) && name.equals(that.name);
    }

    public String toString() {
        return "FK:{"+id+", "+name+"}";
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,name);
    }

}
