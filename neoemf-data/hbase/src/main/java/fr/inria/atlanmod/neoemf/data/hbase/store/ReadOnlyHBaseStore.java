/*
 * Copyright (c) 2013-2017 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.data.hbase.store;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.data.hbase.structure.HBaseFeatureKey;
import fr.inria.atlanmod.neoemf.data.hbase.util.HBaseEncoderUtil;
import fr.inria.atlanmod.neoemf.data.store.AbstractPersistentStoreDecorator;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.util.logging.NeoLogger;

import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.function.Function;

/**
 * A {@link DirectWriteHBaseStore} that only allows read operations on the underlying database.
 * <p>
 * Read-only configuration allows to access model element faster, without checking value consistency
 * between database calls. This store re-implements all the mutators and throws an
 * {@link UnsupportedOperationException} when they are called, preventing resource corruption.
 * <p>
 * * This store can be used as a base store that can be complemented by plugging decorator stores on top of it
 * (see {@link AbstractPersistentStoreDecorator} subclasses) to provide additional features such as caching or logging.
 */
public class ReadOnlyHBaseStore extends DirectWriteHBaseStore {

    /**
     * In-memory cache that holds persisted model elements identifiers mapped to the {@link FeatureKey} used to access
     * them.
     */
    private final Cache<FeatureKey, Object> objectsCache;

    /**
     * Constructs a new {@code ReadOnlyHBaseStore} on the given {@code resource}.
     *
     * @param resource the resource to persist and access
     *
     * @throws IOException if the HBase server cannot be found
     */
    public ReadOnlyHBaseStore(Resource.Internal resource) throws IOException {
        super(resource);
        this.objectsCache = Caffeine.newBuilder().maximumSize(DEFAULT_CACHE_SIZE).build();
    }

    /**
     * Checks that the provided {@code tableName} corresponds to an existing HBase table and opens it.
     * <p>
     * Read-only mode is only available for existing databases.
     *
     * @param connection the connection to the HBase server
     * @param tableName  the name of the table to access on the server
     * @param admin      the administrator client of the HBase server
     *
     * @return the opened HBase table
     *
     * @throws IOException if the HBase server cannot be found or if {@code tableName} does not reference an existing
     *                     table
     */
    @Override
    protected Table initTable(Connection connection, TableName tableName, Admin admin) throws IOException {
        if (!admin.tableExists(tableName)) {
            throw new IOException(MessageFormat.format("Resource with URI {0} does not exist", tableName.getNameAsString()));
        }
        return connection.getTable(tableName);
    }

    /**
     * @throws UnsupportedOperationException operation not supported in read-only mode
     */
    @Override
    protected void updateContainment(PersistentEObject object, EReference reference, PersistentEObject referencedObject) {
        throw unsupportedOperation();
    }

    /**
     * @throws UnsupportedOperationException operation not supported in read-only mode
     */
    @Override
    protected void updateInstanceOf(PersistentEObject object) {
        throw unsupportedOperation();
    }

    /**
     * Gets the {@link EStructuralFeature} {@code feature} from the {@link Table} for the {@link
     * PersistentEObject} {@code object}.
     *
     * @return The value of the {@code feature}. It can be a {@link String} for single-valued {@link
     * EStructuralFeature}s or a {@link String}[] for many-valued {@link EStructuralFeature}s
     */
    @Override
    protected Object getFromTable(PersistentEObject object, EStructuralFeature feature) {
        HBaseFeatureKey entry = HBaseFeatureKey.from(object, feature);
        Object value = null;
        try {
            value = objectsCache.get(entry, new FeatureCacheLoader());
        }
        catch (Exception e) {
            NeoLogger.error("Unable to get property {0} for {1}", feature.getName(), object);
        }
        return value;
    }

    /**
     * @throws UnsupportedOperationException operation not supported in read-only mode
     */
    @Override
    public void unset(InternalEObject internalObject, EStructuralFeature feature) {
        throw unsupportedOperation();
    }

    /**
     * @throws UnsupportedOperationException operation not supported in read-only mode
     */
    @Override
    public void clear(InternalEObject internalObject, EStructuralFeature feature) {
        throw unsupportedOperation();
    }

    /**
     * @throws UnsupportedOperationException operation not supported in read-only mode
     */
    @Override
    public Object set(InternalEObject internalObject, EStructuralFeature feature, int index, Object value) {
        throw unsupportedOperation();
    }

    /**
     * @throws UnsupportedOperationException operation not supported in read-only mode
     */
    @Override
    public void add(InternalEObject internalObject, EStructuralFeature feature, int index, Object value) {
        throw unsupportedOperation();
    }

    /**
     * @throws UnsupportedOperationException operation not supported in read-only mode
     */
    @Override
    public Object remove(InternalEObject internalObject, EStructuralFeature feature, int index) {
        throw unsupportedOperation();
    }

    /**
     * @throws UnsupportedOperationException operation not supported in read-only mode
     */
    @Override
    public Object move(InternalEObject internalObject, EStructuralFeature feature, int targetIndex, int sourceIndex) {
        throw unsupportedOperation();
    }

    /**
     * Processes the exception to thrown when an operation is impossible.
     *
     * @return a new {@link UnsupportedOperationException}
     */
    private UnsupportedOperationException unsupportedOperation() {
        String message = "Unable to write to resource with URI {0}. Make sure that the resource is not read-only";
        String tableName = table.getName().getNameAsString();

        NeoLogger.error(message, tableName);
        return new UnsupportedOperationException(MessageFormat.format(message, tableName));
    }

    /**
     * A cache loader to retrieve a {@link Object} stored in the database.
     */
    private class FeatureCacheLoader implements Function<FeatureKey, Object> {

        @Override
        public Object apply(FeatureKey featureKey) {
            HBaseFeatureKey hBaseFeatureKey;
            if (featureKey instanceof HBaseFeatureKey) {
                hBaseFeatureKey = (HBaseFeatureKey) featureKey;
            }
            else {
                throw new IllegalArgumentException("FeatureKey is not an instance of " + HBaseFeatureKey.class.getSimpleName());
            }

            Result result;
            try {
                result = table.get(new Get(Bytes.toBytes(featureKey.id().toString())));
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }

            byte[] value = result.getValue(PROPERTY_FAMILY, Bytes.toBytes(featureKey.name()));
            if (!hBaseFeatureKey.feature().isMany()) {
                return Bytes.toString(value);
            }
            else {
                if (hBaseFeatureKey.feature() instanceof EAttribute) {
                    return HBaseEncoderUtil.toStrings(value);
                }
                else {
                    return HBaseEncoderUtil.toStringsReferences(value);
                }
            }
        }
    }
}