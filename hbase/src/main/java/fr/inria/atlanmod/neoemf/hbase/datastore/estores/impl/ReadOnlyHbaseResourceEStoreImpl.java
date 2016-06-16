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
package fr.inria.atlanmod.neoemf.hbase.datastore.estores.impl;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.impl.NeoEObjectAdapterFactoryImpl;
import fr.inria.atlanmod.neoemf.core.impl.StringId;
import fr.inria.atlanmod.neoemf.datastore.InternalPersistentEObject;
import fr.inria.atlanmod.neoemf.datastore.estores.SearcheableResourceEStore;
import fr.inria.atlanmod.neoemf.hbase.util.NeoHBaseUtil;
import fr.inria.atlanmod.neoemf.logger.NeoLogger;

import org.apache.commons.lang.ArrayUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.protobuf.generated.ZooKeeperProtos.Table;
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
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import static com.google.common.base.Preconditions.checkNotNull;

public class ReadOnlyHBaseResourceEStoreImpl implements SearcheableResourceEStore {

	private static final byte[] PROPERTY_FAMILY = Bytes.toBytes("p");
	private static final byte[] TYPE_FAMILY = Bytes.toBytes("t");
	private static final byte[] METAMODEL_QUALIFIER = Bytes.toBytes("m");
	private static final byte[] ECLASS_QUALIFIER = Bytes.toBytes("e");
	private static final byte[] CONTAINMENT_FAMILY = Bytes.toBytes("c");
	private static final byte[] CONTAINER_QUALIFIER = Bytes.toBytes("n");
	private static final byte[] CONTAINING_FEATURE_QUALIFIER = Bytes.toBytes("g");

	private final String UNSUP_MSG =
			"Unable to write to resource with URI {0}. Make sure that the resource is not read-only";

	private static Configuration conf = HBaseConfiguration.create();

	private Cache<Object, InternalPersistentEObject> loadedEObjects = CacheBuilder.newBuilder().softValues().build();

	private Cache<EStoreEntryKey, Object> featuresMap = CacheBuilder.newBuilder().softValues().build();

	//protected Connection connection;

	private HTable table;

	private Resource.Internal resource;

	public ReadOnlyHBaseResourceEStoreImpl(Resource.Internal resource) throws IOException {

		this.resource = resource;

		conf.set("hbase.zookeeper.quorum", resource.getURI().host());
		conf.set("hbase.zookeeper.property.clientPort",
				resource.getURI().port() != null ? resource.getURI().port() : "2181");

		TableName tableName = TableName.valueOf(NeoHBaseUtil.formatURI(resource.getURI()));
		@SuppressWarnings("resource")
		HBaseAdmin admin = new HBaseAdmin(conf);

		if (!admin.tableExists(tableName)) {
			throw new IOException(MessageFormat.format("Resource with URI {0} does not exist",
					tableName.getNameAsString()));
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
		}
		else if (feature instanceof EReference) {
			return ((EReference) feature).isContainer() ? getContainer(object) : get(object,
					(EReference) feature,
					index);
		}
		else {
			throw new IllegalArgumentException(feature.toString());
		}
	}

	protected Object get(InternalEObject object, EAttribute eAttribute, int index) {
		Object value = getFromTableIfNotExisting(object, eAttribute);
		if (!eAttribute.isMany()) {
			return parseValue(eAttribute, (String) value);
		}
		else {
			String[] array = (String[]) value;
			return parseValue(eAttribute, array[index]);
		}
	}

	protected Object get(InternalEObject object, EReference eReference, int index) {
		Object value = getFromTableIfNotExisting(object, eReference);
		if (!eReference.isMany()) {
			return eObject(new StringId((String) value));
		}
		else {
			String[] array = (String[]) value;
			return eObject(new StringId(array[index]));
		}
	}


	@Override
	public Object set(InternalEObject object, EStructuralFeature feature, int index, Object value) {
		NeoLogger.error(UNSUP_MSG, table.getName().getNameAsString());
		throw new UnsupportedOperationException(MessageFormat.format(UNSUP_MSG, table.getName().getNameAsString()));
	}

	@Override
	public boolean isSet(InternalEObject object, EStructuralFeature feature) {
		InternalPersistentEObject neoEObject = checkNotNull(
				NeoEObjectAdapterFactoryImpl.getAdapter(object,	InternalPersistentEObject.class));
		try {
			Result result = table.get(new Get(Bytes.toBytes(neoEObject.id().toString())));
			byte[] value = result.getValue(PROPERTY_FAMILY, Bytes.toBytes(feature.getName()));
			return value != null;
		}
		catch (IOException e) {
			NeoLogger.error("Unable to get information for element ''{0}''", neoEObject);
		}
		return false;
	}


