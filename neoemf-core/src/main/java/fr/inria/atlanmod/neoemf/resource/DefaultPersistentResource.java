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
import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.core.StringId;
import fr.inria.atlanmod.neoemf.data.PersistenceBackend;
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.data.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.data.store.PersistentStore;
import fr.inria.atlanmod.neoemf.option.InvalidOptionException;
import fr.inria.atlanmod.neoemf.util.logging.NeoLogger;

import org.apache.commons.io.FileUtils;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.InternalEObject.EStore;
import org.eclipse.emf.ecore.impl.EClassifierImpl;
import org.eclipse.emf.ecore.impl.EReferenceImpl;
import org.eclipse.emf.ecore.impl.EStoreEObjectImpl;
import org.eclipse.emf.ecore.impl.EStoreEObjectImpl.EStoreEList;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * The default implementation of a {@link PersistentResource} that contains {@link PersistentEObject}.
 * <p>
 * {@link DefaultPersistentResource}s is backend-agnostic and only delegates model element operations
 * to its internal {@link PersistentStore} which is responsible of database access.
 */
public class DefaultPersistentResource extends ResourceImpl implements PersistentResource {

    /**
     * ???
     */
    private static final String URI_UNKNOWN = "/-1";

    /**
     * ???
     */
    private static final ResourceContentsEStructuralFeature ROOT_CONTENTS_ESTRUCTURALFEATURE = new ResourceContentsEStructuralFeature();

    /**
     * ???
     */
    private final DummyRootEObject dummyRootEObject;

    /**
     * The {@link PersistentStore} responsible of the database serialization.
     */
    protected PersistentStore store;

    /**
     * The underlying {@link PersistenceBackend} that stores the data.
     */
    protected PersistenceBackend backend;

    /**
     * ???
     */
    private Map<?, ?> options;

    /**
     * ???
     */
    private boolean isPersistent;

    /**
     * Constructs a new {@code DefaultPersistentResource} with the given {@code uri}.
     *
     * @param uri the {@link URI} of the resource
     */
    public DefaultPersistentResource(URI uri) {
        super(uri);
        this.dummyRootEObject = new DummyRootEObject(this);

        this.backend = PersistenceBackendFactoryRegistry.getFactoryProvider(uri.scheme()).createTransientBackend();
        this.store = PersistenceBackendFactoryRegistry.getFactoryProvider(uri.scheme()).createTransientStore(this, backend);

        this.isPersistent = false;

        PersistenceBackendShutdownHook.closeOnExit(backend, getURI());
        NeoLogger.info("{0} created", PersistentResource.class.getSimpleName());
    }

    @Override
    public EList<EObject> getContents() {
        return new ResourceContentsEStoreEList<>(dummyRootEObject, ROOT_CONTENTS_ESTRUCTURALFEATURE, eStore());
    }

    @Override
    public String getURIFragment(EObject eObject) {
        String fragment = URI_UNKNOWN;
        if (eObject.eResource() == this) {
            // Try to adapt as a PersistentEObject and return the ID
            PersistentEObject object = PersistentEObject.from(eObject);
            if (nonNull(object)) {
                fragment = object.id().toString();
            }
        }
        return fragment;
    }

    @Override
    public EObject getEObject(String uriFragment) {
        EObject eObject = store.eObject(StringId.of(uriFragment));
        return isNull(eObject) ? super.getEObject(uriFragment) : eObject;
    }

    @Override
    public void save(Map<?, ?> options) throws IOException {
        if (nonNull(this.options)) {
            // Check that the save options do not collide with previous load options
            for (Entry<?, ?> entry : options.entrySet()) {
                if (this.options.containsKey(entry.getKey()) && !Objects.equals(entry.getValue(), this.options.get(entry.getKey()))) {
                    throw new InvalidOptionException(MessageFormat.format("key = {0}; value = {1}", entry.getKey().toString(), entry.getValue().toString()));
                }
            }
        }
        if (!isLoaded() || !isPersistent) {
            PersistenceBackendFactory factory = PersistenceBackendFactoryRegistry.getFactoryProvider(uri.scheme());

            PersistenceBackend newBackend = factory.createPersistentBackend(uri, options);
            backend.copyTo(newBackend);

            this.backend = newBackend;
            this.store = factory.createPersistentStore(this, backend, options);

            this.isLoaded = true;
            this.isPersistent = true;
        }

        store.save();

        NeoLogger.info("{0} saved: {1}", PersistentResource.class.getSimpleName(), uri);
    }

