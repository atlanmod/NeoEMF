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
import fr.inria.atlanmod.neoemf.util.logging.NeoLogger;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A {@link Serializer} for {@link Object}.
 */
@Experimental
public class ObjectSerializer implements Serializer<Object> {

    @Override
    public byte[] serialize(@Nonnull Object value) {
        checkNotNull(value);

        byte[] data;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutput out = new ObjectOutputStream(bos);
            out.writeObject(value);
            out.flush();
        }
        catch (IOException e) {
            NeoLogger.error(e);
        }
        data = bos.toByteArray();
        return data;
    }

    @Override
    public Object deserialize(@Nonnull byte[] data) {
        checkNotNull(data);

        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        Object value = null;
        try {
            ObjectInput in = new ObjectInputStream(bis);
            value = in.readObject();
        }
        catch (IOException | ClassNotFoundException e) {
            NeoLogger.error(e);
        }
        return value;
    }
}
