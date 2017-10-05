/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.berkeleydb.util;

import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.data.berkeleydb.context.BerkeleyDbContext;
import fr.inria.atlanmod.neoemf.util.AbstractUriTest;

import org.eclipse.emf.common.util.URI;
import org.junit.jupiter.api.Test;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

/**
 * A test-case about {@link BerkeleyDbUri}.
 */
@ParametersAreNonnullByDefault
public class BerkeleyDbUriTest extends AbstractUriTest {

    @Nonnull
    @Override
    protected Context context() {
        return BerkeleyDbContext.getWithIndices();
    }

    /**
     * Checks the creation of a server-based {@link URI}.
     * <p>
     * BerkeleyDB does not support server-based {@link URI}s, so this operation must fail.
     */
    @Test
    public void testCreateUriFromServer() throws Exception {
        assertThat(catchThrowable(() -> BerkeleyDbUri.builder().fromServer("host", 0, "segments")))
                .isExactlyInstanceOf(UnsupportedOperationException.class);
    }
}
