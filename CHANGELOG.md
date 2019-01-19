## *2.0.0*

Current SNAPSHOT.

__IMPORTANT NOTE:__ Due to significant changes in data mapping, databases created with a previous version are not compatible with this release.
No migration helper is provided: the simplest way to transfer the old databases to the new ones, is to export the database into an XMI file and re-import it.

__NOTE:__ The changelog of this version is not exhaustive and regroup only major changes.

### Core
-   __\[NEW\]__ `Id` instances are created by an `IdProvider` instead of static methods
-   __\[UPD\]__ `Id`s use a `long` representation, or a 64-bits word. They can be converted to a hexadecimal string and vice versa to maintain compatibility with `Backend`s that don't support `long`
-   __\[UPD\]__ The hasher used to generate new `Id` from a value is now [xxHash](https://github.com/Cyan4973/xxHash) that provides better performance than MurmurHash3
-   __\[UPD\]__ Features are identified by their `EStructuralFeature#getFeatureId()` instead of their name (see `FeatureBean`)

### Back-ends and stores abstraction
-   __\[NEW\]__ Introduce the new `DataMapper` layer to manipulate elements as key/value pairs.
They are presented as several interfaces and each have their responsibility:
    -   `ContainerMapper`     : manage the container of elements (represented by `ContainerBean`)
    -   `ClassMapper`         : manage the meta-class of elements (represented by `ClassBean`)
    -   `ValueMapper`         : manage single-valued attributes of elements (identified by `SingleFeatureBean`)
    -   `ReferenceMapper`     : manage single-valued references between elements (identified by `SingleFeatureBean`)
    -   `ManyValueMapper`     : manage multi-valued attributes of elements (identified by `ManyFeatureBean`)
    -   `ManyReferenceMapper` : manage multi-valued references between elements (identified by `ManyFeatureBean`)
-   __\[NEW\]__ Several default mappings have been implemented to process references as attributes after a conversion, or to manage data with indices, arrays, lists, or with a custom way.
                They are presented as interfaces to allow a combination of several mappings.
                For example, you can combine: `ReferenceAs<String>`, `MergeManyReferenceAs<String>` and `ManyValueWithLists`. These mappings are *optional*
-   __\[NEW\]__ A generic `DefaultTransientBackend` has been created to handle transient elements in memory:
                Custom `TransientBackend`s are no longer necessary, but this requires the `neoemf-io` module to transfer the transient content to a `PersistentBackend`
-   __\[NEW\]__ `URI`s are now created with factories instead of static methods
-   __\[UPD\]__ Complete optimization of all existing database adapters
-   __\[UPD\]__ `Backend`s are auto-closed when the JVM is shutting-down
-   __\[UPD\]__ `StoreAdapter` become the only `EStore` implementation, and provides a bridge between EMF and `DataMapper`s
-   __\[UPD\]__ All `Backend`s and `Store`s inherit from the `DataMapper` architecture
-   __\[UPD\]__ Back-end configuration have been merged, updated and simplified
-   __\[UPD\]__ Configuration is now managed with a simple `Properties` file *(may change in the near future)*
-   __\[UPD\]__ `PersistentResource` are no longer linked to their `Backend`s, prefer using `Store`s
-   __\[UPD\]__ `BackendFactory#createBackend()` take a `URI` as parameter instead of a `File` to handle distributed `PersistentBackend`
-   __\[DEL\]__ All backend-specific implementations of `PersistentStore` have been merged with those at `core`-level
-   __\[DEL\]__ `InvalidStore` has been replaced by `InvalidBackend`
-   __\[DEL\]__ `TransientStore`s have been removed and replaced by `BoundInMemoryBackend` (a lightweigth and shared version of an `DefaultInMemoryBackend`)

### Automation
-   __\[NEW\]__ `BackendFactory`s are automatically registered at runtime (no need to explicitly register them in the `ResourceSet` registry)
-   __\[NEW\]__ `BackendFactory`s are linked to their associated `UriFactory` and `Config` with annotations which are processed at runtime: a `UriFactory` or `Config` can be retrieved from their association
-   __\[NEW\]__ Stores and mappings are created by using reflection in `BackendFactory` to allow customizations (defined with `Config`)
-   __\[NEW\]__ `URI`s are automatically created according to a common prefix ("neo-") and the lowercase name of their associated `BackendFactory`

### I/O
-   __\[NEW\]__ The direct-import becomes generic and works with all implementations
-   __\[NEW\]__ The direct-export is fully implemented, and is possible to an XMI file (compressed or not), or another `DataMapper`

### Performance
-   __\[NEW\]__ Add batch methods `getAll`, `setAll`,... in addition to `get`, `set`,... to avoid multiple call
-   __\[UPD\]__ `BlueprintsBackend`s labels has been simplified by one-letter labels
-   __\[UPD\]__ The default chunk of `AutoSaveStore` is processed automatically from the total amount of memory
-   __\[UPD\]__ Map-based `Serializer`s have been replaced by generic serializers (the implementation is located in `atlanmod:commons-core` and use [FST](https://github.com/fstpackage/fst))

### Utility methods
-   __\[DEL\]__ Utility classes and methods have been moved to `atlanmod:commons-core`

### Tests
-   __\[NEW\]__ All mappings have a code coverage of ~100% to ensure the expected behavior of future implementations
-   __\[NEW\]__ HBase is now integrated in tests by using an Hadoop mini-cluster (requires Cygwin on Windows)
-   __\[UPD\]__ Test helpers have been merged and simplified: now only a link to `Context` is needed for multi-backend tests
-   __\[UPD\]__ Models used in `io` test-cases are now generated with Maven during the compilation
-   __\[UPD\]__ Tests have been migrated to [JUnit5](http://junit.org/junit5/)

### Benchmarks
-   __\[NEW\]__ Use `NEOEMF_HOME` system variable to locate the base benchmark directory
-   __\[UPD\]__ The NeoEMF database are created using the `io` importer instead of the standard importer
-   __\[NEW\]__ `Store`s can be configured in benchmarks, with the `s` parameter
-   __\[UPD\]__ `Adapter`s are configured with the `a` parameter (previously `b`)

### Miscellaneous
-   __\[NEW\]__ `FeatureMap` support (still not supported by the `neoemf-io` module)
-   __\[NEW\]__ Some methods use `Optional` instead of a comparison to `null`
-   __\[UPD\]__ Complete review of EMF collections to handle massive iterations
-   __\[FIX\]__ Issue #11: The `LoggingStoreDecorator` now use a dedicated `Logger` for its associated `Backend`
-   __\[FIX\]__ Issue #12: The stores are updated according to the EMF calls, so that the backends are always synchronized.
                There is no longer custom processing during `set()` and `add()`
-   __\[FIX\]__ Issue #15: The `blueprints.***.directory` property is overwritten in all cases by the current path:
                If a datastore already exists, then this property is updated with the new path, otherwise, the property stay unchanged.
-   __\[FIX\]__ Issue #27/#28: `Store`s are no longer copied when the associated `Resource` is unloading: A `Resource` should not be called if it's not loaded
-   __\[FIX\]__ Issue #57: The `Cache<Id, PersistentEObject>` is now common for all implementations
-   __\[FIX\]__ Issue #58: `guava` dependencies are no longer used in the project
-   __\[FIX\]__ Issue #63: `BasicReference`s are first processed as `BasicAttribute`s when reading, then redirected in `EcoreProcessor` which has access to its real type with the `EPackage`
-   __\[FIX\]__ Issue #64: If an `Id` is not found in `Backend`s, then an empty array is returned
-   __\[FIX\]__ Issue #70: The `LazyMatchEngine` class has been removed
-   __\[FIX\]__ Issue #71: `BoundInMemoryBackend` are registered in a local registry to ensure that the features can be retrieved even if the associated `PersistentEObject` is freed from memory
-   __\[FIX\]__ Issue #72: Ignore the uniqueness check of identifiers when creating a new `Vertex`
-   __\[FIX\]__ Issue #73: The `neoemf-data-map-core` module no longer exists
-   __\[FIX\]__ Issue #75: The `io` module now use the `DataMapper` structure, and not a custom implementation
-   __\[FIX\]__ Issue #77: Errors are intercepted and displayed in Eclipse UI
-   __\[FIX\]__ Issue #78: Improve the `NullPointerException` message
-   __\[FIX\]__ Issue #80: `DefaultPersistentEObject.toString()` throws a `StackOverflowError` on `EClass` instances
-   __\[FIX\]__ Issue #84: `FeatureMap`s was not supported

### Refactoring
-   __\[UPD\]__ `PersistenceBackendFactory` become `BackendFactory`: they also create `TransientBackend`s
-   __\[UPD\]__ `OptionsBuilder` become `Config`
-   __\[UPD\]__ `PersistenceURI` become `UriFactory`: static methods have been replaced by this factory
-   __\[UPD\]__ `PersistentStore` become `Store`: they don't have any state, so the "Persistent" prefix does not make sense
-   __\[UPD\]__ `FeatureKey` become `SingleFeatureBean`
-   __\[UPD\]__ `MultiFeatureKey` become `ManyFeatureBean`
-   __\[UPD\]__ `MetaclassValue` become `ClassBean`
-   __\[UPD\]__ `ContainerValue` has been merged with `SingleFeatureBean`

### Dependencies
-   __\[NEW\]__ `chronicle-map` : `3.14.5`
-   __\[NEW\]__ `org.reflections` : `0.9.9`
-   __\[UPD\]__ `org.eclipse.emf` : `2.12.0` to `2.15.0` (including associated dependencies)
-   __\[UPD\]__ `cglib` : `3.2.4` to `3.2.6`
-   __\[UPD\]__ `log4j` : `2.7` to `2.8.2`
-   __\[UPD\]__ `org.neo4j` : `1.9.6` to `2.1.8` (include `blueprints-neo4j-graph` to `blueprints-neo4j2-graph`)
-   __\[DEL\]__ `junit`: No longer needed, managed by `atlanmod:commons-core` (`5.0.3+`)
-   __\[DEL\]__ `assertj` : No longer needed, managed by `atlanmod:commons-core` (`3.9.0+`)
-   __\[DEL\]__ `mockito` : No longer needed, managed by `atlanmod:commons-core` (`2.13.0+`)
-   __\[DEL\]__ `caffeine` : No longer needed, managed by `atlanmod:commons-core` (`2.6.0+`)
-   __\[DEL\]__ `guava` : No longer needed
-   __\[DEL\]__ `commons-collections4` : No longer needed
-   __\[DEL\]__ `commons-configuration` : No longer needed
-   __\[DEL\]__ `commons-io` : No longer needed
-   __\[DEL\]__ `commons-lang3` : No longer needed


## 1.0.2

*Released on 2017-05-21.*

### Miscellaneous
-   __\[NEW\]__ Add experimental EMF Compare integration (will stay experimental as long as Guava issues remain)
-   __\[FIX\]__ Issue #53: WildCardType `?` in sample (MapDB) throws an exception when accessed in the Editor
-   __\[FIX\]__ Issue #54: `AbstractDirectWrite#toArray()` is not efficient
-   __\[FIX\]__ Issue #55: `DefaultPersistentEObject#eContainer()` is not efficient
-   __\[FIX\]__ Issue #56: Unnecessary backend lookups in `PersistentStore#eObject(Id)`
-   __\[FIX\]__ Issue #68: Creating contained objects with Epsilon does not work

### Dependencies
-   __\[UPD\]__ `guava` : `20.0` to `15.0` (Conflict with HBase)


## 1.0.1

*Release on 2017-01-16.*

### Review of the structure
-   __\[UPD\]__ `datastore` packages become `data`
-   __\[UPD\]__ Back-end implementations are now placed under the `fr.inria.atlanmod.neoemf.data` package
-   __\[UPD\]__ `graph` package is replaced by `data`: no more structural differentiation in the package structure
-   __\[UPD\]__ `***Map***` classes representing the MapDB implementation are replaced by `***MapDb***`

### Improve tests
-   __\[UPD\]__ Contextualization of tests: One test-case can be executed by several back-end implementations according to the current `Context`
-   __\[UPD\]__ Test-cases are now tagged
-   __\[UPD\]__ Preparation of tests for a future integration of JUnit 5
-   __\[UPD\]__ Externalization JUnit `Rule`s
-   __\[UPD\]__ Reorganization of `@After`/`@Before` methods
-   __\[UPD\]__ `All***` classes become `Abstract***`
-   __\[DEL\]__ Remove `NeoAssertions` class and its related custom `Builder`s

### Back-ends
-   __\[NEW\]__ Integration of a new back-end implementation: BerkeleyDB *(experimental)*.

### Documentation
-   __\[NEW\]__ Addition of new JavaDoc tags: `@future` and `@note`
-   __\[UPD\]__ JavaDoc has been completely revised and completed.

### Miscellaneous
-   __\[NEW\]__ Addition of new common annotations: `@VisibleForTesting` and `@Experimental`
-   __\[UPD\]__ `ClassInfo` and `ContainerInfo` have now static constructor methods: `from()` and `of()`
-   __\[UPD\]__ `Logger` is now fully-concurrent and extensible: We can use different parallel `Logger`s. However, these loggers keep the call order.
-   __\[UPD\]__ Generalization of `PersistenceURI` and its sub-classes
-   __\[UPD\]__ Generalization `BundleActivator`s
-   __\[FIX\]__ Issue #51: Concurrent `Executor` in `Logger` did not stop with the JVM
-   __\[FIX\]__ Issue #52: Partial fix with a `try...catch`

### Dependencies
-   __\[NEW\]__ `org.osgi`: `6.0.0`
-   __\[UPD\]__ `org.eclipse.emf:***`: `2.11.0` to `2.12.0`
-   __\[DEL\]__ `org.eclipse.osgi`: No need for implementation


## 1.0.0

*Released on 2016-12-06.*

First release.
