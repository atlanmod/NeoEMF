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
 * A test-case that checks the specific behavior of {@link Preconditions}.
 */
public class PreconditionsTest extends AbstractTest {

    @Test
    public void testConstructor() throws Exception {
        Constructor<?> constructor = Preconditions.class.getDeclaredConstructor();
        assertThat(Modifier.isPrivate(constructor.getModifiers())).isTrue();

        constructor.setAccessible(true);

        Throwable thrown = catchThrowable(constructor::newInstance);
        assertThat(thrown).isInstanceOf(InvocationTargetException.class);
        assertThat(thrown.getCause()).isExactlyInstanceOf(IllegalStateException.class).hasMessage("This class should not be instantiated");
    }

    @Test
    public void testCheckArgument() {
        Throwable thrown0 = catchThrowable(() -> Preconditions.checkArgument(true));
        assertThat(thrown0).isNull();

        Throwable thrown1 = catchThrowable(() -> Preconditions.checkArgument(false));
        assertThat(thrown1).isExactlyInstanceOf(IllegalArgumentException.class).hasNoCause().hasMessage(null);
    }

    @Test
    public void testCheckArgumentWithMessage() {
        Throwable thrown0 = catchThrowable(() -> Preconditions.checkArgument(true, "Message0"));
        assertThat(thrown0).isNull();

        Throwable thrown1 = catchThrowable(() -> Preconditions.checkArgument(false, "Message0"));
        assertThat(thrown1).isExactlyInstanceOf(IllegalArgumentException.class).hasNoCause().hasMessage("Message0");
    }

    @Test
    public void testCheckArgumentWithPattern() {
        Throwable thrown0 = catchThrowable(() -> Preconditions.checkArgument(true, "Message%d", 0));
        assertThat(thrown0).isNull();

        Throwable thrown1 = catchThrowable(() -> Preconditions.checkArgument(false, "Message%d", 0));
        assertThat(thrown1).isExactlyInstanceOf(IllegalArgumentException.class).hasNoCause().hasMessage("Message0");
    }

    @Test
    public void testCheckState() {
        Throwable thrown0 = catchThrowable(() -> Preconditions.checkState(true));
        assertThat(thrown0).isNull();

        Throwable thrown1 = catchThrowable(() -> Preconditions.checkState(false));
        assertThat(thrown1).isExactlyInstanceOf(IllegalStateException.class).hasNoCause().hasMessage(null);
    }

    @Test
    public void testCheckStateWithMessage() {
        Throwable thrown0 = catchThrowable(() -> Preconditions.checkState(true, "Message0"));
        assertThat(thrown0).isNull();

        Throwable thrown1 = catchThrowable(() -> Preconditions.checkState(false, "Message0"));
        assertThat(thrown1).isExactlyInstanceOf(IllegalStateException.class).hasNoCause().hasMessage("Message0");
    }

    @Test
    public void testCheckStateWithPattern() {
        Throwable thrown0 = catchThrowable(() -> Preconditions.checkState(true, "Message%d", 0));
        assertThat(thrown0).isNull();

        Throwable thrown1 = catchThrowable(() -> Preconditions.checkState(false, "Message%d", 0));
        assertThat(thrown1).isExactlyInstanceOf(IllegalStateException.class).hasNoCause().hasMessage("Message0");
    }

    @Test
    public void testCheckNotNull() {
        Throwable thrown0 = catchThrowable(() -> Preconditions.checkNotNull(new Object()));
        assertThat(thrown0).isNull();

        Throwable thrown1 = catchThrowable(() -> Preconditions.checkNotNull(null));
        assertThat(thrown1).isExactlyInstanceOf(NullPointerException.class).hasNoCause().hasMessage(null);
    }

    @Test
    public void testCheckNotNullWithMessage() {
        Throwable thrown0 = catchThrowable(() -> Preconditions.checkNotNull(new Object(), "Message0"));
        assertThat(thrown0).isNull();

        Throwable thrown1 = catchThrowable(() -> Preconditions.checkNotNull(null, "Message0"));
        assertThat(thrown1).isExactlyInstanceOf(NullPointerException.class).hasNoCause().hasMessage("Message0");
    }

    @Test
    public void testCheckNotNullWithPattern() {
        Throwable thrown0 = catchThrowable(() -> Preconditions.checkNotNull(new Object(), "Message%d", 0));
        assertThat(thrown0).isNull();

        Throwable thrown1 = catchThrowable(() -> Preconditions.checkNotNull(null, "Message%d", 0));
        assertThat(thrown1).isExactlyInstanceOf(NullPointerException.class).hasNoCause().hasMessage("Message0");
    }

    @Test
    public void testCheckElementIndex() {
        // index < 0
        Throwable thrown0 = catchThrowable(() -> Preconditions.checkElementIndex(-1, 0));
        assertThat(thrown0).isExactlyInstanceOf(IndexOutOfBoundsException.class).hasNoCause().hasMessage("index (-1) must not be negative");

        // size < 0
        Throwable thrown1 = catchThrowable(() -> Preconditions.checkElementIndex(0, -1));
        assertThat(thrown1).isExactlyInstanceOf(IllegalArgumentException.class).hasNoCause().hasMessage("negative size: -1");

        // index == size
        Throwable thrown2 = catchThrowable(() -> Preconditions.checkElementIndex(0, 0));
        assertThat(thrown2).isExactlyInstanceOf(IndexOutOfBoundsException.class).hasNoCause().hasMessage("index (0) must be less than size (0)");

        // index < size
        Throwable thrown3 = catchThrowable(() -> Preconditions.checkElementIndex(0, 1));
        assertThat(thrown3).isNull();

        // index > size
        Throwable thrown4 = catchThrowable(() -> Preconditions.checkElementIndex(1, 0));
        assertThat(thrown4).isExactlyInstanceOf(IndexOutOfBoundsException.class).hasNoCause().hasMessage("index (1) must be less than size (0)");
    }

    @Test
    public void testCheckPositionIndex() {
        // index < 0
        Throwable thrown0 = catchThrowable(() -> Preconditions.checkPositionIndex(-1, 0));
        assertThat(thrown0).isExactlyInstanceOf(IndexOutOfBoundsException.class).hasNoCause().hasMessage("index (-1) must not be negative");

        // size < 0
        Throwable thrown1 = catchThrowable(() -> Preconditions.checkPositionIndex(0, -1));
        assertThat(thrown1).isExactlyInstanceOf(IllegalArgumentException.class).hasNoCause().hasMessage("negative size: -1");

        // index == size
        Throwable thrown2 = catchThrowable(() -> Preconditions.checkPositionIndex(0, 0));
        assertThat(thrown2).isNull();

        // index < size
        Throwable thrown3 = catchThrowable(() -> Preconditions.checkPositionIndex(0, 1));
        assertThat(thrown3).isNull();

        // index > size
        Throwable thrown4 = catchThrowable(() -> Preconditions.checkPositionIndex(1, 0));
        assertThat(thrown4).isExactlyInstanceOf(IndexOutOfBoundsException.class).hasNoCause().hasMessage("index (1) must not be greater than size (0)");
    }
}