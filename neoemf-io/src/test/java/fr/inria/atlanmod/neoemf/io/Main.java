/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.io;

import fr.inria.atlanmod.neoemf.config.ImmutableConfig;
import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.im.InMemoryBackendFactory;
import fr.inria.atlanmod.neoemf.data.im.config.InMemoryConfig;
import fr.inria.atlanmod.neoemf.data.im.util.InMemoryUri;
import fr.inria.atlanmod.neoemf.data.mapping.DataMapper;
import fr.inria.atlanmod.neoemf.data.store.StoreFactory;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.gmt.modisco.java.JavaPackage;

import java.io.File;

public class Main {

    public static void main(String[] args) throws Exception {
        EPackage.Registry.INSTANCE.put(JavaPackage.eNS_URI, JavaPackage.eINSTANCE);

        ImmutableConfig config = InMemoryConfig.newConfig();

        BackendFactory factory = InMemoryBackendFactory.getInstance();

        File sourceFile = new File("D:/WS/eclipse/ws/.runtime/neoemf/fr.inria.atlanmod.neoemf.demo/models/sample.xmi");
        URI targetUri = InMemoryUri.builder().fromFile("");

        try (Backend backend = factory.createBackend(targetUri, config); DataMapper mapper = StoreFactory.getInstance().createStore(backend, config)) {
            Migrator.fromXmi(sourceFile)
                    .toMapper(mapper)
                    .withTimer()
                    .withLogger()
                    .migrate();
        }
    }

}
