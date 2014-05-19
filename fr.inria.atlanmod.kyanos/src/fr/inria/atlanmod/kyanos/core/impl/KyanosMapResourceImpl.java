/*******************************************************************************
 * Copyright (c) 2014 Abel Gómez.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Abel Gómez - initial API and implementation
 ******************************************************************************/
package fr.inria.atlanmod.kyanos.core.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.FileUtils;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
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
import org.eclipse.osgi.util.NLS;
import org.mapdb.DB;
import org.mapdb.DBMaker;

import fr.inria.atlanmod.kyanos.core.KyanosEObject;
import fr.inria.atlanmod.kyanos.core.KyanosInternalEObject;
import fr.inria.atlanmod.kyanos.core.KyanosResource;
import fr.inria.atlanmod.kyanos.datastore.estores.SearcheableResourceEStore;
import fr.inria.atlanmod.kyanos.datastore.estores.impl.DirectWriteMapResourceEStoreImpl;
import fr.inria.atlanmod.kyanos.datastore.estores.impl.IsSetCachingDelegatedEStoreImpl;
import fr.inria.atlanmod.kyanos.datastore.estores.impl.SizeCachingDelegatedEStoreImpl;
import fr.inria.atlanmod.kyanos.datastore.exceptions.InvalidOptionsException;
import fr.inria.atlanmod.kyanos.logger.Logger;
import fr.inria.atlanmod.kyanos.util.KyanosURI;

public class KyanosMapResourceImpl extends ResourceImpl implements KyanosResource {

	/**
	 * Fake {@link EStructuralFeature} that represents the
	 * {@link Resource#getContents()} feature.
	 * 
	 * @author agomez
	 * 
	 */
	protected static class ResourceContentsEStructuralFeature extends EReferenceImpl {
		protected static final String RESOURCE__CONTENTS__FEATURE_NAME = "eContents";

		public ResourceContentsEStructuralFeature() {
			this.setUpperBound(ETypedElement.UNBOUNDED_MULTIPLICITY);
			this.setLowerBound(0);
			this.setName(RESOURCE__CONTENTS__FEATURE_NAME);
			this.setEType(new EClassifierImpl() {
			});
			this.setFeatureID(RESOURCE__CONTENTS);
		}
	}

	/**
	 * Dummy {@link EObject} that represents the root entry point for this
	 * {@link Resource}
	 * 
	 * @author agomez
	 * 
	 */
	protected final class DummyRootEObject extends KyanosEObjectImpl {
		protected static final String ROOT_EOBJECT_ID = "ROOT";

		public DummyRootEObject(Resource.Internal resource) {
			super();
			this.id = ROOT_EOBJECT_ID;
			eSetDirectResource(resource);
		}
	}

	protected static final ResourceContentsEStructuralFeature ROOT_CONTENTS_ESTRUCTURALFEATURE = new ResourceContentsEStructuralFeature();

	protected final DummyRootEObject DUMMY_ROOT_EOBJECT = new DummyRootEObject(this);

	protected Map<?, ?> options;

	protected SearcheableResourceEStore eStore;

	protected DB db;
	
	protected boolean isPersistent = false;

	public KyanosMapResourceImpl(URI uri) {
		super(uri);
		this.db = DBMaker.newMemoryDB().closeOnJvmShutdown().make();
		this.eStore = new DirectWriteMapResourceEStoreImpl(this, db);
		this.isPersistent = false;
	}

	/**
	 * Returns the graph DB file
	 * 
	 * @return
	 */
	protected File getFile() {
		return FileUtils.getFile(KyanosURI.createKyanosURI(getURI().appendSegment("kyanos.mapdb")).toFileString());
	}

	@Override
	public void load(Map<?, ?> options) throws IOException {
		try {
			isLoading = true;
			if (isLoaded) {
				return;
			} else if (!getFile().exists()) {
				throw new FileNotFoundException(uri.toFileString());
			} else {
				this.db = DBMaker.newFileDB(getFile()).cacheSoftRefEnable().closeOnJvmShutdown().mmapFileEnableIfSupported().asyncWriteEnable().make();
				this.isPersistent = true;
				this.eStore = createResourceEStore(db);
			}
			this.options = options;
			isLoaded = true;
		} finally {
			isLoading = false;
		}
	}

