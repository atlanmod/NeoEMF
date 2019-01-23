package fr.inria.atlanmod.neoemf.data.mongodb.document;

import org.atlanmod.commons.annotation.VisibleForReflection;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

/**
 * Model for the "instances" MongoDb collection.
 * Represents an instance stored by the {@link fr.inria.atlanmod.neoemf.data.mongodb.MongoDbBackend}.
 */
@ParametersAreNonnullByDefault
public class ModelDocument {

    /**
     * The field name of the unique identifier of a document.
     */
    @Nonnull
    public static final String F_ID = "_id";

    /**
     * The field name of the meta-class.
     */
    @Nonnull
    public static final String F_METACLASS = "class";

    /**
     * The field name of the container.
     */
    @Nonnull
    public static final String F_CONTAINER = "container";

    /**
     * The field name of the single-valued features values.
     */
    @Nonnull
    public static final String F_SINGLE_FEATURE = "singleFeatures";

    /**
     * The field name of the multi-valued features values.
     */
    @Nonnull
    public static final String F_MANY_FEATURE = "manyFeatures";

    /**
     * The unique identifier of the model object.
     */
    @BsonId
    private String id;

    /**
     * The meta-class of the related model object.
     */
    @BsonProperty(F_METACLASS)
    private ClassDocument metaClass;

    /**
     * The container of the related model object.
     */
    @BsonProperty(F_CONTAINER)
    private ContainerDocument container;

    /**
     * The single-valued features values of the related model object.
     */
    @BsonProperty(F_SINGLE_FEATURE)
    private Map<String, String> singleFeatures = new HashMap<>();

    /**
     * The multi-valued features values of the related model object.
     */
    @BsonProperty(F_MANY_FEATURE)
    private Map<String, List<String>> manyFeatures = new HashMap<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ClassDocument getMetaClass() {
        return metaClass;
    }

    public void setMetaClass(ClassDocument metaClass) {
        this.metaClass = metaClass;
    }

    public ContainerDocument getContainer() {
        return container;
    }

    public void setContainer(ContainerDocument container) {
        this.container = container;
    }

    public Map<String, String> getSingleFeatures() {
        return singleFeatures;
    }

    @VisibleForReflection
    public void setSingleFeatures(Map<String, String> singleFeatures) {
        this.singleFeatures = singleFeatures;
    }

    public Map<String, List<String>> getManyFeatures() {
        return manyFeatures;
    }

    @VisibleForReflection
    public void setManyFeatures(Map<String, List<String>> manyFeatures) {
        this.manyFeatures = manyFeatures;
    }
}
