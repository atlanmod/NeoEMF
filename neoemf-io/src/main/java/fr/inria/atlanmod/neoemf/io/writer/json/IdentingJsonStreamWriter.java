package fr.inria.atlanmod.neoemf.io.writer.json;

import java.io.OutputStream;

public class IdentingJsonStreamWriter extends JsonStreamWriter {
	/**
	 * Constructs a new {@code AbstractStreamWriter} with the given {@code stream}.
	 *
	 * @param stream the stream where to write data
	 */
	protected IdentingJsonStreamWriter(OutputStream stream) {
		super(stream);
	}
}
