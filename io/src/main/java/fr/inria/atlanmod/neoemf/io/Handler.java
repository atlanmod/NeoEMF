package fr.inria.atlanmod.neoemf.io;

/**
 * A basic handler.
 */
public interface Handler {

    /**
     * Handle the start of a document.
     */
    void handleStartDocument() throws Exception;

    /**
     * Handle the start of an element.
     *
     * @param namespace the namespace of the element
     * @param localName the name of the element in its namespace
     * @param reference the reference identifier of the element
     */
    void handleStartElement(String namespace, String localName, String reference) throws Exception;

    /**
     * Handle an attribute in the current element.
     * <p/>
     * An attribute is a simple key/value.
     *
     * @param namespace the namespace of the attribute
     * @param localName the name of the attribute in its namespace
     * @param value     the value of the attribute
     */
    void handleAttribute(String namespace, String localName, String value) throws Exception;

    /**
     * Handle a reference from the current element to another element.
     * <p/>
     * A reference is an attribute which is link to another element.
     *
     * @param namespace the namespace of the reference
     * @param localName the name of the reference in its namespace
     * @param reference the referenced element identifier
     */
    void handleReference(String namespace, String localName, String reference) throws Exception;

    /**
     * Handle the end of the current element.
     */
    void handleEndElement() throws Exception;

    /**
     * Handle the end of a document.
     */
    void handleEndDocument() throws Exception;
}
