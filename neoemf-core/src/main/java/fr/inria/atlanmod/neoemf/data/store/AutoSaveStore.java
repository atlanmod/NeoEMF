/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.store;

import fr.inria.atlanmod.commons.annotation.VisibleForReflection;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;
import fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;

import org.eclipse.emf.ecore.InternalEObject.EStore;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.internal.functions.Functions;

/**
 * A {@link Store} wrapper that automatically saves modifications as calls are made.
 */
@ParametersAreNonnullByDefault
@SuppressWarnings("unused") // Called dynamically
public class AutoSaveStore extends AbstractStore {

    /**
     * The default number of allowed changes between saves.
     */
    @Nonnegative
    public static final long DEFAULT_CHUNK = Runtime.getRuntime().maxMemory() / (long) Math.pow(2, 15); // 1GB = ~250k

    /**
     * Number of allowed changes between saves on the underlying {@link EStore} for this store.
     */
    @Nonnegative
    private final long chunk;

    /**
     * Current number of changes made since the last call of {@link #incrementAndSave(int)}.
     */
    private final AtomicLong count = new AtomicLong();

    /**
     * Constructs a new {@code AutoSaveStore} with the given {@code chunk}.
     *
     * @param store the inner store
     * @param chunk the number of modifications between saves
     */
    @VisibleForReflection
    public AutoSaveStore(Store store, Long chunk) {
        super(store);
        this.chunk = chunk;
    }

    /**
     * Constructs a new {@code AutoSaveStore} with the default number of modifications between saves.
     *
     * @param store the underlying store
     */
    @VisibleForReflection
    public AutoSaveStore(Store store) {
        this(store, DEFAULT_CHUNK);
    }

    @Nonnull
    @Override
    public Completable save() {
        count.set(0L);

        return super.save()
                .onErrorComplete()
                .cache();
    }

    @Nonnull
    @Override
    public Completable containerFor(Id id, SingleFeatureBean container) {
        return super.containerFor(id, container)
                .doOnComplete(() -> incrementAndSave(2))
                .cache();
    }

    @Nonnull
    @Override
    public Completable removeContainer(Id id) {
        return super.removeContainer(id)
                .doOnComplete(() -> incrementAndSave(1))
                .cache();
    }

    @Nonnull
    @Override
    public Completable metaClassFor(Id id, ClassBean metaClass) {
        return super.metaClassFor(id, metaClass)
                .doOnComplete(() -> incrementAndSave(1))
                .cache();
    }

    @Nonnull
    @Override
    public <V> Completable valueFor(SingleFeatureBean key, V value) {
        return super.valueFor(key, value)
                .doOnComplete(() -> incrementAndSave(2))
                .cache();
    }

    @Nonnull
    @Override
    public Completable removeValue(SingleFeatureBean key) {
        return super.removeValue(key)
                .doOnComplete(() -> incrementAndSave(1))
                .cache();
    }

    @Nonnull
    @Override
    public Completable referenceFor(SingleFeatureBean key, Id reference) {
        return super.referenceFor(key, reference)
                .doOnComplete(() -> incrementAndSave(2))
                .cache();
    }

    @Nonnull
    @Override
    public Completable removeReference(SingleFeatureBean key) {
        return super.removeReference(key)
                .doOnComplete(() -> incrementAndSave(1))
                .cache();
    }

    @Nonnull
    @Override
    public <V> Completable valueFor(ManyFeatureBean key, V value) {
        return super.valueFor(key, value)
                .doOnComplete(() -> incrementAndSave(2))
                .cache();
    }

    @Nonnull
    @Override
    public <V> Completable addValue(ManyFeatureBean key, V value) {
        return super.addValue(key, value)
                .doOnComplete(() -> incrementAndSave(1))
                .cache();
    }

    @Nonnull
    @Override
    public <V> Completable addAllValues(ManyFeatureBean key, List<? extends V> values) {
        return super.addAllValues(key, values)
                .doOnComplete(() -> incrementAndSave(values.size()))
                .cache();
    }

    @Nonnull
    @Override
    public <V> Single<Integer> appendValue(SingleFeatureBean key, V value) {
        return super.appendValue(key, value)
                .doOnSuccess(Functions.actionConsumer(() -> incrementAndSave(1)))
                .cache();
    }

    @Nonnull
    @Override
    public <V> Single<Integer> appendAllValues(SingleFeatureBean key, List<? extends V> values) {
        return super.appendAllValues(key, values)
                .doOnSuccess(Functions.actionConsumer(() -> incrementAndSave(values.size())))
                .cache();
    }

    @Nonnull
    @Override
    public <V> Maybe<V> removeValue(ManyFeatureBean key) {
        return super.<V>removeValue(key)
                .doOnSuccess(Functions.actionConsumer(() -> incrementAndSave(1)))
                .cache();
    }

    @Nonnull
    @Override
    public Completable removeAllValues(SingleFeatureBean key) {
        // TODO Use async
        int previousSize = sizeOfValue(key)
                .toSingle(0)
                .blockingGet();

        return super.removeAllValues(key)
                .doOnComplete(() -> incrementAndSave(previousSize))
                .cache();
    }

    @Nonnull
    @Override
    public Completable referenceFor(ManyFeatureBean key, Id reference) {
        return super.referenceFor(key, reference)
                .doOnComplete(() -> incrementAndSave(2))
                .cache();
    }

    @Nonnull
    @Override
    public Completable addReference(ManyFeatureBean key, Id reference) {
        return super.addReference(key, reference)
                .doOnComplete(() -> incrementAndSave(1))
                .cache();
    }

    @Nonnull
    @Override
    public Completable addAllReferences(ManyFeatureBean key, List<Id> references) {
        return super.addAllReferences(key, references)
                .doOnComplete(() -> incrementAndSave(references.size()))
                .cache();
    }

    @Nonnull
    @Override
    public Single<Integer> appendReference(SingleFeatureBean key, Id reference) {
        return super.appendReference(key, reference)
                .doOnSuccess(Functions.actionConsumer(() -> incrementAndSave(1)))
                .cache();
    }

    @Nonnull
    @Override
    public Single<Integer> appendAllReferences(SingleFeatureBean key, List<Id> references) {
        return super.appendAllReferences(key, references)
                .doOnSuccess(Functions.actionConsumer(() -> incrementAndSave(references.size())))
                .cache();
    }

    @Nonnull
    @Override
    public Maybe<Id> removeReference(ManyFeatureBean key) {
        return super.removeReference(key)
                .doOnSuccess(Functions.actionConsumer(() -> incrementAndSave(1)))
                .cache();
    }

    @Nonnull
    @Override
    public Completable removeAllReferences(SingleFeatureBean key) {
        // TODO Use async
        int previousSize = sizeOfReference(key)
                .toSingle(0)
                .blockingGet();

        return super.removeAllReferences(key)
                .doOnComplete(() -> incrementAndSave(previousSize))
                .cache();
    }

    /**
     * Increments the number of operation, and saves if necessary, i.e when {@code count % chunk == 0}.
     *
     * @param count the number of changes made
     *
     * @see #save()
     */
    private void incrementAndSave(int count) {
        if (this.count.addAndGet(count) >= chunk) {
            save().subscribe();
        }
    }
}
