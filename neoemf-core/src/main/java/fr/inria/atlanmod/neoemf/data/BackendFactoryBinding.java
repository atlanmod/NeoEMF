package fr.inria.atlanmod.neoemf.data;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotates an element that is bounded to a {@link BackendFactory}.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface BackendFactoryBinding {

    /**
     * The class of the bounded {@link BackendFactory}.
     *
     * @return the class
     */
    Class<? extends BackendFactory> value();
}
