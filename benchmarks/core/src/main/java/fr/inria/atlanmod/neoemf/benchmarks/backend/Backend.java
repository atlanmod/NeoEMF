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

package fr.inria.atlanmod.neoemf.benchmarks.backend;

import org.eclipse.emf.ecore.resource.Resource;

import java.io.File;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Map;

public interface Backend {

    File createResource(String name) throws Exception;

    Resource loadResource(Path path) throws Exception;

    void saveResource(Resource resource) throws Exception;

    void unloadResource(Resource resource) throws Exception;

    default Map<Object, Object> getLoadOptions() {
        return Collections.emptyMap();
    }

    default Map<Object, Object> getSaveOptions() {
        return Collections.emptyMap();
    }
}
