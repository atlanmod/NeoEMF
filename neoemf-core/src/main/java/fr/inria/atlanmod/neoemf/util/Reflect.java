package fr.inria.atlanmod.neoemf.util;

import fr.inria.atlanmod.common.log.Log;
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.BackendFactoryBinding;
import fr.inria.atlanmod.neoemf.data.BackendFactoryRegistry;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
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
    public static void addUrls(URL... urls) {
        checkNotNull(urls);

        Collections.addAll(URLS, urls);
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
     * Retrieves all classes annotated with the specified {@code annotation}, which are also assignable from the given
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
        return newReflections()
                .getTypesAnnotatedWith(annotation).stream()
                .filter(instance::isAssignableFrom)
                .map(c -> (Class<? extends T>) c)
                .collect(Collectors.toSet());
    }

    /**
     * Gets or creates a instance of the given {@code cls} by using the static method named {@code name}.
     *
     * @param cls  the class to look for
     * @param name the name of the method to use
     * @param <T>  the type of the instance
     *
     * @return the single instance if the {@code cls} is a singleton, or a new instance
     *
     * @throws ReflectionException if an error occurs during the instantiation
     */
    @Nonnull
    @SuppressWarnings("unchecked")
    public static <T> T staticNewInstance(Class<T> cls, String name) {
        try {
            return (T) cls.getMethod(name).invoke(null);
        }
        catch (NoSuchMethodException e) {
            throw new ReflectionException(
                    String.format("%s must have a \"%s\" static method", cls.getName(), name));
        }
        catch (IllegalAccessException | InvocationTargetException e) {
            throw new ReflectionException(e);
        }
    }

    /**
     * Retrieves the value of a static field named {@code name} from the specified {@code cls}.
     *
     * @param cls  the class where to look for the field
     * @param name the name of the field to look for
     * @param <T>  the type of the value
     *
     * @return the value of the field
     *
     * @throws ReflectionException if an error occurs during the instantiation
     */
    @Nonnull
    @SuppressWarnings("unchecked")
    public static <T> T staticFieldValue(Class<?> cls, String name) {
        try {
            return  (T) cls.getField(name).get(cls);
        }
        catch (NoSuchFieldException e) {
            Log.error("{0} must have a \"{1}\" static field", cls.getName(), name);
            throw new ReflectionException(e);
        }
        catch (IllegalAccessException e) {
            throw new ReflectionException(e);
        }
    }

    /**
     * Retrieves the sub-class of the {@code expectedClass} that is associated to a
     * {@link fr.inria.atlanmod.neoemf.data.BackendFactory} wearing the given {@code name}.
     * <p>
     * The {@code expectedClass} <b>must</b> be annotated with {@link BackendFactoryBinding}.
     *
     * @param expectedClass the super-class of the classes to look for
     * @param name          the name of the factory
     * @param <T>           the type of the class
     *
     * @return the class associated to the {@code name}
     *
     * @throws IllegalArgumentException if no instance of {@code expectedClass} is found for the given {@code name}
     * @throws ReflectionException      if an error occurs during the instantiation
     *
     * @see BackendFactoryBinding
     */
    @Nonnull
    public static <T> Class<? extends T> forName(Class<? extends T> expectedClass, String name) {
        for (Class<? extends T> cls : typesAnnotatedWith(BackendFactoryBinding.class, expectedClass)) {
            Class<? extends BackendFactory> factoryCls = cls.getAnnotation(BackendFactoryBinding.class).value();

            if (Objects.equals(name, staticFieldValue(factoryCls, "NAME"))) {
                return cls;
            }
        }

        throw new IllegalArgumentException(String.format("No %s has been found for name \"%s\"", expectedClass.getSimpleName(), name));
    }

    /**
     * Retrieves the sub-class of the {@code expectedClass} that is associated to a
     * {@link UriBuilder} which use the given {@code scheme}.
     * <p>
     * The {@code expectedClass} <b>must</b> be annotated with {@link BackendFactoryBinding}.
     *
     * @param expectedClass the super-class of the classes to look for
     * @param scheme        the scheme of the builder
     * @param <T>           the type of the class
     *
     * @return the class associated to the {@code scheme}
     *
     * @throws IllegalArgumentException if no instance of {@code expectedClass} is found for the given {@code scheme}
     * @throws ReflectionException      if an error occurs during the instantiation
     *
     * @see BackendFactoryBinding
     */
    @Nonnull
    public static <T> Class<? extends T> forScheme(Class<? extends T> expectedClass, String scheme) {
        // Look for the expected BackendFactory
        Class<? extends BackendFactory> factoryCls = BackendFactoryRegistry.getInstance().getFactoryProvider(scheme).getClass();

        for (Class<? extends T> cls : Reflect.typesAnnotatedWith(BackendFactoryBinding.class, expectedClass)) {
            if (cls.getAnnotation(BackendFactoryBinding.class).value() == factoryCls) {
                return cls;
            }
        }

        throw new IllegalArgumentException(String.format("No %s has been found for scheme \"%s\"", expectedClass.getSimpleName(), scheme));
    }

    /**
     * Creates a new {@link Reflections}.
     *
     * @return a new reflections
     */
    @Nonnull
    private static Reflections newReflections() {
        return new Reflections(newConfiguration());
    }

    /**
     * Creates a new reflection configuration.
     *
     * @return a new configuration
     */
    @Nonnull
    private static ConfigurationBuilder newConfiguration() {
        return new ConfigurationBuilder()
                .addUrls(URLS)
                .useParallelExecutor()
                .setScanners(new SubTypesScanner(), new TypeAnnotationsScanner());
    }
}
