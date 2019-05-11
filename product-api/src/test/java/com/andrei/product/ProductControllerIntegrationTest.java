package com.andrei.product;

import com.andrei.product.model.Product;
import com.andrei.product.repository.ProductRepository;
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
public class ProductControllerIntegrationTest extends BaseITContext {

    @Autowired
    private ProductRepository repository;

    @Test
    @Sql(scripts = "classpath:sql/category_product_init.sql", executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:sql/truncate.sql", executionPhase = AFTER_TEST_METHOD)
    public void shouldListProducts() throws Exception {
        response = sendGetRequest("/products");
        String expectedJson = getJsonAsString("json/product/listProductsAndCategories.json");
        assertResponseJsonEqualsExpectedJson(expectedJson, response.getContentAsString());
    }

    @Test
    @Sql(scripts = "classpath:sql/category_product_init.sql", executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:sql/truncate.sql", executionPhase = AFTER_TEST_METHOD)
    public void shouldGetProductWhenProductIdIsValid() throws Exception {
        response = sendGetRequest("/products/1");
        String expectedJson = getJsonAsString("json/product/getProductById.json");
        assertResponseJsonEqualsExpectedJson(expectedJson, response.getContentAsString());
    }

    @Test
    @Sql(scripts = "classpath:sql/category_product_init.sql", executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:sql/truncate.sql", executionPhase = AFTER_TEST_METHOD)
    public void shouldDeleteProductWhenProductIdIsValid() throws Exception {
        response = sendDeleteRequest("/products/1");
        assertResponseStatusIsOk();
        assertEquals("Product has been deleted successfully.", response.getContentAsString());
    }

    @Test
    public void shouldReturn404WhenTryDeleteProductIdIsNotValid() throws Exception {
        response = sendDeleteRequest("/products/111");
        assertResponseStatusIsNotFound();
    }

    @Test
    @Sql(scripts = "classpath:sql/category_product_init.sql", executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:sql/truncate.sql", executionPhase = AFTER_TEST_METHOD)
    public void shouldCreateProductFromPostWhenRequestBodyIsValid() throws Exception {
        long registersBeforeTest = repository.count();
        String jsonInput = getJsonAsString("json/product/createProductRequest.json");

        response = sendPostRequestWithJson("/products", jsonInput);

        assertEquals(repository.count(), ++registersBeforeTest);
        assertResponseStatusIsOk();
        assertEquals("New Product has been saved with ID:3", response.getContentAsString());
    }

    @Test
    public void shouldNotCreateProductAndReturnBadRequest() throws Exception {
        String jsonInput = getJsonAsString("json/product/createInvalidProductRequest.json");
        response = sendPostRequestWithJson("/products", jsonInput);

        assertResponseStatusIsBadRequest();
        assertEquals(0L, repository.count());
    }

    @Test
    @Sql(scripts = "classpath:sql/category_product_init.sql", executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:sql/truncate.sql", executionPhase = AFTER_TEST_METHOD)
    public void shouldUpdateProductAndReturnOKFromPutWhenProductIdIsFound() throws Exception {
        String jsonInput = getJsonAsString("json/product/updateProductRequest.json");

        response = sendPutRequestWithJson("/products/1", jsonInput);
        assertResponseStatusIsOk();

        Optional<Product> productOptional = repository.findById(1L);
        assertTrue(productOptional.isPresent());

        Product product = productOptional.get();
        assertEquals("UPDATECODE", product.getProductCode());
        assertTrue(product.isDeleted());
        assertTrue(product.isAutomotive());
        assertTrue(product.isInternational());

    }

    @Test
    @Sql(scripts = "classpath:sql/category_product_init.sql", executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:sql/truncate.sql", executionPhase = AFTER_TEST_METHOD)
    public void shouldReturnNotFoundFromPutWhenProductIdIsNotFound() throws Exception {
        String jsonInput = getJsonAsString("json/product/updateProductRequest.json");

        response = sendPutRequestWithJson("/products/1111", jsonInput);
        assertResponseStatusIsNotFound();
    }

}
