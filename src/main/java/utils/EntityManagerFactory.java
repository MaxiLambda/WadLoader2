package utils;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class EntityManagerFactory {

    @PersistenceContext(unitName = "primary")
    private EntityManager entityManager;

    @ApplicationScoped
    @Produces
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
