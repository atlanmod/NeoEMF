package fr.inria.atlanmod.neoemf.data.mongodb.model;

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
     * The **complete** name of the class of the instance (package.ClassName)
     */
    private String className;

    /**
     * The ID of this instance's container
     */
    private String containerId;

    /**
     * The instance's attributes
     */
    private HashMap<String, String> attributes;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getContainerId() {
        return containerId;
    }

    public void setContainerId(String containerId) {
        this.containerId = containerId;
    }

    public HashMap<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(HashMap<String, String> attributes) {
        this.attributes = attributes;
    }
}
