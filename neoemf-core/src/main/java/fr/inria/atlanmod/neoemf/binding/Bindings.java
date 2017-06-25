package fr.inria.atlanmod.neoemf.binding;

import fr.inria.atlanmod.common.cache.Cache;
import fr.inria.atlanmod.common.cache.CacheBuilder;
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.BackendFactoryRegistry;
import fr.inria.atlanmod.neoemf.util.UriBuilder;

import org.eclipse.emf.common.util.URI;
import org.reflections.ReflectionUtils;
import org.reflections.Reflections;
import org.reflections.scanners.Scanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.common.Preconditions.checkNotNull;
import static java.util.Objects.nonNull;

/**
 * A static utility class for binding, across reflection.
 */
@ParametersAreNonnullByDefault
public final class Bindings {

    /**
     * A set that holds the {@link URL} of the classpath to explore.
     */
    @Nonnull
    private static final Set<URL> URLS = new HashSet<>();

    /**
     * A cache that holds the result of recent queries on types.
     */
    @Nonnull
    private static final Cache<Class<? extends Annotation>, Set<Class<?>>> BOUND_TYPES = CacheBuilder.builder()
            .softValues()
            .build(a -> newReflections(new TypeAnnotationsScanner(), new SubTypesScanner()).getTypesAnnotatedWith(a));

    static {
        // Add the default URLs for scanning
        addUrls(ClasspathHelper.forJavaClassPath());
        addUrls(ClasspathHelper.forManifest());
    }

    /**
     * This class should not be instantiated.
     *
     * @throws IllegalStateException every time
     */
    private Bindings() {
        throw new IllegalStateException("This class should not be instantiated");
    }

    /**
     * Adds {@code urls} to be scanned for binding.
     *
     * @param urls the {@link URL}s to add for scanning
     *
     * @throws NullPointerException if {@code urls} is {@code null}
     */
    public static void addUrls(Collection<URL> urls) {
        checkNotNull(urls);

        // TODO Filter URLs, and remove any that cannot be related to NeoEMF (Java, EMF,...)

        URLS.addAll(urls);

        // Refresh the cache: annotations stay the same, but the result may have been changed
        BOUND_TYPES.invalidateAll();
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
    public static <T> Set<Class<? extends T>> typesBoundWith(Class<? extends Annotation> annotation, Class<? extends T> instance) {
        return BOUND_TYPES.get(annotation).stream()
                .filter(instance::isAssignableFrom)
                .map(c -> (Class<? extends T>) c)
                .collect(Collectors.toSet());
    }

    /**
     * Gets or creates a instance of the given {@code type} by using the static method named {@code name}.
     * <p>
     * If the {@code type} is annotated by {@link Singleton} or by {@link Builder}, then the static method identified
     * by the value of the annotation is used to get the instance. Otherwise, the default constructor is used.
     *
     * @param type the class to look for
     * @param <T>  the type of the instance
     *
     * @return the single instance if the {@code type} is a singleton, or a new instance
     *
     * @throws BindingException if an error occurs during the instantiation
     */
    @Nonnull
    @SuppressWarnings("unchecked")
    public static <T> T newInstance(Class<T> type) {
        Optional<String> methodName;

        if (type.isAnnotationPresent(Singleton.class)) {
            methodName = Optional.of(type.getAnnotation(Singleton.class).value());
        }
        else if (type.isAnnotationPresent(Builder.class)) {
            methodName = Optional.of(type.getAnnotation(Builder.class).value());
        }
        else {
            methodName = Optional.empty();
        }

        try {
            return methodName.isPresent()
                    ? (T) type.getMethod(methodName.get()).invoke(null)
                    : type.newInstance();
        }
        catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            throw new BindingException(e);
        }
    }

    /**
     * Retrieves the {@link URI} scheme for the speficied {@code type}.
     * <p>
     * The {@code type} must be a sub-class of {@link BackendFactory}, or must be annotated with {@link FactoryBinding}.
     *
     * @param type the type of the factory
     *
     * @return the {@link URI} scheme
     *
     * @throws BindingException if no scheme is defined for this {@code type}
     */
    @Nonnull
    @SuppressWarnings("unchecked")
    public static String schemeOf(Class<?> type) {
        Class<? extends BackendFactory> factoryType = null;

        if (BackendFactory.class.isAssignableFrom(type)) {
            factoryType = (Class<? extends BackendFactory>) type;
        }
        else if (type.isAnnotationPresent(FactoryBinding.class)) {
            factoryType = type.getAnnotation(FactoryBinding.class).value();
        }

        if (nonNull(factoryType)) {
            return UriBuilder.PREFIX + nameOf(factoryType).toLowerCase();
        }

        throw new BindingException(
                String.format("%s is not annotated with %s: Unable to retrieve the associated scheme", type.getName(), FactoryBinding.class.getSimpleName()));
    }

