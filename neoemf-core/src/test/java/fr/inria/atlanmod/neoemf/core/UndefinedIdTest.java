/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the Eclipse
 * Public License, v. 2.0 are satisfied: GNU General Public License, version 3.
 */

package fr.inria.atlanmod.neoemf.core;

import fr.inria.atlanmod.commons.AbstractTest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.annotation.ParametersAreNonnullByDefault;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.mock;

/**
 * A test-case about {@link Id#UNDEFINED}.
 */
@ParametersAreNonnullByDefault
public class UndefinedIdTest extends AbstractTest {

    @Test
    public void testToLong() {
        assertThat(
                catchThrowable(Id.UNDEFINED::toLong)
        ).isExactlyInstanceOf(IllegalStateException.class);
    }

    @Test
    public void testToHexString() {
        assertThat(
                catchThrowable(Id.UNDEFINED::toHexString)
        ).isExactlyInstanceOf(IllegalStateException.class);
    }

    @Test
    public void testCompareTo() {
        assertThat(
                catchThrowable(() -> Id.UNDEFINED.compareTo(mock(Id.class)))
        ).isExactlyInstanceOf(IllegalStateException.class);
    }

    @Test
    public void testToString() {
        assertThat(Id.UNDEFINED).hasToString("Id {UNDEFINED}");
    }
}
