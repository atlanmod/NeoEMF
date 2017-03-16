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

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.structure.ClassDescriptor;
import fr.inria.atlanmod.neoemf.data.structure.ContainerDescriptor;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.ManyFeatureKey;
import fr.inria.atlanmod.neoemf.util.log.Log;

import org.eclipse.emf.ecore.InternalEObject.EStore;

import java.util.Optional;
import java.util.concurrent.Callable;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link Store} wrapper that automatically saves modifications as calls are made.
 */
@ParametersAreNonnullByDefault
@SuppressWarnings("MethodDoesntCallSuperMethod")
public class AutoSaveStoreDecorator extends AbstractStoreDecorator {

    /**
     * Number of allowed modifications between saves on the underlying {@link EStore} for this store.
     */
    private final long autoSaveChunk;

    /**
     * Current number of modifications modulo {@link #autoSaveChunk}.
     */
    private long modificationsCount = 0L;

    /**
     * Constructs a new {@code AutoSaveStoreDecorator} with the given {@code chunk}.
     *
     * @param store         the inner store
     * @param autoSaveChunk the number of modifications between saves
     */
    public AutoSaveStoreDecorator(Store store, long autoSaveChunk) {
        super(store);
        this.autoSaveChunk = autoSaveChunk;
        Log.debug("{0} chunk = {1}", getClass().getSimpleName(), autoSaveChunk);
    }

    /**
     * Constructs a new {@code AutoSaveStoreDecorator} with the default number of modifications between saves.
     *
     * @param store the underlying store
     */
    public AutoSaveStoreDecorator(Store store) {
        this(store, 100_000);
    }

    @Override
    public void containerFor(Id id, ContainerDescriptor container) {
        thenIncrementAndSave(() -> super.containerFor(id, container));
    }

    @Override
    public void metaclassFor(Id id, ClassDescriptor metaclass) {
        thenIncrementAndSave(() -> super.metaclassFor(id, metaclass));
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(FeatureKey key, V value) {
        return thenIncrementAndSave(() -> super.valueFor(key, value));
    }

    @Override
    public <V> void unsetValue(FeatureKey key) {
        thenIncrementAndSave(() -> super.unsetValue(key));
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(FeatureKey key, Id reference) {
        return thenIncrementAndSave(() -> super.referenceFor(key, reference));
    }

    @Override
    public void unsetReference(FeatureKey key) {
        thenIncrementAndSave(() -> super.unsetReference(key));
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(ManyFeatureKey key, V value) {
        return thenIncrementAndSave(() -> super.valueFor(key, value));
    }

    @Override
    public <V> void addValue(ManyFeatureKey key, V value) {
        thenIncrementAndSave(() -> super.addValue(key, value));
    }

    @Override
    public <V> void appendValue(FeatureKey key, V value) {
        thenIncrementAndSave(() -> super.appendValue(key, value));
    }

    @Nonnull
    @Override
    public <V> Optional<V> removeValue(ManyFeatureKey key) {
        return thenIncrementAndSave(() -> super.removeValue(key));
    }

    @Override
    public <V> void removeAllValues(FeatureKey key) {
        thenIncrementAndSave(() -> super.removeAllValues(key));
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(ManyFeatureKey key, Id reference) {
        return thenIncrementAndSave(() -> super.referenceFor(key, reference));
    }

    @Override
    public void addReference(ManyFeatureKey key, Id reference) {
        thenIncrementAndSave(() -> super.addReference(key, reference));
    }

    @Override
    public void appendReference(FeatureKey key, Id reference) {
        thenIncrementAndSave(() -> super.appendReference(key, reference));
    }

    @Nonnull
    @Override
    public Optional<Id> removeReference(ManyFeatureKey key) {
        return thenIncrementAndSave(() -> super.removeReference(key));
    }

    @Override
    public void removeAllReferences(FeatureKey key) {
        thenIncrementAndSave(() -> super.removeAllReferences(key));
    }

    /**
     * Calls the given {@code method}, and increments the number of operation, and saves if necessary, i.e when
     * {@code count % chunk == 0}.
     *
     * @param method the method to call before saving
     *
     * @return the result of the {@code method}
     */
    private <V> V thenIncrementAndSave(Callable<V> method) {
        V result;
        try {
            result = method.call();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

        incremendAndSave();

        return result;
    }

    /**
     * Calls the given {@code method}, and increments the number of operation, and saves if necessary, i.e when
     * {@code count % chunk == 0}.
     *
     * @param method the method to call before saving
     */
    private void thenIncrementAndSave(Runnable method) {
        method.run();
        incremendAndSave();
    }

    /**
     * Increments the number of operation, and saves if necessary, i.e when {@code count % chunk == 0}.
     */
    private void incremendAndSave() {
        modificationsCount = (modificationsCount + 1) % autoSaveChunk;
        if (modificationsCount == 0) {
            this.save();
        }
    }

    /**
     * {@inheritDoc}
     * <p>
     * In our case, it saves the last modifications.
     */
    @Override
    protected void finalize() throws Throwable {
        try {
            this.save();
        }
        catch (Exception ignored) {
        }
        finally {
            super.finalize();
        }
    }

    @Override
    public boolean isAutoSave() {
        return true;
    }
}
