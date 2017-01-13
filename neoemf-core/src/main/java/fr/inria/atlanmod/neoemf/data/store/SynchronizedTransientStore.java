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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A {@link TransientStore} that uses synchronized collections to store data in memory, using
 * {@link Collections#synchronizedMap(java.util.Map)}.
 */
public class SynchronizedTransientStore extends AbstractTransientStore {

    /**
     * Constructs a new {@ode SynchronizedTransientStore}.
     */
    public SynchronizedTransientStore() {
        super();
        singleMap = Collections.synchronizedMap(singleMap);
        manyMap = Collections.synchronizedMap(manyMap);
    }

    @Override
    protected List<Object> createValue() {
        return Collections.synchronizedList(new ArrayList<>());
    }
}
