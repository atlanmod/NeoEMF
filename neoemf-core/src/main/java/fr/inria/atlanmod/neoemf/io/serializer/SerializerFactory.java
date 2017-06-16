package fr.inria.atlanmod.neoemf.io.serializer;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.structure.ClassDescriptor;
import fr.inria.atlanmod.neoemf.data.structure.ManyFeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.SingleFeatureKey;

import javax.annotation.Nonnull;

/**
 * A factory that creates {@link Serializer} instances.
 */
public interface SerializerFactory {

    /**
     * Gets the {@link Serializer} for any {@link Object}.
     *
     * @param <T> the type of (de)serialized objects
     *
     * @return a serializer
     */
    @Nonnull
    <T> Serializer<T> forAny();

    /**
     * Gets the {@link Serializer} for {@link Id}s.
     *
     * @return a serializer
     */
    @Nonnull
    Serializer<Id> forId();

    /**
     * Gets the {@link Serializer} for {@link ClassDescriptor}s.
     *
     * @return a serializer
     */
    @Nonnull
    Serializer<ClassDescriptor> forClass();

    /**
     * Gets the {@link Serializer} for {@link SingleFeatureKey}s.
     *
     * @return a serializer
     */
    @Nonnull
    Serializer<SingleFeatureKey> forSingleFeatureKey();

    /**
     * Gets the {@link Serializer} for {@link ManyFeatureKey}s.
     *
     * @return a serializer
     */
    @Nonnull
    Serializer<ManyFeatureKey> forManyFeatureKey();
}
