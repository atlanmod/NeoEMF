/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.io;

import fr.inria.atlanmod.commons.Throwables;
import fr.inria.atlanmod.neoemf.data.DataCopier;
import fr.inria.atlanmod.neoemf.data.mapping.DataMapper;

import org.osgi.service.component.annotations.Component;

import java.io.IOException;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link DataCopier} using the direct import/export.
 */
@Component(service = DataCopier.class)
@ParametersAreNonnullByDefault
public final class DirectDataCopier implements DataCopier {

    @Override
    public void copy(DataMapper source, DataMapper target) {
        try {
            Migrator.fromMapper(source).toMapper(target).migrate();
        }
        catch (IOException e) {
            throw Throwables.shouldNeverHappen(e);
        }
    }
}
