package fr.inria.atlanmod.neoemf.data.mongodb.model;

/**
 * Represents a meta class
 */
public class MetaClass {
    private String name;
    private String uri;

    public MetaClass(){

    }

    public MetaClass(String name, String uri)
    {
        this.name = name;
        this.uri = uri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
