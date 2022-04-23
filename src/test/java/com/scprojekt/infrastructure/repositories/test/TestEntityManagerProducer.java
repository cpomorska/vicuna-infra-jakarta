package com.scprojekt.infrastructure.repositories.test;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Alternative;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

@Alternative
@ApplicationScoped
public class TestEntityManagerProducer {

    @Produces
    @RequestScoped
    protected EntityManager provideTestEntityManager() {
        return Persistence.createEntityManagerFactory("infra").createEntityManager();
    }

    public void close(
            @Disposes EntityManager entityManager) {
        entityManager.close();
    }
}
