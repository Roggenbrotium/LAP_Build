package com.example.lap.test;

import com.example.lap.dao.WebUser;
import com.example.lap.dao.WebUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class WebUserRepositoryTests {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private WebUserRepository repo;

    @Test
    public void testCreateUser() {
        //TODO write actual test including mock requests
        WebUser webUser = new WebUser();
        webUser.setEmail("ravikumar@gmail.com");
        webUser.setPassword("ravi2020");

        WebUser savedWebUser = repo.save(webUser);

        WebUser existWebUser = entityManager.find(WebUser.class, savedWebUser.getId());

        assertThat(webUser.getEmail()).isEqualTo(existWebUser.getEmail());
    }
}
