package fr.inria.atlanmod.neoemf.io.writer.json;

import fr.inria.atlanmod.neoemf.data.im.DefaultInMemoryBackend;
import fr.inria.atlanmod.neoemf.data.mapping.DataMapper;
import fr.inria.atlanmod.neoemf.io.Migrator;
import fr.inria.atlanmod.neoemf.tests.sample.SampleFactory;
import org.atlanmod.commons.log.Log;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.emfjson.jackson.resource.JsonResourceFactory;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.function.Consumer;

public class Helper {
    private static final String testOutputPath = "src/test/resources/test-output/";

    private static Resource resourceEmfJsonJackson;
    private static Resource resourceXmi;
    private static String currentTargetPath;

    static boolean deleteDirectory(String path) {
        File directoryToBeDeleted = new File(path);
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file.getPath());
            }
        }
        return directoryToBeDeleted.delete();
    }

    static private DataMapper getMapperFromXmi() throws IOException {
        DataMapper mapper = new DefaultInMemoryBackend();
        InputStream in = new FileInputStream(currentTargetPath + "test-write.xmi");
        // xmi to mapper
        Migrator.fromXmi(in)
                .toMapper(mapper)
                .migrate();
        return mapper;
    }

    static private void mapperToJson(DataMapper mapper) throws IOException {
        // mapper to json (our implementation)
        Log.info("Exporting to file... [{0}]", currentTargetPath + "test-write.json");
        File targetFileJSON = new File(currentTargetPath + "test-write.json");
        Migrator.fromMapper(mapper)
                .toJson(targetFileJSON)
                .migrate();
    }

    static void testMigration(@NotNull String targetPath) throws IOException {
        currentTargetPath = testOutputPath + targetPath;
        // deleteDirectory(currentTargetPath);

        // java to emfjson-jackson (for testing/comparison purpose)
        // saveWithEmfJsonJackson(populator, targetPath);

        // java to xmi (used in the Migrator input)
        // saveWithEmfXmi(populator, targetPath);

        // migrate xmi to mapper
        DataMapper mapper = getMapperFromXmi();

        // migrate mapper to json
        mapperToJson(mapper);
    }
}
