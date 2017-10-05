/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.io.writer;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;
import fr.inria.atlanmod.neoemf.data.mapping.DataMapper;
import fr.inria.atlanmod.neoemf.io.bean.BasicAttribute;
import fr.inria.atlanmod.neoemf.io.bean.BasicElement;
import fr.inria.atlanmod.neoemf.io.bean.BasicMetaclass;
import fr.inria.atlanmod.neoemf.io.bean.BasicReference;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;

import java.util.Collections;
import java.util.List;

import javax.annotation.ParametersAreNonnullByDefault;

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
        target.save();
    }

    @Override
    public void onStartElement(BasicElement element) {
        super.onStartElement(element);

        createElement(element, false);
    }

    @Override
    public void onAttribute(BasicAttribute attribute, List<Object> values) {
        SingleFeatureBean key = SingleFeatureBean.of(attribute.owner(), attribute.id());

        if (!attribute.isMany()) {
            target.valueFor(key, values.get(0));
        }
        else {
            target.appendAllValues(key, values);
        }
    }

    @Override
    public void onReference(BasicReference reference, List<Id> values) {
        SingleFeatureBean key = SingleFeatureBean.of(reference.owner(), reference.id());

        // Update the containment reference if needed
        if (reference.isContainment()) {
            values.forEach(i -> target.containerFor(i, key));
        }

        if (!reference.isMany()) {
            target.referenceFor(key, values.get(0));
        }
        else {
            target.appendAllReferences(key, values);
        }
    }

    /**
     * Creates an element from the given {@code element}.
     *
     * @param element       the information about the new element
     * @param ignoreFailure {@code true} if the element can be ignored if it is already defined
     *
     * @throws NullPointerException if the {@code element} is {@code null}
     */
    protected void createElement(BasicElement element, boolean ignoreFailure) {
        BasicMetaclass metaClass = element.metaClass();
        boolean success = target.metaClassFor(element.id(), ClassBean.of(metaClass.name(), metaClass.ns().uri()));

        if (!success && !ignoreFailure) {
            throw new IllegalArgumentException(String.format("An element with the same Id (%s) is already defined", element.id().toHexString()));
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
