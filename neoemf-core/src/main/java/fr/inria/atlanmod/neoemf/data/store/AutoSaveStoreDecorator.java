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

import fr.inria.atlanmod.common.log.Log;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.structure.ClassDescriptor;
import fr.inria.atlanmod.neoemf.data.structure.ManyFeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.SingleFeatureKey;

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
@SuppressWarnings("MethodDoesntCallSuperMethod")
public class AutoSaveStoreDecorator extends AbstractStoreDecorator {

    /**
     * Number of allowed changes between saves on the underlying {@link EStore} for this store.
     */
    private final long autoSaveChunk;

    /**
     * Current number of changes made since the last call of {@link #incremendAndSave(int)}.
     */
    private final AtomicLong changesCount = new AtomicLong();

    /**
     * Constructs a new {@code AutoSaveStoreDecorator} with the given {@code chunk}.
     *
     * @param store         the inner store
     * @param autoSaveChunk the number of modifications between saves
     */
    @SuppressWarnings("unused") // Called dynamically
    public AutoSaveStoreDecorator(Store store, Long autoSaveChunk) {
        super(store);
        this.autoSaveChunk = autoSaveChunk;
    }

    /**
     * Constructs a new {@code AutoSaveStoreDecorator} with the default number of modifications between saves.
     *
     * @param store the underlying store
     */
    @SuppressWarnings("unused") // Called dynamically
    public AutoSaveStoreDecorator(Store store) {
        this(store, 50_000L);
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
    public void containerFor(Id id, SingleFeatureKey container) {
        thenIncrementAndSave(() -> super.containerFor(id, container), 1);
    }

    @Override
    public void unsetContainer(Id id) {
        thenIncrementAndSave(() -> super.unsetContainer(id), 1);
    }

    @Override
    public void metaclassFor(Id id, ClassDescriptor metaclass) {
        thenIncrementAndSave(() -> super.metaclassFor(id, metaclass), 1);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(SingleFeatureKey key, V value) {
        return thenIncrementAndSave(() -> super.valueFor(key, value), 1);
    }

    @Override
    public <V> void unsetValue(SingleFeatureKey key) {
        thenIncrementAndSave(() -> super.unsetValue(key), 1);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(SingleFeatureKey key, Id reference) {
        return thenIncrementAndSave(() -> super.referenceFor(key, reference), 1);
    }

    @Override
    public void unsetReference(SingleFeatureKey key) {
        thenIncrementAndSave(() -> super.unsetReference(key), 1);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(ManyFeatureKey key, V value) {
        return thenIncrementAndSave(() -> super.valueFor(key, value), 1);
    }

    @Override
    public <V> void addValue(ManyFeatureKey key, V value) {
        thenIncrementAndSave(() -> super.addValue(key, value), 1);
    }

    @Nonnegative
    @Override
    public <V> int appendValue(SingleFeatureKey key, V value) {
        return thenIncrementAndSave(() -> super.appendValue(key, value), 1);
    }

    @Nonnegative
    @Override
    public <V> int appendAllValues(SingleFeatureKey key, List<V> values) {
        return thenIncrementAndSave(() -> super.appendAllValues(key, values), values.size());
    }

    @Nonnull
    @Override
    public <V> Optional<V> removeValue(ManyFeatureKey key) {
        return thenIncrementAndSave(() -> super.removeValue(key), 1);
    }

    @Override
    public <V> void removeAllValues(SingleFeatureKey key) {
        thenIncrementAndSave(() -> super.removeAllValues(key), sizeOfValue(key).orElse(0));
    }

    @Nonnull
    @Override
    public <V> Optional<V> moveValue(ManyFeatureKey source, ManyFeatureKey target) {
        return thenIncrementAndSave(() -> super.moveValue(source, target), 2);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(ManyFeatureKey key, Id reference) {
        return thenIncrementAndSave(() -> super.referenceFor(key, reference), 1);
    }

    @Override
    public void addReference(ManyFeatureKey key, Id reference) {
        thenIncrementAndSave(() -> super.addReference(key, reference), 1);
    }

    @Nonnegative
    @Override
    public int appendReference(SingleFeatureKey key, Id reference) {
        return thenIncrementAndSave(() -> super.appendReference(key, reference), 1);
    }

    @Nonnegative
    @Override
    public int appendAllReferences(SingleFeatureKey key, List<Id> references) {
        return thenIncrementAndSave(() -> super.appendAllReferences(key, references), references.size());
    }

    @Nonnull
    @Override
    public Optional<Id> removeReference(ManyFeatureKey key) {
        return thenIncrementAndSave(() -> super.removeReference(key), 1);
    }

    @Override
    public void removeAllReferences(SingleFeatureKey key) {
        thenIncrementAndSave(() -> super.removeAllReferences(key), sizeOfReference(key).orElse(0));
    }

    @Nonnull
    @Override
    public Optional<Id> moveReference(ManyFeatureKey source, ManyFeatureKey target) {
        return thenIncrementAndSave(() -> super.moveReference(source, target), 2);
    }

    @Override
    public boolean isAutoSave() {
        return true;
    }

    /**
     * Calls the given {@code method}, and increments the number of operation, and saves if necessary, i.e when
     * {@code count % chunk == 0}.
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
     * Calls the given {@code method}, and increments the number of operation, and saves if necessary, i.e when
     * {@code count % chunk == 0}.
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
            if (isPersistent()) {
                //noinspection ConstantConditions
                Log.debug("PersistentResource saved:   {0} (auto-save after {1} changes)", resource().getURI(), changesCount);
            }

            changesCount.set(0L);
            save();
        }
    }
}
