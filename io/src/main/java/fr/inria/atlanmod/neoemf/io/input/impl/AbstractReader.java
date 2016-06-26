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

package fr.inria.atlanmod.neoemf.io.input.impl;

import com.google.common.collect.HashBiMap;

import fr.inria.atlanmod.neoemf.io.input.Reader;
import fr.inria.atlanmod.neoemf.io.internal.impl.AbstractInternalNotifier;

/**
 *
 */
public abstract class AbstractReader extends AbstractInternalNotifier implements Reader {

    private final HashBiMap<String, String> namespaces;

    public AbstractReader() {
        this.namespaces = HashBiMap.create();
    }

    protected void processNamespace(String prefix, String uri) {
        namespaces.put(prefix, uri);
    }

    protected String getNsUri(String prefix) {
        String nsUri = namespaces.get(prefix);

        if (nsUri == null) {
            throw new IllegalArgumentException("Unknown prefix " + prefix);
        }

        return nsUri;
    }

    protected String getNsPrefix(String uri) {
        String prefix = namespaces.inverse().get(uri);

        if (prefix == null) {
            throw new IllegalArgumentException("Unknown uri " + uri);
        }

        return prefix;
    }
}
