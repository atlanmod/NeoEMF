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

import com.google.common.collect.Iterators;

import fr.inria.atlanmod.neoemf.core.PersistentEObject;

import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.compare.CompareFactory;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.MatchResource;
import org.eclipse.emf.compare.match.DefaultMatchEngine;
import org.eclipse.emf.compare.match.IComparisonFactory;
import org.eclipse.emf.compare.match.eobject.IEObjectMatcher;
import org.eclipse.emf.compare.match.resource.IResourceMatcher;
import org.eclipse.emf.compare.match.resource.StrategyResourceMatcher;
import org.eclipse.emf.compare.scope.IComparisonScope;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static fr.inria.atlanmod.neoemf.util.Preconditions.checkArgument;

/**
 * Overrides {@link DefaultMatchEngine} to allow lazy comparison of {@link PersistentEObject}s. Elements are compared
 * using the {@link LazyEqualityHelper} provided in the {@code comparisonFactory} using
 * {@link java.util.Objects#equals(Object, Object)} instead of {@code ==} when at least one of the element is stored in
 * NeoEMF.
 * <p>
 * <b>Note:</b> This class overrides all the {@code match} methods to remove old Guava dependencies. It should be
 * removed if/when Guava dependencies become compatible with NeoEMF.
 *
 * @see LazyMatchEngineFactory
 * @see LazyEqualityHelper
 */
class LazyMatchEngine extends DefaultMatchEngine {

    /**
     * Creates a new LazyMatchEngine.
     *
     * @param matcher           the matcher to use
     * @param comparisonFactory the {@link IComparisonFactory} to use
     */
    public LazyMatchEngine(IEObjectMatcher matcher, IComparisonFactory comparisonFactory) {
        super(matcher, comparisonFactory);
    }

    /**
     * This will check that at least two of the three given booleans are {@code true}.
     *
     * @param condition1 first of the three booleans
     * @param condition2 second of the three booleans
     * @param condition3 third of the three booleans
     *
     * @return {@code true} if at least two of the three given booleans are {@code true}, {@code false} otherwise.
     */
    private static boolean atLeastTwo(boolean condition1, boolean condition2, boolean condition3) {
        return condition1 && (condition2 || condition3) || (condition2 && condition3);
    }

    /**
     * {@inheritDoc}
     * <p>
     * <b>Important Note:</b> This method overrides the super-method to remove incompatible Guava dependencies. It
     * should be removed if/when Guava dependencies become compatible with NeoEMF.
     */
    @Override
    protected void match(Comparison comparison, IComparisonScope scope, ResourceSet left, ResourceSet right, ResourceSet origin, Monitor monitor) {
        Iterator<? extends Resource> leftChildren = scope.getCoveredResources(left);
        Iterator<? extends Resource> rightChildren = scope.getCoveredResources(right);
        Iterator<? extends Resource> originChildren = origin != null ? scope.getCoveredResources(origin) : Collections.emptyIterator();

        IResourceMatcher resourceMatcher = new StrategyResourceMatcher();

        List<Iterator<? extends EObject>> leftIterators = new LinkedList<>();
        List<Iterator<? extends EObject>> rightIterators = new LinkedList<>();
        List<Iterator<? extends EObject>> originIterators = new LinkedList<>();

        resourceMatcher.createMappings(leftChildren, rightChildren, originChildren).forEach(m -> {
            comparison.getMatchedResources().add(m);

            Resource leftRes = m.getLeft();
            Resource rightRes = m.getRight();
            Resource originRes = m.getOrigin();

            if (leftRes != null) {
                leftIterators.add(scope.getCoveredEObjects(leftRes));
            }

            if (rightRes != null) {
                rightIterators.add(scope.getCoveredEObjects(rightRes));
            }

            if (originRes != null) {
                originIterators.add(scope.getCoveredEObjects(originRes));
            }
        });

        Iterator<? extends EObject> leftEObjects = Iterators.concat(leftIterators.iterator());
        Iterator<? extends EObject> rightEObjects = Iterators.concat(rightIterators.iterator());
        Iterator<? extends EObject> originEObjects = Iterators.concat(originIterators.iterator());

        getEObjectMatcher().createMatches(comparison, leftEObjects, rightEObjects, originEObjects, monitor);
    }

    /**
     * {@inheritDoc}
     * <p>
     * <b>Important Note:</b> This method overrides the super-method to remove incompatible Guava dependencies. It
     * should be removed if/when Guava dependencies become compatible with NeoEMF.
     */
    @Override
    protected void match(Comparison comparison, IComparisonScope scope, Resource left, Resource right, Resource origin, Monitor monitor) {
        // Our "roots" are Resources. Consider them matched
        MatchResource match = CompareFactory.eINSTANCE.createMatchResource();

        match.setLeft(left);
        match.setRight(right);
        match.setOrigin(origin);

        if (left != null) {
            URI uri = left.getURI();
            if (uri != null) {
                match.setLeftURI(uri.toString());
            }
        }

        if (right != null) {
            URI uri = right.getURI();
            if (uri != null) {
                match.setRightURI(uri.toString());
            }
        }

        if (origin != null) {
            URI uri = origin.getURI();
            if (uri != null) {
                match.setOriginURI(uri.toString());
            }
        }

        comparison.getMatchedResources().add(match);

        // We need at least two resources to match them
        if (atLeastTwo(left == null, right == null, origin == null)) {
            // TODO But if we have only one resource, which is then unmatched, should we not still do something with it?
            return;
        }

        Iterator<? extends EObject> leftEObjects = left != null ? scope.getCoveredEObjects(left) : Collections.emptyIterator();
        Iterator<? extends EObject> rightEObjects = right != null ? scope.getCoveredEObjects(right) : Collections.emptyIterator();
        Iterator<? extends EObject> originEObjects = origin != null ? scope.getCoveredEObjects(origin) : Collections.emptyIterator();

        getEObjectMatcher().createMatches(comparison, leftEObjects, rightEObjects, originEObjects, monitor);
    }

    /**
     * {@inheritDoc}
     * <p>
     * <b>Important Note:</b> This method overrides the super-method to remove incompatible Guava dependencies. It
     * should be removed if/when Guava dependencies become compatible with NeoEMF.
     */
    @Override
    protected void match(Comparison comparison, IComparisonScope scope, EObject left, EObject right, EObject origin, Monitor monitor) {
        checkArgument(left != null && right != null);

        Iterator<? extends EObject> leftEObjects = Iterators.concat(Iterators.singletonIterator(left), scope.getChildren(left));
        Iterator<? extends EObject> rightEObjects = Iterators.concat(Iterators.singletonIterator(right), scope.getChildren(right));
        Iterator<? extends EObject> originEObjects;

        if (origin != null) {
            originEObjects = Iterators.concat(Iterators.singletonIterator(origin), scope.getChildren(origin));
        }
        else {
            originEObjects = Collections.emptyIterator();
        }

        getEObjectMatcher().createMatches(comparison, leftEObjects, rightEObjects, originEObjects, monitor);
    }
}
