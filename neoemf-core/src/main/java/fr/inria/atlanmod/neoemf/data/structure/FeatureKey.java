package fr.inria.atlanmod.neoemf.data.structure;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;

import org.eclipse.emf.ecore.EStructuralFeature;

import java.io.Serializable;
import java.util.Objects;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.common.Preconditions.checkNotNull;

/**
 * An abstract representation of a {@link EStructuralFeature} of a {@link PersistentEObject}.
 */
@ParametersAreNonnullByDefault
public abstract class FeatureKey implements Comparable<FeatureKey>, Serializable {

    /**
     * The identifier of the object.
     */
    @Nonnull
    protected final Id id;

    /**
     * The name of the feature of the object.
     */
    @Nonnull
    protected final String name;

    /**
     * Constructs a new {@code FeatureKey} with the given {@code id} and the given {@code name}, which are used as
     * a simple representation of a feature of an object.
     *
     * @param id   the identifier of the {@link PersistentEObject}
     * @param name the name of the {@link EStructuralFeature} of the {@link PersistentEObject}
     */
    protected FeatureKey(Id id, String name) {
        this.id = checkNotNull(id);
        this.name = checkNotNull(name);
    }

    /**
     * Returns the identifier of the {@link PersistentEObject}.
     *
     * @return the identifier of the object
     */
    @Nonnull
    public Id id() {
        return id;
    }

    /**
     * Returns the name of the {@link EStructuralFeature} of the {@link PersistentEObject}.
     *
     * @return the name of the feature
     */
    @Nonnull
    public String name() {
        return name;
    }

    /**
     * Creates a new {@link ManyFeatureKey} with the {@link Id} and the name of this {@code FeatureKey}, and
     * adding the given {@code position}.
     *
     * @param position the position of the {@link EStructuralFeature}
     *
     * @return a new {@link ManyFeatureKey}
     *
     * @see ManyFeatureKey#of(Id, String, int)
     */
    @Nonnull
    public ManyFeatureKey withPosition(@Nonnegative int position) {
        return ManyFeatureKey.of(id, name, position);
    }

    @Override
    public int compareTo(FeatureKey o) {
        if (this == o) {
            return 0;
        }

        int comparison;
        return (comparison = id.compareTo(o.id)) == 0
                ? name.compareTo(o.name)
                : comparison;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!FeatureKey.class.isInstance(o)) {
            return false;
        }

        FeatureKey that = FeatureKey.class.cast(o);
        return Objects.equals(id, that.id)
                && Objects.equals(name, that.name);
    }
}
