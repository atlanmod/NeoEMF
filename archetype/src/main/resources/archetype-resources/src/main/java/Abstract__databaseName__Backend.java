package ${package};

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.AbstractBackend;
import fr.inria.atlanmod.neoemf.data.bean.ClassBean;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;
import fr.inria.atlanmod.neoemf.data.mapping.DataMapper;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.atlanmod.commons.Throwables;

import static org.atlanmod.commons.Preconditions.checkNotNull;

/**
 * An abstract {@link ${databaseName}Backend} that provides overall behavior for the management of a ${databaseName}
 * database.
 */
@ParametersAreNonnullByDefault
abstract class Abstract${databaseName}Backend extends AbstractBackend implements ${databaseName}Backend {

    /**
     * Constructs a new {@code Abstract${databaseName}Backend}.
     */
    protected Abstract${databaseName}Backend() {
        // TODO Implement this constructor
    }

    @Override
    protected void internalSave() throws IOException {
        // TODO Implement this method
    }

    @Override
    protected void internalClose() throws IOException {
        // TODO Implement this method
    }

    @Override
    protected void internalCopyTo(DataMapper target) {
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
    public Stream<Id> allInstancesOf(Set<ClassBean> metaClasses) {
        // TODO Implement this method
        throw Throwables.notImplementedYet("allInstancesOf");
    }
}
