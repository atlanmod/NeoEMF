package fr.inria.atlanmod.neoemf.io.util;

import fr.inria.atlanmod.commons.annotation.Static;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@Static
@ParametersAreNonnullByDefault
public final class IOTestUtils {

    @SuppressWarnings("JavaDoc")
    private IOTestUtils() {
        throw new IllegalStateException("This class should not be instantiated");
    }

    /**
     * Loads the {@code uri} with standard EMF.
     *
     * @param uri the URI to load
     *
     * @return the the loaded content
     *
     * @throws IOException if an I/O error occur during the loading of models
     */
    @Nonnull
    public static EObject loadWithEMF(URI uri) throws IOException {
        Map<String, Object> options = new HashMap<>();
        if (uri.fileExtension().endsWith("zxmi")) {
            options.put(XMIResource.OPTION_ZIP, true);
        }

        Resource resource = new ResourceSetImpl().createResource(uri);
        resource.load(options);

        return resource.getContents().get(0);
    }
}
