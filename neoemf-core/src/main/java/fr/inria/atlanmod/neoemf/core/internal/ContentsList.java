/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.core.internal;

import fr.inria.atlanmod.neoemf.core.PersistentEObject;
import fr.inria.atlanmod.neoemf.data.store.Store;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.EClassImpl;
import org.eclipse.emf.ecore.util.EContentsEList;

import java.util.List;
import java.util.ListIterator;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.ParametersAreNullableByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;
import static java.util.Objects.nonNull;

/**
 * A {@link List} that delegates its operations to the associated {@link Store}.
 * <p>
 * Instances of this class are created by {@link PersistentResource#getContents()} and allows to access the content of a
 * {@link PersistentResource} by lazily loading the elements.
 *
 * @param <E> the type of elements in this list
 */
@ParametersAreNonnullByDefault
// TODO Reimplements `iterator()` and `listIterator()` to use batch methods
public class ContentsList<E> extends EContentsEList<E> {

    /**
     * The instance of an empty {@code ContentsList}.
     */
    @Nonnull
    private static final ContentsList<?> EMPTY = new EmptyContentsList<>();

    /**
     * The owner of this list.
     */
    @Nonnull
    private final PersistentEObject owner;

    /**
     * Constructs a new {@code ContentsList}.
     *
     * @param owner    the owner of this list
     * @param features the containment features that are handled by this list
     */
    protected ContentsList(PersistentEObject owner, EStructuralFeature[] features) {
        super(owner, features);
        this.owner = owner;
    }

    /**
     * Returns an empty {@code ContentsList}.
     *
     * @param <E> the type of elements in this list
     *
     * @return an empty list
     */
    @Nonnull
    @SuppressWarnings("unchecked")
    public static <E> ContentsList<E> empty() {
        return (ContentsList<E>) EMPTY;
    }

    /**
     * Creates a new {@code ContentsList} with the given {@code owner}.
     *
     * @param owner the owner of this list
     * @param <E>   the type of elements in this list
     *
     * @return a new list
     */
    @Nonnull
    public static <E> ContentsList<E> newList(PersistentEObject owner) {
        EStructuralFeature[] containments =
                EClassImpl.FeatureSubsetSupplier.class.cast(owner.eClass()
                        .getEAllStructuralFeatures())
                        .containments();

        return nonNull(containments)
                ? new ContentsList<>(owner, containments)
                : ContentsList.empty();
    }

    @Override
    @SuppressWarnings("unchecked")
    public E get(int index) {
        checkNotNull(eStructuralFeatures, "index=" + index + ", size=0");

        // Find the feature to look for
        int featureSize = 0;

        for (EStructuralFeature feature : eStructuralFeatures) {
            int localFeatureSize = feature.isMany()
                    ? owner.eStore().size(owner, feature)
                    : owner.eStore().isSet(owner, feature) ? 1 : 0;

            featureSize += localFeatureSize;

            if (featureSize > index) {
                // The correct feature has been found
                int featureIndex = index - (featureSize - localFeatureSize);
                return (E) owner.eStore().get(owner, feature, featureIndex);
            }
        }

        throw new IndexOutOfBoundsException("index=" + index + ",size=" + featureSize);
    }

    @Override
    protected ListIterator<E> newResolvingListIterator() {
        return new FeatureListIteratorResolver<>(owner, eStructuralFeatures);
    }

    @Override
    protected ListIterator<E> newNonResolvingListIterator() {
        return new FeatureListIterator<>(owner, eStructuralFeatures);
    }

    @Override
    protected final boolean useIsSet() {
        return false;
    }

    /**
     * An empty {@code ContentsList}.
     *
     * @param <E> the type of elements in this list
     */
    @ParametersAreNullableByDefault
    private static class EmptyContentsList<E> extends ContentsList<E> {

        /**
         * Constructs a new {@code EmptyContentsList}.
         */
        @SuppressWarnings("ConstantConditions")
        public EmptyContentsList() {
            super(null, null);
        }

        @Override
        public List<E> basicList() {
            return this;
        }
    }

    /**
     * A {@link ListIterator} that iterates over all the content of a {@link PersistentEObject}.
     *
     * @param <E> the type of elements in this iterator
     *
     * @see PersistentEObject#eContents()
     * @see PersistentEObject#eAllContents()
     */
    @ParametersAreNonnullByDefault
    private static class FeatureListIterator<E> extends FeatureIteratorImpl<E> {

        /**
         * Constructs a new {@code FeatureListIterator} for the given {@code owner}.
         *
         * @param owner    the owner of this iterator
         * @param features the containment features that are handled by this iterator
         */
        public FeatureListIterator(EObject owner, EStructuralFeature[] features) {
            super(owner, features);
        }

        @Override
        protected final boolean useIsSet() {
            return false;
        }
    }

    /**
     * A {@link FeatureListIterator} that resolves proxies.
     *
     * @param <E> the type of elements in this iterator
     */
    @ParametersAreNonnullByDefault
    private static class FeatureListIteratorResolver<E> extends FeatureListIterator<E> {

        /**
         * Constructs a new {@code FeatureListIteratorResolver} for the given {@code owner}.
         *
         * @param owner    the owner of this iterator
         * @param features the containment features that are handled by this iterator
         */
        public FeatureListIteratorResolver(EObject owner, EStructuralFeature[] features) {
            super(owner, features);
        }

        @Override
        protected boolean resolve() {
            return true;
        }
    }
}
