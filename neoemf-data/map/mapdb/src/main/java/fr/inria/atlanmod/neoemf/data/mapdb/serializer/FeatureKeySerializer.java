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

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;

import org.mapdb.DataInput2;
import org.mapdb.DataOutput2;
import org.mapdb.Serializer;

import java.io.IOException;

import javax.annotation.Nonnull;

/**
 * A {@link Serializer} implementation for {@link FeatureKey}.
 * 
 * @see FeatureKey
 */
public class FeatureKeySerializer implements Serializer<FeatureKey> {
    
    /**
     * The {@link Serializer} that manages {@link String}s
     */
    final Serializer<String> stringSerializer = Serializer.STRING;
    /**
     * The {@link Serializer} the manages {@link Id}s
     */
    final Serializer<Id> idSerializer = new IdSerializer();

    @Override
    public void serialize(@Nonnull DataOutput2 out, @Nonnull FeatureKey key) throws IOException {
        idSerializer.serialize(out, key.id());
        stringSerializer.serialize(out, key.name());
    }

    @Override
    public FeatureKey deserialize(@Nonnull DataInput2 in, int i) throws IOException {
        return FeatureKey.of(
                idSerializer.deserialize(in, -1),
                stringSerializer.deserialize(in, -1)
        );
    }
}
