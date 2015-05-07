package fr.inria.atlanmod.atl_mr.utils;

import org.apache.hadoop.conf.Configuration;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.URIHandlerImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;


public class ATLMRUtils {

	public static void configureRegistry(final Configuration conf) {
		// Initialize ExtensionToFactoryMap
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl() {
			@Override
			public Resource createResource(URI uri) {
				XMLResource result = new XMIResourceImpl(uri) {
					@Override
					protected boolean useIDs() {
						return eObjectToIDMap != null || idToEObjectMap != null;
					}

					@Override
					protected URIConverter getURIConverter() {
						return new HadoopURIConverterImpl(conf);
					}
				};
				result.setEncoding("UTF-8");

				result.getDefaultSaveOptions().put(XMLResource.OPTION_USE_ENCODED_ATTRIBUTE_STYLE, Boolean.TRUE);
				result.getDefaultSaveOptions().put(XMLResource.OPTION_LINE_WIDTH, 80);
				result.getDefaultSaveOptions().put(XMLResource.OPTION_URI_HANDLER, new URIHandlerImpl.PlatformSchemeAware());
				return result;
			}
		});
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl() {
			@Override
			public Resource createResource(URI uri) {
				return new XMIResourceImpl(uri) {
					@Override
					protected URIConverter getURIConverter() {
						return new HadoopURIConverterImpl(conf);
					}
				};
			}
		});

		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("bin", new ResourceFactoryImpl() {
			@Override
			public Resource createResource(URI uri) {
				return new BinaryResourceImpl(uri) {
					@Override
					protected URIConverter getURIConverter() {
						return new HadoopURIConverterImpl(conf);
					}
				};
			}
		});
	}

	/**
	 * Registers the packages
	 * @param resourceSet
	 * @param resource
	 */
	public static void registerPackages(ResourceSet resourceSet, Resource resource) {
		EObject eObject = resource.getContents().get(0);
		if (eObject instanceof EPackage) {
			EPackage p = (EPackage) eObject;
			resourceSet.getPackageRegistry().put(p.getNsURI(), p);
		}

	}


}
