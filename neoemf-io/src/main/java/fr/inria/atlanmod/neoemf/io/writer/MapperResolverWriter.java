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

import com.google.common.collect.HashMultimap;

import fr.inria.atlanmod.neoemf.annotations.Experimental;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.mapper.DataMapper;
import fr.inria.atlanmod.neoemf.io.AlreadyExistingIdException;
import fr.inria.atlanmod.neoemf.io.Handler;
import fr.inria.atlanmod.neoemf.io.structure.RawElement;
import fr.inria.atlanmod.neoemf.io.structure.RawId;
import fr.inria.atlanmod.neoemf.io.structure.RawReference;
import fr.inria.atlanmod.neoemf.util.log.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link Handler} for {@link DataMapper}s.
 * <p>
 * <b>Important Note:</b> This handler has a key conflicts resolution feature, but it consumes much more memory than the
 * {@link DefaultMapperWriter}. Make sure you have enough memory to avoid heap space.
 */
@Experimental
@ParametersAreNonnullByDefault
public class MapperResolverWriter extends DefaultMapperWriter {

    /**
     * Map holding the unlinked elements, waiting until their reference is created.
     */
    private final HashMultimap<String, UnlinkedRawReference> unlinkedElements;

    /**
     * In-memory cache that holds conflicted {@link Id}s, identified by their literal representation.
     */
    private final Map<String, Id> conflictIdsCache = new HashMap<>();

    /**
     * Constructs a new {@code MapperResolverWriter} on the given {@code mapper}.
     *
     * @param mapper the mapper where to write data
     */
    protected MapperResolverWriter(DataMapper mapper) {
        super(mapper);

        this.unlinkedElements = HashMultimap.create();
    }

    @Override
    public void onStartElement(RawElement element) {
        super.onStartElement(element);

        tryLink(element.id().value(), idsStack.getLast());
    }

    @Override
    public void onComplete() {
        super.onComplete();

        long unlinkedNumber = unlinkedElements.size();
        if (unlinkedNumber > 0) {
            Log.warn("Some elements have not been linked ({0})", unlinkedNumber);
            unlinkedElements.asMap().keySet().forEach(e -> Log.warn(" > " + e));
            unlinkedElements.clear();
        }

        long conflictedId = conflictIdsCache.size();
        if (conflictedId > 0) {
            Log.info("{0} key conflicts", conflictedId);
            conflictIdsCache.clear();
        }
    }

    @Override
    protected Id createElement(RawElement element, Id id) {
        boolean conflict = false;
        do {
            try {
                id = super.createElement(element, id);
            }
            catch (AlreadyExistingIdException e) {
                // Id already exists in the mapper : try another
                id = createId(RawId.generated(id.toString()));
                conflictIdsCache.put(element.id().value(), id);
                conflict = true;
            }
        }
        while (conflict);

        return id;
    }

    @Override
    protected Id getOrCreateId(RawId identifier) {
        return Optional.ofNullable(conflictIdsCache.get(identifier.value()))
                .orElse(super.getOrCreateId(identifier));
    }

    @Override
    protected void addReference(Id id, RawReference reference, Id idReference) {
        try {
            super.addReference(id, reference, idReference);
        }
        catch (NoSuchElementException e) {
            // Referenced element does not exist : we save it in a cache
            unlinkedElements.put(reference.idReference().value(), new UnlinkedRawReference(id, reference));
        }
    }

    @Override
    protected void checkExists(Id id) {
        if (!mapper.exists(id)) {
            throw new NoSuchElementException("Unable to find an element with Id: " + id);
        }
    }

    @Override
    protected void checkDoesNotExist(Id id) {
        if (mapper.exists(id)) {
            throw new AlreadyExistingIdException("Already existing Id: " + id);
        }
    }

    /**
     * Tries to link references that have not been linked at their creation.
     *
     * @param reference the reference of the targeted element
     * @param id        the identifier of the targeted element
     */
    private void tryLink(String reference, Id id) {
        unlinkedElements.removeAll(reference).forEach(e -> addReference(e.id, e, id));
    }

    /**
     * A simple representation of an element that could not be linked when it was created.
     */
    private static class UnlinkedRawReference extends RawReference {

        /**
         * The identifier of this element.
         */
        public final Id id;

        /**
         * Constructs a new {@code UnlinkedRawReference} with the given {@code id} and information about the {@link
         * RawReference}.
         *
         * @param id        the identifier of the unlinked feature
         * @param reference the feature of the reference
         */
        public UnlinkedRawReference(Id id, RawReference reference) {
            super(reference.name());
            this.id = id;

            id(reference.id());
            isContainment(reference.isContainment());
            isMany(reference.isMany());
            index(reference.index());
            idReference(reference.idReference());
        }
    }
}
