/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.tests;

import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.tests.provider.ContextProvider;
import fr.inria.atlanmod.neoemf.tests.sample.Node;
import fr.inria.atlanmod.neoemf.tests.sample.PhysicalNode;
import fr.inria.atlanmod.neoemf.tests.sample.RemoteNode;
import fr.inria.atlanmod.neoemf.tests.sample.SamplePackage;
import fr.inria.atlanmod.neoemf.tests.sample.Tree;
import fr.inria.atlanmod.neoemf.tests.sample.VirtualNode;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.io.IOException;
import java.util.Map;
import java.util.stream.IntStream;

import javax.annotation.ParametersAreNonnullByDefault;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A test-case about the {@link PersistentResource#allInstancesOf(EClass, boolean)} method.
 */
@ParametersAreNonnullByDefault
class AllInstancesTest extends AbstractResourceBasedTest {

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
     * Checks the content.
     */
    @ParameterizedTest(name = "[{index}] {0}: isPersistent = {1} ; isStrict = {2}")
    @ArgumentsSource(ContextProvider.AllWithBiBooleans.class)
    void testAllInstances(Context context, Boolean isPersistent, Boolean isStrict) throws IOException {
        try (PersistentResource resource = createResource(context, isPersistent)) {
            fillResource(resource);

            if (!isStrict) {
                assertAllInstancesHas(resource, false, NODE_COUNT, PHYSICAL_NODE_COUNT);
            }
            else {
                assertAllInstancesHas(resource, true, NODE_STRICT_COUNT, PHYSICAL_NODE_STRICT_COUNT);
            }
        }
    }

    /**
     * Checks the content after calling {@link PersistentResource#save(Map)} and {@link PersistentResource#load(Map)}.
     */
    @ParameterizedTest(name = "[{index}] {0}: isStrict = {2}")
    @ArgumentsSource(ContextProvider.AllWithBooleans.class)
    void testAllInstancesLoaded(Context context, Boolean isStrict) throws IOException {
        try (PersistentResource resource = createPersistentResource(context)) {
            fillResource(resource);

            resource.save(context.config().toMap());
            resource.unload();
            resource.load(context.config().toMap());

            if (!isStrict) {
                assertAllInstancesHas(resource, false, NODE_COUNT, PHYSICAL_NODE_COUNT);
            }
            else {
                assertAllInstancesHas(resource, true, NODE_STRICT_COUNT, PHYSICAL_NODE_STRICT_COUNT);
            }
        }
    }

    /**
     * Fills the {@code resource}.
     *
     * @param resource the resource to fill
     */
    private void fillResource(Resource resource) {
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
        Iterable<Tree> trees = resource.allInstancesOf(EPACKAGE.getTree(), strict);
        assertThat(trees).hasSize(TREE_COUNT);

        Iterable<Node> abstractNodes = resource.allInstancesOf(EPACKAGE.getNode(), strict);
        assertThat(abstractNodes).hasSize(nodeCount);

        Iterable<PhysicalNode> physicalNodes = resource.allInstancesOf(EPACKAGE.getPhysicalNode(), strict);
        assertThat(physicalNodes).hasSize(physicalNodeCount);

        Iterable<SamplePackage> remoteNodes = resource.allInstancesOf(EPACKAGE.getRemoteNode(), strict);
        assertThat(remoteNodes).hasSize(REMOTE_NODE_COUNT);

        Iterable<VirtualNode> virtualNodes = resource.allInstancesOf(EPACKAGE.getVirtualNode(), strict);
        assertThat(virtualNodes).hasSize(VIRTUAL_NODE_COUNT);
    }
}
