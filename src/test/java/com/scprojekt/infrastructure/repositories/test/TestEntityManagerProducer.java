package com.scprojekt.infrastructure.repositories.test;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Alternative
@ApplicationScoped
public class TestEntityManagerProducer {

    @Produces
    @PersistenceContext
    protected EntityManager provideTestEntityManager;

    public void close(@Disposes EntityManager entityManager) {
        entityManager.close();
    }
}
