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
     * Handle the end of a document.
     */
    void handleEndDocument() throws Exception;
}
