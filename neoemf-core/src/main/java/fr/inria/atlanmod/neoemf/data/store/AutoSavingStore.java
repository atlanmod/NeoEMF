/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.store;

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

/**
 * A {@link Store} wrapper that automatically saves modifications as calls are made.
 */
@ParametersAreNonnullByDefault
public class AutoSavingStore extends AbstractStore {

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
     * Current number of changes made since the last call of {@link #incremendAndSave(int)}.
     */
    private final AtomicLong count = new AtomicLong();

    /**
     * Constructs a new {@code AutoSavingStore} with the given {@code chunk}.
     *
     * @param chunk the number of modifications between saves
     */
    public AutoSavingStore(Long chunk) {
        super(0);
        this.chunk = chunk;
    }

    /**
     * Constructs a new {@code AutoSavingStore} with the default number of modifications between saves.
     */
    public AutoSavingStore() {
        this(DEFAULT_CHUNK);
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

    @Override
    public void containerFor(Id id, SingleFeatureBean container) {
        thenIncrementAndSave(() -> super.containerFor(id, container), 1);
    }

    @Override
    public void removeContainer(Id id) {
        thenIncrementAndSave(() -> super.removeContainer(id), 1);
    }

    @Override
    public boolean metaClassFor(Id id, ClassBean metaClass) {
        return thenIncrementAndSave(() -> super.metaClassFor(id, metaClass), 1);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(SingleFeatureBean feature, V value) {
        return thenIncrementAndSave(() -> super.valueFor(feature, value), 1);
    }

    @Override
    public void removeValue(SingleFeatureBean feature) {
        thenIncrementAndSave(() -> super.removeValue(feature), 1);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(SingleFeatureBean feature, Id reference) {
        return thenIncrementAndSave(() -> super.referenceFor(feature, reference), 1);
    }

    @Override
    public void removeReference(SingleFeatureBean feature) {
        thenIncrementAndSave(() -> super.removeReference(feature), 1);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(ManyFeatureBean feature, V value) {
        return thenIncrementAndSave(() -> super.valueFor(feature, value), 1);
    }

    @Override
    public <V> void addValue(ManyFeatureBean feature, V value) {
        thenIncrementAndSave(() -> super.addValue(feature, value), 1);
    }

    @Override
    public <V> void addAllValues(ManyFeatureBean feature, List<? extends V> values) {
        thenIncrementAndSave(() -> super.addAllValues(feature, values), values.size());
    }

    @Nonnegative
    @Override
    public <V> int appendValue(SingleFeatureBean feature, V value) {
        return thenIncrementAndSave(() -> super.appendValue(feature, value), 1);
    }

    @Nonnegative
    @Override
    public <V> int appendAllValues(SingleFeatureBean feature, List<? extends V> values) {
        return thenIncrementAndSave(() -> super.appendAllValues(feature, values), values.size());
    }

    @Nonnull
    @Override
    public <V> Optional<V> removeValue(ManyFeatureBean feature) {
        return thenIncrementAndSave(() -> super.removeValue(feature), 1);
    }

    @Override
    public void removeAllValues(SingleFeatureBean feature) {
        thenIncrementAndSave(() -> super.removeAllValues(feature), sizeOfValue(feature).orElse(0));
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(ManyFeatureBean feature, Id reference) {
        return thenIncrementAndSave(() -> super.referenceFor(feature, reference), 1);
    }

    @Override
    public void addReference(ManyFeatureBean feature, Id reference) {
        thenIncrementAndSave(() -> super.addReference(feature, reference), 1);
    }

    @Override
    public void addAllReferences(ManyFeatureBean feature, List<Id> references) {
        thenIncrementAndSave(() -> super.addAllReferences(feature, references), references.size());
    }

    @Nonnegative
    @Override
    public int appendReference(SingleFeatureBean feature, Id reference) {
        return thenIncrementAndSave(() -> super.appendReference(feature, reference), 1);
    }

    @Nonnegative
    @Override
    public int appendAllReferences(SingleFeatureBean feature, List<Id> references) {
        return thenIncrementAndSave(() -> super.appendAllReferences(feature, references), references.size());
    }

    @Nonnull
    @Override
    public Optional<Id> removeReference(ManyFeatureBean feature) {
        return thenIncrementAndSave(() -> super.removeReference(feature), 1);
    }

    @Override
    public void removeAllReferences(SingleFeatureBean feature) {
        thenIncrementAndSave(() -> super.removeAllReferences(feature), sizeOfReference(feature).orElse(0));
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
     * @see #incremendAndSave(int)
     */
    private <V> V thenIncrementAndSave(Supplier<V> method, int count) {
        V result = method.get();
        incremendAndSave(count);
        return result;
    }

    /**
     * Calls the given {@code method}, and increments the number of operation, and saves if necessary, i.e when {@code
     * count % chunk == 0}.
     *
     * @param method the method to call before saving
     * @param count  the number of changes made
     *
     * @see #incremendAndSave(int)
     */
    private void thenIncrementAndSave(Runnable method, int count) {
        method.run();
        incremendAndSave(count);
    }

    /**
     * Increments the number of operation, and saves if necessary, i.e when {@code count % chunk == 0}.
     *
     * @param count the number of changes made
     *
     * @see #save()
     */
    private void incremendAndSave(int count) {
        if (this.count.addAndGet(count) >= chunk) {
            this.count.set(0L);
            save();
        }
    }
}
