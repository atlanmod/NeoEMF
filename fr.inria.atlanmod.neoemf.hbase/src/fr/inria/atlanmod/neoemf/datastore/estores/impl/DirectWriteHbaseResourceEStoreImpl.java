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
package fr.inria.atlanmod.neoemf.datastore.estores.impl;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeoutException;

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
import org.apache.hadoop.hbase.client.Put;
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

import fr.inria.atlanmod.neoemf.core.NeoEMFEObject;
import fr.inria.atlanmod.neoemf.core.NeoEMFInternalEObject;
import fr.inria.atlanmod.neoemf.core.impl.NeoEMFEObjectAdapterFactoryImpl;
import fr.inria.atlanmod.neoemf.datastore.estores.SearcheableResourceEStore;
import fr.inria.atlanmod.neoemf.logger.Logger;
import fr.inria.atlanmod.neoemf.util.NeoEMFUtil;

public class DirectWriteHbaseResourceEStoreImpl implements SearcheableResourceEStore {


	protected static final byte[] PROPERTY_FAMILY =				Bytes.toBytes("p");
	protected static final byte[] TYPE_FAMILY = 				Bytes.toBytes("t");
	protected static final byte[] METAMODEL_QUALIFIER =			Bytes.toBytes("m");
	protected static final byte[] ECLASS_QUALIFIER = 			Bytes.toBytes("e");
	protected static final byte[] CONTAINMENT_FAMILY = 			Bytes.toBytes("c");
	protected static final byte[] CONTAINER_QUALIFIER = 		Bytes.toBytes("n");
	protected static final byte[] CONTAINING_FEATURE_QUALIFIER = Bytes.toBytes("g");
	
<<<<<<< HEAD
	private static final int ATTEMP_TIMES_DEFAULT = 5;
=======
	private static final int ATTEMP_TIMES_DEFAULT = 10;
>>>>>>> old_origin/master
	private static final long SLEEP_DEFAULT = 1L;
	

	
	@SuppressWarnings("unchecked")
	protected Map<Object, NeoEMFInternalEObject> loadedEObjects = new SoftValueHashMap();
	
	//protected Connection connection;
	
	protected HTable table;
	
	protected Resource.Internal resource;
	
	protected static Configuration conf = HBaseConfiguration.create();


