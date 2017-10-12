/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.io.util;

import fr.inria.atlanmod.commons.AbstractTest;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class XmiConstantsTest extends AbstractTest {

    @Test
    public void testFormatWithPrefix() throws Exception {
        assertThat(XmiConstants.format("prefix", "value")).isEqualTo("prefix:value");
    }

    @Test
    public void testFormatWithoutPrefix() throws Exception {
        assertThat(XmiConstants.format(null, "value")).isEqualTo("value");
    }
}