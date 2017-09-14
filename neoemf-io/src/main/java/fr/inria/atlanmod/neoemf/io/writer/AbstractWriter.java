package fr.inria.atlanmod.neoemf.io.writer;

import fr.inria.atlanmod.commons.log.Log;
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
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

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
     * A FIFO that holds multi-valued references of the current element, waiting to be written.
     *
     * @see #flushCurrentElement()
     */
    private Map<BasicReference, List<Id>> manyReferencesAccumulator;

    /**
     * A FIFO that holds multi-valued attributes of the current element, waiting to be written.
     *
     * @see #flushCurrentElement()
     */
    private Map<BasicAttribute, List<String>> manyAttributesAccumulator;

    /**
     * Constructs a new {@code AbstractWriter} with the given {@code target}.
     *
     * @param target the target where to write data
     */
    protected AbstractWriter(T target) {
        this.target = checkNotNull(target);

        Log.debug("{0} created", getClass().getSimpleName());
    }

    @Override
    @OverridingMethodsMustInvokeSuper
    public void onStartElement(BasicElement element) {
        flushCurrentElement();

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
            if (isNull(manyAttributesAccumulator)) {
                manyAttributesAccumulator = new HashMap<>();
            }
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
            if (isNull(manyReferencesAccumulator)) {
                manyReferencesAccumulator = new HashMap<>();
            }
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
        flushCurrentElement();

        previousId.removeLast();
    }

    /**
     * Handles an attribute in the current element.
     *
     * @param attribute the new attribute, without its value
     * @param values    the ordered values of the attribute; when the {@code attribute} is single-valued, this parameter
     *                  is a {@link Collections#singletonList(Object)}
     */
    public abstract void onAttribute(BasicAttribute attribute, List<String> values);

    /**
     * Handles a reference in the current element.
     *
     * @param reference the new reference, without its value
     * @param values    the ordered values of the reference; when the {@code reference} is single-valued, this parameter
     *                  is a {@link Collections#singletonList(Object)}
     */
    public abstract void onReference(BasicReference reference, List<Id> values);

    /**
     * Flushes the current element, and writes all delayed multi-valued features.
     */
    private void flushCurrentElement() {
        if (nonNull(manyReferencesAccumulator)) {
            manyReferencesAccumulator.forEach(this::onReference);

            manyReferencesAccumulator = null;
        }

        if (nonNull(manyAttributesAccumulator)) {
            manyAttributesAccumulator.forEach(this::onAttribute);

            manyAttributesAccumulator = null;
        }
    }
}
