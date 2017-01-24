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
package fr.inria.atlanmod.neoemf.util.emf.compare;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.compare.CompareFactory;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.MatchResource;
import org.eclipse.emf.compare.match.DefaultMatchEngine;
import org.eclipse.emf.compare.match.IComparisonFactory;
import org.eclipse.emf.compare.match.eobject.IEObjectMatcher;
import org.eclipse.emf.compare.match.resource.IResourceMatcher;
import org.eclipse.emf.compare.scope.IComparisonScope;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;

public class LazyMatchEngine extends DefaultMatchEngine {

    /**
     * Creates a new LazyMatchEngine.
     * @param matcher the matcher to use
     * @param comparisonFactory the {@link IComparisonFactory} to use
     */
    public LazyMatchEngine(IEObjectMatcher matcher, IComparisonFactory comparisonFactory) {
        super(matcher, comparisonFactory);
    }
    
    @Override
    protected void match(Comparison comparison, IComparisonScope scope, ResourceSet left,
            ResourceSet right, ResourceSet origin, Monitor monitor) {
        final Iterator<? extends Resource> leftChildren = scope.getCoveredResources(left);
        final Iterator<? extends Resource> rightChildren = scope.getCoveredResources(right);
        final Iterator<? extends Resource> originChildren;
        if (origin != null) {
            originChildren = scope.getCoveredResources(origin);
        } else {
            originChildren = Collections.emptyIterator();
        }

        final IResourceMatcher resourceMatcher = createResourceMatcher();
        final Iterable<MatchResource> mappings = resourceMatcher.createMappings(leftChildren, rightChildren,
                originChildren);

        final List<Iterator<? extends EObject>> leftIterators = Lists.newLinkedList();
        final List<Iterator<? extends EObject>> rightIterators = Lists.newLinkedList();
        final List<Iterator<? extends EObject>> originIterators = Lists.newLinkedList();

        for (MatchResource mapping : mappings) {
            comparison.getMatchedResources().add(mapping);

            final Resource leftRes = mapping.getLeft();
            final Resource rightRes = mapping.getRight();
            final Resource originRes = mapping.getOrigin();

            if (leftRes != null) {
                leftIterators.add(scope.getCoveredEObjects(leftRes));
            }

            if (rightRes != null) {
                rightIterators.add(scope.getCoveredEObjects(rightRes));
            }

            if (originRes != null) {
                originIterators.add(scope.getCoveredEObjects(originRes));
            }
        }

        final Iterator<? extends EObject> leftEObjects = Iterators.concat(leftIterators.iterator());
        final Iterator<? extends EObject> rightEObjects = Iterators.concat(rightIterators.iterator());
        final Iterator<? extends EObject> originEObjects = Iterators.concat(originIterators.iterator());

        getEObjectMatcher().createMatches(comparison, leftEObjects, rightEObjects, originEObjects, monitor);
    }
    
    @Override
    protected void match(Comparison comparison, IComparisonScope scope, EObject left,
            EObject right, EObject origin, Monitor monitor) {
        if (left == null || right == null) {
            throw new IllegalArgumentException();
        }

        final Iterator<? extends EObject> leftEObjects = Iterators.concat(Iterators.singletonIterator(left),
                scope.getChildren(left));
        final Iterator<? extends EObject> rightEObjects = Iterators.concat(
                Iterators.singletonIterator(right), scope.getChildren(right));
        final Iterator<? extends EObject> originEObjects;
        if (origin != null) {
            originEObjects = Iterators.concat(Iterators.singletonIterator(origin), scope.getChildren(origin));
        } else {
            originEObjects = Collections.emptyIterator();
        }

        getEObjectMatcher().createMatches(comparison, leftEObjects, rightEObjects, originEObjects, monitor);
    }
    
    @Override
    protected void match(Comparison comparison, IComparisonScope scope, Resource left,
            Resource right, Resource origin, Monitor monitor) {
        // Our "roots" are Resources. Consider them matched
        final MatchResource match = CompareFactory.eINSTANCE.createMatchResource();

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
            /*
             * TODO But if we have only one resource, which is then unmatched, should we not still do
             * something with it?
             */
            return;
        }

        final Iterator<? extends EObject> leftEObjects;
        if (left != null) {
            leftEObjects = scope.getCoveredEObjects(left);
        } else {
            leftEObjects = Collections.emptyIterator();
        }
        final Iterator<? extends EObject> rightEObjects;
        if (right != null) {
            rightEObjects = scope.getCoveredEObjects(right);
        } else {
            rightEObjects = Collections.emptyIterator();
        }
        final Iterator<? extends EObject> originEObjects;
        if (origin != null) {
            originEObjects = scope.getCoveredEObjects(origin);
        } else {
            originEObjects = Collections.emptyIterator();
        }

        getEObjectMatcher().createMatches(comparison, leftEObjects, rightEObjects, originEObjects, monitor);
    }
    
    /**
     * This will check that at least two of the three given booleans are <code>true</code>.
     * 
     * @param condition1
     *            First of the three booleans.
     * @param condition2
     *            Second of the three booleans.
     * @param condition3
     *            Third of the three booleans.
     * @return <code>true</code> if at least two of the three given booleans are <code>true</code>,
     *         <code>false</code> otherwise.
     */
    private static boolean atLeastTwo(boolean condition1, boolean condition2, boolean condition3) {
        return condition1 && (condition2 || condition3) || (condition2 && condition3);
    }
    
}
