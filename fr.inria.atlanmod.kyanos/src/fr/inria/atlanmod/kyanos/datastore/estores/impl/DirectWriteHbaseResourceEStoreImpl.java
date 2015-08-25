/*******************************************************************************
 * Copyright (c) 2014 Abel G�mez.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Abel G�mez - initial API and implementation
 ******************************************************************************/
package fr.inria.atlanmod.kyanos.datastore.estores.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.eclipse.emf.common.util.URI;
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
import fr.inria.atlanmod.kyanos.logger.Logger;

public class DirectWriteHbaseResourceEStoreImpl implements SearcheableResourceEStore {

//	protected static final String INSTANCE_OF = "kyanosInstanceOf";
//	protected static final String CONTAINER = "eContainer";

	protected static final byte[] PROPERTY_FAMILY =				Bytes.toBytes("p");
	protected static final byte[] TYPE_FAMILY = 				Bytes.toBytes("t");
	protected static final byte[] METAMODEL_QUALIFIER =			Bytes.toBytes("m");
	protected static final byte[] ECLASS_QUALIFIER = 			Bytes.toBytes("e");
	protected static final byte[] CONTAINMENT_FAMILY = 			Bytes.toBytes("c");
	protected static final byte[] CONTAINER_QUALIFIER = 		Bytes.toBytes("n");
	protected static final byte[] CONTAINING_FEATURE_QUALIFIER = Bytes.toBytes("g");

//	// TODO: Change in final version by short version to save space
//	protected static final byte[] PROPERTY_FAMILY =				Bytes.toBytes("property");
//	protected static final byte[] TYPE_FAMILY = 				Bytes.toBytes("type");
//	protected static final byte[] METAMODEL_QUALIFIER =			Bytes.toBytes("metamodel");
//	protected static final byte[] ECLASS_QUALIFIER = 			Bytes.toBytes("eclass");
//	protected static final byte[] CONTAINMENT_FAMILY = 			Bytes.toBytes("containment");
//	protected static final byte[] CONTAINER_QUALIFIER = 		Bytes.toBytes("container");
//	protected static final byte[] CONTAINING_FEATURE_QUALIFIER = Bytes.toBytes("containingFeature");

	
	@SuppressWarnings("unchecked")
	protected Map<Object, KyanosInternalEObject> loadedEObjects = new SoftValueHashMap();
	
	protected Connection connection;
	
	protected Table table;
	
	protected Resource.Internal resource;

	public DirectWriteHbaseResourceEStoreImpl(Resource.Internal resource, Connection connection) throws IOException {
		this.connection = connection;
		this.resource = resource;
		
		TableName tableName = TableName.valueOf(formatURI(resource.getURI()));
		
		if (!connection.getAdmin().tableExists(tableName)) {
			HTableDescriptor desc = new HTableDescriptor(tableName);
			HColumnDescriptor propertyFamily = new HColumnDescriptor(PROPERTY_FAMILY);
			HColumnDescriptor typeFamily = new HColumnDescriptor(TYPE_FAMILY);
			HColumnDescriptor containmentFamily = new HColumnDescriptor(CONTAINMENT_FAMILY);
			desc.addFamily(propertyFamily);
			desc.addFamily(typeFamily);
			desc.addFamily(containmentFamily);
			connection.getAdmin().createTable(desc);
		}
		
		table = connection.getTable(tableName);
	}


	private byte[] formatURI(URI uri) {
		// TODO Auto-generated method stub
		return null;
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
		KyanosEObject kyanosEObject = KyanosEObjectAdapterFactoryImpl.getAdapter(object, KyanosEObject.class);
		if (feature instanceof EAttribute) {
			return set(kyanosEObject, (EAttribute) feature, index, value);
		} else if (feature instanceof EReference) {
			KyanosInternalEObject referencedEObject = KyanosEObjectAdapterFactoryImpl.getAdapter(value, KyanosInternalEObject.class);
			return set(kyanosEObject, (EReference) feature, index, referencedEObject);
		} else {
			throw new IllegalArgumentException(feature.toString());
		}
	}

