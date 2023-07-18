package de.huk.schulung.samples;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Greet {
    /**
     * Name des Grüßers.
     *
     * @return Name des Grüßers oder leer lassen, um den Namen der Methode als Grüßer zu erhalten.
     */
    String value() default "";
}
