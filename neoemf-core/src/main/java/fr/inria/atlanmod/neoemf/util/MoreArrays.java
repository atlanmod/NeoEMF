/*
 * Copyright (c) 2013-2017 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.util;

import java.lang.reflect.Array;
import java.util.Objects;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.neoemf.util.Preconditions.checkArgument;
import static fr.inria.atlanmod.neoemf.util.Preconditions.checkElementIndex;
import static fr.inria.atlanmod.neoemf.util.Preconditions.checkNotNull;

/**
 * Static utility methods related to arrays.
 */
@ParametersAreNonnullByDefault
public final class MoreArrays {

    /**
     * The index value when an element is not found in a list or array: {@code -1}.
     */
    public static final int NO_INDEX = -1;

    /**
     * This class should not be instantiated.
     *
     * @throws IllegalStateException every time
     */
    private MoreArrays() {
        throw new IllegalStateException("This class should not be instantiated");
    }

    /**
     * Creates a new array with the specified component {@code type} and {@code length}.
     *
     * @param type   the class of the new array
     * @param length the length of the new array
     * @param <T>    the type of the new array
     *
     * @return a new array
     *
     * @throws NullPointerException     if the {@code type} is {@code null}
     * @throws IllegalArgumentException if the {@code length} is negative
     */
    @Nonnull
    @SuppressWarnings("unchecked")
    public static <T> T[] newArray(Class<?> type, @Nonnegative int length) {
        checkNotNull(type);
        checkArgument(length >= 0);

        return (type == Object.class)
                ? (T[]) new Object[length]
                : (T[]) Array.newInstance(type, length);
    }

    /**
     * Resizes the {@code array} with the given {@code length}.
     *
     * @param array  the array to resize
     * @param length the new length of the array
     * @param <T>    the type of the new array
     *
     * @return a new array containing the existing elements with the new size
     *
     * @throws NullPointerException     if the {@code type} is {@code null}
     * @throws IllegalArgumentException if the {@code length} is lower than {@code array.length}
     */
    @Nonnull
    public static <T> T[] resize(T[] array, @Nonnegative int length) {
        checkNotNull(array);
        checkArgument(length >= array.length);

        T[] newArray = newArray(array.getClass().getComponentType(), length);
        System.arraycopy(array, 0, newArray, 0, array.length);

        return newArray;
    }

    /**
     * Adds the specified {@code element} at the last position in the {@code array}.
     *
     * @param array   the array to add the element to
     * @param element the object to add
     * @param <T>     the component type of the array
     *
     * @return a new array containing the existing elements and the new element
     *
     * @throws NullPointerException if the {@code array} is {@code null}
     */
    @Nonnull
    public static <T> T[] append(T[] array, @Nullable T element) {
        return add(array, array.length, element);
    }

    /**
     * Adds the specified {@code element} at the specified {@code position} in the {@code array}.
     *
     * @param array   the array to add the element to
     * @param index   the position of the new object
     * @param element the object to add
     * @param <T>     the component type of the array
     *
     * @return a new array containing the existing elements and the new element
     *
     * @throws NullPointerException      if the {@code array} is {@code null}
     * @throws IndexOutOfBoundsException if the {@code index} is out of range (index &lt; 0 || index &gt; array.length)
     */
    @Nonnull
    public static <T> T[] add(T[] array, @Nonnegative int index, @Nullable T element) {
        checkNotNull(array);
        checkElementIndex(index, array.length + 1);

        T[] newArray = newArray(array.getClass().getComponentType(), array.length + 1);
        System.arraycopy(array, 0, newArray, 0, index);

        newArray[index] = element;

        if (index < array.length) {
            System.arraycopy(array, index, newArray, index + 1, array.length - index);
        }

        return newArray;
    }

    /**
     * Removes the element at the specified {@code index} from the specified {@code array}.
     *
     * @param array the array to remove the element from
     * @param index the position of the element to be removed
     * @param <T>   the component type of the array
     *
     * @return a new array containing the existing elements except the element at the specified position.
     *
     * @throws NullPointerException      if the {@code array} is {@code null}
     * @throws IndexOutOfBoundsException if the {@code index} is out of range (index &lt; 0 || index &gt; array.length)
     */
    @Nonnull
    public static <T> T[] remove(T[] array, @Nonnegative int index) {
        checkNotNull(array);
        checkElementIndex(index, array.length);

        T[] newArray = newArray(array.getClass().getComponentType(), array.length - 1);
        System.arraycopy(array, 0, newArray, 0, index);

        if (index < array.length - 1) {
            System.arraycopy(array, index + 1, newArray, index, array.length - index - 1);
        }

        return newArray;
    }

    /**
     * Checks if the {@code value} is in the given {@code array}.
     *
     * @param array the array to search through
     * @param value the object to find
     *
     * @return {@code true} if the array contains the object, {@code false} otherwise
     *
     * @throws NullPointerException if the {@code array} is {@code null}
     */
    public static <T> boolean contains(T[] array, @Nullable T value) {
        checkNotNull(array);

        return indexOf(array, value) != NO_INDEX;
    }

    /**
     * Finds the index of the given {@code value} in the {@code array}.
     *
     * @param array the array to search through for the object
     * @param value the value to find
     *
     * @return the index of the value within the array, {@code -1} if not found
     *
     * @throws NullPointerException if the {@code array} is {@code null}
     */
    public static <T> int indexOf(T[] array, @Nullable T value) {
        checkNotNull(array);

        for (int i = 0; i < array.length; i++) {
            if (Objects.equals(value, array[i])) {
                return i;
            }
        }

        return NO_INDEX;
    }

    /**
     * Finds the last index of the given object in the array.
     *
     * @param array the array to traverse for looking for the object
     * @param value the object to find, may be {@code null}
     *
     * @return the last index of the object within the array, {@code -1} if not found
     *
     * @throws NullPointerException if the {@code array} is {@code null}
     */
    public static <T> int lastIndexOf(T[] array, @Nullable T value) {
        checkNotNull(array);

        for (int i = array.length - 1; i >= 0; i--) {
            if (Objects.equals(value, array[i])) {
                return i;
            }
        }

        return NO_INDEX;
    }
}
