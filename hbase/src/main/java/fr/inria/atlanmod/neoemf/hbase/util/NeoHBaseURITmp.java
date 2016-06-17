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
package fr.inria.atlanmod.neoemf.hbase.util;

import fr.inria.atlanmod.neoemf.util.NeoURI;

import org.apache.commons.io.FileUtils;
import org.eclipse.emf.common.util.URI;

import java.io.File;
import java.text.MessageFormat;

public class NeoHBaseURITmp extends NeoURI {

    public static final String NEO_HBASE_SCHEME = "neo-hbase";
    
    protected NeoHBaseURITmp(int hashCode, URI internalURI) {
        super(hashCode,internalURI);
    }
    
    public static URI createNeoHBaseURI(URI uri) {
        if(NeoURI.FILE_SCHEME.equals(uri.scheme())) {
            return createNeoHBaseURI(FileUtils.getFile(uri.toFileString()));
        }
        else if(NEO_HBASE_SCHEME.equals(uri.scheme())) {
            return NeoURI.createNeoURI(uri);
        }
        else {
            throw new IllegalArgumentException(MessageFormat.format("Can not create NeoHBaseURI from the URI scheme {0}", uri.scheme()));
        }
    }
    
    public static URI createNeoHBaseURI(File file) {
        return NeoURI.createNeoURI(file, NEO_HBASE_SCHEME);
    }
}