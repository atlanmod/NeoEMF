package fr.inria.atlanmod.neoemf.eclipse.ui;

import fr.inria.atlanmod.commons.collect.MoreIterables;
import fr.inria.atlanmod.commons.log.Log;
import fr.inria.atlanmod.commons.primitive.Strings;
import fr.inria.atlanmod.neoemf.eclipse.ui.action.RegisterMetamodelAction;

import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * A registry that manage metamodels.
 * <p>
 * The metamodels are added by using the {@link RegisterMetamodelAction} extension point on {@code *.ecore} files.
 * <p>
 * These metamodels are saved in the {@link NeoUIPlugin} store.
 */
public final class MetamodelRegistry {

    /**
     * The key used to indentify the saved metamodels in the store.
     */
    private static final String STORE_KEY = "metamodels";

    /**
     * The delimiter used between each metamodels.
     */
    private static final String STORE_DELIMITER = ";";

    /**
     * A map that holds all registered metamodels, identified by the name of the file where they can be found.
     */
    private final HashMap<String, Set<EPackage>> managedMetamodels = new HashMap<>();

    /**
     * Constructs a new {@code Registry}.
     */
    private MetamodelRegistry() {
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
        EPackage.Registry.INSTANCE.put(EcorePackage.eINSTANCE.getNsURI(), EcorePackage.eINSTANCE);

        ResourcesPlugin.getWorkspace().addResourceChangeListener(event -> {
            if (event.getType() != IResourceChangeEvent.POST_CHANGE) {
                return;
            }

            Set<IResourceDelta> changed = new HashSet<>();

            try {
                event.getDelta().accept(changed::add);

                Set<String> metamodels = load();
                changed.forEach(delta -> {
                    String metamodel = delta.getResource().getFullPath().toOSString();

                    if (metamodels.contains(metamodel)) {
                        if (delta.getKind() == IResourceDelta.REMOVED) {
                            unregister(metamodel);

                            Optional.ofNullable(delta.getMovedToPath()).ifPresent(p -> register(p.toOSString()));
                        }
                        else {
                            register(metamodel);
                        }
                    }
                });
            }
            catch (CoreException e) {
                Log.error(e);
            }
        });
    }

    /**
     * Returns the instance of this class.
     *
     * @return the instance of this class
     */
    public static MetamodelRegistry getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * Adds all metamodels associated to the given {@code file}.
     *
     * @param file the file to register
     *
     * @see #register(String, boolean)
     */
    public void register(String file) {
        register(file, true);
    }

    /**
     * Removes all metamodels associated to the given {@code file}.
     *
     * @param file the file to unregister
     */
    public void unregister(String file) {
        Optional.ofNullable(managedMetamodels.remove(file))
                .ifPresent(ps -> ps.forEach(p -> EPackage.Registry.INSTANCE.remove(p.getNsURI())));

        Set<String> metamodels = load();
        if (metamodels.remove(file)) {
            save(metamodels);
        }
    }

    /**
     * Loads all previously saved metamodels from the {@link NeoUIPlugin} store.
     *
     * @return the previously saved metamodels
     *
     * @see #save(Set)
     */
    private Set<String> load() {
        String value = NeoUIPlugin.getDefault().getPreferenceStore().getString(STORE_KEY);

        Log.debug("Loaded from preferences: {0}", value);

        return Arrays.stream(value.split(STORE_DELIMITER))
                .filter(Objects::nonNull)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toSet());
    }

    /**
     * Saves the {@code metamodels} in the {@link NeoUIPlugin} store.
     *
     * @param metamodels the metamodels to save
     *
     * @see #load()
     */
    private void save(Set<String> metamodels) {
        String value = metamodels.stream()
                .filter(Objects::nonNull)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.joining(STORE_DELIMITER));

        Log.debug("Saved into preferences:  {0}", value);

        NeoUIPlugin.getDefault().getPreferenceStore().setValue(STORE_KEY, value);
    }

    /**
     * Register all previously saved metamodels.
     *
     * @see #load()
     * @see #register(String)
     */
    public void registerAll() {
        load().forEach(this::register);
    }

    /**
     * Register a metamodel from the given {@code file} to the {@link EPackage.Registry#INSTANCE}.
     *
     * @param file the file of the resource to register
     * @param save {@code true} if the metamodel have to be saved after loading
     *
     * @see #register(URI, EPackage.Registry)
     */
    private void register(String file, boolean save) {
        if (isNull(file) || file.isEmpty()) {
            return;
        }

        try {
            URI uri = URI.createPlatformResourceURI(file, true);
            Set<EPackage> packages = register(uri, EPackage.Registry.INSTANCE);
            managedMetamodels.put(file, packages);

            Log.info("{0,number,#} EPackages registered from {1}", packages.size(), file);

            if (save) {
                Set<String> metamodels = load();
                if (metamodels.add(file)) {
                    save(metamodels);
                }
            }
        }
        catch (Exception e) {
            Log.warn("Failed to register EPackages from {0}", file);

            // Clean the registry: the resource probably no longer exist
            unregister(file);
        }
    }

    /**
     * Registers a metamodel from the {@code uri} to the given {@code registry}.
     *
     * @param uri      the {@link URI} of the resource to register
     * @param registry the registry where to save the metamodel
     *
     * @return a immutable set of all registered metamodels
     */
    private Set<EPackage> register(URI uri, EPackage.Registry registry) throws IOException {
        ResourceSet resourceSet = new ResourceSetImpl();
        final ExtendedMetaData extendedMetaData = new BasicExtendedMetaData(registry);
        resourceSet.getLoadOptions().put(XMLResource.OPTION_EXTENDED_META_DATA, extendedMetaData);

        Resource resource = resourceSet.getResource(uri, true);
        resource.load(Collections.emptyMap());

        return MoreIterables.stream(resource::getAllContents)
                .filter(EPackage.class::isInstance)
                .map(EPackage.class::cast)
                .map(this::fix)
                .peek(p -> registry.put(p.getNsURI(), p))
                .collect(Collectors.toSet());
    }

    /**
     * Adds the missing information in an incomplete {@link EPackage}.
     *
     * @param ePackage the package to fix
     *
     * @return the fixed {@code ePackage}
     */
    private EPackage fix(EPackage ePackage) {
        String uri = ePackage.getNsURI();
        String prefix = ePackage.getNsPrefix();
        EPackage superEPackage = ePackage.getESuperPackage();

        if (Strings.isNullOrEmpty(uri)) {
            if (isNull(superEPackage)) {
                ePackage.setNsURI(ePackage.getName());
            }
            else {
                ePackage.setNsURI(superEPackage.getNsURI() + "/" + ePackage.getName());
            }
        }

        if (Strings.isNullOrEmpty(prefix) && nonNull(superEPackage)) {
            if (nonNull(superEPackage.getNsPrefix())) {
                ePackage.setNsPrefix(superEPackage.getNsPrefix() + "." + ePackage.getName());
            }
            else {
                ePackage.setNsPrefix(ePackage.getName());
            }
        }

        if (isNull(prefix)) {
            ePackage.setNsPrefix(ePackage.getName());
        }

        return ePackage;
    }

    /**
     * The initialization-on-demand holder of the singleton of this class.
     */
    private static final class Holder {

        /**
         * The instance of the outer class.
         */
        private static final MetamodelRegistry INSTANCE = new MetamodelRegistry();
    }
}
