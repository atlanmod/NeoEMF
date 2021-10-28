package fr.inria.atlanmod.neoemf.io.writer.json;

import java.io.OutputStream;

public class JsonStreamWriter extends AbstractJsonStreamWriter {
	/**
	 * Constructs a new {@code AbstractStreamWriter} with the given {@code stream}.
	 *
	 * @param stream the stream where to write data
	 */
	protected JsonStreamWriter(OutputStream stream) {
		super(stream);
	}
}
