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

package fr.inria.atlanmod.neoemf.data.blueprints;

import fr.inria.atlanmod.neoemf.AbstractTestHelper;
import fr.inria.atlanmod.neoemf.TestHelper;
import fr.inria.atlanmod.neoemf.data.BackendFactory;
import fr.inria.atlanmod.neoemf.data.blueprints.util.BlueprintsURI;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;

import java.io.File;

/**
 * An abstract {@link TestHelper} for all Blueprints implementations.
 *
 * @param <B> the "self"-type of this {@link TestHelper}
 */
public abstract class AbstractBlueprintsTestHelper<B extends AbstractBlueprintsTestHelper<B>> extends AbstractTestHelper<B> {

    /**
     * Constructs a new {@code AbstractBlueprintsTestHelper} with the given {@code ePackage}.
     *
     * @param ePackage the {@link EPackage} associated to the built {@link Resource}
     *
     * @see EPackage.Registry
     */
    protected AbstractBlueprintsTestHelper(EPackage ePackage) {
        super(ePackage);
    }

    @Override
    protected BackendFactory factory() {
        return BlueprintsBackendFactory.getInstance();
    }

    @Override
    protected String uriScheme() {
        return BlueprintsURI.SCHEME;
    }

    @Override
    public B uri(URI uri) {
        this.uri = BlueprintsURI.createURI(uri);
        return me();
    }

    @Override
    public B file(File file) {
        this.uri = BlueprintsURI.createFileURI(file);
        return me();
    }
}
