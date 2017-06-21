package fr.inria.atlanmod.neoemf.util;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

import java.util.Arrays;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.common.Preconditions.checkNotNull;
import static java.util.Objects.nonNull;

/**
 * Static utility methods related to {@link EObject}s.
 */
@ParametersAreNonnullByDefault
public final class EObjects {

    /**
     * This class should not be instantiated.
     *
     * @throws IllegalStateException every time
     */
    private EObjects() {
        throw new IllegalStateException("This class should not be instantiated");
    }

    /**
     * Checks whether the {@code feature} represents an attribute.
     *
     * @param feature the feature to test
     *
     * @return {@code true} if the {@code feature} represents an attribute
     */
    public static boolean isAttribute(EStructuralFeature feature) {
        return are(EAttribute.class, feature);
    }

    /**
     * Checks whether the {@code feature} represents a reference.
     *
     * @param feature the feature to test
     *
     * @return {@code true} if the {@code feature} represents an reference
     */
    public static boolean isReference(EStructuralFeature feature) {
        return are(EReference.class, feature);
    }

    /**
     * Casts the {@code feature} as an attribute.
     *
     * @param feature the feature to cast
     *
     * @return the attribute
     *
     * @throws NullPointerException if the {@code feature} is {@code null}
     * @throws ClassCastException   if the {@code feature} is not an attribute
     */
    @Nonnull
    public static EAttribute asAttribute(EStructuralFeature feature) {
        return as(EAttribute.class, feature);
    }

    /**
     * Casts the {@code feature} as a reference.
     *
     * @param feature the feature to cast
     *
     * @return the reference
     *
     * @throws NullPointerException if the {@code feature} is {@code null}
     * @throws ClassCastException   if the {@code feature} is not a reference
     */
    @Nonnull
    public static EReference asReference(EStructuralFeature feature) {
        return as(EReference.class, feature);
    }

    /**
     * Determines if all of the specified {@code objects} are assignable to the {@code cls}.
     *
     * @param cls     the expected instance
     * @param objects the objects to check
     * @param <T>     the type of the {@link EObject}
     *
     * @return {@code true} if the specified {@code objects} are assignable to the {@code cls}
     *
     * @throws NullPointerException if any argument is {@code null}
     */
    private static <T extends EObject> boolean are(Class<T> cls, @Nullable Object... objects) {
        return nonNull(objects)
                && Arrays.stream(objects).allMatch(cls::isInstance);

    }

    /**
     * Casts the specified {@code object} to a {@code cls}.
     *
     * @param cls    the expected instance
     * @param object the object to cast
     * @param <T>    the type of the {@link EObject}
     *
     * @return the {@code object} after casting
     *
     * @throws NullPointerException if any argument is {@code null}
     * @throws ClassCastException   if the {@code object} is not an instance of {@code cls}
     */
    private static <T extends EObject> T as(Class<T> cls, Object object) {
        return cls.cast(checkNotNull(object));
    }
}
