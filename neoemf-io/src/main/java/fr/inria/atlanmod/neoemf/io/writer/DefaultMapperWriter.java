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

import fr.inria.atlanmod.commons.cache.Cache;
import fr.inria.atlanmod.commons.cache.CacheBuilder;
import fr.inria.atlanmod.commons.log.Log;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.StringId;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;
import fr.inria.atlanmod.neoemf.data.mapping.DataMapper;
import fr.inria.atlanmod.neoemf.io.bean.BasicAttribute;
import fr.inria.atlanmod.neoemf.io.bean.BasicElement;
import fr.inria.atlanmod.neoemf.io.bean.BasicId;
import fr.inria.atlanmod.neoemf.io.bean.BasicMetaclass;
import fr.inria.atlanmod.neoemf.io.bean.BasicReference;
import fr.inria.atlanmod.neoemf.io.util.MapperConstants;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;
import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;

/**
 * A {@link MapperWriter} that persists data in a {@link DataMapper}, based on received events.
 */
@ParametersAreNonnullByDefault
public class DefaultMapperWriter implements MapperWriter {

    /**
     * The mapper where to write data.
     */
    @Nonnull
    private final DataMapper mapper;

    /**
     * A LIFO that holds the current {@link Id} chain. It contains the current identifier and the previous.
     */
    @Nonnull
    private final Deque<Id> previousIds = new ArrayDeque<>();

    /**
     * In-memory cache that holds the recently processed {@link Id}s, identified by their literal representation.
     */
    @Nonnull
    private final Cache<String, Id> cache = CacheBuilder.builder()
            .softValues()
            .build();

    /**
     * Constructs a new {@code DefaultMapperWriter} on top of the {@code mapper}.
     *
     * @param mapper the mapper where to write data
     */
    public DefaultMapperWriter(DataMapper mapper) {
        this.mapper = checkNotNull(mapper);

        Log.debug("{0} created", getClass().getSimpleName());
    }

    @Override
    public void onInitialize() {
        // Create the 'ROOT' node with the default meta-class
        BasicMetaclass metaClass = BasicMetaclass.getDefault();

        BasicElement rootElement = new BasicElement(metaClass.ns(), metaClass.name());
        rootElement.id(BasicId.generated(MapperConstants.ROOT_ID.toString()));
        rootElement.className(metaClass.name());
        rootElement.isRoot(false);
        rootElement.metaClass(metaClass);

        createElement(rootElement, MapperConstants.ROOT_ID, true);
    }

    @Override
    public void onStartElement(BasicElement element) {
        previousIds.addLast(createElement(element));
    }

    @Override
    public void onAttribute(BasicAttribute attribute) {
        Id id = Optional.ofNullable(attribute.owner())
                .map(this::getOrCreateId)
                .orElseGet(previousIds::getLast);

        addAttribute(id, attribute);
    }

    @Override
    public void onReference(BasicReference reference) {
        Id id = Optional.ofNullable(reference.owner())
                .map(this::getOrCreateId)
                .orElseGet(previousIds::getLast);

        Id idReference = getOrCreateId(reference.idReference());

        addReference(id, reference, idReference);
    }

    @Override
    public void onCharacters(String characters) {
        // Do nothing
    }

    @Override
    public void onEndElement() {
        previousIds.removeLast();
    }

    @Override
    public void onComplete() {
        mapper.save();
    }

    /**
     * Creates an element from the given {@code element} with the given {@code id}.
     *
     * @param element     the information about the new element
     * @param id          the identifier of the element
     * @param isDummyRoot {@code true} if the {@code element} represents the 'ROOT' node
     *
     * @throws NullPointerException if any of the parameters is {@code null}
     */
    protected void createElement(BasicElement element, Id id, boolean isDummyRoot) {
        updateInstanceOf(id, element.metaClass().name(), element.metaClass().ns().uri());

        if (!isDummyRoot) { // The 'ROOT' node doesn't need these features
            Optional.ofNullable(element.className())
                    .map(cn -> {
                        BasicAttribute attribute = new BasicAttribute();
                        attribute.name(MapperConstants.FEATURE_NAME);
                        attribute.value(cn);
                        return attribute;
                    }).ifPresent(a -> addAttribute(id, a));

            // Add the current element as content of the 'ROOT' node
            if (element.isRoot()) {
                BasicReference reference = new BasicReference();
                reference.name(MapperConstants.ROOT_FEATURE_NAME);
                reference.isMany(true);

                addReference(MapperConstants.ROOT_ID, reference, id);
            }
        }
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
    @Nonnull
    protected Id createElement(BasicElement element) {
        Id id = createId(element.id());

        createElement(element, id, false);
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
    @Nonnull
    protected Id getOrCreateId(BasicId identifier) {
        return cache.get(identifier.value(), value -> createId(identifier));
    }

    /**
     * Creates an {@link Id} from the given {@code identifier}.
     *
     * @param identifier the representation of the {@link Id}
     *
     * @return the {@link Id}
     */
    @Nonnull
    protected Id createId(BasicId identifier) {
        // If identifier has been generated we hash it, otherwise we use the original
        return identifier.isGenerated()
                ? StringId.generate(identifier.value())
                : StringId.of(identifier.value());
    }

    /**
     * Adds a new {@code attribute} to the element identified by the given {@code id}.
     *
     * @param id        the identifier of the element
     * @param attribute the attribute to add
     */
    protected void addAttribute(Id id, BasicAttribute attribute) {
        SingleFeatureBean key = SingleFeatureBean.of(id, attribute.name());

        if (!attribute.isMany()) {
            mapper.valueFor(key, attribute.value());
        }
        else {
            int index = attribute.index();
            if (index >= 0) {
                mapper.addValue(key.withPosition(index), attribute.value());
            }
            else {
                mapper.appendValue(key, attribute.value());
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
        // Update the containment reference if needed
        if (reference.isContainment()) {
            updateContainment(id, reference.name(), idReference);
        }

        SingleFeatureBean key = SingleFeatureBean.of(id, reference.name());

        if (!reference.isMany()) {
            mapper.referenceFor(key, idReference);
        }
        else {
            int index = reference.index();
            if (index >= 0) {
                mapper.addReference(key.withPosition(index), idReference);
            }
            else {
                mapper.appendReference(key, idReference);
            }
        }
    }

    /**
     * Updates the containment identified by its {@code featureId} between the {@code idContainer} and the {@code
     * idContainment}.
     *
     * @param idContainer   the identifier of the element
     * @param featureId     the identifier of the reference (parent -&gt; child)
     * @param idContainment the identifier of the referenced element
     */
    protected void updateContainment(Id idContainer, String featureId, Id idContainment) {
        Optional<SingleFeatureBean> container = mapper.containerOf(idContainment);
        if (!container.isPresent() || !Objects.equals(container.get().owner(), idContainer)) {
            mapper.containerFor(idContainment, SingleFeatureBean.of(idContainer, featureId));
        }
    }

    /**
     * Defines the meta-class to the element identified by the given {@code id}.
     *
     * @param id   the identifier of the element
     * @param name the name of the meta-class
     * @param uri  the uri of the meta-class
     */
    protected void updateInstanceOf(Id id, String name, String uri) {
        Optional<ClassBean> metaClass = mapper.metaClassOf(id);
        if (!metaClass.isPresent()) {
            mapper.metaClassFor(id, ClassBean.of(name, uri));
        }
        else {
            throw new IllegalArgumentException("An element with the same Id (" + id + ") is already defined");
        }
    }
}
