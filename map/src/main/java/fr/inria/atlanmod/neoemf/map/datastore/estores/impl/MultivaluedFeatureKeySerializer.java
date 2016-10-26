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

import org.mapdb.DataInput2;
import org.mapdb.DataOutput2;
import org.mapdb.Serializer;

import java.io.IOException;

/**
 * Created by sunye on 13/10/2016.
 */
public class MultivaluedFeatureKeySerializer extends FeatureKeySerializer {

    Serializer<Integer> intSerializer = Serializer.INTEGER;

    @Override
    public void serialize(DataOutput2 out, FeatureKey fk) throws IOException {
        super.serialize(out, fk);
        intSerializer.serialize(out, ((MultivaluedFeatureKey)fk).position());
    }

    @Override
    public FeatureKey deserialize(DataInput2 input, int i) throws IOException {
        return new MultivaluedFeatureKey(
                idSerializer.deserialize(input, -1),
                stringSerializer.deserialize(input, -1),
                intSerializer.deserialize(input, -1).intValue());
    }

}
