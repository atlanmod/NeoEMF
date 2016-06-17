package fr.inria.atlanmod.neoemf.io;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.io.Handler;

/**
 *
 */
public interface PersistenceHandler extends Handler {

    /**
     * Handle the start of an element.
     *
     * @param namespace the namespace of the element
     * @param localName the name of the element in its namespace
     */
    void handleStartElement(Id id, String namespace, String localName) throws Exception;

    /**
     * Handle an attribute in the current element.
     * <p/>
     * An attribute is a simple key/value.
     *
     * @param namespace the namespace of the attribute
     * @param localName the name of the attribute in its namespace
     * @param value     the value of the attribute
     */
    void handleAttribute(Id id, String namespace, String localName, String value) throws Exception;

    /**
     * Handle a reference from the current element to another element.
     * <p/>
     * A reference is an attribute which is link to another element.
     *
     * @param namespace the namespace of the attribute
     * @param localName the name of the attribute in its namespace
     * @param idReference the id of the targetted element
     */
    void handleReference(Id id, String namespace, String localName, Id idReference) throws Exception;

    /**
     * Handle the end of the current element.
     */
    void handleEndElement(Id id) throws Exception;
}
