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

package fr.inria.atlanmod.commons.hash.murmur;

import fr.inria.atlanmod.commons.hash.HashCode;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * Murmur3 64-bit variant. This is essentially MSB 8 bytes of Murmur3 128-bit variant.
 */
@ParametersAreNonnullByDefault
public class Murmur64 implements MurmurHasher {

    @Nonnull
    @Override
    public HashCode hash(final byte[] bytes) {
        long result = hash(bytes, DEFAULT_SEED);

        byte[] bytesValue = new byte[8];
        for (int i = 7; i >= 0; i--) {
            bytesValue[i] = (byte) (result & 0xffL);
            result >>= 8;
        }

        return new HashCode(bytesValue);
    }

    /**
     * Murmur3 64-bit variant. This is essentially MSB 8 bytes of Murmur3 128-bit variant.
     *
     * @param bytes the bytes to hash
     * @param seed  seed
     *
     * @return a 64-bit hashcode
     */
    private long hash(final byte[] bytes, final int seed) {
        final int length = bytes.length;
        final int nBlocks = length >> 3;

        long h = seed;

        // Body
        for (int i = 0; i < nBlocks; i++) {
            final int i8 = i << 3;
            long k = bytes[i8] & 0xffL
                    | (bytes[i8 + 1] & 0xffL) << 8
                    | (bytes[i8 + 2] & 0xffL) << 16
                    | (bytes[i8 + 3] & 0xffL) << 24
                    | (bytes[i8 + 4] & 0xffL) << 32
                    | (bytes[i8 + 5] & 0xffL) << 40
                    | (bytes[i8 + 6] & 0xffL) << 48
                    | (bytes[i8 + 7] & 0xffL) << 56;

            // Mix functions
            k *= C1_64;
            k = Long.rotateLeft(k, R1_64);
            k *= C2_64;
            h ^= k;
            h = Long.rotateLeft(h, R2_64) * M_64 + N1_64;
        }

        // Tail
        long k1 = 0;
        int tailStart = nBlocks << 3;
        switch (length - tailStart) {
            case 7:
                k1 ^= (bytes[tailStart + 6] & 0xffL) << 48;
            case 6:
                k1 ^= (bytes[tailStart + 5] & 0xffL) << 40;
            case 5:
                k1 ^= (bytes[tailStart + 4] & 0xffL) << 32;
            case 4:
                k1 ^= (bytes[tailStart + 3] & 0xffL) << 24;
            case 3:
                k1 ^= (bytes[tailStart + 2] & 0xffL) << 16;
            case 2:
                k1 ^= (bytes[tailStart + 1] & 0xffL) << 8;
            case 1:
                k1 ^= (bytes[tailStart] & 0xffL);
                k1 *= C1_64;
                k1 = Long.rotateLeft(k1, R1_64);
                k1 *= C2_64;
                h ^= k1;
        }

        // Finalization
        h ^= length;
        h = MurmurHasher.finalizationMix64(h);

        return h;
    }
}