    /**
     * Retrieves the name for the speficied {@code factoryType}.
     * <p>
     * The name of a {@link BackendFactory} must be annotated with {@link FactoryName}.
     *
     * @param factoryType the type of the factory
     *
     * @return the name
     *
     * @throws BindingException if no name is defined for this {@code factoryType}
     */
    @Nonnull
    @SuppressWarnings("unchecked")
    public static String nameOf(Class<? extends BackendFactory> factoryType) {
        Set<Field> boundFields = ReflectionUtils.getFields(factoryType,
                ReflectionUtils.withAnnotation(FactoryName.class),
                ReflectionUtils.withType(String.class),
                ReflectionUtils.withModifier(Modifier.PUBLIC));

        Optional<Field> field = boundFields.stream()
                .filter(f -> factoryType.isAssignableFrom(f.getDeclaringClass()))
                .findAny();

        if (field.isPresent()) {
            try {
                return String.class.cast(field.get().get(factoryType));
            }
            catch (ClassCastException | IllegalAccessException e) {
                throw new BindingException(e);
            }
        }

        throw new BindingException(
                String.format("Unable to find the name of the factory %s", factoryType.getName()));
    }

    /**
     * Retrieves the sub-class of the {@code expectedType} that is associated to a
     * {@link UriBuilder} which use the given {@code scheme}.
     * <p>
     * The {@code expectedType} <b>must</b> be annotated with {@link FactoryBinding}.
     *
     * @param expectedType the super-class of the classes to look for
     * @param scheme       the scheme of the builder
     * @param <T>          the type of the class
     *
     * @return a new instance of the {@code expectedType}
     *
     * @throws BindingException if no instance of {@code expectedType} is found for the given {@code scheme},
     *                          or if an error occurs during the instantiation
     */
    @Nonnull
    public static <T> T findByScheme(Class<? extends T> expectedType, String scheme) {
        Class<? extends BackendFactory> factoryType = BackendFactoryRegistry.getInstance().getFactoryProvider(scheme).getClass();

        for (Class<? extends T> type : typesBoundWith(FactoryBinding.class, expectedType)) {
            if (factoryType.isAssignableFrom(type.getAnnotation(FactoryBinding.class).value())) {
                return newInstance(type);
            }
        }

        throw new BindingException(
                String.format("Unable to find a %s instance for scheme \"%s\"", expectedType.getSimpleName(), scheme));
    }

    /**
     * Retrieves the sub-class of the {@code expectedType} that is associated to a
     * {@link fr.inria.atlanmod.neoemf.data.BackendFactory} wearing the given {@code name}.
     * <p>
     * The {@code expectedType} <b>must</b> be annotated with {@link FactoryBinding}.
     *
     * @param expectedType the super-class of the classes to look for
     * @param name         the name of the factory
     * @param <T>          the type of the class
     *
     * @return a new instance of the {@code expectedType}
     *
     * @throws BindingException if no instance of {@code expectedType} is found for the given {@code name},
     *                          or if an error occurs during the instantiation
     * @see FactoryBinding
     */
    @Nonnull
    public static <T> T findByName(Class<? extends T> expectedType, String name) {
        for (Class<? extends T> type : typesBoundWith(FactoryBinding.class, expectedType)) {
            Class<? extends BackendFactory> factoryType = type.getAnnotation(FactoryBinding.class).value();

            if (Objects.equals(name, nameOf(factoryType))) {
                return newInstance(type);
            }
        }

        throw new BindingException(
                String.format("Unable to find a %s instance for name \"%s\"", expectedType.getSimpleName(), name));
    }

    /**
     * Creates a new {@link Reflections} pre-configured for scanning types.
     *
     * @return a new reflections
     */
    @Nonnull
    private static Reflections newReflections(Scanner... scanners) {
        return new Reflections(new ConfigurationBuilder()
                .addUrls(URLS)
                .useParallelExecutor()
                .setScanners(scanners));
    }
}
