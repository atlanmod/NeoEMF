/*******************************************************************************
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 *******************************************************************************/

package fr.inria.atlanmod.neoemf.resources;

public interface PersistentResourceOptions {
    
    String STORE_OPTIONS = "stores";
    
    interface StoreOption {}
    
    enum EStoreOption implements StoreOption {
        IS_SET_CACHING,
        LOGGING,
        SIZE_CACHING,
        ESTRUCUTRALFEATURE_CACHING,
        LOADED_OBJECT_COUNTER_LOGGING
    }
}
