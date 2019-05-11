package com.andrei.product.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "PRODUCT")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
    private long id;

	@NotNull(message = "product code should not be null")
	@Column(name = "PCODE")
	private String productCode;

	@NotNull(message = "product name should not be null")
	@Column(name = "NAME")
	private String productName;

	@NotNull(message = "Short description code should not be null")
	@Column(name = "SHORT_DESCRIPTION")
	private String shortDescription;

	@NotNull(message = "Long description should not be null")
	@Column(name = "LONG_DESCRIPTION")
	private String longDescription;

	@Column(name = "CAN_DISPLAY")
	private boolean canDisplay;

	@Column(name = "IS_DELETED")
	private boolean isDeleted;

	@Column(name = "IS_AUTOMOTIVE")
	private boolean isAutomotive;

	@Column(name = "IS_INTERNATIONAL")
	private boolean isInternational;

	@OneToOne
	@JoinColumn(name = "PARENT_CATEGORY_ID")
	private Category parentCategory;

	@OneToOne
	@JoinColumn(name = "CATEGORY_ID")
	private Category category;
	
}
