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

import fr.inria.atlanmod.neoemf.AbstractTest;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

/**
 * A test-case that checks the behavior of {@link MoreArrays}.
 */
public class MoreArraysTest extends AbstractTest {

    @Test
    public void testConstructor() throws Exception {
        Constructor<?> constructor = MoreArrays.class.getDeclaredConstructor();
        assertThat(Modifier.isPrivate(constructor.getModifiers())).isTrue();

        constructor.setAccessible(true);

        Throwable thrown = catchThrowable(constructor::newInstance);
        assertThat(thrown).isInstanceOf(InvocationTargetException.class);
        assertThat(thrown.getCause()).isExactlyInstanceOf(IllegalStateException.class).hasMessage("This class should not be instantiated");
    }

    @Test
    public void testNewArray() {
        Object[] array0 = MoreArrays.newArray(Object.class, 1);
        assertThat(array0).isExactlyInstanceOf(Object[].class);
        assertThat(array0).hasSize(1);

        String[] array1 = MoreArrays.newArray(String.class, 2);
        assertThat(array1).isExactlyInstanceOf(String[].class);
        assertThat(array1).hasSize(2);
    }

    @Test
    public void testInvalidNewArray() {
        //noinspection ConstantConditions
        Throwable thrown0 = catchThrowable(() -> MoreArrays.newArray(null, 0));
        assertThat(thrown0).isExactlyInstanceOf(NullPointerException.class);

        //noinspection ConstantConditions
        Throwable thrown1 = catchThrowable(() -> MoreArrays.newArray(Object.class, -1));
        assertThat(thrown1).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testResize() {
        Integer[] array0 = new Integer[]{0, 1, 2, 3};
        assertThat(array0).hasSize(4);

        Integer[] array1 = MoreArrays.resize(array0, 5);
        assertThat(array0).hasSize(4);
        assertThat(array1).hasSize(5);

        assertThat(array1).containsExactly(0, 1, 2, 3, null);

        // newLength < length
        Throwable thrown = catchThrowable(() -> MoreArrays.resize(array1, 2));
        assertThat(thrown).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void testAppend() {
        Integer[] array0 = new Integer[0];
        assertThat(array0).hasSize(0);

        // Insert first
        Integer[] array1 = MoreArrays.append(array0, 0);
        assertThat(array1).hasSize(1);
        assertThat(array1[0]).isEqualTo(0);

        // First array must not change
        assertThat(array0).hasSize(0);

        // Insert before
        Integer[] array2 = MoreArrays.append(array1, 1);
        assertThat(array2).hasSize(2);
        assertThat(array2[0]).isEqualTo(0);
        assertThat(array2[1]).isEqualTo(1);
    }

    @Test
    public void testAdd() {
        Integer[] array0 = new Integer[0];
        assertThat(array0).hasSize(0);

        // Insert first
        Integer[] array1 = MoreArrays.add(array0, 0, 0);
        assertThat(array1).hasSize(1);
        assertThat(array1).containsExactly(0);

        // First array must not change
        assertThat(array0).hasSize(0);

        // Insert before
        Integer[] array2 = MoreArrays.add(array1, 0, 1);
        assertThat(array2).hasSize(2);
        assertThat(array2).containsExactly(1, 0);

        // Insert after
        Integer[] array3 = MoreArrays.add(array2, 2, 2);
        assertThat(array3).hasSize(3);
        assertThat(array3).containsExactly(1, 0, 2);

        // index > size
        Throwable thrown = catchThrowable(() -> MoreArrays.add(array3, 10, 10));
        assertThat(thrown).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    public void testRemove() {
        Integer[] array0 = new Integer[]{0, 1, 2, 3, 4};
        assertThat(array0).hasSize(5);

        // Remove first
        Integer[] array1 = MoreArrays.remove(array0, 0);
        assertThat(array1).hasSize(4);
        assertThat(array1).containsExactly(1, 2, 3, 4);

        // First array must not change
        assertThat(array0).hasSize(5);

        // Remove middle
        Integer[] array2 = MoreArrays.remove(array1, 2);
        assertThat(array2).hasSize(3);
        assertThat(array2).containsExactly(1, 2, 4);

        // Remove last
        Integer[] array3 = MoreArrays.remove(array2, 2);
        assertThat(array3).hasSize(2);
        assertThat(array3).containsExactly(1, 2);

        // index > size
        Throwable thrown = catchThrowable(() -> MoreArrays.remove(array3, 10));
        assertThat(thrown).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    public void testContains() {
        Integer[] array0 = new Integer[]{0, 1, 2, 3, 4};

        assertThat(MoreArrays.contains(array0, 0)).isTrue();
        assertThat(MoreArrays.contains(array0, 2)).isTrue();
        assertThat(MoreArrays.contains(array0, 4)).isTrue();

        assertThat(MoreArrays.contains(array0, 10)).isFalse();
    }

    @Test
    public void testIndexOf() {
        Integer[] array0 = new Integer[]{0, 1, 2, 2, 0, 1};

        assertThat(MoreArrays.indexOf(array0, 0)).isEqualTo(0);
        assertThat(MoreArrays.indexOf(array0, 1)).isEqualTo(1);
        assertThat(MoreArrays.indexOf(array0, 2)).isEqualTo(2);

        assertThat(MoreArrays.indexOf(array0, 10)).isEqualTo(MoreArrays.NO_INDEX);
    }

    @Test
    public void testLastIndexOf() {
        Integer[] array0 = new Integer[]{0, 1, 2, 2, 0, 1};

        assertThat(MoreArrays.lastIndexOf(array0, 0)).isEqualTo(4);
        assertThat(MoreArrays.lastIndexOf(array0, 1)).isEqualTo(5);
        assertThat(MoreArrays.lastIndexOf(array0, 2)).isEqualTo(3);

        assertThat(MoreArrays.lastIndexOf(array0, 10)).isEqualTo(MoreArrays.NO_INDEX);
    }
}