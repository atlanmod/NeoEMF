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

import fr.inria.atlanmod.neoemf.context.Context;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.tests.context.ContextProvider;
import fr.inria.atlanmod.neoemf.tests.sample.Comment;
import fr.inria.atlanmod.neoemf.tests.sample.Node;
import fr.inria.atlanmod.neoemf.tests.sample.Tree;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import javax.annotation.ParametersAreNonnullByDefault;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * A test-case that checks that adding a transient containment sub-tree to an existing {@link PersistentResource} add
 * all its elements to the {@link org.eclipse.emf.ecore.resource.Resource}.
 */
@ParametersAreNonnullByDefault
public class ContainmentTest extends AbstractResourceBasedTest {

    @ParameterizedTest
    @ArgumentsSource(ContextProvider.All.class)
    public void testContainment(Context context) throws Exception {
        try (PersistentResource resource = createPersistentResource(context)) {
            Tree tree0 = EFACTORY.createTree();
            tree0.setName("Tree0");

            resource.getContents().add(tree0);

            assertThat(tree0.resource()).isSameAs(resource);
            assertThat(tree0.eInternalContainer()).isNull();

            Tree tree1 = EFACTORY.createTree();
            tree1.setName("Tree1");

            Node node0 = EFACTORY.createLocalNode();

            Tree tree2 = EFACTORY.createTree();
            tree2.setName("Tree2");

            tree1.getChildren().add(tree2);

            assertThat(tree2.eInternalContainer()).isEqualTo(tree1);

            node0.setLabel("PhysicalNode0");

            tree2.getNodes().add(node0);

            assertThat(node0.eInternalContainer()).isEqualTo(tree2);

            tree0.getChildren().add(tree1);

            assertThat(tree1.eInternalContainer()).isEqualTo(tree0);

            assertThat(resource.getAllContents()).hasSize(4);

            assertThat(tree0.resource()).isSameAs(resource);
            assertThat(tree1.resource()).isSameAs(resource);
            assertThat(tree2.resource()).isSameAs(resource);
            assertThat(node0.resource()).isSameAs(resource);
        }
    }

    @ParameterizedTest
    @ArgumentsSource(ContextProvider.All.class)
    public void testContainmentAfterNonContainment(Context context) throws Exception {
        try (PersistentResource resource = createPersistentResource(context)) {
            Tree tree0 = EFACTORY.createTree();
            tree0.setName("Tree0");

            resource.getContents().add(tree0);

            assertThat(tree0.resource()).isSameAs(resource);
            assertThat(tree0.eInternalContainer()).isNull();

            Tree tree1 = EFACTORY.createTree();
            tree1.setName("Tree1");

            tree0.getChildren().add(tree1);

            Node node0 = EFACTORY.createLocalNode();
            node0.setLabel("PhysicalNode0");

            tree1.getNodes().add(node0);

            Comment comment0 = EFACTORY.createComment();
            comment0.setContent("Comment1");

            // Add using the non-containment reference
            tree1.getManyReference().add(comment0);

            // Then add the element to the resource tree using the containment reference
            node0.getManyContainmentReference().add(comment0);

            assertThat(comment0.resource()).isSameAs(resource);

            // Check that the element has a container (it cannot be in the resource if it does not)
            assertThat(comment0.eInternalContainer()).isEqualTo(node0);

            // Check that the element is in the containment reference list of its parent
            assertThat(node0.getManyContainmentReference()).contains(comment0);

            // Check everything is accessible from the resource
            assertThat(resource.getAllContents()).hasSize(4);
        }
    }
}
