/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.hbase;

import fr.inria.atlanmod.commons.function.Converter;
import fr.inria.atlanmod.commons.io.serializer.Serializer;
import fr.inria.atlanmod.commons.primitive.Bytes;
import fr.inria.atlanmod.commons.primitive.Ints;
import fr.inria.atlanmod.commons.primitive.Strings;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.IdConverters;
import fr.inria.atlanmod.neoemf.data.AbstractBackend;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.serializer.BeanSerializerFactory;
import fr.inria.atlanmod.neoemf.data.query.CommonQueries;

import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.Callable;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.functions.Action;
import io.reactivex.functions.Function;
import io.reactivex.internal.functions.Functions;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * An abstract {@link HBaseBackend} that provides overall behavior for the management of a HBase database.
 */
@ParametersAreNonnullByDefault
abstract class AbstractHBaseBackend extends AbstractBackend implements HBaseBackend {

    /**
     * The column family holding properties.
     */
    protected static final byte[] FAMILY_PROPERTY = Strings.toBytes("p");

    /**
     * The column family holding instances.
     */
    protected static final byte[] FAMILY_TYPE = Strings.toBytes("t");

    /**
     * The column family holding containments.
     */
    protected static final byte[] FAMILY_CONTAINMENT = Strings.toBytes("c");

    /**
     * The column qualifier holding the URI of meta-models.
     */
    private static final byte[] QUALIFIER_CLASS_URI = Strings.toBytes("m");

    /**
     * The column qualifier holding the name of classes.
     */
    private static final byte[] QUALIFIER_CLASS_NAME = Strings.toBytes("e");

    /**
     * The column qualifier holding the identifier of containers.
     */
    private static final byte[] QUALIFIER_CONTAINER = Strings.toBytes("n");

    /**
     * The column qualifier holding the name of the feature used to retrieve the containment.
     */
    private static final byte[] QUALIFIER_CONTAINING_FEATURE = Strings.toBytes("g");

    /**
     * The {@link BeanSerializerFactory} to use for creating the {@link Serializer} instances.
     */
    @Nonnull
    protected final BeanSerializerFactory serializers = BeanSerializerFactory.getInstance();

    /**
     * A converter to use {@code byte[]} instead of {@link Id}.
     */
    @Nonnull
    protected final Converter<Id, byte[]> idConverter = Converter.from(
            id -> Strings.toBytes(IdConverters.withHexString().convert(id)),
            bs -> IdConverters.withHexString().revert(Bytes.toString(bs)));

    /**
     * The HBase table used to access the model.
     */
    @Nonnull
    protected final Table table;

    /**
     * Constructs a new {@code AbstractHBaseBackend} on the given {@code table}.
     *
     * @param table the HBase table
     */
    protected AbstractHBaseBackend(Table table) {
        checkNotNull(table, "table");

        this.table = table;
    }

    @Nonnull
    @Override
    protected Completable asyncClose() {
        // Close the table
        Action closeFunc = table::close;

        // The composed query to execute on the database
        Completable databaseQuery = Completable.fromAction(closeFunc);

        return dispatcher().submit(databaseQuery);
    }

    @Nonnull
    @Override
    protected Completable asyncSave() {
        // TODO Implement this method
        return Completable.complete();
    }

    @Nonnull
    @Override
    public Maybe<SingleFeatureBean> containerOf(Id id) {
        Action checkFunc = () -> checkNotNull(id, "id");

        // Retrieve the binary representation of the container
        Callable<Result> getFunc = () -> {
            Get get = new Get(idConverter.convert(id));
            return table.get(get);
        };

        // Deserialize the container
        Function<Result, SingleFeatureBean> mapFunc = r -> {
            byte[] byteId = r.getValue(FAMILY_CONTAINMENT, QUALIFIER_CONTAINER);
            byte[] byteName = r.getValue(FAMILY_CONTAINMENT, QUALIFIER_CONTAINING_FEATURE);

            return nonNull(byteId) && nonNull(byteName)
                    ? SingleFeatureBean.of(idConverter.revert(byteId), Bytes.toInt(byteName))
                    : null;
        };

        // The composed query to execute on the database
        Maybe<SingleFeatureBean> databaseQuery = Maybe.fromCallable(getFunc)
                .map(mapFunc);

        return dispatcher().submit(checkFunc, databaseQuery);
    }

    @Nonnull
    @Override
    public Completable containerFor(Id id, SingleFeatureBean container) {
        Action checkFunc = () -> {
            checkNotNull(id, "id");
            checkNotNull(container, "container");
        };

        // Serialize and define the container
        Action setFunc = () -> {
            Put put = new Put(idConverter.convert(id))
                    .addColumn(FAMILY_CONTAINMENT, QUALIFIER_CONTAINER, idConverter.convert(container.owner()))
                    .addColumn(FAMILY_CONTAINMENT, QUALIFIER_CONTAINING_FEATURE, Ints.toBytes(container.id()));

            table.put(put);
        };

        // The composed query to execute on the database
        Completable databaseQuery = Completable.fromAction(setFunc);

        return dispatcher().submit(checkFunc, databaseQuery);
    }

