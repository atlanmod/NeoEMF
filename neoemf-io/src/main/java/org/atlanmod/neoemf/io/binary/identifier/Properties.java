package org.atlanmod.neoemf.io.binary.identifier;

import org.atlanmod.commons.collect.Flags;
import org.atlanmod.commons.log.Log;
import org.atlanmod.neoemf.io.binary.BufferWrangler;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Objects;

public class Properties {

    //@formatter:off
    static int ATTRIBUTE            = 0;
    static int FEATURE_MAP          = 1;
    static int CONTAINER            = 2;
    static int CONTAINMENT          = 3;
    static int MANY                 = 4;
    static int RESOLVE_PROXIES      = 5;
    static int TRANSIENT            = 6;
    static int VOLATILE             = 7;
    //@formatter:on

    private Flags bitset;


    public Properties(EAttribute attribute) {
        this(new Flags(8));
        bitset.set(ATTRIBUTE);
        this.extractProperties((EStructuralFeature.Internal) attribute);
    }

    public Properties(EReference reference) {
        this(new Flags(8));
        this.extractProperties((EStructuralFeature.Internal) reference);
    }

    public Properties(Flags flags) {
        this.bitset = flags;
    }

    private void extractProperties(EStructuralFeature.Internal feature) {
        bitset.set(FEATURE_MAP, feature.isFeatureMap());
        bitset.set(CONTAINER, feature.isContainer());
        bitset.set(CONTAINMENT, feature.isContainment());
        bitset.set(MANY, feature.isMany());
        bitset.set(RESOLVE_PROXIES, feature.isResolveProxies());
        bitset.set(TRANSIENT, feature.isTransient());
        bitset.set(VOLATILE, feature.isVolatile());
    }

    public byte[] toBytes() {
        return bitset.toBytes();
    }

    public void writeOn(BufferWrangler buffer) {
        byte[] bytes = toBytes();
        buffer.put(bytes);
    }

    public static Properties readFrom(BufferWrangler buffer) {
        byte[] bytes = new byte[1];
        buffer.get(bytes);
        return fromBytes(bytes);
    }

    @NotNull
    public static Properties fromBytes(byte[] bytes) {
        return new Properties(Flags.fromBytes(bytes));
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Properties that = (Properties) o;
        return Objects.equals(bitset, that.bitset);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bitset);
    }

    @Override
    public String toString() {
        return bitset.toString();
    }
}
