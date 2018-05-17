package fr.inria.atlanmod.neoemf.data.mongodb.model;

import fr.inria.atlanmod.commons.annotation.VisibleForReflection;
import fr.inria.atlanmod.neoemf.core.IdConverters;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * Represents a container.
 */
@ParametersAreNonnullByDefault
public class Container {

    /**
     * The owner ID hex string
     */
    private String featureOwner;

    /**
     * The id
     */
    private int featureId;

    @VisibleForReflection
    public Container() {
    }

    public Container(String owner, int id) {
        this.featureOwner = owner;
        this.featureId = id;
    }

    @Nonnull
    public static Container fromBean(SingleFeatureBean bean) {
        return new Container(bean.owner().toHexString(), bean.id());
    }

    public String getOwner() {
        return featureOwner;
    }

    @VisibleForReflection
    public void setOwner(String owner) {
        this.featureOwner = owner;
    }

    public int getId() {
        return featureId;
    }

    @VisibleForReflection
    public void setId(int id) {
        this.featureId = id;
    }

    @Nonnull
    public SingleFeatureBean toBean() {
        return SingleFeatureBean.of(IdConverters.withHexString().revert(featureOwner), featureId);
    }
}
