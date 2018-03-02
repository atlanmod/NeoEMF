package fr.inria.atlanmod.neoemf.data.mongodb.model;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.IdConverters;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;

/**
 * Stores a SingleFeatureBean
 */
public class SingleFeature {
    /**
     * The owner ID hex string
     */
    private String featureOwner;

    /**
     * The id
     */
    private int featureId;

    public SingleFeature() {

    }

    public SingleFeature(String owner, int id) {
        this.featureOwner = owner;
        this.featureId = id;
    }

    public String getOwner() {
        return featureOwner;
    }

    public void setOwner(String owner) {
        this.featureOwner = owner;
    }

    public int getId() {
        return featureId;
    }

    public void setId(int id) {
        this.featureId = id;
    }

    public static SingleFeature fromSingleFeatureBean(SingleFeatureBean bean) {
        return new SingleFeature(bean.owner().toHexString(), bean.id());
    }

    public SingleFeatureBean toSingleFeatureBean() {
        return SingleFeatureBean.of(IdConverters.withHexString().revert(featureOwner), featureId);
    }
}
