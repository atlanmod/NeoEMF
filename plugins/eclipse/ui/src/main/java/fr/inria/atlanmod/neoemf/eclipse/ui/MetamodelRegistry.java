package fr.inria.atlanmod.neoemf.eclipse.ui;

import fr.inria.atlanmod.common.collect.MoreIterables;
import fr.inria.atlanmod.common.log.Log;
import fr.inria.atlanmod.neoemf.eclipse.ui.action.RegisterMetamodelAction;

import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * A registry that manage metamodels.
 * <p>
 * The metamodels are added by using the {@link RegisterMetamodelAction}
 * extension point on {@code *.ecore} files.
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
        registerMetamodels();

        ResourcesPlugin.getWorkspace().addResourceChangeListener(event -> {
            if (event.getType() != IResourceChangeEvent.POST_CHANGE) {
                return;
            }

            Set<IResourceDelta> changed = new HashSet<>();

            try {
                event.getDelta().accept(changed::add);

                Set<String> metamodels = loadMetamodels();
                changed.forEach(delta -> {
                    String metamodel = delta.getResource().getFullPath().toOSString();

                    if (metamodels.contains(metamodel)) {
                        if (delta.getKind() == IResourceDelta.REMOVED) {
                            removeMetamodel(metamodel);

                            Optional.ofNullable(delta.getMovedToPath())
                                    .ifPresent(p -> addMetamodel(p.toOSString()));
                        }
                        else {
                            addMetamodel(metamodel);
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
     * Replace the instance class name of each {@link EDataType} of the {@code resource} if it represents a boxed
     * primitive.
     *
     * @param resource the resource to analyze
     */
    private static void setDataTypesInstanceClasses(Resource resource) {
        List<Class<?>> primitives = Arrays.asList(
                Byte.class,
                Short.class,
                Integer.class,
                Long.class,
                Float.class,
                Double.class,
                Boolean.class,
                Character.class,
                String.class);

        MoreIterables.stream(resource::getAllContents)
                .filter(EDataType.class::isInstance)
                .map(EDataType.class::cast)
                .forEach(type -> primitives.stream()
                        .filter(c -> Objects.equals(type.getName(), c.getSimpleName()))
                        .findAny()
                        .map(Class::getName)
                        .ifPresent(type::setInstanceClassName));
    }

    /**
     * Adds all metamodels associated to the given {@code file}.
     *
     * @param file the file to add
     *
     * @see #registerMetamodel(String)
     */
    public void addMetamodel(String file) {
        registerMetamodel(file);

        Set<String> metamodels = loadMetamodels();
        if (metamodels.add(file)) {
            saveMetamodels(metamodels);
        }
    }

    /**
     * Removes all metamodels associated to the given {@code file}.
     *
     * @param file the file to remove
     */
    public void removeMetamodel(String file) {
        Optional.ofNullable(managedMetamodels.remove(file))
                .ifPresent(ps -> ps.forEach(p -> EPackage.Registry.INSTANCE.remove(p.getNsURI())));

        Set<String> metamodels = loadMetamodels();
        if (metamodels.remove(file)) {
            saveMetamodels(metamodels);
        }
    }

    /**
     * Loads all previously saved metamodels from the {@link NeoUIPlugin} store.
     *
     * @return the previously saved metamodels
     *
     * @see #saveMetamodels(Set)
     */
    private Set<String> loadMetamodels() {
        String value = NeoUIPlugin.getDefault().getPreferenceStore().getString(STORE_KEY);

        return Arrays.stream(value.split(STORE_DELIMITER))
                .collect(Collectors.toSet());
    }

    /**
     * Saves the {@code metamodels} in the {@link NeoUIPlugin} store.
     *
     * @param metamodels the metamodels to save
     *
     * @see #loadMetamodels()
     */
    private void saveMetamodels(Set<String> metamodels) {
        String value = metamodels.stream()
                .collect(Collectors.joining(STORE_DELIMITER));

        NeoUIPlugin.getDefault().getPreferenceStore().setValue(STORE_KEY, value);
    }

    /**
     * Register all previously saved metamodels.
     *
     * @see #loadMetamodels()
     * @see #registerMetamodel(String)
     */
    private void registerMetamodels() {
        loadMetamodels().forEach(this::registerMetamodel);
    }

    /**
     * Register a metamodel from the given {@code file} to the {@link EPackage.Registry#INSTANCE}.
     *
     * @param file the file of the resource to register
     *
     * @see #registerMetamodel(URI, EPackage.Registry)
     */
    private void registerMetamodel(String file) {
        try {
            URI uri = URI.createPlatformResourceURI(file, true);
            managedMetamodels.put(file, registerMetamodel(uri, EPackage.Registry.INSTANCE));

            Log.info("Metamodel {0} successfully registered", file);
        }
        catch (IOException e) {
            Log.error(e, "Metamodel {0} could not be registered", file);
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
    private Set<EPackage> registerMetamodel(URI uri, EPackage.Registry registry) throws IOException {
        Resource.Factory.Registry.INSTANCE
                .getExtensionToFactoryMap()
                .put("*", new XMIResourceFactoryImpl());

        ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getPackageRegistry().put(EcorePackage.eINSTANCE.getNsURI(), EcorePackage.eINSTANCE);

        Resource metamodel = resourceSet.createResource(uri);
        metamodel.load(Collections.emptyMap());

        setDataTypesInstanceClasses(metamodel);

        return MoreIterables.stream(metamodel::getAllContents)
                .filter(EPackage.class::isInstance)
                .map(EPackage.class::cast)
                .peek(pkg -> {
                    if (isNull(pkg.getNsURI()) || pkg.getNsURI().trim().isEmpty()) {
                        if (isNull(pkg.getESuperPackage())) {
                            pkg.setNsURI(pkg.getName());
                        }
                        else {
                            pkg.setNsURI(pkg.getESuperPackage().getNsURI() + "/" + pkg.getName());
                        }
                    }

                    if ((isNull(pkg.getNsPrefix()) || pkg.getNsPrefix().trim().isEmpty()) && nonNull(pkg.getESuperPackage())) {
                        if (nonNull(pkg.getESuperPackage().getNsPrefix())) {
                            pkg.setNsPrefix(pkg.getESuperPackage().getNsPrefix() + "." + pkg.getName());
                        }
                        else {
                            pkg.setNsPrefix(pkg.getName());
                        }
                    }

                    if (isNull(pkg.getNsPrefix())) {
                        pkg.setNsPrefix(pkg.getName());
                    }
                })
                .peek(pkg -> registry.put(pkg.getNsURI(), pkg))
                .peek(pkg -> metamodel.setURI(URI.createURI(pkg.getNsURI())))
                .collect(Collectors.toSet());
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
