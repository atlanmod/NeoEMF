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

import fr.inria.atlanmod.neoemf.data.structure.ContainerInfo;

import org.apache.commons.lang3.SerializationUtils;

/**
 * ???
 */
public class ContainerInfoSerializer implements Serializer<ContainerInfo> {

    @Override
    public byte[] serialize(ContainerInfo value) {
        return SerializationUtils.serialize(value);
    }

    @Override
    public ContainerInfo deserialize(byte[] data) {
        return (ContainerInfo) SerializationUtils.deserialize(data);
    }
}
