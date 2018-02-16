/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.io.processor;

import fr.inria.atlanmod.neoemf.io.Handler;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link fr.inria.atlanmod.neoemf.io.Handler} that pre-processes and post-processes received events, before notifying
 * the next {@link fr.inria.atlanmod.neoemf.io.Handler}.
 * <p>
 * It can add validation, redirection, cancellation, or data enhancement capabilities.
 * <p>
 * <b>NOTE:</b> Because of the stream-based architecture, all {@code Processor}s are stateful.
 */
@ParametersAreNonnullByDefault
public interface Processor extends Handler {
}
