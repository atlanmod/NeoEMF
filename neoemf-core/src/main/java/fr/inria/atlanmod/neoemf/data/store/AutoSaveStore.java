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

package fr.inria.atlanmod.neoemf.data.store;

import fr.inria.atlanmod.commons.annotation.VisibleForReflection;
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
@SuppressWarnings({"unused", "MethodDoesntCallSuperMethod"}) // Called dynamically
public class AutoSaveStore extends AbstractStore {

    /**
     * Number of allowed changes between saves on the underlying {@link EStore} for this store.
     */
    private final long autoSaveChunk;

    /**
     * Current number of changes made since the last call of {@link #incremendAndSave(int)}.
     */
    private final AtomicLong changesCount = new AtomicLong();

    /**
     * Constructs a new {@code AutoSaveStore} with the given {@code chunk}.
     *
     * @param store         the inner store
     * @param autoSaveChunk the number of modifications between saves
     */
    @VisibleForReflection
    public AutoSaveStore(Store store, Long autoSaveChunk) {
        super(store);
        this.autoSaveChunk = autoSaveChunk;
    }

    /**
     * Constructs a new {@code AutoSaveStore} with the default number of modifications between saves.
     *
     * @param store the underlying store
     */
    @VisibleForReflection
    public AutoSaveStore(Store store) {
        this(store, Runtime.getRuntime().maxMemory() / (2048 * 10));
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
        catch (Exception ignored) {
        }
    }

    @Override
    public void containerFor(Id id, SingleFeatureBean container) {
        thenIncrementAndSave(() -> super.containerFor(id, container), 1);
    }

    @Override
    public void unsetContainer(Id id) {
        thenIncrementAndSave(() -> super.unsetContainer(id), 1);
    }

    @Override
    public boolean metaClassFor(Id id, ClassBean metaClass) {
        return thenIncrementAndSave(() -> super.metaClassFor(id, metaClass), 1);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(SingleFeatureBean key, V value) {
        return thenIncrementAndSave(() -> super.valueFor(key, value), 1);
    }

    @Override
    public <V> void unsetValue(SingleFeatureBean key) {
        thenIncrementAndSave(() -> super.unsetValue(key), 1);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(SingleFeatureBean key, Id reference) {
        return thenIncrementAndSave(() -> super.referenceFor(key, reference), 1);
    }

    @Override
    public void unsetReference(SingleFeatureBean key) {
        thenIncrementAndSave(() -> super.unsetReference(key), 1);
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
    public <V> void removeAllValues(SingleFeatureBean key) {
        thenIncrementAndSave(() -> super.removeAllValues(key), sizeOfValue(key).orElse(0));
    }

    @Nonnull
    @Override
    public <V> Optional<V> moveValue(ManyFeatureBean source, ManyFeatureBean target) {
        return thenIncrementAndSave(() -> super.moveValue(source, target), 2);
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

    @Nonnull
    @Override
    public Optional<Id> moveReference(ManyFeatureBean source, ManyFeatureBean target) {
        return thenIncrementAndSave(() -> super.moveReference(source, target), 2);
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
        if (changesCount.addAndGet(count) >= autoSaveChunk) {
            changesCount.set(0L);
            save();
        }
    }
}
