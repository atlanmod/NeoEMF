package fr.inria.atlanmod.neoemf.data;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.mapper.ManyValueWithArrays;
import fr.inria.atlanmod.neoemf.data.structure.ClassDescriptor;
import fr.inria.atlanmod.neoemf.data.structure.SingleFeatureKey;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.common.Preconditions.checkNotNull;
import static java.util.Objects.isNull;

/**
 * An abstract {@link TransientBackend} that provides the default behavior of containers and metaclasses management.
 *
 * @param <K> the type of keys to use to identify features
 */
@ParametersAreNonnullByDefault
public abstract class AbstractTransientBackend<K> extends AbstractBackend implements TransientBackend, ManyValueWithArrays {

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
        return Optional.ofNullable(allContainers().get(id));
    }

    @Override
    public void containerFor(Id id, SingleFeatureKey container) {
        allContainers().put(id, container);
    }

    @Override
    public void unsetContainer(Id id) {
        allContainers().remove(id);
    }

    @Nonnull
    @Override
    public Optional<ClassDescriptor> metaclassOf(Id id) {
        return Optional.ofNullable(allInstances().get(id));
    }

    @Override
    public void metaclassFor(Id id, ClassDescriptor metaclass) {
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

    /**
     * A {@link Map} that stores keys and values in two distinct {@link Map}s in order to limitate the memory
     * consumption. It does not support {@code null} keys or values.
     * <p>
     * This implementation is only effective if the values are referenced by several keys.
     *
     * @param <K> the type of keys maintained by this map
     * @param <V> the type of mapped values
     */
    @ParametersAreNonnullByDefault
    protected static final class ManyToOneMap<K, V> implements Map<K, V> {

        /**
         * The generator that provides unique identifier for each value.
         */
        @Nonnull
        private final AtomicInteger generator = new AtomicInteger();

        /**
         * A map that holds all keys.
         */
        @Nonnull
        private final Map<K, Integer> keys = new HashMap<>();

        /**
         * A map that holds all values.
         */
        @Nonnull
        private final Map<Integer, V> values = new HashMap<>();

        /**
         * Returns the key of the given {@code value} amoung all {@link #values}. If the {@code value} does not exist,
         * the key is created.
         *
         * @param value the value to look for
         *
         * @return the key
         */
        @Nonnegative
        private int keyOf(V value) {
            return values.entrySet()
                    .parallelStream()
                    .filter(e -> Objects.equals(e.getValue(), value))
                    .findAny()
                    .map(Map.Entry::getKey)
                    .orElseGet(generator::getAndIncrement);
        }

        @Nonnegative
        @Override
        public int size() {
            return keys.size();
        }

        @Override
        public boolean isEmpty() {
            return keys.isEmpty();
        }

        @Override
        public boolean containsKey(Object key) {
            checkNotNull(key); // Follow the default Map behavior

            return keys.containsKey(key);
        }

        @Override
        public boolean containsValue(Object value) {
            checkNotNull(value); // Follow the default Map behavior

            return values.containsValue(value);
        }

        @Nullable
        @Override
        public V get(Object key) {
            checkNotNull(key);

            return Optional.ofNullable(keys.get(key))
                    .map(values::get)
                    .orElse(null);
        }

        @Nullable
        @Override
        public V put(K key, V value) {
            checkNotNull(key);
            checkNotNull(value);

            V previousValue = get(key);

            int intermediateKey = keyOf(value);

            // Update the value if necessary
            values.putIfAbsent(intermediateKey, value);

            // Update the association in all cases
            keys.put(key, intermediateKey);

            return previousValue;
        }

        @Nullable
        @Override
        public V remove(Object key) {
            checkNotNull(key);

            Optional<V> previousValue = Optional.ofNullable(get(key));

            previousValue.ifPresent(v -> {
                keys.remove(key);

                int intermediateKey = keyOf(v);

                // Remove the value if it is no longer referenced
                if (!keys.containsValue(intermediateKey)) {
                    values.remove(intermediateKey);
                }
            });

            return previousValue.orElse(null);
        }

        @Override
        public void putAll(Map<? extends K, ? extends V> m) {
            checkNotNull(m);

            m.forEach(this::put);
        }

        @Override
        public void clear() {
            keys.clear();
            values.clear();
        }

        @Nonnull
        @Override
        public Set<K> keySet() {
            return keys.keySet();
        }

        @Nonnull
        @Override
        public Collection<V> values() {
            return values.values();
        }

        @Nonnull
        @Override
        public Set<Map.Entry<K, V>> entrySet() {
            return keys.entrySet()
                    .parallelStream()
                    .map(e -> new Entry(e.getKey(), e.getValue()))
                    .collect(Collectors.toSet());
        }

        /**
         * A map entry (key-value pair) that processes the value on-demand.
         */
        @ParametersAreNonnullByDefault
        private final class Entry implements Map.Entry<K, V> {

            /**
             * The key of this entry.
             */
            @Nonnull
            private final K key;

            /**
             * The identifier of the associated value.
             */
            @Nonnegative
            private final int valueId;

            /**
             * The value of this entry.
             * <p>
             * It is loaded on-demand, during the first call to {@link #getValue()} or {@link #setValue(Object)}.
             */
            @Nullable
            private transient V value;

            /**
             * Constructs a new {@code Entry} for the given {@code key}.
             *
             * @param key     the key of this entry
             * @param valueId the identifier of the associated value
             */
            private Entry(K key, int valueId) {
                this.key = key;
                this.valueId = valueId;
            }

            @Nonnull
            @Override
            public K getKey() {
                return key;
            }

            @Override
            public V getValue() {
                // Load the value on-demand
                if (isNull(value)) {
                    value = values.get(valueId);
                }

                return value;
            }

            @Override
            public V setValue(V value) {
                this.value = value;

                return put(key, value);
            }
        }
    }
}
