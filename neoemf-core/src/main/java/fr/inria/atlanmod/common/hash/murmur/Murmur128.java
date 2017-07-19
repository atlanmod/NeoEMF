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

package fr.inria.atlanmod.common.hash.murmur;

import fr.inria.atlanmod.common.hash.HashCode;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * Murmur3 128-bit variant.
 */
@ParametersAreNonnullByDefault
public class Murmur128 implements MurmurHasher {

    @Nonnull
    @Override
    public HashCode hash(final byte[] bytes) {
        long[] results = hash(bytes, DEFAULT_SEED);

        long leastSignificant = results[0];
        long mostSignificant = results[1];

        byte[] bytesValue = new byte[16];

        for (int i = 15; i >= 8; i--) {
            bytesValue[i] = (byte) (mostSignificant & 0xffL);
            mostSignificant >>= 8;
        }

        for (int i = 7; i >= 0; i--) {
            bytesValue[i] = (byte) (leastSignificant & 0xffL);
            leastSignificant >>= 8;
        }

        return new HashCode(bytesValue);
    }

    /**
     * Murmur3 128-bit variant.
     *
     * @param bytes the bytes to hash
     * @param seed  seed
     *
     * @return a 128-bit hashcode (2 longs)
     */
    private long[] hash(final byte[] bytes, final int seed) {
        final int length = bytes.length;
        final int nBlocks = length >> 4;

        long h1 = seed;
        long h2 = seed;

        // Body
        for (int i = 0; i < nBlocks; i++) {
            final int i16 = i << 4;
            long k1 = bytes[i16] & 0xff
                    | (bytes[i16 + 1] & 0xffL) << 8
                    | (bytes[i16 + 2] & 0xffL) << 16
                    | (bytes[i16 + 3] & 0xffL) << 24
                    | (bytes[i16 + 4] & 0xffL) << 32
                    | (bytes[i16 + 5] & 0xffL) << 40
                    | (bytes[i16 + 6] & 0xffL) << 48
                    | (bytes[i16 + 7] & 0xffL) << 56;

            long k2 = bytes[i16 + 8] & 0xffL
                    | (bytes[i16 + 9] & 0xffL) << 8
                    | (bytes[i16 + 10] & 0xffL) << 16
                    | (bytes[i16 + 11] & 0xffL) << 24
                    | (bytes[i16 + 12] & 0xffL) << 32
                    | (bytes[i16 + 13] & 0xffL) << 40
                    | (bytes[i16 + 14] & 0xffL) << 48
                    | (bytes[i16 + 15] & 0xffL) << 56;

            // Mix functions for k1
            k1 *= C1_64;
            k1 = Long.rotateLeft(k1, R1_64);
            k1 *= C2_64;
            h1 ^= k1;
            h1 = Long.rotateLeft(h1, R2_64);
            h1 += h2;
            h1 = h1 * M_64 + N1_64;

            // Mix functions for k2
            k2 *= C2_64;
            k2 = Long.rotateLeft(k2, R3_64);
            k2 *= C1_64;
            h2 ^= k2;
            h2 = Long.rotateLeft(h2, R1_64);
            h2 += h1;
            h2 = h2 * M_64 + N2_64;
        }

        // Tail
        long k1 = 0;
        long k2 = 0;
        int tailStart = nBlocks << 4;
        switch (length - tailStart) {
            case 15:
                k2 ^= (bytes[tailStart + 14] & 0xffL) << 48;
            case 14:
                k2 ^= (bytes[tailStart + 13] & 0xffL) << 40;
            case 13:
                k2 ^= (bytes[tailStart + 12] & 0xffL) << 32;
            case 12:
                k2 ^= (bytes[tailStart + 11] & 0xffL) << 24;
            case 11:
                k2 ^= (bytes[tailStart + 10] & 0xffL) << 16;
            case 10:
                k2 ^= (bytes[tailStart + 9] & 0xffL) << 8;
            case 9:
                k2 ^= (bytes[tailStart + 8] & 0xffL);
                k2 *= C2_64;
                k2 = Long.rotateLeft(k2, R3_64);
                k2 *= C1_64;
                h2 ^= k2;

            case 8:
                k1 ^= (bytes[tailStart + 7] & 0xffL) << 56;
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
                h1 ^= k1;
        }

        // Finalization
        h1 ^= length;
        h2 ^= length;

        h1 += h2;
        h2 += h1;

        h1 = MurmurHasher.finalizationMix64(h1);
        h2 = MurmurHasher.finalizationMix64(h2);

        h1 += h2;
        h2 += h1;

        return new long[]{h1, h2};
    }
}
