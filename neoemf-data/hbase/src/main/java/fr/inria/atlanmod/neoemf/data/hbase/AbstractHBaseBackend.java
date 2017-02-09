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

package fr.inria.atlanmod.neoemf.data.hbase;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.StringId;
import fr.inria.atlanmod.neoemf.data.AbstractPersistenceBackend;
import fr.inria.atlanmod.neoemf.data.structure.ContainerValue;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.MetaclassValue;

import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static java.util.Objects.isNull;

/**
 *
 */
@ParametersAreNonnullByDefault
abstract class AbstractHBaseBackend extends AbstractPersistenceBackend implements HBaseBackend {

    /**
     * The column family holding properties.
     */
    protected static final byte[] PROPERTY_FAMILY = Bytes.toBytes("p");

    /**
     * ???
     */
    protected static final byte[] TYPE_FAMILY = Bytes.toBytes("t");

    /**
     * ???
     */
    protected static final byte[] METAMODEL_QUALIFIER = Bytes.toBytes("m");

    /**
     * ???
     */
    protected static final byte[] ECLASS_QUALIFIER = Bytes.toBytes("e");

    /**
     * ???
     */
    protected static final byte[] CONTAINMENT_FAMILY = Bytes.toBytes("c");

    /**
     * ???
     */
    protected static final byte[] CONTAINER_QUALIFIER = Bytes.toBytes("n");

    /**
     * ???
     */
    protected static final byte[] CONTAINING_FEATURE_QUALIFIER = Bytes.toBytes("g");

    /**
     * The HBase table used to access the model.
     */
    protected final Table table;

    protected AbstractHBaseBackend(Table table) {
        this.table = table;
    }

    @Override
    public void save() {
        // TODO Implement this method
    }

    @Override
    public void close() {
        // TODO Implement this method
    }

    @Override
    public boolean isClosed() {
        return false;
    }

    @Override
    public boolean isDistributed() {
        return true;
    }

    @Override
    public void create(Id id) {
        // Do nothing
    }

    @Override
    public boolean has(Id id) {
        return false;
    }

    @Nonnull
    @Override
    public Optional<ContainerValue> containerOf(Id id) {
        return fromDatabase(id)
                .map(result -> {
                    byte[] byteId = result.getValue(CONTAINMENT_FAMILY, CONTAINER_QUALIFIER);
                    byte[] byteName = result.getValue(CONTAINMENT_FAMILY, CONTAINING_FEATURE_QUALIFIER);
                    if (isNull(byteId) || isNull(byteName)) {
                        return Optional.<ContainerValue>empty();
                    }
                    return Optional.of(ContainerValue.of(StringId.of(Bytes.toString(byteId)), Bytes.toString(byteName)));
                })
                .orElse(Optional.empty());
    }

    @Override
    public void containerFor(Id id, ContainerValue container) {
        try {
            Put put = new Put(Bytes.toBytes(id.toString()));
            put.addColumn(CONTAINMENT_FAMILY, CONTAINER_QUALIFIER, Bytes.toBytes(container.id().toString()));
            put.addColumn(CONTAINMENT_FAMILY, CONTAINING_FEATURE_QUALIFIER, Bytes.toBytes(container.name()));
            table.put(put);
        }
        catch (IOException ignore) {
        }
    }

    @Nonnull
    @Override
    public Optional<MetaclassValue> metaclassOf(Id id) {
        return fromDatabase(id)
                .map(result -> {
                    byte[] byteName = result.getValue(TYPE_FAMILY, METAMODEL_QUALIFIER);
                    byte[] byteUri = result.getValue(TYPE_FAMILY, ECLASS_QUALIFIER);
                    if (isNull(byteName) || isNull(byteUri)) {
                        return Optional.<MetaclassValue>empty();
                    }
                    return Optional.of(MetaclassValue.of(Bytes.toString(byteName), Bytes.toString(byteUri)));
                })
                .orElse(Optional.empty());
    }

    @Override
    public void metaclassFor(Id id, MetaclassValue metaclass) {
        try {
            byte[] idAsBytes = Bytes.toBytes(id.toString());
            Put put = new Put(idAsBytes);
            put.addColumn(TYPE_FAMILY, METAMODEL_QUALIFIER, Bytes.toBytes(metaclass.uri()));
            put.addColumn(TYPE_FAMILY, ECLASS_QUALIFIER, Bytes.toBytes(metaclass.name()));
            table.checkAndPut(idAsBytes, TYPE_FAMILY, ECLASS_QUALIFIER, null, put);
        }
        catch (IOException ignore) {
        }
    }

    protected Optional<byte[]> fromDatabase(FeatureKey key) {
        return fromDatabase(key.id())
                .map(result -> Optional.ofNullable(result.getValue(PROPERTY_FAMILY, Bytes.toBytes(key.name()))))
                .orElse(Optional.empty());
    }

    protected void toDatabase(FeatureKey key, @Nullable byte[] value) {
        try {
            byte[] idAsBytes = Bytes.toBytes(key.id().toString());
            Put put = new Put(idAsBytes);
            put.addColumn(PROPERTY_FAMILY, Bytes.toBytes(key.name()), value);
            table.put(put);
        }
        catch (IOException ignore) {
        }
    }

    protected void outDatabase(FeatureKey key) {
        try {
            byte[] idAsBytes = Bytes.toBytes(key.id().toString());
            Delete delete = new Delete(idAsBytes);
            delete.addColumn(PROPERTY_FAMILY, Bytes.toBytes(key.name()));
            table.delete(delete);
        }
        catch (IOException ignore) {
        }
    }

    private Optional<Result> fromDatabase(Id id) {
        Optional<Result> value = Optional.empty();

        try {
            byte[] idAsBytes = Bytes.toBytes(id.toString());
            Get get = new Get(idAsBytes);
            Result result = table.get(get);
            if (!result.isEmpty()) {
                value = Optional.of(result);
            }
        }
        catch (IOException ignore) {
        }

        return value;
    }
}
