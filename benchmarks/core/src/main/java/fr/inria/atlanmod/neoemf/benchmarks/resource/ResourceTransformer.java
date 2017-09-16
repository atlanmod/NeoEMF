package fr.inria.atlanmod.neoemf.benchmarks.resource;

import fr.inria.atlanmod.neoemf.benchmarks.adapter.Adapter;

import org.eclipse.emf.ecore.resource.Resource;

import java.io.File;
import java.io.IOException;
import java.util.function.BiFunction;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkArgument;
import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;

/**
 * An object that pre-process a {@link Resource}.
 */
@ParametersAreNonnullByDefault
interface ResourceTransformer extends BiFunction<File, Adapter.Internal, File> {

    @Override
    default File apply(File file, Adapter.Internal adapter) {
        checkNotNull(file);
        checkArgument(file.exists(), "Resource '%s' does not exist", file);
        Resources.checkValid(file.getAbsolutePath());

        try {
            return transform(file, adapter);
        }
        catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Transforms a {@link Resource} {@code file}.
     *
     * @param file    the resource file
     * @param adapter the adapter where to store the resource
     *
     * @return the created file
     *
     * @throws IOException if a error occurs during the transformation
     */
    @Nonnull
    File transform(File file, Adapter.Internal adapter) throws IOException;
}
