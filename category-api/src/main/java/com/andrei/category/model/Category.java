package com.andrei.category.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "CATEGORY")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

    @NotBlank(message = "Category name should not be null")
	@Column(name = "CATEGORY_NAME")
	private String categoryName;

    @NotBlank(message = "Category description should not be null")
	@Column(name = "CATEGORY_DESCRIPTION")
	private String categoryDescription;

}
