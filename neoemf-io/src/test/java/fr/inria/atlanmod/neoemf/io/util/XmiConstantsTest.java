/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.io.util;

import org.atlanmod.commons.AbstractTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A test-case about {@link XmiConstants#format(String, String)}.
 */
class XmiConstantsTest extends AbstractTest {

    @Test
    void testFormatWithPrefix() {
        assertThat(XmiConstants.format("prefix", "value")).isEqualTo("prefix:value");
    }

    @Test
    void testFormatWithoutPrefix() {
        assertThat(XmiConstants.format(null, "value")).isEqualTo("value");
    }
}