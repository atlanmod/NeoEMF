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

package fr.inria.atlanmod.neoemf.map.datastore.estores.impl;

import fr.inria.atlanmod.neoemf.core.Id;

public class MultivaluedFeatureKey extends FeatureKey {

    private static final long serialVersionUID = 1L;

    private final int position;

    public MultivaluedFeatureKey(Id anId, String aString, int anInt) {
        super(anId,aString);
        position = anInt;
    }

    @Override
    public int compareTo(FeatureKey other) {
        final int BEFORE = -1;
        final int EQUAL = 0;
        final int AFTER = 1;

        if (!(other instanceof MultivaluedFeatureKey)) return AFTER;
        int result = super.compareTo(other);
        if (result == EQUAL) {
            MultivaluedFeatureKey that = (MultivaluedFeatureKey) other;
            return (position > that.position) ? AFTER : (position < that.position) ? BEFORE : EQUAL;
        } else {
            return result;
        }
    }

    /**
     * Defines equality between multivalued feature keys.
     */
    @Override public boolean equals(Object other) {
        return super.equals(other) && position == ((MultivaluedFeatureKey)other).position;
    }

    public int position() {
        return position;
    }
}