    @Nonnull
    @Override
    public Completable removeContainer(Id id) {
        Action checkFunc = () -> checkNotNull(id, "id");

        // Remove the container
        Action removeFunc = () -> {
            Delete delete = new Delete(idConverter.convert(id))
                    .addColumns(FAMILY_CONTAINMENT, QUALIFIER_CONTAINER)
                    .addColumns(FAMILY_CONTAINMENT, QUALIFIER_CONTAINING_FEATURE);

            table.delete(delete);
        };

        // The composed query to execute on the database
        Completable databaseQuery = Completable.fromAction(removeFunc);

        return dispatcher().submit(checkFunc, databaseQuery);
    }

    @Nonnull
    @Override
    public Maybe<ClassBean> metaClassOf(Id id) {
        Action checkFunc = () -> checkNotNull(id, "id");

        // Retrieve the binary representation of the meta-class
        Callable<Result> getFunc = () -> {
            Get get = new Get(idConverter.convert(id));
            return table.get(get);
        };

        // Deserialize the meta-class
        Function<Result, ClassBean> mapFunc = r -> {
            byte[] byteName = r.getValue(FAMILY_TYPE, QUALIFIER_CLASS_NAME);
            byte[] byteUri = r.getValue(FAMILY_TYPE, QUALIFIER_CLASS_URI);

            if (nonNull(byteName) && nonNull(byteUri)) {
                return ClassBean.of(Bytes.toString(byteName), Bytes.toString(byteUri));
            }
            return null;
        };

        // The composed query to execute on the database
        Maybe<ClassBean> databaseQuery = Maybe.fromCallable(getFunc)
                .map(mapFunc);

        return dispatcher().submit(checkFunc, databaseQuery);
    }

    @Nonnull
    @Override
    public Completable metaClassFor(Id id, ClassBean metaClass) {
        Action checkFunc = () -> {
            checkNotNull(id, "id");
            checkNotNull(metaClass, "metaClass");
        };

        // Check that the meta-class does not already exist
        Callable<Boolean> existsFunc = () -> {
            Get get = new Get(idConverter.convert(id))
                    .addColumn(FAMILY_TYPE, QUALIFIER_CLASS_NAME);

            return table.exists(get);
        };

        // Serialize and define the meta-class
        Callable<Boolean> setFunc = () -> {
            Put put = new Put(idConverter.convert(id))
                    .addColumn(FAMILY_TYPE, QUALIFIER_CLASS_NAME, Strings.toBytes(metaClass.name()))
                    .addColumn(FAMILY_TYPE, QUALIFIER_CLASS_URI, Strings.toBytes(metaClass.uri()));

            table.put(put);
            return true;
        };

        // The composed query to execute on the database
        Completable databaseQuery = Single.fromCallable(existsFunc)
                .filter(Functions.equalsWith(true))
                .doOnSuccess(CommonQueries.classAlreadyExists())
                .switchIfEmpty(Single.fromCallable(setFunc))
                .toCompletable();

        return dispatcher().submit(checkFunc, databaseQuery);
    }

    @Nonnull
    @Override
    public Flowable<Id> allInstancesOf(Set<ClassBean> metaClasses) {
        return Flowable.error(new UnsupportedOperationException());
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(SingleFeatureBean key) {
        checkNotNull(key, "key");

        try {
            Get get = new Get(idConverter.convert(key.owner()));
            Result result = table.get(get);

            if (result.isEmpty()) {
                return Optional.empty();
            }

            byte[] byteValue = result.getValue(FAMILY_PROPERTY, Ints.toBytes(key.id()));

            if (isNull(byteValue)) {
                return Optional.empty();
            }

            return Optional.of(serializers.<V>forAny().deserialize(byteValue));
        }
        catch (IOException e) {
            handleException(e);
            return Optional.empty();
        }
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(SingleFeatureBean key, V value) {
        checkNotNull(key, "key");
        checkNotNull(value, "value");

        Optional<V> previousValue = valueOf(key);

        try {
            Put put = new Put(idConverter.convert(key.owner()))
                    .addColumn(FAMILY_PROPERTY, Ints.toBytes(key.id()), serializers.<V>forAny().serialize(value));

            table.put(put);
        }
        catch (IOException e) {
            handleException(e);
        }

        return previousValue;
    }

    @Override
    public void removeValue(SingleFeatureBean key) {
        checkNotNull(key, "key");

        try {
            Delete delete = new Delete(idConverter.convert(key.owner()))
                    .addColumns(FAMILY_PROPERTY, Ints.toBytes(key.id()));

            table.delete(delete);
        }
        catch (IOException e) {
            handleException(e);
        }
    }

    private void handleException(IOException e) {
        throw new RuntimeException(e);
    }
}
