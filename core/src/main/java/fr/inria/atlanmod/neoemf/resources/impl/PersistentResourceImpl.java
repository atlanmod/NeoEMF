/*******************************************************************************
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 *******************************************************************************/

package fr.inria.atlanmod.neoemf.resources.impl;

import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.core.impl.NeoEObjectAdapterFactoryImpl;
import fr.inria.atlanmod.neoemf.core.impl.PersistentEObjectImpl;
import fr.inria.atlanmod.neoemf.core.impl.StringId;
import fr.inria.atlanmod.neoemf.datastore.InternalPersistentEObject;
import fr.inria.atlanmod.neoemf.datastore.InvalidOptionsException;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackend;
import fr.inria.atlanmod.neoemf.datastore.PersistenceBackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.datastore.estores.SearcheableResourceEStore;
import fr.inria.atlanmod.neoemf.logger.NeoLogger;
import fr.inria.atlanmod.neoemf.resources.PersistentResource;
import fr.inria.atlanmod.neoemf.util.NeoURI;

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
import java.util.Objects;

public class PersistentResourceImpl extends ResourceImpl implements PersistentResource {

	private static final ResourceContentsEStructuralFeature ROOT_CONTENTS_ESTRUCTURALFEATURE = new ResourceContentsEStructuralFeature();

	private final DummyRootEObject DUMMY_ROOT_EOBJECT;

	private Map<?, ?> options;

	private SearcheableResourceEStore eStore;

	/**
	 * The underlying {@link PersistenceBackend} that stores the data
	 */
	private PersistenceBackend persistenceBackend;
	
	private boolean isPersistent;

	public PersistentResourceImpl(URI uri) {
		super(uri);
		this.DUMMY_ROOT_EOBJECT = new DummyRootEObject(this);
		this.persistenceBackend = PersistenceBackendFactoryRegistry.getFactoryProvider(uri.scheme()).createTransientBackend();
		this.eStore = PersistenceBackendFactoryRegistry.getFactoryProvider(uri.scheme()).createTransientEStore(this,persistenceBackend);
		this.isPersistent = false;
		// Stop the backend when the application is terminated
		Runtime.getRuntime().addShutdownHook(new ShutdownHook());
		NeoLogger.info("Persistent Resource Created");
	}
	
	/**
	 * Returns the graph DB file
	 * 
	 * @return
	 */
	protected File getFile() {
		return FileUtils.getFile(NeoURI.createNeoURI(getURI()).toFileString());
	}

	@Override
	public void load(Map<?, ?> options) throws IOException {
		try {
			isLoading = true;
			if (!isLoaded) {
				if (getFile().exists()) {
					this.persistenceBackend = PersistenceBackendFactoryRegistry.getFactoryProvider(uri.scheme()).createPersistentBackend(getFile(), options);
					this.eStore = PersistenceBackendFactoryRegistry.getFactoryProvider(uri.scheme()).createPersistentEStore(this, persistenceBackend, options);
					this.isPersistent = true;
					DUMMY_ROOT_EOBJECT.setMapped(true);
				} else {
					throw new FileNotFoundException(uri.toFileString());
				}
				this.options = options;
				isLoaded = true;
			}
		} finally {
			isLoading = false;
			NeoLogger.info("Persistent Resource {0} Loaded", uri);
		}
	}

	@Override
	public void save(Map<?, ?> options) throws IOException {

		if (this.options != null) {
			// Check that the save options do not collide with previous load options
			for (Entry<?, ?> entry : options.entrySet()) {
				if (this.options.containsKey(entry.getKey())
						&& entry.getValue() != null
						&& !entry.getValue().equals(this.options.get(entry.getKey())))
				{
					throw new IOException(new InvalidOptionsException(MessageFormat.format("key = {0}; value = {1}", entry.getKey().toString(), entry.getValue().toString())));
				}
			}
		}
		if(!isLoaded() || !isPersistent) {
			PersistenceBackend newBackend = PersistenceBackendFactoryRegistry.getFactoryProvider(uri.scheme()).createPersistentBackend(getFile(), options);
			PersistenceBackendFactoryRegistry.getFactoryProvider(uri.scheme()).copyBackend(persistenceBackend, newBackend);
			this.persistenceBackend = newBackend;
			this.eStore = PersistenceBackendFactoryRegistry.getFactoryProvider(uri.scheme()).createPersistentEStore(this,persistenceBackend, options);
			this.isLoaded = true;
			this.isPersistent = true;
		}
		persistenceBackend.save();
		NeoLogger.info("Persistent Resource {0} Saved", uri);
	}

	
	@Override
	// FIXME Return of instance of non-static inner class 'ResourceContentsEStoreEList'
	public EList<EObject> getContents() {
		return new ResourceContentsEStoreEList<>(DUMMY_ROOT_EOBJECT, ROOT_CONTENTS_ESTRUCTURALFEATURE, eStore());
	}

