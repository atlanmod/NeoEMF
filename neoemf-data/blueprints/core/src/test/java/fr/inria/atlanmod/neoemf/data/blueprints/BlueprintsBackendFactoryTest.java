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

package fr.inria.atlanmod.neoemf.data.blueprints;

import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.data.AbstractBackendFactoryTest;
import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.blueprints.context.BlueprintsContext;
import fr.inria.atlanmod.neoemf.data.blueprints.option.BlueprintsOptions;

import org.junit.jupiter.api.Test;

import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A test-case about {@link BlueprintsBackendFactory}.
 */
@ParametersAreNonnullByDefault
public class BlueprintsBackendFactoryTest extends AbstractBackendFactoryTest {

    @Nonnull
    @Override
    protected Context context() {
        return BlueprintsContext.getWithIndices();
    }

    @Override
    public void testCreateTransientBackend() throws Exception {
        Backend backend = context().factory().createTransientBackend();
        assertThat(backend).isInstanceOf(BlueprintsBackend.class);
    }

    @Override
    public void testCreateDefaultPersistentBackend() throws Exception {
        Backend backend = context().factory().createPersistentBackend(context().createUri(currentTempFile()), BlueprintsOptions.noOption());
        assertThat(backend).isInstanceOf(DefaultBlueprintsBackend.class);
    }

    @Override
    public void testCopyBackend() throws Exception {
        Backend transientBackend = context().factory().createTransientBackend();
        assertThat(transientBackend).isInstanceOf(BlueprintsBackend.class);

        Backend persistentBackend = context().factory().createPersistentBackend(context().createUri(currentTempFile()), BlueprintsOptions.noOption());
        assertThat(persistentBackend).isInstanceOf(DefaultBlueprintsBackend.class);

        transientBackend.copyTo(persistentBackend);
    }

    /**
     * Checks the creation of a {@link fr.inria.atlanmod.neoemf.data.PersistentBackend}, specific for Blueprints.
     * <p>
     * The mapping {@code indices} is declared explicitly.
     */
    @Test
    public void testCreateIndicesPersistentBackend() throws Exception {
        Map<String, Object> options = BlueprintsOptions.builder()
                .withIndices()
                .asMap();

        Backend backend = context().factory().createPersistentBackend(context().createUri(currentTempFile()), options);
        assertThat(backend).isInstanceOf(DefaultBlueprintsBackend.class);
    }
}
