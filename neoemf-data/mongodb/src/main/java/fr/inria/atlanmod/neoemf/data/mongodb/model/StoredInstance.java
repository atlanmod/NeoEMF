package fr.inria.atlanmod.neoemf.data.mongodb.model;

import org.bson.codecs.pojo.annotations.BsonId;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Model for the "instances" MongoDb collection
 * Represents an instance stored by the backend
 */
public class StoredInstance {
    public StoredInstance() {

    }

    /**
     * The id of the instance (_id)
     */
    @BsonId
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * The **complete** name of the meta class of the instance (package.ClassName)
     */
    private MetaClass metaClass;

    /**
     * This instance's container
     */
    private SingleFeature container;

    /**
     * The singlevaluedReferences
     */
    private Map<String, String> singlevaluedReferences = new HashMap<>();

    public Map<String, String> getSinglevaluedReferences() {
        return singlevaluedReferences;
    }

    public void setSinglevaluedReferences(Map<String, String> singlevaluedReferences) {
        this.singlevaluedReferences = singlevaluedReferences;
    }

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

    public HashMap<String, List<String>> getMultivaluedValues() {
        return multivaluedValues;
    }

    public void setMultivaluedValues(HashMap<String, List<String>> multivaluedValues) {
        this.multivaluedValues = multivaluedValues;
    }

    public Map<String, String> getSinglevaluedValues() {
        return singlevaluedValues;
    }

    public void setSinglevaluedValues(Map<String, String> singlevaluedValues) {
        this.singlevaluedValues = singlevaluedValues;
    }

    public Map<String, List<String>> getMultivaluedReferences() {
        return multivaluedReferences;
    }

    public void setMultivaluedReferences(Map<String, List<String>> references) {
        this.multivaluedReferences = references;
    }


    public MetaClass getMetaClass() {
        return metaClass;
    }

    public void setMetaClass(MetaClass metaClass) {
        this.metaClass = metaClass;
    }

    public SingleFeature getContainer() {
        return container;
    }

    public void setContainer(SingleFeature container) {
        this.container = container;
    }
}
