/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.tests;

import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.tests.provider.ContextProvider;
import fr.inria.atlanmod.neoemf.tests.sample.PrimaryObject;
import fr.inria.atlanmod.neoemf.tests.sample.TargetObject;

import org.eclipse.emf.ecore.resource.Resource;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A test-case about {@link fr.inria.atlanmod.neoemf.core.internal.collect.DirectStoreList}.
 */
// TODO Add missing methods
@ParametersAreNonnullByDefault
class StoreListTest extends AbstractResourceBasedTest {

    @ParameterizedTest(name = "[{index}] {0}: count = {1}")
    @ArgumentsSource(ContextProvider.AllWithIntegers.class)
    void testContainsElements(Context context, Integer count) throws IOException {
        try (PersistentResource resource = createPersistentResource(context)) {
            List<TargetObject> content = fillResource(resource, count);

            PrimaryObject primary = PrimaryObject.class.cast(resource.getContents().get(0));

            assertThat(primary.getManyContainmentReferences()).hasSize(content.size());
            assertThat(primary.getManyContainmentReferences()).containsExactlyElementsOf(content);
        }
    }

    /**
     * Fills the {@code resource}.
     *
     * @param resource the resource to fill
     * @param count    the number of {@link TargetObject} to add
     *
     * @return a list of all created {@link TargetObject}s
     */
    @Nonnull
    private List<TargetObject> fillResource(Resource resource, int count) {
        List<TargetObject> targets = new ArrayList<>();

        PrimaryObject primary = EFACTORY.createPrimaryObject();
        primary.setName("Model");

        IntStream.rangeClosed(0, count).forEachOrdered(i -> {
            TargetObject target = EFACTORY.createTargetObject();
            target.setName("target" + i);
            targets.add(target);
            primary.getManyContainmentReferences().add(target);
        });

        resource.getContents().add(primary);

        return targets;
    }
}
