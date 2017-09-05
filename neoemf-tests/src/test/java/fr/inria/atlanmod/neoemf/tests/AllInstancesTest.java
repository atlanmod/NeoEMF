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
import fr.inria.atlanmod.neoemf.tests.sample.PhysicalNode;
import fr.inria.atlanmod.neoemf.tests.sample.RemoteNode;
import fr.inria.atlanmod.neoemf.tests.sample.Tree;
import fr.inria.atlanmod.neoemf.tests.sample.VirtualNode;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.IOException;
import java.util.Map;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A test-case about the {@link PersistentResource#allInstancesOf(EClass, boolean)} method.
 */
public class AllInstancesTest extends AbstractBackendTest {

    /**
     * The expected number of {@link Tree} in the resulting list.
     */
    private static final int TREE_COUNT = 6;

    /**
     * The expected number of {@link Node} in the resulting list.
     */
    private static final int NODE_COUNT = 150;

    /**
     * The expected number of {@link PhysicalNode} in the resulting list.
     */
    private static final int PHYSICAL_NODE_COUNT = 100;

    /**
     * The expected number of {@link RemoteNode} in the resulting list.
     */
    private static final int REMOTE_NODE_COUNT = 50;

    /**
     * The expected number of {@link VirtualNode} in the resulting list.
     */
    private static final int VIRTUAL_NODE_COUNT = 50;

    /**
     * The expected number of {@link Node} in the resulting list in a {@code strict} mode.
     */
    private static final int NODE_STRICT_COUNT = 0;

    /**
     * The expected number of {@link PhysicalNode} in the resulting list in a {@code strict} mode.
     */
    private static final int PHYSICAL_NODE_STRICT_COUNT = 50;

    /**
     * Checks the content with a persistent store, and {@code strict = false}.
     */
    @Test
    @Category(Tags.PersistentTests.class)
    public void testAllInstancesPersistent() {
        PersistentResource resource = newPersistentStore();
        fillResource(resource);

        assertAllInstancesHas(resource, false, NODE_COUNT, PHYSICAL_NODE_COUNT);
    }

    /**
     * Checks the content with a persistent store, and {@code strict = true}.
     */
    @Test
    @Category(Tags.PersistentTests.class)
    public void testAllInstancesStrictPersistent() {
        PersistentResource resource = newPersistentStore();
        fillResource(resource);

        assertAllInstancesHas(resource, true, NODE_STRICT_COUNT, PHYSICAL_NODE_STRICT_COUNT);
    }

    /**
     * Checks the content after calling {@link PersistentResource#save(Map)} and {@link PersistentResource#load(Map)},
     * with {@code strict = false}.
     *
     * @throws IOException if a I/O error occurs during {@link PersistentResource#save(Map)} or {@link
     *                     PersistentResource#load(Map)}
     */
    @Test
    @Category(Tags.PersistentTests.class)
    public void testAllInstancesPersistentLoaded() throws IOException {
        PersistentResource resource = newPersistentStore();
        fillResource(resource);

        resource.save(context().optionsBuilder().asMap());
        resource.close();
        resource.load(context().optionsBuilder().asMap());

        assertAllInstancesHas(resource, false, NODE_COUNT, PHYSICAL_NODE_COUNT);
    }

    /**
     * Checks the content after calling {@link PersistentResource#save(Map)} and {@link PersistentResource#load(Map)},
     * with {@code strict = true}.
     *
     * @throws IOException if a I/O error occurs during {@link PersistentResource#save(Map)} or {@link
     *                     PersistentResource#load(Map)}
     */
    @Test
    @Category(Tags.PersistentTests.class)
    public void testAllInstancesStrictPersistentLoaded() throws IOException {
        PersistentResource resource = newPersistentStore();
        fillResource(resource);

        resource.save(context().optionsBuilder().asMap());
        resource.close();
        resource.load(context().optionsBuilder().asMap());

        assertAllInstancesHas(resource, true, NODE_STRICT_COUNT, PHYSICAL_NODE_STRICT_COUNT);
    }

    /**
     * Checks the content with a transient store, and {@code strict = false}.
     */
    @Test
    @Category(Tags.TransientTests.class)
    public void testAllInstancesTransient() {
        PersistentResource resource = newTransientStore();
        fillResource(resource);

        assertAllInstancesHas(resource, false, NODE_COUNT, PHYSICAL_NODE_COUNT);
    }

    /**
     * Checks the content with a transient store, and {@code strict = true}.
     */
    @Test
    @Category(Tags.TransientTests.class)
    public void testAllInstancesStrictTransient() {
        PersistentResource resource = newTransientStore();
        fillResource(resource);

        assertAllInstancesHas(resource, true, NODE_STRICT_COUNT, PHYSICAL_NODE_STRICT_COUNT);
    }

    /**
     * Fills the {@code resource}.
     *
     * @param resource the resource to fill
     */
    private void fillResource(PersistentResource resource) {
        Tree rootTree = EFACTORY.createTree();
        rootTree.setName("RootTree");

        IntStream.range(0, 5).forEach(i -> {
            Tree tree = EFACTORY.createTree();
            tree.setName("Tree" + i);
            rootTree.getChildren().add(tree);

            IntStream.range(0, 10).forEach(j -> {
                Node physicalNode = EFACTORY.createPhysicalNode();
                physicalNode.setLabel("Physical" + i + '-' + j);
                tree.getNodes().add(physicalNode);

                Node remoteNode = EFACTORY.createRemoteNode();
                remoteNode.setLabel("Remote" + i + '-' + j);
                tree.getNodes().add(remoteNode);

                Node virtualNode = EFACTORY.createVirtualNode();
                virtualNode.setLabel("Virtual" + i + '-' + j);
                tree.getNodes().add(virtualNode);
            });
        });
        resource.getContents().add(rootTree);
    }

    /**
     * Assert that the {@code resource} has the expected number of each element after calling {@link
     * PersistentResource#allInstancesOf(EClass, boolean)}.
     *
     * @param resource          the resource to test
     * @param strict            {@code true} if the lookup searches for strict instances
     * @param nodeCount         the expected number of {@link Node}
     * @param physicalNodeCount the expected number of {@link PhysicalNode}
     */
    private void assertAllInstancesHas(PersistentResource resource, boolean strict, int nodeCount, int physicalNodeCount) {
        Iterable<EObject> trees = resource.allInstancesOf(EPACKAGE.getTree(), strict);
        assertThat(trees).hasSize(TREE_COUNT);

        Iterable<EObject> abstractNodes = resource.allInstancesOf(EPACKAGE.getNode(), strict);
        assertThat(abstractNodes).hasSize(nodeCount);

        Iterable<EObject> physicalNodes = resource.allInstancesOf(EPACKAGE.getPhysicalNode(), strict);
        assertThat(physicalNodes).hasSize(physicalNodeCount);

        Iterable<EObject> remoteNodes = resource.allInstancesOf(EPACKAGE.getRemoteNode(), strict);
        assertThat(remoteNodes).hasSize(REMOTE_NODE_COUNT);

        Iterable<EObject> virtualNodes = resource.allInstancesOf(EPACKAGE.getVirtualNode(), strict);
        assertThat(virtualNodes).hasSize(VIRTUAL_NODE_COUNT);
    }
}
