package fr.inria.atlanmod.commons.io.serializer;

import fr.inria.atlanmod.commons.annotation.Static;

import javax.annotation.Nonnull;

/**
 * A factory that creates {@link Serializer} instances.
 */
public class SerializerFactory {

    /**
     * The instance of {@link ObjectSerializer}.
     */
    private final Serializer<?> objectSerializer = new ObjectSerializer<>();

    /**
     * Constructs a new {@code SerializerFactory}.
     */
    protected SerializerFactory() {
    }

    /**
     * Returns the instance of this class.
     *
     * @return the instance of this class
     */
    @Nonnull
    public static SerializerFactory getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Gets the {@link Serializer} for any {@link Object}.
     *
     * @param <T> the type of (de)serialized objects
     *
     * @return a serializer
     */
    @Nonnull
    @SuppressWarnings("unchecked")
    public <T> Serializer<T> forAny() {
        return (Serializer<T>) objectSerializer;
    }

    /**
     * The initialization-on-demand holder of the singleton of this class.
     */
    @Static
    private static final class Holder {

        /**
         * The instance of the outer class.
         */
        private static final SerializerFactory INSTANCE = new SerializerFactory();
    }
}
