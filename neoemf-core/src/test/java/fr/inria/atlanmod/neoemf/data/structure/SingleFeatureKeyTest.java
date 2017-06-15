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

package fr.inria.atlanmod.neoemf.data.structure;

import fr.inria.atlanmod.neoemf.AbstractTest;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.StringId;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A test-case that checks the behavior of {@link SingleFeatureKey}.
 */
public class SingleFeatureKeyTest extends AbstractTest {

    /**
     * Checks the comparison of 2 {@link SingleFeatureKey}s. In this case, they are equal.
     */
    @Test
    public void testCompareEqualTo() {
        Id id0 = StringId.of("id0");

        SingleFeatureKey key0 = SingleFeatureKey.of(id0, "aaa");
        SingleFeatureKey key1 = SingleFeatureKey.of(id0, "aaa");

        assertThat(key0.compareTo(key1)).isEqualTo(0);
    }

    /**
     * Checks the comparison of 2 {@link SingleFeatureKey}s. In this case, the first is lower than the second.
     */
    @Test
    public void testCompareLowerThan() {
        Id id0 = StringId.of("id0");

        SingleFeatureKey key0 = SingleFeatureKey.of(id0, "aaa");
        SingleFeatureKey key1 = SingleFeatureKey.of(id0, "bbb");

        assertThat(key0.compareTo(key1)).isLessThan(0);
    }

    /**
     * Checks the comparison of 2 {@link SingleFeatureKey}s. In this case, the first is greater than the second.
     */
    @Test
    public void testCompareGreaterThan() {
        SingleFeatureKey key0 = SingleFeatureKey.of(StringId.of("AAA"), "aaa");
        SingleFeatureKey key1 = SingleFeatureKey.of(StringId.of("BBB"), "zzz");

        assertThat(key0.compareTo(key1)).isGreaterThan(0);
    }
}
