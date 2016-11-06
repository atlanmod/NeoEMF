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

import fr.inria.atlanmod.neoemf.benchmarks.util.BenchmarkUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public interface Creator {

    String getBaseName();

    String getResourceName();

    Class<?> getAssociatedClass();

    default Iterable<File> createAll() {
        List<File> files = new ArrayList<>();
        for (File f : Migrator.getInstance().migrateAll(getBaseName(), getAssociatedClass())) {
            File file = create(f.getAbsolutePath(), BenchmarkUtil.getBaseDirectory().resolve(Files.getNameWithoutExtension(f.getAbsolutePath()) + "." + getResourceName()).toString());

            if (file != null && file.exists()) {
                files.add(file);
            }
        }
        return files;
    }

    File create(String in, String out);
}
