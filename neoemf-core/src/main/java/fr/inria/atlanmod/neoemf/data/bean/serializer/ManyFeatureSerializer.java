/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.bean.serializer;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean;

import org.atlanmod.commons.io.serializer.AbstractBinarySerializer;
import org.atlanmod.commons.io.serializer.BinarySerializer;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.WillNotClose;

/**
 * A {@link BinarySerializer} for {@link ManyFeatureBean}s.
 */
@ParametersAreNonnullByDefault
final class ManyFeatureSerializer extends AbstractBinarySerializer<ManyFeatureBean> {

    private static final long serialVersionUID = -8503200234566675439L;

    @Override
    public void serialize(ManyFeatureBean feature, @WillNotClose DataOutput out) throws IOException {
        out.writeLong(feature.owner().toLong());
        out.writeInt(feature.id());
        out.writeInt(feature.position());
    }

    @Nonnull
    @Override
    public ManyFeatureBean deserialize(@WillNotClose DataInput in) throws IOException {
        return ManyFeatureBean.of(
                Id.getProvider().fromLong(in.readLong()),
                in.readInt(),
                in.readInt()
        );
    }
}
