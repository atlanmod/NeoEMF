package fr.inria.atlanmod.neoemf.binding;

import fr.inria.atlanmod.neoemf.data.BackendFactory;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.annotation.Nonnull;

/**
 * Annotates an element that is bound to a {@link BackendFactory}.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface FactoryBinding {

    /**
     * The type of the bound {@link BackendFactory}.
     *
     * @return the type
     */
    @Nonnull
    Class<? extends BackendFactory> value();
}
