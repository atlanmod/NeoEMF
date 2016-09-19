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
package fr.inria.atlanmod.neoemf.hbase.datastore.estores.impl;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.core.impl.NeoEObjectAdapterFactoryImpl;
import fr.inria.atlanmod.neoemf.core.impl.StringId;
import fr.inria.atlanmod.neoemf.datastore.InternalPersistentEObject;
import fr.inria.atlanmod.neoemf.datastore.estores.SearcheableResourceEStore;
import fr.inria.atlanmod.neoemf.hbase.util.NeoHBaseUtil;
import fr.inria.atlanmod.neoemf.logger.NeoLogger;

import org.apache.commons.lang.ArrayUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Append;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.protobuf.generated.ZooKeeperProtos;
import org.apache.hadoop.hbase.util.Bytes;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.concurrent.TimeoutException;

import static com.google.common.base.Preconditions.checkNotNull;

public class DirectWriteHBaseResourceEStoreImpl implements SearcheableResourceEStore {

	private static final byte[] PROPERTY_FAMILY =				Bytes.toBytes("p");
	private static final byte[] TYPE_FAMILY = 					Bytes.toBytes("t");
	private static final byte[] METAMODEL_QUALIFIER =			Bytes.toBytes("m");
	private static final byte[] ECLASS_QUALIFIER = 				Bytes.toBytes("e");
	private static final byte[] CONTAINMENT_FAMILY = 			Bytes.toBytes("c");
	private static final byte[] CONTAINER_QUALIFIER = 			Bytes.toBytes("n");
	private static final byte[] CONTAINING_FEATURE_QUALIFIER = 	Bytes.toBytes("g");
	
	private static final int ATTEMP_TIMES_DEFAULT = 10;
	private static final long SLEEP_DEFAULT = 1L;

	private static final Configuration conf = HBaseConfiguration.create();

	private final Cache<Object, InternalPersistentEObject> loadedEObjects = CacheBuilder.newBuilder().softValues().build();
	
	//protected Connection connection;
	
	private HTable table;
	
	private final Resource.Internal resource;

	public DirectWriteHBaseResourceEStoreImpl(Resource.Internal resource) throws IOException {

		this.resource = resource;
		
		conf.set("hbase.zookeeper.quorum", resource.getURI().host());
		conf.set("hbase.zookeeper.property.clientPort", resource.getURI().port() != null ? resource.getURI().port() : "2181");
		
		TableName tableName = TableName.valueOf(NeoHBaseUtil.formatURI(resource.getURI()));
		@SuppressWarnings("resource")
		HBaseAdmin admin = new HBaseAdmin(conf);
		if (!admin.tableExists(tableName)) {
			HTableDescriptor desc = new HTableDescriptor(tableName);
			HColumnDescriptor propertyFamily = new HColumnDescriptor(PROPERTY_FAMILY);
			HColumnDescriptor typeFamily = new HColumnDescriptor(TYPE_FAMILY);
			HColumnDescriptor containmentFamily = new HColumnDescriptor(CONTAINMENT_FAMILY);
			desc.addFamily(propertyFamily);
			desc.addFamily(typeFamily);
			desc.addFamily(containmentFamily);
			admin.createTable(desc);
		}
		table = new HTable(conf, tableName);
	}


	@Override
	public Resource.Internal resource() {
		return resource;
	}


	@Override
	public Object get(InternalEObject object, EStructuralFeature feature, int index) {
		if (feature instanceof EAttribute) {
			return get(object, (EAttribute) feature, index);
		} else if (feature instanceof EReference) {
			return ((EReference) feature).isContainer() ? getContainer(object) : get(object, (EReference) feature, index);
		} else {
			throw new IllegalArgumentException(feature.toString());
		}
	}
	
	protected Object get(InternalEObject object, EAttribute eAttribute, int index) {
		Object value = getFromTable(object, eAttribute);
		if (!eAttribute.isMany()) {
			return parseValue(eAttribute, (String) value);
		} else {
			String[] array = (String[]) value;
			return parseValue(eAttribute, array[index]);
		}
	}

	protected Object get(InternalEObject object, EReference eReference, int index) {
		Object value = getFromTable(object, eReference);
		if(value == null) {
		    return null;
		}
		if (!eReference.isMany()) {
			return eObject(new StringId((String) value));
		} else {
			String[] array = (String[]) value;
			return eObject(new StringId(array[index]));
		}
	}


