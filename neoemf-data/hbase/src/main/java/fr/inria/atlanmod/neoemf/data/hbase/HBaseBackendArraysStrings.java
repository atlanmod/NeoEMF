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

package fr.inria.atlanmod.neoemf.data.hbase;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.StringId;
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.mapper.ManyReferenceWith;
import fr.inria.atlanmod.neoemf.data.mapper.ManyValueWith;
import fr.inria.atlanmod.neoemf.data.mapper.ManyValueWithArrays;
import fr.inria.atlanmod.neoemf.data.mapper.ReferenceWith;

import org.apache.hadoop.hbase.client.Table;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.common.Preconditions.checkNotNull;
import static java.util.Objects.isNull;

/**
 * A {@link HBaseBackend} that use a {@link ManyValueWith} mapping for storing attributes and
 * {@link ReferenceWith}/{@link ManyReferenceWith} mappings for storing references.
 *
 * @see HBaseBackendFactory
 */
@ParametersAreNonnullByDefault
class HBaseBackendArraysStrings extends AbstractHBaseBackend implements ManyValueWithArrays, ReferenceWith<String>, ManyReferenceWith<String> {

    /**
     * Constructs a new {@code HBaseBackendArrays} on th given {@code table}
     * <p>
     * <b>Note:</b> This constructor is protected. To create a new {@link HBaseBackend} use {@link
     * BackendFactory#createPersistentBackend(org.eclipse.emf.common.util.URI, java.util.Map)}.
     *
     * @param table the HBase table
     */
    protected HBaseBackendArraysStrings(Table table) {
        super(table);
    }

    @Nonnull
    @Override
    public String mapReference(Id value) {
        return value.toString();
    }

    @Nonnull
    @Override
    public Id unmapReference(String value) {
        return StringId.of(value);
    }

    @Nonnull
    @Override
    public String mapReferences(List<Id> references) {
        return checkNotNull(references).stream()
                .map(id -> isNull(id) ? "" : id.toString())
                .collect(Collectors.joining(","));
    }

    @Nonnull
    @Override
    public List<Id> unmapReferences(String references) {
        return Arrays.stream(checkNotNull(references).split(","))
                .map(r -> r.isEmpty() ? null : StringId.of(r))
                .collect(Collectors.toList());
    }
}