package fr.inria.atlanmod.neoemf.data.map.core;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.data.structure.ClassInfo;
import fr.inria.atlanmod.neoemf.data.structure.ContainerInfo;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.MultivaluedFeatureKey;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sunye on 23/01/2017.
 */
public class MapBackendStub implements MapBackend {


    private final Map<Id, ContainerInfo> containersMap = new HashMap<>();
    private final Map<Id, ClassInfo> instanceOfMap = new HashMap<>();
    private final Map<FeatureKey, Object> features = new HashMap<>();
    private final Map<MultivaluedFeatureKey, Object> multivaluedFeatures = new HashMap<>();


    @Override
    public Map<String, Object> getAll() {
        return null;
    }

    @Override
    public <E> E get(String name) {
        return null;
    }

    @Override
    public ContainerInfo containerFor(Id id) {
        return containersMap.get(id);
    }

    @Override
    public void storeContainer(Id id, ContainerInfo container) {
        containersMap.put(id, container);
    }

    @Override
    public ClassInfo metaclassFor(Id id) {
        return instanceOfMap.get(id);
    }

    @Override
    public void storeMetaclass(Id id, ClassInfo metaclass) {
        instanceOfMap.put(id, metaclass);
    }

    @Override
    public Object storeValue(FeatureKey key, Object value) {
        return features.put(key, value);
    }

    @Override
    public Object valueOf(FeatureKey key) {
        return features.get(key);
    }

    @Override
    public Object removeFeature(FeatureKey key) {
        return features.remove(key);
    }

    @Override
    public boolean isFeatureSet(FeatureKey key) {
        return features.containsKey(key);
    }

    @Override
    public Object storeValueAtIndex(MultivaluedFeatureKey key, Object value) {
        return multivaluedFeatures.put(key, value);
    }

    @Override
    public Object valueAtIndex(MultivaluedFeatureKey key) {
        return multivaluedFeatures.get(key);
    }


    @Override
    public boolean isClosed() {
        return false;
    }

    @Override
    public void close() {

    }

    @Override
    public void save() {

    }
}
