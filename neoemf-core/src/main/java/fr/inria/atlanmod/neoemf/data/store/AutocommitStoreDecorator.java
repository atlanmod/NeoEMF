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

import fr.inria.atlanmod.neoemf.logging.NeoLogger;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.InternalEObject.EStore;

public class AutocommitStoreDecorator extends AbstractPersistentStoreDecorator {

    /**
     * Default number of allowed modifications (100000) between commits on the underlying {@link EStore}.
     */
    private static final long OPS_BETWEEN_COMMITS_DEFAULT = 100000;

    /**
     * Number of allowed modifications between commits on the underlying {@link EStore} for this
     * {@link AutocommitStoreDecorator}.
     */
    private final long opsBetweenCommits;

    /**
     * Current number of modifications modulo {@link #opsBetweenCommits}.
     */
    private long opCount;

    /**
     * Allows to specify the number of allowed modification on the underlying {@link EStore} before saving automatically.
     */
    public AutocommitStoreDecorator(PersistentStore store, long opsBetweenCommits) {
        super(store);
        this.opCount = 0;
        this.opsBetweenCommits = opsBetweenCommits;
        NeoLogger.info("{0} chunk = {1}", getClass().getSimpleName(), opsBetweenCommits);
    }

    /**
     * Allows to make {@link #OPS_BETWEEN_COMMITS_DEFAULT} modifications on the underlying {@link EStore} before saving
     * automatically.
     */
    public AutocommitStoreDecorator(PersistentStore store) {
        this(store, OPS_BETWEEN_COMMITS_DEFAULT);
    }

    @Override
    public Object set(InternalEObject internalObject, EStructuralFeature feature, int index, Object value) {
        Object old = super.set(internalObject, feature, index, value);
        incrementAndCommit();
        return old;
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
        Object old = super.remove(internalObject, feature, index);
        incrementAndCommit();
        return old;
    }

    @Override
    public Object move(InternalEObject internalObject, EStructuralFeature feature, int targetIndex, int sourceIndex) {
        Object old = super.move(internalObject, feature, targetIndex, sourceIndex);
        incrementAndCommit();
        return old;
    }

    @Override
    public void clear(InternalEObject internalObject, EStructuralFeature feature) {
        super.clear(internalObject, feature);
        incrementAndCommit();
    }

    private void incrementAndCommit() {
        opCount = (opCount + 1) % opsBetweenCommits;
        if (opCount == 0) {
            this.save();
        }
    }

    @Override
    protected void finalize() throws Throwable {
        try {
            this.save();
        }
        catch (Exception e) {
            NeoLogger.error(e);
        }
        super.finalize();
    }
}
