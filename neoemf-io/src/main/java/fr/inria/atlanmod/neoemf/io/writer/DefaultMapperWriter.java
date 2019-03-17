/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
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
import fr.inria.atlanmod.neoemf.io.proxy.ProxyElement;
import fr.inria.atlanmod.neoemf.io.proxy.ProxyAttribute;
import fr.inria.atlanmod.neoemf.io.proxy.ProxyClass;
import fr.inria.atlanmod.neoemf.io.proxy.ProxyReference;
import fr.inria.atlanmod.neoemf.io.proxy.ProxyValue;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.annotation.ParametersAreNonnullByDefault;

import static org.atlanmod.commons.Preconditions.checkState;

/**
 * A {@link Writer} that persists data into a {@link fr.inria.atlanmod.neoemf.data.mapping.DataMapper}.
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
        ProxyElement rootElement = new ProxyElement()
                .setId(ProxyValue.resolved(PersistentResource.ROOT_ID))
                .setMetaClass(ProxyClass.DEFAULT);

        createElement(rootElement, true);
    }

    @Override
    public void onComplete() {
        target.save();
    }

    @Override
    public void onStartElement(ProxyElement element) throws IOException {
        super.onStartElement(element);

        createElement(element, false);
    }

    @Override
    public void onAttribute(ProxyAttribute attribute, List<Object> values) {
        SingleFeatureBean bean = SingleFeatureBean.of(attribute.getOwner(), attribute.getId());

        if (!attribute.isMany()) {
            target.valueFor(bean, values.get(0));
        }
        else {
            target.appendAllValues(bean, values);
        }
    }

    @Override
    public void onReference(ProxyReference reference, List<Id> values) {
        SingleFeatureBean bean = SingleFeatureBean.of(reference.getOwner(), reference.getId());

        // Update the containment reference if needed
        if (reference.isContainment()) {
            values.forEach(i -> target.containerFor(i, bean));
        }

        if (!reference.isMany()) {
            target.referenceFor(bean, values.get(0));
        }
        else {
            target.appendAllReferences(bean, values);
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
    protected void createElement(ProxyElement element, boolean ignoreFailure) {
        ProxyClass metaClass = element.getMetaClass();
        boolean success = target.metaClassFor(element.getId().getResolved(), ClassBean.of(metaClass.getName(), metaClass.getNamespace().getUri()));

        checkState(success || ignoreFailure, "An element with the same Id (%s) is already defined", element.getId().getResolved().toHexString());

        // Add the current element as content of the 'ROOT' node
        if (element.isRoot()) {
            ProxyReference rootReference = new ProxyReference()
                    .setOwner(PersistentResource.ROOT_ID)
                    .setName(PersistentResource.ROOT_REFERENCE_NAME)
                    .setId(-1)
                    .setMany(true);

            onReference(rootReference, Collections.singletonList(element.getId().getResolved()));
        }
    }
}
