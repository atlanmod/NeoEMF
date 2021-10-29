package fr.inria.atlanmod.neoemf.io.writer.json;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.OutputStream;

import javax.annotation.Nonnull;

public class JsonStreamWriter extends AbstractJsonStreamWriter {
	@Nonnull
	protected final ObjectMapper mapper;

	/**
	 * Constructs a new {@code AbstractStreamWriter} with the given {@code stream}.
	 *
	 * @param stream the stream where to write data
	 */
	public JsonStreamWriter(OutputStream stream) {
		super(stream);
		mapper = new ObjectMapper();
	}

	@Override
	protected void writeStartDocument() throws IOException {

	}

	@Override
	protected void writeStartElement(String name) throws IOException {

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

	}
}
