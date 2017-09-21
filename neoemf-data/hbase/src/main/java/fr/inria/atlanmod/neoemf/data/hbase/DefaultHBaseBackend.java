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

import fr.inria.atlanmod.commons.Converter;
import fr.inria.atlanmod.commons.primitive.Strings;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.IdProvider;
import fr.inria.atlanmod.neoemf.data.mapping.ManyReferenceMergedAs;
import fr.inria.atlanmod.neoemf.data.mapping.ManyValueWithArrays;
import fr.inria.atlanmod.neoemf.data.mapping.ReferenceAs;

import org.apache.hadoop.hbase.client.Table;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static java.util.Objects.nonNull;

/**
 * A {@link HBaseBackend} that use a {@link ManyValueWithArrays} mapping for storing attributes and {@link
 * ReferenceAs}/{@link ManyReferenceMergedAs} mappings for storing references.
 *
 * @see HBaseBackendFactory
 */
@ParametersAreNonnullByDefault
class DefaultHBaseBackend extends AbstractHBaseBackend implements ReferenceAs<String>, ManyValueWithArrays, ManyReferenceMergedAs<String> {

    /**
     * The {@link String} used to delimit multi-valued references.
     */
    @Nonnull
    private static final String DELIMITER = ",";

    /**
     * The {@link Converter} used to convert single-valued references.
     */
    @Nonnull
    private static final Converter<Id, String> SINGLE_CONVERTER = Converter.from(
            Id::toString,
            IdProvider::create);

    /**
     * The {@link Converter} used to convert multi-valued references.
     */
    @Nonnull
    private static final Converter<List<Id>, String> MANY_CONVERTER = Converter.from(
            rs -> rs.stream().map(r -> nonNull(r) ? SINGLE_CONVERTER.convert(r) : null).map(Strings::nullToEmpty).collect(Collectors.joining(DELIMITER)),
            r -> Arrays.stream(r.split(DELIMITER)).map(Strings::emptyToNull).map(SINGLE_CONVERTER::revert).collect(Collectors.toList()));

    /**
     * Constructs a new {@code HBaseBackendArrays} on th given {@code table}.
     *
     * @param table the HBase table
     */
    protected DefaultHBaseBackend(Table table) {
        super(table);
    }

    @Nonnull
    @Override
    public Converter<Id, String> referenceConverter() {
        return SINGLE_CONVERTER;
    }

    @Nonnull
    @Override
    public Converter<List<Id>, String> manyReferenceMerger() {
        return MANY_CONVERTER;
    }
}