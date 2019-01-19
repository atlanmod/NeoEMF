/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.benchmarks.data.store;

import fr.inria.atlanmod.neoemf.benchmarks.adapter.Adapter;
import fr.inria.atlanmod.neoemf.benchmarks.io.LocalWorkspace;
import fr.inria.atlanmod.neoemf.config.BaseConfig;
import fr.inria.atlanmod.neoemf.data.DataCopier;
import fr.inria.atlanmod.neoemf.data.mapping.DataMapper;

import org.atlanmod.commons.collect.MoreIterables;
import org.eclipse.emf.common.util.URI;

import java.io.IOException;
import java.time.Instant;
import java.util.ServiceLoader;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static org.atlanmod.commons.Preconditions.checkState;

/**
 * A {@link StoreCopier} that uses a {@link fr.inria.atlanmod.neoemf.data.DataCopier}.
 */
@ParametersAreNonnullByDefault
public class HierarchicalStoreCopier implements StoreCopier {

    /**
     *
     */
    @Nonnull
    private final Adapter.Internal adapter;

    /**
     * Constructs a new {@code HierarchicalStoreCopier}.
     *
     * @param adapter
     */
    public HierarchicalStoreCopier(Adapter.Internal adapter) {
        this.adapter = adapter;
    }

    @Nonnull
    @Override
    public URI copy(URI uri) throws IOException {
        checkState(uri.isHierarchical(), "URI must be hierarchical but was %s", uri);

        final URI targetUri = adapter.createUri(LocalWorkspace.newTempDirectory(), Instant.now().getEpochSecond() + '.' + uri.path());

        try (DataMapper source = adapter.createMapper(uri, new BaseConfig<>()); DataMapper target = adapter.createMapper(targetUri, new BaseConfig<>().autoSave())) {
            final DataCopier copier = MoreIterables.stream(ServiceLoader.load(DataCopier.class))
                    .findFirst()
                    .orElseThrow(IllegalStateException::new);

            copier.copy(source, target);
        }

        return targetUri;
    }
}
