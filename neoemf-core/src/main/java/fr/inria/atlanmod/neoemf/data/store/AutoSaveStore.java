/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.store;

import fr.inria.atlanmod.commons.annotation.VisibleForReflection;
import fr.inria.atlanmod.commons.log.Log;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;
import fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;

import org.eclipse.emf.ecore.InternalEObject.EStore;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Supplier;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.internal.functions.Functions;

/**
 * A {@link Store} wrapper that automatically saves modifications as calls are made.
 */
@ParametersAreNonnullByDefault
@SuppressWarnings({"unused", "MethodDoesntCallSuperMethod"}) // Called dynamically
public class AutoSaveStore extends AbstractStore {

    /**
     * The default number of allowed changes between saves.
     */
    @Nonnegative
    public static final long DEFAULT_CHUNK = Runtime.getRuntime().maxMemory() / (long) Math.pow(2, 15);

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

    @Override
    public void close() {
        save();
        super.close();
    }

    @Override
    public void save() {
        try {
            super.save();
        }
        catch (Exception e) {
            Log.warn(e);
        }
    }

    @Nonnull
    @Override
    public Completable containerFor(Id id, SingleFeatureBean container) {
        return super.containerFor(id, container)
                .doOnComplete(this::incrementAndSave)
                .cache();
    }

    @Nonnull
    @Override
    public Completable removeContainer(Id id) {
        return super.removeContainer(id)
                .doOnComplete(this::incrementAndSave)
                .cache();
    }

    @Nonnull
    @Override
    public Completable metaClassFor(Id id, ClassBean metaClass) {
        return super.metaClassFor(id, metaClass)
                .doOnComplete(this::incrementAndSave)
                .cache();
    }

    @Nonnull
    @Override
    public <V> Maybe<V> valueFor(SingleFeatureBean key, V value) {
        return super.valueFor(key, value)
                .doOnComplete(this::incrementAndSave)
                .doOnSuccess(Functions.actionConsumer(this::incrementAndSave))
                .cache();
    }

    @Nonnull
    @Override
    public Completable removeValue(SingleFeatureBean key) {
        return super.removeValue(key)
                .doOnComplete(this::incrementAndSave)
                .cache();
    }

    @Nonnull
    @Override
    public Maybe<Id> referenceFor(SingleFeatureBean key, Id reference) {
        return super.referenceFor(key, reference)
                .doOnComplete(this::incrementAndSave)
                .doOnSuccess(Functions.actionConsumer(this::incrementAndSave))
                .cache();
    }

    @Nonnull
    @Override
    public Completable removeReference(SingleFeatureBean key) {
        return super.removeReference(key)
                .doOnComplete(this::incrementAndSave)
                .cache();
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(ManyFeatureBean key, V value) {
        return thenIncrementAndSave(() -> super.valueFor(key, value), 1);
    }

    @Override
    public <V> void addValue(ManyFeatureBean key, V value) {
        thenIncrementAndSave(() -> super.addValue(key, value), 1);
    }

    @Override
    public <V> void addAllValues(ManyFeatureBean key, List<? extends V> collection) {
        thenIncrementAndSave(() -> super.addAllValues(key, collection), collection.size());
    }

    @Nonnegative
    @Override
    public <V> int appendValue(SingleFeatureBean key, V value) {
        return thenIncrementAndSave(() -> super.appendValue(key, value), 1);
    }

    @Nonnegative
    @Override
    public <V> int appendAllValues(SingleFeatureBean key, List<? extends V> collection) {
        return thenIncrementAndSave(() -> super.appendAllValues(key, collection), collection.size());
    }

    @Nonnull
    @Override
    public <V> Optional<V> removeValue(ManyFeatureBean key) {
        return thenIncrementAndSave(() -> super.removeValue(key), 1);
    }

    @Override
    public void removeAllValues(SingleFeatureBean key) {
        thenIncrementAndSave(() -> super.removeAllValues(key), sizeOfValue(key).orElse(0));
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(ManyFeatureBean key, Id reference) {
        return thenIncrementAndSave(() -> super.referenceFor(key, reference), 1);
    }

    @Override
    public void addReference(ManyFeatureBean key, Id reference) {
        thenIncrementAndSave(() -> super.addReference(key, reference), 1);
    }

    @Override
    public void addAllReferences(ManyFeatureBean key, List<Id> collection) {
        thenIncrementAndSave(() -> super.addAllReferences(key, collection), collection.size());
    }

    @Nonnegative
    @Override
    public int appendReference(SingleFeatureBean key, Id reference) {
        return thenIncrementAndSave(() -> super.appendReference(key, reference), 1);
    }

    @Nonnegative
    @Override
    public int appendAllReferences(SingleFeatureBean key, List<Id> collection) {
        return thenIncrementAndSave(() -> super.appendAllReferences(key, collection), collection.size());
    }

    @Nonnull
    @Override
    public Optional<Id> removeReference(ManyFeatureBean key) {
        return thenIncrementAndSave(() -> super.removeReference(key), 1);
    }

    @Override
    public void removeAllReferences(SingleFeatureBean key) {
        thenIncrementAndSave(() -> super.removeAllReferences(key), sizeOfReference(key).orElse(0));
    }

    /**
     * Calls the given {@code method}, and increments the number of operation, and saves if necessary, i.e when {@code
     * count % chunk == 0}.
     *
     * @param method the method to call before saving
     * @param count  the number of changes made
     *
     * @return the result of the {@code method}
     *
     * @see #incrementAndSave(int)
     */
    private <V> V thenIncrementAndSave(Supplier<V> method, int count) {
        V result = method.get();
        incrementAndSave(count);
        return result;
    }

    /**
     * Calls the given {@code method}, and increments the number of operation, and saves if necessary, i.e when {@code
     * count % chunk == 0}.
     *
     * @param method the method to call before saving
     * @param count  the number of changes made
     *
     * @see #incrementAndSave(int)
     */
    private void thenIncrementAndSave(Runnable method, int count) {
        method.run();
        incrementAndSave(count);
    }

    /**
     * Increments the number of operation by one, and saves if necessary, i.e when {@code count % chunk == 0}.
     */
    private void incrementAndSave() {
        incrementAndSave(1);
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
            this.count.set(0L);
            save();
        }
    }
}
