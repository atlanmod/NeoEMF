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

package fr.inria.atlanmod.neoemf.io.writer;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.StringId;
import fr.inria.atlanmod.neoemf.data.mapper.DataMapper;
import fr.inria.atlanmod.neoemf.data.structure.ClassDescriptor;
import fr.inria.atlanmod.neoemf.data.structure.ContainerDescriptor;
import fr.inria.atlanmod.neoemf.data.structure.FeatureKey;
import fr.inria.atlanmod.neoemf.io.hash.Hashers;
import fr.inria.atlanmod.neoemf.io.structure.BasicAttribute;
import fr.inria.atlanmod.neoemf.io.structure.BasicElement;
import fr.inria.atlanmod.neoemf.io.structure.BasicId;
import fr.inria.atlanmod.neoemf.io.structure.BasicMetaclass;
import fr.inria.atlanmod.neoemf.io.structure.BasicReference;
import fr.inria.atlanmod.neoemf.io.util.MapperConstants;
import fr.inria.atlanmod.neoemf.util.cache.Cache;
import fr.inria.atlanmod.neoemf.util.cache.CacheBuilder;
import fr.inria.atlanmod.neoemf.util.log.Log;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;
import java.util.Optional;

import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.neoemf.util.Preconditions.checkNotNull;

/**
 * A {@link MapperWriter} that persists data in a {@link DataMapper}, based on received events.
 */
@ParametersAreNonnullByDefault
public class DefaultMapperWriter implements MapperWriter {

    /**
     * The mapper where to write data.
     */
    protected final DataMapper mapper;

    /**
     * Queue that holds the current {@link Id} chain. It contains the current element and its parent and is updated
     * after each addition/deletion of an element.
     */
    protected final Deque<Id> stack = new ArrayDeque<>();

    /**
     * In-memory cache that holds the recently processed {@link Id}s, identified by their literal representation.
     */
    private final Cache<String, Id> cache = CacheBuilder.newBuilder()
            .maximumSize()
            .build();

    /**
     * Constructs a new {@code DefaultMapperWriter} on top of the {@code mapper}.
     *
     * @param mapper the mapper where to write data
     */
    protected DefaultMapperWriter(DataMapper mapper) {
        this.mapper = checkNotNull(mapper);

        Log.debug("{0} created", getClass().getSimpleName());
    }

    @Override
    public void onInitialize() {
        // Create the 'ROOT' node with the default metaclass
        BasicMetaclass metaClass = BasicMetaclass.getDefault();

        BasicElement rootElement = new BasicElement(metaClass.ns(), metaClass.name());
        rootElement.id(BasicId.generated(MapperConstants.ROOT_ID.toString()));
        rootElement.className(metaClass.name());
        rootElement.isRoot(false);
        rootElement.metaclass(metaClass);

        createElement(rootElement, MapperConstants.ROOT_ID);
    }

    @Override
    public void onStartElement(BasicElement element) {
        stack.addLast(createElement(element));
    }

    @Override
    public void onAttribute(BasicAttribute attribute) {
        Id id = Optional.ofNullable(attribute.id())
                .map(this::getOrCreateId)
                .orElseGet(stack::getLast);

        addAttribute(id, attribute);
    }

    @Override
    public void onReference(BasicReference reference) {
        Id id = Optional.ofNullable(reference.id())
                .map(this::getOrCreateId)
                .orElseGet(stack::getLast);

        Id idReference = getOrCreateId(reference.idReference());

        addReference(id, reference, idReference);
    }

    @Override
    public void onCharacters(String characters) {
        // Do nothing
    }

    @Override
    public void onEndElement() {
        stack.removeLast();
    }

    @Override
    public void onComplete() {
        mapper.save();
    }

    /**
     * Creates an element from the given {@code element} with the given {@code id}.
     *
     * @param element the information about the new element
     * @param id      the identifier of the element
     *
     * @return the given {@code id}
     *
     * @throws NullPointerException if any of the parameters is {@code null}
     */
    protected Id createElement(BasicElement element, Id id) {
        checkNotNull(element);
        checkNotNull(id);

        updateInstanceOf(id, element.metaclass().name(), element.metaclass().ns().uri());

        Optional.ofNullable(element.className()).ifPresent(c -> {
            BasicAttribute attribute = new BasicAttribute(MapperConstants.FEATURE_NAME);
            attribute.value(c);
            addAttribute(id, attribute);
        });

        // Add the current element as content of the 'ROOT' node
        if (element.isRoot()) {
            BasicReference reference = new BasicReference(MapperConstants.ROOT_FEATURE_NAME);
            reference.isMany(true);

            addReference(MapperConstants.ROOT_ID, reference, id);
        }

        return id;
    }

