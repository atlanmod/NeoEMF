/*
 * Copyright (c) 2013-2016 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.core;

import fr.inria.atlanmod.neoemf.core.impl.PersistenceFactoryImpl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;

/**
 * A factory able to create a {@link PersistentEObject} from an {@link EClass}.
 */
public interface PersistenceFactory extends EFactory {

    /**
     * Returns the default instance of this {@code PersistenceFactory factory}.
     */
    static PersistenceFactory getInstance() {
        return PersistenceFactoryImpl.getInstance();
    }

    @Override
    PersistentEObject create(EClass eClass);
}
