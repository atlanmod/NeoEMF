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

package fr.inria.atlanmod.neoemf.resource;

import fr.inria.atlanmod.neoemf.core.DefaultPersistentEObject;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.core.StringId;
import fr.inria.atlanmod.neoemf.data.PersistenceBackend;
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.data.store.PersistentStore;
import fr.inria.atlanmod.neoemf.data.structure.ClassDescriptor;
import fr.inria.atlanmod.neoemf.option.InvalidOptionException;
import fr.inria.atlanmod.neoemf.util.Iterables;
import fr.inria.atlanmod.neoemf.util.log.Log;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EClassifierImpl;
import org.eclipse.emf.ecore.impl.EReferenceImpl;
import org.eclipse.emf.ecore.impl.EStoreEObjectImpl;
import org.eclipse.emf.ecore.impl.EStoreEObjectImpl.EStoreEList;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.neoemf.util.Preconditions.checkArgument;
import static java.util.Objects.nonNull;

/**
 * The default implementation of a {@link PersistentResource} that contains {@link PersistentEObject}.
 * <p>
 * {@link DefaultPersistentResource}s is backend-agnostic and only delegates model element operations
 * to its internal {@link PersistentStore} which is responsible of database access.
 */
@ParametersAreNonnullByDefault
public class DefaultPersistentResource extends ResourceImpl implements PersistentResource {

    /**
     * An unknown URI fragment.
     */
    private static final String URI_UNKNOWN = "/-1";

    /**
     * The {@link org.eclipse.emf.ecore.EReference} representing the link between the {@link #rootObject} and its
     * content.
     */
    @Nonnull
    private static final RootContentsReference ROOT_CONTENTS_REFERENCE = new RootContentsReference();

    /**
     * The {@link PersistentEObject} representing the root and the entry-point of this {@link PersistentResource}.
     */
    @Nonnull
    private final RootObject rootObject;

    /**
     * The {@link PersistentStore} responsible of the database serialization.
     */
    @Nonnull
    protected PersistentStore store;

    /**
     * The last options used during {@link #load(Map)}.
     */
    @Nullable
    private Map<String, Object> previousOptions;

    /**
     * Whether this {@link PersistentResource} is stored in a {@link PersistenceBackend}, non-transient.
     */
    private boolean isPersistent;

    /**
     * Constructs a new {@code DefaultPersistentResource} with the given {@code uri}.
     *
     * @param uri the {@link URI} of the resource
     */
    public DefaultPersistentResource(URI uri) {
        super(uri);
        rootObject = new RootObject(this);

        PersistenceBackendFactory factory = PersistenceBackendFactoryRegistry.getFactoryProvider(uri.scheme());
        store = factory.createTransientStore(this);

        isPersistent = false;

        Log.info("{0} created", PersistentResource.class.getSimpleName());
    }

    @Nonnull
    @Override
    public EList<EObject> getContents() {
        return new ResourceContentsEStoreEList<>(rootObject, ROOT_CONTENTS_REFERENCE, store());
    }

    @Nonnull
    @Override
    public String getURIFragment(EObject eObject) {
        if (this == eObject.eResource()) {
            // Try to adapt as a PersistentEObject and return the ID
            PersistentEObject object = PersistentEObject.from(eObject);
            if (nonNull(object)) {
                return object.id().toString();
            }
        }
        return URI_UNKNOWN;
    }

    @Override
    public EObject getEObject(String uriFragment) {
        return Optional.<EObject>of(store.resolve(StringId.of(uriFragment)))
                .orElseGet(() -> super.getEObject(uriFragment));
    }