    @Override
    public void load(Map<?, ?> options) throws IOException {
        try {
            isLoading = true;

            if (!isLoaded) {
                if ((uri.isFile() && FileUtils.getFile(uri.toFileString()).exists()) || uri.hasAuthority()) {
                    PersistenceBackendFactory factory = PersistenceBackendFactoryRegistry.getFactoryProvider(uri.scheme());

                    backend = factory.createPersistentBackend(uri, options);
                    store = factory.createPersistentStore(this, backend, options);

                    isPersistent = true;
                    dummyRootEObject.setMapped(true);
                }
                else {
                    throw new FileNotFoundException(uri.toFileString());
                }

                this.options = options;
                isLoaded = true;
            }
        }
        finally {
            isLoading = false;
            NeoLogger.info("{0} loaded: {1}", PersistentResource.class.getSimpleName(), uri);
        }
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

    @Override
    public void close() {
        if (!backend.isClosed()) {
            this.backend.close();
        }

        this.backend = PersistenceBackendFactoryRegistry.getFactoryProvider(uri.scheme()).createTransientBackend();
        this.store = PersistenceBackendFactoryRegistry.getFactoryProvider(uri.scheme()).createTransientStore(this, backend);

        this.isPersistent = false;
        this.isLoaded = false;

        NeoLogger.info("{0} closed: {1}", PersistentResource.class.getSimpleName(), getURI());
    }

    @Override
    public InternalEObject.EStore eStore() {
        return store;
    }

    @Override
    public boolean isDistributed() {
        return backend.isDistributed();
    }

    @Override
    public Iterable<EObject> getAllInstances(EClass eClass, boolean strict) {
        Iterable<EObject> allInstances;
        try {
            allInstances = store.getAllInstances(eClass, strict);
        }
        catch (UnsupportedOperationException e) {
            NeoLogger.warn("This PersistenceBackend does not support advanced allInstances() computation. Using standard EMF API instead");
            List<EObject> instanceList = new ArrayList<>();
            Iterable<EObject> allContents = this::getAllContents;
            for (EObject eObject : allContents) {
                if (eClass.isInstance(eObject)) {
                    if (strict) {
                        if (Objects.equals(eObject.eClass(), eClass)) {
                            instanceList.add(eObject);
                        }
                    }
                    else {
                        instanceList.add(eObject);
                    }
                }
            }
            allInstances = instanceList;
        }
        return allInstances;
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
     * Fake {@link EStructuralFeature} that represents the {@link Resource#getContents()} feature.
     */
    private static class ResourceContentsEStructuralFeature extends EReferenceImpl {

        /**
         * ???
         */
        private static final String CONTENTS = "eContents";

        /**
         * Constructs a new {@code ResourceContentsEStructuralFeature}.
         */
        public ResourceContentsEStructuralFeature() {
            setUpperBound(ETypedElement.UNBOUNDED_MULTIPLICITY);
            setLowerBound(0);
            setName(CONTENTS);
            setEType(new EClassifierImpl() {
            });
            setFeatureID(RESOURCE__CONTENTS);
        }
    }

    /**
     * Dummy {@link EObject} that represents the root entry point for this {@link Resource}.
     */
    private static final class DummyRootEObject extends DefaultPersistentEObject {

        /**
         * The literal representation of the identifier of the root element in a database.
         */
        private static final String ROOT_EOBJECT_ID = "ROOT";

        /**
         * Constructs a new {@code DummyRootEObject} with the given {@code resource}.
         *
         * @param resource the resource containing this object.
         */
        public DummyRootEObject(Resource.Internal resource) {
            super(StringId.of(ROOT_EOBJECT_ID));
            eSetDirectResource(resource);
        }
    }

    /**
     * A shutdown-hook that stops a persistent back-end when the application exits.
     */
    private static class PersistenceBackendShutdownHook extends Thread {

        /**
         * The back-end to stop when the application will exit.
         */
        private final PersistenceBackend backend;

        /**
         * The {@link URI} of the resource used by the {@code backend}.
         */
        private final URI uri;

        /**
         * Creates a new {@code PersistenceBackendShutdownHook} with the given {@code backend}.
         *
         * @param backend the back-end to stop when the application will exit
         * @param uri     the {@link URI} of the resource used by the {@code backend}
         */
        private PersistenceBackendShutdownHook(PersistenceBackend backend, URI uri) {
            this.backend = backend;
            this.uri = uri;
        }

        /**
         * Adds a shutdown hook on the given {@code backend}. It will be stopped when the application will exit.
         *
         * @param backend the back-end to stop when the application will exit
         * @param uri     the {@link URI} of the resource used by the {@code backend}
         */
        public static void closeOnExit(PersistenceBackend backend, URI uri) {
            Runtime.getRuntime().addShutdownHook(new PersistenceBackendShutdownHook(backend, uri));
        }

        /**
         * {@inheritDoc}
         * <p>
         * Cleanly stops the underlying database.
         *
         * @see PersistenceBackend#close()
         */
        @Override
        public void run() {
            if (!backend.isClosed()) {
                backend.close();
                NeoLogger.debug("{0} closed: {1} ", PersistenceBackend.class.getSimpleName(), uri);
            }
        }
    }

    /**
     * A notifying {@link EStoreEList} list implementation for supporting {@link Resource#getContents}.
     */
    private class ResourceContentsEStoreEList<E> extends EStoreEObjectImpl.EStoreEList<E> {

        @SuppressWarnings("JavaDoc")
        private static final long serialVersionUID = 4130828923851153715L;

        /**
         * Constructs a new {@code ResourceContentsEStoreEList} with ???
         *
         * @param owner   ???
         * @param feature ???
         * @param store   ???
         */
        public ResourceContentsEStoreEList(InternalEObject owner, EStructuralFeature feature, EStore store) {
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
            List<Object> hardLinksList = new ArrayList<>();
            PersistentEObject eObject = PersistentEObject.from(object);
            // Collect all contents
            hardLinksList.add(object);
            Iterator<EObject> it = eObject.eAllContents();
            while (it.hasNext()) {
                hardLinksList.add(it.next());
            }
            /*
             * Iterate using the hard links list instead the getAllContents.
			 * We ensure that using the hardLinksList it is not taken out by JIT compiler
			 */
            for (Object element : hardLinksList) {
                PersistentEObject internalElement = PersistentEObject.from(element);
                internalElement.resource(DefaultPersistentResource.this);
            }
            super.delegateAdd(index, object);
        }

        @Override
        @SuppressWarnings("unchecked") // Unchecked cast: 'org.eclipse.emf.ecore.EObject' to 'E'
        protected E delegateRemove(int index) {
            E object = super.delegateRemove(index);
            List<E> hardLinksList = new ArrayList<>();
            PersistentEObject eObject = PersistentEObject.from(object);
            // Collect all contents
            hardLinksList.add(object);
            Iterator<EObject> it = eObject.eAllContents();
            while (it.hasNext()) {
                hardLinksList.add((E) it.next());
            }
            /*
             * Iterate using the hard links list instead the getAllContents.
			 * We ensure that using the hardLinksList it is not taken out by JIT compiler
			 */
            for (E element : hardLinksList) {
                PersistentEObject internalElement = PersistentEObject.from(element);
                internalElement.resource(null);
            }
            return object;
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
         * ???
         */
        private void loaded() {
            if (!isLoaded()) {
                Notification notification = setLoaded(true);
                if (nonNull(notification)) {
                    eNotify(notification);
                }
            }
        }

        /**
         * ???
         */
        private void modified() {
            if (isTrackingModification()) {
                setModified(true);
            }
        }
    }
}
