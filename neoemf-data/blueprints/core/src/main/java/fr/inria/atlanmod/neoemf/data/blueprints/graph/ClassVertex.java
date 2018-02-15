/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.blueprints.graph;

import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Vertex;

import fr.inria.atlanmod.commons.Lazy;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;
import fr.inria.atlanmod.neoemf.data.blueprints.graph.wrapper.IdVertex;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static java.util.Objects.isNull;

/**
 * An {@link IdVertex} representing a meta-class.
 */
@ParametersAreNonnullByDefault
public class ClassVertex extends IdVertex<ModelGraph> {

    /**
     * The property key used to define the name of meta-class {@link Vertex}s.
     */
    protected static final String PROPERTY_NAME = "_in";

    /**
     * The property key used to define the URI of meta-class {@link Vertex}s.
     */
    protected static final String PROPERTY_URI = "_iu";

    /**
     * The name of the represented meta-class.
     */
    @Nonnull
    private final Lazy<String> lazyName = Lazy.with(() -> check(getProperty(PROPERTY_NAME)));

    /**
     * Constructs a new {@code ClassVertex}.
     *
     * @param baseVertex the base vertex
     * @param graph      the graph that owns this vertex
     */
    private ClassVertex(Vertex baseVertex, ModelGraph graph) {
        super(baseVertex, graph);
    }

    /**
     * Creates a new instance of {@code ClassVertex} on the {@code baseVertex}.
     *
     * @param baseVertex the base vertex
     * @param graph      the graph that owns the vertex
     *
     * @return the meta-class vertex
     */
    @Nonnull
    public static ClassVertex from(Vertex baseVertex, ModelGraph graph) {
        return new ClassVertex(baseVertex, graph);
    }

    /**
     * Creates a new {@code ClassVertex} on the {@code graph}.
     *
     * @param graph the graph that owns the vertex
     * @param bean  the simple representation of the meta-class
     *
     * @return a new meta-class vertex
     */
    @Nonnull
    protected static ClassVertex create(ModelGraph graph, ClassBean bean) {
        final Object id = graph.getIdConverter().convert(ClassVertex.createId(bean));

        ClassVertex v = graph.addVertex(id, ClassVertex::new).setName(bean.name()).setUri(bean.uri());
        graph.updateIndex(v);

        return v;
    }

    /**
     * Retrieves or creates a new {@code ClassVertex} on the {@code graph}.
     *
     * @param graph the graph that owns the vertex
     * @param bean  the simple representation of the meta-class
     *
     * @return a new meta-class vertex
     */
    @Nonnull
    protected static ClassVertex getOrCreate(ModelGraph graph, ClassBean bean) {
        return graph.getClassVertex(bean).orElseGet(() -> create(graph, bean));
    }

    /**
     * Generates the unique identifier of the specified {@code bean}.
     *
     * @param bean the simple representation of a meta-class
     *
     * @return the unique identifier
     */
    @Nonnull
    private static Id createId(ClassBean bean) {
        return Id.getProvider().generate(String.format("%s@%s", bean.name(), bean.uri()));
    }

    /**
     * Returns the name of the represented meta-class.
     *
     * @return the name of the represented meta-class
     */
    @Nonnull
    public String getName() {
        return lazyName.get();
    }

    /**
     * Defines the name of the represented meta-class.
     *
     * @param name the name of the represented meta-class
     *
     * @return this vertex (for chaining)
     */
    @Nonnull
    private ClassVertex setName(String name) {
        setProperty(PROPERTY_NAME, name);
        lazyName.update(name);
        return this;
    }

    /**
     * Returns the URI of the represented meta-class.
     *
     * @return the URI of the represented meta-class
     */
    @Nonnull
    public String getUri() {
        return check(getProperty(PROPERTY_URI));
    }

    /**
     * Defines the URI of the represented meta-class.
     *
     * @param uri the URI of the represented meta-class
     *
     * @return this vertex (for chaining)
     */
    @Nonnull
    private ClassVertex setUri(String uri) {
        setProperty(PROPERTY_URI, uri);
        return this;
    }

    /**
     * Returns the simple representation of this meta-class vertex.
     *
     * @return the simple representation of this meta-class vertex
     */
    @Nonnull
    public ClassBean toBean() {
        return ClassBean.of(getName(), getUri());
    }

    /**
     * Returns all elements that are instance of the represented meta-class.
     *
     * @return a stream of vertices
     */
    @Nonnull
    public Iterable<ElementVertex> getAllInstancesOf() {
        return getVertices(Direction.IN, toArray(InstanceOfEdge.LABEL), ElementVertex::from);
    }

    /**
     * Ensures that this vertex is a meta-class representation by checking the specified value.
     *
     * @param value the property value
     *
     * @return the value
     */
    @Nonnull
    private <T> T check(@Nullable T value) {
        if (isNull(value)) {
            throw new IllegalStateException("This vertex is not a meta-class representation");
        }
        return value;
    }

    /**
     * Returns an array that only contains the specified {@code value}.
     *
     * @param value the value
     *
     * @return an array
     */
    @Nonnull
    private String[] toArray(String value) {
        return new String[]{value};
    }
}
