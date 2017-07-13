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
 * Murmur3 32-bit variant.
 */
@ParametersAreNonnullByDefault
public class Murmur32 implements MurmurHasher {

    @Nonnull
    @Override
    public HashCode hash(final byte[] data) {
        int result = hash(data, DEFAULT_SEED);

        byte[] bytesValue = new byte[]{(byte) (result >> 24), (byte) (result >> 16), (byte) (result >> 8), (byte) result};

        return new HashCode(bytesValue);
    }

    /**
     * Murmur3 32-bit variant.
     *
     * @param data the data to hash
     * @param seed seed
     *
     * @return a 32-bit hashcode
     */
    private int hash(final byte[] data, final int seed) {
        final int length = data.length;
        final int nBlocks = length >> 2;

        int h = seed;

        // Body
        for (int i = 0; i < nBlocks; i++) {
            int i4 = i << 2;
            int k = (data[i4] & 0xff)
                    | ((data[i4 + 1] & 0xff) << 8)
                    | ((data[i4 + 2] & 0xff) << 16)
                    | ((data[i4 + 3] & 0xff) << 24);

            // Mix functions
            k *= C1_32;
            k = Integer.rotateLeft(k, R1_32);
            k *= C2_32;
            h ^= k;
            h = Integer.rotateLeft(h, R2_32) * M_32 + N_32;
        }

        // Tail
        int idx = nBlocks << 2;
        int k1 = 0;
        switch (length - idx) {
            case 3:
                k1 ^= data[idx + 2] << 16;
            case 2:
                k1 ^= data[idx + 1] << 8;
            case 1:
                k1 ^= data[idx];

                // Mix functions
                k1 *= C1_32;
                k1 = Integer.rotateLeft(k1, R1_32);
                k1 *= C2_32;
                h ^= k1;
        }

        // Finalization
        h ^= length;
        h = MurmurHasher.finalizationMix32(h);

        return h;
    }
}
