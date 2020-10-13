package org.atlanmod.neoemf.data.redis;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.bean.ManyFeatureBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.atlanmod.commons.Throwables;

import static org.atlanmod.commons.Preconditions.checkNotContainsNull;
import static org.atlanmod.commons.Preconditions.checkNotNull;

/**
 * The default {@link RedisBackend} mapping.
 *
 * @see RedisBackendFactory
 */
@ParametersAreNonnullByDefault
class DefaultRedisBackend extends AbstractRedisBackend {

    /**
     * Constructs a new {@code DefaultRedisBackend}.
     *
     * @see RedisBackendFactory
     */
    protected DefaultRedisBackend() {
        super();

        // TODO Implement this constructor
    }

    //region Single-valued attributes

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(SingleFeatureBean feature) {
        checkNotNull(feature, "feature");

        // TODO Implement this method
        throw Throwables.notImplementedYet("valueOf");
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(SingleFeatureBean feature, V value) {
        checkNotNull(feature, "feature");
        checkNotNull(value, "value");

        // TODO Implement this method
        throw Throwables.notImplementedYet("valueFor");
    }

    @Override
    public void removeValue(SingleFeatureBean feature) {
        checkNotNull(feature, "feature");

        // TODO Implement this method
        throw Throwables.notImplementedYet("removeValue");
    }

    //endregion

    //region Single-valued references

    @Nonnull
    @Override
    public Optional<Id> referenceOf(SingleFeatureBean feature) {
        checkNotNull(feature, "feature");

        // TODO Implement this method
        throw Throwables.notImplementedYet("referenceOf");
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(SingleFeatureBean feature, Id reference) {
        checkNotNull(feature, "feature");
        checkNotNull(reference, "reference");

        // TODO Implement this method
        throw Throwables.notImplementedYet("referenceFor");
    }

    @Override
    public void removeReference(SingleFeatureBean feature) {
        checkNotNull(feature, "feature");

        // TODO Implement this method
        throw Throwables.notImplementedYet("removeReference");
    }

    //endregion

    //region Multi-valued attributes

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(ManyFeatureBean feature) {
        checkNotNull(feature, "feature");

        // TODO Implement this method
        throw Throwables.notImplementedYet("valueOf");
    }

    @Nonnull
    @Override
    public <V> Stream<V> allValuesOf(SingleFeatureBean feature) {
        checkNotNull(feature, "feature");

        // TODO Implement this method
        throw Throwables.notImplementedYet("allValuesOf");
    }

    @Nonnull
    public <V> Optional<V> valueFor(ManyFeatureBean feature, V value) {
        checkNotNull(feature, "feature");
        checkNotNull(value, "value");

        // TODO Implement this method
        throw Throwables.notImplementedYet("valueFor");
    }

    @Override
    public <V> void addValue(ManyFeatureBean feature, V value) {
        checkNotNull(feature, "feature");
        checkNotNull(value, "value");

        // TODO Implement this method
        throw Throwables.notImplementedYet("addValue");
    }

    @Override
    public <V> void addAllValues(ManyFeatureBean feature, List<? extends V> collection) {
        checkNotNull(feature, "feature");
        checkNotNull(collection, "collection");
        checkNotContainsNull(collection);

        if (collection.isEmpty()) {
            return;
        }

        // TODO Implement this method
        throw Throwables.notImplementedYet("addAllValues");
    }

    @Nonnull
    @Override
    public <V> Optional<V> removeValue(ManyFeatureBean feature) {
        checkNotNull(feature, "feature");

        // TODO Implement this method
        throw Throwables.notImplementedYet("removeValue");
    }

    @Override
    public void removeAllValues(SingleFeatureBean feature) {
        checkNotNull(feature, "feature");

        // TODO Implement this method
        throw Throwables.notImplementedYet("removeAllValues");
    }

    @Nonnull
    @Nonnegative
    @Override
    public Optional<Integer> sizeOfValue(SingleFeatureBean feature) {
        checkNotNull(feature, "feature");

        // TODO Implement this method
        throw Throwables.notImplementedYet("sizeOfValue");
    }

    //endregion

    //region Multi-valued references

    @Nonnull
    @Override
    public Optional<Id> referenceOf(ManyFeatureBean feature) {
        checkNotNull(feature, "feature");

        // TODO Implement this method
        throw Throwables.notImplementedYet("referenceOf");
    }

    @Nonnull
    @Override
    public Stream<Id> allReferencesOf(SingleFeatureBean feature) {
        checkNotNull(feature, "feature");

        // TODO Implement this method
        throw Throwables.notImplementedYet("allReferencesOf");
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(ManyFeatureBean feature, Id reference) {
        checkNotNull(feature, "feature");
        checkNotNull(reference, "reference");

        // TODO Implement this method
        throw Throwables.notImplementedYet("referenceFor");
    }

    @Override
    public void addReference(ManyFeatureBean feature, Id reference) {
        checkNotNull(feature, "feature");
        checkNotNull(reference, "reference");

        // TODO Implement this method
        throw Throwables.notImplementedYet("addReference");
    }

    @Override
    public void addAllReferences(ManyFeatureBean feature, List<Id> collection) {
        checkNotNull(feature, "feature");
        checkNotNull(collection, "collection");
        checkNotContainsNull(collection);

        if (collection.isEmpty()) {
            return;
        }

        // TODO Implement this method
        throw Throwables.notImplementedYet("addAllReferences");
    }

    @Nonnull
    @Override
    public Optional<Id> removeReference(ManyFeatureBean feature) {
        checkNotNull(feature, "feature");

        // TODO Implement this method
        throw Throwables.notImplementedYet("removeReference");
    }

    @Override
    public void removeAllReferences(SingleFeatureBean feature) {
        checkNotNull(feature, "feature");

        // TODO Implement this method
        throw Throwables.notImplementedYet("removeAllReferences");
    }

    @Nonnull
    @Override
    public Optional<Integer> sizeOfReference(SingleFeatureBean feature) {
        checkNotNull(feature, "feature");

        // TODO Implement this method
        throw Throwables.notImplementedYet("sizeOfReference");
    }

    //endregion
}
