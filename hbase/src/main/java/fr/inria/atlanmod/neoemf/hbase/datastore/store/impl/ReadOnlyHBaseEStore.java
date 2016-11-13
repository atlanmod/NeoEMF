/*
 * Copyright (c) 2013-2016 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.hbase.datastore.store.impl;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.datastore.store.PersistentEStore;
import fr.inria.atlanmod.neoemf.hbase.util.NeoHBaseUtil;
import fr.inria.atlanmod.neoemf.logging.NeoLogger;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.protobuf.generated.ZooKeeperProtos.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

import static java.util.Objects.isNull;

public class ReadOnlyHBaseEStore extends DirectWriteHBaseEStore {

    private final Cache<EStoreKey, Object> cache = CacheBuilder.newBuilder().softValues().build();

    public ReadOnlyHBaseEStore(Resource.Internal resource) throws IOException {
        super(resource);
    }

    @Override
    protected HTable initTable(Configuration conf, TableName tableName, HBaseAdmin admin) throws IOException {
        if (!admin.tableExists(tableName)) {
            throw new IOException(MessageFormat.format("Resource with URI {0} does not exist", tableName.getNameAsString()));
        }
        return new HTable(conf, tableName);
    }

    @Override
    protected void updateContainment(PersistentEObject object, EReference eReference, PersistentEObject referencedObject) {
        throw unsupportedOperation();
    }

    @Override
    protected void updateInstanceOf(PersistentEObject object) {
        throw unsupportedOperation();
    }

    /**
     * Gets the {@link EStructuralFeature} {@code feature} from the {@link Table} for the {@link
     * fr.inria.atlanmod.neoemf.core.PersistentEObject} {@code object}
     *
     * @return The value of the {@code feature}. It can be a {@link String} for single-valued {@link
     * EStructuralFeature}s or a {@link String}[] for many-valued {@link EStructuralFeature}s
     */
    @Override
    protected Object getFromTable(PersistentEObject object, EStructuralFeature feature) {
        PersistentEObject neoEObject = PersistentEObject.from(object);

        EStoreKey entry = new EStoreKey(neoEObject.id().toString(), feature);
        Object returnValue = null;
        try {
            returnValue = cache.get(entry, new FeatureCacheLoader(neoEObject.id(), feature));
        }
        catch (ExecutionException e) {
            NeoLogger.error("Unable to get property ''{0}'' for ''{1}''", feature.getName(), object);
        }
        return returnValue;
    }

    @Override
    public void unset(InternalEObject object, EStructuralFeature feature) {
        throw unsupportedOperation();
    }

    @Override
    public void clear(InternalEObject object, EStructuralFeature feature) {
        throw unsupportedOperation();
    }

    @Override
    public Object set(InternalEObject object, EStructuralFeature feature, int index, Object value) {
        throw unsupportedOperation();
    }

    @Override
    public void add(InternalEObject object, EStructuralFeature feature, int index, Object value) {
        throw unsupportedOperation();
    }

    @Override
    public Object remove(InternalEObject object, EStructuralFeature feature, int index) {
        throw unsupportedOperation();
    }

    @Override
    public Object move(InternalEObject object, EStructuralFeature feature, int targetIndex, int sourceIndex) {
        throw unsupportedOperation();
    }

    private UnsupportedOperationException unsupportedOperation() {
        String message = "Unable to write to resource with URI {0}. Make sure that the resource is not read-only";
        String tableName = table.getName().getNameAsString();

        NeoLogger.error(message, tableName);
        return new UnsupportedOperationException(MessageFormat.format(message, tableName));
    }

    private class EStoreKey {

        private final String eObject;
        private final EStructuralFeature eStructuralFeature;

        public EStoreKey(String eObject, EStructuralFeature eStructuralFeature) {
            this.eObject = eObject;
            this.eStructuralFeature = eStructuralFeature;
        }

        @Override
        public int hashCode() {
            return Objects.hash(getOuterType(), eObject, eStructuralFeature);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (isNull(obj) || getClass() != obj.getClass()) {
                return false;
            }
            EStoreKey other = (EStoreKey) obj;
            return Objects.equals(eObject, other.eObject)
                    && Objects.equals(eStructuralFeature, other.eStructuralFeature)
                    && Objects.equals(getOuterType(), other.getOuterType());
        }

        private PersistentEStore getOuterType() {
            return ReadOnlyHBaseEStore.this;
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