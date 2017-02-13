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

package fr.inria.atlanmod.neoemf.io.writer;

import fr.inria.atlanmod.neoemf.annotations.Experimental;

import java.io.OutputStream;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link Writer} that writes data into a {@link OutputStream}.
 */
@Experimental
@ParametersAreNonnullByDefault
public interface StreamWriter extends Writer {
}
