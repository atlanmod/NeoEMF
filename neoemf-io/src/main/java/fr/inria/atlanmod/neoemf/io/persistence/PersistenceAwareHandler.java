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

package fr.inria.atlanmod.neoemf.io.persistence;

import com.google.common.collect.HashMultimap;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.PersistenceBackend;
import fr.inria.atlanmod.neoemf.io.AlreadyExistingIdException;
import fr.inria.atlanmod.neoemf.io.Handler;
import fr.inria.atlanmod.neoemf.io.structure.RawElement;
import fr.inria.atlanmod.neoemf.io.structure.RawId;
import fr.inria.atlanmod.neoemf.io.structure.RawReference;
import fr.inria.atlanmod.neoemf.util.logging.NeoLogger;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A {@link Handler} for {@link PersistenceBackend}s.
 *
 * @note This handler has a key conflicts resolution feature, but it consumes much more memory than a handler without
 * conflicts resolution. Make sure you have enough memory to avoid heap space.
 */
@ParametersAreNonnullByDefault
public class PersistenceAwareHandler extends AbstractPersistenceHandler {

    /**
     * Map holding the unlinked elements, waiting until their reference is created.
     *
     * @note In case of conflict detection only.
     */
    private final HashMultimap<String, UnlinkedRawReference> unlinkedElements;

    /**
     * In-memory cache that holds conflicted {@link Id}s, identified by their literal representation.
     *
     * @note In case of conflict detection only.
     */
    private final Map<String, Id> conflictIdsCache = new HashMap<>();

    /**
     * Constructs a new {@code PersistenceAwareHandler} on the given {@code backend}.
     *
     * @param backend the back-end where to store data
     */
    protected PersistenceAwareHandler(PersistenceBackend backend) {
        super(backend);

        this.unlinkedElements = HashMultimap.create();
    }

    @Override
    public void handleStartElement(RawElement element) {
        super.handleStartElement(element);

        tryLink(element.id().value(), idsStack.getLast());
    }

    @Override
    public void handleEndDocument() {
        super.handleEndDocument();

        long unlinkedNumber = unlinkedElements.size();
        if (unlinkedNumber > 0) {
            NeoLogger.warn("Some elements have not been linked ({0})", unlinkedNumber);
            for (String e : unlinkedElements.asMap().keySet()) {
                NeoLogger.warn(" > " + e);
            }
            unlinkedElements.clear();
        }

        long conflictedId = conflictIdsCache.size();
        if (conflictedId > 0) {
            NeoLogger.info("{0} key conflicts", conflictedId);
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
                // Id already exists in the back-end : try another
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
    protected void persist(Id id) {
        if (backend.has(id)) {
            throw new AlreadyExistingIdException("Already existing Id: " + id);
        }
        backend.create(id);
    }

    @Override
    protected void checkExists(Id id) {
        if (!backend.has(id)) {
            throw new NoSuchElementException("Unable to find an element with Id: " + id);
        }
    }

    /**
     * Tries to link references that have not been linked at their creation.
     *
     * @param reference the reference of the targeted element
     * @param id        the identifier of the targeted element
     */
    private void tryLink(String reference, Id id) {
        for (UnlinkedRawReference e : unlinkedElements.removeAll(reference)) {
            addReference(e.id, e, id);
        }
    }

    /**
     * A simple representation of an element that could not be linked when it was created.
     */
    private class UnlinkedRawReference extends RawReference {

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
            containment(reference.containment());
            many(reference.many());
            index(reference.index());
            idReference(reference.idReference());
        }
    }
}