	@Override
	public void add(InternalEObject object, EStructuralFeature feature, int index, Object value) {
		NeoLogger.error(UNSUP_MSG, table.getName().getNameAsString());
		throw new UnsupportedOperationException(MessageFormat.format(UNSUP_MSG, table.getName().getNameAsString()));
	}

	@Override
	public Object remove(InternalEObject object, EStructuralFeature feature, int index) {
		// TODO Nothing guarantees that the index is still the same.
		NeoLogger.error(UNSUP_MSG, table.getName().getNameAsString());
		throw new UnsupportedOperationException(MessageFormat.format(UNSUP_MSG, table.getName().getNameAsString()));
	}

	@Override
	public Object move(InternalEObject object, EStructuralFeature feature, int targetIndex, int sourceIndex) {
		NeoLogger.error(UNSUP_MSG, table.getName().getNameAsString());
		throw new UnsupportedOperationException(MessageFormat.format(UNSUP_MSG, table.getName().getNameAsString()));
	}


	@Override
	public void unset(InternalEObject object, EStructuralFeature feature) {
		NeoLogger.error(UNSUP_MSG, table.getName().getNameAsString());
		throw new UnsupportedOperationException(MessageFormat.format(UNSUP_MSG, table.getName().getNameAsString()));
	}


	@Override
	public boolean isEmpty(InternalEObject object, EStructuralFeature feature) {
		return size(object, feature) == 0;
	}


	@Override
	public int size(InternalEObject object, EStructuralFeature feature) {
		String[] array = (String[]) getFromTableIfNotExisting(object, feature);
		return array != null ? array.length : 0;
	}


	@Override
	public boolean contains(InternalEObject object, EStructuralFeature feature, Object value) {
		return indexOf(object, feature, value) != -1;
	}

	@Override
	public int indexOf(InternalEObject object, EStructuralFeature feature, Object value) {
		String[] array = (String[]) getFromTableIfNotExisting(object, feature);
		if (array == null) {
			return -1;
		}
		if (feature instanceof EAttribute) {
			return ArrayUtils.indexOf(array, serializeValue((EAttribute) feature, value));
		}
		else {
			InternalPersistentEObject childEObject = checkNotNull(
					NeoEObjectAdapterFactoryImpl.getAdapter(value, InternalPersistentEObject.class));
			return ArrayUtils.indexOf(array, childEObject.id().toString());
		}
	}


	@Override
	public int lastIndexOf(InternalEObject object, EStructuralFeature feature, Object value) {
		String[] array = (String[]) getFromTableIfNotExisting(object, feature);
		if (array == null) {
			return -1;
		}
		if (feature instanceof EAttribute) {
			return ArrayUtils.lastIndexOf(array, serializeValue((EAttribute) feature, value));
		}
		else {
			InternalPersistentEObject childEObject = checkNotNull(
					NeoEObjectAdapterFactoryImpl.getAdapter(value, InternalPersistentEObject.class));
			return ArrayUtils.lastIndexOf(array, childEObject.id().toString());
		}
	}


