package fr.inria.atlanmod.neoemf.io.writer.json;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.io.proxy.ProxyAttribute;
import fr.inria.atlanmod.neoemf.io.proxy.ProxyElement;
import fr.inria.atlanmod.neoemf.io.proxy.ProxyReference;
import fr.inria.atlanmod.neoemf.io.writer.AbstractStreamWriter;
import org.atlanmod.commons.primitive.Strings;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractJsonStreamWriter extends AbstractStreamWriter {
    /**
     * Constructs a new {@code AbstractStreamWriter} with the given {@code stream}.
     *
     * @param stream the stream where to write data
     */
    public AbstractJsonStreamWriter(OutputStream stream) {
        super(stream);
    }

    @Override
    public void onInitialize() throws IOException {
        writeStartDocument();
    }

    @Override
    public final void onStartElement(ProxyElement element) throws IOException {
        super.onStartElement(element);
        writeStartElement(element.getMetaClass().getNamespace().getUri(), element.getMetaClass().getName());
    }

    @Override
    public void onAttribute(ProxyAttribute attribute, List<Object> values) throws IOException {
        if (!attribute.isMany()) {
            writeAttribute(attribute.getName(), values.get(0));
        } else {
            writeMultipleAttributes(attribute.getName(), values);
        }
    }

    @Override
    public void onReference(ProxyReference reference, List<Id> values) throws IOException {
        if (reference.isContainment()) {
            if (reference.isMany()) {
                writeStartMultiValuedContainmentReference(reference.getName());
            } else {
                writeStartContainmentReference(reference.getName());
            }
            return;
        }
        if (reference.isMany()) {
            writeAttribute(reference.getName(), values.stream().map(Id::toHexString).collect(Collectors.joining(Strings.SPACE)));
        } else {
            writeAttribute(reference.getName(), values.get(0).toHexString());
        }
    }

    @Override
    public void onEndElement() throws IOException {
        super.onEndElement();
        writeEndElement();
    }

    @Override
    public void onComplete() throws IOException {
        writeEndDocument();
    }

    /**
     * Writes the start of a document, including the general header.
     *
     * @throws IOException if an I/O error occurs when writing
     */
    protected abstract void writeStartDocument() throws IOException;

    /**
     * Writes the start of an element {@code name}
     *
     * @param name the name of the element
     * @throws IOException if an I/O error occurs when writing
     */
    protected abstract void writeStartElement(String namespace, String name) throws IOException;

    /**
     * Writes an attribute of the current element.
     *
     * @param name  the name of the attribute
     * @param value the value of the attribute
     * @throws IOException if an I/O error occurs when writing
     */
    protected abstract void writeAttribute(String name, Object value) throws IOException;

    /**
     * Writes a multi valued attribute.
     *
     * @param name   the name of the attribute
     * @param values multiple values of the attribute
     * @throws IOException if an I/O error occurs when writing
     */
    protected abstract void writeMultipleAttributes(String name, List<Object> values) throws IOException;

    /**
     * Writes characters.
     *
     * @param characters the characters
     * @throws IOException if an I/O error occurs when writing
     */
    protected abstract void writeCharacters(String characters) throws IOException;

    /**
     * Writes the start of a containment reference
     *
     * @param name the name fo the reference
     * @throws IOException
     */
    protected abstract void writeStartContainmentReference(String name) throws IOException;

    /**
     * Write the start of a multi-valued containment reference
     *
     * @param name the name of the reference
     */
    protected abstract void writeStartMultiValuedContainmentReference(String name) throws IOException;

    /**
     * Writes the end of the current element.
     *
     * @throws IOException if an I/O error occurs when writing
     */
    protected abstract void writeEndElement() throws IOException;

    /**
     * Writes the end of the document and finalizes the migration.
     *
     * @throws IOException if an I/O error occurs when writing
     */
    protected abstract void writeEndDocument() throws IOException;
}
