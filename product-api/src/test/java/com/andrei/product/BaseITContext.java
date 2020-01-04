package com.andrei.product;

import org.apache.commons.io.FileUtils;
import org.hamcrest.MatcherAssert;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.ServletResponse;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.yml")
public abstract class BaseITContext {

    private static final int HTTP_STATUS_OK = HttpStatus.OK.value();
    private static final int HTTP_STATUS_CREATED = HttpStatus.CREATED.value();
    private static final int HTTP_STATUS_BAD_REQUEST = HttpStatus.BAD_REQUEST.value();
    private static final int HTTP_STATUS_NO_CONTENT = HttpStatus.NO_CONTENT.value();
    private static final int HTTP_STATUS_NOT_FOUND = HttpStatus.NOT_FOUND.value();

    @Autowired
    private MockMvc mockMvc;

    MockHttpServletResponse response;

    protected static void assertResponseJsonEqualsExpectedJson(String expectedJson, ServletResponse response, String actualJson) throws JSONException {
        MatcherAssert.assertThat(response.getContentType(), is(MediaType.APPLICATION_JSON_UTF8_VALUE));
        JSONAssert.assertEquals(expectedJson, actualJson, true);
    }

    protected MockHttpServletResponse sendPutRequestWithJson(String requestUri, String content) throws Exception {
        return mockMvc.perform(put(requestUri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andDo(print())
                .andReturn()
                .getResponse();
    }

    protected MockHttpServletResponse sendPostRequestWithJson(String requestUri, String content) throws Exception {
        return mockMvc.perform(post(requestUri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andDo(print())
                .andReturn()
                .getResponse();
    }

    protected MockHttpServletResponse sendGetRequest(String requestUri) throws Exception {
        return mockMvc.perform(get(requestUri))
                .andDo(print())
                .andReturn()
                .getResponse();
    }

    protected MockHttpServletResponse sendDeleteRequest(String requestUri) throws Exception {
        return mockMvc.perform(delete(requestUri))
                .andDo(print())
                .andReturn()
                .getResponse();
    }

    protected String getJsonAsString(String jsonFilePath) throws IOException {
        return FileUtils.readFileToString(new ClassPathResource(jsonFilePath).getFile(), "UTF-8");
    }

    protected void assertResponseStatusIsCreated() {
        assertEquals(HTTP_STATUS_CREATED, response.getStatus());
    }

    protected void assertResponseStatusIsOk() {
        assertEquals(HTTP_STATUS_OK, response.getStatus());
    }

    protected void assertResponseStatusIsBadRequest() {
        assertEquals(HTTP_STATUS_BAD_REQUEST, response.getStatus());
    }

    protected void assertResponseStatusIsNoContent() {
        assertEquals(HTTP_STATUS_NO_CONTENT, response.getStatus());
    }

    protected void assertResponseStatusIsNotFound() {
        assertEquals(HTTP_STATUS_NOT_FOUND, response.getStatus());
    }

    protected void assertResponseJsonEqualsExpectedJson(String expectedJson, String actualJson) throws JSONException {
        JSONAssert.assertEquals(expectedJson, actualJson, true);
    }

}
