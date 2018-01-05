/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.io.writer;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.InconsistencyException;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;
import fr.inria.atlanmod.neoemf.data.mapping.ClassAlreadyExistsException;
import fr.inria.atlanmod.neoemf.data.mapping.DataMapper;
import fr.inria.atlanmod.neoemf.io.bean.BasicAttribute;
import fr.inria.atlanmod.neoemf.io.bean.BasicElement;
import fr.inria.atlanmod.neoemf.io.bean.BasicMetaclass;
import fr.inria.atlanmod.neoemf.io.bean.BasicReference;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;

import java.util.Collections;
import java.util.List;

import javax.annotation.ParametersAreNonnullByDefault;

import io.reactivex.Completable;

/**
 * A {@link Writer} that persists data into a {@link DataMapper}.
 */
@ParametersAreNonnullByDefault
public class DefaultMapperWriter extends AbstractWriter<DataMapper> {

    /**
     * Constructs a new {@code DefaultMapperWriter} with the given {@code mapper}.
     *
     * @param mapper the mapper where to write data
     */
    public DefaultMapperWriter(DataMapper mapper) {
        super(mapper);
    }

    @Override
    public void onInitialize() {
        // Create the 'ROOT' node with the default meta-class
        BasicElement rootElement = new BasicElement();
        rootElement.id(PersistentResource.ROOT_ID);
        rootElement.metaClass(BasicMetaclass.getDefault());

        createElement(rootElement, true);
    }

    @Override
    public void onComplete() {
        // Blocking save to ensure all queries are completed before exiting
        target.save().blockingAwait();
    }

    @Override
    public void onStartElement(BasicElement element) {
        super.onStartElement(element);

        createElement(element, false);
    }

    @Override
    public void onAttribute(BasicAttribute attribute, List<Object> values) {
        SingleFeatureBean key = SingleFeatureBean.of(attribute.owner(), attribute.id());

        Completable addFunc = !attribute.isMany()
                ? target.valueFor(key, values.get(0))
                : target.appendAllValues(key, values).toCompletable();

        // TODO Use async
        addFunc.blockingAwait();
    }

    @Override
    public void onReference(BasicReference reference, List<Id> values) {
        SingleFeatureBean key = SingleFeatureBean.of(reference.owner(), reference.id());

        // Update the containment reference if needed
        if (reference.isContainment()) {
            // TODO Use async
            values.forEach(id -> target.containerFor(id, key).blockingAwait());
        }

        Completable addFunc = !reference.isMany()
                ? target.referenceFor(key, values.get(0))
                : target.appendAllReferences(key, values).toCompletable();

        // TODO Use async
        addFunc.blockingAwait();
    }

    /**
     * Creates an element from the given {@code element}.
     *
     * @param element       the information about the new element
     * @param ignoreFailure {@code true} if the element can be ignored if it is already defined
     *
     * @throws NullPointerException   if the {@code element} is {@code null}
     * @throws InconsistencyException if an element with the same {@link Id} is already defined
     */
    protected void createElement(BasicElement element, boolean ignoreFailure) {
        BasicMetaclass metaClass = element.metaClass();

        try {
            // TODO Use async
            target.metaClassFor(element.id(), ClassBean.of(metaClass.name(), metaClass.ns().uri())).blockingAwait();
        }
        catch (ClassAlreadyExistsException e) {
            if (!ignoreFailure) {
                throw new InconsistencyException(String.format("An element with the same Id (%s) is already defined", element.id().toHexString()));
            }
        }

        // Add the current element as content of the 'ROOT' node
        if (element.isRoot()) {
            BasicReference reference = new BasicReference();
            reference.owner(PersistentResource.ROOT_ID);
            reference.name(PersistentResource.ROOT_REFERENCE_NAME);
            reference.id(-1); // TODO Calculates the feature identifier
            reference.isMany(true);

            onReference(reference, Collections.singletonList(element.id()));
        }
    }
}
