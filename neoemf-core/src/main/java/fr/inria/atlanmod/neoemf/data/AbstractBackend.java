/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data;

import fr.inria.atlanmod.commons.Stopwatch;
import fr.inria.atlanmod.commons.concurrent.MoreThreads;
import fr.inria.atlanmod.commons.function.Copier;
import fr.inria.atlanmod.commons.log.Log;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;
import fr.inria.atlanmod.neoemf.data.mapping.AbstractDataMapper;
import fr.inria.atlanmod.neoemf.data.mapping.DataMapper;
import fr.inria.atlanmod.neoemf.data.query.AsyncQueryDispatcher;
import fr.inria.atlanmod.neoemf.data.query.QueryDispatcher;
import fr.inria.atlanmod.neoemf.data.store.Store;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import io.reactivex.Completable;
import io.reactivex.Flowable;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;
import static java.util.Objects.isNull;

/**
 * An abstract {@link Backend} that provides a global behavior about the closure.
 */
@ParametersAreNonnullByDefault
public abstract class AbstractBackend extends AbstractDataMapper implements Backend {

    /**
     * A set that holds all active {@link Backend} instances.
     */
    @Nonnull
    private static final Set<AbstractBackend> ACTIVE_BACKENDS = new HashSet<>();

    static {
        MoreThreads.executeAtExit(() -> ACTIVE_BACKENDS.parallelStream().forEach(b -> b.close(false)));
    }

    /**
     * The unique name of this backend.
     */
    @Nullable
    protected final String name;

    /**
     * The query dispatcher associated with this backend.
     */
    @Nonnull
    private final QueryDispatcher dispatcher;

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
        this.dispatcher = new AsyncQueryDispatcher(name);

        if (isPersistent()) {
            ACTIVE_BACKENDS.add(this);
        }
    }

    /**
     * Returns the query dispatcher of this backend.
     *
     * @return the query dispatcher
     */
    @Nonnull
    protected QueryDispatcher dispatcher() {
        return dispatcher;
    }

    @Override
    public final void close() {
        close(true);
    }

    @Override
    public final synchronized void save() {
        if (!isClosed) {
            asyncSave().cache().subscribe();
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
    private synchronized void close(boolean clean) {
        if (isClosed) {
            return;
        }

        Log.debug("{0} is closing...", this);
        final Stopwatch stopwatch = Stopwatch.createStarted();

        try {
            asyncSave().andThen(asyncClose()).blockingAwait();
        }
        catch (RuntimeException e) {
            Log.warn(e.getMessage());
        }
        finally {
            isClosed = true;

            if (clean && isPersistent()) {
                ACTIVE_BACKENDS.remove(this);
            }

            try {
                dispatcher.close();
            }
            catch (IOException e) {
                Log.warn("An error occured when closing the query dispatcher", e);
            }

            Log.debug("{0} is now closed. Took {1}", this, stopwatch.stop().elapsed());
        }
    }

    /**
     * Asynchronously closes the database, and releases any system resources associated with it.
     *
     * @return the deferred computation
     */
    @Nonnull
    protected abstract Completable asyncClose();

    /**
     * Asynchronously saves the last modifications.
     *
     * @return the deferred computation
     */
    @Nonnull
    protected abstract Completable asyncSave();

    @Nonnull
    @Override
    public final Flowable<Id> allInstancesOf(ClassBean metaClass, boolean strict) {
        if ((metaClass.isAbstract() || metaClass.isInterface()) && strict) {
            return Flowable.empty();
        }

        Set<ClassBean> allInstances = strict ? new HashSet<>() : metaClass.inheritedBy();
        allInstances.add(metaClass);

        return allInstancesOf(allInstances);
    }

    @Override
    @SuppressWarnings("unchecked")
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
                innerCopyTo(target);
            }
            else {
                defaultCopyTo(target);
            }
        }
        catch (UnsupportedOperationException e) {
            defaultCopyTo(target);
        }
    }

    @SuppressWarnings("unchecked")
    private void defaultCopyTo(DataMapper target) {
        final String typeName = "fr.inria.atlanmod.neoemf.io.DirectDataCopier";

        try {
            final Class<Copier<DataMapper>> directCopierType = (Class<Copier<DataMapper>>) Class.forName(typeName);

            Copier<DataMapper> copier = directCopierType.newInstance();
            copier.copy(this, target);
        }
        catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            throw new UnsupportedOperationException(String.format("Unable to find class %s. Make sure the `neoemf-io` module is in the classpath", typeName));
        }
    }

    /**
     * Copies the content of this back-end to the {@code target}, using a specific implementation when the {@code
     * target} is an instance of this back-end.
     *
     * @param target the back-end where to store the copied content
     *
     * @throws UnsupportedOperationException if this back-end does not support the specific copy
     */
    protected void innerCopyTo(DataMapper target) {
        throw new UnsupportedOperationException(String.format("%s does not support specific copy", getClass().getName()));
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + (isNull(name) ? ("@" + hashCode()) : ("#" + name));
    }
}
