package fr.inria.atlanmod.neoemf.data.mongodb.document;

import fr.inria.atlanmod.commons.annotation.VisibleForReflection;

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
     * The field name of the single-valued attributes values.
     */
    @Nonnull
    public static final String F_SINGLE_VALUE = "singleValues";

    /**
     * The fields name of the single-valued references.
     */
    @Nonnull
    public static final String F_SINGLE_REFERENCE = "singleReferences";

    /**
     * The field name of the multi-valued attributes values.
     */
    @Nonnull
    public static final String F_MANY_VALUE = "manyValues";

    /**
     * The field name of the multi-valued references.
     */
    @Nonnull
    public static final String F_MANY_REFERENCE = "manyReferences";

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
     * The single-valued attributes values of the related model object.
     */
    @BsonProperty(F_SINGLE_VALUE)
    private Map<String, String> singleValues = new HashMap<>();

    /**
     * The single-valued references of the related model object.
     */
    @BsonProperty(F_SINGLE_REFERENCE)
    private Map<String, String> singleReferences = new HashMap<>();

    /**
     * The multi-valued attributes values of the related model object.
     */
    @BsonProperty(F_MANY_VALUE)
    private HashMap<String, List<String>> manyValues = new HashMap<>();

    /**
     * The multi-valued references of the related model object.
     */
    @BsonProperty(F_MANY_REFERENCE)
    private Map<String, List<String>> manyReferences = new HashMap<>();

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

    public Map<String, String> getSingleValues() {
        return singleValues;
    }

    @VisibleForReflection
    public void setSingleValues(Map<String, String> singleValues) {
        this.singleValues = singleValues;
    }

    public Map<String, String> getSingleReferences() {
        return singleReferences;
    }

    @VisibleForReflection
    public void setSingleReferences(Map<String, String> singleReferences) {
        this.singleReferences = singleReferences;
    }

    public HashMap<String, List<String>> getManyValues() {
        return manyValues;
    }

    @VisibleForReflection
    public void setManyValues(HashMap<String, List<String>> manyValues) {
        this.manyValues = manyValues;
    }

    public Map<String, List<String>> getManyReferences() {
        return manyReferences;
    }

    @VisibleForReflection
    public void setManyReferences(Map<String, List<String>> references) {
        this.manyReferences = references;
    }
}
