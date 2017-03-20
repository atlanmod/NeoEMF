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
import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

/**
 * A test-case that checks the specific behavior of {@link MoreIterables}.
 */
public class MoreIterablesTest extends AbstractTest {

    @Test
    public void testConstructor() throws Exception {
        Constructor<?> constructor = MoreIterables.class.getDeclaredConstructor();
        assertThat(Modifier.isPrivate(constructor.getModifiers())).isTrue();

        constructor.setAccessible(true);

        Throwable thrown = catchThrowable(constructor::newInstance);
        assertThat(thrown).isInstanceOf(InvocationTargetException.class);
        assertThat(thrown.getCause()).isExactlyInstanceOf(IllegalStateException.class).hasMessage("This class should not be instantiated");
    }

    @Test
    public void testIsEmptyWithIterator() {
        Iterable<Integer> iterable0 = () -> Collections.<Integer>emptyList().iterator();
        assertThat(MoreIterables.isEmpty(iterable0)).isTrue();

        Iterable<Integer> iterable1 = () -> Collections.singletonList(0).iterator();
        assertThat(MoreIterables.isEmpty(iterable1)).isFalse();

        Iterable<Integer> iterable2 = () -> Arrays.asList(0, 1).iterator();
        assertThat(MoreIterables.isEmpty(iterable2)).isFalse();
    }

    @Test
    public void testIsNotEmptyWithIterator() {
        Iterable<Integer> iterable0 = () -> Collections.<Integer>emptyList().iterator();
        assertThat(MoreIterables.isNotEmpty(iterable0)).isFalse();

        Iterable<Integer> iterable1 = () -> Collections.singletonList(0).iterator();
        assertThat(MoreIterables.isNotEmpty(iterable1)).isTrue();

        Iterable<Integer> iterable2 = () -> Arrays.asList(0, 1).iterator();
        assertThat(MoreIterables.isNotEmpty(iterable2)).isTrue();
    }

    @Test
    public void testIsEmptyWithList() {
        Iterable<Integer> iterable0 = Collections.emptyList();
        assertThat(MoreIterables.isEmpty(iterable0)).isTrue();

        Iterable<Integer> iterable1 = Collections.singletonList(0);
        assertThat(MoreIterables.isEmpty(iterable1)).isFalse();

        Iterable<Integer> iterable2 = Arrays.asList(0, 1);
        assertThat(MoreIterables.isEmpty(iterable2)).isFalse();
    }

    @Test
    public void testIsNotEmptyWithList() {
        Iterable<Integer> iterable0 = Collections.emptyList();
        assertThat(MoreIterables.isNotEmpty(iterable0)).isFalse();

        Iterable<Integer> iterable1 = Collections.singletonList(0);
        assertThat(MoreIterables.isNotEmpty(iterable1)).isTrue();

        Iterable<Integer> iterable2 = Arrays.asList(0, 1);
        assertThat(MoreIterables.isNotEmpty(iterable2)).isTrue();
    }
}