/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.bean;

import fr.inria.atlanmod.commons.AbstractTest;
import fr.inria.atlanmod.neoemf.core.Id;

import org.junit.jupiter.api.Test;

import javax.annotation.ParametersAreNonnullByDefault;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A test-case about {@link SingleFeatureBean}.
 */
@ParametersAreNonnullByDefault
public class SingleFeatureBeanTest extends AbstractTest {

    /**
     * Checks the comparison of 2 {@link SingleFeatureBean}s. In this case, they are equal.
     */
    @Test
    public void testCompareEqualTo() throws Exception {
        Id id0 = Id.getProvider().fromLong(42);

        SingleFeatureBean key0 = SingleFeatureBean.of(id0, 10);
        SingleFeatureBean key1 = SingleFeatureBean.of(id0, 10);

        assertThat(key0).isEqualByComparingTo(key1);
    }

    /**
     * Checks the comparison of 2 {@link SingleFeatureBean}s. In this case, the first is lower than the second.
     */
    @Test
    public void testCompareLowerThan() throws Exception {
        Id id0 = Id.getProvider().fromLong(42);

        SingleFeatureBean key0 = SingleFeatureBean.of(id0, 10);
        SingleFeatureBean key1 = SingleFeatureBean.of(id0, 11);

        assertThat(key0).isLessThan(key1);
    }

    /**
     * Checks the comparison of 2 {@link SingleFeatureBean}s. In this case, the first is greater than the second.
     */
    @Test
    public void testCompareGreaterThan() throws Exception {
        SingleFeatureBean key0 = SingleFeatureBean.of(Id.getProvider().fromLong(44), 10);
        SingleFeatureBean key1 = SingleFeatureBean.of(Id.getProvider().fromLong(42), 11);

        assertThat(key0).isGreaterThan(key1);
    }
}
