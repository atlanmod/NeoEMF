package fr.inria.atlanmod.kyanos.datastore.estores.impl;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
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
import org.mapdb.DB;
import org.mapdb.Fun;
import org.mapdb.Fun.Tuple2;

import fr.inria.atlanmod.kyanos.core.KyanosEObject;
import fr.inria.atlanmod.kyanos.core.KyanosInternalEObject;
import fr.inria.atlanmod.kyanos.core.impl.KyanosEObjectAdapterFactoryImpl;
import fr.inria.atlanmod.kyanos.datastore.estores.SearcheableResourceEStore;
import fr.inria.atlanmod.kyanos.datastore.estores.impl.pojo.ContainerInfo;
import fr.inria.atlanmod.kyanos.datastore.estores.impl.pojo.EClassInfo;
import fr.inria.atlanmod.kyanos.logger.Logger;

public class DirectWriteMapResourceEStoreImpl implements SearcheableResourceEStore {

	protected static final String INSTANCE_OF = "kyanosInstanceOf";
	protected static final String CONTAINER = "eContainer";

	@SuppressWarnings("unchecked")
	protected Map<Object, KyanosInternalEObject> loadedEObjects = new SoftValueHashMap();
	
	protected DB db;
	
	protected Map<Tuple2<String, String>, Object> map;
	
	protected Map<String, EClassInfo> instanceOfMap;

	protected Map<String, ContainerInfo> containersMap;
	
	protected Resource.Internal resource;

