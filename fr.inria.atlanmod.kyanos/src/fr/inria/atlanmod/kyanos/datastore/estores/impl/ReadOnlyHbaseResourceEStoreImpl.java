/*******************************************************************************
 * Copyright (c) 2014 Abel Gomez.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Abel Gomez - initial API and implementation
 *     Amine Benelallam - implementation
 ******************************************************************************/
package fr.inria.atlanmod.kyanos.datastore.estores.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.util.Bytes;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.jboss.util.collection.SoftValueHashMap;

import fr.inria.atlanmod.kyanos.core.KyanosEObject;
import fr.inria.atlanmod.kyanos.core.KyanosInternalEObject;
import fr.inria.atlanmod.kyanos.core.impl.KyanosEObjectAdapterFactoryImpl;
import fr.inria.atlanmod.kyanos.datastore.estores.SearcheableResourceEStore;
import fr.inria.atlanmod.kyanos.datastore.estores.impl.TransientEStoreImpl.EStoreEntryKey;
import fr.inria.atlanmod.kyanos.logger.Logger;
import fr.inria.atlanmod.kyanos.util.KyanosUtil;

public class ReadOnlyHbaseResourceEStoreImpl implements SearcheableResourceEStore {


	protected static final byte[] PROPERTY_FAMILY =				Bytes.toBytes("p");
	protected static final byte[] TYPE_FAMILY = 				Bytes.toBytes("t");
	protected static final byte[] METAMODEL_QUALIFIER =			Bytes.toBytes("m");
	protected static final byte[] ECLASS_QUALIFIER = 			Bytes.toBytes("e");
	protected static final byte[] CONTAINMENT_FAMILY = 			Bytes.toBytes("c");
	protected static final byte[] CONTAINER_QUALIFIER = 		Bytes.toBytes("n");
	protected static final byte[] CONTAINING_FEATURE_QUALIFIER = Bytes.toBytes("g");
	
	@SuppressWarnings("unchecked")
	protected Map<Object, KyanosInternalEObject> loadedEObjects = new SoftValueHashMap();
	
	//protected Connection connection;
	
	protected HTable table;
	
	protected Resource.Internal resource;
	
	protected static Configuration conf = HBaseConfiguration.create();

	final String UNSUP_MSG = "Unable to write to resource with URI {0}. Make sure that the resource is not read-only";
	
	// theses maps are not synchronized
		protected Map<EStoreEntryKey, String> singleMap = new HashMap<EStoreEntryKey, String>();
		protected Map<EStoreEntryKey, List<Object>> manyMap = new HashMap<EStoreEntryKey, List<Object>>();


		
		public class EStoreEntryKey {

			protected String eObject;
			protected EStructuralFeature eStructuralFeature;

			public EStoreEntryKey(String eObject, EStructuralFeature eStructuralFeature) {
				this.eObject = eObject;
				this.eStructuralFeature = eStructuralFeature;
			}

			@Override
			public int hashCode() {
				final int prime = 31;
				int result = 1;
				result = prime * result + getOuterType().hashCode();
				result = prime * result + ((eObject == null) ? 0 : eObject.hashCode());
				result = prime * result + ((eStructuralFeature == null) ? 0 : eStructuralFeature.hashCode());
				return result;
			}

			@Override
			public boolean equals(Object obj) {
				if (this == obj)
					return true;
				if (obj == null)
					return false;
				if (getClass() != obj.getClass())
					return false;
				EStoreEntryKey other = (EStoreEntryKey) obj;
				if (!getOuterType().equals(other.getOuterType()))
					return false;
				if (eObject == null) {
					if (other.eObject != null)
						return false;
				} else if (!eObject.equals(other.eObject))
					return false;
				if (eStructuralFeature == null) {
					if (other.eStructuralFeature != null)
						return false;
				} else if (!eStructuralFeature.equals(other.eStructuralFeature))
					return false;
				return true;
			}

