/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.resource;

import fr.inria.atlanmod.commons.collect.MoreIterables;
import fr.inria.atlanmod.commons.log.Log;
import fr.inria.atlanmod.neoemf.config.BaseConfig;
import fr.inria.atlanmod.neoemf.config.ImmutableConfig;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.core.internal.AllContentsIterator;
import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.BackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.data.InvalidBackend;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;
import fr.inria.atlanmod.neoemf.data.im.InMemoryBackendFactory;
import fr.inria.atlanmod.neoemf.data.im.config.InMemoryConfig;
import fr.inria.atlanmod.neoemf.data.store.Store;
import fr.inria.atlanmod.neoemf.data.store.StoreFactory;
import fr.inria.atlanmod.neoemf.data.store.adapter.PersistentStoreAdapter;
import fr.inria.atlanmod.neoemf.data.store.adapter.StoreAdapter;
import fr.inria.atlanmod.neoemf.resource.internal.RootContentsList;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * The default implementation of a {@link PersistentResource} that contains {@link
 * fr.inria.atlanmod.neoemf.core.PersistentEObject}.
 * <p>
 * {@code DefaultPersistentResource}s are backend-agnostic and only delegate model element operations to their internal
 * {@link fr.inria.atlanmod.neoemf.data.store.Store} which is responsible of database access.
 *
 * @see PersistentStoreAdapter
 */
@ParametersAreNonnullByDefault
public class DefaultPersistentResource extends ResourceImpl implements PersistentResource {

    /**
     * The {@link BackendFactory} associated to the {@link #uri}.
     */
    @Nonnull
    private final BackendFactory factory;

    /**
     * The {@link StoreAdapter} responsible of the database serialization.
     */
    @Nonnull
    private StoreAdapter eStore;

    /**
     * Constructs a new {@code DefaultPersistentResource} with the given {@code uri}.
     *
     * @param uri the URI of the resource
     */
    public DefaultPersistentResource(URI uri) {
        super(uri);

        factory = BackendFactoryRegistry.getInstance().getFactoryFor(uri.scheme());

        // Creates a transient backend until a call to `save()`/`load()`
        eStore = createTransientStore();

        logState("created");
    }

    @Nonnull
    @Override
    public EList<EObject> getContents() {
        return new RootContentsList<>(this);
    }

    @Nonnull
    @Override
    @SuppressWarnings("unchecked")
    public TreeIterator<EObject> getAllContents() {
        return TreeIterator.class.cast(new AllContentsIterator<>(this));
    }

    @Nonnull
    @Override
    public String getURIFragment(EObject eObject) {
        return Optional.of(PersistentEObject.from(eObject))
                .filter(o -> this == o.eResource())
                .map(o -> o.id().toHexString())
                .orElse("/-1");
    }

    @Override
    public EObject getEObject(String uriFragment) {
        return eStore.resolve(Id.getProvider().fromHexString(uriFragment));
    }

    @Override
    @SuppressWarnings("unchecked")
    public void save(Map<?, ?> options) {
        save(createConfig(options));
    }

    @Override
    @SuppressWarnings("unchecked")
    public void load(Map<?, ?> options) throws IOException {
        load(createConfig(options));
    }

    @Override
    public Notification setLoaded(boolean isLoaded) {
        // Here to change the visibility (`protected` to `public`): see RootContentsList
        return super.setLoaded(isLoaded);
    }

    @Override
    protected List<EObject> getUnloadingContents() {
        // Content is created on demand
        return Collections.emptyList();
    }

    @Override
    protected void doUnload() {
        getErrors().clear();
        getWarnings().clear();

        eStore.close();

        logState("closed");
    }

    @Override
    public void close() {
        unload();
    }

    @Override
    public void save(ImmutableConfig config) {
        if (!isLoaded || !eStore.store().backend().isPersistent()) {
            mergeStore(config);
            Optional.ofNullable(setLoaded(true)).ifPresent(this::eNotify);
        }

        eStore.save();

        logState("saved");
    }