	public DirectWriteMapResourceEStoreImpl(Resource.Internal resource, DB db) {
		this.db = db;
		this.resource = resource;
		this.map = db.getHashMap("Kyanos");
		this.instanceOfMap = db.getHashMap(INSTANCE_OF);
		this.containersMap = db.getHashMap(CONTAINER);
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
			return get(kyanosEObject, (EReference) feature, index);
		} else {
			throw new IllegalArgumentException(feature.toString());
		}
	}
	
	protected Object get(KyanosEObject object, EAttribute eAttribute, int index) {
		Object value = getFromMap(object, eAttribute);
		if (!eAttribute.isMany()) {
			return parseMapValue(eAttribute, value);
		} else {
			Object[] array = (Object[]) value;
			return parseMapValue(eAttribute, array[index]);
		}
	}

	protected Object get(KyanosEObject object, EReference eReference, int index) {
		Object value = getFromMap(object, eReference);
		if (!eReference.isMany()) {
			return getEObject((String) value);
		} else {
			Object[] array = (Object[]) value;
			return getEObject((String) array[index]);
		}
	}


	@Override
	public Object set(InternalEObject object, EStructuralFeature feature, int index, Object value) {
		KyanosEObject kyanosEObject = KyanosEObjectAdapterFactoryImpl.getAdapter(object, KyanosEObject.class);
		if (feature instanceof EAttribute) {
			return set(kyanosEObject, (EAttribute) feature, index, value);
		} else if (feature instanceof EReference) {
			KyanosEObject referencedEObject = KyanosEObjectAdapterFactoryImpl.getAdapter(value, KyanosEObject.class);
			return set(kyanosEObject, (EReference) feature, index, referencedEObject);
		} else {
			throw new IllegalArgumentException(feature.toString());
		}
	}

	protected Object set(KyanosEObject object, EAttribute eAttribute, int index, Object value) {
		if (!eAttribute.isMany()) {
			Object oldValue = map.put(Fun.t2(object.kyanosId(), eAttribute.getName()), serializeToMapValue(eAttribute, value));
			return parseMapValue(eAttribute, oldValue);
		} else {
			Object[] array = (Object[]) getFromMap(object, eAttribute);
			Object oldValue = array[index]; 
			array[index] = serializeToMapValue(eAttribute, value);
			map.put(Fun.t2(object.kyanosId(), eAttribute.getName()), array);
			return parseMapValue(eAttribute, oldValue);
		}
	}

	protected Object set(KyanosEObject object, EReference eReference, int index, KyanosEObject referencedObject) {
		updateContainment(object, eReference, referencedObject);
		updateInstanceOf(referencedObject);
		if (!eReference.isMany()) {
			Object oldId = map.put(Fun.t2(object.kyanosId(), eReference.getName()), referencedObject.kyanosId());
			return oldId != null ? getEObject((String) oldId) : null;
		} else {
			Object[] array = (Object[]) getFromMap(object, eReference);
			Object oldId = array[index];
			array[index] = referencedObject.kyanosId();
			map.put(Fun.t2(object.kyanosId(), eReference.getName()), array);
			return oldId != null ? getEObject((String) oldId) : null;
		}
	}


	@Override
	public boolean isSet(InternalEObject object, EStructuralFeature feature) {
		KyanosEObject kyanosEObject = KyanosEObjectAdapterFactoryImpl.getAdapter(object, KyanosEObject.class);
		return map.containsKey(Fun.t2(kyanosEObject.kyanosId(), feature.getName()));
	}


	@Override
	public void add(InternalEObject object, EStructuralFeature feature, int index, Object value) {
		KyanosEObject kyanosEObject = KyanosEObjectAdapterFactoryImpl.getAdapter(object, KyanosEObject.class);
		if (feature instanceof EAttribute) {
			add(kyanosEObject, (EAttribute) feature, index, value);
		} else if (feature instanceof EReference) {
			KyanosEObject referencedEObject = KyanosEObjectAdapterFactoryImpl.getAdapter(value, KyanosEObject.class);
			add(kyanosEObject, (EReference) feature, index, referencedEObject);
		} else {
			throw new IllegalArgumentException(feature.toString());
		}
	}

	protected void add(KyanosEObject object, EAttribute eAttribute, int index, Object value) {
		Object[] array = (Object[]) getFromMap(object, eAttribute);
		if (array == null) {
			array = new Object[] {};
		}
		array = ArrayUtils.add(array, index, serializeToMapValue(eAttribute, value));
		map.put(Fun.t2(object.kyanosId(), eAttribute.getName()), array);
	}

	protected void add(KyanosEObject object, EReference eReference, int index, KyanosEObject referencedObject) {
		updateContainment(object, eReference, referencedObject);
		updateInstanceOf(referencedObject);
		Object[] array = (Object[]) getFromMap(object, eReference);
		if (array == null) {
			array = new Object[] {};
		}
		array = ArrayUtils.add(array, index, referencedObject.kyanosId());
		map.put(Fun.t2(object.kyanosId(), eReference.getName()), array);
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
		Object[] array = (Object[]) getFromMap(object, eAttribute);
		Object oldValue = array[index];
		array = ArrayUtils.remove(array, index);
		map.put(Fun.t2(object.kyanosId(), eAttribute.getName()), array);
		return parseMapValue(eAttribute, oldValue);
	}

	protected Object remove(KyanosEObject object, EReference eReference, int index) {
		Object[] array = (Object[]) getFromMap(object, eReference);
		Object oldId = array[index];
		array = ArrayUtils.remove(array, index);
		map.put(Fun.t2(object.kyanosId(), eReference.getName()), array);
		return getEObject((String) oldId);

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
		map.remove(Fun.t2(kyanosEObject.kyanosId(), feature.getName()));
	}


	@Override
	public boolean isEmpty(InternalEObject object, EStructuralFeature feature) {
		return size(object, feature) == 0; 
	}


	@Override
	public int size(InternalEObject object, EStructuralFeature feature) {
		KyanosEObject kyanosEObject = KyanosEObjectAdapterFactoryImpl.getAdapter(object, KyanosEObject.class);
		Object[] array = (Object[]) getFromMap(kyanosEObject, feature);
		return array != null ? array.length : 0; 
	}


	@Override
	public boolean contains(InternalEObject object, EStructuralFeature feature, Object value) {
		return indexOf(object, feature, value) != -1;
	}


	@Override
	public int indexOf(InternalEObject object, EStructuralFeature feature, Object value) {
		KyanosEObject kyanosEObject = KyanosEObjectAdapterFactoryImpl.getAdapter(object, KyanosEObject.class);
		Object[] array = (Object[]) getFromMap(kyanosEObject, feature);
		if (array == null) {
			return -1;
		}
		if (feature instanceof EAttribute) {
			return ArrayUtils.indexOf(array, serializeToMapValue((EAttribute) feature, value));
		} else {
			KyanosEObject childEObject = KyanosEObjectAdapterFactoryImpl.getAdapter(value, KyanosEObject.class);
			return ArrayUtils.indexOf(array, childEObject.kyanosId());
		}
	}


	@Override
	public int lastIndexOf(InternalEObject object, EStructuralFeature feature, Object value) {
		KyanosEObject kyanosEObject = KyanosEObjectAdapterFactoryImpl.getAdapter(object, KyanosEObject.class);
		Object[] array = (Object[]) getFromMap(kyanosEObject, feature);
		if (array == null) {
			return -1;
		}
		if (feature instanceof EAttribute) {
			return ArrayUtils.lastIndexOf(array, serializeToMapValue((EAttribute) feature, value));
		} else {
			KyanosEObject childEObject = KyanosEObjectAdapterFactoryImpl.getAdapter(value, KyanosEObject.class);
			return ArrayUtils.lastIndexOf(array, childEObject.kyanosId());
		}
	}


	@Override
	public void clear(InternalEObject object, EStructuralFeature feature) {
		KyanosEObject kyanosEObject = KyanosEObjectAdapterFactoryImpl.getAdapter(object, KyanosEObject.class);
		map.put(Fun.t2(kyanosEObject.kyanosId(), feature.getName()), new Object[] {});
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
		ContainerInfo info = containersMap.get(kyanosEObject.kyanosId());
		if (info != null) {
			return (InternalEObject) getEObject(info.containerId);
		}
		return null;
	}


	@Override
	public EStructuralFeature getContainingFeature(InternalEObject object) {
		KyanosEObject kyanosEObject = KyanosEObjectAdapterFactoryImpl.getAdapter(object, KyanosEObject.class);
		ContainerInfo info = containersMap.get(kyanosEObject.kyanosId());
		if (info != null) {
			EObject container = getEObject(info.containerId);
			container.eClass().getEStructuralFeature(info.containingFeatureName);
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
		EClassInfo eClassInfo = instanceOfMap.get(id);
		if (eClassInfo != null) {
			EClass eClass = (EClass) Registry.INSTANCE.getEPackage(eClassInfo.nsURI).getEClassifier(eClassInfo.className);
			return eClass;
		}
		return null;
	}
	
	protected void updateContainment(KyanosEObject object, EReference eReference, KyanosEObject referencedObject) {
		if (eReference.isContainment()) {
			ContainerInfo info = containersMap.get(referencedObject.kyanosId());
			if (info == null || !StringUtils.equals(info.containerId, object.kyanosId())) {
				containersMap.put(referencedObject.kyanosId(), new ContainerInfo(object.kyanosId(), eReference.getName()));
			}
		}
	}
	
	protected void updateInstanceOf(KyanosEObject object) {
		EClassInfo info = instanceOfMap.get(object.kyanosId());
		if (info == null) {
			instanceOfMap.put(object.kyanosId(), new EClassInfo(object.eClass().getEPackage().getNsURI(), object.eClass().getName()));
		}
	}


	protected static Object parseMapValue(EAttribute eAttribute, Object property) {
		return property != null ? EcoreUtil.createFromString(eAttribute.getEAttributeType(), property.toString()) : null;
	}

	protected static Object serializeToMapValue(EAttribute eAttribute, Object value) {
		return value != null ? EcoreUtil.convertToString(eAttribute.getEAttributeType(), value) : null;
	}
	
	protected Object getFromMap(KyanosEObject object, EStructuralFeature feature) {
		return map.get(Fun.t2(object.kyanosId(), feature.getName()));
	}
	
}
