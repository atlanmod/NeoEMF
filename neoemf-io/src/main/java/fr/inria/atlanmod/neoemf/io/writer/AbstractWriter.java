/*
 * Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.io.writer;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.io.bean.BasicAttribute;
import fr.inria.atlanmod.neoemf.io.bean.BasicElement;
import fr.inria.atlanmod.neoemf.io.bean.BasicReference;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Nonnull;
import javax.annotation.OverridingMethodsMustInvokeSuper;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkArgument;
import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;

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
    private final Deque<Id> previousId = new ArrayDeque<>();

    /**
     * A map that holds multi-valued references of the current element, waiting to be written.
     *
     * @see #flushAllFeatures()
     */
    @Nonnull
    private final Map<BasicReference, List<Id>> manyReferencesAccumulator = new HashMap<>();

    /**
     * A map that holds multi-valued attributes of the current element, waiting to be written.
     *
     * @see #flushAllFeatures()
     */
    @Nonnull
    private final Map<BasicAttribute, List<Object>> manyAttributesAccumulator = new HashMap<>();

    /**
     * The last multi-valued feature identifier processed by {@link #onAttribute(BasicAttribute)} or {@link
     * #onReference(BasicReference)}.
     */
    private int lastManyFeatureId = -1;

    /**
     * Constructs a new {@code AbstractWriter} with the given {@code target}.
     *
     * @param target the target where to write data
     */
    protected AbstractWriter(T target) {
        this.target = checkNotNull(target, "target");
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void onStartElement(BasicElement element) {
        flushAllFeatures();

        previousId.addLast(element.id());
    }

    @Override
    public final void onAttribute(BasicAttribute attribute) {
        checkArgument(Objects.equals(previousId.getLast(), attribute.owner()),
                "%s is not the owner of this attribute (%s)", previousId.getLast(), attribute.owner());

        if (!attribute.isMany()) {
            onAttribute(attribute, Collections.singletonList(attribute.value()));
        }
        else {
            flushLastFeature(attribute.id());
            manyAttributesAccumulator.computeIfAbsent(attribute, a -> new ArrayList<>()).add(attribute.value());
        }
    }

    @Override
    public final void onReference(BasicReference reference) {
        if (!reference.isContainment()) { // Containment references are processed differently from standard references
            checkArgument(Objects.equals(previousId.getLast(), reference.owner()),
                    "%s is not the owner of this reference (%s)", previousId.getLast(), reference.owner());
        }

        if (!reference.isMany()) {
            onReference(reference, Collections.singletonList(reference.value()));
        }
        else {
            flushLastFeature(reference.id());
            manyReferencesAccumulator.computeIfAbsent(reference, r -> new ArrayList<>()).add(reference.value());
        }
    }

    @Override
    public final void onCharacters(String characters) {
        // Do nothing
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void onEndElement() {
        flushAllFeatures();

        previousId.removeLast();
    }

    /**
     * Handles an attribute in the current element.
     *
     * @param attribute the new attribute, without its value
     * @param values    the ordered values of the attribute; when the {@code attribute} is single-valued, this parameter
     *                  is a {@link Collections#singletonList(Object)}
     */
    public abstract void onAttribute(BasicAttribute attribute, List<Object> values);

    /**
     * Handles a reference in the current element.
     *
     * @param reference the new reference, without its value
     * @param values    the ordered values of the reference; when the {@code reference} is single-valued, this parameter
     *                  is a {@link Collections#singletonList(Object)}
     */
    public abstract void onReference(BasicReference reference, List<Id> values);

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
    private void flushLastFeature(int currentManyFeatureId) {
        if (!requireEndBeforeFlush() && lastManyFeatureId != -1 && currentManyFeatureId != lastManyFeatureId) {
            flushAllFeatures();
        }
        lastManyFeatureId = currentManyFeatureId;
    }

    /**
     * Flushes all delayed multi-valued features.
     */
    private void flushAllFeatures() {
        if (!manyReferencesAccumulator.isEmpty()) {
            manyReferencesAccumulator.forEach(this::onReference);
            manyReferencesAccumulator.clear();
        }

        if (!manyAttributesAccumulator.isEmpty()) {
            manyAttributesAccumulator.forEach(this::onAttribute);
            manyAttributesAccumulator.clear();
        }

        lastManyFeatureId = -1;
    }
}
