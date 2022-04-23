package com.scprojekt.infrastructure.repositories;

import com.scprojekt.domain.model.user.User;
import com.scprojekt.domain.model.user.UserNumber;
import com.scprojekt.domain.model.user.UserType;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.transaction.Transactional;
import org.jboss.weld.junit5.WeldInitiator;
import org.jboss.weld.junit5.WeldSetup;
import org.jboss.weld.junit5.auto.EnableAutoWeld;
import org.jglue.cdiunit.ProducesAlternative;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@EnableAutoWeld
public class InfrastructureUserRepositoryTest {

    @WeldSetup
    public WeldInitiator weld = WeldInitiator.of( WeldInitiator
            .createWeld()
            .addBeanClass(InfrastructureUserRepository.class));

    @Produces
    @ProducesAlternative
    public EntityManager getEm(){
        return Persistence
                .createEntityManagerFactory("test")
                .createEntityManager();
    }
    protected EntityManager em;

    @Inject
    private InfrastructureUserRepository infrastructureUserRepository;

    @BeforeAll
    public void setUp() {
        User user = createTestUser();
        infrastructureUserRepository.createEntity(user);
    }


    @Test
    @Transactional
    public void findAll() {
        List<User> result = infrastructureUserRepository.findAll();
        assertNotNull(result);
        assertEquals("Testuser",result.get(0).getUserName());
        assertEquals(1, result.get(0).getUserType().get(0).getUserTypeId());
    }

    @Test
    public void createUser() {
        UUID uuid1 = UUID.fromString("35fa10da-594a-4601-a7b7-0a707a3c1ce7");
        User user1 = createTestUser();
        user1.setUserName("Insertuser");
        user1.setUserNumber(new UserNumber(uuid1));
        infrastructureUserRepository.createEntity(user1);

        List<User> result = infrastructureUserRepository.findAll();
        assertNotNull(result);
        assertEquals(2,result.size());
        assertEquals(uuid1, result.get(1).getUserNumber());
        assertEquals("Insertuser",result.get(1).getUserName());
    }

    private User createTestUser() {
        User user = new User();
        UserType userType = new UserType();
        List<UserType> userTypeList = new ArrayList<>();

        userType.setUserTypeId(1);
        userType.setUserRoleType("testrole");
        userType.setUserTypeDescription("Testuser");
        userTypeList.add(userType);

        user.setUserName("Testuser");
        user.setUserNumber(new UserNumber(UUID.fromString("586c2084-d545-4fac-b7d3-2319382df14f")));
        user.setUserType(userTypeList);

        return user;
    }
}