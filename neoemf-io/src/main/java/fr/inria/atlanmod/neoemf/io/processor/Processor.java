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

package fr.inria.atlanmod.neoemf.io.processor;

import fr.inria.atlanmod.neoemf.io.Handler;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link Handler} that pre-processes and post-processes received events, before notifying the next {@link Handler}.
 * <p>
 * It can add validation, redirection, cancellation, or data enhancement capabilities.
 * <p>
 * <b>NOTE:</b> Because of the stream-based architecture, all {@code Processor}s are stateful.
 */
@ParametersAreNonnullByDefault
public interface Processor extends Handler {
}
