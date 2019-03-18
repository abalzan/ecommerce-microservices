package com.andrei.category;

import com.andrei.category.model.Category;
import com.andrei.category.repository.CategoryRepository;
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
public class CategoryControllerIntegrationTest extends BaseITContext {

    private static final String CATEGORY_INIT_SQL = "classpath:sql/category_init.sql";
    private static final String TRUNCATE_SQL = "classpath:sql/truncate.sql";

    private static final String JSON_CATEGORY_CREATE_INVALID_CATEGORY_REQUEST_JSON = "json/category/createInvalidCategoryRequest.json";
    private static final String JSON_CATEGORY_CREATE_CATEGORY_REQUEST_JSON = "json/category/createCategoryRequest.json";
    private static final String JSON_CATEGORY_UPDATE_CATEGORY_REQUEST_JSON = "json/category/updateCategoryRequest.json";
    private static final String JSON_CATEGORY_UPDATE_CATEGORY_REQUEST_JSON1 = "json/category/updateCategoryRequest.json";
    private static final String JSON_CATEGORY_GET_CATEGORY_BY_ID_JSON = "json/category/getCategoryById.json";
    private static final String JSON_CATEGORY_LIST_CATEGORIES_JSON = "json/category/listCategories.json";

    @Autowired
    private CategoryRepository repository;

    @Test
    @Sql(scripts = CATEGORY_INIT_SQL, executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = TRUNCATE_SQL, executionPhase = AFTER_TEST_METHOD)
    public void shouldListCategories() throws Exception {
        response = sendGetRequest("/categories");
        String expectedJson = getJsonAsString(JSON_CATEGORY_LIST_CATEGORIES_JSON);
        assertResponseJsonEqualsExpectedJson(expectedJson, response.getContentAsString());
    }

    @Test
    @Sql(scripts = CATEGORY_INIT_SQL, executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = TRUNCATE_SQL, executionPhase = AFTER_TEST_METHOD)
    public void shouldGetCategoryWhenCategoryIdIsValid() throws Exception {
        response = sendGetRequest("/categories/1");
        String expectedJson = getJsonAsString(JSON_CATEGORY_GET_CATEGORY_BY_ID_JSON);
        assertResponseJsonEqualsExpectedJson(expectedJson, response.getContentAsString());
    }

    @Test
    @Sql(scripts = CATEGORY_INIT_SQL, executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = TRUNCATE_SQL, executionPhase = AFTER_TEST_METHOD)
    public void shouldDeleteCategoryWhenCategoryIdIsValid() throws Exception {
        response = sendDeleteRequest("/categories/1");
        assertResponseStatusIsOk();
        assertEquals("Category has been deleted successfully.", response.getContentAsString());
    }

    @Test
    public void shouldReturn404WhenTryDeleteCategoryIdIsNotValid() throws Exception {
        response = sendDeleteRequest("/categories/111");
        assertResponseStatusIsNotFound();
    }

    @Test
    @Sql(scripts = CATEGORY_INIT_SQL, executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = TRUNCATE_SQL, executionPhase = AFTER_TEST_METHOD)
    public void shouldCreateCategoryFromPostWhenRequestBodyIsValid() throws Exception {
        long registersBeforeTest = repository.count();
        String jsonInput = getJsonAsString(JSON_CATEGORY_CREATE_CATEGORY_REQUEST_JSON);

        response = sendPostRequestWithJson("/categories", jsonInput);

        assertEquals(repository.count(), ++registersBeforeTest);
        assertResponseStatusIsOk();
        assertEquals("New Category has been saved with ID:5", response.getContentAsString());
    }

    @Test
    public void shouldNotCreateCategoryAndReturnBadRequest() throws Exception {
        String jsonInput = getJsonAsString(JSON_CATEGORY_CREATE_INVALID_CATEGORY_REQUEST_JSON);
        response = sendPostRequestWithJson("/categories", jsonInput);

        assertResponseStatusIsBadRequest();
        assertEquals(0L, repository.count());
    }

    @Test
    @Sql(scripts = CATEGORY_INIT_SQL, executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = TRUNCATE_SQL, executionPhase = AFTER_TEST_METHOD)
    public void shouldUpdateCategoryAndReturnOKFromPutWhenCategoryIdIsFound() throws Exception {
        String jsonInput = getJsonAsString(JSON_CATEGORY_UPDATE_CATEGORY_REQUEST_JSON);

        response = sendPutRequestWithJson("/categories/1", jsonInput);
        assertResponseStatusIsOk();

        Optional<Category> categoryOptional = repository.findById(1L);
        assertTrue(Optional.ofNullable(categoryOptional).isPresent());

        Category category = categoryOptional.get();
        assertEquals("Updating Category", category.getCategoryName());

    }

    @Test
    @Sql(scripts = CATEGORY_INIT_SQL, executionPhase = BEFORE_TEST_METHOD)
    @Sql(scripts = TRUNCATE_SQL, executionPhase = AFTER_TEST_METHOD)
    public void shouldReturnNotFoundFromPutWhenCategoryIdIsNotFound() throws Exception {
        String jsonInput = getJsonAsString(JSON_CATEGORY_UPDATE_CATEGORY_REQUEST_JSON1);

        response = sendPutRequestWithJson("/categories/1111", jsonInput);
        assertResponseStatusIsNotFound();
    }

}