			private SearcheableResourceEStore getOuterType() {
				return ReadOnlyHbaseResourceEStoreImpl.this;
			}

			public String getEObject() {
				return eObject;
			}

			public EStructuralFeature getEStructuralFeature() {
				return eStructuralFeature;
			}
		}

	
	public ReadOnlyHbaseResourceEStoreImpl(Resource.Internal resource) throws IOException {

		this.resource = resource;
		
		conf.set("hbase.zookeeper.quorum", resource.getURI().host());
		conf.set("hbase.zookeeper.property.clientPort", resource.getURI().port() != null ? resource.getURI().port() : "2181");
		
		TableName tableName = TableName.valueOf(KyanosUtil.formatURI(resource.getURI()));
		@SuppressWarnings("resource")
		HBaseAdmin admin = new HBaseAdmin(conf);
		
		if (! admin.tableExists(tableName)) {
			throw new IOException(MessageFormat.format("Resource with URI {0} does not exist", tableName.getNameAsString()));
		}
		
		table = new HTable(conf, tableName);
	}


	@Override
	public Resource.Internal getResource() {
		return resource;
	}


	@Override
	public Object get(InternalEObject object, EStructuralFeature feature, int index) {
		KyanosEObject kyanosEObject = KyanosEObjectAdapterFactoryImpl.getAdapter(object, KyanosEObject.class);
		if (feature instanceof EAttribute) {
			return get(kyanosEObject, (EAttribute) feature, index);
		} else if (feature instanceof EReference) {
			return ((EReference) feature).isContainer() ? getContainer(object) : get(kyanosEObject, (EReference) feature, index);
		} else {
			throw new IllegalArgumentException(feature.toString());
		}
	}
	
	protected Object get(KyanosEObject object, EAttribute eAttribute, int index) {
		Object value = getFromTable(object, eAttribute);
		if (!eAttribute.isMany()) {
			return parseValue(eAttribute, (String) value);
		} else {
			String[] array = (String[]) value;
			return parseValue(eAttribute, array[index]);
		}
	}

	protected Object get(KyanosEObject object, EReference eReference, int index) {
		Object value = getFromTable(object, eReference);
		if (!eReference.isMany()) {
			return getEObject((String) value);
		} else {
			String[] array = (String[]) value;
			return getEObject(array[index]);
		}
	}


	@Override
	public Object set(InternalEObject object, EStructuralFeature feature, int index, Object value) {
		throw new UnsupportedOperationException(MessageFormat.format(UNSUP_MSG, table.getName().getNameAsString()));
	}

	protected Object set(KyanosEObject object, EAttribute eAttribute, int index, Object value) {
		throw new UnsupportedOperationException(MessageFormat.format(UNSUP_MSG, table.getName().getNameAsString()));
	}

	protected Object set(KyanosEObject object, EReference eReference, int index, KyanosInternalEObject referencedObject) {
		throw new UnsupportedOperationException(MessageFormat.format(UNSUP_MSG, table.getName().getNameAsString()));
	}


	@Override
	public boolean isSet(InternalEObject object, EStructuralFeature feature) {
		KyanosEObject kyanosEObject = KyanosEObjectAdapterFactoryImpl.getAdapter(object, KyanosEObject.class);
		try {
			Result result= table.get(new Get(Bytes.toBytes(kyanosEObject.kyanosId())));
			byte[] value = result.getValue(PROPERTY_FAMILY, Bytes.toBytes(feature.getName()));
			return value != null;
		} catch (IOException e) {
			Logger.log(Logger.SEVERITY_ERROR, 
					MessageFormat.format("Unable to get information for element ''{0}''", kyanosEObject));
		}
		return false;
	}


	@Override
	public void add(InternalEObject object, EStructuralFeature feature, int index, Object value) {
		throw new UnsupportedOperationException(MessageFormat.format(UNSUP_MSG, table.getName().getNameAsString()));
	}

