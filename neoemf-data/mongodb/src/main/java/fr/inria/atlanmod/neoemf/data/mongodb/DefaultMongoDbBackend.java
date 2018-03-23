/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import fr.inria.atlanmod.commons.Throwables;
import fr.inria.atlanmod.commons.collect.MoreIterables;
import fr.inria.atlanmod.commons.io.serializer.BinarySerializerFactory;
import fr.inria.atlanmod.commons.io.serializer.StringSerializer;
import fr.inria.atlanmod.commons.io.serializer.StringSerializerFactory;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.IdConverters;
import fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;
import fr.inria.atlanmod.neoemf.data.mongodb.config.MongoDbConfig;
import fr.inria.atlanmod.neoemf.data.mongodb.model.StoredInstance;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.swing.text.html.Option;
import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.include;
import static com.mongodb.client.model.Updates.*;
import static fr.inria.atlanmod.commons.Preconditions.checkNotContainsNull;
import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;

/**
 * The default {@link MongoDbBackend} mapping.
 *
 * @see MongoDbBackendFactory
 */
@ParametersAreNonnullByDefault
class DefaultMongoDbBackend extends AbstractMongoDbBackend {

    private static final StringSerializer<Object> SERIALIZER = StringSerializerFactory.base64(BinarySerializerFactory.getInstance().forAny());

    /**
     * Constructs a new {@code DefaultMongoDbBackend}.
     *
     * @see MongoDbBackendFactory
     */
    protected DefaultMongoDbBackend(MongoDbConfig config, MongoClient client, MongoDatabase database) {
        super(config, client, database);
    }

    //region Single-valued attributes

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(SingleFeatureBean key) {
        checkNotNull(key, "key");

        StoredInstance instance = find(eq("_id", key.owner().toHexString()))
                .projection(include("singlevaluedValues")).first();

        String kid = String.valueOf(key.id());

        return instance != null && instance.getSinglevaluedValues().containsKey(kid)
                ? Optional.of((V) deserializeValue(instance.getSinglevaluedValues().get(kid)))
                : Optional.empty();
    }

    private String serializeValue(Object o) {
        return SERIALIZER.convert(o);
    }

