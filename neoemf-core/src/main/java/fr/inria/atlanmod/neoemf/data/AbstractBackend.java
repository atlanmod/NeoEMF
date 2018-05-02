/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data;

import fr.inria.atlanmod.commons.log.Log;
import fr.inria.atlanmod.neoemf.config.BaseConfig;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;
import fr.inria.atlanmod.neoemf.data.mapping.AbstractDataMapper;
import fr.inria.atlanmod.neoemf.data.mapping.DataMapper;
import fr.inria.atlanmod.neoemf.data.store.Store;
import fr.inria.atlanmod.neoemf.data.store.StoreFactory;
import fr.inria.atlanmod.neoemf.util.service.ServiceResolver;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;

/**
 * An abstract {@link Backend} that provides a global behavior about the closure.
 */
@ParametersAreNonnullByDefault
public abstract class AbstractBackend extends AbstractDataMapper implements Backend {

    /**
     * The unique name of this backend.
     */
    @Nullable
    private final String name;

    /**
     * Whether this backend is closed.
     */
    private boolean isClosed;

    /**
     * Constructs a new {@code AbstractBackend}.
     */
    protected AbstractBackend() {
        this(null);
    }

    /**
     * Constructs a new {@code AbstractBackend} with the given {@code name}.
     *
     * @param name the unique name of this backend
     */
    protected AbstractBackend(@Nullable String name) {
        this.name = name;

        if (isPersistent()) {
            BackendManager.getInstance().register(this);
        }
    }

    @Override
    public final void close() {
        close(true);
    }

    @Override
    public final void save() {
        try {
            internalSave();
        }
        catch (Exception e) {
            Log.warn(e);
        }
    }

    /**
     * Cleanly closes this back-end, and releases any system resources associated with it. All modifications are saved
     * before closing.
     * <p>
     * If the back-end is already closed, then invoking this method has no effect.
     *
     * @param clean {@code true} if the registry must be cleaned after closure
     */
    void close(boolean clean) {
        if (isClosed) {
            return;
        }

        if (clean && isPersistent()) {
            BackendManager.getInstance().unregister(this);
        }

        try {
            save();
            internalClose();
        }
        catch (Exception e) {
            Log.warn(e);
        }
        finally {
            isClosed = true;
        }
    }

    /**
     * Cleanly closes the database, and releases any system resources associated with it.
     *
     * @throws IOException if an I/O error occurs when closing
     */
    protected abstract void internalClose() throws IOException;

    /**
     * Saves all modifications made on this back-end since the last call.
     *
     * @throws IOException if an I/O error occurs when saving
     */
    protected abstract void internalSave() throws IOException;

    @Nonnull
    @Override
    public final Iterable<Id> allInstancesOf(ClassBean metaClass, boolean strict) {
        if ((metaClass.isAbstract() || metaClass.isInterface()) && strict) {
            return Collections.emptySet();
        }

        Set<ClassBean> allInstances = strict ? new HashSet<>() : metaClass.inheritedBy();
        allInstances.add(metaClass);

        return allInstancesOf(allInstances);
    }

    // region Copy

    @Override
    public void copyTo(DataMapper target) {
        checkNotNull(target, "target");

        if (isDistributed()) {
            Log.warn("Copy of a distributed back-end may lead to unexpected errors");
        }

        if (Backend.class.isInstance(target)) {
            copyTo(Backend.class.cast(target));
        }
        else if (Store.class.isInstance(target)) {
            copyTo(Store.class.cast(target).backend());
        }
        else {
            throw new IllegalStateException(String.format("Unable to copy a DataMapper of type %s", target.getClass()));
        }
    }

    /**
     * Copies all the contents from this backend to the {@code target}.
     *
     * @param target the backend where to store the copied content
     */
    private void copyTo(Backend target) {
        // This back-end and the target are the same object
        if (equals(target)) {
            return;
        }

        try {
            if (getClass() == target.getClass()) {
                internalCopyTo(target);
            }
            else {
                defaultCopyTo(target);
            }
        }
        catch (UnsupportedOperationException e) {
            defaultCopyTo(target);
        }
    }

    /**
     * Copies all contents from this backend to the target using the direct copy.
     *
     * @param target the backend where to store the copied content
     */
    private void defaultCopyTo(Backend target) {
        final DataCopier copier = ServiceResolver.getInstance()
                .resolve(DataCopier.class)
                .findFirst()
                .<UnsupportedOperationException>orElseThrow(() -> new UnsupportedOperationException("Unable to find any DataCopier implementation"));

        Store store = StoreFactory.getInstance().createStore(target, new BaseConfig<>().autoSave());
        copier.copy(this, store);
    }

    /**
     * Copies the content of this back-end to the {@code target}, using a specific implementation when the {@code
     * target} is an instance of this back-end.
     *
     * @param target the back-end where to store the copied content
     *
     * @throws UnsupportedOperationException if this back-end does not support the specific copy
     */
    protected void internalCopyTo(DataMapper target) {
        throw new UnsupportedOperationException(String.format("%s does not support specific copy", getClass().getName()));
    }

    // endregion

    @Override
    public String toString() {
        final String identifier = Optional.ofNullable(name).map(n -> "#" + name).orElseGet(() -> "@" + hashCode());
        return getClass().getSimpleName() + identifier;
    }
}