	@Override
	public EObject getEObject(String uriFragment) {
		EObject eObject = eStore.eObject(new StringId(uriFragment));
		return eObject != null ? eObject : super.getEObject(uriFragment);
	}

	@Override
	public String getURIFragment(EObject eObject) {
		String returnValue = super.getURIFragment(eObject);
		if (eObject.eResource() == this) {
			// Try to adapt as a PersistentEObject and return the ID
			PersistentEObject persistentEObject = NeoEObjectAdapterFactoryImpl.getAdapter(eObject, PersistentEObject.class);
			if (persistentEObject != null) {
				returnValue = persistentEObject.id().toString();
			}
		} else {
			returnValue = "/-1";
		}
		return returnValue;
	}
	
	@Override
	public EList<EObject> getAllInstances(EClass eClass) {
		return getAllInstances(eClass, false);
	}
	
	@Override
	public EList<EObject> getAllInstances(EClass eClass, boolean strict) {
		EList<EObject> returnValue;
	    try {
			returnValue = eStore.getAllInstances(eClass,strict);
	    } catch(UnsupportedOperationException e) {
	        NeoLogger.warn("Persistence Backend does not support advanced allInstances() computation, using standard EMF API instead");
	        EList<EObject> instanceList = new BasicEList<>();
			Iterator<EObject> it = getAllContents();
	        while(it.hasNext()) {
	            EObject eObject = it.next();
	            if(eClass.isInstance(eObject)) {
	                if(strict) {
	                    if(eObject.eClass().equals(eClass)) {
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
	

	private void shutdown() {
		persistenceBackend.stop();
		this.persistenceBackend = PersistenceBackendFactoryRegistry.getFactoryProvider(uri.scheme()).createTransientBackend();
		this.eStore = PersistenceBackendFactoryRegistry.getFactoryProvider(uri.scheme()).createTransientEStore(this,persistenceBackend);
		this.isPersistent = false;
		this.isLoaded = false;
	}

	@Override
	protected void doUnload() {
		Iterator<EObject> allContents = getAllProperContents(unloadingContents);
		getErrors().clear();
		getWarnings().clear();
		while (allContents.hasNext()) {
			unloaded((InternalEObject) allContents.next());
		}
		shutdown();
	}

	@Override
	protected void finalize() throws Throwable {
		unload();
		super.finalize();
	}

	@Override
	public InternalEObject.EStore eStore() {
		return eStore;
	}

	public static void shutdownWithoutUnload(PersistentResourceImpl resource) {
		if (resource != null) {
			NeoLogger.info("Shutdown Without Unload of Persistent Resource {0}", resource.getURI());
			resource.shutdown();
		}
	}

	/**
	 * Fake {@link EStructuralFeature} that represents the
	 * {@link Resource#getContents()} feature.
	 *
	 */
	private static class ResourceContentsEStructuralFeature extends EReferenceImpl {
		private static final String RESOURCE__CONTENTS__FEATURE_NAME = "eContents";

		public ResourceContentsEStructuralFeature() {
			setUpperBound(ETypedElement.UNBOUNDED_MULTIPLICITY);
			setLowerBound(0);
			setName(RESOURCE__CONTENTS__FEATURE_NAME);
			setEType(new EClassifierImpl() {});
			setFeatureID(RESOURCE__CONTENTS);
		}
	}

	/**
	 * Dummy {@link EObject} that represents the root entry point for this
	 * {@link Resource}
	 *
	 */
	private static final class DummyRootEObject extends PersistentEObjectImpl {
		private static final String ROOT_EOBJECT_ID = "ROOT";

		public DummyRootEObject(Resource.Internal resource) {
			super(new StringId(ROOT_EOBJECT_ID));
			eSetDirectResource(resource);
		}
	}

	/**
	 * A notifying {@link EStoreEList} list implementation for supporting
	 * {@link Resource#getContents}.
	 * 
	 * @author agomez
	 * 
	 */
	private class ResourceContentsEStoreEList<E> extends EStoreEObjectImpl.EStoreEList<E> {
		protected static final long serialVersionUID = 1L;

		public ResourceContentsEStoreEList(InternalEObject owner, EStructuralFeature eStructuralFeature, EStore store) {
			super(owner, eStructuralFeature, store);
		}

		@Override
		protected E validate(int index, E object) {
			if (!canContainNull() && object == null) {
				throw new IllegalArgumentException("The 'no null' constraint is violated");
			}
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
		protected boolean useEquals() {
			return false;
		}

		@Override
		protected boolean hasInverse() {
			return true;
		}

		@Override
		protected boolean isUnique() {
			return true;
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
			if (isLoaded || unloadingContents != null) {
				detached(eObject);
			}
			return eObject.eSetResource(null, notifications);
		}
		
		@Override
		protected void delegateAdd(int index, Object object) {
			// FIXME Maintain a list of hard links to the elements while moving
			// them to the new resource. If a garbage collection happens while
			// traversing the children elements, some unsaved objects that are
			// referenced from a saved object may be garbage collected before
			// they have been completely stored in the DB
			List<Object> hardLinksList = new ArrayList<>();
			InternalPersistentEObject eObject = Objects.requireNonNull(NeoEObjectAdapterFactoryImpl.getAdapter(object, InternalPersistentEObject.class));
			// Collect all contents
			hardLinksList.add(object);
			for (Iterator<EObject> it = eObject.eAllContents(); it.hasNext(); hardLinksList.add(it.next())) {
			}
			// Iterate using the hard links list instead the getAllContents
			// We ensure that using the hardLinksList it is not taken out by JIT
			// compiler
			for (Object element : hardLinksList) {
				InternalPersistentEObject internalElement = Objects.requireNonNull(NeoEObjectAdapterFactoryImpl.getAdapter(element, InternalPersistentEObject.class));
				internalElement.resource(PersistentResourceImpl.this);
			}
			super.delegateAdd(index, object);
		}

		@Override
		@SuppressWarnings("unchecked") // Unchecked cast: 'org.eclipse.emf.ecore.EObject' to 'E'
		protected E delegateRemove(int index) {
			E object = super.delegateRemove(index);
			List<E> hardLinksList = new ArrayList<>();
			InternalPersistentEObject eObject = Objects.requireNonNull(NeoEObjectAdapterFactoryImpl.getAdapter(object, InternalPersistentEObject.class));
			// Collect all contents
			hardLinksList.add(object);
			for (Iterator<EObject> it = eObject.eAllContents(); it.hasNext(); hardLinksList.add((E)it.next())) {
			}
			// Iterate using the hard links list instead the getAllContents
			// We ensure that using the hardLinksList it is not taken out by JIT
			// compiler
			for (E element : hardLinksList) {
				InternalPersistentEObject internalElement = Objects.requireNonNull(NeoEObjectAdapterFactoryImpl.getAdapter(element, InternalPersistentEObject.class));
				internalElement.resource(null);
			}
			return object;			
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
		protected void didSet(int index, E newObject, E oldObject) {
			super.didSet(index, newObject, oldObject);
			modified();
		}

		@Override
		protected void didClear(int oldSize, Object[] oldData) {
			if (oldSize == 0) {
				loaded();
			} else {
				super.didClear(oldSize, oldData);
			}
		}

		private void loaded() {
			if (!isLoaded()) {
				Notification notification = setLoaded(true);
				if (notification != null) {
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
            if(persistenceBackend.isStarted()) {
                NeoLogger.info("Closing backend of resource {0}", uri);
                persistenceBackend.stop();
                NeoLogger.info("Backend of resource {0} closed", uri);
            }
        }
	}
}