    @Override
    public void load(ImmutableConfig config) throws IOException {
        if (isLoading || isLoaded) {
            return;
        }

        try {
            isLoading = true;

            if (uri.isFile() && new File(uri.toFileString()).exists() || uri.hasAuthority()) {
                eSetStore(createStore(config));
            }
            else {
                throw new FileNotFoundException(uri.toFileString());
            }
        }
        finally {
            isLoading = false;
            logState("loaded");

            Optional.ofNullable(setLoaded(true)).ifPresent(this::eNotify);
            setModified(false);
        }
    }

    @Nonnull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends EObject> Iterable<T> allInstancesOf(EClass eClass, boolean strict) {
        // There is no strict instance of an abstract class
        if ((eClass.isAbstract() || eClass.isInterface()) && strict) {
            return Collections.emptyList();
        }

        Stream<EObject> allInstancesOf;

        try {
            allInstancesOf = MoreIterables.stream(eStore.store().allInstancesOf(ClassBean.from(eClass), strict))
                    .map(id -> eStore.resolve(id));
        }
        catch (UnsupportedOperationException e) {
            Log.debug("This mapper doesn't support the lookup of all instances: using standard EMF API instead");

            allInstancesOf = MoreIterables.stream(this::getAllContents)
                    .filter(eClass::isInstance)
                    .filter(o -> !strict || Objects.equals(o.eClass(), eClass));
        }

        return allInstancesOf
                .map(o -> (T) o)
                .collect(Collectors.toSet());
    }

    @Nonnull
    @Override
    public StoreAdapter eStore() {
        return eStore;
    }

    /**
     * Closes the previous store of this resource and defines the new.
     *
     * @param newStore the new store of this resource
     *
     * @see StoreAdapter#close()
     */
    private void eSetStore(StoreAdapter newStore) {
        eStore.close();
        eStore = newStore;
    }

    /**
     * Merges the content of the current {@link #eStore} to a newly created {@link Backend}, using the current {@link
     * #factory}.
     *
     * @param config the configuration of the store/back-end
     */
    private void mergeStore(ImmutableConfig config) {
        StoreAdapter newStore = createStore(config);
        eStore.copyTo(newStore);
        eSetStore(newStore);
    }

    /**
     * Creates a new {@link StoreAdapter} on top of a newly created transient {@link Backend}, using the default {@link
     * InMemoryBackendFactory} implementation.
     *
     * @return a new store
     */
    @Nonnull
    private StoreAdapter createTransientStore() {
        Backend backend;
        ImmutableConfig config;

        if (factory.supportsTransient()) {
            config = InMemoryConfig.newConfig();
            backend = InMemoryBackendFactory.getInstance().createBackend(uri, config);
        }
        else {
            config = BaseConfig.newConfig();
            backend = new InvalidBackend("This back-end does not provide a transient layer: you must save/load the associated resource before using it");
        }

        return createStore(backend, config);
    }

    /**
     * Creates a new {@link StoreAdapter} on top of a newly created {@link Backend}, using the current {@link
     * #factory}.
     *
     * @param config the configuration of the store/back-end
     *
     * @return a new store
     */
    @Nonnull
    private StoreAdapter createStore(ImmutableConfig config) {
        return createStore(factory.createBackend(uri, config), config);
    }

    /**
     * Creates a new {@link StoreAdapter} on top a the given {@code backend}.
     *
     * @param backend the backend
     * @param config  the configuration of the store
     *
     * @return a new store
     */
    @Nonnull
    private StoreAdapter createStore(Backend backend, ImmutableConfig config) {
        Store baseStore = StoreFactory.getInstance().createStore(backend, config);
        return new PersistentStoreAdapter(baseStore, this);
    }

    @Nonnull
    @SuppressWarnings("unchecked")
    private ImmutableConfig createConfig(Map<?, ?> options) {
        return BaseConfig.newConfig().merge((Map<String, Object>) options);
    }

    /**
     * Logs the new {@code state} of this resource.
     *
     * @param state the new state of this resource
     */
    private void logState(String state) {
        Log.info("PersistentResource {0} : {1}", String.format("%1$-7s", state), uri);
    }
}
