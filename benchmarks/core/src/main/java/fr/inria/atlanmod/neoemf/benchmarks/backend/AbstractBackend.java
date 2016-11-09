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

import com.google.common.io.Files;

import fr.inria.atlanmod.neoemf.benchmarks.io.Workspace;
import fr.inria.atlanmod.neoemf.benchmarks.io.Migrator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.emf.ecore.resource.Resource;

import java.io.File;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Map;

import static java.util.Objects.isNull;

public abstract class AbstractBackend implements Backend {

    protected static final Logger LOG = LogManager.getLogger();

    protected abstract String getResourceExtension();

    protected abstract String getStoreExtension();

    protected abstract Class<?> getEPackageClass();

    protected abstract File create(File inputFile, Path outputPath) throws Exception;

    @Override
    public File create(String name) throws Exception {
        File inputFile = Migrator.getInstance().migrate(name, getResourceExtension(), getEPackageClass());

        String outputFileName = Files.getNameWithoutExtension(inputFile.getAbsolutePath()) + "." + getStoreExtension();
        Path outputPath = Workspace.getStoreDirectory().resolve(outputFileName);
        File file = create(inputFile, outputPath);

        if (isNull(file) || !file.exists()) {
            throw new IllegalArgumentException("'" + name + ".xmi' does not exist in resource directory");
        }

        return file;
    }

    @Override
    public void save(Resource resource) throws Exception {
        resource.save(getSaveOptions());
    }

    protected Map<Object, Object> getLoadOptions() {
        return Collections.emptyMap();
    }

    protected Map<Object, Object> getSaveOptions() {
        return Collections.emptyMap();
    }
}
