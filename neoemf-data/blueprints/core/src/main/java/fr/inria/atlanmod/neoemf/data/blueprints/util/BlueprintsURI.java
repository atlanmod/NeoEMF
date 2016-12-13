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

package fr.inria.atlanmod.neoemf.data.blueprints.util;

import fr.inria.atlanmod.neoemf.util.PersistenceURI;

import org.apache.commons.io.FileUtils;
import org.eclipse.emf.common.util.URI;

import java.io.File;
import java.text.MessageFormat;
import java.util.Objects;

public class BlueprintsURI extends PersistenceURI {

    public static final String SCHEME = "neo-blueprints";

    protected BlueprintsURI(int hashCode, URI internalURI) {
        super(hashCode, internalURI);
    }

    public static URI createURI(URI uri) {
        if (Objects.equals(PersistenceURI.FILE_SCHEME, uri.scheme())) {
            return createFileURI(uri);
        }
        else if (Objects.equals(SCHEME, uri.scheme())) {
            return PersistenceURI.createURI(uri);
        }
        throw new IllegalArgumentException(MessageFormat.format("Can not create {0} from the URI scheme {1}", BlueprintsURI.class.getSimpleName(), uri.scheme()));
    }

    public static URI createFileURI(File file) {
        return PersistenceURI.createFileURI(file, SCHEME);
    }

    public static URI createFileURI(URI uri) {
        return createFileURI(FileUtils.getFile(uri.toFileString()));
    }
}
