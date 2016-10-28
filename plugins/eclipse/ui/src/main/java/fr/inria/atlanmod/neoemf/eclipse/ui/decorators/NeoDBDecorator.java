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

package fr.inria.atlanmod.neoemf.eclipse.ui.decorators;

import fr.inria.atlanmod.neoemf.datastore.PersistenceBackendFactory;
import fr.inria.atlanmod.neoemf.eclipse.ui.NeoEMFUiPlugin;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class NeoDBDecorator extends LabelProvider implements ILabelDecorator {

    @Override
    public Image decorateImage(Image image, Object element) {
        if (element instanceof IFolder) {
            IFolder folder = (IFolder) element;
            IFile configFile = folder.getFile(PersistenceBackendFactory.NEO_CONFIG_FILE);
            if (configFile.exists()) {
                // In a NeoEMF Database Folder
                return NeoEMFUiPlugin.getImageDescriptor("icons/full/obj16/neoemf.png").createImage();
            }
        }
        return null;
    }

    @Override
    public String decorateText(String text, Object element) {
        // TODO Auto-generated method stub
        return null;
    }
}
