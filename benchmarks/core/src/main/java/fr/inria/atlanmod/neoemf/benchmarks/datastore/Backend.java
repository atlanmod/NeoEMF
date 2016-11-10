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

package fr.inria.atlanmod.neoemf.benchmarks.datastore;

import org.eclipse.emf.ecore.resource.Resource;

import java.io.File;

public interface Backend {

    File create(String name) throws Exception;

    Resource load(File file) throws Exception;

    void save(Resource resource) throws Exception;

    void unload(Resource resource) throws Exception;

    File copy(File inputFile) throws Exception;
}
