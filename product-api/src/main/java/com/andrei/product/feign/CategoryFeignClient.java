package com.andrei.product.feign;

import com.andrei.contract.category.CategoryDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/*
 * Name needs to match with application-name configured on bootstrap.yml,
 * this annotation basically say: Feign Client go to service discovery and fetch al the endpoints
 * that belongs to category-api
 */
@FeignClient(name = "category-api")
public interface CategoryFeignClient {

    @GetMapping("/categories/{id}")
    ResponseEntity<CategoryDTO> getCategory(@PathVariable("id") long id);
}
