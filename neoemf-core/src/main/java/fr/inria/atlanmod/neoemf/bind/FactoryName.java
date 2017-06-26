package fr.inria.atlanmod.neoemf.bind;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotates a field that represents the name of a {@link fr.inria.atlanmod.neoemf.data.BackendFactory}.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FactoryName {
}
