package com.andrei.user;

import com.andrei.user.model.User;
import com.andrei.user.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(profiles = "test")
public class UserControllerIntegrationTest extends BaseITContext {

    @Autowired
    private UserRepository repository;

    @Test
    @Sql(scripts = "classpath:sql/user_init.sql", executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:sql/truncate.sql", executionPhase = AFTER_TEST_METHOD)
    public void shouldListUsers() throws Exception {
        response = sendGetRequest("/api/users");
        String expectedJson = getJsonAsString("json/user/listUsers.json");
        assertResponseJsonEqualsExpectedJson(expectedJson, response.getContentAsString());
    }

    @Test
    @Sql(scripts = "classpath:sql/user_init.sql", executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:sql/truncate.sql", executionPhase = AFTER_TEST_METHOD)
    public void shouldGetUserWhenUserIdIsValid() throws Exception {
        response = sendGetRequest("/api/users/1");
        String expectedJson = getJsonAsString("json/user/getUserById.json");
        assertResponseJsonEqualsExpectedJson(expectedJson, response.getContentAsString());
    }

    @Test
    @Sql(scripts = "classpath:sql/user_init.sql", executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:sql/truncate.sql", executionPhase = AFTER_TEST_METHOD)
    public void shouldDeleteUserWhenUserIdIsValid() throws Exception {
        response = sendDeleteRequest("/api/users/1");
        assertResponseStatusIsOk();
        assertEquals("User has been deleted successfully.", response.getContentAsString());
    }

    @Test
    public void shouldReturn404WhenTryDeleteUserIdIsNotValid() throws Exception {
        response = sendDeleteRequest("/api/users/111");
        assertResponseStatusIsNotFound();
    }

    @Test
    @Sql(scripts = "classpath:sql/user_init.sql", executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:sql/truncate.sql", executionPhase = AFTER_TEST_METHOD)
    public void shouldCreateUserFromPostWhenRequestBodyIsValid() throws Exception {
        long registersBeforeTest = repository.count();
        String jsonInput = getJsonAsString("json/user/createUserRequest.json");

        response = sendPostRequestWithJson("/api/users", jsonInput);

        assertEquals(repository.count(), ++registersBeforeTest);
        assertResponseStatusIsOk();
        assertEquals("New User has been saved with ID:3", response.getContentAsString());
    }

    @Test
    public void shouldNotCreateUserAndReturnBadRequest() throws Exception {
        String jsonInput = getJsonAsString("json/user/createInvalidUserRequest.json");
        response = sendPostRequestWithJson("/api/users", jsonInput);

        assertResponseStatusIsBadRequest();
        assertEquals(0L, repository.count());
    }

    @Test
    @Sql(scripts = "classpath:sql/user_init.sql", executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:sql/truncate.sql", executionPhase = AFTER_TEST_METHOD)
    public void shouldUpdateUserAndReturnOKFromPutWhenUserIdIsFound() throws Exception {
        String jsonInput = getJsonAsString("json/user/updateUserRequest.json");

        response = sendPutRequestWithJson("/api/users/1", jsonInput);
        assertResponseStatusIsOk();

        Optional<User> userOptional = repository.findById(1L);
        assertTrue(userOptional.isPresent());

        User user = userOptional.get();
        assertEquals("UPDATEFirstName", user.getFirstName());

    }

    @Test
    @Sql(scripts = "classpath:sql/user_init.sql", executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:sql/truncate.sql", executionPhase = AFTER_TEST_METHOD)
    public void shouldReturnNotFoundFromPutWhenUserIdIsNotFound() throws Exception {
        String jsonInput = getJsonAsString("json/user/updateUserRequest.json");

        response = sendPutRequestWithJson("/api/users/1111", jsonInput);
        assertResponseStatusIsNotFound();
    }

}
