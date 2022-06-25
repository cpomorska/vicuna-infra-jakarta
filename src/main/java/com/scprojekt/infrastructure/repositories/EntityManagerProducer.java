package com.scprojekt.infrastructure.repositories;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;


@ApplicationScoped
public class EntityManagerProducer {

    @Produces
    @RequestScoped
    public EntityManager createEntityManager(){
        return Persistence.createEntityManagerFactory("infra").createEntityManager();
    }

    public void close(@Disposes EntityManager entityManager) {
        entityManager.close();
    }

}

