/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.eclipse.ui.decorator;

import fr.inria.atlanmod.neoemf.config.Config;
import fr.inria.atlanmod.neoemf.eclipse.ui.NeoUIPlugin;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import java.io.File;
import java.util.Optional;

/**
 * A {@link ILabelDecorator} that decorates {@link fr.inria.atlanmod.neoemf.data.Backend} directories with an icon.
 */
public class BackendDirectoryDecorator extends LabelProvider implements ILabelDecorator {

    /**
     * The NeoEMF icon path.
     */
    private static final String ICON_PATH = "icons/full/obj16/neoemf.png";

    @Override
    public Image decorateImage(Image image, Object element) {
        return Optional.ofNullable(element)
                .filter(IFolder.class::isInstance)
                .map(IFolder.class::cast)
                .map(IResource::getLocation)
                .map(IPath::toFile)
                .map(File::toPath)
                .filter(Config::exists)
                .map(s -> NeoUIPlugin.getImageDescriptor(ICON_PATH).createImage())
                .orElse(null);
    }

    @Override
    public String decorateText(String text, Object element) {
        return null;
    }
}
