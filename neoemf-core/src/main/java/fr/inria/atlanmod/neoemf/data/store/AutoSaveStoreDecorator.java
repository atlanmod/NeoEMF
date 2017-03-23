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

import java.util.List;
import java.util.NoSuchElementException;
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
     * Number of allowed changes between saves on the underlying {@link EStore} for this store.
     */
    private final long autoSaveChunk;

    /**
     * Current number of changes made since the last call of {@link #incremendAndSave(int)}.
     */
    private long changesCount = 0L;

    /**
     * Constructs a new {@code AutoSaveStoreDecorator} with the given {@code chunk}.
     *
     * @param store         the inner store
     * @param autoSaveChunk the number of modifications between saves
     */
    public AutoSaveStoreDecorator(Store store, Long autoSaveChunk) {
        super(store);
        this.autoSaveChunk = autoSaveChunk;
    }

    /**
     * Constructs a new {@code AutoSaveStoreDecorator} with the default number of modifications between saves.
     *
     * @param store the underlying store
     */
    public AutoSaveStoreDecorator(Store store) {
        this(store, 50_000L);
    }

    @Override
    public void containerFor(Id id, ContainerDescriptor container) {
        thenIncrementAndSave(() -> super.containerFor(id, container), 1);
    }

    @Override
    public void metaclassFor(Id id, ClassDescriptor metaclass) {
        thenIncrementAndSave(() -> super.metaclassFor(id, metaclass), 1);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(FeatureKey key, V value) {
        return thenIncrementAndSave(() -> super.valueFor(key, value), 1);
    }

    @Override
    public <V> void unsetValue(FeatureKey key) {
        thenIncrementAndSave(() -> super.unsetValue(key), 1);
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(FeatureKey key, Id reference) {
        return thenIncrementAndSave(() -> super.referenceFor(key, reference), 1);
    }

    @Override
    public void unsetReference(FeatureKey key) {
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

    @Override
    public <V> void appendValue(FeatureKey key, V value) {
        thenIncrementAndSave(() -> super.appendValue(key, value), 1);
    }

    @Override
    public <V> void appendAllValues(FeatureKey key, List<V> values) {
        thenIncrementAndSave(() -> super.appendAllValues(key, values), values.size());
    }

    @Nonnull
    @Override
    public <V> Optional<V> removeValue(ManyFeatureKey key) {
        return thenIncrementAndSave(() -> super.removeValue(key), 1);
    }

    @Override
    public <V> void removeAllValues(FeatureKey key) {
        thenIncrementAndSave(() -> super.removeAllValues(key), sizeOfValue(key).orElse(0));
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

    @Override
    public void appendReference(FeatureKey key, Id reference) {
        thenIncrementAndSave(() -> super.appendReference(key, reference), 1);
    }

    @Override
    public void appendAllReferences(FeatureKey key, List<Id> references) {
        thenIncrementAndSave(() -> super.appendAllReferences(key, references), references.size());
    }

    @Nonnull
    @Override
    public Optional<Id> removeReference(ManyFeatureKey key) {
        return thenIncrementAndSave(() -> super.removeReference(key), 1);
    }

    @Override
    public void removeAllReferences(FeatureKey key) {
        thenIncrementAndSave(() -> super.removeAllReferences(key), sizeOfReference(key).orElse(0));
    }

    /**
     * @see #save()
     */
    @Override
    protected void finalize() throws Throwable {
        try {
            save();
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
    private <V> V thenIncrementAndSave(Callable<V> method, int count) {
        V result;
        try {
            result = method.call();
        }
        catch (NoSuchElementException | NullPointerException e) { // known exceptions
            throw e;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

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
        changesCount += count;

        if (changesCount >= autoSaveChunk) {
            if (isPersistent()) {
                //noinspection ConstantConditions
                Log.debug("PersistentResource saved:   {0} (auto-save after {1} changes)", resource().getURI(), changesCount);
            }

            changesCount = 0L;
            save();
        }
    }
}