    private Object deserializeValue(String value) {
        try {
            return SERIALIZER.deserialize(value);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(SingleFeatureBean key, V value) {
        checkNotNull(key, "key");
        checkNotNull(value, "value");

        String hexId = key.owner().toHexString();

        StoredInstance instance = find(eq("_id", hexId)).projection(include("singlevaluedValues")).first();

        if (instance == null) {
            instance = new StoredInstance();
            instance.setId(hexId);

            instance.getSinglevaluedValues().put(String.valueOf(key.id()), serializeValue(value));

            insertOne(instance);

            return Optional.empty();
        } else {
            updateOne(
                    eq("_id", hexId),
                    set("singlevaluedValues." + key.id(), serializeValue(value))
            );

            if (instance.getSinglevaluedValues().containsKey(String.valueOf(key.id()))) {
                return Optional.of((V) deserializeValue(instance.getSinglevaluedValues().get(String.valueOf(key.id()))));
            } else {
                return Optional.empty();
            }
        }
    }

    @Override
    public void removeValue(SingleFeatureBean key) {
        checkNotNull(key, "key");

        updateOne(
                eq("_id", key.owner().toHexString()),
                unset("singlevaluedValues." + key.id()));
    }

    //endregion

    //region Single-valued references

    @Nonnull
    @Override
    public Optional<Id> referenceOf(SingleFeatureBean key) {
        checkNotNull(key, "key");

        String hexId = key.owner().toHexString();
        String stringKeyId = String.valueOf(key.id());

        StoredInstance instance = find(eq("_id", hexId))
                .projection(include("singlevaluedReferences." + key.id())).first();

        if (instance == null || instance.getSinglevaluedReferences() == null) {
            return Optional.empty();
        } else {
            if (instance.getSinglevaluedReferences().containsKey(stringKeyId)) {
                return Optional.of(IdConverters.withHexString().revert(instance.getSinglevaluedReferences().get(stringKeyId)));
            } else {

                return Optional.empty();
            }
        }
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(SingleFeatureBean key, Id reference) {
        checkNotNull(key, "key");
        checkNotNull(reference, "reference");

        String hexId = key.owner().toHexString();
        String stringKeyId = String.valueOf(key.id());

        StoredInstance instance = find(eq("_id", hexId))
                .projection(include("singlevaluedReferences." + key.id())).first();

        if (instance == null) {
            instance = new StoredInstance();
            instance.setId(hexId);

            instance.getSinglevaluedReferences().put(stringKeyId, reference.toHexString());

            insertOne(instance);

            return Optional.empty();
        } else {
            updateOne(
                    eq("_id", hexId),
                    set("singlevaluedReferences." + stringKeyId, reference.toHexString()));

            if (instance.getSinglevaluedReferences().containsKey(stringKeyId)) {
                return Optional.of(IdConverters.withHexString().revert(instance.getSinglevaluedReferences().get(stringKeyId)));
            } else {
                return Optional.empty();
            }

        }
    }

    @Override
    public void removeReference(SingleFeatureBean key) {
        checkNotNull(key, "key");

        String hexId = key.owner().toHexString();
        String stringKeyId = String.valueOf(key.id());

        updateOne(
                eq("_id", hexId),
                unset("singlevaluedReferences." + stringKeyId));
    }

    //endregion

    //region Multi-valued attributes

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(ManyFeatureBean key) {
        checkNotNull(key, "key");

        String hexId = key.owner().toHexString();
        String stringKeyId = String.valueOf(key.id());

        StoredInstance instance = find(eq("_id", hexId))
                .projection(include("multivaluedValues." + stringKeyId)).first();

        if (instance == null || instance.getMultivaluedValues() == null) {
            return Optional.empty();
        } else {
            if (instance.getMultivaluedValues().containsKey(stringKeyId)) {
                return Optional.of((V) IdConverters.withHexString()
                        .revert(instance.getMultivaluedValues().get(stringKeyId).get(key.position())));
            } else {
                return Optional.empty();
            }
        }
    }

    @Nonnull
    @Override
    public <V> Stream<V> allValuesOf(SingleFeatureBean key) {
        checkNotNull(key, "key");

        StoredInstance instance = find(
                        eq("_id", key.owner().toHexString()))
                .projection(include("multivaluedValues." + key.id()))
                .first();

        if (instance == null || instance.getMultivaluedReferences().size() == 0)
            return Stream.empty();

        List<String> refs = instance.getMultivaluedValues().get(String.valueOf(key.id()));

        return MoreIterables.stream(refs).
                map(v -> (V) deserializeValue(v));
    }

    @Nonnull
    public <V> Optional<V> valueFor(ManyFeatureBean key, V value) {
        checkNotNull(key, "key");
        checkNotNull(value, "value");

        String hexId = key.owner().toHexString();
        String stringKeyId = String.valueOf(key.id());

        StoredInstance instance = find(eq("_id", hexId))
                .projection(include(("multivaluedValues"))).first();

        if (instance == null) {
            return Optional.empty();
        } else {
            if (!instance.getMultivaluedValues().containsKey(stringKeyId)){
                throw new NoSuchElementException();
            }
            else if (key.position() >= instance.getMultivaluedValues().get(stringKeyId).size()) {
                throw new NoSuchElementException();
            }
            else{
                List<String> multivaluedValues = instance.getMultivaluedValues().get(stringKeyId);
                Optional<V> toReturn = Optional.of((V) deserializeValue(instance.getMultivaluedValues().get(stringKeyId).get(key.position())));

                multivaluedValues.set(key.position(), serializeValue(value));

                updateOne(eq("_id", hexId),
                        set("multivaluedValues." + stringKeyId, multivaluedValues));

                return toReturn;
            }
        }
    }

    @Override
    public <V> void addValue(ManyFeatureBean key, V value) {
        checkNotNull(key, "key");
        checkNotNull(value, "value");

        String hexId = key.owner().toHexString();
        String stringKeyId = String.valueOf(key.id());

        StoredInstance instance = find(eq("_id", hexId))
                .projection(include(("multivaluedValues."+stringKeyId))).first();

        //TODO vérifier si franchement, ya pas mieux (update la liste directement dans mongo)
        List<String> multivaluedValues = instance.getMultivaluedValues().get(stringKeyId);
        if (multivaluedValues == null) {
            multivaluedValues = new ArrayList<>();
        }

        if (key.position() != 0 && key.position() > multivaluedValues.size()){
            throw new IndexOutOfBoundsException();
        }

        if (key.position() == multivaluedValues.size()){
            multivaluedValues.add(serializeValue(value));
        } else {
            multivaluedValues.add(key.position(), serializeValue(value));
        }

        if (instance != null) {
            updateOne(
                    eq("_id", hexId),
                    set("multivaluedValues." + stringKeyId, multivaluedValues));
        }

    }

    @Override
    public <V> void addAllValues(ManyFeatureBean key, List<? extends V> collection) {
        checkNotNull(key, "key");
        checkNotNull(collection, "collection");
        checkNotContainsNull(collection);

        if (collection.isEmpty()) {
            return;
        }

        String hexId = key.owner().toHexString();
        String stringKeyId = String.valueOf(key.id());

        StoredInstance instance = find(eq("_id", hexId))
                .projection(include(("multivaluedValues."+stringKeyId))).first();

        List<String> multivaluedValues = instance.getMultivaluedValues().get(stringKeyId);
        if (multivaluedValues == null) {
            multivaluedValues = new ArrayList<>();
        }


        if(key.position() > multivaluedValues.size()){
            throw new IndexOutOfBoundsException();
        }

        List<String> toAdd = new ArrayList<String>();
        for(V value : collection){
            toAdd.add(serializeValue(value));
        }
        multivaluedValues.addAll(key.position(), toAdd);

        if (instance != null) {
            updateOne(
                    eq("_id", hexId),
                    set("multivaluedValues." + stringKeyId, multivaluedValues));
        }


    }

    @Nonnull
    @Override
    public <V> Optional<V> removeValue(ManyFeatureBean key) {
        checkNotNull(key, "key");

        String hexId = key.owner().toHexString();
        String stringKeyId = String.valueOf(key.id());



        StoredInstance instance = find(eq("_id", hexId))
                .projection(include("multivaluedValues")).first();

        Optional<V> res = Optional.empty();

        res = (instance != null && instance.getMultivaluedValues().containsKey(stringKeyId) && instance.getMultivaluedValues().get(stringKeyId).size() < key.position())
                ? Optional.of((V) deserializeValue(instance.getMultivaluedValues().get(stringKeyId).get(key.position())))
                : Optional.empty();

        if (res == Optional.empty()) return res;


        updateOne(
                eq("_id", hexId),
                pull("multivaluedValues." + stringKeyId, res));

        return res;
    }

    @Override
    public void removeAllValues(SingleFeatureBean key) {
        checkNotNull(key, "key");

        String hexId = key.owner().toHexString();

        updateOne(
                eq("_id", hexId),
                combine(unset("singlevaluedValues"), unset("multivaluedValues")));

    }

    @Nonnull
    @Nonnegative
    @Override
    public Optional<Integer> sizeOfValue(SingleFeatureBean key) {
        checkNotNull(key, "key");

        String hexId = key.owner().toHexString();

        StoredInstance instance = find(eq("_id", hexId))
                .projection(include("singlevaluedValues")).first();

        if (instance == null || instance.getSinglevaluedValues() == null || instance.getSinglevaluedValues().isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(instance.getSinglevaluedValues().size());
        }
    }

    //endregion

    //region Multi-valued references

    @Nonnull
    @Override
    public Optional<Id> referenceOf(ManyFeatureBean key) {
        checkNotNull(key, "key");

        String hexId = key.owner().toHexString();
        String stringKeyId = String.valueOf(key.id());

        StoredInstance instance = find(eq("_id", hexId))
                .projection(include("multivaluedReferences." + key.id())).first();

        if (instance == null || instance.getMultivaluedReferences() == null) {
            return Optional.empty();
        } else if (!instance.getMultivaluedReferences().containsKey(stringKeyId)) {
            return Optional.empty();
        } else if (instance.getMultivaluedReferences().get(stringKeyId).size() <= key.position()) {
            return Optional.empty();
        }

        return Optional.of(IdConverters.withHexString()
                .revert(instance.getMultivaluedReferences().get(stringKeyId).get(key.position())));
    }

    @Nonnull
    @Override
    public Stream<Id> allReferencesOf(SingleFeatureBean key) {
        checkNotNull(key, "key");

        StoredInstance instance = find(
                        eq("_id", key.owner().toHexString()))
                .projection(include("multivaluedReferences." + key.id()))
                .first();

        if (instance == null || instance.getMultivaluedReferences().size() == 0)
            return Stream.empty();

        List<String> refs = instance.getMultivaluedReferences().get(String.valueOf(key.id()));

        return MoreIterables.stream(refs).
                map(v -> IdConverters.withHexString().revert(v));
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(ManyFeatureBean key, Id reference) {
        checkNotNull(key, "key");
        checkNotNull(reference, "reference");

        String hexId = key.owner().toHexString();
        String stringKeyId = String.valueOf(key.id());

        StoredInstance instance = find(eq("_id", hexId))
                .projection(include("multivaluedReferences")).first();

        addReference(key, reference);

        if (instance == null) {
            return Optional.empty();
        }

        if (instance.getMultivaluedReferences().containsKey(stringKeyId) &&
                instance.getMultivaluedReferences().get(stringKeyId).size() > key.position()) {
            return Optional.of(IdConverters.withHexString().revert(instance.getMultivaluedReferences().get(stringKeyId).get(key.position())));
        }

        throw new NoSuchElementException();
    }

    @Override
    public void addReference(ManyFeatureBean key, Id reference) {
        checkNotNull(key, "key");
        checkNotNull(reference, "reference");

        String hexId = key.owner().toHexString();
        String stringKeyId = String.valueOf(key.id());

        StoredInstance instance = find(eq("_id", hexId))
                .projection(include("multivaluedReferences")).first();

        //TODO vérifier si franchement, ya pas mieux (update la liste directement dans mongo)
        List<String> multivaluedReference = instance.getMultivaluedReferences().get(stringKeyId);
        if (multivaluedReference == null) {
            multivaluedReference = new ArrayList<>();
        }

        if (key.position() > multivaluedReference.size())
            throw new IndexOutOfBoundsException();
        if (key.position() < multivaluedReference.size()) {
            multivaluedReference.set(key.position(), reference.toHexString());
        } else {
            multivaluedReference.add(key.position(), reference.toHexString());
        }

        if (instance != null) {
            updateOne(
                    eq("_id", hexId),
                    set("multivaluedReferences." + stringKeyId, multivaluedReference));
        }
    }

    @Override
    public void addAllReferences(ManyFeatureBean key, List<Id> collection) {
        checkNotNull(key, "key");
        checkNotNull(collection, "collection");
        checkNotContainsNull(collection);

        if (collection.isEmpty()) {
            return;
        }

        String hexId = key.owner().toHexString();
        String stringKeyId = String.valueOf(key.id());

        StoredInstance instance = find(eq("_id", hexId))
                .projection(include("multivaluedReferences")).first();

        if (instance == null) {
            return;
        }

        //TODO vérifier si franchement, ya pas mieux (update la liste directement dans mongo)
        List<String> multivaluedReference = instance.getMultivaluedReferences().get(stringKeyId);

        if (multivaluedReference == null) {
            multivaluedReference = new ArrayList<>();
        }

        if (key.position() > multivaluedReference.size())
            throw new IndexOutOfBoundsException();

        List<String> toAdd = new ArrayList<String>();
        for (Id id : collection) {
            toAdd.add(id.toHexString());
        }

        if (key.position() + toAdd.size() > multivaluedReference.size()) {
            multivaluedReference.addAll(Collections.nCopies(key.position() + toAdd.size() - multivaluedReference.size(), null));
        }

        int i = key.position();
        for (String a : toAdd) {
            multivaluedReference.set(i, a);
            i++;
        }

        updateOne(
                eq("_id", hexId),
                set("multivaluedReferences." + stringKeyId, multivaluedReference));
    }

    @Nonnull
    @Override
    public Optional<Id> removeReference(ManyFeatureBean key) {
        checkNotNull(key, "key");

        String hexId = key.owner().toHexString();
        String stringKeyId = String.valueOf(key.id());

        StoredInstance instance = find(eq("_id", hexId))
                .projection(include("multivaluedReferences")).first();

        if (instance == null || instance.getMultivaluedReferences().get(stringKeyId) == null) {
            return Optional.empty();
        }

        String item = instance.getMultivaluedReferences().get(stringKeyId).get(key.position());
        Optional<Id> res = Optional.of(IdConverters.withHexString().revert(item));

        updateOne(
                eq("_id", hexId),
                pull("multivaluedReferences." + stringKeyId, item));

        return res;
    }

    @Override
    public void removeAllReferences(SingleFeatureBean key) {
        checkNotNull(key, "key");

        String hexId = key.owner().toHexString();

        updateOne(
                eq("_id", hexId),
                combine(unset("singlevaluedReferences"), unset("multivaluedReferences")));
    }

    @Nonnull
    @Override
    public Optional<Integer> sizeOfReference(SingleFeatureBean key) {
        checkNotNull(key, "key");

        String hexId = key.owner().toHexString();

        StoredInstance instance = find(eq("_id", hexId))
                .projection(include("singlevaluedReferences")).first();

        if (instance == null || instance.getSinglevaluedReferences() == null || instance.getSinglevaluedReferences().isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(instance.getSinglevaluedReferences().size());
        }
    }

    //endregion
}
