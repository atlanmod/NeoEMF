package fr.inria.atlanmod.neoemf.data.structure;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.PersistentEObject;

import org.eclipse.emf.ecore.EStructuralFeature;

import java.util.Objects;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.common.Preconditions.checkArgument;
import static fr.inria.atlanmod.common.Preconditions.checkNotNull;

/**
 * An abstract {@link FeatureKey}.
 */
@ParametersAreNonnullByDefault
public abstract class AbstractFeatureKey implements FeatureKey {

    /**
     * The position that indicates that this feature has no position.
     */
    private static final int NO_POSITION = -1;

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
     * The position of this key.
     */
    @Nonnegative
    protected final int position;

    /**
     * Constructs a new {@code AbstractFeatureKey} with the given {@code id} and the given {@code name}.
     *
     * @param id   the identifier of the {@link PersistentEObject}
     * @param name the name of the {@link EStructuralFeature} of the {@link PersistentEObject}
     */
    protected AbstractFeatureKey(Id id, String name) {
        this(id, name, NO_POSITION);
    }

    /**
     * Constructs a new {@code ManyFeatureKey} with the given {@code id} and the given {@code name}, which are
     * used as a simple representation of a feature of an object. The "multi-valued" characteristic is identified with
     * the {@code position}.
     *
     * @param id       the identifier of the {@link PersistentEObject}
     * @param name     the name of the {@link EStructuralFeature} of the {@link PersistentEObject}
     * @param position the position of the {@link EStructuralFeature}
     *
     * @throws NullPointerException     if any argument is {@code null}
     * @throws IllegalArgumentException if the {@code position} is negative, if {@code isMany() == true}
     */
    protected AbstractFeatureKey(Id id, String name, int position) {
        this.id = checkNotNull(id);
        this.name = checkNotNull(name);

        if (isMany()) {
            checkArgument(position >= 0, "Position must be >= 0");
        }
        this.position = position;
    }

    @Nonnull
    @Override
    public Id id() {
        return id;
    }

    @Nonnull
    @Override
    public String name() {
        return name;
    }

    @Nonnegative
    public int position() {
        return position;
    }

    /**
     * Creates a new {@link ManyFeatureKey} with the {@link Id} and the name of this {@code FeatureKey}, and adding
     * the given {@code position}.
     *
     * @param position the position of the {@link EStructuralFeature}
     *
     * @return a new {@link ManyFeatureKey}
     *
     * @throws IllegalArgumentException if the {@code position} is negative
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

        int comparison = Boolean.compare(isMany(), o.isMany());

        if (comparison == 0) {
            comparison = id.compareTo(o.id());
        }

        if (comparison == 0) {
            comparison = name.compareTo(o.name());
        }

        if (comparison == 0 && isMany()) {
            comparison = Integer.compare(position, o.position());
        }

        return comparison;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, position);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AbstractFeatureKey that = (AbstractFeatureKey) o;
        return isMany() == that.isMany()
                && position == that.position
                && Objects.equals(id, that.id)
                && Objects.equals(name, that.name);
    }
}
