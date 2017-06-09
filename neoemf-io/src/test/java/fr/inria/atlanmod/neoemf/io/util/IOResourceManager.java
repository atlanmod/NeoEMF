package fr.inria.atlanmod.neoemf.io.util;

import fr.inria.atlanmod.neoemf.util.log.Log;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;

import java.net.URL;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.neoemf.util.Preconditions.checkNotNull;

/**
 * A utility class that manages external resources for I/O tests.
 */
@ParametersAreNonnullByDefault
public final class IOResourceManager {

    @SuppressWarnings("JavaDoc")
    private IOResourceManager() {
        throw new IllegalStateException("This class should not be instantiated");
    }

    /**
     * Returns the XMI file that uses XPath references.
     *
     * @return the XMI file
     */
    @Nonnull
    public static URI xmiStandard() {
        return ResourceLoader.Holder.INSTANCE.getUri("/xmi/sampleStandard.xmi");
    }

    /**
     * Returns the XMI file that uses {@code xmi:id} references.
     *
     * @return the XMI file
     */
    @Nonnull
    public static URI xmiWithId() {
        return ResourceLoader.Holder.INSTANCE.getUri("/xmi/sampleWithId.xmi");
    }

    /**
     * Registers a EPackage in {@link EPackage.Registry} according to its {@code prefix} and {@code uri}, from an
     * Ecore file.
     * <p>
     * The targeted Ecore file must be present in the {@code /resources/ecore} directory of this modules.
     */
    public static void registerPackage(String prefix, String uri) {
        EPackage pkg = null;

        Resource.Factory.Registry.INSTANCE
                .getExtensionToFactoryMap()
                .put("ecore", new EcoreResourceFactoryImpl());

        ResourceSet rs = new ResourceSetImpl();

        final ExtendedMetaData extendedMetaData = new BasicExtendedMetaData(rs.getPackageRegistry());
        rs.getLoadOptions().put(XMLResource.OPTION_EXTENDED_META_DATA, extendedMetaData);

        Resource r = rs.getResource(ResourceLoader.Holder.INSTANCE.getUri("/ecore/" + prefix + ".ecore"), true);

        EObject eObject = r.getContents().get(0);
        if (EPackage.class.isInstance(eObject)) {
            pkg = EPackage.class.cast(eObject);
            rs.getPackageRegistry().put(pkg.getNsURI(), pkg);
        }

        checkNotNull(pkg);

        EPackage.Registry.INSTANCE.put(uri, pkg);
    }

    /**
     * A resource loader that allows to retrieve a resource from this module, even if the call comes from another one.
     *
     * @see Class#getResource(String)
     */
    private static final class ResourceLoader {

        /**
         * Retrieves the resource with the given {@code name}.
         *
         * @param name the name of the resource
         *
         * @return the URL of the resource
         *
         * @throws NullPointerException if the resource cannot be found
         */
        @Nonnull
        private URL getUrl(String name) {
            URL url = checkNotNull(getClass().getResource(name), "Unable to find the resource %s", name);
            Log.info("Loading resource: {0}", url.toString());
            return url;
        }

        /**
         * Retrieves the resource with the given {@code name}.
         *
         * @param name the name of the resource
         *
         * @return a URI of the resource
         *
         * @throws NullPointerException if the resource cannot be found
         */
        @Nonnull
        private URI getUri(String name) {
            return URI.createURI(getUrl(name).toString());
        }

        /**
         * The initialization-on-demand holder of the singleton of this class.
         */
        private static final class Holder {

            /**
             * The instance of the outer class.
             */
            private static final ResourceLoader INSTANCE = new ResourceLoader();
        }
    }
}
