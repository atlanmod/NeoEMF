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

package fr.inria.atlanmod.neoemf.data.berkeleydb.serializer;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.StringId;

import org.apache.commons.lang3.SerializationUtils;

/**
 * ???
 */
public class IdSerializer implements Serializer<Id> {

    @Override
    public byte[] serialize(Id id) {
        return SerializationUtils.serialize(id.toString());
    }

    @Override
    public Id deserialize(byte[] data) {
        return new StringId(SerializationUtils.deserialize(data));
    }
}
