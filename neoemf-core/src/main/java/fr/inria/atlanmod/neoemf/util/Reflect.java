package fr.inria.atlanmod.neoemf.util;

import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.BackendFactoryBinding;
import fr.inria.atlanmod.neoemf.data.BackendFactoryRegistry;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.common.Preconditions.checkNotNull;

/**
 * A static utility class for reflection.
 */
@ParametersAreNonnullByDefault
public final class Reflect {

    /**
     * A set that holds the {@link URL} of the classpath to explore.
     */
    @Nonnull
    private static final Set<URL> URLS = new HashSet<>();

    static {
        URLS.addAll(ClasspathHelper.forJavaClassPath());
        URLS.addAll(ClasspathHelper.forManifest());
    }

    /**
     * This class should not be instantiated.
     *
     * @throws IllegalStateException every time
     */
    private Reflect() {
        throw new IllegalStateException("This class should not be instantiated");
    }

    /**
     * Adds {@code urls} to be scanned for reflection.
     *
     * @param urls the {@link URL}s to add for scanning
     *
     * @throws NullPointerException if {@code urls} is {@code null}
     */
    public static void addUrls(Collection<URL> urls) {
        checkNotNull(urls);

        URLS.addAll(urls);
    }

    /**
     * Retrieves all types annotated with the specified {@code annotation}.
     *
     * @param annotation the expected annotation
     *
     * @return a set of annotated classes
     *
     * @see #typesAnnotatedWith(Class, Class)
     */
    @Nonnull
    public static Set<Class<?>> typesAnnotatedWith(Class<? extends Annotation> annotation) {
        return typesAnnotatedWith(annotation, Object.class);
    }

    /**
     * Retrieves all types annotated with the specified {@code annotation}, which are also assignable from the given
     * {@code instance}.
     *
     * @param annotation the expected annotation
     * @param instance   the instance of the expected classes
     * @param <T>        the type of the instances to look for
     *
     * @return a set of annotated classes
     */
    @Nonnull
    @SuppressWarnings("unchecked")
    public static <T> Set<Class<? extends T>> typesAnnotatedWith(Class<? extends Annotation> annotation, Class<? extends T> instance) {
        Set<Class<?>> types = newReflectionsForTypes().getTypesAnnotatedWith(annotation);

        // Filter by instances
        return types.stream()
                .filter(instance::isAssignableFrom)
                .map(c -> (Class<? extends T>) c)
                .collect(Collectors.toSet());
    }

    /**
     * Gets or creates a instance of the given {@code type} by using the static method named {@code name}.
     *
     * @param type the class to look for
     * @param name the name of the method to use
     * @param <T>  the type of the instance
     *
     * @return the single instance if the {@code type} is a singleton, or a new instance
     *
     * @throws ReflectionException if an error occurs during the instantiation
     */
    @Nonnull
    @SuppressWarnings("unchecked")
    public static <T> T newStaticInstance(Class<T> type, String name) {
        try {
            return (T) getStaticMethod(type, name).invoke(null);
        }
        catch (IllegalAccessException | InvocationTargetException e) {
            throw new ReflectionException(e);
        }
    }

    /**
     * Retrieves the value of a static field named {@code name} from the specified {@code type}.
     *
     * @param type the class where to look for the field
     * @param name the name of the field to look for
     * @param <T>  the type of the value
     *
     * @return the value of the field
     *
     * @throws ReflectionException if an error occurs during the instantiation
     */
    @Nonnull
    @SuppressWarnings("unchecked")
    public static <T> T getStaticFieldValue(Class<?> type, String name) {
        try {
            return (T) getStaticField(type, name).get(type);
        }
        catch (IllegalAccessException e) {
            throw new ReflectionException(e);
        }
    }

    /**
     * Retrieves the static field {@code name} from the specified {@code type}.
     *
     * @param type the class where to look for the method
     * @param name the name of the method to look for
     *
     * @return the static method
     *
     * @throws ReflectionException if the {@code type} has no static method {@code name}
     */
    @Nonnull
    public static Method getStaticMethod(Class<?> type, String name) {
        try {
            return type.getMethod(name);
        }
        catch (NoSuchMethodException e) {
            throw new ReflectionException(e);
        }
    }

    /**
     * Retrieves the static field {@code name} from the specified {@code type}.
     *
     * @param type the class where to look for the field
     * @param name the name of the field to look for
     *
     * @return the static field
     *
     * @throws ReflectionException if the {@code type} has no static field {@code name}
     */
    @Nonnull
    public static Field getStaticField(Class<?> type, String name) {
        try {
            return type.getField(name);
        }
        catch (NoSuchFieldException e) {
            throw new ReflectionException(e);
        }
    }

    /**
     * Retrieves the sub-class of the {@code expectedType} that is associated to a
     * {@link fr.inria.atlanmod.neoemf.data.BackendFactory} wearing the given {@code name}.
     * <p>
     * The {@code expectedType} <b>must</b> be annotated with {@link BackendFactoryBinding}.
     *
     * @param expectedType the super-class of the classes to look for
     * @param name         the name of the factory
     * @param <T>          the type of the class
     *
     * @return the class associated to the {@code name}
     *
     * @throws IllegalArgumentException if no instance of {@code expectedType} is found for the given {@code name}
     * @throws ReflectionException      if an error occurs during the instantiation
     * @see BackendFactoryBinding
     */
    @Nonnull
    public static <T> Class<? extends T> forName(Class<? extends T> expectedType, String name) {
        for (Class<? extends T> type : typesAnnotatedWith(BackendFactoryBinding.class, expectedType)) {
            Class<? extends BackendFactory> factoryType = type.getAnnotation(BackendFactoryBinding.class).value();

            if (Objects.equals(name, getStaticFieldValue(factoryType, "NAME"))) {
                return type;
            }
        }

        throw new IllegalArgumentException(String.format("No %s has been found for name \"%s\"", expectedType.getSimpleName(), name));
    }

    /**
     * Retrieves the sub-class of the {@code expectedType} that is associated to a
     * {@link UriBuilder} which use the given {@code scheme}.
     * <p>
     * The {@code expectedType} <b>must</b> be annotated with {@link BackendFactoryBinding}.
     *
     * @param expectedType the super-class of the classes to look for
     * @param scheme       the scheme of the builder
     * @param <T>          the type of the class
     *
     * @return the class associated to the {@code scheme}
     *
     * @throws IllegalArgumentException if no instance of {@code expectedType} is found for the given {@code scheme}
     * @throws ReflectionException      if an error occurs during the instantiation
     * @see BackendFactoryBinding
     */
    @Nonnull
    public static <T> Class<? extends T> forScheme(Class<? extends T> expectedType, String scheme) {
        // Look for the expected BackendFactory
        Class<? extends BackendFactory> factoryType = BackendFactoryRegistry.getInstance().getFactoryProvider(scheme).getClass();

        for (Class<? extends T> type : typesAnnotatedWith(BackendFactoryBinding.class, expectedType)) {
            if (type.getAnnotation(BackendFactoryBinding.class).value() == factoryType) {
                return type;
            }
        }

        throw new IllegalArgumentException(String.format("No %s has been found for scheme \"%s\"", expectedType.getSimpleName(), scheme));
    }

    /**
     * Creates a new {@link Reflections} pre-configured for scanning types.
     *
     * @return a new reflections
     */
    @Nonnull
    private static Reflections newReflectionsForTypes() {
        return new Reflections(new ConfigurationBuilder()
                .addUrls(URLS)
                .useParallelExecutor()
                .setScanners(new SubTypesScanner(), new TypeAnnotationsScanner()));
    }
}
