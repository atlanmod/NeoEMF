/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.resource;

import fr.inria.atlanmod.commons.collect.MoreIterables;
import fr.inria.atlanmod.commons.log.Log;
import fr.inria.atlanmod.neoemf.bind.Bindings;
import fr.inria.atlanmod.neoemf.config.BaseConfig;
import fr.inria.atlanmod.neoemf.config.Config;
import fr.inria.atlanmod.neoemf.config.ImmutableConfig;
import fr.inria.atlanmod.neoemf.core.DefaultPersistentEObject;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.BackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.data.InvalidBackend;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;
import fr.inria.atlanmod.neoemf.data.im.InMemoryBackendFactory;
import fr.inria.atlanmod.neoemf.data.store.Store;
import fr.inria.atlanmod.neoemf.data.store.StoreFactory;
import fr.inria.atlanmod.neoemf.data.store.adapter.PersistentStoreAdapter;
import fr.inria.atlanmod.neoemf.data.store.adapter.StoreAdapter;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.impl.EClassifierImpl;
import org.eclipse.emf.ecore.impl.EReferenceImpl;
import org.eclipse.emf.ecore.impl.EStoreEObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkArgument;
import static java.util.Objects.nonNull;

/**
 * The default implementation of a {@link PersistentResource} that contains {@link PersistentEObject}.
 * <p>
 * {@link DefaultPersistentResource}s is backend-agnostic and only delegates model element operations to its internal
 * {@link Store} which is responsible of database access.
 *
 * @see PersistentStoreAdapter
 */
@ParametersAreNonnullByDefault
public class DefaultPersistentResource extends ResourceImpl implements PersistentResource {

    /**
     * An unknown URI fragment.
     */
    @Nonnull
    private static final String URI_UNKNOWN = "/-1";

    /**
     * The reference representing the link between the {@link #rootObject} and its content.
     */
    @Nonnull
    private static final EReference ROOT_CONTENTS_REFERENCE = new RootContentsReference();

    /**
     * The object representing the root and the entry-point of this resource.
     */
    @Nonnull
    private final PersistentEObject rootObject;

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
     * @param uri the {@link URI} of the resource
     */
    public DefaultPersistentResource(URI uri) {
        super(uri);

        factory = BackendFactoryRegistry.getInstance().getFactoryFor(uri.scheme());

        Backend backend;
        ImmutableConfig config = BaseConfig.newConfig();

        if (factory.supportsTransient()) {
            // Creates an in-memory backend until a call to `save()`/`load()`
            final String imScheme = Bindings.schemeOf(InMemoryBackendFactory.class);
            BackendFactory imFactory = BackendFactoryRegistry.getInstance().getFactoryFor(imScheme);

            backend = imFactory.createBackend(uri, config);
        }
        else {
            backend = new InvalidBackend("This back-end does not provide a transient layer: you must save/load the associated resource before using it");
        }

        Store baseStore = StoreFactory.getInstance().createStore(backend, config);
        eStore = new PersistentStoreAdapter(baseStore, this);

        rootObject = new RootObject(this);

        Log.info("PersistentResource created: {0}", uri);
    }

    @Nonnull
    @Override
    public EList<EObject> getContents() {
        return new RootContentsList<>();
    }

    @Nonnull
    @Override
    public String getURIFragment(EObject eObject) {
        return Optional.of(PersistentEObject.from(eObject))
                .filter(o -> this == o.eResource())
                .map(o -> o.id().toHexString())
                .orElse(URI_UNKNOWN);
    }

    @Override
    public EObject getEObject(String uriFragment) {
        return eStore.resolve(Id.getProvider().fromHexString(uriFragment));
    }

    @Override
    @SuppressWarnings("unchecked")
    public void save(Map<?, ?> options) throws IOException {
        doSave(Config.forScheme(uri.scheme()).merge((Map<String, Object>) options));
    }

    @Override
    @SuppressWarnings("unchecked")
    public void load(Map<?, ?> options) throws IOException {
        doLoad(Config.forScheme(uri.scheme()).merge((Map<String, Object>) options));
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

        Log.info("PersistentResource closed:  {0}", uri);
    }

    /**
     * Saves this resource using the specified {@code config}.
     *
     * @param config the save configuration
     */
    protected void doSave(ImmutableConfig config) {
        if (!isLoaded || !eStore.store().backend().isPersistent()) {
            Backend backend = factory.createBackend(uri, config);
            Store baseStore = StoreFactory.getInstance().createStore(backend, config);
            StoreAdapter newStore = new PersistentStoreAdapter(baseStore, this);

            // Direct copy
            eStore.copyTo(newStore);

            // Close the previous store, and assign the new
            eStore.close();
            eStore = newStore;

            isLoaded = true;
        }

        eStore.save();

        Log.info("PersistentResource saved:   {0}", uri);
    }

    /**
     * Loads this resource using the specified {@code config}.
     *
     * @param config the load configuration
     *
     * @throws IOException if an I/O error occurs during the load
     */
    protected void doLoad(ImmutableConfig config) throws IOException {
        try {
            isLoading = true;

            if (!isLoaded) {
                if (uri.isFile() && new File(uri.toFileString()).exists() || uri.hasAuthority()) {
                    eStore.close();

                    Backend backend = factory.createBackend(uri, config);
                    Store baseStore = StoreFactory.getInstance().createStore(backend, config);
                    eStore = new PersistentStoreAdapter(baseStore, this);
                }
                else {
                    throw new FileNotFoundException(uri.toFileString());
                }

                isLoaded = true;
            }
        }
        finally {
            isLoading = false;
            Log.info("PersistentResource loaded:  {0}", uri);
        }
    }

