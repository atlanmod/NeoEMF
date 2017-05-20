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
import fr.inria.atlanmod.neoemf.core.StringId;

import org.mapdb.DataInput2;
import org.mapdb.DataOutput2;
import org.mapdb.Serializer;

import java.io.IOException;
import java.util.Objects;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static java.util.Objects.isNull;

/**
 * A {@link Serializer} implementation for {@link Id}s.
 * <p>
 * For now, this serializer only works with {@link StringId}.
 *
 * @see Id
 * @see StringId
 */
public class IdSerializer implements Serializer<Id> {

    /**
     * An embedded {@link String} {@link Serializer} used to handle {@link StringId}s.
     */
    private final Serializer<String> serializer = Serializer.STRING;

    @Override
    public void serialize(@Nonnull DataOutput2 out, @Nonnull Id id) throws IOException {
        serializer.serialize(out, id.toString());
    }

    @Override
    public Id deserialize(@Nonnull DataInput2 in, int i) throws IOException {
        return new StringId(serializer.deserialize(in, i));
    }

    @Override
    public int hashCode() {
        return Objects.hash(serializer);
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) {
            return true;
        }
        if (isNull(o) || !(o instanceof IdSerializer)) {
            return false;
        }

        IdSerializer that = (IdSerializer) o;

        return Objects.equals(serializer, that.serializer);
    }
}
