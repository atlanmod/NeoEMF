package fr.inria.atlanmod.neoemf.map.datastore.estores.impl;

import fr.inria.atlanmod.neoemf.core.Id;

/**
 * Created by sunye on 13/10/2016.
 */
public class MultivaluedFeatureKey extends FeatureKey {
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
}
