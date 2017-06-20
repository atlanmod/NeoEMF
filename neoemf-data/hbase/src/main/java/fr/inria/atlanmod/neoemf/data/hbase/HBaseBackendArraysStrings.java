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
import fr.inria.atlanmod.neoemf.data.mapper.ManyValueWithArrays;
import fr.inria.atlanmod.neoemf.data.mapper.MappingFunction;
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
 * A {@link HBaseBackend} that use a {@link ManyValueWithArrays} mapping for storing attributes and
 * {@link ReferenceWith}/{@link ManyReferenceWith} mappings for storing references.
 *
 * @see HBaseBackendFactory
 */
@ParametersAreNonnullByDefault
class HBaseBackendArraysStrings extends AbstractHBaseBackend implements ReferenceWith<String>, ManyValueWithArrays, ManyReferenceWith<String> {

    /**
     * The mapping used to convert single-valued references.
     */
    private static final MappingFunction<Id, String> SINGLE_MAPPING = new MappingFunction<Id, String>() {
        @Nonnull
        @Override
        public String map(Id i) {
            return i.toString();
        }

        @Nonnull
        @Override
        public Id unmap(String o) {
            return StringId.of(o);
        }
    };

    /**
     * The mapping used to convert multi-valued references.
     */
    private static final MappingFunction<List<Id>, String> MANY_MAPPING = new MappingFunction<List<Id>, String>() {
        @Nonnull
        @Override
        public String map(List<Id> i) {
            return checkNotNull(i).stream()
                    .map(id -> isNull(id) ? "" : id.toString())
                    .collect(Collectors.joining(","));
        }

        @Nonnull
        @Override
        public List<Id> unmap(String o) {
            return Arrays.stream(checkNotNull(o).split(","))
                    .map(r -> r.isEmpty() ? null : StringId.of(r))
                    .collect(Collectors.toList());
        }
    };

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
    public MappingFunction<Id, String> referenceMapping() {
        return SINGLE_MAPPING;
    }

    @Nonnull
    @Override
    public MappingFunction<List<Id>, String> manyReferencesMapping() {
        return MANY_MAPPING;
    }
}