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

package fr.inria.atlanmod.neoemf.hbase.util;

import fr.inria.atlanmod.neoemf.util.NeoURI;

import org.apache.commons.io.FileUtils;
import org.eclipse.emf.common.util.URI;

import java.io.File;
import java.text.MessageFormat;

public class NeoHBaseURI extends NeoURI {

    public static final String SCHEME = "neo-hbase";

    protected NeoHBaseURI(int hashCode, URI internalURI) {
        super(hashCode, internalURI);
    }

    public static URI createURI(URI uri) {
        if (NeoURI.FILE_SCHEME.equals(uri.scheme())) {
            return createFileURI(FileUtils.getFile(uri.toFileString()));
        }
        else if (SCHEME.equals(uri.scheme())) {
            return NeoURI.createURI(uri);
        }
        else {
            throw new IllegalArgumentException(MessageFormat.format("Can not create NeoHBaseURI from the URI scheme {0}", uri.scheme()));
        }
    }

    public static URI createURI(String host, String port, URI modelURI) {
        return URI.createHierarchicalURI(
                SCHEME,
                host + ":" + port,
                null,
                modelURI.segments(),
                null,
                null);
    }

    public static URI createFileURI(File file) {
        return NeoURI.createFileURI(file, SCHEME);
    }

    public static String format(URI uri) {
        StringBuilder strBld = new StringBuilder();
        for (int i = 0; i < uri.segmentCount(); i++) {
            strBld.append(uri.segment(i).replaceAll("-", "_"));
            if (i != uri.segmentCount() - 1) {
                strBld.append("_");
            }
        }
        return strBld.toString();
    }
}