package fr.inria.atlanmod.neoemf.data.structure;

import fr.inria.atlanmod.neoemf.core.Id;

import java.io.Serializable;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

/**
 * The simple representation of a {@link org.eclipse.emf.ecore.EStructuralFeature} of a {@link
 * fr.inria.atlanmod.neoemf.core.PersistentEObject}.
 */
public interface FeatureKey extends Comparable<FeatureKey>, Serializable {

    /**
     * Returns the identifier of the object using this feature.
     *
     * @return the identifier
     */
    @Nonnull
    Id id();

    /**
     * Returns the name of this feature.
     *
     * @return the name
     */
    @Nonnull
    String name();

    /**
     * Returns the position of this feature.
     * <p>
     * If {@code isMany() == false}, then returns {@code -1}.
     *
     * @return the position
     */
    @Nonnegative
    int position();

    /**
     * Checks if the feature is multi-valued.
     *
     * @return {@code true} if this feature is multi-valued
     */
    boolean isMany();
}
