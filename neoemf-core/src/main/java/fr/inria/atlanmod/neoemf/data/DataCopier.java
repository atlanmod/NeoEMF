/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data;

import fr.inria.atlanmod.commons.function.Copier;
import fr.inria.atlanmod.neoemf.data.mapping.DataMapper;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link Copier} for {@link DataMapper} instances.
 */
@ParametersAreNonnullByDefault
public interface DataCopier extends Copier<DataMapper> {
}
