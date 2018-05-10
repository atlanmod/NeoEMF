package fr.inria.atlanmod.neoemf.data.mongodb.model;

import fr.inria.atlanmod.commons.annotation.VisibleForReflection;

import org.bson.codecs.pojo.annotations.BsonId;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * Model for the "instances" MongoDb collection.
 * Represents an instance stored by the {@link fr.inria.atlanmod.neoemf.data.mongodb.MongoDbBackend}.
 */
@ParametersAreNonnullByDefault
public class StoredInstance {

    /**
     * The id of the instance (_id)
     */
    @BsonId
    private String id;

    /**
     * The **complete** name of the meta class of the instance (package.ClassName)
     */
    private MetaClass metaClass;

    /**
     * This instance's container
     */
    private Container container;

    /**
     * The singlevaluedReferences
     */
    private Map<String, String> singlevaluedReferences = new HashMap<>();

    /**
     * The Multivalued singlevaluedReferences
     */
    private Map<String, List<String>> multivaluedReferences = new HashMap<>();

    /**
     * Singlevalued singlevaluedValues
     */
    private Map<String, String> singlevaluedValues = new HashMap<>();

    /**
     * Multivalued singlevaluedValues
     */
    private HashMap<String, List<String>> multivaluedValues = new HashMap<>();

    @VisibleForReflection
    public StoredInstance() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MetaClass getMetaClass() {
        return metaClass;
    }

    public void setMetaClass(MetaClass metaClass) {
        this.metaClass = metaClass;
    }

    public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        this.container = container;
    }

    public Map<String, String> getSinglevaluedReferences() {
        return singlevaluedReferences;
    }

    @VisibleForReflection
    public void setSinglevaluedReferences(Map<String, String> singlevaluedReferences) {
        this.singlevaluedReferences = singlevaluedReferences;
    }

    public HashMap<String, List<String>> getMultivaluedValues() {
        return multivaluedValues;
    }

    @VisibleForReflection
    public void setMultivaluedValues(HashMap<String, List<String>> multivaluedValues) {
        this.multivaluedValues = multivaluedValues;
    }

    public Map<String, String> getSinglevaluedValues() {
        return singlevaluedValues;
    }

    @VisibleForReflection
    public void setSinglevaluedValues(Map<String, String> singlevaluedValues) {
        this.singlevaluedValues = singlevaluedValues;
    }

    public Map<String, List<String>> getMultivaluedReferences() {
        return multivaluedReferences;
    }

    @VisibleForReflection
    public void setMultivaluedReferences(Map<String, List<String>> references) {
        this.multivaluedReferences = references;
    }
}