	protected Object set(KyanosEObject object, EAttribute eAttribute, int index, Object value) {
		Object oldValue = isSet((InternalEObject) object, eAttribute) ? get(object, eAttribute, index) :  null;
		try {
			if (!eAttribute.isMany()) {
				Put put = new Put(Bytes.toBytes(object.kyanosId()));
				put.addColumn(PROPERTY_FAMILY, Bytes.toBytes(eAttribute.getName()), Bytes.toBytes(serializeValue(eAttribute, value)));
				table.put(put);
			} else {
				String[] array = (String[]) getFromTable(object, eAttribute);
				array[index] = serializeValue(eAttribute, value);
				Put put = new Put(Bytes.toBytes(object.kyanosId()));
				put.addColumn(PROPERTY_FAMILY, Bytes.toBytes(eAttribute.getName()), toBytes(array));
				table.put(put);
			}
		} catch (IOException e) {
			Logger.log(Logger.SEVERITY_ERROR, 
					MessageFormat.format("Unable to set information for element ''{0}''", object));
		}
		return oldValue;
	}

	protected Object set(KyanosEObject object, EReference eReference, int index, KyanosInternalEObject referencedObject) {
		Object oldValue = isSet((InternalEObject) object, eReference) ? get(object, eReference, index) :  null;
		if (referencedObject == null)
			System.out.println("");
		updateLoadedEObjects(referencedObject);
		updateContainment(object, eReference, referencedObject);
		updateInstanceOf(referencedObject);
		
		try {
			if (!eReference.isMany()) {
				Put put = new Put(Bytes.toBytes(object.kyanosId()));
				put.addColumn(PROPERTY_FAMILY, Bytes.toBytes(eReference.getName()), Bytes.toBytes(referencedObject.kyanosId()));
				table.put(put);
			} else {
				String[] array = (String[]) getFromTable(object, eReference);
				array[index] = referencedObject.kyanosId();
				Put put = new Put(Bytes.toBytes(object.kyanosId()));
				put.addColumn(PROPERTY_FAMILY, Bytes.toBytes(eReference.getName()), toBytes(array));
				table.put(put);
			}
		} catch (IOException e) {
			Logger.log(Logger.SEVERITY_ERROR, 
					MessageFormat.format("Unable to set information for element ''{0}''", object));
		}
		return oldValue;
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
		KyanosEObject kyanosEObject = KyanosEObjectAdapterFactoryImpl.getAdapter(object, KyanosEObject.class);
		if (feature instanceof EAttribute) {
			add(kyanosEObject, (EAttribute) feature, index, value);
		} else if (feature instanceof EReference) {
			KyanosInternalEObject referencedEObject = KyanosEObjectAdapterFactoryImpl.getAdapter(value, KyanosInternalEObject.class);
			add(kyanosEObject, (EReference) feature, index, referencedEObject);
		} else {
			throw new IllegalArgumentException(feature.toString());
		}
	}

	protected void add(KyanosEObject object, EAttribute eAttribute, int index, Object value) {
		try {
			String[] array = (String[]) getFromTable(object, eAttribute);
			if (array == null) {
				array = new String[] {};
			}
			array = (String[]) ArrayUtils.add(array, index, serializeValue(eAttribute, value));
			Put put = new Put(Bytes.toBytes(object.kyanosId()));
			put.addColumn(PROPERTY_FAMILY, Bytes.toBytes(eAttribute.getName()), toBytes(array));
			table.put(put);
		} catch (IOException e) {
			Logger.log(Logger.SEVERITY_ERROR, 
					MessageFormat.format("Unable to add ''{0}'' to ''{1}'' for element ''{2}''", value, eAttribute.getName(), object));
		}
	}

	protected void add(KyanosEObject object, EReference eReference, int index, KyanosInternalEObject referencedObject) {
		try {
			updateLoadedEObjects(referencedObject);
			updateContainment(object, eReference, referencedObject);
			updateInstanceOf(referencedObject);
			String[] array = (String[]) getFromTable(object, eReference);
			if (array == null) {
				array = new String[] {};
			}
			array = (String[]) ArrayUtils.add(array, index, referencedObject.kyanosId());
			Put put = new Put(Bytes.toBytes(object.kyanosId()));
			put.addColumn(PROPERTY_FAMILY, Bytes.toBytes(eReference.getName()), toBytes(array));
			table.put(put);
		} catch (IOException e) {
			Logger.log(Logger.SEVERITY_ERROR, 
					MessageFormat.format("Unable to add ''{0}'' to ''{1}'' for element ''{2}''", referencedObject, eReference.getName(), object));
		}

	}

