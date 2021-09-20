package fr.inria.atlanmod.neoemf.io.provider;

import org.atlanmod.neoemf.io.binary.NeoBinaryResourceImpl;
import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.*;
import java.util.stream.Stream;

public class OptionsProvider  {

    public static class Style implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
            List<String> keys = List.of(
                    NeoBinaryResourceImpl.OPTION_STYLE_BINARY_FLOATING_POINT,
                    NeoBinaryResourceImpl.OPTION_STYLE_BINARY_DATE
            );
            List<Boolean> values = List.of(true, false);

            List<Map<String, Boolean>> options = new ArrayList<>(keys.size());
            for (String key : keys) {
                for(Boolean value : values) {
                    Map<String, Boolean> map = new HashMap<>();
                    map.put(key, value);
                    options.add(map);
                }
            }
            options.add(Collections.emptyMap());

            return options.stream()
                    .map(Arguments::of);
        }
    }

    public static class Version implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
            return Stream.of(
                    Map.of(BinaryResourceImpl.OPTION_VERSION, BinaryResourceImpl.BinaryIO.Version.VERSION_1_1),
                    Map.of(BinaryResourceImpl.OPTION_VERSION, BinaryResourceImpl.BinaryIO.Version.VERSION_1_0)
            )
                    .map(Arguments::of);
        }
    }
}
