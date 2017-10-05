/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.util.compare;

import com.google.common.cache.LoadingCache;

import fr.inria.atlanmod.neoemf.core.PersistentEObject;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.compare.Match;
import org.eclipse.emf.compare.utils.EqualityHelper;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * An {@link EqualityHelper} subclass that takes into account NeoEMF lazy-loading.
 * <p>
 * Superclass' implementation assumes {@code eGet()} methods always return the same instance. NeoEMF lazy-loading
 * doesn't ensure this, in particular when internal caches are full, and stored elements are discarded and have to be
 * fetched again from the database. This class overcomes these limitations by using {@link Objects#equals(Object,
 * Object)} instead of raw equality.
 * <p>
 * If this class is used with models that are not stored in NeoEMF the comparison is computed in a standard way using
 * {@code ==}.
 * <p>
 * <b>Note:</b> This implementation is slower than its parent because comparisons involving at least one {@link
 * PersistentEObject} are computed using {@link Objects#equals(Object, Object)} which may imply some data query process
 * to compute the object identifier.
 *
 * @see LazyEqualityHelperFactory
 */
class LazyEqualityHelper extends EqualityHelper {

    /**
     * Creates a new {@code LazyEqualityHelper} with the given {@code cache}.
     *
     * @param uriCache the cache to be used for {@link EcoreUtil#getURI(EObject)} calls
     */
    public LazyEqualityHelper(LoadingCache<EObject, URI> uriCache) {
        super(uriCache);
    }

    /**
     * {@inheritDoc}
     * <p>
     * This method extends the default parent behavior by calling {@link Objects#equals(Object, Object)} if at least one
     * of the compared element is an instance of {@link PersistentEObject}. Calling equals instead of {@code ==} allows
     * to compare NeoEMF objects that have been potentially reloading between the <i>match</i> and <i>diff</i> phases.
     * <p>
     * If the comparison doesn't involve a {@link PersistentEObject} it is computed in a standard way using {@code ==}.
     */
    @Override
    protected boolean matchingEObjects(EObject object1, EObject object2) {
        Match match = getMatch(object1);

        if (nonNull(match)) {
            List<EObject> branches = Arrays.asList(
                    match.getOrigin(),
                    match.getLeft(),
                    match.getRight());

            if (PersistentEObject.class.isInstance(object2) || branches.stream().anyMatch(PersistentEObject.class::isInstance)) {
                return branches.stream().anyMatch(o -> Objects.equals(o, object2));
            }
            else {
                return branches.stream().anyMatch(o -> o == object2);
            }
        }
        else {
            return isNull(getTarget().getMatch(object2))
                    && object1.eClass() == object2.eClass()
                    && matchingURIs(object1, object2);
        }
    }
}
