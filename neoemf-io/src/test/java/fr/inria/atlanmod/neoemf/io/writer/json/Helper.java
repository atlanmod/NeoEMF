package fr.inria.atlanmod.neoemf.io.writer.json;

import fr.inria.atlanmod.neoemf.data.im.DefaultInMemoryBackend;
import fr.inria.atlanmod.neoemf.data.mapping.DataMapper;
import fr.inria.atlanmod.neoemf.io.Migrator;
import fr.inria.atlanmod.neoemf.tests.sample.SampleFactory;
import fr.inria.atlanmod.neoemf.tests.sample.SamplePackage;
import org.atlanmod.commons.log.Log;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.emfjson.jackson.resource.JsonResourceFactory;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.Collections;
import java.util.Map;
import java.util.function.Consumer;

public class Helper {
    private static final String testOutputPath = "src/test/resources/test-output/";

    private static Resource resourceEmfJsonJackson;
    private static Resource resourceXmi;
    private static String currentTargetPath;
    private static String packageName = SamplePackage.eNAME;

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

    static private void saveWithEmfJsonJackson(Consumer<Resource> populator, @NotNull String targetPath) throws IOException {
        ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getResourceFactoryRegistry()
                .getExtensionToFactoryMap()
                .put(packageName, new JsonResourceFactory());
        Log.info("Exporting to file... [{0}]", currentTargetPath + "test-jacksonjson-write." + packageName);
        resourceEmfJsonJackson = resourceSet.createResource(URI.createFileURI(currentTargetPath + "test-jacksonjson-write." + packageName));
        populator.accept(resourceEmfJsonJackson);
        resourceEmfJsonJackson.save(Collections.EMPTY_MAP);
    }

    static private void saveWithEmfXmi(Consumer<Resource> populator, @NotNull String targetPath) throws IOException {
        final Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
        final Map<String, Object> m = reg.getExtensionToFactoryMap();
        m.put(packageName, new XMIResourceFactoryImpl());

        ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getResourceFactoryRegistry()
                .getExtensionToFactoryMap()
                .put(packageName, new XMIResourceFactoryImpl());
        Log.info("Exporting to file... [{0}]", currentTargetPath + "test-write-xmi." + packageName);
        resourceXmi = resourceSet.createResource(URI.createFileURI(currentTargetPath + "test-write-xmi." + packageName));
        populator.accept(resourceXmi);
        resourceXmi.save(Collections.EMPTY_MAP);
    }

    static private DataMapper getMapperFromXmi() throws IOException {
        DataMapper mapper = new DefaultInMemoryBackend();
        InputStream in = new FileInputStream(currentTargetPath + "test-write-xmi." + packageName);
        // xmi to mapper
        Migrator.fromXmi(in)
                .toMapper(mapper)
                .migrate();
        return mapper;
    }

    static private void mapperToJson(DataMapper mapper) throws IOException {
        // mapper to json (our implementation)
        Log.info("Exporting to file... [{0}]", currentTargetPath + "test-write-json." + packageName);
        File targetFileJSON = new File(currentTargetPath + "test-write-json." + packageName);
        Migrator.fromMapper(mapper)
                .toJson(targetFileJSON)
                .migrate();
    }


    static void testMigration(Consumer<Resource> populator, @NotNull String targetPath) {
        try {
            currentTargetPath = testOutputPath + targetPath;
            deleteDirectory(currentTargetPath);

            // java to emfjson-jackson (for testing/comparison purpose)
            saveWithEmfJsonJackson(populator, targetPath);

            // java to xmi (used in the Migrator input)
            saveWithEmfXmi(populator, targetPath);

            // migrate xmi to mapper
            DataMapper mapper = getMapperFromXmi();

            // migrate mapper to json
            // mapperToJson(mapper);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
