package jee.training.registration.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.InjectionPoint;

import java.util.logging.Logger;

@ApplicationScoped
public class Resources {
    @Produces
    Logger createLogger(InjectionPoint injectionPoint) {
        String name = injectionPoint.getMember().getDeclaringClass().getSimpleName();
        return Logger.getLogger(name);
    }

//    @PersistenceUnit
//    private EntityManagerFactory entityManagerFactory;
//
//    @Produces
//    @Default
//    @RequestScoped
//    public EntityManager create() {
//        return this.entityManagerFactory.createEntityManager();
//    }
//
//    public void dispose(@Disposes @Default EntityManager entityManager) {
//        if (entityManager.isOpen()) {
//            entityManager.close();
//        }
//    }
}
