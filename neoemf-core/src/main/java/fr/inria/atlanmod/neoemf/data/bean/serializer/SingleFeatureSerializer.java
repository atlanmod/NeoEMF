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
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link Serializer} for {@link SingleFeatureBean}s.
 */
@ParametersAreNonnullByDefault
final class SingleFeatureSerializer implements Serializer<SingleFeatureBean> {

    @SuppressWarnings("JavaDoc")
    private static final long serialVersionUID = -6425763366179041775L;

    @Override
    public void serialize(SingleFeatureBean key, DataOutput out) throws IOException {
        out.writeLong(key.owner().toLong());
        out.writeInt(key.id());
    }

    @Nonnull
    @Override
    public SingleFeatureBean deserialize(DataInput in) throws IOException {
        return SingleFeatureBean.of(
                Id.getProvider().fromLong(in.readLong()),
                in.readInt()
        );
    }
}
