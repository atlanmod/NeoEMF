## *1.0.2*

Current SNAPSHOT.
- __[NEW]__ Add experimental EMF Compare integration (will stay experimental as
  long as Guava issues remain)
- __[FIX]__ Issue #53: WildCardType '?' in sample (mapdb) throws an exception
  when accessed in the Editor
- __[FIX]__ Issue #54: AbstractDirectWrite.toArray is not efficient
- __[FIX]__ Issue #55: DefaultPersistentEObject.eContainer is not efficient
- __[FIX]__ Issue #56: Unnecessary backend lookups in Store.eObject(Id)

### Back-ends
- __[NEW]__ Merge common code from MapDB & BerkeleyDB in Map module

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
- __[DEL]__ `org.eclipse.osgi`: we don't need the implementation
- __[UPD]__ `org.eclipse.emf:***`: `2.11.0` to `2.12.0`


## 1.0.0 _(2016-12-06)_

First release.
