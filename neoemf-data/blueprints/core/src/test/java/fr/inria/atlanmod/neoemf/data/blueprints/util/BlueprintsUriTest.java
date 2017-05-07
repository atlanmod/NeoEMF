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

package fr.inria.atlanmod.neoemf.data.blueprints.util;

import fr.inria.atlanmod.neoemf.data.blueprints.context.BlueprintsTest;
import fr.inria.atlanmod.neoemf.util.AbstractUriTest;

import org.eclipse.emf.common.util.URI;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.mock;

/**
 * A test-case that checks the behavior of {@link BlueprintsURI}.
 */
public class BlueprintsUriTest extends AbstractUriTest implements BlueprintsTest {

    /**
     * Checks the creation of a server-based {@link URI}.
     * <p>
     * Blueprints does not support server-based {@link URI}s, so this operation must fail.
     */
    @Test
    public void testCreateUriFromServer() {
        Throwable thrown = catchThrowable(() -> BlueprintsURI.newBuilder().fromServer("host", 0, mock(URI.class)));
        assertThat(thrown).isExactlyInstanceOf(UnsupportedOperationException.class);
    }
}
