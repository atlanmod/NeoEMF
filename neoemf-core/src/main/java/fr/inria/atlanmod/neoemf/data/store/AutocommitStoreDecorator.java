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
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.ManyFeatureKey;
import fr.inria.atlanmod.neoemf.util.log.Log;

import org.eclipse.emf.ecore.InternalEObject.EStore;

import java.util.Optional;
import java.util.concurrent.Callable;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link PersistentStore} wrapper that automatically saves modifications as calls are made.
 */
@ParametersAreNonnullByDefault
@SuppressWarnings("MethodDoesntCallSuperMethod")
public class AutocommitStoreDecorator extends AbstractPersistentStoreDecorator<PersistentStore> {

    /**
     * Number of allowed modifications between commits on the underlying {@link EStore} for this store.
     */
    private final long autocommitChuck;

    /**
     * Current number of modifications modulo {@link #autocommitChuck}.
     */
    private long autocommitCount;

    /**
     * Constructs a new {@code AutocommitStoreDecorator} with the given {@code opsBetweenCommits}.
     *
     * @param store           the underlying store
     * @param autocommitChuck the number of modifications between commit
     */
    public AutocommitStoreDecorator(PersistentStore store, long autocommitChuck) {
        super(store);
        this.autocommitCount = 0;
        this.autocommitChuck = autocommitChuck;
        Log.info("{0} chunk = {1}", getClass().getSimpleName(), autocommitChuck);
    }

    /**
     * Constructs a new {@code AutocommitStoreDecorator} with the default number of modifications between commits.
     *
     * @param store the underlying store
     */
    public AutocommitStoreDecorator(PersistentStore store) {
        this(store, 100_000);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(FeatureKey key, V value) {
        return thenIncrementAndCommit(() -> super.valueFor(key, value));
    }

    @Override
    public <V> void unsetValue(FeatureKey key) {
        thenIncrementAndCommit(() -> super.unsetValue(key));
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(ManyFeatureKey key, V value) {
        return thenIncrementAndCommit(() -> super.valueFor(key, value));
    }

    @Override
    public <V> void addValue(ManyFeatureKey key, V value) {
        thenIncrementAndCommit(() -> super.addValue(key, value));
    }

    @Override
    public <V> void appendValue(FeatureKey key, V value) {
        thenIncrementAndCommit(() -> super.appendValue(key, value));
    }

    @Nonnull
    @Override
    public <V> Optional<V> removeValue(ManyFeatureKey key) {
        return thenIncrementAndCommit(() -> super.removeValue(key));
    }

    @Override
    public <V> void removeAllValues(FeatureKey key) {
        thenIncrementAndCommit(() -> super.removeAllValues(key));
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(FeatureKey key, Id reference) {
        return thenIncrementAndCommit(() -> super.referenceFor(key, reference));
    }

    @Override
    public void unsetReference(FeatureKey key) {
        thenIncrementAndCommit(() -> super.unsetReference(key));
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(ManyFeatureKey key, Id reference) {
        return thenIncrementAndCommit(() -> super.referenceFor(key, reference));
    }

    @Override
    public void addReference(ManyFeatureKey key, Id reference) {
        thenIncrementAndCommit(() -> super.addReference(key, reference));
    }

    @Override
    public void appendReference(FeatureKey key, Id reference) {
        thenIncrementAndCommit(() -> super.appendReference(key, reference));
    }

    @Nonnull
    @Override
    public Optional<Id> removeReference(ManyFeatureKey key) {
        return thenIncrementAndCommit(() -> super.removeReference(key));
    }

    @Override
    public void removeAllReferences(FeatureKey key) {
        thenIncrementAndCommit(() -> super.removeAllReferences(key));
    }

    /**
     * Calls the given {@code method}, and increments the number of operation, and commits if necessary, i.e when
     * {@code opCount % opsBetweenCommits == 0}.
     *
     * @param method the method to call before committing
     *
     * @return the result of the {@code method}
     */
    private <V> V thenIncrementAndCommit(Callable<V> method) {
        V result;
        try {
            result = method.call();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

        incremendAndCommit();

        return result;
    }

    /**
     * Calls the given {@code method}, and increments the number of operation, and commits if necessary, i.e when
     * {@code opCount % opsBetweenCommits == 0}.
     *
     * @param method the method to call before committing
     */
    private void thenIncrementAndCommit(Runnable method) {
        method.run();
        incremendAndCommit();
    }

    /**
     * Increments the number of operation, and commits if necessary, i.e when {@code opCount % opsBetweenCommits == 0}.
     */
    private void incremendAndCommit() {
        autocommitCount = (autocommitCount + 1) % autocommitChuck;
        if (autocommitCount == 0) {
            this.save();
        }
    }

    /**
     * {@inheritDoc}
     * <p>
     * In our case, it commits the last modifications.
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
}
