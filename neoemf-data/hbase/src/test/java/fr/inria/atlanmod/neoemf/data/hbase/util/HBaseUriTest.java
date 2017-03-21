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

package fr.inria.atlanmod.neoemf.data.hbase.util;

import fr.inria.atlanmod.neoemf.data.hbase.context.HBaseTest;
import fr.inria.atlanmod.neoemf.util.AbstractUriTest;

import org.assertj.core.api.Assertions;
import org.eclipse.emf.common.util.URI;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class HBaseUriTest extends AbstractUriTest implements HBaseTest {

    @Override
    public void testCreateUriFromStandardUriInvalidScheme() {
        URI invalidURI = URI.createURI("invalid:/test");

        Throwable thrown = catchThrowable(() -> context().createUri(invalidURI));
        Assertions.assertThat(thrown).isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    public void testCreateUriFromUri() {
        //noinspection ConstantConditions
        Throwable thrown = catchThrowable(() -> HBaseURI.newBuilder().fromUri(null));
        assertThat(thrown).isExactlyInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    public void testCreateUriFromFile() {
        //noinspection ConstantConditions
        Throwable thrown = catchThrowable(() -> HBaseURI.newBuilder().fromFile(null));
        assertThat(thrown).isExactlyInstanceOf(UnsupportedOperationException.class);
    }
}
