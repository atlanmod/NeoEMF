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

package fr.inria.atlanmod.neoemf.data.mapdb.util.serializer;

import fr.inria.atlanmod.neoemf.data.structure.MultiFeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.SingleFeatureKey;

import org.mapdb.DataInput2;
import org.mapdb.DataOutput2;
import org.mapdb.Serializer;

import java.io.IOException;

import javax.annotation.Nonnull;

/**
 * A {@link SingleFeatureKeySerializer} that is able to serialize {@link MultiFeatureKey}.
 *
 * @see SingleFeatureKeySerializer
 * @see MultiFeatureKey
 */
public class MultiFeatureKeySerializer implements Serializer<MultiFeatureKey> {

    /**
     * An embedded {@link Integer} {@link Serializer} used to handle collection indices.
     */
    private final Serializer<Integer> intSerializer = INTEGER;

    /**
     * An embedded {@link SingleFeatureKey} {@link Serializer} used to handle single-valued feature key.
     */
    private final Serializer<SingleFeatureKey> featureKeySerializer = new SingleFeatureKeySerializer();

    @Override
    public void serialize(@Nonnull DataOutput2 out, @Nonnull MultiFeatureKey key) throws IOException {
        featureKeySerializer.serialize(out, key);
        intSerializer.serialize(out, key.position());
    }

    @Override
    public MultiFeatureKey deserialize(@Nonnull DataInput2 in, int i) throws IOException {
        SingleFeatureKey key = featureKeySerializer.deserialize(in, i);
        return key.withPosition(intSerializer.deserialize(in, -1));
    }
}
