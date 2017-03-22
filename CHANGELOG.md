## *1.0.2*

Current SNAPSHOT.

### Back-ends and stores abstraction
- __[NEW]__ Introduce the new `DataMapper` layer to manipulate elements as key/value pairs
- __[NEW]__ Several default mappings have been implemented to manage data with indices, arrays, lists, or strings
- __[NEW]__ Store decorators and mappings are created by using reflection in `BackendFactory`
- __[NEW]__ `Backend` are split in 2 categories: `PersistentBackend` and `TransientBackend`
- __[NEW]__ A generic `DefaultTransientBackend` has been created to handle elements in several in-memory `Map`s _(experimental)_
- __[NEW]__ `URI`s are automatically created according to a common prefix ("neo-") and the lowercase name of the created `Backend` of their associated `BackendFactory`
- __[UPD]__ `Backend`s are auto-closed by the `DirectWriteStore` in front of them
- __[UPD]__ `StoreAdapter` is the only `EStore` implementation, which provides a bridge between EMF and `DataMapper`s
- __[UPD]__ All back-ends and stores inherit from the `DataMapper` architecture
- __[UPD]__ `PersistenceOptions` have been updated and simplified
- __[UPD]__ The default chunk of `AutoSaveStoreDecorator` have been decreased from 100 000 to 50 000
- __[UPD]__ `PersistentResource` are not linked to their `Backend`s (prefer using `Store`s)
- __[UPD]__ `BlueprintsBackend`s use "neoInstanceOf" instead of "kyanosInstance" to link `EObject`s to their `EClass` (with support for the previous)
- __[UPD]__ `BackendFactory#createPersistentBackend(***)` take a `URI` as parameter instead of a `File` to handle distributed `PersistentBackend`
- __[DEL]__ MapDB `Serializer`s have been replaced by Java serializers _(may change in the near future)_
- __[DEL]__ All backend-specific implementations of `PersistentStore` have been merged with those at `core`-level
- __[DEL]__ `InvalidStore` has been replaced by `InvalidBackend`
- __[DEL]__ `TransientStore`s have been replaced by `BoundedTransientStore` (a lightweigth version of `TransientBackend`)

### Utility methods
- __[NEW]__ Some classes that provides utility methods have been added: `MoreIterables`, `MoreArrays`, `Preconditions`
- __[NEW]__ Cache implementation is now wrapped to avoid dependencies declaration in modules
- __[NEW]__ `URI`s are now created with builders instead of static methods
- __[NEW]__ `Hashers` now provides SHA-1 and SHA-256 hash algorithms in addition to MD5
- __[UPD]__ `Hasher` now process `HashCode`s from `byte` arrays instead of `String`s
- __[UPD]__ Configuration is now managed with a simple `Properties` file _(may change in the near future)_

### Improve tests
- __[NEW]__ All mappings have a code coverage of 100% to ensure the expected behavior of future implementations
- __[NEW]__ All utility methods have their tests
- __[NEW]__ HBase is now integrated in tests by using an Hadoop mini-cluster (requires Cygwin on Windows)
- __[UPD]__ Test helpers have been merged and simplified: now only a link to `Context` is needed for multi-backend tests
- __[UPD]__ Tests display the full stacktrace when a test fails

### Benchmarks
- __[NEW]__ `Store`s can be configured in benchmarks, with the `s` parameter
- __[UPD]__ `Adapter`s are configured with the `a` parameter (previously `b`)

### Miscellaneous
- __[NEW]__ The direct-import becomes generic and works with all implementations
- __[NEW]__ Some methods use `Optional` instead of a comparison to `null`
- __[NEW]__ Add experimental EMF Compare integration (will stay experimental as long as Guava issues remain)
- __[UPD]__ `EList` and `EMap` implementations are inner classes in `DefaultPersistentEObject`
- __[FIX]__ Issue #53: WildCardType `?` in sample (MapDB) throws an exception when accessed in the Editor
- __[FIX]__ Issue #54: `AbstractDirectWrite#toArray()` is not efficient
- __[FIX]__ Issue #55: `DefaultPersistentEObject#eContainer()` is not efficient
- __[FIX]__ Issue #56: Unnecessary backend lookups in `PersistentStore#eObject(Id)`
- __[FIX]__ Issue #68: Creating contained objects with Epsilon does not work

