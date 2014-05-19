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
import org.eclipse.emf.ecore.InternalEObject.EStore;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.jboss.util.collection.SoftValueHashMap;
import org.mapdb.DB;
import org.mapdb.Fun;
import org.mapdb.Fun.Tuple2;
import org.mapdb.Fun.Tuple3;

import fr.inria.atlanmod.kyanos.core.KyanosEObject;
import fr.inria.atlanmod.kyanos.core.KyanosInternalEObject;
import fr.inria.atlanmod.kyanos.core.impl.KyanosEObjectAdapterFactoryImpl;
import fr.inria.atlanmod.kyanos.datastore.estores.SearcheableResourceEStore;
import fr.inria.atlanmod.kyanos.datastore.estores.impl.pojo.ContainerInfo;
import fr.inria.atlanmod.kyanos.datastore.estores.impl.pojo.EClassInfo;
import fr.inria.atlanmod.kyanos.logger.Logger;

public class DirectWriteMapWithIndexesResourceEStoreImpl implements SearcheableResourceEStore {

	protected static final String INSTANCE_OF = "kyanosInstanceOf";
	protected static final String CONTAINER = "eContainer";

	@SuppressWarnings("unchecked")
	protected Map<Object, KyanosInternalEObject> loadedEObjects = new SoftValueHashMap();
	
	protected DB db;
	
	protected Map<Tuple3<String, String, Integer>, Object> map;
	protected Map<Tuple2<String, String>, Integer> sizesMap;
	
	protected Map<String, EClassInfo> instanceOfMap;

	protected Map<String, ContainerInfo> containersMap;
	
	protected Resource.Internal resource;

	public DirectWriteMapWithIndexesResourceEStoreImpl(Resource.Internal resource, DB db) {
		this.db = db;
		this.resource = resource;
		this.map = db.getHashMap("Kyanos");
		this.sizesMap = db.getHashMap("SIZES");
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
			Object value = map.get(Fun.t3(kyanosEObject.kyanosId(), feature.getName(), index));
			return parseMapValue((EAttribute) feature, value);
		} else if (feature instanceof EReference) {
			Object value = map.get(Fun.t3(kyanosEObject.kyanosId(), feature.getName(), index));
			return getEObject((String) value);
		} else {
			throw new IllegalArgumentException(feature.toString());
		}
	}
	
	@Override
	public Object set(InternalEObject object, EStructuralFeature feature, int index, Object value) {
		KyanosEObject kyanosEObject = KyanosEObjectAdapterFactoryImpl.getAdapter(object, KyanosEObject.class);
		if (!feature.isMany()) {
			sizesMap.put(Fun.t2(kyanosEObject.kyanosId(), feature.getName()), EStore.NO_INDEX);
		}
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
		Object oldValue = map.put(Fun.t3(object.kyanosId(), eAttribute.getName(), index), serializeToMapValue(eAttribute, value));
		return parseMapValue(eAttribute, oldValue);
	}

	protected Object set(KyanosEObject object, EReference eReference, int index, KyanosEObject referencedObject) {
		updateContainment(object, eReference, referencedObject);
		updateInstanceOf(referencedObject);
		Object oldId = map.put(Fun.t3(object.kyanosId(), eReference.getName(), index), referencedObject.kyanosId());
		return oldId != null ? getEObject((String) oldId) : null;
	}


	@Override
	public boolean isSet(InternalEObject object, EStructuralFeature feature) {
		KyanosEObject kyanosEObject = KyanosEObjectAdapterFactoryImpl.getAdapter(object, KyanosEObject.class);
		return sizesMap.containsKey(Fun.t2(kyanosEObject.kyanosId(), feature.getName()));
	}


	@Override
	public void add(InternalEObject object, EStructuralFeature feature, int index, Object value) {
		KyanosEObject kyanosEObject = KyanosEObjectAdapterFactoryImpl.getAdapter(object, KyanosEObject.class);
		// Make space for the new element
		Integer size = sizesMap.get(Fun.t2(kyanosEObject.kyanosId(), feature.getName()));
		if (size == null) {
			size = 0;
		}
		for (int i = size - 1; i >= index; i--) {
			Object movingValue = map.get(Fun.t3(kyanosEObject.kyanosId(),  feature.getName(), i));
			map.put(Fun.t3(kyanosEObject.kyanosId(), feature.getName(), i + 1), movingValue);
		}
		sizesMap.put(Fun.t2(kyanosEObject.kyanosId(), feature.getName()), size + 1);
		
		// add element
		if (feature instanceof EAttribute) {
			map.put(Fun.t3(kyanosEObject.kyanosId(), feature.getName(), index), value);
		} else if (feature instanceof EReference) {
			KyanosEObject referencedEObject = KyanosEObjectAdapterFactoryImpl.getAdapter(value, KyanosEObject.class);
			updateContainment(kyanosEObject, (EReference) feature, referencedEObject);
			updateInstanceOf(referencedEObject);
			map.put(Fun.t3(kyanosEObject.kyanosId(), feature.getName(), index), referencedEObject.kyanosId());
		} else {
			throw new IllegalArgumentException(feature.toString());
		}
	}

	@Override
	public Object remove(InternalEObject object, EStructuralFeature feature, int index) {
		KyanosEObject kyanosEObject = KyanosEObjectAdapterFactoryImpl.getAdapter(object, KyanosEObject.class);

		Integer size = sizesMap.get(Fun.t2(kyanosEObject.kyanosId(), feature.getName()));
		// Gete element to remove
		Object returnValue = map.get(Fun.t3(kyanosEObject.kyanosId(),feature.getName(), index));
		// Update indexes (element to remove is overwriten)
		for (int i = index + 1; i < size; i++) {
			Object movingValue = map.get(Fun.t3(kyanosEObject.kyanosId(), feature.getName(), i));
			map.put(Fun.t3(kyanosEObject.kyanosId(), feature.getName(), i - 1), movingValue);
		}
		sizesMap.put(Fun.t2(kyanosEObject.kyanosId(), feature.getName()), size - 1);
		return returnValue;
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
		sizesMap.remove(Fun.t2(kyanosEObject.kyanosId(), feature.getName()));
	}


	@Override
	public boolean isEmpty(InternalEObject object, EStructuralFeature feature) {
		return size(object, feature) == 0; 
	}


	@Override
	public int size(InternalEObject object, EStructuralFeature feature) {
		KyanosEObject kyanosEObject = KyanosEObjectAdapterFactoryImpl.getAdapter(object, KyanosEObject.class);
		Integer size = sizesMap.get(Fun.t2(kyanosEObject.kyanosId(), feature.getName()));
		return size != null ? size : 0; 
	}


	@Override
	public boolean contains(InternalEObject object, EStructuralFeature feature, Object value) {
		return indexOf(object, feature, value) != -1;
	}


	@Override
	public int indexOf(InternalEObject object, EStructuralFeature feature, Object value) {
		return ArrayUtils.indexOf(toArray(object, feature), value);
	}


	@Override
	public int lastIndexOf(InternalEObject object, EStructuralFeature feature, Object value) {
		return ArrayUtils.indexOf(toArray(object, feature), value);
	}


	@Override
	public void clear(InternalEObject object, EStructuralFeature feature) {
		KyanosEObject kyanosEObject = KyanosEObjectAdapterFactoryImpl.getAdapter(object, KyanosEObject.class);
		Integer size = sizesMap.remove(Fun.t2(kyanosEObject.kyanosId(), feature.getName()));
//		for (int i = 0; i < size; i++) {
//			map.remove(Fun.t3(kyanosEObject.kyanosId(), feature.getName(), i));
//		}
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
	
}
