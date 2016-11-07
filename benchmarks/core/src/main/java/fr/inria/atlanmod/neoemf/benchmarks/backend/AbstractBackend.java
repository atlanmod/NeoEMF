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

import fr.inria.atlanmod.neoemf.benchmarks.io.Migrator;
import fr.inria.atlanmod.neoemf.benchmarks.util.BenchmarkUtil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.emf.ecore.resource.Resource;

import java.io.File;
import java.nio.file.Path;

import static java.util.Objects.isNull;

public abstract class AbstractBackend implements Backend {

    protected static final Logger LOG = LogManager.getLogger();

    protected abstract String getResourceImportExtension();

    protected abstract String getResourceExportExtension();

    protected abstract Class<?> getEPackageClass();

    @Override
    public void saveResource(Resource resource) throws Exception {
        resource.save(getSaveOptions());
    }

    @Override
    public File createResource(String name) throws Exception {
        File migratedFile = Migrator.getInstance().migrate(name, getResourceImportExtension(), getEPackageClass());

        Path createdFilePath = BenchmarkUtil.getStoreDirectory().resolve(Files.getNameWithoutExtension(migratedFile.getAbsolutePath()) + "." + getResourceExportExtension());
        File file = create(migratedFile.getAbsolutePath(), createdFilePath.toString());

        if (isNull(file) || !file.exists()) {
            throw new IllegalArgumentException("'" + name + ".xmi' does not exist in resource directory");
        }

        return file;
    }

    protected abstract File create(String in, String out) throws Exception;
}
