package fr.inria.atlanmod.neoemf.io.writer.json;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class JsonStreamWriter extends AbstractJsonStreamWriter {
    @Nonnull
    protected final ObjectMapper mapper;
    @Nonnull
    protected final JsonGenerator jGenerator;
    protected int id;

    /**
     * Constructs a new {@code AbstractStreamWriter} with the given {@code stream}.
     *
     * @param stream the stream where to write data
     */
    public JsonStreamWriter(OutputStream stream) throws IOException {
        super(stream);
        mapper = new ObjectMapper();
        jGenerator = mapper.getFactory().createGenerator(target);
        jGenerator.useDefaultPrettyPrinter();
        id = 0;
    }

    @Override
    protected void writeStartDocument() throws IOException {
    }

    @Override
    protected void writeStartElement(String namespace, String name) throws IOException {
        jGenerator.writeStartObject();
        jGenerator.writeStringField("eClass", namespace + "#//" + name);
    }

    @Override
    protected void writeAttribute(String name, Object value) throws IOException {
        jGenerator.writeObjectField(name, value);
    }

    @Override
    protected void writeMultipleAttributes(String name, List<Object> values) throws IOException {
        jGenerator.writeArrayFieldStart(name);
        for (Object v : values) {
            jGenerator.writeObject(v);
        }
        jGenerator.writeEndArray();
    }

    @Override
    protected void writeStartContainmentReference(String name) throws IOException {
        jGenerator.writeFieldName(name);
        jGenerator.writeStartObject();
    }

    @Override
    protected void writeStartMultiValuedContainmentReference(String name) throws IOException {
        jGenerator.writeFieldName(name);
        jGenerator.writeStartArray();
    }

    @Override
    protected void writeCharacters(String characters) throws IOException {
        jGenerator.writeString(characters);
    }

    @Override
    protected void writeEndElement() throws IOException {
        try {
            jGenerator.writeEndObject();
        } catch (JsonGenerationException e) {
            jGenerator.writeEndArray();
            jGenerator.writeEndObject();
        }
    }

    @Override
    protected void writeEndDocument() throws IOException {
        jGenerator.close();
    }
}
