/*
 * Copyright (c) 2013-2018 Atlanmod, Inria, LS2N, and IMT Nantes.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v2.0 which accompanies
 * this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
 */

package fr.inria.atlanmod.neoemf.data.mongodb;

import fr.inria.atlanmod.commons.Throwables;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.AbstractBackend;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;
import fr.inria.atlanmod.neoemf.data.mapping.DataMapper;

import java.util.Optional;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.commons.Preconditions.checkNotNull;

/**
 * An abstract {@link MongoDbBackend} that provides overall behavior for the management of a MongoDb
 * database.
 */
@ParametersAreNonnullByDefault
abstract class AbstractMongoDbBackend extends AbstractBackend implements MongoDbBackend {

    /**
     * Constructs a new {@code AbstractMongoDbBackend}.
     */
    protected AbstractMongoDbBackend() {
        // TODO Implement this constructor
    }

    @Override
    public void save() {
        // TODO Implement this method
    }

    @Override
    protected void innerClose() {
        // TODO Implement this method
    }

    @Override
    protected void innerCopyTo(DataMapper target) {
        // TODO Implement this method
        throw Throwables.notImplementedYet("innerCopyTo");
    }

    @Nonnull
    @Override
    public Optional<SingleFeatureBean> containerOf(Id id) {
        checkNotNull(id, "id");

        // TODO Implement this method
        throw Throwables.notImplementedYet("containerOf");
    }

    @Override
    public void containerFor(Id id, SingleFeatureBean container) {
        checkNotNull(id, "id");
        checkNotNull(container, "container");

        // TODO Implement this method
        throw Throwables.notImplementedYet("containerFor");
    }

    @Override
    public void removeContainer(Id id) {
        checkNotNull(id, "id");

        // TODO Implement this method
        throw Throwables.notImplementedYet("removeContainer");
    }

    @Nonnull
    @Override
    public Optional<ClassBean> metaClassOf(Id id) {
        checkNotNull(id, "id");

        // TODO Implement this method
        throw Throwables.notImplementedYet("metaClassOf");
    }

    @Override
    public boolean metaClassFor(Id id, ClassBean metaClass) {
        checkNotNull(id, "id");
        checkNotNull(metaClass, "metaClass");

        // TODO Implement this method
        throw Throwables.notImplementedYet("metaClassFor");
    }

    @Nonnull
    @Override
    public Iterable<Id> allInstancesOf(Set<ClassBean> metaClasses) {
        // TODO Implement this method
        throw Throwables.notImplementedYet("allInstancesOf");
    }
}