	@Override
	public void save(Map<?, ?> options) throws IOException {

		if (this.options != null) {
			// Check that the save options do not collide with previous load options
			for (Entry<?, ?> entry : options.entrySet()) {
				Object key = entry.getKey();
				Object value = entry.getValue();
				if (this.options.containsKey(key) && value != null) {
					if (!value.equals(this.options.get(key))) {
						throw new IOException(new InvalidOptionsException(MessageFormat.format("key = {0}; value = {1}", key.toString(), value.toString())));
					}
				}
			}
		}

		if (!isLoaded() || !this.isPersistent) {
			if (!getFile().getParentFile().exists()) {
				getFile().getParentFile().mkdirs();
			}
			DB newDb =  DBMaker.newFileDB(getFile()).cacheSoftRefEnable().closeOnJvmShutdown().mmapFileEnableIfSupported().asyncWriteEnable().make();
			if (!newDb.getCatalog().isEmpty()) {
				Logger.log(Logger.SEVERITY_WARNING, 
						NLS.bind("Saving on existing graph {0} without previously loading its contents. "
								+ "Graph contents will be lost.", getFile().toString()));
				for (Entry<String, Object> entry : newDb.getCatalog().entrySet()) {
					newDb.delete(entry.getKey());
				}
			}
			// TODO: Copy in memory map to persistent map
			this.db = newDb;
			this.isPersistent = true;
			this.eStore = createResourceEStore(this.db);
			this.isLoaded = true;
		}

		db.commit();
	}

	@Override
	public EList<EObject> getContents() {
		return new ResourceContentsEStoreEList(DUMMY_ROOT_EOBJECT, ROOT_CONTENTS_ESTRUCTURALFEATURE, eStore());
	}

	@Override
	public EObject getEObject(String uriFragment) {
		EObject eObject = eStore.getEObject(uriFragment);
		if (eObject != null) {
			return eObject;
		} else {
			return super.getEObject(uriFragment);
		}
	}

	@Override
	public String getURIFragment(EObject eObject) {
		if (eObject.eResource() != this) {
			return "/-1";
		} else {
			// Try to adapt as a KyanosEObject and return the ID
			KyanosEObject kyanosEObject = KyanosEObjectAdapterFactoryImpl.getAdapter(eObject, KyanosEObject.class);
			if (kyanosEObject != null) {
				return (kyanosEObject.kyanosId());
			}
		}
		return super.getURIFragment(eObject);
	}

