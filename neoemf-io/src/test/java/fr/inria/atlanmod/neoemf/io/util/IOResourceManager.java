package fr.inria.atlanmod.neoemf.io.util;

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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.neoemf.util.Preconditions.checkNotNull;

/**
 * A utility class that manages external resources for I/O tests.
 */
@ParametersAreNonnullByDefault
public final class IOResourceManager {

    /**
     * Constructs a new {@code IOResourceManager}.
     */
    private IOResourceManager() {
    }

    /**
     * Returns the instance of this class.
     *
     * @return the instance
     */
    public static IOResourceManager getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Returns the XMI file that uses XPath references.
     *
     * @return the XMI file
     */
    @Nonnull
    public InputStream xmiStandard() throws IOException {
        return urlFromResource("/xmi/sampleStandard.xmi").openStream();
    }

    /**
     * Returns the XMI file that uses {@code xmi:id} references.
     *
     * @return the XMI file
     */
    @Nonnull
    public InputStream xmiWithId() throws IOException {
        return urlFromResource("/xmi/sampleWithId.xmi").openStream();
    }

    /**
     * Registers a EPackage in {@link EPackage.Registry} according to its {@code prefix} and {@code uri}, from an
     * Ecore file.
     * <p>
     * The targeted Ecore file must be present in {@code /resources/ecore}.
     */
    public void registerPackage(String prefix, String uri) {
        EPackage pkg = null;

        Resource.Factory.Registry.INSTANCE
                .getExtensionToFactoryMap()
                .put("ecore", new EcoreResourceFactoryImpl());

        ResourceSet rs = new ResourceSetImpl();

        final ExtendedMetaData extendedMetaData = new BasicExtendedMetaData(rs.getPackageRegistry());
        rs.getLoadOptions().put(XMLResource.OPTION_EXTENDED_META_DATA, extendedMetaData);

        File file = fileFromResource("/ecore/" + prefix + ".ecore");
        Resource r = rs.getResource(URI.createFileURI(file.getAbsolutePath()), true);

        EObject eObject = r.getContents().get(0);
        if (EPackage.class.isInstance(eObject)) {
            pkg = EPackage.class.cast(eObject);
            rs.getPackageRegistry().put(pkg.getNsURI(), pkg);
        }

        checkNotNull(pkg);

        EPackage.Registry.INSTANCE.put(uri, pkg);
    }

    /**
     * Retrieves the resource with the given {@code name}.
     *
     * @param name the name of the resource
     *
     * @return a URL
     *
     * @throws NullPointerException if the resource cannot be found
     */
    @Nonnull
    private URL urlFromResource(String name) {
        return checkNotNull(getClass().getResource(name), "Unable to find the resource %s", name);
    }

    /**
     * Retrieves the resource with the given {@code name}.
     *
     * @param name the name of the resource
     *
     * @return a file
     *
     * @see #urlFromResource(String)
     * @throws NullPointerException if the resource cannot be found
     */
    @Nonnull
    private File fileFromResource(String name) {
        URL url = urlFromResource(name);

        try {
            return new File(url.toURI());
        }
        catch (URISyntaxException e) {
            return new File(url.getPath());
        }
    }

    /**
     * The initialization-on-demand holder of the singleton of this class.
     */
    private static final class Holder {

        /**
         * The instance of the outer class.
         */
        private static final IOResourceManager INSTANCE = new IOResourceManager();
    }
}
