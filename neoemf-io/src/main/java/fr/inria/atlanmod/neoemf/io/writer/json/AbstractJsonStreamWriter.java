package fr.inria.atlanmod.neoemf.io.writer.json;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.io.proxy.ProxyAttribute;
import fr.inria.atlanmod.neoemf.io.proxy.ProxyReference;
import fr.inria.atlanmod.neoemf.io.writer.AbstractStreamWriter;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public abstract class AbstractJsonStreamWriter extends AbstractStreamWriter  {
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
		writeEndDocument();
	}

	@Override
	public void onAttribute(ProxyAttribute attribute, List<Object> values) throws IOException {

	}

	@Override
	public void onReference(ProxyReference reference, List<Id> values) throws IOException {

	}

	@Override
	public void onComplete() throws IOException {

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
	 *
	 * @throws IOException if an I/O error occurs when writing
	 */
	protected abstract void writeStartElement(String name) throws IOException;

	/**
	 * Writes an attribute of the current element.
	 *
	 * @param name  the name of the attribute
	 * @param value the value of the attribute
	 *
	 * @throws IOException if an I/O error occurs when writing
	 */
	protected abstract void writeAttribute(String name, String value) throws IOException;

	/**
	 * Writes characters.
	 *
	 * @param characters the characters
	 *
	 * @throws IOException if an I/O error occurs when writing
	 */
	protected abstract void writeCharacters(String characters) throws IOException;

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
