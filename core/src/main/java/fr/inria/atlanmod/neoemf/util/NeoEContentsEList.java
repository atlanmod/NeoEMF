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

package fr.inria.atlanmod.neoemf.util;

import fr.inria.atlanmod.neoemf.core.PersistentEObject;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.EClassImpl;
import org.eclipse.emf.ecore.util.EContentsEList;
import org.eclipse.emf.ecore.util.InternalEList;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Objects.isNull;

public class NeoEContentsEList<E> extends EContentsEList<E> implements EList<E>, InternalEList<E> {

    private static final NeoEContentsEList<?> EMPTY_NEO_CONTENTS_ELIST = new EmptyNeoEContentsEList<>();

    private final PersistentEObject owner;

    public NeoEContentsEList(EObject owner) {
        super(owner);
        this.owner = PersistentEObject.from(owner);
    }

    public NeoEContentsEList(EObject owner, EStructuralFeature[] eStructuralFeatures) {
        super(owner, eStructuralFeatures);
        this.owner = PersistentEObject.from(owner);
    }

    @SuppressWarnings("unchecked") // Unchecked cast: 'NeoEContentsEList<?>' to 'NeoEContentsEList<...>'
    public static <E> NeoEContentsEList<E> emptyNeoContentsEList() {
        return (NeoEContentsEList<E>) EMPTY_NEO_CONTENTS_ELIST;
    }

    public static <E> NeoEContentsEList<E> createNeoEContentsEList(EObject eObject) {
        NeoEContentsEList<E> contentEList;
        EStructuralFeature[] eStructuralFeatures =
                ((EClassImpl.FeatureSubsetSupplier) eObject.eClass().getEAllStructuralFeatures()).containments();
        if (isNull(eStructuralFeatures)) {
            contentEList = NeoEContentsEList.emptyNeoContentsEList();
        }
        else {
            contentEList = new NeoEContentsEList<>(eObject, eStructuralFeatures);
        }
        return contentEList;
    }

    @Override
    @SuppressWarnings("unchecked") // Unchecked cast: 'Object' to 'E'
    public E get(int index) {
        checkNotNull(eStructuralFeatures, "index=" + index + ", size=0");
        // Find the feature to look for
        int featureSize = 0;
        for (EStructuralFeature eStructuralFeature : eStructuralFeatures) {
            int localFeatureSize;
            if (eStructuralFeature.isMany()) {
                localFeatureSize = owner.eStore().size(owner, eStructuralFeature);
            }
            else {
                localFeatureSize = owner.eStore().isSet(owner, eStructuralFeature) ? 1 : 0;
            }
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
