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
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import static java.util.Objects.nonNull;

/**
 * A {@link ILabelDecorator} that decorates {@code Backend} directories with an icon and a suffix.
 */
public class BackendDirectoryDecorator extends LabelProvider implements ILabelDecorator {

    /**
     * The NeoEMF icon path.
     */
    private static final String ICON_PATH = "icons/full/obj16/neoemf.png";

    /**
     * The pattern used to append the name of the NeoEMF adapter.
     */
    private static final String NAME_PATTERN = " [%s]";

    @Override
    public Image decorateImage(Image image, Object element) {
        final Optional<Path> resourceLocation = getResourceLocation(element);

        if (resourceLocation.isPresent() && Config.exists(resourceLocation.get())) {
            final ImageDescriptor descriptor = NeoUIPlugin.getImageDescriptor(ICON_PATH);
            if (nonNull(descriptor)) {
                return descriptor.createImage();
            }
        }

        return null;
    }

    @Override
    public String decorateText(String text, Object element) {
        final Optional<Path> resourceLocation = getResourceLocation(element);

        if (resourceLocation.isPresent()) {
            try {
                final Optional<Config> config = Config.load(resourceLocation.get());
                if (config.isPresent()) {
                    final String name = config.get().getName();
                    return text + String.format(NAME_PATTERN, name);
                }
            }
            catch (IOException e) {
                return null;
            }
        }

        return null;
    }

    /**
     * Returns the resource location from the given {@code element}.
     *
     * @param element the element whose image is being decorated
     *
     * @return an {@link Optional} containing the resource location, or {@link Optional#empty()} if the specified {@code
     * folder} does not represents a directory
     */
    private Optional<Path> getResourceLocation(Object element) {
        return Optional.ofNullable(element)
                .filter(IFolder.class::isInstance)
                .map(IFolder.class::cast)
                .map(IResource::getLocation)
                .map(IPath::toFile)
                .map(File::toPath);
    }
}