### Refactoring
- __[UPD]__ `AutoCommitStoreDecorator` become `AutoSaveStoreDecorator`
- __[UPD]__ `PersistenceBackendFactory` become `BackendFactory`: they also create `TransientBackend`s
- __[UPD]__ `PersistenceURI` becore `URIBuilder`: static methods have been replaced by this builder
- __[UPD]__ `PersistentStore` becore `Store`: they don't have any state, so the "Persistent" prefix does not make sense
- __[UPD]__ `MultiFeatureKey` become `ManyFeatureKey`
- __[UPD]__ `MetaclassValue` become `ClassDescriptor`
- __[UPD]__ `ContainerValue` become `ContainerDescriptor`
- __[UPD]__ `***OptionsBuilder` becore `***Options`

### Dependencies
- __[UPD]__ `assertj` : `3.6.1` to `3.6.2`
- __[UPD]__ `mockito` : `1.10.19` to `2.7.18`
- __[UPD]__ `cglib` : `3.2.4` to `3.2.5`
- __[UPD]__ `log4j` : `2.7` to `2.8.1`
- __[UPD]__ `caffeine` : `2.3.5` to `2.4.0`
- __[DEL]__ `guava` : No more needed
- __[DEL]__ `commons-collections4` : No more needed
- __[DEL]__ `commons-configuration` : Replaced by native `Properties`
- __[DEL]__ `commons-io` : Replaced by `nio` methods
- __[DEL]__ `commons-lang3` : No more needed

## 1.0.1 _(2017-01-16)_

### Review of the structure
- __[UPD]__ `datastore` packages become `data`
- __[UPD]__ Back-end implementations are now placed under the `fr.inria.atlanmod.neoemf.data` package
- __[UPD]__ `graph` package is replaced by `data`: no more structural differentiation in the package structure
- __[UPD]__ `***Map***` classes representing the MapDB implementation are replaced by `***MapDb***`

### Improve tests
- __[UPD]__ Contextualization of tests: One test-case can be executed by several back-end implementations according to
  the current `Context` defined by `@Parameterized.Parameters`
- __[UPD]__ Test-cases are now tagged
- __[UPD]__ Preparation of tests for a future integration of JUnit 5
- __[UPD]__ Externalization JUnit `Rule`s
- __[UPD]__ Reorganization of `@After`/`@Before` methods
- __[UPD]__ `All***` classes become `Abstract***`
- __[DEL]__ Remove `NeoAssertions` class and its related custom `Builder`s

### Back-ends
- __[NEW]__ Integration of a new back-end implementation: BerkeleyDB *(experimental)*.

### Documentation
- __[NEW]__ Addition of new JavaDoc tags: `@future` and `@note`
- __[UPD]__ JavaDoc has been completely revised and completed.

### Miscellaneous
- __[NEW]__ Addition of new common annotations: `@VisibleForTesting` and `@Experimental`
- __[UPD]__ `ClassInfo` and `ContainerInfo` have now static constructor methods: `from()` and `of()`
- __[UPD]__ `Logger` is now fully-concurrent and extensible: We can use different parallel `Logger`s. However, these loggers keep the call order.
- __[UPD]__ Generalization of `PersistenceURI` and its sub-classes
- __[UPD]__ Generalization `BundleActivator`s
- __[FIX]__ Issue #51: Concurrent `Executor` in `Logger` did not stop with the JVM
- __[FIX]__ Issue #52: Partial fix with a `try...catch`

### Dependencies
- __[NEW]__ `org.osgi`: `6.0.0`
- __[UPD]__ `org.eclipse.emf:***`: `2.11.0` to `2.12.0`
- __[DEL]__ `org.eclipse.osgi`: No need for implementation


## 1.0.0 _(2016-12-06)_

First release.
