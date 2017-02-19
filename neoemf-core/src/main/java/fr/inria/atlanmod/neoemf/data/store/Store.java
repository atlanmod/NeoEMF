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

package fr.inria.atlanmod.neoemf.data.store;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;

/**
 * An {@link InternalEObject.EStore} to establish a mapping between {@link Resource}s and {@link InternalEObject.EStore}s.
 */
public interface Store extends InternalEObject.EStore {

    /**
     * A value indicating that no index is specified.
     */
    int NO_INDEX = InternalEObject.EStore.NO_INDEX;

    @Override
    default EObject create(EClass eClass) {
        throw new IllegalStateException("This method should not be called");
    }
}
