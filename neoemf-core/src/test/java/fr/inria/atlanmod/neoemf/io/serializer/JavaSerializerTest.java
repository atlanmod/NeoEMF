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

package fr.inria.atlanmod.neoemf.io.serializer;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

/**
 * A test-case that checks the behavior of {@link Serializer} instances created by the {@link JavaSerializerFactory}.
 */
public class JavaSerializerTest extends AbstractSerializerTest {

    @Override
    protected SerializerFactory factory() {
        return JavaSerializerFactory.getInstance();
    }

    @Test
    public void testSerializeNonSerializable() {
        assertThat(catchThrowable(() -> new ObjectSerializer<>().serialize(new Object())))
                .isInstanceOf(IllegalArgumentException.class);
    }
}