	protected void add(KyanosEObject object, EAttribute eAttribute, int index, Object value) {
		throw new UnsupportedOperationException(MessageFormat.format(UNSUP_MSG, table.getName().getNameAsString()));
	}

	protected void add(KyanosEObject object, EReference eReference, int index, KyanosInternalEObject referencedObject) {
		throw new UnsupportedOperationException(MessageFormat.format(UNSUP_MSG, table.getName().getNameAsString()));

	}

	@Override
	public Object remove(InternalEObject object, EStructuralFeature feature, int index) {
		// TODO nothing guarantees that the index is still the same 
		KyanosEObject kyanosEObject = KyanosEObjectAdapterFactoryImpl.getAdapter(object, KyanosEObject.class);
		if (feature instanceof EAttribute) {
			return remove(kyanosEObject, (EAttribute) feature, index);
		} else if (feature instanceof EReference) {
			return remove(kyanosEObject, (EReference) feature, index);
		} else {
			throw new IllegalArgumentException(feature.toString());
		}
	}

	protected Object remove(KyanosEObject object, EAttribute eAttribute, int index) {
		throw new UnsupportedOperationException(MessageFormat.format(UNSUP_MSG, table.getName().getNameAsString()));
	}

	protected Object remove(KyanosEObject object, EReference eReference, int index) {
		
		throw new UnsupportedOperationException(MessageFormat.format(UNSUP_MSG, table.getName().getNameAsString()));
	}

	@Override
	public Object move(InternalEObject object, EStructuralFeature feature, int targetIndex, int sourceIndex) {
		throw new UnsupportedOperationException(MessageFormat.format(UNSUP_MSG, table.getName().getNameAsString()));
	}


	@Override
	public void unset(InternalEObject object, EStructuralFeature feature) {
		throw new UnsupportedOperationException(MessageFormat.format(UNSUP_MSG, table.getName().getNameAsString()));
	}


	@Override
	public boolean isEmpty(InternalEObject object, EStructuralFeature feature) {
		return size(object, feature) == 0; 
	}


	@Override
	public int size(InternalEObject object, EStructuralFeature feature) {
		KyanosEObject kyanosEObject = KyanosEObjectAdapterFactoryImpl.getAdapter(object, KyanosEObject.class);
		String[] array = (String[]) getFromTable(kyanosEObject, feature);
		return array != null ? array.length : 0; 
	}


	@Override
	public boolean contains(InternalEObject object, EStructuralFeature feature, Object value) {
		return indexOf(object, feature, value) != -1;
	}


	@Override
	public int indexOf(InternalEObject object, EStructuralFeature feature, Object value) {
		KyanosEObject kyanosEObject = KyanosEObjectAdapterFactoryImpl.getAdapter(object, KyanosEObject.class);
		String[] array = (String[]) getFromTable(kyanosEObject, feature);
		if (array == null) {
			return -1;
		}
		if (feature instanceof EAttribute) {
			return ArrayUtils.indexOf(array, serializeValue((EAttribute) feature, value));
		} else {
			KyanosEObject childEObject = KyanosEObjectAdapterFactoryImpl.getAdapter(value, KyanosEObject.class);
			return ArrayUtils.indexOf(array, childEObject.kyanosId());
		}
	}


	@Override
	public int lastIndexOf(InternalEObject object, EStructuralFeature feature, Object value) {
		KyanosEObject kyanosEObject = KyanosEObjectAdapterFactoryImpl.getAdapter(object, KyanosEObject.class);
		String[] array = (String[]) getFromTable(kyanosEObject, feature);
		if (array == null) {
			return -1;
		}
		if (feature instanceof EAttribute) {
			return ArrayUtils.lastIndexOf(array, serializeValue((EAttribute) feature, value));
		} else {
			KyanosEObject childEObject = KyanosEObjectAdapterFactoryImpl.getAdapter(value, KyanosEObject.class);
			return ArrayUtils.lastIndexOf(array, childEObject.kyanosId());
		}
	}


