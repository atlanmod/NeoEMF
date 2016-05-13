/*******************************************************************************
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 *******************************************************************************/

package fr.inria.atlanmod.neoemf.test.commons;

import fr.inria.atlanmod.neoemf.resources.PersistentResource;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractResourceBuilder {

    protected EPackage ePackage;
    
    protected ResourceSet rSet;
    protected URI uri;
    protected Map<Object,Object> resourceOptions;
    
    protected boolean isPersistent;
    
    public AbstractResourceBuilder(EPackage ePackage) {
        this.ePackage = ePackage;
        initBuilder();
    }
    
    protected void initBuilder() {
        isPersistent = false;
        EPackage.Registry.INSTANCE.put(ePackage.getNsURI(), ePackage);
        rSet = new ResourceSetImpl();
        resourceOptions = new HashMap<>();
    }
    
    public abstract AbstractResourceBuilder uri(URI uri);
    
    public abstract AbstractResourceBuilder file(File file);
    
    public AbstractResourceBuilder persistent() {
        isPersistent = true;
        return this;
    }
    
    public PersistentResource build() throws IOException {
        PersistentResource resource = (PersistentResource)rSet.createResource(uri);
        if(isPersistent) {
            resource.save(resourceOptions);
        }
        initBuilder();
        return resource;
    }
    
}
