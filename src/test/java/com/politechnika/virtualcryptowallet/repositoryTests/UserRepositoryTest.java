package com.politechnika.virtualcryptowallet.repositoryTests;

import com.politechnika.virtualcryptowallet.model.User;
import com.politechnika.virtualcryptowallet.repository.UserRepository;
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
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;


    @Test
    public void findUserByUsernameTest() {

        User user = new User();
        user.setUsername("adminTest");
        user.setPassword("adminTest");

        userRepository.save(user);

        User found = userRepository.findByUsername(user.getUsername());
        Assert.assertEquals(user.getUsername(), found.getUsername());
    }
}
