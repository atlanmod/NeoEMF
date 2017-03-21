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

package fr.inria.atlanmod.neoemf.data.mapdb.util;

import fr.inria.atlanmod.neoemf.data.mapdb.context.MapDbTest;
import fr.inria.atlanmod.neoemf.util.AbstractUriTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class MapDbUriTest extends AbstractUriTest implements MapDbTest {

    @Test
    public void testCreateUriFromServer() {
        //noinspection ConstantConditions
        Throwable thrown = catchThrowable(() -> MapDbURI.newBuilder().fromServer("host", 0, null));
        assertThat(thrown).isExactlyInstanceOf(UnsupportedOperationException.class);
    }
}
