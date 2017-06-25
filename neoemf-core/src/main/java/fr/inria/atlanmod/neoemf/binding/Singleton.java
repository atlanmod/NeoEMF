package fr.inria.atlanmod.neoemf.binding;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Singleton {

    /**
     * Gets the name of the method used to get the single instance of the annotated class.
     *
     * @return the name of the method
     */
    String value() default "getInstance";
}