    @Override
    @SuppressWarnings("unchecked")
    public void save(Map<?, ?> options) throws IOException {
        safeSave((Map<String, Object>) options);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void load(Map<?, ?> options) throws IOException {
        safeLoad((Map<String, Object>) options);
    }

    @Override
    protected void doUnload() {
        Iterable<EObject> allContents = () -> getAllProperContents(unloadingContents);
        getErrors().clear();
        getWarnings().clear();
        for (EObject e : allContents) {
            unloaded((InternalEObject) e);
        }
        close();
    }

    /**
     * Saves this resource using the specified {@code options}.
     * <p>
     * Options are handled generically as feature-to-setting entries; the resource will ignore options it doesn't
     * recognize.
     *
     * @param options the save options
     *
     * @throws IOException if an I/O error occurs during the save
     */
    protected void safeSave(Map<String, Object> options) throws IOException {
        checkOptions(options);

        if (!isLoaded || !isPersistent) {
            PersistenceBackendFactory factory = PersistenceBackendFactoryRegistry.getFactoryProvider(uri.scheme());
            PersistentStore newStore = factory.createPersistentStore(this, options);

            store.copyTo(newStore.backend());

            // Close the previous store, and assign the new
            store.close();
            store = newStore;

            isLoaded = true;
            isPersistent = true;
        }

        store.save();

        Log.info("{0} saved:   {1}", PersistentResource.class.getSimpleName(), uri);
    }

    /**
     * Loads this resource using the specified {@code options}.
     * <p>
     * Options are handled generically as feature-to-setting entries; the resource will ignore options it doesn't
     * recognize.
     *
     * @param options the load options
     *
     * @throws IOException if an I/O error occurs during the load
     */
    protected void safeLoad(Map<String, Object> options) throws IOException {
        try {
            isLoading = true;

            if (!isLoaded) {
                if ((uri.isFile() && new File(uri.toFileString()).exists()) || uri.hasAuthority()) {
                    // Closes the previous store
                    store.close();

                    PersistenceBackendFactory factory = PersistenceBackendFactoryRegistry.getFactoryProvider(uri.scheme());
                    store = factory.createPersistentStore(this, options);

                    isPersistent = true;
                    rootObject.setMapped(true);
                }
                else {
                    throw new FileNotFoundException(uri.toFileString());
                }

                previousOptions = options;
                isLoaded = true;
            }
        }
        finally {
            isLoading = false;
            Log.info("{0} loaded:  {1}", PersistentResource.class.getSimpleName(), uri);
        }
    }

    @Override
    public void close() {
        store.close();

        PersistenceBackendFactory factory = PersistenceBackendFactoryRegistry.getFactoryProvider(uri.scheme());
        store = factory.createTransientStore(this);

        isPersistent = false;
        isLoaded = false;

        Log.info("{0} closed:  {1}", PersistentResource.class.getSimpleName(), uri);
    }

    @Nonnull
    @Override
    public PersistentStore store() {
        return store;
    }

    @Override
    public boolean isPersistent() {
        return isPersistent;
    }

    @Override
    @Deprecated
    public boolean isDistributed() {
        return store.backend().isDistributed();
    }

    @Nonnull
    @Override
    public Iterable<EObject> allInstances(EClass eClass, boolean strict) {
        Iterable<EObject> allInstances;
        try {
            return Iterables.stream(store.allInstancesOf(ClassDescriptor.from(eClass), strict))
                    .map(id -> store.resolve(id))
                    .collect(Collectors.toList());
        }
        catch (UnsupportedOperationException e) {
            Log.info("The store does not support advanced allInstances() computation: using standard EMF API instead");

            return Iterables.stream(this::getAllContents)
                    .filter(eClass::isInstance)
                    .map(o -> !strict || Objects.equals(o.eClass(), eClass) ? o : null)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        }
    }

    /**
     * Check that the {@code options} do not collide with {@link #previousOptions}.
     *
     * @param options the options to check
     */
    private void checkOptions(Map<String, Object> options) {
        if (nonNull(previousOptions)) {
            for (Map.Entry<String, Object> entry : options.entrySet()) {
                if (previousOptions.containsKey(entry.getKey()) && !Objects.equals(entry.getValue(), previousOptions.get(entry.getKey()))) {
                    throw new InvalidOptionException(MessageFormat.format("key = {0}; value = {1}", entry.getKey(), entry.getValue()));
                }
            }
        }
    }

    @Override
    protected void finalize() throws Throwable {
        try {
            unload();
        }
        finally {
            super.finalize();
        }
    }

    /**
     * The {@link PersistentEObject} that represents the root entry point for this {@link PersistentResource}.
     */
    private static final class RootObject extends DefaultPersistentEObject {

        /**
         * The literal representation of the identifier of the root element in a database.
         */
        private static final Id ID = StringId.of("ROOT");

        /**
         * Constructs a new {@code RootObject} with the given {@code resource}.
         *
         * @param resource the resource containing this object.
         */
        public RootObject(Resource.Internal resource) {
            super(ID);
            eSetDirectResource(resource);
        }
    }

    /**
     * The {@link org.eclipse.emf.ecore.EReference} that represents the {@link Resource#getContents()} feature.
     */
    private static final class RootContentsReference extends EReferenceImpl {

        /**
         * The name of this reference.
         */
        private static final String NAME = "eContents";

        /**
         * Constructs a new {@code RootContentsReference}.
         */
        public RootContentsReference() {
            setUpperBound(ETypedElement.UNBOUNDED_MULTIPLICITY);
            setLowerBound(0);
            setName(NAME);
            setEType(new EClassifierImpl() {});
            setFeatureID(RESOURCE__CONTENTS);
        }
    }

    /**
     * A notifying {@link EStoreEList} list implementation for supporting {@link Resource#getContents}.
     */
    private class ResourceContentsEStoreEList<E> extends EStoreEObjectImpl.EStoreEList<E> {

        @SuppressWarnings("JavaDoc")
        private static final long serialVersionUID = 4130828923851153715L;

        /**
         * Constructs a new {@code ResourceContentsEStoreEList}.
         *
         * @param owner   the owner of this list
         * @param feature ???
         * @param store   ???
         */
        public ResourceContentsEStoreEList(InternalEObject owner, EStructuralFeature feature, PersistentStore store) {
            super(owner, feature, store);
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
            return RESOURCE__CONTENTS;
        }

        @Override
        protected boolean isNotificationRequired() {
            return eNotificationRequired();
        }

        @Override
        public NotificationChain inverseAdd(E object, NotificationChain notifications) {
            InternalEObject internalObject = (InternalEObject) object;
            notifications = internalObject.eSetResource(DefaultPersistentResource.this, notifications);
            attached(internalObject);
            return notifications;
        }

        @Override
        public NotificationChain inverseRemove(E object, NotificationChain notifications) {
            InternalEObject internalObject = (InternalEObject) object;
            if (isLoaded || nonNull(unloadingContents)) {
                detached(internalObject);
            }
            return internalObject.eSetResource(null, notifications);
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
        protected void delegateAdd(int index, Object object) {
            /*
             * FIXME Maintain a list of hard links to the elements while moving them to the new resource.
			 * If a garbage collection happens while traversing the children elements, some unsaved objects that are
			 * referenced from a saved object may be garbage collected before they have been completely stored in the DB
			 */
            PersistentEObject eObject = PersistentEObject.from(object);

            // Collect all contents
            Set<EObject> allContents = Iterables.stream(eObject::eAllContents)
                    .collect(Collectors.toSet());

            allContents.add(eObject);

            /*
             * Iterate using the hard links set instead the getAllContents.
			 * We ensure that using the hard links set it is not taken out by JIT compiler.
			 */
            allContents.stream()
                    .map(PersistentEObject::from)
                    .forEach(e -> e.resource(DefaultPersistentResource.this));

            super.delegateAdd(index, object);
        }

        @Override
        @SuppressWarnings("unchecked") // Unchecked cast: 'org.eclipse.emf.ecore.EObject' to 'E'
        protected E delegateRemove(int index) {
            E previousValue = super.delegateRemove(index);

            PersistentEObject eObject = PersistentEObject.from(previousValue);

            // Collect all contents
            Set<E> allContents = Iterables.stream(eObject::eAllContents)
                    .map(e -> (E) e)
                    .collect(Collectors.toSet());

            allContents.add(previousValue);

            /*
             * Iterate using the hard links set instead the getAllContents.
			 * We ensure that using the hard links set it is not taken out by JIT compiler.
			 */
            allContents.stream()
                    .map(PersistentEObject::from)
                    .forEach(e -> e.resource(null));

            return previousValue;
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
                Optional.ofNullable(setLoaded(true))
                        .ifPresent(DefaultPersistentResource.this::eNotify);
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
    }
}
