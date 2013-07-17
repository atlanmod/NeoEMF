This plug-in demonstrates the org.eclipse.emf.codegen.ecore.generatorAdapters
extension point, showing how it can be used to extend EMF's code generator.

It adds two types of artifacts to those usually generated in a model plug-in:

1. a validator interface for each modeled class
2. a text description of the model.

These artifacts are added by the GenClassValidatorGeneratorAdapter.java
GenModelValidatorGeneratorAdapter.java generator adapters, and are generated
using the Validator.javajet and ModelDescription.txtjet templates.

The generator adapters are registered, by default, via an adapter factory
that creates them. They can also be registered directly, with no functional
change in behaviour. To do so, simply comment out the adapterFactory element
and uncomment the adapter elements in plugin.xml.
