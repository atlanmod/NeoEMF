package fr.inria.atlanmod.neoemf.io.reader.json;

import fr.inria.atlanmod.neoemf.io.reader.AbstractStreamReader;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStream;

public class AbstractJsonStreamReader extends AbstractStreamReader {

	@Override
	public void parse(InputStream stream) throws IOException {

	}

	@Override
	protected boolean isSpecialAttribute(@Nullable String prefix, String name, String value) throws IOException {
		return false;
	}
}
