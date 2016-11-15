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

package fr.inria.atlanmod.neoemf.map.datastore.store.impl.serializer;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.datastore.store.impl.cache.FeatureKey;

import org.mapdb.DataInput2;
import org.mapdb.DataOutput2;
import org.mapdb.Serializer;

import java.io.IOException;

public class FeatureKeySerializer implements Serializer<FeatureKey> {

    final Serializer<String> stringSerializer = Serializer.STRING;
    final Serializer<Id> idSerializer = new IdSerializer();

    @Override
    public void serialize(DataOutput2 out, FeatureKey fk) throws IOException {
        idSerializer.serialize(out, fk.id());
        stringSerializer.serialize(out, fk.name());
    }

    @Override
    public FeatureKey deserialize(DataInput2 input, int i) throws IOException {
        return new FeatureKey(
                idSerializer.deserialize(input, -1),
                stringSerializer.deserialize(input, -1)
        );
    }

    public int compare(FeatureKey one, FeatureKey other) {
        return one.compareTo(other);
    }
}
