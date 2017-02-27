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

package fr.inria.atlanmod.neoemf.util.emf.compare;

import com.google.common.base.Objects;
import com.google.common.cache.LoadingCache;

import fr.inria.atlanmod.neoemf.core.PersistentEObject;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.compare.Match;
import org.eclipse.emf.compare.utils.EqualityHelper;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * An {@link EqualityHelper} subclass that takes into account NeoEMF lazy-loading.
 * <p>
 * Superclass' implementation assumes {@code eGet()} methods always return the same instance. NeoEMF lazy-loading
 * doesn't ensure this, in particular when internal caches are full, and stored elements are discarded and have to be
 * fetched again from the database. This class overcomes these limitations by using
 * {@link Objects#equal(Object, Object)} instead of raw equality.
 * <p>
 * If this class is used with models that are not stored in NeoEMF the comparison is computed in a standard way using
 * {@code ==}.
 * <p>
 * <b>Note: </b> This implementation is slower than its parent because comparisons involving at least one
 * {@link PersistentEObject} are computed using {@code equals} which may imply some data query process to compute the
 * object identifier.
 *
 * @see LazyEqualityHelperFactory
 */
class LazyEqualityHelper extends EqualityHelper {

    /**
     * Creates a new LazyEqualityHelper with the given cache.
     *
     * @param uriCache the cache to be used for {@link EcoreUtil#getURI(EObject)} calls
     */
    public LazyEqualityHelper(LoadingCache<EObject, URI> uriCache) {
        super(uriCache);
    }

    /**
     * {@inheritDoc}
     * <p>
     * This method extends the default parent behavior by calling {@link Objects#equal(Object, Object)} if at least one
     * of the compared element is an instance of {@link PersistentEObject}. Calling equals instead of {@code ==} allows
     * to compare NeoEMF objects that have been potentially reloading between the <i>match</i> and <i>diff</i> phases.
     * <p>
     * If the comparison doesn't involve a {@link PersistentEObject} it is computed in a standard way using {@code ==}.
     */
    @Override
    protected boolean matchingEObjects(EObject object1, EObject object2) {
        // Cannot call super: it would take some additional time to get the match two times
        final Match match = getMatch(object1);
        final boolean equal;

        if (match != null) {
            if (object2 instanceof PersistentEObject || match.getLeft() instanceof PersistentEObject || match.getRight() instanceof PersistentEObject || match.getOrigin() instanceof PersistentEObject) {
                equal = Objects.equal(match.getLeft(), object2) || Objects.equal(match.getRight(), object2) || Objects.equal(match.getOrigin(), object2);
            }
            else {
                equal = match.getLeft() == object2 || match.getRight() == object2 || match.getOrigin() == object2;
            }
        }
        else {
            equal = getTarget().getMatch(object2) == null && object1.eClass() == object2.eClass() && matchingURIs(object1, object2);
        }
        return equal;
    }
}
