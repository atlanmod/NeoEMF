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
import java.io.InputStream;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import static fr.inria.atlanmod.neoemf.util.Preconditions.checkNotNull;

/**
 * A utility class that manages external resources for I/O tests.
 */
@ParametersAreNonnullByDefault
public final class IOResources {

    private static final String XMI_SAMPLE_STD = "/xmi/sampleStandard.xmi";

    private static final String XMI_SAMPLE_ID = "/xmi/sampleWithId.xmi";

    private IOResources() {
        throw new IllegalStateException("This class should not be instantiated");
    }

    /**
     * Returns the XMI file that uses XPath references.
     *
     * @return the XMI file
     */
    @Nonnull
    public static InputStream xmiStandard() {
        return IOResources.class.getResourceAsStream(XMI_SAMPLE_STD);
    }

    /**
     * Returns the XMI file that uses {@code xmi:id} references.
     *
     * @return the XMI file
     */
    @Nonnull
    public static InputStream xmiWithId() {
        return IOResources.class.getResourceAsStream(XMI_SAMPLE_ID);
    }

    /**
     * Registers a EPackage in {@link EPackage.Registry} according to its {@code prefix} and {@code uri}, from an
     * Ecore file.
     * <p>
     * The targeted Ecore file must be present in {@code /resources/ecore}.
     */
    public static void registerPackage(String prefix, String uri) {
        File file = new File(IOResources.class.getResource("/ecore/{name}.ecore".replaceAll("\\{name\\}", prefix)).getFile());

        EPackage pkg = null;

        Resource.Factory.Registry.INSTANCE
                .getExtensionToFactoryMap()
                .put("ecore", new EcoreResourceFactoryImpl());

        ResourceSet rs = new ResourceSetImpl();

        final ExtendedMetaData extendedMetaData = new BasicExtendedMetaData(rs.getPackageRegistry());
        rs.getLoadOptions().put(XMLResource.OPTION_EXTENDED_META_DATA, extendedMetaData);

        Resource r = rs.getResource(URI.createFileURI(file.toString()), true);

        EObject eObject = r.getContents().get(0);
        if (EPackage.class.isInstance(eObject)) {
            pkg = EPackage.class.cast(eObject);
            rs.getPackageRegistry().put(pkg.getNsURI(), pkg);
        }

        checkNotNull(pkg);

        EPackage.Registry.INSTANCE.put(uri, pkg);
    }
}
