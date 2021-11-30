/*
 * Copyright (c) 2013 Atlanmod.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.blueprints;

import com.tinkerpop.blueprints.Element;
import com.tinkerpop.blueprints.Graph;
import com.tinkerpop.blueprints.KeyIndexableGraph;
import com.tinkerpop.blueprints.util.ElementHelper;
import com.tinkerpop.blueprints.util.GraphHelper;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.AbstractBackend;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;
import fr.inria.atlanmod.neoemf.data.blueprints.internal.graph.ClassVertex;
import fr.inria.atlanmod.neoemf.data.blueprints.internal.graph.ContainingEdge;
import fr.inria.atlanmod.neoemf.data.blueprints.internal.graph.ElementVertex;
import fr.inria.atlanmod.neoemf.data.blueprints.internal.graph.ModelGraph;
import fr.inria.atlanmod.neoemf.data.mapping.DataMapper;

import org.atlanmod.commons.collect.MoreIterables;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static org.atlanmod.commons.Guards.checkNotNull;

/**
 * An abstract {@link BlueprintsBackend} that provides overall behavior for the management of a Blueprints database.
 */
@ParametersAreNonnullByDefault
abstract class AbstractBlueprintsBackend extends AbstractBackend implements BlueprintsBackend {

    /**
     * The Blueprints graph.
     */
    @Nonnull
    protected final ModelGraph graph;

    /**
     * Constructs a new {@code AbstractBlueprintsBackend} wrapping the provided {@code baseGraph}.
     *
     * @param baseGraph the base {@link com.tinkerpop.blueprints.KeyIndexableGraph} used to access the database
     *
     * @see BlueprintsBackendFactory
     */
    protected AbstractBlueprintsBackend(KeyIndexableGraph baseGraph) {
        checkNotNull(baseGraph, "baseGraph");

        graph = new ModelGraph(baseGraph);
    }

    /**
     * Provides a direct access to the underlying graph.
     * <p>
     * This method is public for tool compatibility (see the <a href="https://github.com/atlanmod/Mogwai">Mogwaï</a>)
     * framework, NeoEMF consistency is not guaranteed if the graph is modified manually.
     *
     * @return the Blueprints graph
     */
    @Nonnull
    public ModelGraph getGraph() {
        return graph;
    }

    @Override
    protected void internalClose() {
        graph.shutdown();
    }

    @Override
    public void internalSave() {
        if (graph.getFeatures().supportsTransactions) {
            graph.commit();
        }
        else {
            graph.shutdown();
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see GraphHelper#copyGraph(Graph, Graph)
     * @see ElementHelper#copyProperties(Element, Element)
     */
    @Override
    protected void internalCopyTo(DataMapper target) {
        AbstractBlueprintsBackend to = (AbstractBlueprintsBackend) target;

        graph.copyTo(to.graph);
    }

    @Nonnull
    @Override
    public Optional<SingleFeatureBean> containerOf(Id id) {
        checkNotNull(id, "id");

        return graph.getVertex(id)
                .flatMap(ElementVertex::getContainingEdge)
                .map(ContainingEdge::toBean);
    }

    @Override
    public void containerFor(Id id, SingleFeatureBean container) {
        checkNotNull(id, "id");
        checkNotNull(container, "container");

        graph.getOrCreateVertex(id)
                .setContainingEdge(container);
    }

    @Override
    public void removeContainer(Id id) {
        checkNotNull(id, "id");

        graph.getVertex(id)
                .ifPresent(ElementVertex::removeContainingEdge);
    }

    @Nonnull
    @Override
    public Optional<ClassBean> metaClassOf(Id id) {
        checkNotNull(id, "id");

        return graph.getVertex(id)
                .flatMap(ElementVertex::getClassVertex)
                .map(ClassVertex::toBean);
    }

    @Override
    public boolean metaClassFor(Id id, ClassBean metaClass) {
        checkNotNull(id, "id");
        checkNotNull(metaClass, "metaClass");

        return graph.getOrCreateVertex(id)
                .setClassVertex(metaClass);
    }

    @Nonnull
    @Override
    public Stream<Id> allInstancesOf(Set<ClassBean> metaClasses) {
        return graph.getClassVertices(metaClasses)
                .map(ClassVertex::getAllInstancesOf)
                .flatMap(MoreIterables::stream)
                .map(ElementVertex::getElementId)
                .distinct();
    }
}
