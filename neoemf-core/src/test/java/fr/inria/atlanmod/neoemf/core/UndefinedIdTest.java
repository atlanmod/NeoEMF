/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.core;

import fr.inria.atlanmod.commons.AbstractTest;
import fr.inria.atlanmod.neoemf.core.internal.UndefinedId;

import org.junit.jupiter.api.Test;

import javax.annotation.ParametersAreNonnullByDefault;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.mock;

/**
 * A test-case about {@link UndefinedId}.
 */
@ParametersAreNonnullByDefault
class UndefinedIdTest extends AbstractTest {

    @Test
    void testToLong() {
        assertThat(
                catchThrowable(Id.UNDEFINED::toLong)
        ).isExactlyInstanceOf(IllegalStateException.class);
    }

    @Test
    void testToHexString() {
        assertThat(
                catchThrowable(Id.UNDEFINED::toHexString)
        ).isExactlyInstanceOf(IllegalStateException.class);
    }

    @Test
    void testCompareTo() {
        assertThat(
                catchThrowable(() -> Id.UNDEFINED.compareTo(mock(Id.class)))
        ).isExactlyInstanceOf(IllegalStateException.class);
    }

    @Test
    void testToString() {
        assertThat(Id.UNDEFINED).hasToString("Id {UNDEFINED}");
    }
}
