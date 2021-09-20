package org.atlanmod.neoemf.io.binary.identifier;

import org.atlanmod.neoemf.io.binary.adapter.BinaryAdapterProvider;

import javax.annotation.Nonnull;

public class Identifier {
    private final int index;
    private final BinaryAdapterProvider provider;

    @Nonnull
    public Identifier(int index, BinaryAdapterProvider provider) {
        this.index = index;
        this.provider = provider;
    }

    public int index() {
        return index;
    }


    public BinaryAdapterProvider provider() {
        return provider;
    }

}
