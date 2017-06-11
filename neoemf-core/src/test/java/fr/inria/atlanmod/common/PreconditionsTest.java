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

package fr.inria.atlanmod.common;

import fr.inria.atlanmod.neoemf.AbstractTest;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

/**
 * A test-case that checks the behavior of {@link Preconditions}.
 */
public class PreconditionsTest extends AbstractTest {

    @Test
    public void testConstructor() throws Exception {
        Constructor<?> constructor = Preconditions.class.getDeclaredConstructor();
        assertThat(Modifier.isPrivate(constructor.getModifiers())).isTrue();

        constructor.setAccessible(true);

        assertThat(catchThrowable(constructor::newInstance))
                .isInstanceOf(InvocationTargetException.class)
                .hasCauseExactlyInstanceOf(IllegalStateException.class);
    }

    @Test
    public void testCheckArgument() {
        assertThat(catchThrowable(() -> Preconditions.checkArgument(true)))
                .isNull();

        assertThat(catchThrowable(() -> Preconditions.checkArgument(false)))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasNoCause()
                .hasMessage(null);
    }

    @Test
    public void testCheckArgumentWithMessage() {
        assertThat(catchThrowable(() -> Preconditions.checkArgument(true, "Message0")))
                .isNull();

        assertThat(catchThrowable(() -> Preconditions.checkArgument(false, "Message0")))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasNoCause()
                .hasMessage("Message0");
    }

    @Test
    public void testCheckArgumentWithPattern() {
        assertThat(catchThrowable(() -> Preconditions.checkArgument(true, "Message%d", 0)))
                .isNull();

        assertThat(catchThrowable(() -> Preconditions.checkArgument(false, "Message%d", 0)))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasNoCause()
                .hasMessage("Message0");
    }

    @Test
    public void testCheckState() {
        assertThat(catchThrowable(() -> Preconditions.checkState(true)))
                .isNull();

        assertThat(catchThrowable(() -> Preconditions.checkState(false)))
                .isExactlyInstanceOf(IllegalStateException.class)
                .hasNoCause()
                .hasMessage(null);
    }

    @Test
    public void testCheckStateWithMessage() {
        assertThat(catchThrowable(() -> Preconditions.checkState(true, "Message0")))
                .isNull();

        assertThat(catchThrowable(() -> Preconditions.checkState(false, "Message0")))
                .isExactlyInstanceOf(IllegalStateException.class)
                .hasNoCause()
                .hasMessage("Message0");
    }

    @Test
    public void testCheckStateWithPattern() {
        assertThat(catchThrowable(() -> Preconditions.checkState(true, "Message%d", 0)))
                .isNull();

        assertThat(catchThrowable(() -> Preconditions.checkState(false, "Message%d", 0)))
                .isExactlyInstanceOf(IllegalStateException.class)
                .hasNoCause()
                .hasMessage("Message0");
    }

    @Test
    public void testCheckNotNull() {
        assertThat(catchThrowable(() -> Preconditions.checkNotNull(new Object())))
                .isNull();

        assertThat(catchThrowable(() -> Preconditions.checkNotNull(null)))
                .isExactlyInstanceOf(NullPointerException.class)
                .hasNoCause()
                .hasMessage(null);
    }

    @Test
    public void testCheckNotNullWithMessage() {
        assertThat(catchThrowable(() -> Preconditions.checkNotNull(new Object(), "Message0")))
                .isNull();

        assertThat(catchThrowable(() -> Preconditions.checkNotNull(null, "Message0")))
                .isExactlyInstanceOf(NullPointerException.class)
                .hasNoCause()
                .hasMessage("Message0");
    }

    @Test
    public void testCheckNotNullWithPattern() {
        assertThat(catchThrowable(() -> Preconditions.checkNotNull(new Object(), "Message%d", 0)))
                .isNull();

        assertThat(catchThrowable(() -> Preconditions.checkNotNull(null, "Message%d", 0)))
                .isExactlyInstanceOf(NullPointerException.class)
                .hasNoCause()
                .hasMessage("Message0");
    }

    @Test
    public void testCheckElementIndex() {
        // index < 0
        assertThat(catchThrowable(() -> Preconditions.checkElementIndex(-1, 0)))
                .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                .hasNoCause()
                .hasMessage("index (-1) must not be negative");

        // size < 0
        assertThat(catchThrowable(() -> Preconditions.checkElementIndex(0, -1)))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasNoCause()
                .hasMessage("negative size: -1");

        // index == size
        assertThat(catchThrowable(() -> Preconditions.checkElementIndex(0, 0)))
                .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                .hasNoCause()
                .hasMessage("index (0) must be less than size (0)");

        // index < size
        assertThat(catchThrowable(() -> Preconditions.checkElementIndex(0, 1)))
                .isNull();

        // index > size
        assertThat(catchThrowable(() -> Preconditions.checkElementIndex(1, 0)))
                .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                .hasNoCause()
                .hasMessage("index (1) must be less than size (0)");
    }

    @Test
    public void testCheckPositionIndex() {
        // index < 0
        assertThat(catchThrowable(() -> Preconditions.checkPositionIndex(-1, 0)))
                .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                .hasNoCause()
                .hasMessage("index (-1) must not be negative");

        // size < 0
        assertThat(catchThrowable(() -> Preconditions.checkPositionIndex(0, -1)))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasNoCause()
                .hasMessage("negative size: -1");

        // index == size
        assertThat(catchThrowable(() -> Preconditions.checkPositionIndex(0, 0)))
                .isNull();

        // index < size
        assertThat(catchThrowable(() -> Preconditions.checkPositionIndex(0, 1)))
                .isNull();

        // index > size
        assertThat(catchThrowable(() -> Preconditions.checkPositionIndex(1, 0)))
                .isExactlyInstanceOf(IndexOutOfBoundsException.class)
                .hasNoCause()
                .hasMessage("index (1) must not be greater than size (0)");
    }
}