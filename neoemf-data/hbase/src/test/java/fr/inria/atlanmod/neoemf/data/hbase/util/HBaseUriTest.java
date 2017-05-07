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

import org.eclipse.emf.common.util.URI;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.mock;

/**
 * A test-case that checks the behavior of {@link HBaseURI}.
 */
public class HBaseUriTest extends AbstractUriTest implements HBaseTest {

    @Ignore("Not supported because of the mini-cluster")
    @Override
    public void testCreateUriFromStandardUriInvalidScheme() {
    }

    /**
     * Checks the creation of a file-based {@link URI} from another {@link URI}.
     * <p>
     * HBase does not support file-based {@link URI}s, so this operation must fail.
     */
    @Test
    public void testCreateUriFromUri() {
        Throwable thrown = catchThrowable(() -> HBaseURI.newBuilder().fromUri(mock(URI.class)));
        assertThat(thrown).isExactlyInstanceOf(UnsupportedOperationException.class);
    }

    /**
     * Checks the creation of a file-based {@link URI} from a {@link File}.
     * <p>
     * HBase does not support file-based {@link URI}s, so this operation must fail.
     */
    @Test
    public void testCreateUriFromFile() {
        Throwable thrown = catchThrowable(() -> HBaseURI.newBuilder().fromFile(mock(File.class)));
        assertThat(thrown).isExactlyInstanceOf(UnsupportedOperationException.class);
    }
}
