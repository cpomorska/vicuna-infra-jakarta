package com.scprojekt.infrastructure.repositories;

import com.scprojekt.domain.model.user.User;
import com.scprojekt.domain.model.user.UserRepository;
import com.scprojekt.domain.model.user.UserType;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class InfrastructureUserRepository implements UserRepository {

    private final EntityManager em;

    @Inject
    public InfrastructureUserRepository(EntityManager em) {
        this.em = em;
    }


    @Override
    public List<User> findAll() {
        Query query = em.createQuery(" SELECT u from User u");
        return query.getResultList();
    }

    @Override
    public User findById(long id) {
        return em.find(User.class,id);
    }

    @Override
    public void createEntity(User entity) {
        em.getTransaction().begin();
        em.merge(entity);
        em.flush();
        em.getTransaction().commit();
    }

    @Override
    public void removeEntity(User entity) {
        em.getTransaction().begin();
        em.remove(entity);
        em.getTransaction().commit();
    }

    @Override
    public void updateEntity(User entity) {
        em.merge(entity);
    }

    @Override
    public User findByUUID(UUID uuid) {
        Query query = em.createQuery(" SELECT u from User u WHERE u.userNumber.uuid = :userNumber");
        query.setParameter("userNumber", uuid);
        return (User) query.getSingleResult();
    }

    @Override
    public List<User> findByType(UserType type) {
        Query query = em.createQuery(" SELECT u from User u WHERE :type MEMBER of u.userType");
        query.setParameter("type", type);
        return query.getResultList();
    }

    @Override
    public List<User> findByName(String name) {
        Query query = em.createQuery(" SELECT u from User u WHERE u.userName := username");
        query.setParameter("username", name);
        return query.getResultList();
    }

    @Override
    public List<User> findByDesription(String description) {
        Query query = em.createQuery(" SELECT u from User u WHERE u.userDescription := userdescription");
        query.setParameter("userdescription", description);
        return query.getResultList();
    }
}