	@Override
	public Object remove(InternalEObject object, EStructuralFeature feature, int index) {
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
		Object oldValue = get(object, eAttribute, index);
		try {
			String[] array = (String[]) getFromTable(object, eAttribute);
			array = (String[]) ArrayUtils.remove(array, index);
			Put put = new Put(Bytes.toBytes(object.kyanosId()));
			put.addColumn(PROPERTY_FAMILY, Bytes.toBytes(eAttribute.getName()), toBytes(array));
			table.put(put);
		} catch (IOException e) {
			Logger.log(Logger.SEVERITY_ERROR, 
					MessageFormat.format("Unable to delete ''{0}[{1}''] for element ''{2}''", eAttribute.getName(), index, object));
		}
		return oldValue;
	}

	protected Object remove(KyanosEObject object, EReference eReference, int index) {
		
		Object oldValue = get(object, eReference, index);
		try {
			String[] array = (String[]) getFromTable(object, eReference);
			array = (String[]) ArrayUtils.remove(array, index);
			Put put = new Put(Bytes.toBytes(object.kyanosId()));
			put.addColumn(PROPERTY_FAMILY, Bytes.toBytes(eReference.getName()), toBytes(array));
			table.put(put);
		} catch (IOException e) {
			Logger.log(Logger.SEVERITY_ERROR, 
					MessageFormat.format("Unable to delete ''{0}[{1}''] for element ''{2}''", eReference.getName(), index, object));
		}
		return oldValue;
	}

	@Override
	public Object move(InternalEObject object, EStructuralFeature feature, int targetIndex, int sourceIndex) {
		Object movedElement = remove(object, feature, sourceIndex);
		add(object, feature, targetIndex, movedElement);
		return movedElement;
	}


	@Override
	public void unset(InternalEObject object, EStructuralFeature feature) {
		KyanosEObject kyanosEObject = KyanosEObjectAdapterFactoryImpl.getAdapter(object, KyanosEObject.class);
		try {
			Delete delete = new Delete(Bytes.toBytes(kyanosEObject.kyanosId()));
			delete.addColumn(PROPERTY_FAMILY, Bytes.toBytes(feature.toString()));
			table.delete(delete);
		} catch (IOException e) {
			Logger.log(Logger.SEVERITY_ERROR, 
					MessageFormat.format("Unable to get containment information for {0}", kyanosEObject));
		}
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
		KyanosEObject kyanosEObject = KyanosEObjectAdapterFactoryImpl.getAdapter(object, KyanosEObject.class);
		try {
			Put put = new Put(Bytes.toBytes(kyanosEObject.kyanosId()));
			put.addColumn(PROPERTY_FAMILY, Bytes.toBytes(feature.toString()), toBytes(new String[] {}));
			table.put(put);
		} catch (IOException e) {
			Logger.log(Logger.SEVERITY_ERROR, 
					MessageFormat.format("Unable to get containment information for {0}", kyanosEObject));
		}
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
		if (eReference.isContainment()) {
			try {
				Put put = new Put(Bytes.toBytes(referencedObject.kyanosId()));
				put.addColumn(CONTAINMENT_FAMILY, CONTAINER_QUALIFIER, Bytes.toBytes(object.kyanosId()));
				put.addColumn(CONTAINMENT_FAMILY, CONTAINING_FEATURE_QUALIFIER, Bytes.toBytes(eReference.getName()));
//				table.checkAndPut(
//						Bytes.toBytes(referencedObject.kyanosId()), CONTAINMENT_FAMILY, CONTAINER_QUALIFIER, CompareOp.NOT_EQUAL,
//						Bytes.toBytes(object.kyanosId()), put);
				table.put(put);

			} catch (IOException e) {
				Logger.log(Logger.SEVERITY_ERROR, 
						MessageFormat.format("Unable to update containment information for {0}", object));
			}
		}
	}
	
	protected void updateInstanceOf(KyanosEObject object) {
		try {
			Put put = new Put(Bytes.toBytes(object.kyanosId()));
			put.addColumn(TYPE_FAMILY, METAMODEL_QUALIFIER, Bytes.toBytes(object.eClass().getEPackage().getNsURI()));
			put.addColumn(TYPE_FAMILY, ECLASS_QUALIFIER, Bytes.toBytes(object.eClass().getName()));
			table.checkAndPut(Bytes.toBytes(object.kyanosId()), TYPE_FAMILY, ECLASS_QUALIFIER, null, put);

		} catch (IOException e) {
			Logger.log(Logger.SEVERITY_ERROR, 
					MessageFormat.format("Unable to update containment information for {0}", object));
		}
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
