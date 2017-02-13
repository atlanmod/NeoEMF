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
 * A {@link FeatureKeySerializer} that is able to serialize {@link MultiFeatureKey}.
 *
 * @see FeatureKeySerializer
 * @see MultiFeatureKey
 */
public class MultivaluedFeatureKeySerializer extends FeatureKeySerializer {

    /**
     * An embedded {@link Integer} {@link Serializer} used to handle collection indices.
     */
    protected final Serializer<Integer> intSerializer = INTEGER;

    @Override
    public void serialize(@Nonnull DataOutput2 out, @Nonnull SingleFeatureKey key) throws IOException {
        super.serialize(out, key);
        intSerializer.serialize(out, ((MultiFeatureKey) key).position());
    }

    @Override
    public SingleFeatureKey deserialize(@Nonnull DataInput2 in, int i) throws IOException {
        return MultiFeatureKey.of(
                idSerializer.deserialize(in, -1),
                stringSerializer.deserialize(in, -1),
                intSerializer.deserialize(in, -1));
    }
}
