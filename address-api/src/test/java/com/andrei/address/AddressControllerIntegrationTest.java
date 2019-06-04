package com.andrei.address;

import com.andrei.address.model.Address;
import com.andrei.address.repository.AddressRepository;
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
public class AddressControllerIntegrationTest extends BaseITContext {

    @Autowired
    private AddressRepository repository;

    @Test
    @Sql(scripts = "classpath:sql/address_init.sql", executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:sql/truncate.sql", executionPhase = AFTER_TEST_METHOD)
    public void shouldListAddresss() throws Exception {
        response = sendGetRequest("/api/addresses");
        String expectedJson = getJsonAsString("json/address/listAddresses.json");
        assertResponseJsonEqualsExpectedJson(expectedJson, response.getContentAsString());
    }

    @Test
    @Sql(scripts = "classpath:sql/address_init.sql", executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:sql/truncate.sql", executionPhase = AFTER_TEST_METHOD)
    public void shouldGetAddressWhenAddressIdIsValid() throws Exception {
        response = sendGetRequest("/api/addresses/1");
        String expectedJson = getJsonAsString("json/address/getAddressById.json");
        assertResponseJsonEqualsExpectedJson(expectedJson, response.getContentAsString());
    }

    @Test
    @Sql(scripts = "classpath:sql/address_init.sql", executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:sql/truncate.sql", executionPhase = AFTER_TEST_METHOD)
    public void shouldDeleteAddressWhenAddressIdIsValid() throws Exception {
        response = sendDeleteRequest("/api/addresses/1");
        assertResponseStatusIsOk();
        assertEquals("Address has been deleted successfully.", response.getContentAsString());
    }

    @Test
    public void shouldReturn404WhenTryDeleteAddressIdIsNotValid() throws Exception {
        response = sendDeleteRequest("/api/addresses/111");
        assertResponseStatusIsNotFound();
    }

    @Test
    @Sql(scripts = "classpath:sql/address_init.sql", executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:sql/truncate.sql", executionPhase = AFTER_TEST_METHOD)
    public void shouldCreateAddressFromPostWhenRequestBodyIsValid() throws Exception {
        long registersBeforeTest = repository.count();
        String jsonInput = getJsonAsString("json/address/createAddressRequest.json");

        response = sendPostRequestWithJson("/api/addresses", jsonInput);

        assertEquals(repository.count(), ++registersBeforeTest);
        assertResponseStatusIsOk();
        assertEquals("New Address has been saved with ID:3", response.getContentAsString());
    }

    @Test
    public void shouldNotCreateAddressAndReturnBadRequest() throws Exception {
        String jsonInput = getJsonAsString("json/address/createInvalidAddressRequest.json");
        response = sendPostRequestWithJson("/api/addresses", jsonInput);

        assertResponseStatusIsBadRequest();
        assertEquals(0L, repository.count());
    }

    @Test
    @Sql(scripts = "classpath:sql/address_init.sql", executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:sql/truncate.sql", executionPhase = AFTER_TEST_METHOD)
    public void shouldUpdateAddressAndReturnOKFromPutWhenAddressIdIsFound() throws Exception {
        String jsonInput = getJsonAsString("json/address/updateAddressRequest.json");

        response = sendPutRequestWithJson("/api/addresses/1", jsonInput);
        assertResponseStatusIsOk();

        Optional<Address> addressOptional = repository.findById(1L);
        assertTrue(addressOptional.isPresent());

        Address address = addressOptional.get();
        assertEquals("Updated Street", address.getStreetAddress());

    }

    @Test
    @Sql(scripts = "classpath:sql/address_init.sql", executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:sql/truncate.sql", executionPhase = AFTER_TEST_METHOD)
    public void shouldReturnNotFoundFromPutWhenAddressIdIsNotFound() throws Exception {
        String jsonInput = getJsonAsString("json/address/updateAddressRequest.json");

        response = sendPutRequestWithJson("/api/addresses/1111", jsonInput);
        assertResponseStatusIsNotFound();
    }

}
