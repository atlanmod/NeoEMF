/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.benchmarks.adapter;

import fr.inria.atlanmod.neoemf.config.ImmutableConfig;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import java.io.File;
import java.util.Collections;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * An {@link Adapter} on top a an original XMI {@link Resource}.
 */
@AdapterName("xmi")
@ParametersAreNonnullByDefault
public class XmiAdapter extends AbstractAdapter {

    static {
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("zxmi", new XMIResourceFactoryImpl());
    }

    /**
     * Constructs a new {@code XmiAdapter}.
     */
    public XmiAdapter() {
        super("xmi", org.eclipse.gmt.modisco.java.emf.impl.JavaPackageImpl.class);
    }

    @Nonnull
    @Override
    public Resource createResource(File file) {
        final URI uri = URI.createFileURI(file.getAbsolutePath());
        return new ResourceSetImpl().createResource(uri);
    }

    @Nonnull
    @Override
    protected Map<String, ?> getOptions(ImmutableConfig config) {
        return Collections.emptyMap();
    }
}
