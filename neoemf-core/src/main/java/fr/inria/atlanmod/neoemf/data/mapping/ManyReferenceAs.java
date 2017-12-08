/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.mapping;

import fr.inria.atlanmod.commons.function.Converter;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * A {@link ManyReferenceMapper} that provides a default behavior to use {@link M} instead of {@link Id} for
 * multi-valued references.
 *
 * @param <M> the type of the references after mapping
 */
@ParametersAreNonnullByDefault
public interface ManyReferenceAs<M> extends ManyValueMapper, ManyReferenceMapper {

    @Nonnull
    @Override
    default Maybe<Id> referenceOf(ManyFeatureBean key) {
        Converter<Id, M> converter = manyReferenceConverter();

        return this.<M>valueOf(key)
                .map(converter::revert)
                .cache();
    }

    @Nonnull
    @Override
    default Flowable<Id> allReferencesOf(SingleFeatureBean key) {
        Converter<Id, M> converter = manyReferenceConverter();

        return this.<M>allValuesOf(key)
                .map(converter::revert)
                .cache();
    }

    @Nonnull
    @Override
    default Completable referenceFor(ManyFeatureBean key, Id reference) {
        Converter<Id, M> converter = manyReferenceConverter();

        return this.valueFor(key, converter.convert(reference));
    }

    @Nonnull
    @Override
    default Completable addReference(ManyFeatureBean key, Id reference) {
        Converter<Id, M> converter = manyReferenceConverter();

        return this.addValue(key, converter.convert(reference));
    }

    @Nonnull
    @Override
    default Completable addAllReferences(ManyFeatureBean key, List<Id> references) {
        Converter<Id, M> converter = manyReferenceConverter();

        List<M> mappedReferences = references
                .stream()
                .map(converter::convert)
                .collect(Collectors.toList());

        return this.addAllValues(key, mappedReferences);
    }

    @Nonnull
    @Override
    default Single<Integer> appendReference(SingleFeatureBean key, Id reference) {
        Converter<Id, M> converter = manyReferenceConverter();

        return this.appendValue(key, converter.convert(reference));
    }

    @Nonnull
    @Override
    default Single<Integer> appendAllReferences(SingleFeatureBean key, List<Id> references) {
        Converter<Id, M> converter = manyReferenceConverter();

        List<M> mappedReferences = references.stream()
                .map(converter::convert)
                .collect(Collectors.toList());

        return this.appendAllValues(key, mappedReferences);
    }

    @Nonnull
    @Override
    default Single<Boolean> removeReference(ManyFeatureBean key) {
        return this.removeValue(key);
    }

    @Nonnull
    @Override
    default Completable removeAllReferences(SingleFeatureBean key) {
        return this.removeAllValues(key);
    }

    @Nonnull
    @Override
    default Maybe<Integer> sizeOfReference(SingleFeatureBean key) {
        return this.sizeOfValue(key);
    }

    /**
     * Returns the converter used to transform a multi-valued reference to the desired type.
     *
     * @return the converter
     */
    @Nonnull
    Converter<Id, M> manyReferenceConverter();
}
