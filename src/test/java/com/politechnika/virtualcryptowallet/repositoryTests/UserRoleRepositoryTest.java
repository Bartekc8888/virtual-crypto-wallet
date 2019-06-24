package com.politechnika.virtualcryptowallet.repositoryTests;

import com.politechnika.virtualcryptowallet.model.User;
import com.politechnika.virtualcryptowallet.model.UserRole;
import com.politechnika.virtualcryptowallet.model.UserRoleType;
import com.politechnika.virtualcryptowallet.repository.UserRepository;
import com.politechnika.virtualcryptowallet.repository.UserRoleRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class UserRoleRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;


    @Test
    public void findUserByUsernameTest() {

        User user = new User();
        user.setUsername("adminTest");
        user.setPassword("adminTest");

        userRepository.save(user);


        UserRole userRole = new UserRole();
        userRole.setUsername("adminTest");
        userRole.setUserRoleType(UserRoleType.ADMIN);

        userRoleRepository.save(userRole);

        UserRole found = userRoleRepository.findByUsername(userRole.getUsername());
        Assert.assertEquals(userRole.getUsername(), found.getUsername());
    }
}
