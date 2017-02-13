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

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.structure.SingleFeatureKey;

import org.mapdb.DataInput2;
import org.mapdb.DataOutput2;
import org.mapdb.Serializer;

import java.io.IOException;

import javax.annotation.Nonnull;

/**
 * A {@link Serializer} implementation for {@link SingleFeatureKey}.
 *
 * @see SingleFeatureKey
 */
public class FeatureKeySerializer implements Serializer<SingleFeatureKey> {

    /**
     * The {@link Serializer} that manages {@link String}s.
     */
    protected final Serializer<String> stringSerializer = STRING;

    /**
     * The {@link Serializer} the manages {@link Id}s.
     */
    protected final Serializer<Id> idSerializer = new IdSerializer();

    @Override
    public void serialize(@Nonnull DataOutput2 out, @Nonnull SingleFeatureKey key) throws IOException {
        idSerializer.serialize(out, key.id());
        stringSerializer.serialize(out, key.name());
    }

    @Override
    public SingleFeatureKey deserialize(@Nonnull DataInput2 in, int i) throws IOException {
        return SingleFeatureKey.of(
                idSerializer.deserialize(in, -1),
                stringSerializer.deserialize(in, -1)
        );
    }
}
