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

package fr.inria.atlanmod.neoemf.benchmarks.datastore;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

import java.io.File;
import java.util.Collections;
import java.util.Map;

public interface InternalBackend extends Backend {

    String getName();

    String getResourceExtension();

    String getStoreExtension();

    EPackage initAndGetEPackage() throws Exception;

    Resource createResource(File file, ResourceSet resourceSet) throws Exception;

    default Map<String, Object> getOptions() {
        return Collections.emptyMap();
    }
}
