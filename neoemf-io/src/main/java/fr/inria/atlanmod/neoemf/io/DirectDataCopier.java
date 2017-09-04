package fr.inria.atlanmod.neoemf.io;

import fr.inria.atlanmod.commons.Copier;
import fr.inria.atlanmod.neoemf.data.mapping.DataMapper;

import java.io.IOException;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link Copier} of {@link DataMapper} instances using the direct import/export.
 */
@ParametersAreNonnullByDefault
@SuppressWarnings("unused") // Called dynamically
public final class DirectDataCopier implements Copier<DataMapper> {

    @Override
    public void copy(DataMapper source, DataMapper target) {
        try {
            Migrator.fromMapper(source)
                    .toMapper(target)
                    .migrate();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
