package fr.inria.atlanmod.neoemf.data.mongodb.document;

import org.atlanmod.commons.function.Converter;
import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.core.IdConverters;
import fr.inria.atlanmod.neoemf.data.bean.SingleFeatureBean;

import org.bson.codecs.pojo.annotations.BsonProperty;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * Represents a container.
 */
@ParametersAreNonnullByDefault
public class ContainerDocument {

    /**
     * The field name of the owner of a container.
     */
    @Nonnull
    public static final String F_OWNER = "owner";

    /**
     * The field name of the containing feature.
     */
    @Nonnull
    public static final String F_ID = "id";

    @Nonnull
    private final static Converter<Id, String> CONVERTER = IdConverters.withHexString();

    /**
     * The identifier of the owner of this container.
     */
    @BsonProperty(F_OWNER)
    private String owner;

    /**
     * The identifier of the containing feature.
     */
    @BsonProperty(F_ID)
    private int id;

    @Nonnull
    public static ContainerDocument fromBean(SingleFeatureBean bean) {
        ContainerDocument c = new ContainerDocument();
        c.setOwner(CONVERTER.convert(bean.owner()));
        c.setId(bean.id());
        return c;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Nonnull
    public SingleFeatureBean toBean() {
        return SingleFeatureBean.of(CONVERTER.revert(owner), id);
    }
}
