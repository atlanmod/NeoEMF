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

package fr.inria.atlanmod.neoemf.eclipse.ui.decorator;

import fr.inria.atlanmod.neoemf.data.BackendConfig;
import fr.inria.atlanmod.neoemf.eclipse.ui.NeoUIPlugin;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

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
                .map(f -> f.getFile(BackendConfig.DEFAULT_FILENAME))
                .filter(IResource::exists)
                .map(s -> NeoUIPlugin.getImageDescriptor(ICON_PATH).createImage())
                .orElse(null);
    }

    @Override
    public String decorateText(String text, Object element) {
        return null;
    }
}