	@Override
	public void clear(InternalEObject object, EStructuralFeature feature) {
		throw new UnsupportedOperationException(MessageFormat.format(UNSUP_MSG, table.getName().getNameAsString()));
	}

	
	@Override
	public Object[] toArray(InternalEObject object, EStructuralFeature feature) {
		int size = size(object, feature);
		Object[] result = new Object[size];
		for (int index = 0; index < size; index++) {
			result[index] = get(object, feature, index);
		}
		return result;
	}


	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] toArray(InternalEObject object, EStructuralFeature feature, T[] array) {
		int size = size(object, feature);
		T[] result = null;
		if (array.length < size) {
			result = Arrays.copyOf(array, size);
		} else {
			result = array;
		}
		for (int index = 0; index < size; index++) {
			result[index] = (T) get(object, feature, index);
		}
		return result;
	}


	@Override
	public int hashCode(InternalEObject object, EStructuralFeature feature) {
		return toArray(object, feature).hashCode();
	}


	@Override
	public InternalEObject getContainer(InternalEObject object) {
		KyanosEObject kyanosEObject = KyanosEObjectAdapterFactoryImpl.getAdapter(object, KyanosEObject.class);
		
		try {
			Result result= table.get(new Get(Bytes.toBytes(kyanosEObject.kyanosId())));
			String containerId = Bytes.toString(result.getValue(CONTAINMENT_FAMILY, CONTAINER_QUALIFIER));
			String containingFeatureName = Bytes.toString(result.getValue(CONTAINMENT_FAMILY, CONTAINING_FEATURE_QUALIFIER));
	
			if (containerId != null && containingFeatureName != null) {
				return (InternalEObject) getEObject(containerId);
			}
			
		} catch (IOException e) {
			Logger.log(Logger.SEVERITY_ERROR, 
					MessageFormat.format("Unable to get containment information for {0}", kyanosEObject));
		}
		return null;
	}


	@Override
	public EStructuralFeature getContainingFeature(InternalEObject object) {
		KyanosEObject kyanosEObject = KyanosEObjectAdapterFactoryImpl.getAdapter(object, KyanosEObject.class);
		
		try {
			Result result= table.get(new Get(Bytes.toBytes(kyanosEObject.kyanosId())));
			String containerId = Bytes.toString(result.getValue(CONTAINMENT_FAMILY, CONTAINER_QUALIFIER));
			String containingFeatureName = Bytes.toString(result.getValue(CONTAINMENT_FAMILY, CONTAINING_FEATURE_QUALIFIER));
	
			if (containerId != null && containingFeatureName != null) {
				EObject container = getEObject(containerId);
				return container.eClass().getEStructuralFeature(containingFeatureName);
			}
			
		} catch (IOException e) {
			Logger.log(Logger.SEVERITY_ERROR, 
					MessageFormat.format("Unable to get containment information for {0}", kyanosEObject));
		}
		return null;
	}


	@Override
	public EObject create(EClass eClass) {
		// This should not be called
		throw new UnsupportedOperationException();
	}


	@Override
	public EObject getEObject(String id) {
		if (id == null) {
			return null;
		}
		KyanosInternalEObject kyanosEObject = loadedEObjects.get(id);
		if (kyanosEObject == null) {
			EClass eClass = resolveInstanceOf(id);
			if (eClass != null) {
				EObject eObject = EcoreUtil.create(eClass);
				if (eObject instanceof KyanosInternalEObject) {
					kyanosEObject = (KyanosInternalEObject) eObject;
				} else {
					kyanosEObject = KyanosEObjectAdapterFactoryImpl.getAdapter(eObject, KyanosInternalEObject.class);
				}
				kyanosEObject.kyanosSetId(id.toString());
			} else {
				Logger.log(Logger.SEVERITY_ERROR, 
						MessageFormat.format("Element {0} does not have an associated EClass", id));
			}
			loadedEObjects.put(id, kyanosEObject);
		}
		if (kyanosEObject.kyanosResource() != getResource()) {
			kyanosEObject.kyanosSetResource(getResource());
		}
		return kyanosEObject;
	}
	

	protected EClass resolveInstanceOf(String id) {
		try {
			Result result= table.get(new Get(Bytes.toBytes(id)));
			String nsURI = Bytes.toString(result.getValue(TYPE_FAMILY, METAMODEL_QUALIFIER));
			String className = Bytes.toString(result.getValue(TYPE_FAMILY, ECLASS_QUALIFIER));
			if (nsURI != null && className != null) {
				EClass eClass = (EClass) Registry.INSTANCE.getEPackage(nsURI).getEClassifier(className);
				return eClass;
			}
		} catch (IOException e) {
			Logger.log(Logger.SEVERITY_ERROR, 
					MessageFormat.format("Unable to get instance of information for {0}", id));
		}
		return null;
	}
	
	protected void updateLoadedEObjects(KyanosInternalEObject eObject) {
		loadedEObjects.put(eObject.kyanosId(), eObject);
	}
	
	protected void updateContainment(KyanosEObject object, EReference eReference, KyanosEObject referencedObject) {
		throw new UnsupportedOperationException();
	}
	
	protected void updateInstanceOf(KyanosEObject object) {
		throw new UnsupportedOperationException(MessageFormat.format(UNSUP_MSG, table.getName().getNameAsString()));
	}


	protected static Object parseValue(EAttribute eAttribute, String value) {
		return value != null ? EcoreUtil.createFromString(eAttribute.getEAttributeType(), value) : null;
	}

	protected static String serializeValue(EAttribute eAttribute, Object value) {
		return value != null ? EcoreUtil.convertToString(eAttribute.getEAttributeType(), value) : null;
	}
	
	/**
	 * Gets the {@link EStructuralFeature} {@code feature} from the
	 * {@link Table} for the {@link KyanosEObject} {@code object}
	 * 
	 * @param object
	 * @param feature
	 * @return The value of the {@code feature}. It can be a {@link String} for
	 *         single-valued {@link EStructuralFeature}s or a {@link String}[]
	 *         for many-valued {@link EStructuralFeature}s
	 */
	protected Object getFromTable(KyanosEObject object, EStructuralFeature feature) {
		try {
			Result result = table.get(new Get(Bytes.toBytes(object.kyanosId())));
			byte[] value = result.getValue(PROPERTY_FAMILY, Bytes.toBytes(feature.getName()));
			if (!feature.isMany()) {
				return Bytes.toString(value);
			} else {
				return toStrings(value);
			}
		} catch (IOException e) {
			Logger.log(Logger.SEVERITY_ERROR, MessageFormat.format("Unable to get property ''{0}'' for ''{1}''", feature.getName(), object));
		}
		return null;
	}

	protected static byte[] toBytes(String[] strings) {
		try {
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
			objectOutputStream.writeObject(strings);
			objectOutputStream.flush();
			objectOutputStream.close();
			return byteArrayOutputStream.toByteArray();
		} catch (IOException e) {
			Logger.log(Logger.SEVERITY_ERROR, MessageFormat.format("Unable to convert ''{0}'' to byte[]", strings.toString()));
		}
		return null;
	}

	protected static String[] toStrings(byte[] bytes) {
		if (bytes == null) {
			return null;
		}
		String[] result = null;
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
		ObjectInputStream objectInputStream = null;
		try {
			objectInputStream = new ObjectInputStream(byteArrayInputStream);
			result = (String[]) objectInputStream.readObject();

		} catch (IOException e) {
			Logger.log(Logger.SEVERITY_ERROR, MessageFormat.format("Unable to convert ''{0}'' to String[]", bytes.toString()));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(objectInputStream);
		}
		return result;

	}
}
