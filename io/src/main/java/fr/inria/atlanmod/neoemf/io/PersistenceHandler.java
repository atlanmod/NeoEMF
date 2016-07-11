/*
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.io;

/**
 * A {@link IOHandler handler} able to persist notications sent by a {@link IONotifier notifier}.
 * <p/>
 * It correspond to the tail of the parsing process in case of an import.
 */
public interface PersistenceHandler extends IOHandler {

}
