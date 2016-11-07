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

package fr.inria.atlanmod.neoemf.benchmarks;

import com.google.common.io.Files;

import fr.inria.atlanmod.neoemf.benchmarks.io.Migrator;
import fr.inria.atlanmod.neoemf.benchmarks.util.BenchmarkUtil;

import org.eclipse.emf.ecore.resource.Resource;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public interface Backend {

    String getName();

    String getResourceName();

    Class<?> getEPackageClass();

    Resource loadResource(String path) throws Exception;

    void saveResource(Resource resource) throws Exception;

    void unloadResource(Resource resource) throws Exception;

    default Map<Object, Object> getLoadOptions() {
        return Collections.emptyMap();
    }

    default Map<Object, Object> getSaveOptions() {
        return Collections.emptyMap();
    }

    File create(String in, String out) throws Exception;

    default List<File> createAll() throws Exception {
        List<File> paths = new ArrayList<>();
        for (File f : Migrator.getInstance().migrateAll(getName(), getEPackageClass())) {
            File file = create(f.getAbsolutePath(), BenchmarkUtil.getStoreDirectory().resolve(Files.getNameWithoutExtension(f.getAbsolutePath()) + "." + getResourceName()).toString());

            if (file != null && file.exists()) {
                paths.add(file);
            }
        }
        return paths;
    }
}
