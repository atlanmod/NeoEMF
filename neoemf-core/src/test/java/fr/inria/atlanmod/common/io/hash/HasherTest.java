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

package fr.inria.atlanmod.common.io.hash;

import fr.inria.atlanmod.neoemf.AbstractTest;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

/**
 * A test-case that checks the behavior of {@link Hasher}s.
 */
public class HasherTest extends AbstractTest {

    @Test
    public void testConstructor() throws Exception {
        Constructor<?> constructor = Hashers.class.getDeclaredConstructor();
        assertThat(Modifier.isPrivate(constructor.getModifiers())).isTrue();

        constructor.setAccessible(true);

        assertThat(catchThrowable(constructor::newInstance))
                .isInstanceOf(InvocationTargetException.class)
                .hasCauseExactlyInstanceOf(IllegalStateException.class);
    }

    @Test
    public void testInvalidAlgorithm() {
        assertThat(catchThrowable(() -> Hashers.nativeHash("unknown", new byte[0])))
                .isInstanceOf(RuntimeException.class)
                .hasCauseExactlyInstanceOf(NoSuchAlgorithmException.class);
    }

    @Test
    public void testMD5WithBytes() throws Exception {
        assertThat(Hashers.md5().hash("Nantes".getBytes(Charset.forName("UTF-8"))).toString()).isEqualToIgnoringCase("62ba359fa6b58bea641314e7a4635cf6");
    }

    @Test
    public void testMD5WithInt() throws Exception {
        assertThat(Hashers.md5().hash(44).toString()).isEqualToIgnoringCase("6ff43a9c893f569f5e21180998ad0db6");
    }

    @Test
    public void testMD5WithLong() throws Exception {
        assertThat(Hashers.md5().hash(44L).toString()).isEqualToIgnoringCase("dae78b2a1c0493063800e7c3720391a8");
    }

    @Test
    public void testMD5WithString() throws Exception {
        assertThat(Hashers.md5().hash("Nantes").toString()).isEqualToIgnoringCase("62ba359fa6b58bea641314e7a4635cf6");
    }

    @Test
    public void testSHA1WithBytes() throws Exception {
        assertThat(Hashers.sha1().hash("Nantes".getBytes(Charset.forName("UTF-8"))).toString()).isEqualToIgnoringCase("02e91bece69129f6c1f1dcc4322228cc362906a9");
    }

    @Test
    public void testSHA1WithInt() throws Exception {
        assertThat(Hashers.sha1().hash(44).toString()).isEqualToIgnoringCase("6dd4ac18c5ac5532393ce704782a38e4a566320a");
    }

    @Test
    public void testSHA1WithLong() throws Exception {
        assertThat(Hashers.sha1().hash(44L).toString()).isEqualToIgnoringCase("4ccc3c3702164c936283bb83743bfc8ff483bd66");
    }

    @Test
    public void testSHA1WithString() throws Exception {
        assertThat(Hashers.sha1().hash("Nantes").toString()).isEqualToIgnoringCase("02e91bece69129f6c1f1dcc4322228cc362906a9");
    }

    @Test
    public void testSHA256WithBytes() throws Exception {
        assertThat(Hashers.sha256().hash("Nantes".getBytes(Charset.forName("UTF-8"))).toString()).isEqualToIgnoringCase("1e3c7690b503643635e3a349985281c1c7a85389b843ff7133a4a3d904d2a18a");
    }

    @Test
    public void testSHA256WithInt() throws Exception {
        assertThat(Hashers.sha256().hash(44).toString()).isEqualToIgnoringCase("552d7d672c5bbbccfb210a425e36a1d65bc663d3d3c395114c8ebc9c7cf28394");
    }

    @Test
    public void testSHA256WithLong() throws Exception {
        assertThat(Hashers.sha256().hash(44L).toString()).isEqualToIgnoringCase("45fff65563e17379eda2d9b8669b9a74fa206a5bc89db9464c8552e62ac9059e");
    }

    @Test
    public void testSHA256WithString() throws Exception {
        assertThat(Hashers.sha256().hash("Nantes").toString()).isEqualToIgnoringCase("1e3c7690b503643635e3a349985281c1c7a85389b843ff7133a4a3d904d2a18a");
    }
}