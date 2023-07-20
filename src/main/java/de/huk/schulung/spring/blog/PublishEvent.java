package de.huk.schulung.spring.blog;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotate a method with this aspect to get an event published after
 * method execution. The given class is the event type and must have a single constructor
 * that matches the annotated method's parameters.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PublishEvent {

    /**
     * The event type. Must have a constructor with the same parameters as the annotated method.
     * @return the event type
     */
    Class<?> value();

}