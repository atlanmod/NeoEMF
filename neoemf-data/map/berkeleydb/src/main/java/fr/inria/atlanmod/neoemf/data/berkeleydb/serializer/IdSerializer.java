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

import fr.inria.atlanmod.neoemf.annotations.Experimental;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.StringId;

import org.apache.commons.lang3.SerializationUtils;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A {@link Serializer} for {@link Id}.
 */
@Experimental
public class IdSerializer implements Serializer<Id> {

    @Override
    public byte[] serialize(@Nonnull Id id) {
        checkNotNull(id);

        return SerializationUtils.serialize(id.toString());
    }

    @Override
    public Id deserialize(@Nonnull byte[] data) {
        checkNotNull(data);

        return StringId.of(SerializationUtils.deserialize(data));
    }
}