    /**
     * Creates an element from the given {@code element}, and creates its {@link Id}.
     *
     * @param element the information about the new element
     *
     * @return the {@link Id} of the created element
     *
     * @throws NullPointerException if the {@code element} is {@code null} or if it does not have an {@link Id}
     */
    protected Id createElement(BasicElement element) {
        checkNotNull(element.id());

        Id id = createId(element.id());

        createElement(element, id);
        cache.put(element.id().value(), id);

        return id;
    }

    /**
     * Returns the {@link Id} of the given {@code identifier}.
     *
     * @param identifier the representation of the {@link Id}
     *
     * @return the registered {@link Id} of the given identifier, or {@code null} if the identifier is not registered.
     */
    protected Id getOrCreateId(BasicId identifier) {
        checkNotNull(identifier);

        return cache.get(identifier.value(), value -> createId(identifier));
    }

    /**
     * Creates an {@link Id} from the given {@code identifier}.
     *
     * @param identifier the representation of the {@link Id}
     *
     * @return the {@link Id}
     */
    protected Id createId(BasicId identifier) {
        checkNotNull(identifier);

        String idValue = identifier.value();

        // If identifier has been generated we hash it, otherwise we use the original
        if (identifier.isGenerated()) {
            idValue = Hashers.md5().hash(idValue).toString();
        }

        return StringId.of(idValue);
    }

    /**
     * Adds a new {@code attribute} to the element identified by the given {@code id}.
     *
     * @param id        the identifier of the element
     * @param attribute the attribute to add
     */
    protected void addAttribute(Id id, BasicAttribute attribute) {
        checkNotNull(id);
        checkNotNull(attribute);

        FeatureKey key = FeatureKey.of(id, attribute.name());

        if (!attribute.isMany()) {
            mapper.valueFor(key, attribute.value());
        }
        else {
            int index = attribute.index();
            if (index == DataMapper.NO_INDEX) {
                mapper.appendValue(key, attribute.value());
            }
            else {
                mapper.addValue(key.withPosition(index), attribute.value());
            }
        }
    }

    /**
     * Adds a new reference to the element identified by the given {@code id}.
     *
     * @param id          the identifier of the element
     * @param reference   the reference to add
     * @param idReference the identifier of the referenced element
     */
    protected void addReference(Id id, BasicReference reference, Id idReference) {
        checkNotNull(id);
        checkNotNull(reference);
        checkNotNull(idReference);

        // Update the containment reference if needed
        if (reference.isContainment()) {
            updateContainment(id, reference.name(), idReference);
        }

        FeatureKey key = FeatureKey.of(id, reference.name());

        if (!reference.isMany()) {
            mapper.referenceFor(key, idReference);
        }
        else {
            int index = reference.index();
            if (index == DataMapper.NO_INDEX) {
                mapper.appendReference(key, idReference);
            }
            else {
                mapper.addReference(key.withPosition(index), idReference);
            }
        }
    }

    /**
     * Updates the containment identified by its {@code name} between the {@code idContainer} and the {@code
     * idContainment}.
     *
     * @param idContainer   the identifier of the element
     * @param name          the name of the property identifying the reference (parent -&gt; child)
     * @param idContainment the identifier of the referenced element
     */
    protected void updateContainment(Id idContainer, String name, Id idContainment) {
        checkNotNull(idContainer);
        checkNotNull(name);
        checkNotNull(idContainment);

        Optional<ContainerDescriptor> container = mapper.containerOf(idContainment);
        if (!container.isPresent() || !Objects.equals(container.get().id(), idContainer)) {
            mapper.containerFor(idContainment, ContainerDescriptor.of(idContainer, name));
        }
    }

    /**
     * Defines the metaclass to the element identified by the given {@code id}.
     *
     * @param id   the identifier of the element
     * @param name the name of the metaclass
     * @param uri  the uri of the metaclass
     */
    protected void updateInstanceOf(Id id, String name, String uri) {
        checkNotNull(id);
        checkNotNull(name);
        checkNotNull(uri);

        Optional<ClassDescriptor> metaclass = mapper.metaclassOf(id);
        if (!metaclass.isPresent()) {
            mapper.metaclassFor(id, ClassDescriptor.of(name, uri));
        }
        else {
            throw new IllegalArgumentException("An element with the same Id (" + id + ") is already defined. Use a handler with a conflicts resolution feature instead.");
        }
    }
}
