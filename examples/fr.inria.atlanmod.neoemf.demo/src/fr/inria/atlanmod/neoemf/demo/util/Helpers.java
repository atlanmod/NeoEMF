package fr.inria.atlanmod.neoemf.demo.util;

import fr.inria.atlanmod.commons.log.Log;
import fr.inria.atlanmod.neoemf.resource.PersistentResource;
import fr.inria.atlanmod.neoemf.util.compare.LazyMatchEngineFactory;

import org.eclipse.emf.compare.Comparison;
import org.eclipse.emf.compare.Diff;
import org.eclipse.emf.compare.EMFCompare;
import org.eclipse.emf.compare.match.IMatchEngine;
import org.eclipse.emf.compare.match.impl.MatchEngineFactoryRegistryImpl;
import org.eclipse.emf.compare.scope.DefaultComparisonScope;
import org.eclipse.emf.compare.scope.IComparisonScope;
import org.eclipse.emf.compare.utils.UseIdentifiers;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import java.util.List;
import java.util.stream.StreamSupport;

public final class Helpers {

    /**
     * @param resource the resource to compute the size of
     *
     * @return the number of elements in the containment tree of the Resource
     */
    public static long countElements(Resource resource) {
        Iterable<EObject> allContents = resource::getAllContents;
        return StreamSupport.stream(allContents.spliterator(), true).count();
    }

    /**
     * Checks that NeoEMF model contains the same elements as the input XMI.
     *
     * This operation can take some time for large models because both input and output models have to be entirely
     * traversed. This step is presented for the demonstration purpose and can be ignored in real-world applications:
     * NeoEMF ensures that created models from input XMI files contains all the input elements.
     */
    public static void compare(Resource expected, PersistentResource actual) {
        IMatchEngine.Factory factory = new LazyMatchEngineFactory(UseIdentifiers.NEVER);

        IMatchEngine.Factory.Registry matchEngineRegistry = new MatchEngineFactoryRegistryImpl();
        matchEngineRegistry.add(factory);

        IComparisonScope scope = new DefaultComparisonScope(expected, actual, null);

        Comparison comparison = EMFCompare.builder()
                .setMatchEngineFactoryRegistry(matchEngineRegistry)
                .build()
                .compare(scope);

        List<Diff> diffs = comparison.getDifferences();

        if (diffs.size() > 0) {
            Log.error("Created model has {0} diffs compared to the input XMI", diffs.size());
            for (Diff diff : diffs) {
                Log.error("\t {0}", diff.toString());
            }
        }
        else {
            Log.info("Created model contains all the elements from the input XMI");
        }
    }
}
