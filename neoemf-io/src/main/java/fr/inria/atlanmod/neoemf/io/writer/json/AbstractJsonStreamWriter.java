package fr.inria.atlanmod.neoemf.io.writer.json;

import fr.inria.atlanmod.neoemf.core.Id;
import fr.inria.atlanmod.neoemf.io.proxy.ProxyAttribute;
import fr.inria.atlanmod.neoemf.io.proxy.ProxyReference;
import fr.inria.atlanmod.neoemf.io.writer.AbstractStreamWriter;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class AbstractJsonStreamWriter extends AbstractStreamWriter  {
	/**
	 * Constructs a new {@code AbstractStreamWriter} with the given {@code stream}.
	 *
	 * @param stream the stream where to write data
	 */
	protected AbstractJsonStreamWriter(OutputStream stream) {
		super(stream);
	}

	@Override
	public void onAttribute(ProxyAttribute attribute, List<Object> values) throws IOException {

	}

	@Override
	public void onReference(ProxyReference reference, List<Id> values) throws IOException {

	}

	@Override
	public void onInitialize() throws IOException {

	}

	@Override
	public void onComplete() throws IOException {

	}
}
