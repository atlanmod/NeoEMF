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

import java.util.Set;
import java.util.concurrent.Callable;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.internal.functions.Functions;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;

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
    protected Action blockingClose() {
        return table::close;
    }

    @Nonnull
    @Override
    protected Action blockingSave() {
        // TODO Implement this method
        return Functions.EMPTY_ACTION;
    }

    @Nonnull
    @Override
    public Maybe<SingleFeatureBean> containerOf(Id id) {
        checkNotNull(id, "id");

        // Create get operation
        Callable<Get> createGet = () -> new Get(idConverter.convert(id));

        // Deserialize the container
        Function<Result, SingleFeatureBean> mapFunc = r -> {
            byte[] byteId = r.getValue(FAMILY_CONTAINMENT, QUALIFIER_CONTAINER);
            byte[] byteName = r.getValue(FAMILY_CONTAINMENT, QUALIFIER_CONTAINING_FEATURE);

            return SingleFeatureBean.of(idConverter.revert(byteId), Bytes.toInt(byteName));
        };

        // The composed query to execute on the database
        Maybe<SingleFeatureBean> databaseQuery = Maybe.fromCallable(createGet)
                .map(table::get)
                .filter(r -> !r.isEmpty())
                .filter(r -> r.containsNonEmptyColumn(FAMILY_CONTAINMENT, QUALIFIER_CONTAINER))
                .map(mapFunc);

        return dispatcher().submit(databaseQuery);
    }

    @Nonnull
    @Override
    public Completable containerFor(Id id, SingleFeatureBean container) {
        checkNotNull(id, "id");
        checkNotNull(container, "container");

        // Create set operation
        Callable<Put> createPut = () -> new Put(idConverter.convert(id))
                .addColumn(FAMILY_CONTAINMENT, QUALIFIER_CONTAINER, idConverter.convert(container.owner()))
                .addColumn(FAMILY_CONTAINMENT, QUALIFIER_CONTAINING_FEATURE, Ints.toBytes(container.id()));

        // The composed query to execute on the database
        Completable databaseQuery = Maybe.fromCallable(createPut)
                .doOnSuccess(table::put)
                .ignoreElement();

        return dispatcher().submit(databaseQuery);
    }

    @Nonnull
    @Override
    public Completable removeContainer(Id id) {
        checkNotNull(id, "id");

        // Create remove operation
        Callable<Delete> createDelete = () -> new Delete(idConverter.convert(id))
                .addColumns(FAMILY_CONTAINMENT, QUALIFIER_CONTAINER)
                .addColumns(FAMILY_CONTAINMENT, QUALIFIER_CONTAINING_FEATURE);

        // The composed query to execute on the database
        Completable databaseQuery = Maybe.fromCallable(createDelete)
                .doOnSuccess(table::delete)
                .ignoreElement();

        return dispatcher().submit(databaseQuery);
    }

    @Nonnull
    @Override
    public Maybe<ClassBean> metaClassOf(Id id) {
        checkNotNull(id, "id");

        // Create get operation
        Callable<Get> createGet = () -> new Get(idConverter.convert(id));

        // Deserialize the meta-class
        Function<Result, ClassBean> mapFunc = r -> {
            byte[] byteName = r.getValue(FAMILY_TYPE, QUALIFIER_CLASS_NAME);
            byte[] byteUri = r.getValue(FAMILY_TYPE, QUALIFIER_CLASS_URI);

            return ClassBean.of(Bytes.toString(byteName), Bytes.toString(byteUri));
        };

        // The composed query to execute on the database
        Maybe<ClassBean> databaseQuery = Maybe.fromCallable(createGet)
                .map(table::get)
                .filter(r -> !r.isEmpty())
                .filter(r -> r.containsNonEmptyColumn(FAMILY_TYPE, QUALIFIER_CLASS_NAME))
                .map(mapFunc);

        return dispatcher().submit(databaseQuery);
    }

    @Nonnull
    @Override
    public Completable metaClassFor(Id id, ClassBean metaClass) {
        checkNotNull(id, "id");
        checkNotNull(metaClass, "metaClass");

        // Create get operation
        Callable<Get> createGet = () -> new Get(idConverter.convert(id))
                .addColumn(FAMILY_TYPE, QUALIFIER_CLASS_NAME);

        // Create put operation
        Callable<Put> createPut = () -> new Put(idConverter.convert(id))
                .addColumn(FAMILY_TYPE, QUALIFIER_CLASS_NAME, Strings.toBytes(metaClass.name()))
                .addColumn(FAMILY_TYPE, QUALIFIER_CLASS_URI, Strings.toBytes(metaClass.uri()));

        // Set the meta-class, if it does not already exists
        Completable setIfAbsent = Maybe.fromCallable(createPut)
                .doOnSuccess(table::put)
                .ignoreElement();

        // The composed query to execute on the database
        Completable databaseQuery = Maybe.fromCallable(createGet)
                .map(table::exists)
                .filter(Functions.equalsWith(true))
                .doOnSuccess(CommonQueries.classAlreadyExists())
                .switchIfEmpty(setIfAbsent.toMaybe())
                .ignoreElement();

        return dispatcher().submit(databaseQuery);
    }

    @Nonnull
    @Override
    public Flowable<Id> allInstancesOf(Set<ClassBean> metaClasses) {
        return CommonQueries.unsupportedAllInstancesOf();
    }

    @Nonnull
    @Override
    public <V> Maybe<V> valueOf(SingleFeatureBean key) {
        checkNotNull(key, "key");

        Callable<Get> createGet = () -> new Get(idConverter.convert(key.owner()));

        Function<Integer, byte[]> toQualifier = Ints::toBytes;

        Function<Result, V> mapFunc = r -> {
            byte[] byteValue = r.getValue(FAMILY_PROPERTY, toQualifier.apply(key.id()));

            return serializers.<V>forAny().deserialize(byteValue);
        };

        Maybe<V> databaseQuery = Maybe.fromCallable(createGet)
                .map(table::get)
                .filter(r -> !r.isEmpty())
                .filter(r -> r.containsNonEmptyColumn(FAMILY_PROPERTY, toQualifier.apply(key.id())))
                .map(mapFunc);

        return dispatcher().submit(databaseQuery);
    }

    @Nonnull
    @Override
    public <V> Maybe<V> valueFor(SingleFeatureBean key, V value) {
        checkNotNull(key, "key");
        checkNotNull(value, "value");

        Function<Integer, byte[]> toQualifier = Ints::toBytes;

        Callable<Put> createPut = () -> new Put(idConverter.convert(key.owner()))
                .addColumn(FAMILY_PROPERTY, toQualifier.apply(key.id()), serializers.<V>forAny().serialize(value));

        Action setFunc = () -> table.put(createPut.call());

        Consumer<V> replaceFunc = Functions.actionConsumer(setFunc);

        Maybe<V> databaseQuery = this.<V>valueOf(key)
                .doOnComplete(setFunc)
                .doOnSuccess(replaceFunc);

        return dispatcher().submit(databaseQuery);
    }

    @Nonnull
    @Override
    public Completable removeValue(SingleFeatureBean key) {
        checkNotNull(key, "key");

        Function<Integer, byte[]> toQualifier = Ints::toBytes;

        Callable<Delete> createDelete = () -> new Delete(idConverter.convert(key.owner()))
                .addColumns(FAMILY_PROPERTY, toQualifier.apply(key.id()));

        Completable databaseQuery = Maybe.fromCallable(createDelete)
                .doOnSuccess(table::delete)
                .ignoreElement();

        return dispatcher().submit(databaseQuery);
    }
}
