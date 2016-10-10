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
import fr.inria.atlanmod.neoemf.core.PersistentEObject;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.EClassImpl;
import org.eclipse.emf.ecore.util.EContentsEList;
import org.eclipse.emf.ecore.util.InternalEList;

import java.util.List;

public class NeoEContentsEList<E> extends EContentsEList<E> implements EList<E>, InternalEList<E> {

    private final PersistentEObject owner;

    private static final NeoEContentsEList<?> EMPTY_NEO_CONTENTS_ELIST = new EmptyNeoEContentsEList<>();

    @SuppressWarnings("unchecked") // Unchecked cast
    public static <E> NeoEContentsEList<E> emptyNeoContentsEList() {
        return (NeoEContentsEList<E>)EMPTY_NEO_CONTENTS_ELIST;
    }
    
    public static <E> NeoEContentsEList<E> createNeoEContentsEList(EObject eObject) {
        NeoEContentsEList<E> contentEList;
        EStructuralFeature[] eStructuralFeatures =
                ((EClassImpl.FeatureSubsetSupplier) eObject.eClass().getEAllStructuralFeatures()).containments();
        if (eStructuralFeatures == null) {
            contentEList = NeoEContentsEList.emptyNeoContentsEList();
        }
        else {
            contentEList = new NeoEContentsEList<>(eObject, eStructuralFeatures);
        }
        return contentEList;
    }
    
    public NeoEContentsEList(EObject owner) {
        super(owner);
        this.owner = NeoEObjectAdapterFactoryImpl.getAdapter(owner, PersistentEObject.class);
    }
    
    public NeoEContentsEList(EObject owner, EStructuralFeature[] eStructuralFeatures) {
        super(owner,eStructuralFeatures);
        this.owner = NeoEObjectAdapterFactoryImpl.getAdapter(owner, PersistentEObject.class);
    }
    
    @Override
    @SuppressWarnings("unchecked") // Unchecked cast: 'java.lang.Object' to 'E'
    public E get(int index) {
        if(eStructuralFeatures == null) {
            throw new IndexOutOfBoundsException("index=" + index + ",size=0");
        }
        // Find the feature to look for
        int featureSize = 0;
        for (EStructuralFeature eStructuralFeature : eStructuralFeatures) {
            int localFeatureSize = owner.eStore().size(owner, eStructuralFeature);
            featureSize += localFeatureSize;
            if (featureSize > index) {
                // The correct feature has been found
                return (E) owner.eStore().get(owner, eStructuralFeature, (index - (featureSize - localFeatureSize)));
            }
        }
        throw new IndexOutOfBoundsException("index=" + index + ",size=" + featureSize);
    }

    private static class EmptyNeoEContentsEList<T> extends NeoEContentsEList<T> {

        public EmptyNeoEContentsEList() {
            super(null, null);
        }

        @Override
        public List<T> basicList() {
            return this;
        }
    }
}
