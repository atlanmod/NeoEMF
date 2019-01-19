/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.mapping;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;

import org.atlanmod.commons.function.Converter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link ManyReferenceMapper} that provides a default behavior to use {@code M} instead of {@link Id} for
 * multi-valued references.
 *
 * @param <M> the type of the references after mapping
 */
@ParametersAreNonnullByDefault
public interface ManyReferenceAs<M> extends ManyValueMapper, ManyReferenceMapper {

    @Nonnull
    @Override
    default Optional<Id> referenceOf(ManyFeatureBean feature) {
        Converter<Id, M> converter = manyReferenceConverter();

        return this.<M>valueOf(feature)
                .map(converter::revert);
    }

    @Nonnull
    @Override
    default Stream<Id> allReferencesOf(SingleFeatureBean feature) {
        Converter<Id, M> converter = manyReferenceConverter();

        return this.<M>allValuesOf(feature)
                .map(converter::revert);
    }

    @Nonnull
    @Override
    default Optional<Id> referenceFor(ManyFeatureBean feature, Id reference) {
        Converter<Id, M> converter = manyReferenceConverter();

        return this.valueFor(feature, converter.convert(reference))
                .map(converter::revert);
    }

    @Override
    default void addReference(ManyFeatureBean feature, Id reference) {
        Converter<Id, M> converter = manyReferenceConverter();

        this.addValue(feature, converter.convert(reference));
    }

    @Override
    default void addAllReferences(ManyFeatureBean feature, List<Id> collection) {
        Converter<Id, M> converter = manyReferenceConverter();

        this.addAllValues(feature, collection.stream().map(converter::convert).collect(Collectors.toList()));
    }

    @Override
    default int appendReference(SingleFeatureBean feature, Id reference) {
        Converter<Id, M> converter = manyReferenceConverter();

        return this.appendValue(feature, converter.convert(reference));
    }

    @Override
    default int appendAllReferences(SingleFeatureBean feature, List<Id> collection) {
        Converter<Id, M> converter = manyReferenceConverter();

        return this.appendAllValues(feature, collection.stream().map(converter::convert).collect(Collectors.toList()));
    }

    @Nonnull
    @Override
    default Optional<Id> removeReference(ManyFeatureBean feature) {
        Converter<Id, M> converter = manyReferenceConverter();

        return this.<M>removeValue(feature)
                .map(converter::revert);
    }

    @Override
    default void removeAllReferences(SingleFeatureBean feature) {
        this.removeAllValues(feature);
    }

    @Nonnull
    @Override
    default Optional<Integer> sizeOfReference(SingleFeatureBean feature) {
        return this.sizeOfValue(feature);
    }

    /**
     * Returns the converter used to transform a multi-valued reference to the desired type.
     *
     * @return the converter
     */
    @Nonnull
    Converter<Id, M> manyReferenceConverter();
}
