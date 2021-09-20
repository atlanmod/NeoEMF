package org.atlanmod.neoemf.io.binary.serializer;

import org.atlanmod.neoemf.io.binary.Converter;
import org.atlanmod.neoemf.io.binary.adapter.BinaryAdapterProvider;

public class FeatureSerializer {
    private final BinaryAdapterProvider provider;
    private final Converter<String> converter;

    public FeatureSerializer(BinaryAdapterProvider provider) {
        this.provider = provider;
        converter = provider.getStringConverter();
    }
}
