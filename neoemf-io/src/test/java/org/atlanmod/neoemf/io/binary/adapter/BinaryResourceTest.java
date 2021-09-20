package org.atlanmod.neoemf.io.binary.adapter;

import fr.inria.atlanmod.neoemf.io.provider.UriProvider;
import fr.inria.atlanmod.neoemf.io.util.FileAssert;
import fr.inria.atlanmod.neoemf.io.util.ResourceManager;
import fr.inria.atlanmod.neoemf.util.compare.LazyMatchEngineFactory;
import org.atlanmod.neoemf.io.binary.NeoBinaryResourceImpl;
import fr.inria.atlanmod.neoemf.util.ModelComparisonUtils;
import org.atlanmod.commons.AbstractFileBasedTest;
import org.atlanmod.commons.log.Log;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.compare.EMFCompare;
import org.eclipse.emf.compare.match.IMatchEngine;
import org.eclipse.emf.compare.match.impl.MatchEngineFactoryImpl;
import org.eclipse.emf.compare.match.impl.MatchEngineFactoryRegistryImpl;
import org.eclipse.emf.compare.scope.DefaultComparisonScope;
import org.eclipse.emf.compare.scope.IComparisonScope;
import org.eclipse.emf.compare.utils.UseIdentifiers;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class BinaryResourceTest extends AbstractFileBasedTest {
    private static ResourceSetImpl resources;

    @BeforeAll
    static void registerPackages() {
        ResourceManager.registerAllPackages();
        resources = new ResourceSetImpl();


        // Register Binary Resource Factory
        Resource.Factory.Registry registry = Resource.Factory.Registry.INSTANCE;
        Map<String, Object> factories = registry.getExtensionToFactoryMap();

        factories.put("neo", new ResourceFactoryImpl() {
            @Override
            public Resource createResource(URI uri) {
                NeoBinaryResourceImpl newResource = new NeoBinaryResourceImpl(uri);
                return newResource;
            }
        });

        factories.put("bin", new ResourceFactoryImpl() {
            @Override
            public Resource createResource(URI uri) {
                BinaryResourceImpl newResource = new BinaryResourceImpl(uri);
                return newResource;
            }
        });
    }

    @ParameterizedTest(name = "[{index}] source = {0}")
    @ArgumentsSource(UriProvider.UncompressedMixed.class)
    void testBinCopy(URI uri) throws IOException {
        final File targetFile = new File(currentTempFile() + "." + "neo");
        Log.info("#### Binary Resource Round trip {0}", targetFile);

        Resource origin = resources.getResource(uri, true);
        Log.info("Loaded resource with {0} elements", origin.getContents().size());

        Resource binaryResource = resources.createResource(URI.createFileURI(targetFile.getAbsolutePath()));
        binaryResource.getContents().addAll(EcoreUtil.copyAll(origin.getContents()));

        Map<String, Object> options = new HashMap<>();
        options.put(NeoBinaryResourceImpl.OPTION_VERSION, BinaryResourceImpl.BinaryIO.Version.VERSION_1_1);

        // The only option set to true by default:
        options.put(NeoBinaryResourceImpl.OPTION_STYLE_BINARY_FLOATING_POINT, false);
        binaryResource.save(options);

        // Load Binary file

        //resources.getLoadOptions().putAll(options);
        Resource actual = resources.getResource(URI.createFileURI(targetFile.getAbsolutePath()), false);
        Log.info("Loaded binary resource with {0} elements", actual.getContents().size());


        Resource expected = resources.getResource(uri, true);
        assertEObjectAreEqual(actual.getContents().get(0), expected.getContents().get(0));

        expected.unload();
        actual.unload();
        binaryResource.unload();
    }

    @ParameterizedTest(name = "[{index}] source = {0}")
    @ArgumentsSource(UriProvider.UncompressedMixed.class)
    void testBinCopyWithOptions(URI uri) throws IOException {
        final File targetFile = new File(currentTempFile() + "." + "neo");
        Log.info("#### Binary Resource Round trip {0}", targetFile);

        Resource origin = resources.getResource(uri, true);
        Log.info("Loaded resource with {0} elements", origin.getContents().size());

        Resource binaryResource = resources.createResource(URI.createFileURI(targetFile.getAbsolutePath()));
        binaryResource.getContents().addAll(EcoreUtil.copyAll(origin.getContents()));


        Map<String, Object> options = new HashMap<>();
        options.put(NeoBinaryResourceImpl.OPTION_VERSION, BinaryResourceImpl.BinaryIO.Version.VERSION_1_1);
        options.put(NeoBinaryResourceImpl.OPTION_STYLE_DATA_CONVERTER, true);
        options.put(NeoBinaryResourceImpl.OPTION_STYLE_BINARY_FLOATING_POINT, true);
        options.put(NeoBinaryResourceImpl.OPTION_STYLE_BINARY_DATE, true);
        options.put(NeoBinaryResourceImpl.OPTION_STYLE_BINARY_ENUMERATOR, true);
        options.put(NeoBinaryResourceImpl.OPTION_STYLE_PROXY_ATTRIBUTES, true);

        binaryResource.save(options);

        // Load Binary file

        resources.getLoadOptions().putAll(options);
        Resource actual = resources.getResource(URI.createFileURI(targetFile.getAbsolutePath()), false);
        Log.info("Loaded binary resource with {0} elements", actual.getContents().size());


        Resource expected = resources.getResource(uri, true);
        assertEObjectAreEqual(actual.getContents().get(0), expected.getContents().get(0));

        expected.unload();
        actual.unload();
        binaryResource.unload();
    }

    /**
     * source:XMI -> temp:BIN -> target:XMI
     * <p>
     * We cannot ensure that the source and the target resources are the same, because proxies get resolved
     * during the copye.
     */
    @ParameterizedTest(name = "[{index}] container = {0} containment = {1}")
    @ArgumentsSource(UriProvider.WithProxies.class)
    void testBinCopyWithProxies(URI containerURI, URI containmentURI) throws IOException {
        final File targetXmi = new File(currentTempFile() + ".xmi");
        final File targetBin = new File(currentTempFile() + ".bin");

        final URI targetXmiURI = URI.createFileURI(targetXmi.getAbsolutePath());
        final URI targetBinURI = URI.createFileURI(targetBin.getAbsolutePath());

        copyResources(containerURI, targetBinURI);
        copyResources(containerURI, targetXmiURI);

        compareResources(targetBinURI, targetXmiURI);
    }

    @ParameterizedTest(name = "[{index}] source = {0}")
    @ArgumentsSource(UriProvider.UncompressedMixed.class)
    @Disabled
    void testEnsureCompatibility(URI uri) throws IOException {
        final String tempFile = "/Users/sunye/.neoemf/" + currentTempFile().getName();
        final File neoFile = new File(tempFile + ".neo");
        final File legacyFile = new File(tempFile + ".bin");

        final URI neoURI = URI.createFileURI(neoFile.getAbsolutePath());
        final URI legacyURI = URI.createFileURI(legacyFile.getAbsolutePath());

        Map<String, Object> options = new HashMap<>();
        Map<String, Object> legacyOptions = new HashMap<>();
        options.put(NeoBinaryResourceImpl.OPTION_VERSION, BinaryResourceImpl.BinaryIO.Version.VERSION_1_1);
        legacyOptions.put(BinaryResourceImpl.OPTION_VERSION, BinaryResourceImpl.BinaryIO.Version.VERSION_1_1);
        legacyOptions.put(BinaryResourceImpl.OPTION_STYLE_DATA_CONVERTER, false);

        copyResources(uri, neoURI, options);
        copyResources(uri, legacyURI, legacyOptions);

        FileAssert.assertBinaryEquals(legacyFile, neoFile);

    }

    private void compareResources(URI actual, URI expected) {
        Resource actualResource = resources.getResource(actual, true);
        Resource expectedResource = resources.getResource(expected, true);

        assertEObjectAreEqual(actualResource.getContents().get(0), expectedResource.getContents().get(0));
        actualResource.unload();
        expectedResource.unload();
    }

    private void copyResources(URI source, URI target) throws IOException {
        this.copyResources(source, target, Collections.emptyMap());
        Resource sourceResource = resources.getResource(source, true);
        Resource targetResource = resources.createResource(target);

        targetResource.getContents().addAll(EcoreUtil.copyAll(sourceResource.getContents()));
        targetResource.save(Collections.emptyMap());
    }

    private void copyResources(URI source, URI target, Map<String, Object> options) throws IOException {
        Resource sourceResource = resources.getResource(source, true);
        Resource targetResource = resources.createResource(target);

        targetResource.getContents().addAll(EcoreUtil.copyAll(sourceResource.getContents()));
        targetResource.save(options);
    }

    static void assertEObjectAreEqual(EObject actual, EObject expected) {


        final MatchEngineFactoryImpl factory = new MatchEngineFactoryImpl(UseIdentifiers.NEVER);
        IMatchEngine.Factory.Registry registry = MatchEngineFactoryRegistryImpl.createStandaloneInstance();
        factory.setRanking(20);
        registry.add(factory);

        IComparisonScope scope = new DefaultComparisonScope(expected, actual, null);

        Comparison comparison = EMFCompare.builder()
                .setMatchEngineFactoryRegistry(registry)
                .build()
                .compare(scope);

        final List<Diff> differences = comparison.getDifferences();

        // Don't display all differences
        assertThat(differences.size())
                .withFailMessage("Models have %d differences", differences.size())
                .isZero();
    }
}
