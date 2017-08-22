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

package fr.inria.atlanmod.neoemf.data.serializer;

import fr.inria.atlanmod.neoemf.data.bean.ClassBean;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link Serializer} for {@link ClassBean}s.
 */
@ParametersAreNonnullByDefault
final class ClassSerializer implements Serializer<ClassBean> {

    @Override
    public void serialize(ClassBean metaClass, DataOutput out) throws IOException {
        out.writeUTF(metaClass.name());
        out.writeUTF(metaClass.uri());
    }

    @Nonnull
    @Override
    public ClassBean deserialize(DataInput in) throws IOException {
        return ClassBean.of(
                in.readUTF(),
                in.readUTF()
        );
    }
}
