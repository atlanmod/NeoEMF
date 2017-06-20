package fr.inria.atlanmod.neoemf.data.store;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.mapper.DataMapper;
import fr.inria.atlanmod.neoemf.data.structure.ClassDescriptor;
import fr.inria.atlanmod.neoemf.data.structure.ManyFeatureKey;
import fr.inria.atlanmod.neoemf.data.structure.SingleFeatureKey;

import org.eclipse.emf.ecore.resource.Resource;

import java.util.List;
import java.util.Optional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * A closed {@link Store}.
 * <p>
 * All methods throws an {@link UnsupportedOperationException}.
 */
@ParametersAreNonnullByDefault
public final class ClosedStore implements Store {

    /**
     * The exceptions thrown when calling methods.
     */
    private static final RuntimeException E = new UnsupportedOperationException(
            "The back-end you are using is closed");

    @Nullable
    @Override
    public Resource.Internal resource() {
        return null;
    }

    @Override
    public void resource(@Nullable Resource.Internal resource) {

    }

    @Nonnull
    @Override
    public Backend backend() {
        throw E;
    }

    @Nonnull
    @Override
    public Optional<ClassDescriptor> metaclassOf(Id id) {
        throw E;
    }

    @Override
    public void metaclassFor(Id id, ClassDescriptor metaclass) {
        throw E;
    }

    @Override
    public boolean hasMetaclass(Id id) {
        throw E;
    }

    @Nonnull
    @Override
    public Iterable<Id> allInstancesOf(ClassDescriptor metaclass, boolean strict) {
        throw E;
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(SingleFeatureKey key) {
        throw E;
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(SingleFeatureKey key, V value) {
        throw E;
    }

    @Override
    public <V> void unsetValue(SingleFeatureKey key) {
        throw E;
    }

    @Override
    public <V> boolean hasValue(SingleFeatureKey key) {
        throw E;
    }

    @Nonnull
    @Override
    public Optional<SingleFeatureKey> containerOf(Id id) {
        throw E;
    }

    @Override
    public void containerFor(Id id, SingleFeatureKey container) {
        throw E;
    }

    @Override
    public void unsetContainer(Id id) {
        throw E;
    }

    @Override
    public boolean hasContainer(Id id) {
        throw E;
    }

    @Override
    public void close() {
        throw E;
    }

    @Override
    public void save() {
        throw E;
    }

    @Override
    public void copyTo(DataMapper target) {
        throw E;
    }

    @Override
    public boolean exists(Id id) {
        throw E;
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueOf(ManyFeatureKey key) {
        throw E;
    }

    @Nonnull
    @Override
    public <V> List<V> allValuesOf(SingleFeatureKey key) {
        throw E;
    }

    @Nonnull
    @Override
    public <V> Optional<V> valueFor(ManyFeatureKey key, V value) {
        throw E;
    }

    @Override
    public <V> boolean hasAnyValue(SingleFeatureKey key) {
        throw E;
    }

    @Override
    public <V> void addValue(ManyFeatureKey key, V value) {
        throw E;
    }

    @Override
    public <V> void addAllValues(ManyFeatureKey key, List<? extends V> collection) {
        throw E;
    }

    @Override
    public <V> int appendValue(SingleFeatureKey key, V value) {
        throw E;
    }

    @Override
    public <V> int appendAllValues(SingleFeatureKey key, List<? extends V> collection) {
        throw E;
    }

    @Nonnull
    @Override
    public <V> Optional<V> removeValue(ManyFeatureKey key) {
        throw E;
    }

    @Override
    public <V> void removeAllValues(SingleFeatureKey key) {
        throw E;
    }

    @Nonnull
    @Override
    public <V> Optional<V> moveValue(ManyFeatureKey source, ManyFeatureKey target) {
        throw E;
    }

    @Override
    public <V> boolean containsValue(SingleFeatureKey key, @Nullable V value) {
        throw E;
    }

    @Nonnull
    @Override
    public <V> Optional<Integer> indexOfValue(SingleFeatureKey key, @Nullable V value) {
        throw E;
    }

    @Nonnull
    @Override
    public <V> Optional<Integer> lastIndexOfValue(SingleFeatureKey key, @Nullable V value) {
        throw E;
    }

    @Nonnull
    @Override
    public <V> Optional<Integer> sizeOfValue(SingleFeatureKey key) {
        throw E;
    }

    @Nonnull
    @Override
    public Optional<Id> referenceOf(SingleFeatureKey key) {
        throw E;
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(SingleFeatureKey key, Id reference) {
        throw E;
    }

    @Override
    public void unsetReference(SingleFeatureKey key) {
        throw E;
    }

    @Override
    public boolean hasReference(SingleFeatureKey key) {
        throw E;
    }

    @Nonnull
    @Override
    public Optional<Id> referenceOf(ManyFeatureKey key) {
        throw E;
    }

    @Nonnull
    @Override
    public List<Id> allReferencesOf(SingleFeatureKey key) {
        throw E;
    }

    @Nonnull
    @Override
    public Optional<Id> referenceFor(ManyFeatureKey key, Id reference) {
        throw E;
    }

    @Override
    public boolean hasAnyReference(SingleFeatureKey key) {
        throw E;
    }

    @Override
    public void addReference(ManyFeatureKey key, Id reference) {
        throw E;
    }

    @Override
    public void addAllReferences(ManyFeatureKey key, List<Id> collection) {
        throw E;
    }

    @Override
    public int appendReference(SingleFeatureKey key, Id reference) {
        throw E;
    }

    @Override
    public int appendAllReferences(SingleFeatureKey key, List<Id> collection) {
        throw E;
    }

    @Nonnull
    @Override
    public Optional<Id> removeReference(ManyFeatureKey key) {
        throw E;
    }

    @Override
    public void removeAllReferences(SingleFeatureKey key) {
        throw E;
    }

    @Nonnull
    @Override
    public Optional<Id> moveReference(ManyFeatureKey source, ManyFeatureKey target) {
        throw E;
    }

    @Override
    public boolean containsReference(SingleFeatureKey key, @Nullable Id reference) {
        throw E;
    }

    @Nonnull
    @Override
    public Optional<Integer> indexOfReference(SingleFeatureKey key, @Nullable Id reference) {
        throw E;
    }

    @Nonnull
    @Override
    public Optional<Integer> lastIndexOfReference(SingleFeatureKey key, @Nullable Id reference) {
        throw E;
    }

    @Nonnull
    @Override
    public Optional<Integer> sizeOfReference(SingleFeatureKey key) {
        throw E;
    }
}
