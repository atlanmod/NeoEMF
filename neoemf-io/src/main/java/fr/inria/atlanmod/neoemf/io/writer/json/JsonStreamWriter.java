package fr.inria.atlanmod.neoemf.io.writer.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.OutputStream;

import javax.annotation.Nonnull;

public class JsonStreamWriter extends AbstractJsonStreamWriter {
	@Nonnull
	protected final ObjectMapper mapper;
	@Nonnull
	protected final JsonGenerator jGenerator;

	/**
	 * Constructs a new {@code AbstractStreamWriter} with the given {@code stream}.
	 *
	 * @param stream the stream where to write data
	 */
	public JsonStreamWriter(OutputStream stream) throws IOException {
		super(stream);
		mapper = new ObjectMapper();
		jGenerator = mapper.getFactory().createGenerator(target);
	}

	@Override
	protected void writeStartDocument() throws IOException {
		jGenerator.writeStartArray();
	}

	@Override
	protected void writeStartElement(String name) throws IOException {
		jGenerator.writeStartObject();
	}

	@Override
	protected void writeAttribute(String name, String value) throws IOException {

	}

	@Override
	protected void writeCharacters(String characters) throws IOException {

	}

	@Override
	protected void writeEndElement() throws IOException {

	}

	@Override
	protected void writeEndDocument() throws IOException {
		jGenerator.writeEndArray();
		jGenerator.close();
	}
}
