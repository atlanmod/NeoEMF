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
import fr.inria.atlanmod.neoemf.data.query.CommonQueries;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.functions.Functions;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;
import static fr.inria.atlanmod.commons.Preconditions.checkPositionIndex;
import static java.util.Objects.isNull;

/**
 * A {@link ManyReferenceMapper} that provides a default behavior to use {@link M} instead of a set of {@link Id} for
 * multi-valued references.
 * <p>
 * This mapper merges the multi-valued references into a single value.
 *
 * @param <M> the type of the multi-valued reference after mapping
 */
@ParametersAreNonnullByDefault
public interface ManyReferenceMergedAs<M> extends ValueMapper, ManyReferenceMapper {

    @Nonnull
    @Override
    default Maybe<Id> referenceOf(ManyFeatureBean key) {
        Converter<List<Id>, M> converter = manyReferenceMerger();

        return this.<M>valueOf(key.withoutPosition())
                .map(converter::revert)
                .filter(rs -> key.position() < rs.size())
                .map(rs -> rs.get(key.position()))
                .cache();
    }

    @Nonnull
    @Override
    default Flowable<Id> allReferencesOf(SingleFeatureBean key) {
        Converter<List<Id>, M> converter = manyReferenceMerger();

        return this.<M>valueOf(key)
                .map(converter::revert)
                .flattenAsFlowable(Functions.identity())
                .cache();
    }

    @Nonnull
    @Override
    default Completable referenceFor(ManyFeatureBean key, Id reference) {
        checkNotNull(key, "key");
        checkNotNull(reference, "reference");

        Converter<List<Id>, M> converter = manyReferenceMerger();

        Consumer<List<Id>> replaceFunc = rs -> {
            rs.set(key.position(), reference);
            valueFor(key.withoutPosition(), converter.convert(rs)).subscribe();
        };

        return this.<M>valueOf(key.withoutPosition())
                .map(converter::revert)
                .filter(rs -> key.position() < rs.size())
                .toSingle()
                .doOnSuccess(replaceFunc)
                .toCompletable()
                .cache();
    }

    @Nonnull
    @Override
    default Completable addReference(ManyFeatureBean key, Id reference) {
        checkNotNull(key, "key");
        checkNotNull(reference, "reference");

        Converter<List<Id>, M> converter = manyReferenceMerger();

        List<Id> rs = this.<M>valueOf(key.withoutPosition())
                .to(CommonQueries::toOptional)
                .map(converter::revert)
                .orElseGet(ArrayList::new);

        checkPositionIndex(key.position(), rs.size());

        rs.add(key.position(), reference);

        return valueFor(key.withoutPosition(), converter.convert(rs));
    }

    @Nonnull
    @Override
    default Completable addAllReferences(ManyFeatureBean key, List<Id> references) {
        checkNotNull(key, "key");
        checkNotNull(references, "collection");

        if (references.isEmpty()) {
            return Completable.complete();
        }

        if (references.stream().anyMatch(Objects::isNull)) {
            throw new NullPointerException();
        }

        Converter<List<Id>, M> converter = manyReferenceMerger();

        List<Id> rs = this.<M>valueOf(key.withoutPosition())
                .to(CommonQueries::toOptional)
                .map(converter::revert)
                .orElseGet(ArrayList::new);

        checkPositionIndex(key.position(), rs.size());

        rs.addAll(key.position(), references);

        return valueFor(key.withoutPosition(), converter.convert(rs));
    }

    @Nonnull
    @Override
    default Maybe<Id> removeReference(ManyFeatureBean key) {
        checkNotNull(key, "key");

        Converter<List<Id>, M> converter = manyReferenceMerger();

        List<Id> rs = this.<M>valueOf(key.withoutPosition())
                .to(CommonQueries::toOptional)
                .map(converter::revert)
                .orElse(null);

        if (isNull(rs)) {
            return Maybe.empty();
        }

        Optional<Id> previousReference = Optional.empty();

        if (key.position() < rs.size()) {
            previousReference = Optional.of(rs.remove(key.position()));

            if (rs.isEmpty()) {
                removeAllReferences(key.withoutPosition()).blockingAwait();
            }
            else {
                valueFor(key.withoutPosition(), converter.convert(rs)).blockingAwait();
            }
        }

        return previousReference
                .map(Maybe::just)
                .orElseGet(Maybe::empty);
    }

    @Nonnull
    @Override
    default Completable removeAllReferences(SingleFeatureBean key) {
        return removeReference(key);
    }

    @Nonnull
    @Override
    default Maybe<Integer> sizeOfReference(SingleFeatureBean key) {
        Converter<List<Id>, M> converter = manyReferenceMerger();

        return this.<M>valueOf(key)
                .map(converter::revert)
                .map(List::size)
                .filter(s -> s > 0)
                .cache();
    }

    /**
     * Returns the converter used to transform a ordered list of references to the desired type.
     *
     * @return the conveter
     */
    @Nonnull
    Converter<List<Id>, M> manyReferenceMerger();
}
