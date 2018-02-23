package fr.inria.atlanmod.neoemf.data.mongodb.model;

import org.bson.codecs.pojo.annotations.BsonId;

import java.util.HashMap;

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
     * The instance's attributes
     */
    private HashMap<String, String> attributes;

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

    public HashMap<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(HashMap<String, String> attributes) {
        this.attributes = attributes;
    }
}
