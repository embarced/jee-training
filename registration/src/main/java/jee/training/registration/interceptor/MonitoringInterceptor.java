package jee.training.registration.interceptor;

import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

import java.time.Duration;
import java.time.Instant;
import java.util.logging.Logger;

@Interceptor
@Monitor
public class MonitoringInterceptor {

    @Inject
    private Logger logger;

    @AroundInvoke
    public Object monitor(InvocationContext invocationContext) throws Exception {
        Instant start = Instant.now();
        Object result = invocationContext.proceed();
        long milliseconds = Duration.between(start, Instant.now()).toMillis();
        logger.info(String.format("Methode %s, Ausführungszeit: %s ms", invocationContext.getMethod().getName(), milliseconds));
        return result;
    }
}
