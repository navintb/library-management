package com.codeseita.librarymanagement.repository;

import com.codeseita.librarymanagement.entity.User;
import com.codeseita.librarymanagement.type.Role;
import com.codeseita.librarymanagement.type.Status;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findByStatusAndRole_ifUserExists_returnUsers(){
        User user1 = getMockUser();
        this.testEntityManager.persist(user1);
        User user2 = getMockUser();
        user2.setStatus(Status.DELETED);
        this.testEntityManager.persist(user2);
        User user3 = getMockUser();
        user3.setRole(Role.LIBRARIAN);
        this.testEntityManager.persist(user3);
        List<User> users = this.userRepository.findByStatusAndRole(Status.ACTIVE, Role.ADMIN);
        Assert.assertNotNull(users);
        Assert.assertEquals(users.size(), 1);
        Assert.assertEquals(users.get(0).getStatus(), Status.ACTIVE);
    }

    private User getMockUser() {
        User user = new User();
        user.setRole(Role.ADMIN);
        user.setStatus(Status.ACTIVE);
        user.setUsername("navin");
        return user;
    }
}
