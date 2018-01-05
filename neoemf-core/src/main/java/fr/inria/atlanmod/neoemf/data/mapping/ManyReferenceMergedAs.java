/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
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

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.internal.functions.Functions;

import static fr.inria.atlanmod.commons.Preconditions.checkNotContainsNull;
import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;
import static fr.inria.atlanmod.commons.Preconditions.checkPositionIndex;

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

        return this.<M>valueOf(key.withoutPosition())
                .map(converter::revert)
                .filter(rs -> key.position() < rs.size())
                .toSingle()
                .flatMapCompletable(rs -> {
                    rs.set(key.position(), reference);
                    return valueFor(key.withoutPosition(), converter.convert(rs));
                })
                .cache();
    }

    @Nonnull
    @Override
    default Completable addReference(ManyFeatureBean key, Id reference) {
        checkNotNull(key, "key");
        checkNotNull(reference, "reference");

        Converter<List<Id>, M> converter = manyReferenceMerger();

        return this.<M>valueOf(key.withoutPosition())
                .map(converter::revert)
                .filter(rs -> {
                    checkPositionIndex(key.position(), rs.size());
                    return true;
                })
                .switchIfEmpty(Maybe.fromCallable(ArrayList::new))
                .flatMapCompletable(rs -> {
                    rs.add(key.position(), reference);
                    return valueFor(key.withoutPosition(), converter.convert(rs));
                })
                .cache();
    }

    @Nonnull
    @Override
    default Completable addAllReferences(ManyFeatureBean key, List<Id> references) {
        checkNotNull(key, "key");
        checkNotNull(references, "references");

        if (references.isEmpty()) {
            return Completable.complete();
        }

        checkNotContainsNull(references);

        Converter<List<Id>, M> converter = manyReferenceMerger();

        return this.<M>valueOf(key.withoutPosition())
                .map(converter::revert)
                .filter(rs -> {
                    checkPositionIndex(key.position(), rs.size());
                    return true;
                })
                .switchIfEmpty(Maybe.fromCallable(ArrayList::new))
                .flatMapCompletable(rs -> {
                    rs.addAll(key.position(), references);
                    return valueFor(key.withoutPosition(), converter.convert(rs));
                })
                .cache();
    }

    @Nonnull
    @Override
    default Single<Boolean> removeReference(ManyFeatureBean key) {
        checkNotNull(key, "key");

        Converter<List<Id>, M> converter = manyReferenceMerger();

        return this.<M>valueOf(key.withoutPosition())
                .map(converter::revert)
                .filter(rs -> key.position() < rs.size())
                .concatMap(rs -> {
                    Completable completable;
                    if (rs.size() == 1) {
                        completable = removeAllReferences(key.withoutPosition());
                    }
                    else {
                        rs.remove(key.position());
                        completable = valueFor(key.withoutPosition(), converter.convert(rs));
                    }
                    return completable.toSingleDefault(true).toMaybe();
                })
                .toSingle(false)
                .cache();
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
