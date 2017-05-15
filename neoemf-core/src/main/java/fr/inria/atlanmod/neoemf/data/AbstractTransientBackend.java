package fr.inria.atlanmod.neoemf.data;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.mapper.ManyValueWithArrays;
import fr.inria.atlanmod.neoemf.data.structure.ClassDescriptor;
import fr.inria.atlanmod.neoemf.data.structure.ContainerDescriptor;

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

import static fr.inria.atlanmod.neoemf.util.Preconditions.checkNotNull;

/**
 * An abstract implementation of {@link TransientBackend} that provides the default behavior of {@link
 * ContainerDescriptor} and {@link ClassDescriptor} management.
 */
@ParametersAreNonnullByDefault
public abstract class AbstractTransientBackend extends AbstractBackend implements TransientBackend, ManyValueWithArrays {

    /**
     * Casts the {@code value} as expected.
     *
     * @param value the value to cast
     * @param <V>   the expected type of the value
     *
     * @return the casted value
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
    protected abstract Map<Id, ContainerDescriptor> allContainers();

    /**
     * Returns the map that holds all instances.
     *
     * @return a mutable map
     */
    @Nonnull
    protected abstract Map<Id, ClassDescriptor> allInstances();

    @Nonnull
    @Override
    public Optional<ContainerDescriptor> containerOf(Id id) {
        checkNotNull(id);

        return Optional.ofNullable(allContainers().get(id));
    }

    @Override
    public void containerFor(Id id, ContainerDescriptor container) {
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

    /**
     * A {@link Map} that stores keys and values in separate {@link HashMap}s in order to limitate the memory
     * consumption.
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
         * Returns the key of the given {@code value} amoung all {@link #values}.
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
            return keys.containsKey(key);
        }

        @Override
        public boolean containsValue(Object value) {
            return values.containsValue(value);
        }

        @Override
        public V get(Object key) {
            return Optional.ofNullable(keys.get(key))
                    .map(values::get)
                    .orElse(null);
        }

        @Override
        public V put(K key, V value) {
            V previousValue = get(key);

            int intermediateKey = keyOf(value);

            values.putIfAbsent(intermediateKey, value);
            keys.put(key, intermediateKey);

            return previousValue;
        }

        @Override
        public V remove(Object key) {
            Optional<V> previousValue = Optional.ofNullable(get(key));

            if (previousValue.isPresent()) {
                keys.remove(key);

                // Remove the value if it is no longer referenced
                int intermediateKey = keyOf(previousValue.get());

                if (!keys.containsValue(intermediateKey)) {
                    values.remove(intermediateKey);
                }
            }

            return previousValue.orElse(null);
        }

        @Override
        public void putAll(Map<? extends K, ? extends V> m) {
            m.forEach(this::put);
        }

        @Override
        public void clear() {
            keys.clear();
            values.clear();
        }

        @Override
        public Set<K> keySet() {
            return keys.keySet();
        }

        @Override
        public Collection<V> values() {
            return values.values();
        }

        @Override
        public Set<Entry<K, V>> entrySet() {
            return keys.entrySet()
                    .parallelStream()
                    .collect(Collectors.toMap(Entry::getKey, e -> values.get(e.getValue())))
                    .entrySet();
        }
    }
}