	@Override
	public Object set(InternalEObject object, EStructuralFeature feature, int index, Object value) {
		if (feature instanceof EAttribute) {
			return set(object, (EAttribute) feature, index, value);
		} else if (feature instanceof EReference) {
			return set(object, (EReference) feature, index, (EObject)value);
		} else {
			throw new IllegalArgumentException(feature.toString());
		}
	}

	protected Object set(InternalEObject object, EAttribute eAttribute, int index, Object value) {
	    InternalPersistentEObject neoEObject = checkNotNull(
				NeoEObjectAdapterFactoryImpl.getAdapter(object, InternalPersistentEObject.class));
		Object oldValue = isSet(object, eAttribute) ? get(object, eAttribute, index) :  null;
		try {
			if (!eAttribute.isMany()) {
				Put put = new Put(Bytes.toBytes(neoEObject.id().toString()));
				put.add(PROPERTY_FAMILY, Bytes.toBytes(eAttribute.getName()), Bytes.toBytes(serializeValue(eAttribute, value)));
				table.put(put);
			} else {
				try {
					String[] array;
					boolean passed;
					int attemp = 0;			

					do {				
						array = (String[]) getFromTable(object, eAttribute);
						//array = (String[]) ArrayUtils.add(array, index, serializeValue(eAttribute, value));
						Put put = new Put(Bytes.toBytes(neoEObject.id().toString())).add( 
									PROPERTY_FAMILY,
									Bytes.toBytes(eAttribute.getName()),
									NeoHBaseUtil.EncoderUtil.toBytes((String[]) ArrayUtils.add(array, index, serializeValue(eAttribute, value))));
						passed = table.checkAndPut(Bytes.toBytes(neoEObject.id().toString()), 
												   PROPERTY_FAMILY,
												   Bytes.toBytes(eAttribute.getName()),
												   array == null ? null : NeoHBaseUtil.EncoderUtil.toBytes(array),
												   put);
						if (!passed) {
							if (attemp > ATTEMP_TIMES_DEFAULT) 
								throw new TimeoutException();
							Thread.sleep((++attemp)*SLEEP_DEFAULT);
									}
						
						} while (!passed);	
					
				} catch (IOException e) {
					NeoLogger.error("Unable to set ''{0}'' to ''{1}'' for element ''{2}''", value, eAttribute.getName(), object);
				}catch (TimeoutException e) {
					NeoLogger.error("Unable to set ''{0}'' to ''{1}'' for element ''{2}'' after ''{3}'' times", value, eAttribute.getName(), object, ATTEMP_TIMES_DEFAULT);
					e.printStackTrace();
				} catch (InterruptedException e) {
					NeoLogger.error("InterruptedException while updating element ''{0}''.\n{1}", object, e.getMessage());
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			NeoLogger.error("Unable to set information for element ''{0}''", object);
		} 
		return oldValue;
	}

	protected Object set(InternalEObject object, EReference eReference, int index, EObject referencedObject) {
	    InternalPersistentEObject neoEObject = checkNotNull(
				NeoEObjectAdapterFactoryImpl.getAdapter(object, InternalPersistentEObject.class));
	    InternalPersistentEObject neoReferencedEObject = checkNotNull(
				NeoEObjectAdapterFactoryImpl.getAdapter(referencedObject, InternalPersistentEObject.class));
		Object oldValue = isSet(object, eReference) ? get(object, eReference, index) :  null;
		updateLoadedEObjects(neoReferencedEObject);
		updateContainment(neoEObject, eReference, neoReferencedEObject);
		updateInstanceOf(neoReferencedEObject);
		
		try {
			if (!eReference.isMany()) {
				Put put = new Put(Bytes.toBytes(neoEObject.id().toString()));
				put.add(PROPERTY_FAMILY, 
						Bytes.toBytes(eReference.getName()), 
						Bytes.toBytes(neoReferencedEObject.id().toString()));
				table.put(put);
			} else {
				String[] array = (String[]) getFromTable(object, eReference);
				array[index] = neoReferencedEObject.id().toString();
				Put put = new Put(Bytes.toBytes(neoEObject.id().toString()));
				put.add(PROPERTY_FAMILY, 
						Bytes.toBytes(eReference.getName()), 
						NeoHBaseUtil.EncoderUtil.toBytesReferences(array));
				table.put(put);
			}
		} catch (IOException e) {
			NeoLogger.error("Unable to set information for element ''{0}''", object);
		}
		return oldValue;
	}


	@Override
	public boolean isSet(InternalEObject object, EStructuralFeature feature) {
		InternalPersistentEObject neoEObject = checkNotNull(
				NeoEObjectAdapterFactoryImpl.getAdapter(object, InternalPersistentEObject.class));
		try {
			Result result= table.get(new Get(Bytes.toBytes(neoEObject.id().toString())));
			byte[] value = result.getValue(PROPERTY_FAMILY, Bytes.toBytes(feature.getName()));
			return value != null;
		} catch (IOException e) {
			NeoLogger.error("Unable to get information for element ''{0}''", neoEObject);
		}
		return false;
	}


	@Override
	public void add(InternalEObject object, EStructuralFeature feature, int index, Object value) {
		if (feature instanceof EAttribute) {
			add(object, (EAttribute) feature, index, value);
		} else if (feature instanceof EReference) {
			add(object, (EReference) feature, index, (EObject)value);
		} else {
			throw new IllegalArgumentException(feature.toString());
		}
	}

	protected void add(InternalEObject object, EAttribute eAttribute, int index, Object value) {
		try {
		    InternalPersistentEObject neoEObject = checkNotNull(
					NeoEObjectAdapterFactoryImpl.getAdapter(object, InternalPersistentEObject.class));
			String[] array;
			boolean passed;
			int attemp = 0;			

			do {				
				array = (String[]) getFromTable(object, eAttribute);
				//array = (String[]) ArrayUtils.add(array, index, serializeValue(eAttribute, value));
				Put put = new Put(Bytes.toBytes(neoEObject.id().toString())).add( 
							PROPERTY_FAMILY,
							Bytes.toBytes(eAttribute.getName()),
							NeoHBaseUtil.EncoderUtil.toBytes( index < 0 ?
									(String[])ArrayUtils.add(array, serializeValue(eAttribute, value)) : 
										(String[])ArrayUtils.add(array, serializeValue(eAttribute, value))
											));
				passed = table.checkAndPut(Bytes.toBytes(neoEObject.id().toString()), 
										   PROPERTY_FAMILY,
										   Bytes.toBytes(eAttribute.getName()),
										   array == null ? null : NeoHBaseUtil.EncoderUtil.toBytes(array),
										   put);
				if (!passed) {
					if (attemp > ATTEMP_TIMES_DEFAULT) 
						throw new TimeoutException();
					Thread.sleep((++attemp)*SLEEP_DEFAULT);
							}
				
				} while (!passed);
			
		} catch (IOException e) {
			NeoLogger.error("Unable to add ''{0}'' to ''{1}'' for element ''{2}''", value, eAttribute.getName(), object);
		}catch (TimeoutException e) {
			NeoLogger.error("Unable to add ''{0}'' to ''{1}'' for element ''{2}'' after ''{3}'' times", value, eAttribute.getName(), object, ATTEMP_TIMES_DEFAULT);
			e.printStackTrace();
		} catch (InterruptedException e) {
			NeoLogger.error("InterruptedException while updating element ''{0}''.\n{1}", object, e.getMessage());
			e.printStackTrace();
		}
	}

	protected void add(InternalEObject object, EReference eReference, int index, EObject referencedObject) {
		try {
			InternalPersistentEObject neoEObject = checkNotNull(
					NeoEObjectAdapterFactoryImpl.getAdapter(object, InternalPersistentEObject.class));
			InternalPersistentEObject neoReferencedEObject = checkNotNull(
					NeoEObjectAdapterFactoryImpl.getAdapter(referencedObject, InternalPersistentEObject.class));
			// as long as the element is not attached to the resource, the containment and type  information 
			// are not stored.
			updateLoadedEObjects(neoReferencedEObject);
			updateContainment(neoEObject, eReference, neoReferencedEObject);
			updateInstanceOf(neoReferencedEObject);
			
			if (index == NO_INDEX) {
				addAsAppend(neoEObject, eReference, true, neoReferencedEObject);
			} else {
				
				String[] array;
				boolean passed;
				int attemp = 0;			

				do {				
					array = (String[]) getFromTable(object, eReference);
					//array = (String[]) ArrayUtils.add(array, index, referencedObject.neoemfId());
					Put put = new Put(Bytes.toBytes(neoEObject.id().toString())).add(
									  PROPERTY_FAMILY,
									  Bytes.toBytes(eReference.getName()),
									  NeoHBaseUtil.EncoderUtil.toBytesReferences((String[]) ArrayUtils.add(array, index, neoReferencedEObject.id().toString())));
					
					passed = table.checkAndPut(Bytes.toBytes(neoEObject.id().toString()), 
											   PROPERTY_FAMILY,
											   Bytes.toBytes(eReference.getName()),
											   array == null ? null : NeoHBaseUtil.EncoderUtil.toBytesReferences(array),
											   put);
					if (!passed) {
						if (attemp > ATTEMP_TIMES_DEFAULT) 
							throw new TimeoutException();
						Thread.sleep((++attemp)*SLEEP_DEFAULT);
								}
					
					} while (!passed);
			}
			
		} catch (IOException e) {
			NeoLogger.error("Unable to add ''{0}'' to ''{1}'' for element ''{2}''", referencedObject, eReference.getName(), object);
		} catch (TimeoutException e) {
			NeoLogger.error("Unable to add ''{0}'' to ''{1}'' for element ''{2}'' after ''{3}'' times", referencedObject, eReference.getName(), object, ATTEMP_TIMES_DEFAULT);
			e.printStackTrace();
		} catch (InterruptedException e) {
			NeoLogger.error("InterruptedException while updating element ''{0}''.\n{1}", object, e.getMessage());
			e.printStackTrace();
		}

	}

	
	protected void addAsAppend(InternalPersistentEObject object, EReference eReference, boolean atEnd, EObject referencedObject) throws IOException {
		
		InternalPersistentEObject neoReferencedEObject = checkNotNull(
				NeoEObjectAdapterFactoryImpl.getAdapter(referencedObject, InternalPersistentEObject.class));
		
		Append append = new Append(Bytes.toBytes(object.id().toString()));
		append.add(PROPERTY_FAMILY, 
					Bytes.toBytes(eReference.getName()), 
					atEnd ? Bytes.toBytes(NeoHBaseUtil.EncoderUtil.VALUE_SEPERATOR_DEFAULT + neoReferencedEObject.id().toString()) :
							Bytes.toBytes(neoReferencedEObject.id().toString() + NeoHBaseUtil.EncoderUtil.VALUE_SEPERATOR_DEFAULT)
							);
		
		table.append(append);
	}

	@Override
	public Object remove(InternalEObject object, EStructuralFeature feature, int index) {
		// TODO Nothing guarantees that the index is still the same.
		if (feature instanceof EAttribute) {
			return remove(object, (EAttribute) feature, index);
		} else if (feature instanceof EReference) {
			return remove(object, (EReference) feature, index);
		} else {
			throw new IllegalArgumentException(feature.toString());
		}
	}

	protected Object remove(InternalEObject object, EAttribute eAttribute, int index) {
		InternalPersistentEObject neoEObject = checkNotNull(
				NeoEObjectAdapterFactoryImpl.getAdapter(object, InternalPersistentEObject.class));
	    Object oldValue = get(object, eAttribute, index);
		try {
			
			String[] array;
			boolean passed;
			int attemp = 0;			

			do {				
				array = (String[]) getFromTable(object, eAttribute);
				//array = (String[]) ArrayUtils.add(array, index, serializeValue(eAttribute, value));
				Put put = new Put(Bytes.toBytes(neoEObject.id().toString())).add( 
							PROPERTY_FAMILY,
							Bytes.toBytes(eAttribute.getName()),
							NeoHBaseUtil.EncoderUtil.toBytes((String[]) ArrayUtils.remove(array, index)));
				passed = table.checkAndPut(Bytes.toBytes(neoEObject.id().toString()), 
										   PROPERTY_FAMILY,
										   Bytes.toBytes(eAttribute.getName()),
										   NeoHBaseUtil.EncoderUtil.toBytes(array),
										   put);
				if (!passed) {
					if (attemp > ATTEMP_TIMES_DEFAULT) 
						throw new TimeoutException();
					Thread.sleep((++attemp)*SLEEP_DEFAULT);
					oldValue = get(object, eAttribute, index);
							}
				
				} while (!passed);	
			
		} catch (IOException e) {
			NeoLogger.error("Unable to delete ''{0}'' to ''{1}'' for element ''{2}''", oldValue, eAttribute.getName(), object);
		}catch (TimeoutException e) {
			NeoLogger.error("Unable to delete ''{0}'' to ''{1}'' for element ''{2}'' after ''{3}'' times", oldValue, eAttribute.getName(), object, ATTEMP_TIMES_DEFAULT);
			e.printStackTrace();
		} catch (InterruptedException e) {
			NeoLogger.error("InterruptedException while updating element ''{0}''.\n{1}", object, e.getMessage());
			e.printStackTrace();
		}

		return oldValue;
	}

	protected Object remove(InternalEObject object, EReference eReference, int index) {
		InternalPersistentEObject neoEObject = checkNotNull(
				NeoEObjectAdapterFactoryImpl.getAdapter(object, InternalPersistentEObject.class));
		Object oldValue = get(object, eReference, index);
		
		try {
			
			String[] array;
			boolean passed;
			int attemp = 0;			

			do {				
				array = (String[]) getFromTable(object, eReference);
				//array = (String[]) ArrayUtils.add(array, index, referencedObject.neoemfId());
				Put put = new Put(Bytes.toBytes(neoEObject.id().toString())).add(
								  PROPERTY_FAMILY,
								  Bytes.toBytes(eReference.getName()),
								  NeoHBaseUtil.EncoderUtil.toBytesReferences((String[]) ArrayUtils.remove(array, index)));
				
				passed = table.checkAndPut(Bytes.toBytes(neoEObject.id().toString()), 
										   PROPERTY_FAMILY,
										   Bytes.toBytes(eReference.getName()),
										   NeoHBaseUtil.EncoderUtil.toBytesReferences(array),
										   put);
				
				if (!passed) {
					if (attemp > ATTEMP_TIMES_DEFAULT) 
						throw new TimeoutException();
					
					Thread.sleep((++attemp)*SLEEP_DEFAULT);
							}
				
				} while (!passed);
			
		} catch (IOException e) {
			NeoLogger.error("Unable to delete ''{0}[{1}''] for element ''{2}''", eReference.getName(), index, object);
		} catch (TimeoutException e) {
			NeoLogger.error("Unable to delete ''{0}[{1}''] for element ''{2}''", eReference.getName(), index, object);
			e.printStackTrace();
		} catch (InterruptedException e) {
			NeoLogger.error("InterruptedException while updating element ''{0}''.\n{1}", object, e.getMessage());
			e.printStackTrace();
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
		InternalPersistentEObject neoEObject = checkNotNull(
				NeoEObjectAdapterFactoryImpl.getAdapter(object, InternalPersistentEObject.class));
		try {
			Delete delete = new Delete(Bytes.toBytes(neoEObject.id().toString()));
			delete.deleteColumn(PROPERTY_FAMILY, Bytes.toBytes(feature.toString()));
			table.delete(delete);
		} catch (IOException e) {
			NeoLogger.error("Unable to get containment information for {0}", neoEObject);
		}
	}


	@Override
	public boolean isEmpty(InternalEObject object, EStructuralFeature feature) {
		return size(object, feature) == 0; 
	}


	@Override
	public int size(InternalEObject object, EStructuralFeature feature) {
		String[] array = (String[]) getFromTable(object, feature);
		return array != null ? array.length : 0; 
	}


	@Override
	public boolean contains(InternalEObject object, EStructuralFeature feature, Object value) {
		return false;
		//return indexOf(object, feature, value) != -1;
	}


	@Override
	public int indexOf(InternalEObject object, EStructuralFeature feature, Object value) {
		String[] array = (String[]) getFromTable(object, feature);
		if (array == null) {
			return -1;
		}
		if (feature instanceof EAttribute) {
			return ArrayUtils.indexOf(array, serializeValue((EAttribute) feature, value));
		} else {
			InternalPersistentEObject childEObject = checkNotNull(
					NeoEObjectAdapterFactoryImpl.getAdapter(value, InternalPersistentEObject.class));
			return ArrayUtils.indexOf(array, childEObject.id().toString());
		}
	}


	@Override
	public int lastIndexOf(InternalEObject object, EStructuralFeature feature, Object value) {
		String[] array = (String[]) getFromTable(object, feature);
		if (array == null) {
			return -1;
		}
		if (feature instanceof EAttribute) {
			return ArrayUtils.lastIndexOf(array, serializeValue((EAttribute) feature, value));
		} else {
			InternalPersistentEObject childEObject = checkNotNull(
					NeoEObjectAdapterFactoryImpl.getAdapter(value, InternalPersistentEObject.class));
			return ArrayUtils.lastIndexOf(array, childEObject.id().toString());
		}
	}


	@Override
	public void clear(InternalEObject object, EStructuralFeature feature) {
		InternalPersistentEObject neoEObject = checkNotNull(
				NeoEObjectAdapterFactoryImpl.getAdapter(object, InternalPersistentEObject.class));
		try {
			Put put = new Put(Bytes.toBytes(neoEObject.id().toString()));
			put.add(PROPERTY_FAMILY, Bytes.toBytes(feature.toString()), NeoHBaseUtil.EncoderUtil.toBytes(new String[] {}));
			table.put(put);
		} catch (IOException e) {
			NeoLogger.error("Unable to get containment information for {0}", neoEObject);
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
		T[] result;
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
		return Arrays.hashCode(toArray(object, feature));
	}


	@Override
	public InternalEObject getContainer(InternalEObject object) {
		InternalPersistentEObject neoEObject = checkNotNull(
				NeoEObjectAdapterFactoryImpl.getAdapter(object, InternalPersistentEObject.class));
		
		try {
			Result result= table.get(new Get(Bytes.toBytes(neoEObject.id().toString())));
			String containerId = Bytes.toString(result.getValue(CONTAINMENT_FAMILY, CONTAINER_QUALIFIER));
			String containingFeatureName = Bytes.toString(result.getValue(CONTAINMENT_FAMILY, CONTAINING_FEATURE_QUALIFIER));
	
			if (containerId != null && containingFeatureName != null) {
				return (InternalEObject) eObject(new StringId(containerId));
			}
			
		} catch (IOException e) {
			NeoLogger.error("Unable to get containment information for {0}", neoEObject);
		}
		return null;
	}


	@Override
	public EStructuralFeature getContainingFeature(InternalEObject object) {
		InternalPersistentEObject neoEObject = checkNotNull(
				NeoEObjectAdapterFactoryImpl.getAdapter(object, InternalPersistentEObject.class));
		
		try {
			Result result= table.get(new Get(Bytes.toBytes(neoEObject.id().toString())));
			String containerId = Bytes.toString(result.getValue(CONTAINMENT_FAMILY, CONTAINER_QUALIFIER));
			String containingFeatureName = Bytes.toString(result.getValue(CONTAINMENT_FAMILY, CONTAINING_FEATURE_QUALIFIER));
	
			if (containerId != null && containingFeatureName != null) {
				EObject container = eObject(new StringId(containerId));
				return container.eClass().getEStructuralFeature(containingFeatureName);
			}
			
		} catch (IOException e) {
			NeoLogger.error("Unable to get containment information for {0}", neoEObject);
		}
		return null;
	}


	@Override
	public EObject create(EClass eClass) {
		// This should not be called
		throw new UnsupportedOperationException();
	}


	@Override
	public EObject eObject(Id id) {
		if (id == null) {
			return null;
		}
		InternalPersistentEObject neoEObject = loadedEObjects.getIfPresent(id);
		if (neoEObject == null) {
			EClass eClass = resolveInstanceOf(id);
			if (eClass != null) {
				EObject eObject = EcoreUtil.create(eClass);
				if (eObject instanceof InternalPersistentEObject) {
					neoEObject = (InternalPersistentEObject) eObject;
				} else {
					neoEObject = checkNotNull(
							NeoEObjectAdapterFactoryImpl.getAdapter(eObject, InternalPersistentEObject.class));
				}
				neoEObject.id(id);
			} else {
				NeoLogger.error("Element {0} does not have an associated EClass", id);
			}
			if (neoEObject == null) {
				MessageFormat.format("Element {0} does not exist", id);
				return null;
			} else {
				loadedEObjects.put(id, neoEObject);
			}
		}
		if (neoEObject.resource() != resource()) {
			neoEObject.resource(resource());
		}
		return neoEObject;
	}
	

	protected EClass resolveInstanceOf(Id id) {
		try {
			Result result= table.get(new Get(Bytes.toBytes(id.toString())));
			String nsURI = Bytes.toString(result.getValue(TYPE_FAMILY, METAMODEL_QUALIFIER));
			String className = Bytes.toString(result.getValue(TYPE_FAMILY, ECLASS_QUALIFIER));
			if (nsURI != null && className != null) {
				return (EClass) Registry.INSTANCE.getEPackage(nsURI).getEClassifier(className);
			}
		} catch (IOException e) {
			NeoLogger.error("Unable to get instance of information for {0}", id);
		}
		return null;
	}
	
	protected void updateLoadedEObjects(InternalPersistentEObject eObject) {
		loadedEObjects.put(eObject.id(), eObject);
	}
	
	protected void updateContainment(InternalPersistentEObject object, EReference eReference, EObject referencedObject) {
		if (eReference.isContainment()) {
			
		    InternalPersistentEObject neoReferencedEObject = checkNotNull(
					NeoEObjectAdapterFactoryImpl.getAdapter(referencedObject, InternalPersistentEObject.class));
			// remove from root 
			
			try {
				Put put = new Put(Bytes.toBytes(neoReferencedEObject.id().toString()));
				put.add(CONTAINMENT_FAMILY, CONTAINER_QUALIFIER, Bytes.toBytes(object.id().toString()));
				put.add(CONTAINMENT_FAMILY, CONTAINING_FEATURE_QUALIFIER, Bytes.toBytes(eReference.getName()));
//						no need to use the CAS mech	
//						table.checkAndPut(
//						Bytes.toBytes(referencedObject.neoemfId()), CONTAINMENT_FAMILY, CONTAINER_QUALIFIER, CompareOp.NOT_EQUAL,
//						Bytes.toBytes(object.neoemfId()), put);
				table.put(put);

			} catch (IOException e) {
				NeoLogger.error("Unable to update containment information for {0}", object);
			}
		}
	}
	
	protected void updateInstanceOf(InternalPersistentEObject object) {
		
		try {
			Put put = new Put(Bytes.toBytes(object.id().toString()));
			put.add(TYPE_FAMILY, METAMODEL_QUALIFIER, Bytes.toBytes(object.eClass().getEPackage().getNsURI()));
			put.add(TYPE_FAMILY, ECLASS_QUALIFIER, Bytes.toBytes(object.eClass().getName()));
			table.checkAndPut(Bytes.toBytes(object.id().toString()), TYPE_FAMILY, ECLASS_QUALIFIER, null, put);

		} catch (IOException e) {
			NeoLogger.error("Unable to update containment information for {0}", object);
		}
	}


	protected static Object parseValue(EAttribute eAttribute, String value) {
		return value != null ? EcoreUtil.createFromString(eAttribute.getEAttributeType(), value) : null;
	}

	protected static String serializeValue(EAttribute eAttribute, Object value) {
		return value != null ? EcoreUtil.convertToString(eAttribute.getEAttributeType(), value) : null;
	}
	
	/**
	 * Gets the {@link EStructuralFeature} {@code feature} from the {@link ZooKeeperProtos.Table} for the {@link PersistentEObject object}
	 * 
	 * @return The value of the {@code feature}. It can be a {@link String} for
	 *         single-valued {@link EStructuralFeature}s or a {@link String}[]
	 *         for many-valued {@link EStructuralFeature}s
	 */
	protected Object getFromTable(InternalEObject object, EStructuralFeature feature) {
		try {
		    PersistentEObject neoEObject = checkNotNull(
					NeoEObjectAdapterFactoryImpl.getAdapter(object, InternalPersistentEObject.class));
			Result result = table.get(new Get(Bytes.toBytes(neoEObject.id().toString())));
			byte[] value = result.getValue(PROPERTY_FAMILY, Bytes.toBytes(feature.getName()));
			if (!feature.isMany()) {
				return Bytes.toString(value);
			} else {
				if (feature instanceof EAttribute) 
					return NeoHBaseUtil.EncoderUtil.toStrings(value);
					return NeoHBaseUtil.EncoderUtil.toStringsReferences(value);
			}
		} catch (IOException e) {
			NeoLogger.error("Unable to get property ''{0}'' for ''{1}''", feature.getName(), object);
		}
		return null;
	}


    @Override
    public EList<EObject> getAllInstances(EClass eClass, boolean strict)
            throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }
}