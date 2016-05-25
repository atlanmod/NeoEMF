/*
 * Copyright (c) 2013 Atlanmod INRIA LINA Mines Nantes.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Atlanmod INRIA LINA Mines Nantes - initial API and implementation
 */

package fr.inria.atlanmod.neoemf.util;

import fr.inria.atlanmod.neoemf.core.impl.NeoEObjectAdapterFactoryImpl;
import fr.inria.atlanmod.neoemf.datastore.InternalPersistentEObject;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.EClassImpl;
import org.eclipse.emf.ecore.util.EContentsEList;
import org.eclipse.emf.ecore.util.InternalEList;

import java.util.List;

public class NeoEContentsEList<E> extends EContentsEList<E> implements EList<E>, InternalEList<E> {
    
    private InternalPersistentEObject owner;
    
    public static final NeoEContentsEList<?> EMPTY_NEO_CONTENTS_ELIST = 
        new NeoEContentsEList<Object>(null, (EStructuralFeature[])null)
        {
          @Override
          public List<Object> basicList()
          {
            return this;
          }
        };

    @SuppressWarnings("unchecked") // Unchecked cast
    public static <T> NeoEContentsEList<T> emptyNeoContentsEList() {
        return (NeoEContentsEList<T>)EMPTY_NEO_CONTENTS_ELIST;
    }
    
    public static <T> NeoEContentsEList<T> createNeoEContentsEList(EObject eObject) {
        EStructuralFeature[] eStructuralFeatures = ((EClassImpl.FeatureSubsetSupplier) eObject
                .eClass().getEAllStructuralFeatures()).containments();
        return eStructuralFeatures == null ? NeoEContentsEList.<T> emptyNeoContentsEList()
                : new NeoEContentsEList<T>(eObject,eStructuralFeatures);
    }
    
    public NeoEContentsEList(EObject owner) {
        super(owner);
        this.owner = NeoEObjectAdapterFactoryImpl.getAdapter(owner, InternalPersistentEObject.class);
    }
    
    public NeoEContentsEList(EObject owner, EStructuralFeature[] eStructuralFeatures) {
        super(owner,eStructuralFeatures);
        this.owner = NeoEObjectAdapterFactoryImpl.getAdapter(owner, InternalPersistentEObject.class);
    }
    
    @Override
    @SuppressWarnings("unchecked") // Unchecked cast: 'java.lang.Object' to 'E'
    public E get(int index) {
        if(eStructuralFeatures == null) {
            throw new IndexOutOfBoundsException("index=" + index + ",size=0");
        }
        // Find the feature to look for
        int featureSize = 0;
        for(int i = 0; i < eStructuralFeatures.length; i++) {
            int localFeatureSize = owner.eStore().size(owner, eStructuralFeatures[i]);
            featureSize += localFeatureSize;
            if(featureSize > index) {
                // The correct feature has been found
                return (E)owner.eStore().get(owner, eStructuralFeatures[i], (index-(featureSize-localFeatureSize)));
            }
        }
        throw new IndexOutOfBoundsException("index=" + index + ",size=" + featureSize);
    }
    
}
