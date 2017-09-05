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

package fr.inria.atlanmod.neoemf.tests;

import fr.inria.atlanmod.neoemf.Tags;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.tests.sample.Node;
import fr.inria.atlanmod.neoemf.tests.sample.Tree;

import org.eclipse.emf.ecore.EObject;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

/**
 * A test-case for the contains method, related to performance issue descibed in #30 <a
 * href="https://github.com/atlanmod/NeoEMF/issues/30">https://github.com/atlanmod/NeoEMF/issues/30</a>
 */
public class EContentTest extends AbstractBackendTest {

    /**
     * The expected number of {@link Tree} contained in {@link #tree}.
     */
    private static final int TREES_COUNT = 5;

    /**
     * The expected number of {@link Node} contained in {@link #tree}.
     */
    private static final int NODES_COUNT = 3;

    /**
     * The root {@link Tree}.
     */
    private Tree tree;

    /**
     * The {@link Tree}s contained in {@link #tree}.
     */
    private List<Tree> treeChildren;

    /**
     * The owned content of {@link #tree}.
     */
    private List<Node> treeNodes;

    @Test
    @Category(Tags.PersistentTests.class)
    public void testEObjectEContents() {
        PersistentResource resource = newPersistentStore();
        fillResource(resource);

        List<EObject> eContents = tree.eContents();
        assertThat(eContents).hasSize(TREES_COUNT + NODES_COUNT);

        IntStream.range(0, NODES_COUNT).forEach(i ->
                assertThat(eContents.get(i)).isEqualTo(treeNodes.get(i))
        );

        IntStream.range(0, TREES_COUNT).forEach(i ->
                assertThat(eContents.get(i + NODES_COUNT)).isEqualTo(treeChildren.get(i))
        );
    }

    @Test
    @Category(Tags.PersistentTests.class)
    public void testEObjectEmptyEContentsSize() {
        PersistentResource resource = newPersistentStore();
        fillResourceWithEmpty(resource);

        List<EObject> eContents = tree.eContents();
        assertThat(eContents).isEmpty();
    }

    @Test
    @Category(Tags.PersistentTests.class)
    public void testEObjectEmptyEContentsGet() {
        PersistentResource resource = newPersistentStore();
        fillResourceWithEmpty(resource);

        assertThat(catchThrowable(() -> tree.eContents().get(0))).isInstanceOf(IndexOutOfBoundsException.class);
    }

    /**
     * Fills the {@code resource}.
     *
     * @param resource the resource to fill
     */
    private void fillResource(PersistentResource resource) {
        treeChildren = new ArrayList<>();
        treeNodes = new ArrayList<>();

        Tree rootTree = EFACTORY.createTree();
        rootTree.setName("RootTree");

        this.tree = EFACTORY.createTree();
        this.tree.setName("Tree0");
        this.tree.setParent(rootTree);

        IntStream.range(0, NODES_COUNT).forEach(i -> {
            Node node = EFACTORY.createPhysicalNode();
            node.setLabel("Node0-" + i);
            this.tree.getNodes().add(node);
            treeNodes.add(node);
        });

        IntStream.range(0, TREES_COUNT).forEach(i -> {
            Tree subTree = EFACTORY.createTree();
            subTree.setName("Tree0-" + i);
            this.tree.getChildren().add(subTree);
            treeChildren.add(subTree);
        });

        resource.getContents().add(this.tree);
    }

    /**
     * Fills the {@code resource} with an empty {@link Tree}.
     *
     * @param resource the resource to fill
     */
    private void fillResourceWithEmpty(PersistentResource resource) {
        tree = EFACTORY.createTree();
        tree.setName("EmptyTree");
        resource.getContents().add(tree);
    }
}