	public DirectWriteHbaseResourceEStoreImpl(Resource.Internal resource) throws IOException {

		this.resource = resource;
		
		conf.set("hbase.zookeeper.quorum", resource.getURI().host());
		conf.set("hbase.zookeeper.property.clientPort", resource.getURI().port() != null ? resource.getURI().port() : "2181");
		
		TableName tableName = TableName.valueOf(NeoEMFUtil.formatURI(resource.getURI()));
		@SuppressWarnings("resource")
		HBaseAdmin admin = new HBaseAdmin(conf);
		if (! admin.tableExists(tableName)) {
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
	public Resource.Internal getResource() {
		return resource;
	}


	@Override
	public Object get(InternalEObject object, EStructuralFeature feature, int index) {
		NeoEMFEObject neoEMFEObject = NeoEMFEObjectAdapterFactoryImpl.getAdapter(object, NeoEMFEObject.class);
		if (feature instanceof EAttribute) {
			return get(neoEMFEObject, (EAttribute) feature, index);
		} else if (feature instanceof EReference) {
			return ((EReference) feature).isContainer() ? getContainer(object) : get(neoEMFEObject, (EReference) feature, index);
		} else {
			throw new IllegalArgumentException(feature.toString());
		}
	}
	
	protected Object get(NeoEMFEObject object, EAttribute eAttribute, int index) {
		Object value = getFromTable(object, eAttribute);
		if (!eAttribute.isMany()) {
			return parseValue(eAttribute, (String) value);
		} else {
			String[] array = (String[]) value;
			return parseValue(eAttribute, array[index]);
		}
	}

	protected Object get(NeoEMFEObject object, EReference eReference, int index) {
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
		NeoEMFEObject neoEMFEObject = NeoEMFEObjectAdapterFactoryImpl.getAdapter(object, NeoEMFEObject.class);
		if (feature instanceof EAttribute) {
			return set(neoEMFEObject, (EAttribute) feature, index, value);
		} else if (feature instanceof EReference) {
			NeoEMFInternalEObject referencedEObject = NeoEMFEObjectAdapterFactoryImpl.getAdapter(value, NeoEMFInternalEObject.class);
			return set(neoEMFEObject, (EReference) feature, index, referencedEObject);
		} else {
			throw new IllegalArgumentException(feature.toString());
		}
	}

	protected Object set(NeoEMFEObject object, EAttribute eAttribute, int index, Object value) {
		Object oldValue = isSet((InternalEObject) object, eAttribute) ? get(object, eAttribute, index) :  null;
		try {
			if (!eAttribute.isMany()) {
				Put put = new Put(Bytes.toBytes(object.neoemfId()));
				put.add(PROPERTY_FAMILY, Bytes.toBytes(eAttribute.getName()), Bytes.toBytes(serializeValue(eAttribute, value)));
				table.put(put);
			} else {
				try {
					String[] array;
					boolean passed = false;
					int attemp = 0;			

					do {				
						array = (String[]) getFromTable(object, eAttribute);
						//array = (String[]) ArrayUtils.add(array, index, serializeValue(eAttribute, value));
						Put put = new Put(Bytes.toBytes(object.neoemfId())).add( 
									PROPERTY_FAMILY,
									Bytes.toBytes(eAttribute.getName()),
									NeoEMFUtil.EncoderUtil.toBytes((String[]) ArrayUtils.add(array, index, serializeValue(eAttribute, value))));
						passed = table.checkAndPut(Bytes.toBytes(object.neoemfId()), 
												   PROPERTY_FAMILY,
												   Bytes.toBytes(eAttribute.getName()),
												   array == null ? null :NeoEMFUtil.EncoderUtil.toBytes(array),
												   put);
						if (!passed) {
							if (attemp > ATTEMP_TIMES_DEFAULT) 
								throw new TimeoutException();
							Thread.sleep((++attemp)*SLEEP_DEFAULT);
									}
						
						} while (!passed);	
					
				} catch (IOException e) {
					Logger.log(Logger.SEVERITY_ERROR, 
							MessageFormat.format("Unable to set ''{0}'' to ''{1}'' for element ''{2}''", value, eAttribute.getName(), object));
				}catch (TimeoutException e) {
					Logger.log(Logger.SEVERITY_ERROR, 
							MessageFormat.format("Unable to set ''{0}'' to ''{1}'' for element ''{2}'' after ''{3}'' times", value, eAttribute.getName(), object, ATTEMP_TIMES_DEFAULT));
					e.printStackTrace();
				} catch (InterruptedException e) {
					Logger.log(Logger.SEVERITY_ERROR, 
							MessageFormat.format("InterruptedException while updating element ''{0}''.\n{1}", object, e.getMessage()));
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			Logger.log(Logger.SEVERITY_ERROR, 
					MessageFormat.format("Unable to set information for element ''{0}''", object));
		} 
		return oldValue;
	}

	protected Object set(NeoEMFEObject object, EReference eReference, int index, NeoEMFInternalEObject referencedObject) {
		Object oldValue = isSet((InternalEObject) object, eReference) ? get(object, eReference, index) :  null;
		updateLoadedEObjects(referencedObject);
		updateContainment(object, eReference, referencedObject);
		updateInstanceOf(referencedObject);
		
		try {
			if (!eReference.isMany()) {
				Put put = new Put(Bytes.toBytes(object.neoemfId()));
				put.add(PROPERTY_FAMILY, 
						Bytes.toBytes(eReference.getName()), 
						Bytes.toBytes(referencedObject.neoemfId()));
				table.put(put);
			} else {
				String[] array = (String[]) getFromTable(object, eReference);
				array[index] = referencedObject.neoemfId();
				Put put = new Put(Bytes.toBytes(object.neoemfId()));
				put.add(PROPERTY_FAMILY, 
						Bytes.toBytes(eReference.getName()), 
						NeoEMFUtil.EncoderUtil.toBytesReferences(array));
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
		NeoEMFEObject neoEMFEObject = NeoEMFEObjectAdapterFactoryImpl.getAdapter(object, NeoEMFEObject.class);
		try {
			Result result= table.get(new Get(Bytes.toBytes(neoEMFEObject.neoemfId())));
			byte[] value = result.getValue(PROPERTY_FAMILY, Bytes.toBytes(feature.getName()));
			return value != null;
		} catch (IOException e) {
			Logger.log(Logger.SEVERITY_ERROR, 
					MessageFormat.format("Unable to get information for element ''{0}''", neoEMFEObject));
		}
		return false;
	}


	@Override
	public void add(InternalEObject object, EStructuralFeature feature, int index, Object value) {
		NeoEMFEObject neoEMFEObject = NeoEMFEObjectAdapterFactoryImpl.getAdapter(object, NeoEMFEObject.class);
		if (feature instanceof EAttribute) {
			add(neoEMFEObject, (EAttribute) feature, index, value);
		} else if (feature instanceof EReference) {
			NeoEMFInternalEObject referencedEObject = NeoEMFEObjectAdapterFactoryImpl.getAdapter(value, NeoEMFInternalEObject.class);
			add(neoEMFEObject, (EReference) feature, index, referencedEObject);
		} else {
			throw new IllegalArgumentException(feature.toString());
		}
	}

	protected void add(NeoEMFEObject object, EAttribute eAttribute, int index, Object value) {
		try {
			String[] array;
			boolean passed = false;
			int attemp = 0;			

			do {				
				array = (String[]) getFromTable(object, eAttribute);
				//array = (String[]) ArrayUtils.add(array, index, serializeValue(eAttribute, value));
				Put put = new Put(Bytes.toBytes(object.neoemfId())).add( 
							PROPERTY_FAMILY,
							Bytes.toBytes(eAttribute.getName()),
							NeoEMFUtil.EncoderUtil.toBytes( index < 0 ? 
									(String[])ArrayUtils.add(array, serializeValue(eAttribute, value)) : 
										(String[])ArrayUtils.add(array, serializeValue(eAttribute, value))
											));
				passed = table.checkAndPut(Bytes.toBytes(object.neoemfId()), 
										   PROPERTY_FAMILY,
										   Bytes.toBytes(eAttribute.getName()),
										   array == null ? null :NeoEMFUtil.EncoderUtil.toBytes(array),
										   put);
				if (!passed) {
					if (attemp > ATTEMP_TIMES_DEFAULT) 
						throw new TimeoutException();
					Thread.sleep((++attemp)*SLEEP_DEFAULT);
							}
				
				} while (!passed);
			
		} catch (IOException e) {
			Logger.log(Logger.SEVERITY_ERROR, 
					MessageFormat.format("Unable to add ''{0}'' to ''{1}'' for element ''{2}''", value, eAttribute.getName(), object));
		}catch (TimeoutException e) {
			Logger.log(Logger.SEVERITY_ERROR, 
					MessageFormat.format("Unable to add ''{0}'' to ''{1}'' for element ''{2}'' after ''{3}'' times", value, eAttribute.getName(), object, ATTEMP_TIMES_DEFAULT));
			e.printStackTrace();
		} catch (InterruptedException e) {
			Logger.log(Logger.SEVERITY_ERROR, 
					MessageFormat.format("InterruptedException while updating element ''{0}''.\n{1}", object, e.getMessage()));
			e.printStackTrace();
		}
	}

	protected void add(NeoEMFEObject object, EReference eReference, int index, NeoEMFInternalEObject referencedObject) {
		try {
			
			// as long as the element is not attached to the resource, the containment and type  information 
			// are not stored.
			updateLoadedEObjects(referencedObject);
			updateContainment(object, eReference, referencedObject);
			updateInstanceOf(referencedObject);
			
			if (index == NO_INDEX) {
				addAsAppend(object, eReference, index == NO_INDEX, referencedObject);
			} else {
				
				String[] array;
				boolean passed = false;
				int attemp = 0;			

				do {				
					array = (String[]) getFromTable(object, eReference);
					//array = (String[]) ArrayUtils.add(array, index, referencedObject.neoemfId());
					Put put = new Put(Bytes.toBytes(object.neoemfId())).add(
									  PROPERTY_FAMILY,
									  Bytes.toBytes(eReference.getName()),
									  NeoEMFUtil.EncoderUtil.toBytesReferences((String[]) ArrayUtils.add(array, index, referencedObject.neoemfId())));
					
					passed = table.checkAndPut(Bytes.toBytes(object.neoemfId()), 
											   PROPERTY_FAMILY,
											   Bytes.toBytes(eReference.getName()),
											   array == null ? null : NeoEMFUtil.EncoderUtil.toBytesReferences(array),
											   put);
					if (!passed) {
						if (attemp > ATTEMP_TIMES_DEFAULT) 
							throw new TimeoutException();
						Thread.sleep((++attemp)*SLEEP_DEFAULT);
								}
					
					} while (!passed);
			}
			
		} catch (IOException e) {
			Logger.log(Logger.SEVERITY_ERROR, 
					MessageFormat.format("Unable to add ''{0}'' to ''{1}'' for element ''{2}''", referencedObject, eReference.getName(), object));
		} catch (TimeoutException e) {
			Logger.log(Logger.SEVERITY_ERROR, 
					MessageFormat.format("Unable to add ''{0}'' to ''{1}'' for element ''{2}'' after ''{3}'' times", referencedObject, eReference.getName(), object, ATTEMP_TIMES_DEFAULT));
			e.printStackTrace();
		} catch (InterruptedException e) {
			Logger.log(Logger.SEVERITY_ERROR, 
					MessageFormat.format("InterruptedException while updating element ''{0}''.\n{1}", object, e.getMessage()));
			e.printStackTrace();
		}

	}

	
	protected void addAsAppend(NeoEMFEObject object, EReference eReference,
			boolean atEnd, NeoEMFInternalEObject referencedObject) throws IOException {
		
		
		Append append = new Append(Bytes.toBytes(object.neoemfId()));
		append.add(PROPERTY_FAMILY, 
					Bytes.toBytes(eReference.getName()), 
					atEnd ? Bytes.toBytes(NeoEMFUtil.EncoderUtil.VALUE_SEPERATOR_DEFAULT + referencedObject.neoemfId()) :
							Bytes.toBytes(referencedObject.neoemfId() + NeoEMFUtil.EncoderUtil.VALUE_SEPERATOR_DEFAULT)
							);
		
		table.append(append);
	}

	@Override
	public Object remove(InternalEObject object, EStructuralFeature feature, int index) {
		// TODO nothing guarantees that the index is still the same 
		NeoEMFEObject neoEMFEObject = NeoEMFEObjectAdapterFactoryImpl.getAdapter(object, NeoEMFEObject.class);
		if (feature instanceof EAttribute) {
			return remove(neoEMFEObject, (EAttribute) feature, index);
		} else if (feature instanceof EReference) {
			return remove(neoEMFEObject, (EReference) feature, index);
		} else {
			throw new IllegalArgumentException(feature.toString());
		}
	}

	protected Object remove(NeoEMFEObject object, EAttribute eAttribute, int index) {
		Object oldValue = get(object, eAttribute, index);
		try {
			
			String[] array;
			boolean passed = false;
			int attemp = 0;			

			do {				
				array = (String[]) getFromTable(object, eAttribute);
				//array = (String[]) ArrayUtils.add(array, index, serializeValue(eAttribute, value));
				Put put = new Put(Bytes.toBytes(object.neoemfId())).add( 
							PROPERTY_FAMILY,
							Bytes.toBytes(eAttribute.getName()),
							NeoEMFUtil.EncoderUtil.toBytes((String[]) ArrayUtils.remove(array, index)));
				passed = table.checkAndPut(Bytes.toBytes(object.neoemfId()), 
										   PROPERTY_FAMILY,
										   Bytes.toBytes(eAttribute.getName()),
										   array == null ? null :NeoEMFUtil.EncoderUtil.toBytes(array),
										   put);
				if (!passed) {
					if (attemp > ATTEMP_TIMES_DEFAULT) 
						throw new TimeoutException();
					Thread.sleep((++attemp)*SLEEP_DEFAULT);
					oldValue = get(object, eAttribute, index);
							}
				
				} while (!passed);	
			
		} catch (IOException e) {
			Logger.log(Logger.SEVERITY_ERROR, 
					MessageFormat.format("Unable to delete ''{0}'' to ''{1}'' for element ''{2}''", oldValue, eAttribute.getName(), object));
		}catch (TimeoutException e) {
			Logger.log(Logger.SEVERITY_ERROR, 
					MessageFormat.format("Unable to delete ''{0}'' to ''{1}'' for element ''{2}'' after ''{3}'' times", oldValue, eAttribute.getName(), object, ATTEMP_TIMES_DEFAULT));
			e.printStackTrace();
		} catch (InterruptedException e) {
			Logger.log(Logger.SEVERITY_ERROR, 
					MessageFormat.format("InterruptedException while updating element ''{0}''.\n{1}", object, e.getMessage()));
			e.printStackTrace();
		}

		return oldValue;
	}

	protected Object remove(NeoEMFEObject object, EReference eReference, int index) {
		
		Object oldValue = get(object, eReference, index);
		
		try {
			
			String[] array;
			boolean passed = false;
			int attemp = 0;			

			do {				
				array = (String[]) getFromTable(object, eReference);
				//array = (String[]) ArrayUtils.add(array, index, referencedObject.neoemfId());
				Put put = new Put(Bytes.toBytes(object.neoemfId())).add(
								  PROPERTY_FAMILY,
								  Bytes.toBytes(eReference.getName()),
								  NeoEMFUtil.EncoderUtil.toBytesReferences((String[]) ArrayUtils.remove(array, index)));
				
				passed = table.checkAndPut(Bytes.toBytes(object.neoemfId()), 
										   PROPERTY_FAMILY,
										   Bytes.toBytes(eReference.getName()),
										   array == null ? null : NeoEMFUtil.EncoderUtil.toBytesReferences(array),
										   put);
				
				if (!passed) {
					if (attemp > ATTEMP_TIMES_DEFAULT) 
						throw new TimeoutException();
					
					Thread.sleep((++attemp)*SLEEP_DEFAULT);
							}
				
				} while (!passed);
			
		} catch (IOException e) {
			Logger.log(Logger.SEVERITY_ERROR, 
					MessageFormat.format("Unable to delete ''{0}[{1}''] for element ''{2}''", eReference.getName(), index, object));
		} catch (TimeoutException e) {
			Logger.log(Logger.SEVERITY_ERROR, 
					MessageFormat.format("Unable to delete ''{0}[{1}''] for element ''{2}''", eReference.getName(), index, object));
			e.printStackTrace();
		} catch (InterruptedException e) {
			Logger.log(Logger.SEVERITY_ERROR, 
					MessageFormat.format("InterruptedException while updating element ''{0}''.\n{1}", object, e.getMessage()));
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
		NeoEMFEObject neoEMFEObject = NeoEMFEObjectAdapterFactoryImpl.getAdapter(object, NeoEMFEObject.class);
		try {
			Delete delete = new Delete(Bytes.toBytes(neoEMFEObject.neoemfId()));
			delete.deleteColumn(PROPERTY_FAMILY, Bytes.toBytes(feature.toString()));
			table.delete(delete);
		} catch (IOException e) {
			Logger.log(Logger.SEVERITY_ERROR, 
					MessageFormat.format("Unable to get containment information for {0}", neoEMFEObject));
		}
	}


	@Override
	public boolean isEmpty(InternalEObject object, EStructuralFeature feature) {
		return size(object, feature) == 0; 
	}


	@Override
	public int size(InternalEObject object, EStructuralFeature feature) {
		NeoEMFEObject neoEMFEObject = NeoEMFEObjectAdapterFactoryImpl.getAdapter(object, NeoEMFEObject.class);
		String[] array = (String[]) getFromTable(neoEMFEObject, feature);
		return array != null ? array.length : 0; 
	}


	@Override
	public boolean contains(InternalEObject object, EStructuralFeature feature, Object value) {
		return false;
		//return indexOf(object, feature, value) != -1;
	}


	@Override
	public int indexOf(InternalEObject object, EStructuralFeature feature, Object value) {
		NeoEMFEObject neoEMFEObject = NeoEMFEObjectAdapterFactoryImpl.getAdapter(object, NeoEMFEObject.class);
		String[] array = (String[]) getFromTable(neoEMFEObject, feature);
		if (array == null) {
			return -1;
		}
		if (feature instanceof EAttribute) {
			return ArrayUtils.indexOf(array, serializeValue((EAttribute) feature, value));
		} else {
			NeoEMFEObject childEObject = NeoEMFEObjectAdapterFactoryImpl.getAdapter(value, NeoEMFEObject.class);
			return ArrayUtils.indexOf(array, childEObject.neoemfId());
		}
	}


	@Override
	public int lastIndexOf(InternalEObject object, EStructuralFeature feature, Object value) {
		NeoEMFEObject neoEMFEObject = NeoEMFEObjectAdapterFactoryImpl.getAdapter(object, NeoEMFEObject.class);
		String[] array = (String[]) getFromTable(neoEMFEObject, feature);
		if (array == null) {
			return -1;
		}
		if (feature instanceof EAttribute) {
			return ArrayUtils.lastIndexOf(array, serializeValue((EAttribute) feature, value));
		} else {
			NeoEMFEObject childEObject = NeoEMFEObjectAdapterFactoryImpl.getAdapter(value, NeoEMFEObject.class);
			return ArrayUtils.lastIndexOf(array, childEObject.neoemfId());
		}
	}


	@Override
	public void clear(InternalEObject object, EStructuralFeature feature) {
		NeoEMFEObject neoEMFEObject = NeoEMFEObjectAdapterFactoryImpl.getAdapter(object, NeoEMFEObject.class);
		try {
			Put put = new Put(Bytes.toBytes(neoEMFEObject.neoemfId()));
			put.add(PROPERTY_FAMILY, Bytes.toBytes(feature.toString()), NeoEMFUtil.EncoderUtil.toBytes(new String[] {}));
			table.put(put);
		} catch (IOException e) {
			Logger.log(Logger.SEVERITY_ERROR, 
					MessageFormat.format("Unable to get containment information for {0}", neoEMFEObject));
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
		NeoEMFEObject neoEMFEObject = NeoEMFEObjectAdapterFactoryImpl.getAdapter(object, NeoEMFEObject.class);
		
		try {
			Result result= table.get(new Get(Bytes.toBytes(neoEMFEObject.neoemfId())));
			String containerId = Bytes.toString(result.getValue(CONTAINMENT_FAMILY, CONTAINER_QUALIFIER));
			String containingFeatureName = Bytes.toString(result.getValue(CONTAINMENT_FAMILY, CONTAINING_FEATURE_QUALIFIER));
	
			if (containerId != null && containingFeatureName != null) {
				return (InternalEObject) getEObject(containerId);
			}
			
		} catch (IOException e) {
			Logger.log(Logger.SEVERITY_ERROR, 
					MessageFormat.format("Unable to get containment information for {0}", neoEMFEObject));
		}
		return null;
	}


	@Override
	public EStructuralFeature getContainingFeature(InternalEObject object) {
		NeoEMFEObject neoEMFEObject = NeoEMFEObjectAdapterFactoryImpl.getAdapter(object, NeoEMFEObject.class);
		
		try {
			Result result= table.get(new Get(Bytes.toBytes(neoEMFEObject.neoemfId())));
			String containerId = Bytes.toString(result.getValue(CONTAINMENT_FAMILY, CONTAINER_QUALIFIER));
			String containingFeatureName = Bytes.toString(result.getValue(CONTAINMENT_FAMILY, CONTAINING_FEATURE_QUALIFIER));
	
			if (containerId != null && containingFeatureName != null) {
				EObject container = getEObject(containerId);
				return container.eClass().getEStructuralFeature(containingFeatureName);
			}
			
		} catch (IOException e) {
			Logger.log(Logger.SEVERITY_ERROR, 
					MessageFormat.format("Unable to get containment information for {0}", neoEMFEObject));
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
		NeoEMFInternalEObject neoemfEObject = loadedEObjects.get(id);
		if (neoemfEObject == null) {
			EClass eClass = resolveInstanceOf(id);
			if (eClass != null) {
				EObject eObject = EcoreUtil.create(eClass);
				if (eObject instanceof NeoEMFInternalEObject) {
					neoemfEObject = (NeoEMFInternalEObject) eObject;
				} else {
					neoemfEObject = NeoEMFEObjectAdapterFactoryImpl.getAdapter(eObject, NeoEMFInternalEObject.class);
				}
				neoemfEObject.neoemfSetId(id.toString());
			} else {
				Logger.log(Logger.SEVERITY_ERROR, 
						MessageFormat.format("Element {0} does not have an associated EClass", id));
			}
			loadedEObjects.put(id, neoemfEObject);
		}
		if (neoemfEObject == null) {
			MessageFormat.format("Element {0} does not exist", id);
			return null;
			}
		if (neoemfEObject.neoemfResource() != getResource()) {
			neoemfEObject.neoemfSetResource(getResource());
		}
		return neoemfEObject;
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
	
	protected void updateLoadedEObjects(NeoEMFInternalEObject eObject) {
		loadedEObjects.put(eObject.neoemfId(), eObject);
	}
	
	protected void updateContainment(NeoEMFEObject object, EReference eReference, NeoEMFEObject referencedObject) {
		if (eReference.isContainment()) {
			
			// remove from root 
			
			try {
				Put put = new Put(Bytes.toBytes(referencedObject.neoemfId()));
				put.add(CONTAINMENT_FAMILY, CONTAINER_QUALIFIER, Bytes.toBytes(object.neoemfId()));
				put.add(CONTAINMENT_FAMILY, CONTAINING_FEATURE_QUALIFIER, Bytes.toBytes(eReference.getName()));
//						no need to use the CAS mech	
//						table.checkAndPut(
//						Bytes.toBytes(referencedObject.neoemfId()), CONTAINMENT_FAMILY, CONTAINER_QUALIFIER, CompareOp.NOT_EQUAL,
//						Bytes.toBytes(object.neoemfId()), put);
				table.put(put);

			} catch (IOException e) {
				Logger.log(Logger.SEVERITY_ERROR, 
						MessageFormat.format("Unable to update containment information for {0}", object));
			}
		}
	}
	
	protected void updateInstanceOf(NeoEMFEObject object) {
		
		try {
			Put put = new Put(Bytes.toBytes(object.neoemfId()));
			put.add(TYPE_FAMILY, METAMODEL_QUALIFIER, Bytes.toBytes(object.eClass().getEPackage().getNsURI()));
			put.add(TYPE_FAMILY, ECLASS_QUALIFIER, Bytes.toBytes(object.eClass().getName()));
			table.checkAndPut(Bytes.toBytes(object.neoemfId()), TYPE_FAMILY, ECLASS_QUALIFIER, null, put);

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
	 * {@link Table} for the {@link NeoEMFEObject} {@code object}
	 * 
	 * @param object
	 * @param feature
	 * @return The value of the {@code feature}. It can be a {@link String} for
	 *         single-valued {@link EStructuralFeature}s or a {@link String}[]
	 *         for many-valued {@link EStructuralFeature}s
	 */
	protected Object getFromTable(NeoEMFEObject object, EStructuralFeature feature) {
		try {
			Result result = table.get(new Get(Bytes.toBytes(object.neoemfId())));
			byte[] value = result.getValue(PROPERTY_FAMILY, Bytes.toBytes(feature.getName()));
			if (!feature.isMany()) {
				return Bytes.toString(value);
			} else {
				if (feature instanceof EAttribute) 
					return NeoEMFUtil.EncoderUtil.toStrings(value);
					return NeoEMFUtil.EncoderUtil.toStringsReferences(value);
			}
		} catch (IOException e) {
			Logger.log(Logger.SEVERITY_ERROR, MessageFormat.format("Unable to get property ''{0}'' for ''{1}''", feature.getName(), object));
		}
		return null;
	}


}
