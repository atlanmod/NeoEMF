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

package fr.inria.atlanmod.neoemf.data.mapdb.serializer;

import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.MultivaluedFeatureKey;

import org.mapdb.DataInput2;
import org.mapdb.DataOutput2;
import org.mapdb.Serializer;

import java.io.IOException;

import javax.annotation.Nonnull;

/**
 * A {@link FeatureKeySerializer} subclass that is able to serialize {@link MultivaluedFeatureKey}.
 *
 * @see FeatureKeySerializer
 * @see MultivaluedFeatureKey
 */
public class MultivaluedFeatureKeySerializer extends FeatureKeySerializer {

    /**
     * An embedded {@link Integer} {@link Serializer} used to handle collection indices.
     */
    final Serializer<Integer> intSerializer = INTEGER;

    @Override
    public void serialize(@Nonnull DataOutput2 out, @Nonnull FeatureKey key) throws IOException {
        super.serialize(out, key);
        intSerializer.serialize(out, ((MultivaluedFeatureKey) key).position());
    }

    @Override
    public FeatureKey deserialize(@Nonnull DataInput2 in, int i) throws IOException {
        return MultivaluedFeatureKey.of(
                idSerializer.deserialize(in, -1),
                stringSerializer.deserialize(in, -1),
                intSerializer.deserialize(in, -1));
    }

}
