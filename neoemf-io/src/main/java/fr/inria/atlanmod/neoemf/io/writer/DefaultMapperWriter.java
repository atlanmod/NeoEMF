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

import fr.inria.atlanmod.commons.log.Log;
import fr.inria.atlanmod.neoemf.core.Id;
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
        rootElement.id(BasicId.original(MapperConstants.ROOT_ID.toString()));
        rootElement.className(metaClass.name());
        rootElement.isRoot(false);
        rootElement.metaClass(metaClass);

        createElement(rootElement, true);
    }

    @Override
    public void onStartElement(BasicElement element) {
        createElement(element, false);
    }

    @Override
    public void onAttribute(BasicAttribute attribute) {
        final Id id = Optional.ofNullable(attribute.owner())
                .map(BasicId::getOrCreateId)
                .orElseGet(previousIds::getLast);

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

    @Override
    public void onReference(BasicReference reference) {
        final Id id = Optional.ofNullable(reference.owner())
                .map(BasicId::getOrCreateId)
                .orElseGet(previousIds::getLast);

        final Id idReference = reference.idReference().getOrCreateId();

        // Update the containment reference if needed
        if (reference.isContainment()) {
            mapper.containerFor(idReference, SingleFeatureBean.of(id, reference.name()));
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
     * Creates an element from the given {@code element}.
     *
     * @param element     the information about the new element
     * @param isDummyRoot {@code true} if the {@code element} represents the 'ROOT' node
     *
     * @throws NullPointerException if the {@code element} is {@code null}
     */
    protected void createElement(BasicElement element, boolean isDummyRoot) {
        final Id id = element.id().getOrCreateId();
        Optional<ClassBean> metaClass = mapper.metaClassOf(id);

        if (!metaClass.isPresent()) {
            mapper.metaClassFor(id, ClassBean.of(element.metaClass().name(), element.metaClass().ns().uri()));
        }
        else if (!isDummyRoot) { // The "ROOT" Id may already exist for an existing resource
            throw new IllegalArgumentException("An element with the same Id (" + id + ") is already defined");
        }

        if (!isDummyRoot) { // The 'ROOT' node doesn't need these features
            Optional.ofNullable(element.className())
                    .map(cn -> {
                        BasicAttribute attribute = new BasicAttribute();
                        attribute.owner(element.id());
                        attribute.name(MapperConstants.FEATURE_NAME);
                        attribute.value(cn);
                        return attribute;
                    }).ifPresent(this::onAttribute);

            // Add the current element as content of the 'ROOT' node
            if (element.isRoot()) {
                BasicReference reference = new BasicReference();
                reference.owner(BasicId.original(MapperConstants.ROOT_ID.toString()));
                reference.name(MapperConstants.ROOT_FEATURE_NAME);
                reference.idReference(element.id());
                reference.isMany(true);

                onReference(reference);
            }
        }

        previousIds.addLast(id);
    }
}
