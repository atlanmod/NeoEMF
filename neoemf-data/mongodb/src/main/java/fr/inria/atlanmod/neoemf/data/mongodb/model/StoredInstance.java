package fr.inria.atlanmod.neoemf.data.mongodb.model;

import org.bson.codecs.pojo.annotations.BsonId;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Model for the "instances" MongoDb collection
 * Represents an instance stored by the backend
 */
public class StoredInstance
{
    public StoredInstance()
    {

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
     * The references
     */
    private Map<String, String> references =  new HashMap<>();

    public Map<String, String> getReferences() { return references; }

    public void setReferences(Map<String, String> references) {
        this.references = references;
    }

    /**
     * The Multivalued references
     */
    private Map<String, List<String>> multivaluedReferences = new HashMap<>();

    /**
     * The values
     */
    private Map<String, String> values = new HashMap<>();

    public Map<String, String> getValues() {
        return values;
    }

    public void setValues(Map<String, String> values) {
        this.values = values;
    }

    public Map<String, List<String>> getMultivaluedReferences() { return multivaluedReferences; }

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
