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

package fr.inria.atlanmod.neoemf.data.bean.serializer;

import fr.inria.atlanmod.commons.io.serializer.Serializer;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.StringId;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link Serializer} for {@link Id}s.
 */
@ParametersAreNonnullByDefault
final class IdSerializer implements Serializer<Id> {

    @SuppressWarnings("JavaDoc")
    private static final long serialVersionUID = 4870583637926304450L;

    @Override
    public void serialize(Id id, DataOutput out) throws IOException {
        out.writeUTF(id.toString());
    }

    @Nonnull
    @Override
    public Id deserialize(DataInput in) throws IOException {
        return StringId.of(in.readUTF());
    }
}
