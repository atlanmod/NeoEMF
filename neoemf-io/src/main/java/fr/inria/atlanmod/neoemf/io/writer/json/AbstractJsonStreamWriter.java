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
	}

	@Override
	public final void onStartElement(ProxyElement element) throws IOException {
		System.out.println("__________________");
		super.onStartElement(element);
		System.out.println("ON START ELEM");
		writeStartElement(element.getMetaClass().getNamespace().getUri(), element.getName());
		System.out.println("__________________");
	}

	@Override
	public void onAttribute(ProxyAttribute attribute, List<Object> values) throws IOException {
		System.out.println("__________________");
		System.out.println(attribute.getName());
		if (!attribute.isMany()) {
			System.out.println("SINGLE ATTR");
			writeAttribute(attribute.getName(), values.get(0));
		} else {
			System.out.println("MULTIPLE ATTR");
			writeMultipleAttributes(attribute.getName(), values);
		}
		System.out.println("__________________");
	}

	@Override
	public void onReference(ProxyReference reference, List<Id> values) throws IOException {
		System.out.println("REF");
		if (reference.isContainment()) {
			System.out.println("CONTAINMENT");
			writeStartContainmentReference(reference.getName());
			return;
		}
		if (!reference.isMany()) {
			writeAttribute(reference.getName(), values.get(0).toHexString());
		}
		else {
			writeAttribute(reference.getName(), values.stream().map(Id::toHexString).collect(Collectors.joining(Strings.SPACE)));
		}
	}

	@Override
	public void onEndElement() throws IOException {
		super.onEndElement();
		System.out.println("__________________");
		System.out.println("ON END ELEM");
		writeEndElement();
		System.out.println("__________________");
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
	 *
	 * @throws IOException if an I/O error occurs when writing
	 */
	protected abstract void writeStartElement(String namespace, String name) throws IOException;

	/**
	 * Writes an attribute of the current element.
	 *
	 * @param name  the name of the attribute
	 * @param value the value of the attribute
	 *
	 * @throws IOException if an I/O error occurs when writing
	 */
	protected abstract void writeAttribute(String name, Object value) throws IOException;

	/**
	 * Writes a multi valued attribute.
	 *
	 * @param name  the name of the attribute
	 * @param values multiple values of the attribute
	 *
	 * @throws IOException if an I/O error occurs when writing
	 */
	protected abstract void writeMultipleAttributes(String name, List<Object> values) throws IOException;

	/**
	 * Writes characters.
	 *
	 * @param characters the characters
	 *
	 * @throws IOException if an I/O error occurs when writing
	 */
	protected abstract void writeCharacters(String characters) throws IOException;

	/**
	 * Writes the start of a containment reference
	 *
	 * @param name the name fo the reference
	 *
	 * @throws IOException
	 */
	protected abstract void writeStartContainmentReference(String name) throws IOException;

	/**
	 * Writes the end of a containment reference
	 *
	 * @throws IOException
	 */
	protected abstract void writeEndContainmentReference() throws IOException;

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