    @Override
    public void close() {
        unload();
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
     * The {@link PersistentEObject} that represents the root entry point for this {@link PersistentResource}.
     */
    @ParametersAreNonnullByDefault
    private static final class RootObject extends DefaultPersistentEObject {

        /**
         * Constructs a new {@code RootObject} with the given {@code resource}.
         *
         * @param resource the resource containing this object.
         */
        public RootObject(Resource.Internal resource) {
            super(ROOT_ID);
            eSetDirectResource(resource);
        }
    }

    /**
     * The {@link org.eclipse.emf.ecore.EReference} that represents the {@link Resource#getContents()} feature.
     */
    @ParametersAreNonnullByDefault
    private static final class RootContentsReference extends EReferenceImpl {

        /**
         * Constructs a new {@code RootContentsReference}.
         */
        public RootContentsReference() {
            setFeatureID(ROOT_REFERENCE_ID);
            setName(ROOT_REFERENCE_NAME);
            setLowerBound(0);
            setUpperBound(ETypedElement.UNBOUNDED_MULTIPLICITY);
            setEType(new EClassifierImpl() {});
        }
    }

    /**
     * A {@link java.util.List} that delegates its operations to a {@link Store} for supporting {@link
     * Resource#getContents}.
     *
     * @see RootContentsReference
     */
    @ParametersAreNonnullByDefault
    // TODO Reimplements `iterator()` and `listIterator()` to use batch methods
    private class RootContentsList<E> extends EStoreEObjectImpl.BasicEStoreEList<E> {

        @SuppressWarnings("JavaDoc")
        private static final long serialVersionUID = 4130828923851153715L;

        /**
         * Constructs a new {@code RootContentsList}.
         */
        public RootContentsList() {
            super(rootObject, ROOT_CONTENTS_REFERENCE);
        }

        @Override
        protected E validate(int index, E object) {
            checkArgument(canContainNull() || nonNull(object), "The 'no null' constraint is violated");
            return object;
        }

        @Override
        public Object getNotifier() {
            return DefaultPersistentResource.this;
        }

        @Override
        public int getFeatureID() {
            return ROOT_REFERENCE_ID;
        }

        @Override
        protected boolean isNotificationRequired() {
            return eNotificationRequired();
        }

        @Override
        public NotificationChain inverseAdd(E object, NotificationChain notifications) {
            PersistentEObject eObject = PersistentEObject.from(object);
            notifications = eObject.eSetResource(DefaultPersistentResource.this, notifications);
            attached(eObject);
            return notifications;
        }

        @Override
        public NotificationChain inverseRemove(E object, NotificationChain notifications) {
            PersistentEObject eObject = PersistentEObject.from(object);
            if (isLoaded || nonNull(unloadingContents)) {
                detached(eObject);
            }
            return eObject.eSetResource(null, notifications);
        }

        @Override
        protected boolean useEquals() {
            return false;
        }

        @Override
        protected boolean isUnique() {
            return true;
        }

        @Override
        protected boolean hasInverse() {
            return true;
        }

        @Override
        protected void didSet(int index, E newObject, E oldObject) {
            super.didSet(index, newObject, oldObject);
            modified();
        }

        @Override
        protected void didAdd(int index, E object) {
            super.didAdd(index, object);
            if (index == size() - 1) {
                loaded();
            }
            modified();
        }

        @Override
        protected void didRemove(int index, E object) {
            super.didRemove(index, object);
            modified();
        }

        @Override
        protected void didClear(int oldSize, Object[] oldData) {
            if (oldSize == 0) {
                loaded();
            }
            else {
                super.didClear(oldSize, oldData);
            }
        }

        /**
         * Notifies that this resource has been loaded.
         */
        private void loaded() {
            if (!isLoaded()) {
                Optional.ofNullable(setLoaded(true)).ifPresent(DefaultPersistentResource.this::eNotify);
            }
        }

        /**
         * Notifies that this resource has been modified.
         */
        private void modified() {
            if (isTrackingModification()) {
                setModified(true);
            }
        }

        @Override
        protected StoreAdapter eStore() {
            return DefaultPersistentResource.this.eStore();
        }

        @Override
        protected void delegateAdd(int index, Object value) {
            hardAllContents(PersistentEObject.from(value)).forEach(e -> e.resource(DefaultPersistentResource.this));
            super.delegateAdd(index, value);
        }

        @Override
        @SuppressWarnings("unchecked")
        protected E delegateRemove(int index) {
            E previousValue = super.delegateRemove(index);
            hardAllContents(PersistentEObject.from(previousValue)).forEach(e -> e.resource(null));
            return previousValue;
        }

        /**
         * Retrieves all the content of the specified {@code rootObject} and stores it in a {@link List}.
         * <p>
         * By iterating using the hard links list instead the {@link #getAllContents()}, we ensure that the content is
         * not taken out by JIT compiler.
         *
         * @param rootObject the object from which to retrieve the content
         *
         * @return the content of {@code rootObject}
         */
        @Nonnull
        private Iterable<PersistentEObject> hardAllContents(PersistentEObject rootObject) {
            Collection<PersistentEObject> allContents = new ArrayDeque<>();
            allContents.add(rootObject);

            MoreIterables.stream(rootObject::eAllContents)
                    .map(PersistentEObject::from)
                    .collect(Collectors.toCollection(() -> allContents));

            return allContents;
        }
    }
}
