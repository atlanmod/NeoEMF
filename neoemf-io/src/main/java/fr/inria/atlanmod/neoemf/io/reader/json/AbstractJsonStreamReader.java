package fr.inria.atlanmod.neoemf.io.reader.json;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jsonldjava.utils.Obj;
import fr.inria.atlanmod.neoemf.io.reader.AbstractStreamReader;
import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import org.eclipse.emf.ecore.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;


public class AbstractJsonStreamReader extends AbstractStreamReader {

	private final Logger logger = LoggerFactory.getLogger(AbstractJsonStreamReader.class);

	/*public AbstractJsonStreamReader(){
		JsonReaderHelper.resourceSet.getResourceFactoryRegistry()
				.getExtensionToFactoryMap()
				.put("json", new JsonResourceFactory());

	}*/

	@Override
	public void parse(@NotNull InputStream stream) throws IOException {


		/*
		 resource = JsonReaderHelper.mapper.reader()
				.withAttribute(EMFContext.Attributes.RESOURCE_SET, JsonReaderHelper.resourceSet)
				.withAttribute(EMFContext.Attributes.RESOURCE_URI, JsonReaderHelper.resourceData)
				.forType(Resource.class)
				.readValue(stream);
		 */

			String theString = IOUtils.toString(stream, StandardCharsets.UTF_8);
			ObjectMapper mapper = new ObjectMapper();
			Reader reader = new StringReader(theString);


	}

	@Override
	protected boolean isSpecialAttribute(@Nullable String prefix, String name, String value) throws IOException {
		return false;
	}
}
