/*
 * Copyright (c) 2013-2016 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.resource.impl;

import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.core.impl.PersistentEObjectImpl;
import fr.inria.atlanmod.neoemf.core.impl.StringId;
import fr.inria.atlanmod.neoemf.datastore.InvalidOptionsException;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackend;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.datastore.store.PersistentStore;
import fr.inria.atlanmod.neoemf.logging.NeoLogger;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.util.PersistenceURI;

import org.apache.commons.io.FileUtils;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.isNull;

public class PersistentResourceImpl extends ResourceImpl implements PersistentResource {

    private static final String URI_UNKNOWN = "/-1";

    private static final ResourceContentsEStructuralFeature ROOT_CONTENTS_ESTRUCTURALFEATURE = new ResourceContentsEStructuralFeature();

    private final DummyRootEObject dummyRootEObject;

    protected PersistentStore eStore;

    /**
     * The underlying {@link PersistenceBackend} that stores the data.
     */
    protected PersistenceBackend persistenceBackend;

    private Map<?, ?> options;

    private boolean isPersistent;

    public PersistentResourceImpl(URI uri) {
        super(uri);
        this.dummyRootEObject = new DummyRootEObject(this);
        this.persistenceBackend = PersistenceBackendFactoryRegistry.getFactoryProvider(uri.scheme()).createTransientBackend();
        this.eStore = PersistenceBackendFactoryRegistry.getFactoryProvider(uri.scheme()).createTransientStore(this, persistenceBackend);
        this.isPersistent = false;
        // Stop the backend when the application is terminated
        Runtime.getRuntime().addShutdownHook(new ShutdownHook());
        NeoLogger.info("{0} created", PersistentResource.class.getSimpleName());
    }

    /**
     * @deprecated Use {@link PersistentResource#close()} instead.
     */
    @Deprecated
    public static void shutdownWithoutUnload(PersistentResource resource) {
        if (!isNull(resource)) {
            resource.close();
            NeoLogger.info("{0} closed: {1}", PersistentResource.class.getSimpleName(), resource.getURI());
        }
    }

    /**
     * Returns the database file.
     */
    protected File getFile() {
        return FileUtils.getFile(PersistenceURI.createURI(getURI()).toFileString());
    }

    @Override
    public EList<EObject> getContents() {
        return new ResourceContentsEStoreEList<>(dummyRootEObject, ROOT_CONTENTS_ESTRUCTURALFEATURE, eStore());
    }

    @Override
    public String getURIFragment(EObject eObject) {
        String returnValue = URI_UNKNOWN;
        if (eObject.eResource() == this) {
            // Try to adapt as a PersistentEObject and return the ID
            PersistentEObject persistentEObject = PersistentEObject.from(eObject);
            if (!isNull(persistentEObject)) {
                returnValue = persistentEObject.id().toString();
            }
        }
        return returnValue;
    }

    @Override
    public EObject getEObject(String uriFragment) {
        EObject eObject = eStore.eObject(new StringId(uriFragment));
        return isNull(eObject) ? super.getEObject(uriFragment) : eObject;
    }

    @Override
    public void save(Map<?, ?> options) throws IOException {

        if (!isNull(this.options)) {
            // Check that the save options do not collide with previous load options
            for (Entry<?, ?> entry : options.entrySet()) {
                if (this.options.containsKey(entry.getKey())
                        && !isNull(entry.getValue())
                        && !entry.getValue().equals(this.options.get(entry.getKey())))
                {
                    throw new IOException(new InvalidOptionsException(MessageFormat.format("key = {0}; value = {1}", entry.getKey().toString(), entry.getValue().toString())));
                }
            }
        }
        if (!isLoaded() || !isPersistent) {
            PersistenceBackend newBackend = PersistenceBackendFactoryRegistry.getFactoryProvider(uri.scheme()).createPersistentBackend(getFile(), options);
            PersistenceBackendFactoryRegistry.getFactoryProvider(uri.scheme()).copyBackend(persistenceBackend, newBackend);
            this.persistenceBackend = newBackend;
            try {
                this.eStore = PersistenceBackendFactoryRegistry.getFactoryProvider(uri.scheme()).createPersistentStore(this, persistenceBackend, options);
            }
            catch (InvalidOptionsException e) {
                throw new IOException(e);
            }
            this.isLoaded = true;
            this.isPersistent = true;
        }
        persistenceBackend.save();
        NeoLogger.info("{0} saved: {1}", PersistentResource.class.getSimpleName(), uri);
    }

    @Override
    public void load(Map<?, ?> options) throws IOException {
        try {
            isLoading = true;
            if (!isLoaded) {
                if (getFile().exists() || !isNull(uri.authority())) {
                    // Check authority to enable remote resource loading
                    this.persistenceBackend = PersistenceBackendFactoryRegistry.getFactoryProvider(uri.scheme()).createPersistentBackend(getFile(), options);
                    try {
                        this.eStore = PersistenceBackendFactoryRegistry.getFactoryProvider(uri.scheme()).createPersistentStore(this, persistenceBackend, options);
                    }
                    catch (InvalidOptionsException e) {
                        throw new IOException(e);
                    }
                    this.isPersistent = true;
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
        Iterator<EObject> allContents = getAllProperContents(unloadingContents);
        getErrors().clear();
        getWarnings().clear();
        while (allContents.hasNext()) {
            unloaded((InternalEObject) allContents.next());
        }
        close();
    }

    @Override
    public void close() {
        this.persistenceBackend.close();

        this.persistenceBackend = PersistenceBackendFactoryRegistry.getFactoryProvider(uri.scheme()).createTransientBackend();
        this.eStore = PersistenceBackendFactoryRegistry.getFactoryProvider(uri.scheme()).createTransientStore(this, persistenceBackend);

        this.isPersistent = false;
        this.isLoaded = false;

        NeoLogger.info("{0} closed: {1}", PersistentResource.class.getSimpleName(), getURI());
    }

    @Override
    public InternalEObject.EStore eStore() {
        return eStore;
    }

    @Override
    public EList<EObject> getAllInstances(EClass eClass) {
        return getAllInstances(eClass, false);
    }

    @Override
    public EList<EObject> getAllInstances(EClass eClass, boolean strict) {
        EList<EObject> returnValue;
        try {
            returnValue = eStore.getAllInstances(eClass, strict);
        }
        catch (UnsupportedOperationException e) {
            NeoLogger.warn(e.getMessage());
            NeoLogger.warn("Using standard EMF API instead");
            EList<EObject> instanceList = new BasicEList<>();
            Iterator<EObject> it = getAllContents();
            while (it.hasNext()) {
                EObject eObject = it.next();
                if (eClass.isInstance(eObject)) {
                    if (strict) {
                        if (eObject.eClass().equals(eClass)) {
                            instanceList.add(eObject);
                        }
                    }
                    else {
                        instanceList.add(eObject);
                    }
                }
            }
            returnValue = instanceList;
        }
        return returnValue;
    }

    @Override
    protected void finalize() throws Throwable {
        unload();
        super.finalize();
    }

    /**
     * Fake {@link EStructuralFeature} that represents the {@link Resource#getContents()} feature.
     */
    private static class ResourceContentsEStructuralFeature extends EReferenceImpl {

        private static final String CONTENTS = "eContents";

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
    private static final class DummyRootEObject extends PersistentEObjectImpl {

        private static final String ROOT_EOBJECT_ID = "ROOT";

        public DummyRootEObject(Resource.Internal resource) {
            super(new StringId(ROOT_EOBJECT_ID));
            eSetDirectResource(resource);
        }
    }

    /**
     * A notifying {@link EStoreEList} list implementation for supporting {@link Resource#getContents}.
     */
    private class ResourceContentsEStoreEList<E> extends EStoreEObjectImpl.EStoreEList<E> {

        protected static final long serialVersionUID = 1L;

        public ResourceContentsEStoreEList(InternalEObject owner, EStructuralFeature eStructuralFeature, EStore store) {
            super(owner, eStructuralFeature, store);
        }

        @Override
        protected E validate(int index, E object) {
            checkArgument(canContainNull() || !isNull(object), "The 'no null' constraint is violated");
            return object;
        }

        @Override
        public Object getNotifier() {
            return PersistentResourceImpl.this;
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
            InternalEObject eObject = (InternalEObject) object;
            notifications = eObject.eSetResource(PersistentResourceImpl.this, notifications);
            attached(eObject);
            return notifications;
        }

        @Override
        public NotificationChain inverseRemove(E object, NotificationChain notifications) {
            InternalEObject eObject = (InternalEObject) object;
            if (isLoaded || !isNull(unloadingContents)) {
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
                internalElement.resource(PersistentResourceImpl.this);
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

        private void loaded() {
            if (!isLoaded()) {
                Notification notification = setLoaded(true);
                if (!isNull(notification)) {
                    eNotify(notification);
                }
            }
        }

        private void modified() {
            if (isTrackingModification()) {
                setModified(true);
            }
        }
    }

    private class ShutdownHook extends Thread {

        @Override
        public void run() {
            if (!persistenceBackend.isClosed()) {
                persistenceBackend.close();
                NeoLogger.info("{0} closed: {1} ", PersistenceBackend.class.getSimpleName(), uri);
            }
        }
    }
}