	@Override
	public void clear(InternalEObject object, EStructuralFeature feature) {
		NeoLogger.error(UNSUP_MSG, table.getName().getNameAsString());
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
		}
		else {
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
			Result result = table.get(new Get(Bytes.toBytes(neoEObject.id().toString())));
			String containerId = Bytes.toString(result.getValue(CONTAINMENT_FAMILY, CONTAINER_QUALIFIER));
			String containingFeatureName = Bytes.toString(result.getValue(CONTAINMENT_FAMILY,
					CONTAINING_FEATURE_QUALIFIER));

			if (containerId != null && containingFeatureName != null) {
				return (InternalEObject) eObject(new StringId(containerId));
			}

		}
		catch (IOException e) {
			NeoLogger.error("Unable to get containment information for {0}", neoEObject);
		}
		return null;
	}


	@Override
	public EStructuralFeature getContainingFeature(InternalEObject object) {
		InternalPersistentEObject neoEObject = checkNotNull(
				NeoEObjectAdapterFactoryImpl.getAdapter(object, InternalPersistentEObject.class));

		try {
			Result result = table.get(new Get(Bytes.toBytes(neoEObject.id().toString())));
			String containerId = Bytes.toString(result.getValue(CONTAINMENT_FAMILY, CONTAINER_QUALIFIER));
			String containingFeatureName = Bytes.toString(result.getValue(CONTAINMENT_FAMILY,
					CONTAINING_FEATURE_QUALIFIER));

			if (containerId != null && containingFeatureName != null) {
				EObject container = eObject(new StringId(containerId));
				return container.eClass().getEStructuralFeature(containingFeatureName);
			}

		}
		catch (IOException e) {
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
				}
				else {
					neoEObject = NeoEObjectAdapterFactoryImpl.getAdapter(eObject, InternalPersistentEObject.class);
				}
				neoEObject.id(id);
			}
			else {
				NeoLogger.error("Element {0} does not have an associated EClass", id);
			}
			loadedEObjects.put(id, neoEObject);
		}
		if (neoEObject.resource() != resource()) {
			neoEObject.resource(resource());
		}
		return neoEObject;
	}

	protected EClass resolveInstanceOf(Id id) {
		try {
			Result result = table.get(new Get(Bytes.toBytes(id.toString())));
			String nsURI = Bytes.toString(result.getValue(TYPE_FAMILY, METAMODEL_QUALIFIER));
			String className = Bytes.toString(result.getValue(TYPE_FAMILY, ECLASS_QUALIFIER));
			if (nsURI != null && className != null) {
				return (EClass) Registry.INSTANCE.getEPackage(nsURI).getEClassifier(className);
			}
		}
		catch (IOException e) {
			NeoLogger.error("Unable to get instance of information for {0}", id);
		}
		return null;
	}

	protected void updateLoadedEObjects(InternalPersistentEObject eObject) {
		loadedEObjects.put(eObject.id(), eObject);
	}

	protected void updateContainment(InternalPersistentEObject object,
									 EReference eReference,
									 EObject referencedObject)
	{
		throw new UnsupportedOperationException();
	}

	protected void updateInstanceOf(InternalPersistentEObject object) {
		NeoLogger.error(UNSUP_MSG, table.getName().getNameAsString());
		throw new UnsupportedOperationException(MessageFormat.format(UNSUP_MSG, table.getName().getNameAsString()));
	}


	protected static Object parseValue(EAttribute eAttribute, String value) {
		return value != null ? EcoreUtil.createFromString(eAttribute.getEAttributeType(), value) : null;
	}

	protected static String serializeValue(EAttribute eAttribute, Object value) {
		return value != null ? EcoreUtil.convertToString(eAttribute.getEAttributeType(), value) : null;
	}

	/**
	 * Gets the {@link EStructuralFeature} {@code feature} from the {@link Table} for the {@link
	 * fr.inria.atlanmod.neoemf.core.PersistentEObject} {@code object}
	 *
	 * @return The value of the {@code feature}. It can be a {@link String} for single-valued {@link
	 * EStructuralFeature}s or a {@link String}[] for many-valued {@link EStructuralFeature}s
	 */
	private Object getFromTableIfNotExisting(InternalEObject object, EStructuralFeature feature) {
		InternalPersistentEObject neoEObject = checkNotNull(
				NeoEObjectAdapterFactoryImpl.getAdapter(object,	InternalPersistentEObject.class));

		EStoreEntryKey entry = new EStoreEntryKey(neoEObject.id().toString(), feature);
		Object returnValue = null;
		try {
			returnValue = featuresMap.get(entry, new FeatureCacheLoader(neoEObject.id(), feature));
		}
		catch (ExecutionException e) {
			NeoLogger.error("Unable to get property ''{0}'' for ''{1}''", feature.getName(), object);
		}
		return returnValue;
	}

	@Override
	public EList<EObject> getAllInstances(EClass eClass, boolean strict) {
		throw new UnsupportedOperationException();
	}

	private class EStoreEntryKey {

		private String eObject;
		private EStructuralFeature eStructuralFeature;

		public EStoreEntryKey(String eObject, EStructuralFeature eStructuralFeature) {
			this.eObject = eObject;
			this.eStructuralFeature = eStructuralFeature;
		}

		@Override
		public int hashCode() {
			return Objects.hash(getOuterType(), eObject, eStructuralFeature);
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
			}
			else if (!eObject.equals(other.eObject))
				return false;
			if (eStructuralFeature == null) {
				if (other.eStructuralFeature != null)
					return false;
			}
			else if (!eStructuralFeature.equals(other.eStructuralFeature))
				return false;
			return true;
		}

		private SearcheableResourceEStore getOuterType() {
			return ReadOnlyHBaseResourceEStoreImpl.this;
		}

		public String getEObject() {
			return eObject;
		}

		public EStructuralFeature getEStructuralFeature() {
			return eStructuralFeature;
		}
	}

	private class FeatureCacheLoader implements Callable<Object> {

		private final Id id;
		private final EStructuralFeature feature;

		public FeatureCacheLoader(Id id, EStructuralFeature feature) {
			this.id = id;
			this.feature = feature;
		}

		@Override
		public Object call() throws Exception {
				Result result = table.get(new Get(Bytes.toBytes(id.toString())));
				byte[] value = result.getValue(PROPERTY_FAMILY, Bytes.toBytes(feature.getName()));
				if (!feature.isMany()) {
					return Bytes.toString(value);
				}
				else {
					if (feature instanceof EAttribute) {
						return NeoHBaseUtil.EncoderUtil.toStrings(value);
					}
					else {
						return NeoHBaseUtil.EncoderUtil.toStringsReferences(value);
					}
				}
		}
	}
}