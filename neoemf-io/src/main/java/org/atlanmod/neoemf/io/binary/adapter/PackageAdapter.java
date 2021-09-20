package org.atlanmod.neoemf.io.binary.adapter;

import org.atlanmod.commons.log.Log;
import org.atlanmod.neoemf.io.binary.identifier.PackageIdentifier;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.EcoreUtil;

import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PackageAdapter extends EcoreAdapter {
    private EPackage adaptee;
    private final int id;
    private final BinaryAdapterProvider provider;
    private URI uri;
    private String namespace;
    private Map<EClass, ClassAdapter> classes = new HashMap<>();


    public PackageAdapter(PackageIdentifier identifier) {
        adaptee = identifier.ePackage();
        this.id = identifier.index();
        this.provider = identifier.provider();

        uri = EcoreUtil.getURI(adaptee);
        namespace = adaptee.getNsURI();
        this.verify();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PackageAdapter that = (PackageAdapter) o;
        return Objects.equals(uri, that.uri) && Objects.equals(namespace, that.namespace);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uri, namespace);
    }

    @Override
    public String toString() {
        return "Adapter(namespace= " + namespace + ", URI= " + uri + ")";
    }

    public int id() {
        return id;
    }

    public String uri() {
        return uri.toString();
    }

    public String namespace() {
        return namespace;
    }

    public EPackage adaptee() {
        return adaptee;
    }

    public Map<EClass, ClassAdapter> classes() {
        return classes;
    }

    public ClassAdapter adapt(EClass eClass) {
        return classes.computeIfAbsent(eClass, provider::createEClassAdapter);
    }

    private void verify() {
        BitSet bitSet = new BitSet();
        for(EClassifier each : adaptee.getEClassifiers()) {
            int id = each.getClassifierID();
            if (bitSet.get(id)) {
                Log.warn("Package {0} has more than one classes with the same id: {1}",
                        adaptee, id);
            } else {
                bitSet.set(id);
            }
        }
    }
}
