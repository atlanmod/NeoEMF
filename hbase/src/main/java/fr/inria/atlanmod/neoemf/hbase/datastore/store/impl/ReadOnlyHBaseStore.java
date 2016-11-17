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

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.hbase.datastore.store.impl.cache.HBaseFeatureKey;
import fr.inria.atlanmod.neoemf.hbase.util.NeoHBaseUtil;
import fr.inria.atlanmod.neoemf.logging.NeoLogger;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.function.Function;

public class ReadOnlyHBaseStore extends DirectWriteHBaseStore {

    // TODO Find the more predictable maximum cache size
    private static final int DEFAULT_CACHE_SIZE = 10000;

    private final Cache<HBaseFeatureKey, Object> cache = Caffeine.newBuilder().maximumSize(DEFAULT_CACHE_SIZE).build();

    public ReadOnlyHBaseStore(Resource.Internal resource) throws IOException {
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
     * Gets the {@link EStructuralFeature} {@code feature} from the {@link HTable} for the {@link
     * PersistentEObject} {@code object}
     *
     * @return The value of the {@code feature}. It can be a {@link String} for single-valued {@link
     * EStructuralFeature}s or a {@link String}[] for many-valued {@link EStructuralFeature}s
     */
    @Override
    protected Object getFromTable(PersistentEObject object, EStructuralFeature feature) {
        PersistentEObject neoEObject = PersistentEObject.from(object);

        HBaseFeatureKey entry = new HBaseFeatureKey(neoEObject.id(), feature);
        Object returnValue = null;
        try {
            returnValue = cache.get(entry, new FeatureCacheLoader());
        }
        catch (Exception e) {
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

    private class FeatureCacheLoader implements Function<HBaseFeatureKey, Object> {

        @Override
        public Object apply(HBaseFeatureKey featureKey) {
            Result result;
            try {
                result = table.get(new Get(Bytes.toBytes(featureKey.id().toString())));
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }

            byte[] value = result.getValue(PROPERTY_FAMILY, Bytes.toBytes(featureKey.name()));
            if (!featureKey.feature().isMany()) {
                return Bytes.toString(value);
            }
            else {
                if (featureKey.feature() instanceof EAttribute) {
                    return NeoHBaseUtil.EncoderUtil.toStrings(value);
                }
                else {
                    return NeoHBaseUtil.EncoderUtil.toStringsReferences(value);
                }
            }
        }
    }
}