	protected void shutdown() {
		this.db.close();
		this.db = DBMaker.newMemoryDB().closeOnJvmShutdown().make();
		this.eStore = new DirectWriteMapResourceEStoreImpl(this, db);
		this.isPersistent = false;
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

	/**
	 * Creates the {@link SearcheableResourceEStore} used by this
	 * {@link Resource}.
	 * 
	 * @param graph
	 * @return
	 */
	protected SearcheableResourceEStore createResourceEStore(DB db) {
		return new IsSetCachingDelegatedEStoreImpl(new SizeCachingDelegatedEStoreImpl(new DirectWriteMapResourceEStoreImpl(this, db)));
	}

	/**
	 * A notifying {@link EStoreEList} list implementation for supporting
	 * {@link Resource#getContents}.
	 * 
	 * @author agomez
	 * 
	 */
	protected class ResourceContentsEStoreEList extends EStoreEObjectImpl.EStoreEList<EObject> {
		protected static final long serialVersionUID = 1L;

		protected ResourceContentsEStoreEList(InternalEObject owner, EStructuralFeature eStructuralFeature, EStore store) {
			super(owner, eStructuralFeature, store);
		}

		@Override
		protected EObject validate(int index, EObject object) {
			if (!canContainNull() && object == null) {
				throw new IllegalArgumentException("The 'no null' constraint is violated");
			}
			return object;
		}

		@Override
		public Object getNotifier() {
			return KyanosMapResourceImpl.this;
		}

		@Override
		public int getFeatureID() {
			return RESOURCE__CONTENTS;
		}

		@Override
		protected boolean isNotificationRequired() {
			return KyanosMapResourceImpl.this.eNotificationRequired();
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
		public NotificationChain inverseAdd(EObject object, NotificationChain notifications) {
			InternalEObject eObject = (InternalEObject) object;
			notifications = eObject.eSetResource(KyanosMapResourceImpl.this, notifications);
			KyanosMapResourceImpl.this.attached(eObject);
			return notifications;
		}

		@Override
		public NotificationChain inverseRemove(EObject object, NotificationChain notifications) {
			InternalEObject eObject = (InternalEObject) object;
			if (KyanosMapResourceImpl.this.isLoaded || unloadingContents != null) {
				KyanosMapResourceImpl.this.detached(eObject);
			}
			return eObject.eSetResource(null, notifications);
		}
		
		@Override
		protected void delegateAdd(int index, EObject object) {
			// FIXME? Maintain a list of hard links to the elements while moving
			// them to the new resource. If a garbage collection happens while
			// traversing the children elements, some unsaved objects that are
			// referenced from a saved object may be garbage collected before
			// they have been completely stored in the DB
			List<EObject> hardLinksList = new ArrayList<>();
			KyanosInternalEObject eObject = KyanosEObjectAdapterFactoryImpl.getAdapter(object, KyanosInternalEObject.class);
			// Collect all contents
			hardLinksList.add(object);
			for (Iterator<EObject> it = eObject.eAllContents(); it.hasNext(); hardLinksList.add(it.next()));
			// Iterate using the hard links list instead the getAllContents
			// We ensure that using the hardLinksList it is not taken out by JIT
			// compiler
			for (EObject element : hardLinksList) {
				KyanosInternalEObject internalElement = KyanosEObjectAdapterFactoryImpl.getAdapter(element, KyanosInternalEObject.class);
				internalElement.kyanosSetResource(KyanosMapResourceImpl.this);
			}
			super.delegateAdd(index, object);
		}

		@Override
		protected EObject delegateRemove(int index) {
			EObject object = super.delegateRemove(index);
			List<EObject> hardLinksList = new ArrayList<>();
			KyanosInternalEObject eObject = KyanosEObjectAdapterFactoryImpl.getAdapter(object, KyanosInternalEObject.class);
			// Collect all contents
			hardLinksList.add(object);
			for (Iterator<EObject> it = eObject.eAllContents(); it.hasNext(); hardLinksList.add(it.next()));
			// Iterate using the hard links list instead the getAllContents
			// We ensure that using the hardLinksList it is not taken out by JIT
			// compiler
			for (EObject element : hardLinksList) {
				KyanosInternalEObject internalElement = KyanosEObjectAdapterFactoryImpl.getAdapter(element, KyanosInternalEObject.class);
				internalElement.kyanosSetResource(null);
			}
			return object;			
		}
		
		@Override
		protected void didAdd(int index, EObject object) {
			super.didAdd(index, object);
			if (index == size() - 1) {
				loaded();
			}
			modified();
		}

		@Override
		protected void didRemove(int index, EObject object) {
			super.didRemove(index, object);
			modified();
		}

		@Override
		protected void didSet(int index, EObject newObject, EObject oldObject) {
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

		protected void loaded() {
			if (!KyanosMapResourceImpl.this.isLoaded()) {
				Notification notification = KyanosMapResourceImpl.this.setLoaded(true);
				if (notification != null) {
					KyanosMapResourceImpl.this.eNotify(notification);
				}
			}
		}

		protected void modified() {
			if (isTrackingModification()) {
				setModified(true);
			}
		}
	}

	public static void shutdownWithoutUnload(KyanosMapResourceImpl resource) {
		resource.shutdown();
	}
}
