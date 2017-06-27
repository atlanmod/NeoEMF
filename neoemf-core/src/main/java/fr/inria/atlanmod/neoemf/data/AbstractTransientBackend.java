package fr.inria.atlanmod.neoemf.data;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.mapper.ManyReferenceAsManyValue;
import fr.inria.atlanmod.neoemf.data.mapper.ManyValueWithArrays;
import fr.inria.atlanmod.neoemf.data.mapper.ReferenceAsValue;
import fr.inria.atlanmod.neoemf.data.structure.ClassDescriptor;
import fr.inria.atlanmod.neoemf.data.structure.SingleFeatureKey;

import java.util.Map;
import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.common.Preconditions.checkNotNull;

/**
 * An abstract {@link TransientBackend} that provides the default behavior of containers and metaclasses management.
 *
 * @param <K> the type of keys to use to identify features
 */
@ParametersAreNonnullByDefault
public abstract class AbstractTransientBackend<K> extends AbstractBackend implements TransientBackend, ReferenceAsValue, ManyValueWithArrays, ManyReferenceAsManyValue {

    /**
     * Casts the {@code value} as expected.
     *
     * @param value the value to be cast
     * @param <V>   the expected type of the value
     *
     * @return the {@code value} after casting, or {@code null} if the {@code value} is {@code null}
     *
     * @throws ClassCastException if the {@code value} is not {@code null} and is not assignable to the type {@code V}
     */
    @Nullable
    @SuppressWarnings("unchecked")
    protected static <V> V cast(@Nullable Object value) {
        return (V) value;
    }

    /**
     * Returns the map that holds all containers.
     *
     * @return a mutable map
     */
    @Nonnull
    protected abstract Map<Id, SingleFeatureKey> allContainers();

    /**
     * Returns the map that holds all instances.
     *
     * @return a mutable map
     */
    @Nonnull
    protected abstract Map<Id, ClassDescriptor> allInstances();

    /**
     * Returns the map that holds all features.
     *
     * @return a mutable map
     */
    @Nonnull
    protected abstract Map<K, Object> allFeatures();

    /**
     * Transforms the specified {@code key} to be compatible with the type {@link K} used by this backend.
     *
     * @param key the key to transform
     *
     * @return the transformed key
     *
     * @see #allFeatures()
     * @see #valueOf(SingleFeatureKey)
     * @see #valueFor(SingleFeatureKey, Object)
     * @see #unsetValue(SingleFeatureKey)
     */
    @Nonnull
    protected abstract K transform(SingleFeatureKey key);

    @Nonnull
    @Override
    public Optional<SingleFeatureKey> containerOf(Id id) {
        checkNotNull(id);

        return Optional.ofNullable(allContainers().get(id));
    }

    @Override
    public void containerFor(Id id, SingleFeatureKey container) {
        checkNotNull(id);
        checkNotNull(container);

        allContainers().put(id, container);
    }

    @Override
    public void unsetContainer(Id id) {
        checkNotNull(id);

        allContainers().remove(id);
    }

    @Nonnull
    @Override
    public Optional<ClassDescriptor> metaclassOf(Id id) {
        checkNotNull(id);

        return Optional.ofNullable(allInstances().get(id));
    }

    @Override
    public void metaclassFor(Id id, ClassDescriptor metaclass) {
        checkNotNull(id);
        checkNotNull(metaclass);

        allInstances().put(id, metaclass);
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(SingleFeatureKey key) {
        K k = transform(key);

        return Optional.ofNullable(cast(allFeatures().get(k)));
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(SingleFeatureKey key, V value) {
        K k = transform(key);
        checkNotNull(value);

        return Optional.ofNullable(cast(allFeatures().put(k, value)));
    }

    @Override
    public <V> void unsetValue(SingleFeatureKey key) {
        K k = transform(key);

        allFeatures().remove(k);
    }
}
