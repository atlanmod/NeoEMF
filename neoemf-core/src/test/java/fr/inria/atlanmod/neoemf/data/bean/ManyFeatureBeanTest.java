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

package fr.inria.atlanmod.neoemf.data.bean;

import fr.inria.atlanmod.commons.AbstractTest;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.IdProvider;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A test-case about {@link SingleFeatureBean}.
 */
public class ManyFeatureBeanTest extends AbstractTest {

    /**
     * Checks the comparison of 2 {@link ManyFeatureBean}s. In this case, they are equal.
     */
    @Test
    public void testCompareEqualTo() {
        Id id0 = IdProvider.create("id0");

        SingleFeatureBean key = SingleFeatureBean.of(id0, "aaa");
        ManyFeatureBean key0 = key.withPosition(0);
        ManyFeatureBean key1 = key.withPosition(0);

        assertThat(key0.compareTo(key1)).isEqualTo(0);
    }

    /**
     * Checks the comparison of 2 {@link ManyFeatureBean}s. In this case, the first is lower than the second.
     */
    @Test
    public void testCompareLowerThan() {
        Id id0 = IdProvider.create("id0");

        SingleFeatureBean key = SingleFeatureBean.of(id0, "aaa");
        ManyFeatureBean key0 = key.withPosition(0);
        ManyFeatureBean key1 = key.withPosition(1);

        assertThat(key0.compareTo(key1)).isLessThan(0);
    }

    /**
     * Checks the comparison of 2 {@link ManyFeatureBean}s. In this case, the first is greater than the second.
     */
    @Test
    public void testCompareGreaterThan() {
        Id id0 = IdProvider.create("id0");
        SingleFeatureBean key = SingleFeatureBean.of(id0, "aaa");

        ManyFeatureBean key0 = key.withPosition(1);
        ManyFeatureBean key1 = key.withPosition(0);

        assertThat(key0.compareTo(key1)).isGreaterThan(0);
    }
}
