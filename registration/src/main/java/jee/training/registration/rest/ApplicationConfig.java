package jee.training.registration.rest;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.JsonbConfig;
import jakarta.json.bind.config.PropertyNamingStrategy;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.ext.ContextResolver;
import jee.training.registration.json.EventAdapter;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("api")
public class ApplicationConfig extends Application {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        resources.add(EventResource.class);
        return resources;
    }

    @Override
    public Set<Object> getSingletons() {
        Set<Object> singletons = new HashSet<>();
        ContextResolver resolver = getJSONBConfiguration();
        singletons.add(resolver);
        return singletons;
    }

    private ContextResolver getJSONBConfiguration() {
        return new ContextResolver<Jsonb>() {
            @Override
            public Jsonb getContext(Class type) {
                JsonbConfig config = new JsonbConfig()
                        .withPropertyNamingStrategy(PropertyNamingStrategy.UPPER_CAMEL_CASE)
                        .withAdapters(new EventAdapter(em));
                return JsonbBuilder.newBuilder().
                        withConfig(config).
                        build();
            }
        };
    }
}
