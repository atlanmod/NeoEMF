package writer.json;

import fr.inria.atlanmod.neoemf.data.Backend;
import fr.inria.atlanmod.neoemf.data.im.DefaultInMemoryBackend;
import fr.inria.atlanmod.neoemf.data.mapping.DataMapper;
import fr.inria.atlanmod.neoemf.io.Migrator;
import fr.inria.atlanmod.neoemf.io.provider.UriProvider;
import fr.inria.atlanmod.neoemf.io.util.ResourceManager;
import fr.inria.atlanmod.neoemf.util.ModelComparisonUtils;
import org.atlanmod.commons.AbstractFileBasedTest;
import org.atlanmod.commons.log.Log;
import org.atlanmod.commons.primitive.Strings;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

class JsonWriterTest {
	static void testWrite(String sourcePath, String targetPath) throws IOException {
		Log.info("Exporting to file... [{0}]", targetPath);
		File targetFile = new File(targetPath);

		try (DataMapper mapper = new DefaultInMemoryBackend(); InputStream in = new FileInputStream(sourcePath)) {
			Migrator.fromXmi(in).toMapper(mapper).migrate();

			Migrator.fromMapper(mapper)
					.toJson(targetFile)
					.migrate();
		}

		// Comparing with EMF
		/*
		EObject actual = ResourceManager.load(URI.createFileURI(targetFile.toString()));
		EObject expected = ResourceManager.load(uri);

		ModelComparisonUtils.assertEObjectAreEqual(actual, expected);

		actual.eResource().unload();
		expected.eResource().unload();
		 */
	}

	public static void main(String[] args) throws IOException {
		String sourcePath = "D:/Users/Daiki/Documents/Ecole/Universite/Master/M2/Capstone/NeoEMF/neoemf-io/src/test/resources/model/subSampleModel/simplestClass/Sample.ecore";
		String targePath = "D:/Users/Daiki/Documents/Ecole/Universite/Master/M2/Capstone/NeoEMF/neoemf-io/src/test/resources/test-output/test-write.json";
		testWrite(sourcePath, targePath);
	}
}
