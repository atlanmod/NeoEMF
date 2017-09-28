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

package fr.inria.atlanmod.neoemf.io.util;

import fr.inria.atlanmod.commons.AbstractTest;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class XmlConstantsTest extends AbstractTest {

    @Test
    public void formatWithPrefix() {
        assertThat(XmlConstants.format("prefix", "value")).isEqualTo("prefix:value");
    }

    @Test
    public void formatWithoutPrefix() {
        assertThat(XmlConstants.format(null, "value")).isEqualTo("value");
    }
}