/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.io.writer;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.io.Handler;
import fr.inria.atlanmod.neoemf.io.proxy.ProxyElement;
import fr.inria.atlanmod.neoemf.io.proxy.ProxyAttribute;
import fr.inria.atlanmod.neoemf.io.proxy.ProxyReference;

import org.atlanmod.commons.log.Log;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import javax.annotation.ParametersAreNonnullByDefault;

import static org.atlanmod.commons.Preconditions.checkEqualTo;
import static org.atlanmod.commons.Preconditions.checkNotNull;

/**
 * An abstract {@link Writer} that acts as an accumulator of multi-value features in order to notify them once. This
 * allow to use batch methods in a {@link fr.inria.atlanmod.neoemf.data.mapping.DataMapper}, and ensure the call order
 * when writing in a file.
 *
 * @param <T> the type of the target
 */
@ParametersAreNonnullByDefault
public abstract class AbstractWriter<T> implements Writer {

    /**
     * The target where to write data.
     */
    @Nonnull
    protected final T target;

    /**
     * A LIFO that holds the current {@link Id} chain. It contains the current identifier and the previous.
     */
    @Nonnull
    private final Deque<Id> identifiers = new ArrayDeque<>();

    /**
     * A map that holds multi-valued references of the current element, waiting to be written.
     *
     * @see #flushAllFeatures()
     */
    @Nonnull
    private final Map<ProxyReference, List<Id>> referencesAccumulator = new HashMap<>();

    /**
     * A map that holds multi-valued attributes of the current element, waiting to be written.
     *
     * @see #flushAllFeatures()
     */
    @Nonnull
    private final Map<ProxyAttribute, List<Object>> attributesAccumulator = new HashMap<>();

    /**
     * The last multi-valued feature identifier processed by {@link Handler#onAttribute(ProxyAttribute)} or {@link
     * Handler#onReference(ProxyReference)}.
     */
    private int lastManyFeatureId = -1;

    /**
     * Constructs a new {@code AbstractWriter} with the given {@code target}.
     *
     * @param target the target where to write data
     */
    protected AbstractWriter(T target) {
        this.target = checkNotNull(target, "target");

        Log.debug("{0} created", getClass().getSimpleName());
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void onStartElement(ProxyElement element) throws IOException {
        flushAllFeatures();

        identifiers.addLast(element.getId().getResolved());
    }

    @Override
    public final void onAttribute(ProxyAttribute attribute) throws IOException {
        checkEqualTo(identifiers.getLast(), attribute.getOwner(),
                "%s is not the owner of this attribute (%s)", identifiers.getLast(), attribute.getOwner());

        final Object value = attribute.getValue().getResolved();

        if (!attribute.isMany()) {
            onAttribute(attribute, Collections.singletonList(value));
        }
        else {
            flushLastFeature(attribute.getId());
            attributesAccumulator.computeIfAbsent(attribute, a -> new LinkedList<>()).add(value);
        }
    }

    @Override
    public final void onReference(ProxyReference reference) throws IOException {
        if (!reference.isContainment()) { // Containment references are processed differently from standard references
            checkEqualTo(identifiers.getLast(), reference.getOwner(),
                    "%s is not the owner of this reference (%s)", identifiers.getLast(), reference.getOwner());
        }

        final Id id = reference.getValue().getResolved();

        if (!reference.isMany()) {
            onReference(reference, Collections.singletonList(id));
        }
        else {
            flushLastFeature(reference.getId());
            referencesAccumulator.computeIfAbsent(reference, r -> new LinkedList<>()).add(id);
        }
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void onEndElement() throws IOException {
        flushAllFeatures();

        identifiers.removeLast();
    }

    /**
     * Handles an attribute in the current element.
     *
     * @param attribute the new attribute, without its value
     * @param values    the ordered values of the attribute; when the {@code attribute} is single-valued, this parameter
     *                  is a {@link Collections#singletonList(Object)}
     */
    public abstract void onAttribute(ProxyAttribute attribute, List<Object> values) throws IOException;

    /**
     * Handles a reference in the current element.
     *
     * @param reference the new reference, without its value
     * @param values    the ordered values of the reference; when the {@code reference} is single-valued, this parameter
     *                  is a {@link Collections#singletonList(Object)}
     */
    public abstract void onReference(ProxyReference reference, List<Id> values) throws IOException;

    /**
     * Returns {@code true} if this writer requires the end of the current element before flushing all features,
     * otherwise, all features will be flushed as soon as another feature is intercepted. This is typically used when
     * writing a structured file with streams.
     *
     * @return {@code true} if this writer requires the end of the current element before flushing all features
     */
    protected boolean requireEndBeforeFlush() {
        return false;
    }

    /**
     * Flushes the last delayed multi-valued feature.
     *
     * @param currentManyFeatureId the identifier of the current feature
     *
     * @see #lastManyFeatureId
     * @see #requireEndBeforeFlush()
     */
    private void flushLastFeature(int currentManyFeatureId) throws IOException {
        if (!requireEndBeforeFlush() && lastManyFeatureId != -1 && currentManyFeatureId != lastManyFeatureId) {
            flushAllFeatures();
        }
        lastManyFeatureId = currentManyFeatureId;
    }

    /**
     * Flushes all delayed multi-valued features.
     */
    private void flushAllFeatures() throws IOException {
        if (!referencesAccumulator.isEmpty()) {
            for (Map.Entry<ProxyReference, List<Id>> e : referencesAccumulator.entrySet()) {
                onReference(e.getKey(), e.getValue());
            }
            referencesAccumulator.clear();
        }

        if (!attributesAccumulator.isEmpty()) {
            for (Map.Entry<ProxyAttribute, List<Object>> e : attributesAccumulator.entrySet()) {
                onAttribute(e.getKey(), e.getValue());
            }
            attributesAccumulator.clear();
        }

        lastManyFeatureId = -1;
    }
}
