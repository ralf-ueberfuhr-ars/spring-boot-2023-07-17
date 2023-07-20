package de.huk.schulung.spring.blog;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class PublishEventAspect {

    private final ApplicationEventPublisher eventPublisher;

    // we can annotate the method directly
    @Around("@annotation(PublishEvent)")
    public Object publishEventForAnnotatedMethod(ProceedingJoinPoint jp) throws Throwable {
        // find annotation method (Reflection)
        if (jp.getSignature() instanceof MethodSignature ms) {
            final var parameters = jp.getArgs();
            final var method = ms.getMethod();
            // get annotation
            final var annotation = AnnotationUtils.findAnnotation(method, PublishEvent.class);
            // get event type
            final var eventClass = annotation.value();
            // find the constructor of the event type
            final var eventConstructor = parameters.length == 0
                    ? eventClass.getConstructor()
                    : eventClass.getDeclaredConstructors()[0];
            // invoke annotated method and save return value for the end of this aspect
            final var result = jp.proceed();
            // create and publish event
            final var event = eventConstructor.newInstance(parameters);
            eventPublisher.publishEvent(event);
            // return the return value of the annotated method
            return result;
        } else {
            return jp.proceed();
        }
    }

}