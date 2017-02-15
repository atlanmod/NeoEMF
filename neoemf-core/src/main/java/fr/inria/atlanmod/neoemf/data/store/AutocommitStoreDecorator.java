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

import fr.inria.atlanmod.neoemf.util.logging.NeoLogger;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.InternalEObject.EStore;

/**
 * A {@link PersistentStore} wrapper that automatically saves modifications as calls are made.
 */
public class AutocommitStoreDecorator extends AbstractPersistentStoreDecorator {

    /**
     * Default number of allowed modifications (100000) between commits on the underlying {@link EStore}.
     */
    private static final long DEFAULT_AUTOCOMMIT_CHUNK = 100_000;

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
        NeoLogger.info("{0} chunk = {1}", getClass().getSimpleName(), autocommitChuck);
    }

    /**
     * Constructs a new {@code AutocommitStoreDecorator} with the default number of modifications between commits.
     *
     * @param store the underlying store
     */
    public AutocommitStoreDecorator(PersistentStore store) {
        this(store, DEFAULT_AUTOCOMMIT_CHUNK);
    }

    @Override
    public Object set(InternalEObject internalObject, EStructuralFeature feature, int index, Object value) {
        Object previous = super.set(internalObject, feature, index, value);
        incrementAndCommit();
        return previous;
    }

    @Override
    public void unset(InternalEObject internalObject, EStructuralFeature feature) {
        super.unset(internalObject, feature);
        incrementAndCommit();
    }

    @Override
    public void add(InternalEObject internalObject, EStructuralFeature feature, int index, Object value) {
        super.add(internalObject, feature, index, value);
        incrementAndCommit();
    }

    @Override
    public Object remove(InternalEObject internalObject, EStructuralFeature feature, int index) {
        Object previous = super.remove(internalObject, feature, index);
        incrementAndCommit();
        return previous;
    }

    @Override
    public Object move(InternalEObject internalObject, EStructuralFeature feature, int targetIndex, int sourceIndex) {
        Object previous = super.move(internalObject, feature, targetIndex, sourceIndex);
        incrementAndCommit();
        return previous;
    }

    @Override
    public void clear(InternalEObject internalObject, EStructuralFeature feature) {
        super.clear(internalObject, feature);
        incrementAndCommit();
    }

    /**
     * Increments the number of operation, and commits if necessary, i.e when {@code opCount % opsBetweenCommits == 0}.
     */
    private void incrementAndCommit() {
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
        catch (Exception e) {
            NeoLogger.error(e);
        }
        finally {
            super.finalize();
        }
    }
}
