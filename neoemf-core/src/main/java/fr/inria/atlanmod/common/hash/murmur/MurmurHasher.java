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

import fr.inria.atlanmod.common.hash.Hasher;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
interface MurmurHasher extends Hasher {

    int DEFAULT_SEED = 104729;

    // Constants for 32 bit variant
    int C1_32 = 0xcc9e2d51;

    int C2_32 = 0x1b873593;

    int R1_32 = 15;

    int R2_32 = 13;

    int M_32 = 5;

    int N_32 = 0xe6546b64;

    // Constants for 64+ bit variant
    long C1_64 = 0x87c37b91114253d5L;

    long C2_64 = 0x4cf5ad432745937fL;

    int R1_64 = 31;

    int R2_64 = 27;

    int R3_64 = 33;

    int M_64 = 5;

    int N1_64 = 0x52dce729;

    int N2_64 = 0x38495ab5;

    /**
     * Finalization mix (32 bit). Force all bits of a hash block to avalanche.
     */
    static int finalizationMix32(int h) {
        h ^= (h >>> 16);
        h *= 0x85ebca6b;
        h ^= (h >>> 13);
        h *= 0xc2b2ae35;
        h ^= (h >>> 16);
        return h;
    }

    /**
     * Finalization mix (64 bit). Force all bits of a hash block to avalanche.
     */
    static long finalizationMix64(long h) {
        h ^= (h >>> 33);
        h *= 0xff51afd7ed558ccdL;
        h ^= (h >>> 33);
        h *= 0xc4ceb9fe1a85ec53L;
        h ^= (h >>> 33);
        return h;
    }
}
